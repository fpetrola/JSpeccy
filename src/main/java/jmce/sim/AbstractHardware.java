/**
   $Id: AbstractHardware.java 695 2011-09-21 06:09:11Z mviara $

   Copyright (c) 2010, Mario Viara

   Permission is hereby granted, free of charge, to any person obtaining a
   copy of this software and associated documentation files (the "Software"),
   to deal in the Software without restriction, including without limitation
   the rights to use, copy, modify, merge, publish, distribute, sublicense,
   and/or sell copies of the Software, and to permit persons to whom the
   Software is furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in
   all copies or substantial portions of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL
   ROBERT M SUPNIK BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
   IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
   CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

   Except as contained in this notice, the name of Mario Viara shall not be
   used in advertising or otherwise to promote the sale, use or other dealings
   in this Software without prior written authorization from Mario Viara.
*/
package jmce.sim;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Vector;

import jmce.JMonitor;

/**
 * Abstract implementation of <tt>Hardware</tt> implements all not specific
 * method.
 * 
 * <p>
 * This class can be used from Hardware interface as parent an order to
 * implement only the hardware depending method.
 *
 * @author Mario Viara
 * @version 1.00
 */
public abstract class AbstractHardware implements Hardware {
  private static java.util.logging.Logger log = java.util.logging.Logger.global;
  static private CPU cpu = null;
  private Hardware parent = null;
  private ArrayList<Hardware> hardwares = new ArrayList<Hardware>();
  private String name;
  private JFrame frame = null;
  private Vector<JComponent> swingComponents = new Vector<JComponent>();

  public AbstractHardware() {
  }

  public AbstractHardware(String name) {
    setName(name);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void reset() throws SIMException {
    for (int i = 0; i < getHardwareCount(); i++) {
      log.fine("Reset " + getHardware(i));
      getHardware(i).reset();
    }

    if (swingComponents.size() > 0)
      createFrame(swingComponents);

  }

  public void init(Hardware parent) throws SIMException {
    this.parent = parent;
    if (this instanceof CPU)
      cpu = (CPU) this;

    log.fine("Start Init=" + this + " Parent=" + parent + " Cpu=" + cpu);

    for (int i = 0; i < getHardwareCount(); i++) {
      Hardware child = getHardware(i);

      child.init(this);

      if (cpu != null && child instanceof Peripheral)
        ((Peripheral) child).registerCPU(cpu);
    }

    log.fine("End Init=" + this + " Parent=" + parent);

  }

  public void initSwing(Hardware parent) throws SIMException {

    // Check the GUI interface
    if (this instanceof SwingHardware) {
      SwingHardware sc = (SwingHardware) this;
      log.fine("Init swing " + getName());
      Component comp = sc.getComponent();
      boolean createFrame = false;

      if (comp instanceof JComponent) {
        JComponent jc = (JComponent) comp;

        if (jc.getParent() == null || jc.isVisible() == false)
          createFrame = true;

        if (createFrame) {

          // Search for root
          if (comp instanceof JComponent)
            jc = (JComponent) comp;
          else {
            JPanel p = new JPanel();
            p.add(comp);
            jc = p;
          }

          AbstractHardware root = getRoot();

          log.info("Add JComponent " + this + " to " + root);

          root.swingComponents.add(jc);
        }

      }
    }

    for (int i = 0; i < getHardwareCount(); i++) {
      Hardware child = getHardware(i);
      log.fine("initSwing " + child);

      child.initSwing(this);
    }

  }

  private AbstractHardware getRoot() {
    AbstractHardware root = this;
    while (root.parent != null)
      root = (AbstractHardware) root.parent;

    return root;
  }

  public Hardware addHardware(Hardware h) {
    log.fine("Add " + h.toString() + " to " + toString());
    hardwares.add(h);

    return h;
  }

  public int getHardwareCount() {
    return hardwares.size();
  }

  public Hardware getHardware(int i) {
    return hardwares.get(i);
  }

  public void removeHardware(int n) {
    hardwares.remove(n);
  }

  public void removeHardware(Hardware h) {
    hardwares.remove(h);
  }

  public void destroy() throws SIMException {
    for (int i = 0; i < getHardwareCount(); i++) {
      log.fine("Destroy " + getHardware(i));
      getHardware(i).destroy();
    }

    log.fine("Destroy " + this);
    if (frame != null) {
      frame.setVisible(false);
    }

  }

  public Hardware getHardwareForName(String name) {
    for (int i = 0; i < getHardwareCount(); i++) {
      Hardware h = getHardware(i);
      if (h.getName().equalsIgnoreCase(name))
        return h;
    }

    return null;

  }

  @SuppressWarnings("rawtypes")
  public Hardware getHardwareTree(Class... classes) {
    Hardware parent = this;

    for (Class c : classes) {
      if (parent == null)
        return null;
      parent = parent.getHardware(c);
      if (c == null)
        return null;
    }

    return parent;

  }

  @SuppressWarnings("rawtypes")
  public Hardware getHardware(Class... classes) {
    Hardware h = this;

    for (Class c : classes) {
      for (int i = 0; i < h.getHardwareCount(); i++)
        System.out.println("" + i + " = " + h.getHardware(i));
      h = h.getHardware(c);
      if (h == null) {
        return h;
      }
    }

    return h;
  }

  @SuppressWarnings("rawtypes")
  public Hardware getHardware(Class c) {
    return getHardware(c, 0);
  }

  @SuppressWarnings("rawtypes")
  public Hardware getHardware(Class c, int n) {
    for (int i = 0; i < getHardwareCount(); i++) {
      Hardware o = getHardware(i);
      if (c.isInstance(o))
        if (n-- <= 0)
          return o;
    }

    return null;
  }

  @SuppressWarnings("rawtypes")
  public Object[] getHardwareInstances(Class c) {
    ArrayList<Object> a = new ArrayList<Object>();

    for (int i = 0; i < getHardwareCount(); i++) {
      Object o = getHardware(i);
      if (c.isInstance(o))
        a.add(o);
    }

    return a.toArray();
  }

  public Hardware[] getHardware() {
    if (hardwares.size() == 0)
      return null;

    return hardwares.toArray(new Hardware[hardwares.size()]);
  }

  public void setHardware(int n, Hardware h) {
    hardwares.set(n, h);
  }

  public void setHardware(Hardware h[]) {
    for (int i = 0; i < h.length; i++)
      addHardware(h[i]);
  }

  public Hardware getParent() {
    return parent;
  }

  public void createFrame(Vector<JComponent> j) {
    String name = toString();
    log.info("Create frame in Class=" + this.getClass().getName() + " Name=" + name + " Len=" + j.size());

    if (frame != null) {
      log.info("Destroy old frame");
      frame.setVisible(false);
      frame = null;
    }

    GridBagConstraints g = new GridBagConstraints();
    g.gridx = 0;
    g.gridy = 0;
    g.gridwidth = 1;
    g.gridheight = 1;
    g.fill = GridBagConstraints.BOTH;
    g.insets = new Insets(2, 2, 2, 2);
    g.anchor = GridBagConstraints.CENTER;
    JPanel p = new JPanel(new GridBagLayout());

    for (int i = 0; i < j.size(); i++) {
      p.add(j.get(i), g);
      g.gridy++;
    }

    frame = new JMonitor(cpu, p);
    frame.setTitle(name + " - JMCE " + jmce.Jmce.versionNumber);
    frame.pack();
    frame.setVisible(true);
  }

  public String toString() {
    return getName();
  }

}
