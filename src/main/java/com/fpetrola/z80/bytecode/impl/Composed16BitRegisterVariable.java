package com.fpetrola.z80.bytecode.impl;

import org.cojen.maker.*;

public class Composed16BitRegisterVariable implements Variable {
  private MethodMaker methodMaker;
  private String name;
  Variable delegate;

  public Composed16BitRegisterVariable(Variable delegate) {
  }

  public Composed16BitRegisterVariable(MethodMaker methodMaker, String name) {
    this.methodMaker = methodMaker;
    this.name = name;
  }

  private void createDelegate() {
    this.delegate = methodMaker.invoke(name);
  }

  @Override
  public Variable set(Object value) {
    return delegate = methodMaker.invoke(name, value);
//    return delegate.set(value);
  }

  @Override
  public Class<?> classType() {
    //createDelegate();
    return int.class;
  }

  @Override
  public ClassMaker makerType() {
    return delegate.makerType();
  }

  @Override
  public String name() {
    return delegate.name();
  }

  @Override
  public Variable name(String name) {
    return delegate.name(name);
  }

  @Override
  public Variable signature(Object... components) {
    return delegate.signature(components);
  }

  @Override
  public AnnotationMaker addAnnotation(Object annotationType, boolean visible) {
    return delegate.addAnnotation(annotationType, visible);
  }

  @Override
  public Variable clear() {
    return delegate.clear();
  }

  @Override
  public Variable setExact(Object value) {
    return delegate.setExact(value);
  }

  @Override
  public Variable get() {
    createDelegate();
    return delegate;
  }

  @Override
  public void ifTrue(Label label) {
    delegate.ifTrue(label);
  }

  @Override
  public void ifTrue(Runnable then) {
    delegate.ifTrue(then);
  }

  @Override
  public void ifTrue(Runnable then, Runnable else_) {
    delegate.ifTrue(then, else_);
  }

  @Override
  public void ifFalse(Label label) {
    delegate.ifFalse(label);
  }

  @Override
  public void ifFalse(Runnable then) {
    delegate.ifFalse(then);
  }

  @Override
  public void ifFalse(Runnable then, Runnable else_) {
    delegate.ifFalse(then, else_);
  }

  @Override
  public void ifEq(Object value, Label label) {
    delegate.ifEq(value, label);
  }

  @Override
  public void ifEq(Object value, Runnable then) {
    delegate.ifEq(value, then);
  }

  @Override
  public void ifEq(Object value, Runnable then, Runnable else_) {
    delegate.ifEq(value, then, else_);
  }

  @Override
  public void ifNe(Object value, Label label) {
    delegate.ifNe(value, label);
  }

  @Override
  public void ifNe(Object value, Runnable then) {
    delegate.ifNe(value, then);
  }

  @Override
  public void ifNe(Object value, Runnable then, Runnable else_) {
    delegate.ifNe(value, then, else_);
  }

  @Override
  public void ifLt(Object value, Label label) {
    delegate.ifLt(value, label);
  }

  @Override
  public void ifLt(Object value, Runnable then) {
    delegate.ifLt(value, then);
  }

  @Override
  public void ifLt(Object value, Runnable then, Runnable else_) {
    delegate.ifLt(value, then, else_);
  }

  @Override
  public void ifGe(Object value, Label label) {
    delegate.ifGe(value, label);
  }

  @Override
  public void ifGe(Object value, Runnable then) {
    delegate.ifGe(value, then);
  }

  @Override
  public void ifGe(Object value, Runnable then, Runnable else_) {
    delegate.ifGe(value, then, else_);
  }

  @Override
  public void ifGt(Object value, Label label) {
    delegate.ifGt(value, label);
  }

  @Override
  public void ifGt(Object value, Runnable then) {
    delegate.ifGt(value, then);
  }

  @Override
  public void ifGt(Object value, Runnable then, Runnable else_) {
    delegate.ifGt(value, then, else_);
  }

  @Override
  public void ifLe(Object value, Label label) {
    delegate.ifLe(value, label);
  }

  @Override
  public void ifLe(Object value, Runnable then) {
    delegate.ifLe(value, then);
  }

  @Override
  public void ifLe(Object value, Runnable then, Runnable else_) {
    delegate.ifLe(value, then, else_);
  }

  @Override
  public void switch_(Label defaultLabel, int[] cases, Label... labels) {
    delegate.switch_(defaultLabel, cases, labels);
  }

  @Override
  public void switch_(Label defaultLabel, String[] cases, Label... labels) {
    delegate.switch_(defaultLabel, cases, labels);
  }

