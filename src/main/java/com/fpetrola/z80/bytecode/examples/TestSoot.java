package com.fpetrola.z80.bytecode.examples;

import soot.*;
import soot.jimple.Stmt;
import soot.options.Options;

import java.util.Iterator;
import java.util.Map;

import static java.io.File.pathSeparator;

public class TestSoot extends BodyTransformer {
  public static void main(String[] args) {
    String mainclass = "JetSetWilly";

    //set classpath
    String bootpath = System.getProperty("sun.boot.class.path");
    String javapath = System.getProperty("java.class.path");
    String jceDir = "/home/fernando/.sdkman/candidates/java/8.0.282-open/jre" + "/lib/rt.jar";
    String path = jceDir + pathSeparator + javapath + pathSeparator + "/home/fernando/detodo/desarrollo/m/zx/zx/zx3/soot-demo-1/inputs" + pathSeparator + "/home/fernando/detodo/desarrollo/m/zx/zx/zx3/ZX3D/target/classes";
    Scene.v().setSootClassPath(path);

    //add an intra-procedural analysis phase to Soot
    TestSoot analysis = new TestSoot();
    PackManager.v().getPack("jtp").add(new Transform("jtp.TestSoot", analysis));

    //load and set main class
    Options.v().set_app(true);
    SootClass appclass = Scene.v().loadClassAndSupport(mainclass);
   // Scene.v().setMainClass(appclass);
    Scene.v().loadNecessaryClasses();

    //start working
    PackManager.v().runPacks();
  }

  @Override
  protected void internalTransform(Body b, String phaseName, Map<String, String> options) {
    Iterator<Unit> it = b.getUnits().snapshotIterator();
    while (it.hasNext()) {
      Stmt stmt = (Stmt) it.next();
      System.out.println(stmt);
    }
  }
}