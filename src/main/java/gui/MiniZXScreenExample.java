package gui;

import com.fpetrola.z80.minizx.MiniZX;
import com.fpetrola.z80.opcodes.references.WordNumber;

import javax.swing.*;
import java.util.Random;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;

public class MiniZXScreenExample<T extends WordNumber> extends MiniZX.MiniZXScreen {

  private int ballX = 128;  // Initial X position of the ball (in pixels)
  private int ballY = 96;   // Initial Y position of the ball (in pixels)
  private int ballDirectionX = 2;  // X-axis movement direction
  private int ballDirectionY = 2;  // Y-axis movement direction
  private final Random random = new Random();
  private int colorAttribute;

  public MiniZXScreenExample(int[] screenMemory) {
    super(screenMemory);

    // Timer to update the screen every 20ms (for smooth animation)
    new Timer(160, e -> {
      updateScreenMemory();
      updateBallPosition();
    }).start();
  }

  private void updateBallPosition() {
    // Move the ball
    ballX += ballDirectionX;
    ballY += ballDirectionY;

    // Bounce the ball off the edges
    if (ballX <= 0 || ballX >= 248) {
      ballDirectionX *= -1;
      changeBallColor();
    }
    if (ballY <= 0 || ballY >= 184) {
      ballDirectionY *= -1;
      changeBallColor();
    }

    updateColor();
  }

  private void changeBallColor() {
    // Randomize the color of the ball
    int newColor = random.nextInt(8); // ZX Spectrum has 8 basic colors
    int bright = random.nextBoolean() ? 0x40 : 0x00; // Randomly choose bright or normal
    colorAttribute = newColor | bright;

    updateColor();
  }

  private void updateColor() {
    // Update the attribute memory for the ball area
    int attributeX = ballX / 8;
    int attributeY = ballY / 8;
    //  colorAttribute= 0x33;
    screenMemory[16384 + 6144 + (attributeY * 32 + attributeX)] = colorAttribute;
  }

  private void updateScreenMemory() {
    // Clear the screen memory
    for (int i = 0; i < 22528; i++) {
      screenMemory[i] = createValue(0);
    }

    // Draw the ball (8x8 pixels) in the screen memory with pixel-level shifting
    int xByte = ballX / 8;        // X position in bytes (8 pixels per byte)
    int xOffset = ballX % 8;      // X position within the byte (pixel offset)
    int ballPatternShifted;

    for (int row = 0; row < 8; row++) {
      int screenAddress = getScreenMemoryAddress(xByte, ballY + row);

      // Shift the ball sprite to align with the pixel offset
      ballPatternShifted = ((getBallPattern(row) & 0xFF) >> xOffset);
      if (xByte < 31) {
        screenMemory[screenAddress] = screenMemory[screenAddress] | ballPatternShifted & 0xFF;
        screenMemory[screenAddress + 1] = screenMemory[screenAddress + 1] | (getBallPattern(row) << (8 - xOffset));
      } else {
        screenMemory[screenAddress] = screenMemory[screenAddress] | ballPatternShifted;
      }
    }
  }

  private int getScreenMemoryAddress(int xByte, int y) {
    int block = (y / 64) * 2048;  // Determine block: top (0), middle (1), bottom (2)
    int lineInBlock = (y % 8) * 256;
    int row = ((y % 64) / 8) * 32;
    return 16384 + block + row + lineInBlock + xByte;
  }

  private byte getBallPattern(int row) {
    switch (row) {
      case 0:
        return (byte) 0b00111100;
      case 1:
        return (byte) 0b01111110;
      case 2:
        return (byte) 0b11111111;
      case 3:
        return (byte) 0b11111111;
      case 4:
        return (byte) 0b11111111;
      case 5:
        return (byte) 0b11111111;
      case 6:
        return (byte) 0b01111110;
      case 7:
        return (byte) 0b00111100;
      default:
        return 0;
    }
  }

  public static void main(String[] args) {
    // Use 0x10000 (65536) for the arrays' initial length
    int[] screenMemory = new int[0x10000];  // Allocate 65536 bytes for screen memory
    byte[] attributeMemory = new byte[0x10000]; // Allocate 65536 bytes for attribute memory

    // Initialize the frame and show it
    JFrame frame = new JFrame("ZX Spectrum Bouncing Ball Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new MiniZXScreenExample(screenMemory));
    frame.pack();
    frame.setVisible(true);
  }
}
