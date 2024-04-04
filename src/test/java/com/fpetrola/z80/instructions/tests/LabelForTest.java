package com.fpetrola.z80.instructions.tests;

import org.cojen.maker.Label;
import org.cojen.maker.MethodMaker;

public class LabelForTest implements Label {
  @Override
  public Label here() {
    return null;
  }

  @Override
  public Label goto_() {
    return null;
  }

  @Override
  public Label insert(Runnable runnable) {
    return new LabelForTest();
  }

  @Override
  public MethodMaker methodMaker() {
    return null;
  }
}
