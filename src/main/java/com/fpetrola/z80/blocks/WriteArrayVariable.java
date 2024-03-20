package com.fpetrola.z80.blocks;

import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import org.cojen.maker.*;

public class WriteArrayVariable implements Variable {
  private final ByteCodeGenerator byteCodeGenerator;
  private final Field field;
  private final MemoryPlusRegister8BitReference source1;

  public WriteArrayVariable(ByteCodeGenerator byteCodeGenerator, Field field, MemoryPlusRegister8BitReference source1) {
    this.byteCodeGenerator = byteCodeGenerator;
    this.field = field;
    this.source1 = source1;
  }

  @Override
  public Class<?> classType() {
    return null;
  }

  @Override
  public ClassMaker makerType() {
    return null;
  }

  @Override
  public String name() {
    return null;
  }

  @Override
  public Variable name(String s) {
    return null;
  }

  @Override
  public Variable signature(Object... objects) {
    return null;
  }

  @Override
  public AnnotationMaker addAnnotation(Object o, boolean b) {
    return null;
  }

  @Override
  public Variable clear() {
    return null;
  }

  @Override
  public Variable set(Object o) {
    byteCodeGenerator.memory.aset(field.add(source1.fetchRelative()), o);
    return null;
  }

  @Override
  public Variable setExact(Object o) {
    return null;
  }

  @Override
  public Variable get() {
    return null;
  }

  @Override
  public void ifTrue(Label label) {

  }

  @Override
  public void ifFalse(Label label) {

  }

  @Override
  public void ifEq(Object o, Label label) {

  }

  @Override
  public void ifNe(Object o, Label label) {

  }

  @Override
  public void ifLt(Object o, Label label) {

  }

  @Override
  public void ifGe(Object o, Label label) {

  }

  @Override
  public void ifGt(Object o, Label label) {

  }

  @Override
  public void ifLe(Object o, Label label) {

  }

  @Override
  public void switch_(Label label, int[] ints, Label... labels) {

  }

  @Override
  public void switch_(Label label, String[] strings, Label... labels) {

  }

  @Override
  public void switch_(Label label, Enum<?>[] enums, Label... labels) {

  }

  @Override
  public void switch_(Label label, Object[] objects, Label... labels) {

  }

  @Override
  public void inc(Object o) {

  }

  @Override
  public Variable add(Object o) {
    return null;
  }

  @Override
  public Variable sub(Object o) {
    return null;
  }

  @Override
  public Variable mul(Object o) {
    return null;
  }

  @Override
  public Variable div(Object o) {
    return null;
  }

  @Override
  public Variable rem(Object o) {
    return null;
  }

  @Override
  public Variable eq(Object o) {
    return null;
  }

  @Override
  public Variable ne(Object o) {
    return null;
  }

  @Override
  public Variable lt(Object o) {
    return null;
  }

  @Override
  public Variable ge(Object o) {
    return null;
  }

  @Override
  public Variable gt(Object o) {
    return null;
  }

  @Override
  public Variable le(Object o) {
    return null;
  }

  @Override
  public Variable instanceOf(Object o) {
    return null;
  }

  @Override
  public Variable cast(Object o) {
    return null;
  }

  @Override
  public Variable not() {
    return null;
  }

  @Override
  public Variable and(Object o) {
    return null;
  }

  @Override
  public Variable or(Object o) {
    return null;
  }

  @Override
  public Variable xor(Object o) {
    return null;
  }

  @Override
  public Variable shl(Object o) {
    return null;
  }

  @Override
  public Variable shr(Object o) {
    return null;
  }

  @Override
  public Variable ushr(Object o) {
    return null;
  }

  @Override
  public Variable neg() {
    return null;
  }

  @Override
  public Variable com() {
    return null;
  }

  @Override
  public Variable box() {
    return null;
  }

  @Override
  public Variable unbox() {
    return null;
  }

  @Override
  public Variable alength() {
    return null;
  }

  @Override
  public Variable aget(Object o) {
    return null;
  }

  @Override
  public void aset(Object o, Object o1) {

  }

  @Override
  public Field field(String s) {
    return null;
  }

  @Override
  public Variable invoke(String s, Object... objects) {
    return null;
  }

  @Override
  public Variable invoke(Object o, String s, Object[] objects, Object... objects1) {
    return null;
  }

  @Override
  public Variable methodHandle(Object o, String s, Object... objects) {
    return null;
  }

  @Override
  public Bootstrap indy(String s, Object... objects) {
    return null;
  }

  @Override
  public Bootstrap condy(String s, Object... objects) {
    return null;
  }

  @Override
  public void throw_() {

  }

  @Override
  public void monitorEnter() {

  }

  @Override
  public void monitorExit() {

  }

  @Override
  public void synchronized_(Runnable runnable) {

  }

  @Override
  public MethodMaker methodMaker() {
    return null;
  }
}
