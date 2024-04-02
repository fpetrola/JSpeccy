package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.CPUExecutionContext;
import org.cojen.maker.*;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClassMakerForTest implements ClassMaker {
  public List<MethodMakerForTest> methodMakers = new ArrayList<>();
  public List<FieldMakerForTest> fieldMakers= new ArrayList<>();

  @Override
  public ClassMaker another(String s) {
    return null;
  }

  @Override
  public ClassMaker public_() {
    return null;
  }

  @Override
  public ClassMaker private_() {
    return null;
  }

  @Override
  public ClassMaker protected_() {
    return null;
  }

  @Override
  public ClassMaker static_() {
    return null;
  }

  @Override
  public ClassMaker final_() {
    return null;
  }

  @Override
  public ClassMaker interface_() {
    return null;
  }

  @Override
  public ClassMaker abstract_() {
    return null;
  }

  @Override
  public ClassMaker synthetic() {
    return null;
  }

  @Override
  public ClassMaker enum_() {
    return null;
  }

  @Override
  public ClassMaker annotation() {
    return null;
  }

  @Override
  public ClassMaker extend(Object o) {
    return null;
  }

  @Override
  public ClassMaker implement(Object o) {
    return null;
  }

  @Override
  public ClassMaker signature(Object... objects) {
    return null;
  }

  @Override
  public ClassMaker permitSubclass(Object o) {
    return null;
  }

  @Override
  public FieldMaker addField(Object o, String s) {
    FieldMakerForTest fieldMakerForTest = new FieldMakerForTest(o, s);
    fieldMakers.add(fieldMakerForTest);
    return fieldMakerForTest;
  }

  @Override
  public MethodMaker addMethod(Object o, String s, Object... objects) {
    MethodMakerForTest methodMakerForTest = new MethodMakerForTest(this, o, s, objects);
    methodMakers.add(methodMakerForTest);
    return methodMakerForTest;
  }

  @Override
  public MethodMaker addConstructor(Object... objects) {
    return null;
  }

  @Override
  public MethodMaker addClinit() {
    return null;
  }

  @Override
  public MethodMaker asRecord() {
    return null;
  }

  @Override
  public ClassMaker addInnerClass(String s) {
    return null;
  }

  @Override
  public ClassMaker sourceFile(String s) {
    return null;
  }

  @Override
  public Object arrayType(int i) {
    return null;
  }

  @Override
  public ClassLoader classLoader() {
    return null;
  }

  @Override
  public Set<String> unimplementedMethods() {
    return null;
  }

  @Override
  public Class<?> finish() {
    return null;
  }

  @Override
  public MethodHandles.Lookup finishLookup() {
    return null;
  }

  @Override
  public MethodHandles.Lookup finishHidden() {
    return null;
  }

  @Override
  public byte[] finishBytes() {
    return new byte[0];
  }

  @Override
  public void finishTo(OutputStream outputStream) throws IOException {

  }

  @Override
  public ClassMaker classMaker() {
    return null;
  }

  @Override
  public AnnotationMaker addAnnotation(Object o, boolean b) {
    return null;
  }

  @Override
  public void addAttribute(String s, Object o) {

  }

}
