package jmce;

import com.fpetrola.z80.OOZ80;

import jmce.sim.CPU;
import jmce.sim.Hardware;
import jmce.sim.Memory;
import jmce.sim.MemoryReadListener;
import jmce.sim.MemoryWriteListener;
import jmce.sim.SIMException;
import z80core.IZ80;

public class MemoryImpl implements Memory {

  private OOZ80 z80;

  public MemoryImpl(OOZ80 z80) {
    this.z80 = z80;
  }

  @Override
  public void registerCPU(CPU cpu) throws SIMException {
    // TODO Auto-generated method stub

  }

  @Override
  public int idle() throws SIMException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setName(String name) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void init(Hardware parent) throws SIMException {
    // TODO Auto-generated method stub

  }

  @Override
  public void initSwing(Hardware parent) throws SIMException {
    // TODO Auto-generated method stub

  }

  @Override
  public void reset() throws SIMException {
    // TODO Auto-generated method stub

  }

  @Override
  public void destroy() throws SIMException {
    // TODO Auto-generated method stub

  }

  @Override
  public Hardware getParent() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Hardware addHardware(Hardware h) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getHardwareCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void removeHardware(Hardware h) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeHardware(int n) {
    // TODO Auto-generated method stub

  }

  @Override
  public Object[] getHardwareInstances(Class c) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Hardware getHardwareTree(Class... classes) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Hardware getHardware(Class clazz) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Hardware getHardware(Class clazz, int n) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setHardware(int n, Hardware h) {
    // TODO Auto-generated method stub

  }

  @Override
  public Hardware getHardware(int n) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Hardware[] getHardware() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setHardware(Hardware[] h) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setMemory(int address, int value) throws SIMException {
    // TODO Auto-generated method stub

  }

  @Override
  public int getMemory(int address) throws SIMException {
    return z80.readMemoryAt(address);
  }

  @Override
  public int getSize() {
    // TODO Auto-generated method stub
    return 1000;
  }

  @Override
  public void setSize(int size) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addMemoryWriteListener(MemoryWriteListener l) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addMemoryWriteListener(int address, MemoryWriteListener l) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeMemoryWriteListener(MemoryWriteListener l) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeMemoryWriteListener(int a, MemoryWriteListener l) {
    // TODO Auto-generated method stub

  }

  @Override
  public int getMemoryWriteListenerCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getMemoryWriteListenerCount(int a) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public MemoryWriteListener getMemoryWriteListenerAt(int i) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MemoryWriteListener getMemoryWriteListenerAt(int i, int a) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addMemoryReadListener(MemoryReadListener l) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeMemoryReadListener(MemoryReadListener l) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeMemoryReadListener(int a, MemoryReadListener l) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addMemoryReadListener(int a, MemoryReadListener l) {
    // TODO Auto-generated method stub

  }

  @Override
  public int getMemoryReadListenerCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getMemoryReadListenerCount(int a) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public MemoryReadListener getMemoryReadListenerAt(int i) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MemoryReadListener getMemoryReadListenerAt(int i, int a) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setMemoryName(int address, String name) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getMemoryName(int address) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setBit(int a, int mask) throws SIMException {
    // TODO Auto-generated method stub

  }

  @Override
  public void clrBit(int a, int mask) throws SIMException {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isBit(int a, int mask) throws SIMException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setReadOnly(int start, int len) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setReadOnly(int address) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setReadOnly() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean getReadOnly(int add) {
    // TODO Auto-generated method stub
    return false;
  }

}
