package com.fpetrola.z80.minizx;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

@SuppressWarnings("ALL")
public abstract class MiniZX extends SpectrumApplication {

  public MiniZX() {
    init();
  }

  public void init() {
    mem = new int[0x10000];
    createScreen();
    byte[] bytes = getProgramBytes();
    for (int i = 0; i < 0x10000; i++) {
      mem[i] = bytes[i];
    }
  }

  protected abstract byte[] getProgramBytes();

  public byte[] gzipDecompressFromBase64(final String content) {
    if (StringUtils.isBlank(content)) {
      throw new IllegalArgumentException("content is either null or blank");
    }

    byte[] decode = Base64.getDecoder().decode(content.getBytes(UTF_8));
    try (ByteArrayInputStream bis = new ByteArrayInputStream(decode)) {
      try (GZIPInputStream gis = new GZIPInputStream(bis)) {
        byte[] bytes = gis.readAllBytes();
        return bytes;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void createScreen() {
    JFrame frame = new JFrame("Mini ZX Spectrum");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new MiniZXScreen(mem));
    frame.pack();
    frame.setVisible(true);
    frame.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent e) {
      }

      public void keyPressed(KeyEvent e) {
        io.setCurrentKey(e.getKeyCode(), true);
      }

      public void keyReleased(KeyEvent e) {
        io.setCurrentKey(e.getKeyCode(), false);
      }
    });
  }

  public static class MiniZXIO {
    private int[] ports = initPorts();

    public MiniZXIO() {
    }

    public synchronized Integer in(Integer port) {
      if (port.intValue() == 31) {
        ports[31] |= 32;
      }
      int port1 = ports[port.intValue()];
      if (port1 != 0) {
        //      System.out.println(port1);
        return port1;
      } else {
        if (port.intValue() == 31)
          return 0;
        else
          return 191;
      }
    }

    public void out(Integer port, Integer value) {
    }

    public void setCurrentKey(int e, boolean pressed) {
      if (KeyEvent.VK_RIGHT == e) {
        activateKey(1, pressed);
      } else if (KeyEvent.VK_LEFT == e) {
        activateKey(2, pressed);
      } else if (KeyEvent.VK_UP == e) {
        activateKey(8, pressed);
      } else if (KeyEvent.VK_DOWN == e) {
        activateKey(4, pressed);
      } else if (KeyEvent.VK_SPACE == e) {
        activateKey(16, pressed);
      } else if (KeyEvent.VK_ENTER == e) {
        ports[49150] = pressed ? 255 : 191;
        ports[45054] = pressed ? 255 : 0;

      }
    }

    private void activateKey(int i, boolean pressed) {
      if (pressed)
        ports[31] |= i;
      else {
        int i1 = ~i;
        ports[31] &= i1;
      }

      ports[32510] = 255;
      ports[61438] = 255;
    }

    private int[] initPorts() {
      int[] ports = new int[0x10000];
      Arrays.fill(ports, 0);
      //    ports[65278]= 191;
      //    ports[32766]= 191;
      //    ports[65022]= 191;
      //    ports[49150]= 191;
      //    ports[61438]= 191;
      //    ports[64510]= 191;
      //    ports[59390]= 191;
      //    ports[59390]= 191;
      //    ports[59390]= 191;
      return ports;
    }
  }

  public static class MiniZXScreen extends JPanel {

    protected final int[] screenMemory;
    protected final byte[] newScreen;
    protected boolean flashState = false;
    private double zoom = 2;
    Color[] colors = {Color.BLACK, Color.BLUE, Color.RED, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.YELLOW, Color.WHITE};

    public MiniZXScreen(int[] screenMemory) {
      this.screenMemory = screenMemory;
      this.newScreen = new byte[256 * 192];
      setPreferredSize(new Dimension((int) (256 * zoom), (int) (192 * zoom)));

      new Timer(10, e -> {
        flashState = !flashState;
        convertScreen();
        repaint();
      }).start();

      this.addComponentListener(new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          zoom = (e.getComponent().getSize().getWidth() / 256f);
        }
      });
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g.create();

      double width = getWidth();
      double height = getHeight();

      double zoomWidth = width * zoom;
      double zoomHeight = height * zoom;

      double anchorx = (width - zoomWidth) / 2f;
      double anchory = (height - zoomHeight) / 2f;

      AffineTransform at = new AffineTransform();
      //at.translate(anchorx, anchory);
      at.scale(zoom, zoom);
      //  at.translate(-10 * zoom, -10 * zoom);

      g2d.setTransform(at);

      for (int i = 0; i < newScreen.length; i++) {
        double x = i % 256;
        double y = i / 256;

        int zxColorCode = newScreen[i];
        g2d.setColor(zxColorCode >= 8 ? colors[zxColorCode - 8] : colors[zxColorCode].darker());
        g2d.fillRect((int) x, (int) y, 1, 1);
      }
      g2d.dispose();
    }

    protected void convertScreen() {
      Arrays.fill(newScreen, (byte) 0);

      for (int block = 0; block < 3; block++) {
        int blockAddrOffset = block * 2048;
        int address = 0;
        int line = 0;
        int offset = 0;

        for (int byteRow = 0; byteRow < 2048; byteRow += 32) {
          for (int b = 0; b < 32; b++) {
            byte bite = (byte) screenMemory[16384 + blockAddrOffset + byteRow + b];

            byte[] pixels = byteToBits(bite);
            for (int pixel = 7; pixel >= 0; pixel--) {
              writeColourPixelToNewScreen(pixels[pixel], blockAddrOffset * 8 + address);
              address++;
            }
          }

          address += 256 * 7;
          line += 1;

          if (line == 8) {
            line = 0;
            offset += 1;
            address = offset * 256;
          }
        }
      }
    }

    protected void writeColourPixelToNewScreen(byte pixel, int newScreenAddress) {
      Colour colour = Colour.colourFromAttribute((byte) screenMemory[22528 + (newScreenAddress / 2048) * 32 + (newScreenAddress / 8) % 32]);

      byte paperColour = colour.PAPER;
      byte inkColour = colour.INK;

      if (colour.FLASH && flashState) {
        byte newINK = paperColour;
        paperColour = inkColour;
        inkColour = newINK;
      }

      byte colourID = paperColour;
      if (pixel == 1) {
        colourID = inkColour;
      }

      if (colour.BRIGHT) {
        colourID += 8;
      }

      if (newScreen[newScreenAddress] != colourID) {
        newScreen[newScreenAddress] = colourID;
      }
    }

    protected byte[] byteToBits(byte b) {
      byte[] bits = new byte[8];
      for (int i = 0; i < 8; i++) {
        bits[i] = (byte) ((b >> i) & 1);
      }
      return bits;
    }

    protected static class Colour {
      boolean FLASH;
      boolean BRIGHT;
      byte PAPER;
      byte INK;

      protected static Colour colourFromAttribute(byte attribute) {
        Colour colour = new Colour();

        colour.FLASH = (attribute & 0x80) != 0;
        colour.BRIGHT = (attribute & 0x40) != 0;
        colour.PAPER = (byte) ((attribute >> 3) & 0x07);
        colour.INK = (byte) (attribute & 0x07);

        return colour;
      }
    }
  }
}
