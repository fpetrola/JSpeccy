package jmce;

import com.fpetrola.z80.OOZ80;

import jmce.sim.BreakPoint;
import jmce.sim.CPU;
import jmce.sim.CycleListener;
import jmce.sim.Decoder;
import jmce.sim.ExceptionListener;
import jmce.sim.Hardware;
import jmce.sim.Interrupt;
import jmce.sim.LoadInfo;
import jmce.sim.Memory;
import jmce.sim.MemoryReadListener;
import jmce.sim.MemoryWriteListener;
import jmce.sim.Register;
import jmce.sim.ResetListener;
import jmce.sim.SIMException;
import jmce.sim.TraceListener;
import jmce.sim.cpu.MultiOpcode;
import jmce.util.Timer;

public class CPUImplementation implements CPU {
  private OOZ80 z80;
  private MemoryImpl memory;

  public CPUImplementation(OOZ80 z80) {
    this.z80 = z80;
    memory = new MemoryImpl(z80);
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setName(String name) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setHardware(Hardware[] h) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setHardware(int n, Hardware h) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void reset() throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeHardware(int n) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeHardware(Hardware h) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void initSwing(Hardware parent) throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void init(Hardware parent) throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Hardware getParent() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getName() {
    return "CPU1";
  }

  @Override
  public Hardware getHardwareTree(Class... classes) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object[] getHardwareInstances(Class c) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getHardwareCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Hardware[] getHardware() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Hardware getHardware(int n) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Hardware getHardware(Class clazz, int n) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Hardware getHardware(Class clazz) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void destroy() throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Hardware addHardware(Hardware h) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void stop() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int step() throws SIMException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void start() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setWord(int a, int v) throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setTill(int address) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setStatusLine(char c) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setResetAddress(int address) throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setRealTime(boolean mode) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setIOByte(int a, int v) throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setEndian(int n) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setClockPerCycle(int n) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setClock(long clock) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setByte(int a, int v) throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeTraceListener(TraceListener t) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeExceptionListener(ExceptionListener l) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeBreakPoint(BreakPoint b) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeBreakPoint(int i) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void pc(int value) throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int pc() throws SIMException {
    // TODO Auto-generated method stub
    return 10;
  }

  @Override
  public void notifyInterrupt(Interrupt isr) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void load(String name, int base, LoadInfo info) throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void load(Memory m, String name, int base, LoadInfo info) throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isRunning() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isLittleEndian() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isInterruptEnabled() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isBigEndian() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int idle() throws SIMException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getWord(int a) throws SIMException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getUsageDesc() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double getUsage() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getResetAddress() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Register getRegisterForName(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getRegisterCount() {
    return 0;
  }

  @Override
  public Register getRegisterAt(int i) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean getRealTime() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Memory getMemoryForName(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getMemoryCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Memory getMemoryAt(int i) {
    return getMemory();
  }

  @Override
  public Memory getMemory() {
    return memory;
  }

  @Override
  public int getLenghtAt(int pc) throws SIMException {
    // TODO Auto-generated method stub
    return z80.getLenghtAt(pc);
  }

  @Override
  public int getIOByte(int a) throws SIMException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getExceptionListenerCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public ExceptionListener getExceptionListenerAt(int i) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getEndian() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getDecoderCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Decoder getDecoderAt(int i) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long getCycleMillis() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public long getCycle() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getClockPerCycle() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public long getClock() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getByte(int a) throws SIMException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getBreakPointCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public BreakPoint getBreakPointAt(int i) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int fireISR(Interrupt isr) throws SIMException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int fetch(int pc) throws SIMException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void dumpStatistics(String filename) throws SIMException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public String decodeAt(int pc) throws SIMException {
    // TODO Auto-generated method stub
    return z80.decodeAt(pc);
  }

  @Override
  public BreakPoint addWriteBreakPoint(int m, int a) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addTraceListener(TraceListener t) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addTimerMs(Timer t) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addTimerCycle(Timer t) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addResetListener(ResetListener l) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Register addRegister(Register r) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BreakPoint addReadBreakPoint(int m, int a) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addMemoryWriteListener(MemoryWriteListener l) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addInterrupt(Interrupt isr) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addIOWriteListener(int a, MemoryWriteListener l) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addIOWriteListener(MemoryWriteListener l) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addIOReadListener(MemoryReadListener l) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addIOReadListener(int a, MemoryReadListener l) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public BreakPoint addExecBreakPoint(int m, int a) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addExceptionListener(ExceptionListener l) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Decoder addDecoder(Decoder d) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addCycleListener(CycleListener l) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void abort(String s) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int getInterruptCounter(int n) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getInterruptCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getInterruptName(int i) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MultiOpcode getOpcodes() {
    // TODO Auto-generated method stub
    return null;
  }
}