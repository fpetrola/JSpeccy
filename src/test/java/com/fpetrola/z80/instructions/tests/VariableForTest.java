package com.fpetrola.z80.instructions.tests;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.cojen.maker.*;

import java.util.Map;
import java.util.TreeMap;

public class VariableForTest implements Variable {
  public final Object type;
  public String name;
  private MultiValuedMap<String, Object> operations = new ArrayListValuedHashMap<>();
  private static int tempCounter;

  public VariableForTest(Object type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type + " " + name + " = {" + toStringOperations() + "}";
  }

  private String toStringOperations() {
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, Object> entries : operations.entries()) {
      Object value = entries.getValue();
      value = value == this ? "this" : value;
      result.append(entries.getKey() + " " + value);
    }
    return result.toString();
  }

  @Override
  public Class<?> classType() {
    return (Class<?>) type;
  }

  @Override
  public ClassMaker makerType() {
    return null;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public Variable name(String s) {
    this.name = s;
    return this;
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
    operations.put("set", o);
    return this;
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
    operations.put("inc", o);
  }

  @Override
  public Variable add(Object o) {
    VariableForTest variableForTest = new VariableForTest(type);
    variableForTest.operations.put("add", o);
    variableForTest.name("temp" + ++tempCounter);
    return variableForTest;
  }

  @Override
  public Variable sub(Object o) {
    operations.put("sub", o);
    return this;
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
    operations.put("and", o);
    return this;
  }

  @Override
  public Variable or(Object o) {
    operations.put("or", o);
    return this;
  }

  @Override
  public Variable xor(Object o) {
    operations.put("xor", o);
    return this;
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
