package com.fpetrola.z80.minizx;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.opcodes.references.WordNumber;
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
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Function;
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
    frame.setContentPane(new MiniZXScreen(getMemFunction()));
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

  protected Function<Integer, Integer> getMemFunction() {
    return index -> mem[index];
  }

  public static class MiniZXIO implements IO<WordNumber> {
    private int[] ports = initPorts();
    private LinkedList<PortInput> lastEmuInputs = new LinkedList<>();
    private LinkedList<PortInput> lastJavaInputs = new LinkedList<>();

    public MiniZXIO() {
    }

    public synchronized WordNumber in(WordNumber port) {
      return processLastInputs(port, lastEmuInputs, lastJavaInputs);
    }

    private WordNumber in0(WordNumber port) {
      int portNumber = port.intValue();
      //  portNumber = portNumber & 0xff;
      if (portNumber == 31) {
        ports[31] |= 32;
      }
      int port1 = ports[portNumber];
      if (port1 != 0) {
        //      System.out.println(port1);
        return WordNumber.createValue(port1);
      } else {
        if (portNumber == 31)
          return WordNumber.createValue(0);
        else
          return WordNumber.createValue(191);
      }
    }

    public void out(WordNumber port, WordNumber value) {
    }

    public void setCurrentKey(int e, boolean pressed) {
      if (KeyEvent.VK_RIGHT == e) {
        ports[getAnInt(61438)] = pressed ? 187 : 255;
        activateKey(1, pressed);
      } else if (KeyEvent.VK_LEFT == e) {
        activateKey(2, pressed);
        ports[getAnInt(61438)] = pressed ? 175 : 255;
        ports[getAnInt(59390)] = pressed ? 175 : 255;
      } else if (KeyEvent.VK_UP == e) {
        activateKey(8, pressed);
      } else if (KeyEvent.VK_DOWN == e) {
        activateKey(4, pressed);
      } else if (KeyEvent.VK_SPACE == e) {
        activateKey(16, pressed);
        ports[getAnInt(61438)] = pressed ? 254 : 255;
      } else if (KeyEvent.VK_ENTER == e) {
        ports[getAnInt(49150)] = pressed ? 254 : 255;
        ports[getAnInt(45054)] = pressed ? 190 : 255;
        ports[getAnInt(61438)] = pressed ? 254 : 255;
      }
    }

    private int getAnInt(int i) {
      //System.out.println(i & 0xff);
      return i;
    }

    private void activateKey(int i, boolean pressed) {
      if (pressed)
        ports[31] |= i;
      else {
        int i1 = ~i;
        ports[31] &= i1;
      }
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

    public synchronized WordNumber in2(WordNumber port) {
      return processLastInputs(port, lastJavaInputs, lastEmuInputs);
    }

    private WordNumber processLastInputs(WordNumber port, LinkedList<PortInput> ownInputs, LinkedList<PortInput> otherInputs) {
      if (otherInputs.isEmpty()) {
        WordNumber in = in0(port);
        ownInputs.offer(new PortInput(port, in));
        return in;
      } else {
        PortInput pop = otherInputs.poll();
        if (pop.port.intValue() != port.intValue())
          System.out.println("port!");

        return pop.result;
      }
    }
  }

  public static class MiniZXScreen extends JPanel {

    protected final Function<Integer, Integer> screenMemory;
    protected final byte[] newScreen;
    protected boolean flashState = false;
    private double zoom = 2;
    Color[] colors = {Color.BLACK, Color.BLUE, Color.RED, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.YELLOW, Color.WHITE};

    public MiniZXScreen(Function<Integer, Integer> screenMemory) {
      this.screenMemory = screenMemory;
      this.newScreen = new byte[256 * 192];
      setPreferredSize(new Dimension((int) (256 * zoom), (int) (192 * zoom)));

      new Timer(60, e -> {
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
            byte bite = (byte) screenMemory.apply(16384 + blockAddrOffset + byteRow + b).intValue();

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
      Colour colour = Colour.colourFromAttribute((byte) screenMemory.apply(22528 + (newScreenAddress / 2048) * 32 + (newScreenAddress / 8) % 32).intValue());

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