  @Override
  public void switch_(Label defaultLabel, Enum<?>[] cases, Label... labels) {
    delegate.switch_(defaultLabel, cases, labels);
  }

  @Override
  public void switch_(Label defaultLabel, Object[] cases, Label... labels) {
    delegate.switch_(defaultLabel, cases, labels);
  }

  @Override
  public void inc(Object value) {
    createDelegate();
    set(delegate.add(1));
  }

  @Override
  public Variable add(Object value) {
    createDelegate();
    return delegate.add(ByteCodeGenerator.getRealVariable(value));
  }

  @Override
  public Variable sub(Object value) {
    createDelegate();
    return delegate.sub(ByteCodeGenerator.getRealVariable(value));
  }

  @Override
  public Variable mul(Object value) {
    createDelegate();
    return delegate.mul(ByteCodeGenerator.getRealVariable(value));
  }

  @Override
  public Variable div(Object value) {
    return delegate.div(value);
  }

  @Override
  public Variable rem(Object value) {
    return delegate.rem(value);
  }

  @Override
  public Variable eq(Object value) {
    return delegate.eq(value);
  }

  @Override
  public Variable ne(Object value) {
    return delegate.ne(value);
  }

  @Override
  public Variable lt(Object value) {
    return delegate.lt(value);
  }

  @Override
  public Variable ge(Object value) {
    return delegate.ge(value);
  }

  @Override
  public Variable gt(Object value) {
    return delegate.gt(value);
  }

  @Override
  public Variable le(Object value) {
    return delegate.le(value);
  }

  @Override
  public Variable instanceOf(Object type) {
    return delegate.instanceOf(type);
  }

  @Override
  public Variable cast(Object type) {
    return delegate.cast(type);
  }

  @Override
  public Variable not() {
    return delegate.not();
  }

  @Override
  public Variable and(Object value) {
    return delegate.and(value);
  }

  @Override
  public Variable or(Object value) {
    return delegate.or(value);
  }

  @Override
  public Variable xor(Object value) {
    return delegate.xor(value);
  }

  @Override
  public Variable shl(Object value) {
    return delegate.shl(value);
  }

  @Override
  public Variable shr(Object value) {
    return delegate.shr(value);
  }

  @Override
  public Variable ushr(Object value) {
    return delegate.ushr(value);
  }

  @Override
  public Variable neg() {
    return delegate.neg();
  }

  @Override
  public Variable com() {
    return delegate.com();
  }

  @Override
  public Variable box() {
    return delegate.box();
  }

  @Override
  public Variable unbox() {
    return delegate.unbox();
  }

  @Override
  public Variable alength() {
    return delegate.alength();
  }

  @Override
  public Variable aget(Object index) {
    return delegate.aget(index);
  }

  @Override
  public void aset(Object index, Object value) {
    delegate.aset(index, value);
  }

  @Override
  public Field field(String name) {
    return delegate.field(name);
  }

  @Override
  public Variable invoke(String name, Object... values) {
    return delegate.invoke(name, values);
  }

  @Override
  public Variable invoke(String name) {
    return delegate.invoke(name);
  }

  @Override
  public Variable invoke(Object returnType, String name, Object[] types, Object... values) {
    return delegate.invoke(returnType, name, types, values);
  }

  @Override
  public Variable invoke(Object returnType, String name, Object[] types) {
    return delegate.invoke(returnType, name, types);
  }

  @Override
  public Variable methodHandle(Object returnType, String name, Object... types) {
    return delegate.methodHandle(returnType, name, types);
  }

  @Override
  public Variable methodHandle(Object returnType, String name) {
    return delegate.methodHandle(returnType, name);
  }

  @Override
  public Bootstrap indy(String name, Object... args) {
    return delegate.indy(name, args);
  }

  @Override
  public Bootstrap indy(String name) {
    return delegate.indy(name);
  }

  @Override
  public Bootstrap condy(String name, Object... args) {
    return delegate.condy(name, args);
  }

  @Override
  public Bootstrap condy(String name) {
    return delegate.condy(name);
  }

  @Override
  public void throw_() {
    delegate.throw_();
  }

  @Override
  public void monitorEnter() {
    delegate.monitorEnter();
  }

  @Override
  public void monitorExit() {
    delegate.monitorExit();
  }

  @Override
  public void synchronized_(Runnable body) {
    delegate.synchronized_(body);
  }

  @Override
  public MethodMaker methodMaker() {
    return delegate.methodMaker();
  }
}
