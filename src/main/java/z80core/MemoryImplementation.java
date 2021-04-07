package z80core;

import java.util.HashMap;
import java.util.Map;

import com.mxgraph.model.mxCell;
import com.pretosmind.emu.z80.GraphFrame;
import com.pretosmind.emu.z80.mmu.Memory;

final class MemoryImplementation implements Memory {
	private MemIoOps memory;
	private GraphFrame graph;

	public MemoryImplementation(MemIoOps memory) {
		this.memory = memory;
	}

	@Override
	public int read(int address, boolean log) {
//		if (log)
//			System.out.println("read memory: " + address);
		return memory.peek8(address);
	}

	@Override
	public void write(int address, int value) {
		
//		if (address >= 16384 && address <= 16384 + 6144) {
//			System.out.println("pantalla!");
//		}
	}

	@Override
	public void setGraph(GraphFrame graph) {
		this.graph = graph;
		// TODO Auto-generated method stub

	}
}