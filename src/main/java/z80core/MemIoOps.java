/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package z80core;

import emulator.BitContainer;

/**
 * 
 * @author jsanchez
 */
public interface MemIoOps {
    int fetchOpcode(int address);

    BitContainer peek8(int address);
    void poke8(int address, BitContainer value);
    BitContainer peek16(int address); 
    void poke16(int address, BitContainer word);

    int inPort(int port);
    void outPort(int port, int value);

    void contendedStates(int address, int tstates);
}
