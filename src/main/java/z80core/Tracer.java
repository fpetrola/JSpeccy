package z80core;

import java.util.Arrays;

public class Tracer {

	private int[][] traceMemory = new int[100000000][];

	public int getregAAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregBAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregCAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregDAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregEAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregFAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregHAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregLAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregIAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregRAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregIXhAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getregIXlAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addTrace8Bit(int destinationAddress, int sourceAddress) {
		long nanoTime = System.nanoTime();

		while (System.nanoTime() - nanoTime < 600L)
			;

		for (int i = 0; i < 8; i++) {
			int[] src = traceMemory[sourceAddress * 8 + i];
			int[] dest = traceMemory[destinationAddress * 8 + i];
			if (dest == null)
				src = new int[0];
			if (dest == null)
				dest = new int[0];
			int destLength = dest.length;
			dest = Arrays.copyOf(dest, destLength + src.length);
			System.arraycopy(src, 0, dest, destLength, src.length);
		}
	}
}
