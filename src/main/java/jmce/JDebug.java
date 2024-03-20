/**
   $Id: JDebug.java 814 2012-03-29 11:07:49Z mviara $

   Copyright (c) 2011, Mario Viara

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
package jmce;

import jmce.sim.*;
import jmce.sim.cpu.AbstractOpcode;
import jmce.sim.cpu.MultiOpcode;
import jmce.swing.KFixedField;
import jmce.swing.KHexField;
import jmce.swing.KLongField;
import jmce.swing.KMemoryChoice;
import jmce.util.FastArray;
import jmce.util.Hex;
import jmce.util.StringUtil;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class SortedLong extends java.util.TreeMap<Long, java.util.Vector<String>> {
  private static final long serialVersionUID = 1L;

  
  void put(long key, String value) {
    Long l = new Long(key);
    Object o;
    java.util.Vector<String> vector;

    vector = get(l);

    if (vector == null) {
      vector = new java.util.Vector<String>();
      put(l, vector);
    }

    vector.add(value);
  }

  JTree createTree() {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode();
    java.util.Vector<Long> keys = new java.util.Vector<Long>();

    java.util.Set<Long> set = keySet();
    java.util.Iterator<Long> iter = set.iterator();

    while (iter.hasNext())
      keys.add(iter.next());

    for (int i = 0; i < keys.size(); i++) {
      Long l = keys.elementAt(keys.size() - 1 - i);
      String s = l.longValue() + "";
      while (s.length() < 16)
        s = " " + s;

      java.util.Vector<String> v = get(l);
      if (v.size() > 1) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(s);
        root.add(node);

        for (int j = 0; j < v.size(); j++) {
          DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(v.elementAt(j).toString());
          node.add(n1);
        }
      } else {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(s + " " + v.elementAt(0).toString());
        root.add(node);
      }
    }

    return new JTree(root);
  }

}

/**
 * Base class for all panel used in the debug application.
 *
 * @author Mario Viara
 * @since 1.02
 */
class PanelDebug extends JPanel {
  private static final long serialVersionUID = 1L;

  protected CPU cpu;
  protected GridBagConstraints g = new GridBagConstraints();

  PanelDebug(String title, CPU cpu) {
    super(new GridBagLayout());
    this.cpu = cpu;
    jmce.swing.Util.setTitle(this, title);
    g.gridx = 0;
    g.gridy = 0;
    g.gridwidth = 1;
    g.gridheight = 1;
    g.insets = new Insets(1, 1, 1, 1);
    g.fill = GridBagConstraints.BOTH;

  }

  /**
   * Update the value displayed
   */
  public void update(CPU cpu) throws SIMException {
  }

  /**
   * Set the emulation mode. Can be used to disable some control not available
   * when the cpu is running.
   *
   * @param mode - true if the CPU is running
   */
  public void setEmulation(boolean mode) {
  }

  void add(String label, JComponent c) {

    g.fill = GridBagConstraints.NONE;
    g.anchor = GridBagConstraints.EAST;
    add(new JLabel(label), g);
    g.gridx++;
    g.fill = GridBagConstraints.BOTH;
    g.anchor = GridBagConstraints.WEST;
    add(c, g);
    g.gridx = 0;
    g.gridy++;
  }
}

class PanelAssembly extends PanelDebug {
  JScrollPane sc;
  private static final long serialVersionUID = 1L;
  private Memory memory = null;
  AbstractTableModel tm;
  JTable jt;
  private ArrayList<Boolean> breakPoint = new ArrayList<Boolean>();
  private ArrayList<Integer> pcs = new ArrayList<Integer>();
  private boolean completed = false;

  class Renderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      setMonospacedFont();
      return this;
    }

    private void setMonospacedFont() {
      Font font = getFont();

      font = new Font("Monospaced", font.getStyle(), font.getSize());
      setFont(font);

    }

  };

  PanelAssembly(CPU _cpu) {
    super("Assembler", _cpu);

    memory = cpu.getMemory();

    tm = new AbstractTableModel() {
      private static final long serialVersionUID = 1L;

      public int getRowCount() {
        return completed ? pcs.size() : pcs.size() + 1;

      }

      public int getColumnCount() {
        return 3;
      }

      public Object getValueAt(int r, int c) {
        int pc;

        if (r >= pcs.size()) {
          try {
            decode(-1, r);
          } catch (Exception ex) {
          }
        }

        if (r >= pcs.size())
          return null;
        pc = pc(r);
        switch (c) {
        case 0:
          return breakPoint.get(r);

        case 1:
          return Hex.formatValue(pc, 4);

        case 2:
          try {
            String s = cpu.decodeAt(pc);
            return StringUtil.expandTab(s);
          } catch (SIMException ex) {
            return "??";
          }
        default:
          return null;
        }
      }

      public String getColumnName(int c) {
        switch (c) {
        case 0:
          return "";
        case 1:
          return "Add";
        case 2:
          return "Decode";
        default:
          return null;
        }

      }

      public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 ? true : false;
      }

      public void setValueAt(Object v, int r, int c) {
        // Boolean b = (Boolean)v;
        // breakPoint.set(r,b);
        // cpu.setBreakPoint(pc(r),b.booleanValue());
      }
    };

    jt = new JTable(tm);
    sc = new JScrollPane(jt);
    jt.setDefaultRenderer(String.class, new Renderer());

    sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    GridBagConstraints g = new GridBagConstraints();
    g.gridx = 0;
    g.gridy = 0;
    g.gridwidth = 1;
    g.gridheight = 1;
    g.fill = GridBagConstraints.BOTH;
    g.insets = new Insets(1, 1, 1, 1);
    g.anchor = GridBagConstraints.CENTER;

    add(sc, g);

    createTable();
  }

  public void setPreferredHeight(int h) {
    Dimension d = sc.getPreferredSize();
    d.height = h;
    sc.setPreferredSize(d);
  }

  public void purge() {
    pcs.clear();
    breakPoint.clear();
  }

  private int pc(int r) {
    return pcs.get(r);

  }

  private void decode(int maxPc, int maxRow) throws SIMException {
    int pc;
    // int count = 0;

    if (pcs.size() == 0)
      pc = 0;
    else {
      pc = pc(pcs.size() - 1);
      pc += cpu.getLenghtAt(pc);
    }

    int from = pcs.size();
    for (;;) {
      if (pc >= memory.getSize()) {
        completed = true;
        break;
      }
      if (maxRow >= 0 && pcs.size() > maxRow)
        break;
      if (maxPc >= 0 && pc > maxPc)
        break;
      // count++;
      pcs.add(new Integer(pc));
      breakPoint.add(new Boolean(false));
      pc += cpu.getLenghtAt(pc);
      tm.fireTableRowsInserted(pcs.size() - 1, pcs.size() - 1);

    }

  }

  private void createTable() {
    pcs.clear();
    breakPoint.clear();
    completed = false;

    for (int i = 0; i < tm.getColumnCount(); i++) {
      TableColumn c = jt.getColumnModel().getColumn(i);
      Component comp;
      switch (i) {
      default:
      case 2:
        comp = new JTextField(50);
        break;
      case 1:
        comp = new JTextField(4);
        break;
      case 0:
        comp = new JCheckBox();
        break;
      }
      Dimension dim = comp.getPreferredSize();
      c.setPreferredWidth(dim.width);
      // c.setMinWidth(dim.width);
      // c.setMaxWidth(dim.width);
      c.setResizable(false);
    }
    tm.fireTableDataChanged();

  }

  public void update(CPU cpu) throws SIMException {
    int row = 0;

    decode(cpu.pc(), -1);

    for (int i = 0; i < pcs.size(); i++) {
      // System.out.println("Row "+i+" = "+Hex.bin2word(pc(i))+" Search
      // "+Hex.bin2word(cpu.pc()));

      if (pc(i) >= cpu.pc()) {

        row = i;
        break;
      }
    }

    Rectangle rect = jt.getCellRect(row, 0, true);
    jt.scrollRectToVisible(rect);
    jt.clearSelection();
    jt.setRowSelectionInterval(row, row);

  }

  public void setEmulation(boolean mode) {
    jt.setEnabled(!mode);
  }

}

/**
 * Panel with break point.
 *
 * @author Mario Viara
 * @version 1.00
 */
class PanelBreak extends PanelDebug implements ActionListener {
  private static final long serialVersionUID = 1L;
  static public final int NROW = 8;
  KMemoryChoice m[] = new KMemoryChoice[NROW];
  KHexField a[] = new KHexField[NROW];
  KHexField value[] = new KHexField[NROW];
  JComboBox w[] = new JComboBox[NROW];
  JCheckBox e[] = new JCheckBox[NROW];
  BreakPoint b[] = new BreakPoint[NROW];

  PanelBreak(CPU cpu) {
    super("Break point", cpu);
    for (int i = 0; i < NROW; i++) {
      m[i] = new KMemoryChoice(cpu);
      a[i] = new KHexField(32);
      value[i] = new KHexField(8);
      w[i] = new JComboBox();
      e[i] = new JCheckBox();
      e[i].addActionListener(this);

      w[i].addItem("Exec");
      w[i].addItem("Read");
      w[i].addItem("Write");

      a[i].setValue(0);
      a[i].setValue(0);

      a[i].addActionListener(this);
      a[i].setEditable(true);
      add(new JLabel("Address"), g);
      g.gridx++;
      add(m[i], g);
      g.gridx++;
      add(a[i], g);
      g.gridx++;
      add(value[i], g);
      g.gridx++;
      add(new JLabel("Break"), g);
      g.gridx++;
      add(w[i], g);
      g.gridx++;
      add(e[i], g);
      g.gridx++;
      g.gridx = 0;
      g.gridy++;
    }
  }

  public void actionPerformed(ActionEvent ev) {
    Object source = ev.getSource();

    for (int i = 0; i < NROW; i++) {
      /** Enabled disable break point */
      if (e[i] == source) {
        if (e[i].isSelected() == false) {
          cpu.removeBreakPoint(b[i]);
          b[i] = null;
          setEnabled(i, true);

        } else {
          setEnabled(i, false);
          int address = a[i].getValue();
          int memory = m[i].getSelectedIndex();
          int type = w[i].getSelectedIndex();
          BreakPoint bp = null;

          switch (type) {
          case 0:
            bp = cpu.addExecBreakPoint(memory, address);
            break;
          case 1:
            bp = cpu.addReadBreakPoint(memory, address);
            break;
          case 2:
            bp = cpu.addWriteBreakPoint(memory, address);
            break;
          }

          b[i] = bp;
        }
      }

      /** Change address */
      if (a[i] == source) {
        a[i].setValue(a[i].getValue());
        a[i].setValue(a[i].getValue());
        update(cpu, i);
        break;
      }
    }
  }

  /** Enabled / disable one specified row */
  void setEnabled(int i, boolean mode) {
    m[i].setEnabled(mode);
    w[i].setEnabled(mode);
    a[i].setEditable(mode);
  }

  void update(CPU cpu, int i) {
    Memory mem = cpu.getMemoryAt(m[i].getSelectedIndex());
    try {
      int add = a[i].getValue();
      if (add < 0 || add >= mem.getSize())
        value[i].setText("??");
      else {
        int v = mem.getMemory(a[i].getValue());
        value[i].setValue(v);
      }
    } catch (SIMException ex) {
      value[i].setText("??");
    }
  }

  public void update(CPU cpu) {
    for (int i = 0; i < NROW; i++)
      update(cpu, i);
  }

}

class PanelRegister extends PanelDebug {
  private static final long serialVersionUID = 1L;
  private FastArray<Register> regs = new FastArray<Register>();
  private FastArray<KHexField> values = new FastArray<KHexField>();

  PanelRegister(CPU cpu) {
    super("Register", cpu);

    g.insets = new Insets(1, 2, 1, 2);
    for (int i = 0; i < cpu.getRegisterCount(); i++) {
      Register reg = cpu.getRegisterAt(i);
      KHexField f = new KHexField(reg.getWidth());
      regs.add(reg);
      values.add(f);
      g.gridx = (i / 8) * 2;
      g.gridy = i % 8;

      add(new JLabel(reg.getName()), g);
      g.gridx++;
      add(f, g);

    }
  }

  public void setEmulation(boolean mode) {
    for (int i = 0; i < regs.getSize(); i++) {
      KHexField f = values.get(i);
      f.setEditable(!mode);
    }
  }

  public void update(CPU cpu) {
    for (int i = 0; i < regs.getSize(); i++) {
      Register r = regs.get(i);
      KHexField f = values.get(i);
      try {
        f.setValue(r.getRegister());
      } catch (Exception ignore) {
      }
    }
  }

}

class PanelStatus extends PanelDebug {
  class TextArea extends JTextArea {
    private static final long serialVersionUID = 1L;

    TextArea(int r, int c) {
      super(r, c);
      setLineWrap(true);
    }

    void clear() {
      setText("");
    }

    public void append(char c) {
      append("" + c);
    }

    public void append(String s) {
      super.append(s);
      setCaretPosition(getDocument().getLength());
    }
  }

  private static final long serialVersionUID = 1L;
  TextArea ta = new TextArea(1000, 80);
  TextArea tSiz = new TextArea(6, 80);

  PanelStatus(CPU cpu) {
    super("Status", cpu);

    Dimension d = tSiz.getPreferredSize();
    JScrollPane sc = new JScrollPane(ta);
    sc.setPreferredSize(d);
    sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    add(sc, g);

    ta.setEditable(false);

  }

  void append(String s) {
    ta.append(s);
  }
}

class PanelMemory extends PanelDebug {
  private static final long serialVersionUID = 1L;
  static public final int NROW = 8;
  static public final int NCOL = 16;

  KHexField data[][] = new KHexField[NROW][NCOL];
  KHexField address[] = new KHexField[NROW];
  KMemoryChoice memories;
  KHexField start = new KHexField(32);

  public PanelMemory(String title, CPU _cpu) {
    super(title, _cpu);
    int r, c, i;

    memories = new KMemoryChoice(cpu);

    for (r = 0; r < NROW; r++) {
      address[r] = new KHexField(32);
      address[r].setText("********");

      for (c = 0; c < NCOL; c++) {
        data[r][c] = new KHexField(8);
        data[r][c].setText("**");
      }
    }

    g.anchor = GridBagConstraints.CENTER;
    g.gridwidth = 3;
    add(new JLabel("Start"), g);
    g.gridx += g.gridwidth;
    g.gridwidth = 5;
    add(start, g);
    g.gridx += g.gridwidth;
    g.gridwidth = 3;
    add(new JLabel(" Memory"), g);
    g.gridx += g.gridwidth;
    g.gridwidth = 6;
    add(memories, g);
    g.gridwidth = 1;
    g.gridx = 0;
    g.gridy++;

    for (r = 0; r < NROW; r++) {
      add(address[r], g);
      g.gridx++;
      add(new JLabel(" "), g);
      g.gridx++;

      for (c = 0; c < NCOL; c++) {
        add(data[r][c], g);
        g.gridx++;
        if (c == (NCOL / 2 - 1)) {
          add(new JLabel(" - "), g);
          g.gridx++;
        }
      }

      g.gridx = 0;
      g.gridy++;
    }

    /**
     * Update memory content action
     */
    ActionListener updateMemory = new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        update(cpu);
      }
    };

    memories.addActionListener(updateMemory);
    start.addActionListener(updateMemory);

    start.setEditable(true);
    start.setValue(0);
    start.setValue(0);

  }

  void setStart(int a) {
    start.setValue(a);
    update(cpu);
  }

  public void update(CPU cpu) {
    int r, c;
    int a = start.getValue();

    Memory m = memories.getMemory();

    for (r = 0; r < NROW; r++) {
      if (a >= m.getSize())
        address[r].setText("--------");
      else
        address[r].setValue(a);

      for (c = 0; c < NCOL; c++) {
        if (a >= m.getSize())
          data[r][c].setText("--");
        else {
          try {
            data[r][c].setValue(m.getMemory(a));
            a++;
          } catch (Exception ex) {
            data[r][c].setText("??");
          }
        }
      }
    }

  }

}

/**
 * Panel with cpu information
 *
 * @author Mario Viara
 * @versiion 1.00
 */
class PanelInfo extends PanelDebug implements ActionListener {
  private static final long serialVersionUID = 1L;
  KFixedField name;
  KLongField clock;
  KLongField clockPerCycle;
  KLongField cycle;
  JCheckBox realtime;
  KFixedField usage;
  KLongField memory;

  PanelInfo(CPU info) {
    super("Information", info);
    int i;

    name = new KFixedField(cpu.getName());
    memory = new KLongField();
    cycle = new KLongField();
    realtime = new JCheckBox();
    realtime.addActionListener(this);
    clockPerCycle = new KLongField(3);
    clock = new KLongField();
    usage = new KFixedField(10);
    usage.setHorizontalAlignment(KFixedField.RIGHT);

    add("Cpu name", name);
    add("Oscillator", clock);
    add("Clock per cycle", clockPerCycle);
    add("Realtime mode", realtime);
    add("Current cycle", cycle);
    add("Virtual machine usage", usage);
    add("Memory usage", memory);

    /** Add all installed peripheral */
    for (i = 0; i < cpu.getMemoryCount(); i++) {
      KFixedField f;
      Memory m = cpu.getMemoryAt(i);
      f = new KFixedField(m.getName());
      f.setToolTipText(m.toString());
      add("Memory #" + i, f);
    }

  }

  /**
   * Actionlistener to manage event from widgets
   */
  public void actionPerformed(ActionEvent e) {
    Object o = e.getSource();

    if (o == realtime)
      cpu.setRealTime(realtime.isSelected());
  }

  public void setEmulation(boolean mode) {
    name.setEditable(false);
    clock.setEditable(!mode);
    clockPerCycle.setEditable(!mode);
    realtime.setEnabled(!mode);
    cycle.setEditable(false);
  }

  public void update(CPU cpu) {
    clock.setValue(cpu.getClock());
    clockPerCycle.setValue(cpu.getClockPerCycle());
    realtime.setSelected(cpu.getRealTime());
    cycle.setValue(cpu.getCycle());

    double d = cpu.getUsage();
    usage.setText(d + "%");

    memory.setValue(Runtime.getRuntime().totalMemory());
  }

}

/**
 * Main debug frame.
 *
 * @author Mario Viara
 * @since 1.02
 */
public class JDebug extends ApplicationFrame implements ExceptionListener {
  private static final long serialVersionUID = 1L;
  private AbstractAction actionDebugReset;
  private AbstractAction actionDebugGo;
  private AbstractAction actionDebugStop;
  private AbstractAction actionDebugInto;
  private AbstractAction actionDebugStep;
  private AbstractAction actionToolsProfile;
  private AbstractAction actionToolsInterrupt;
  private PanelInfo info;
  private PanelRegister regs;
  private PanelAssembly ass;
  private PanelStatus sts;
  private PanelMemory mem1;
  private PanelBreak wat;
  private JRadioButtonMenuItem bLaf = null;

  public JDebug(CPU _cpu) {
    super(_cpu);
    setTitle("JMCE Debug - " + 1);

    cpu.addExceptionListener(this);

    createActions();
    createMenuBar();
    createPanels();
    
//    getContentPane().setPreferredSize( new Dimension( 1640, 1480 ) );
//    getContentPane().setMinimumSize( new Dimension( 1640, 1480 ) );
//    getContentPane().setMaximumSize( new Dimension( 1640, 1480 ) );
    
    setEmulation(false);
    update(cpu);
    update(cpu);

    javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        swingTimer();
      }

    });
    t.setRepeats(true);
    t.start();

    /* If necessary set the last look and feel */
    if (bLaf != null)
      bLaf.doClick();

    showMessage("$Id: JDebug.java 814 2012-03-29 11:07:49Z mviara $ - Ready");
  }

  /**
   * Add an action to a toolbar
   */
  private void addAction(JToolBar bar, AbstractAction a) {

    JButton b = bar.add(a);
    b.setToolTipText((String) a.getValue(Action.NAME));

  }

  public void createPanels() {
    JToolBar bar = super.createToolBar();

    addAction(bar, actionFileLoad);
    addAction(bar, actionDebugGo);
    addAction(bar, actionDebugStop);
    addAction(bar, actionDebugStep);
    addAction(bar, actionDebugInto);
    addAction(bar, actionDebugReset);

    info = new PanelInfo(cpu);
    regs = new PanelRegister(cpu);
    ass = new PanelAssembly(cpu);
    sts = new PanelStatus(cpu);
    mem1 = new PanelMemory("Memory", cpu);
    wat = new PanelBreak(cpu);

//    ass.setPreferredHeight(mem1.getPreferredSize().height);
//    ass.setPreferredSize(new Dimension(300, 300));
    JPanel p = new JPanel(new GridBagLayout());
    GridBagConstraints g = new GridBagConstraints();
    g.fill = GridBagConstraints.BOTH;
    g.gridx = 0;
    g.gridy = 0;

    g.fill = GridBagConstraints.NONE;
    g.anchor = GridBagConstraints.WEST;
    p.add(bar, g);
    g.fill = GridBagConstraints.BOTH;
    g.gridy++;
    g.anchor = GridBagConstraints.CENTER;
    p.add(ass, g);
    g.gridx++;
    p.add(regs, g);
    g.gridx++;
    p.add(info, g);
    g.gridx++;
    g.gridy++;
    g.gridx = 0;
    g.gridwidth = 1;
    p.add(mem1, g);
    g.gridx++;
    g.gridwidth = 2;
    p.add(wat, g);
    g.gridy++;
    g.gridx = 0;
    g.gridwidth = 3;
    p.add(sts, g);

    setContentPane(p);
  }

  public void createMenuBar() {
    JMenuBar bar = new JMenuBar();
    bar.add(createMenuFile());
    bar.add(createMenuDebug());
    bar.add(createMenuTools());

    bar.add(createMenuLaf());

    setJMenuBar(bar);

  }

  JMenu createMenuLaf() {
    JMenu lnf = new JMenu("Look & Feel", true);
    lnf.setIcon(jmce.swing.Util.getIcon(this.getClass(), "colors.png"));

    ButtonGroup buttonGroup = new ButtonGroup();
    final UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
//    String s = Property.getProperty(Property.lookAndFeel, "");

    for (int i = 0; i < info.length; i++) {
      boolean set = false;

      JRadioButtonMenuItem item = new JRadioButtonMenuItem(info[i].getName(), set);
      final String className = info[i].getClassName();

      item.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          try {
            Property.setProperty(Property.lookAndFeel, className);
            UIManager.setLookAndFeel(className);
          } catch (Exception e) {
            showError(e);
          }
          SwingUtilities.updateComponentTreeUI(JDebug.this);
          JDebug.this.pack();

        }
      });

//      if (className.equals(s))
//        bLaf = item;

      buttonGroup.add(item);
      lnf.add(item);
    }

    lnf.setMnemonic('K');

    return lnf;

  }

  JMenu createMenuTools() {
    JMenu menu = new JMenu("Tools");
    JMenuItem m;

    menu.setMnemonic('T');
    menu.setIcon(jmce.swing.Util.getIcon(this.getClass(), "tools.gif"));
    menu.add(actionToolsProfile);
    menu.add(actionToolsInterrupt);

    return menu;
  }

  JMenu createMenuDebug() {
    JMenu menu = new JMenu("Debug");

    menu.setMnemonic('D');
    menu.setIcon(jmce.swing.Util.getIcon(this.getClass(), "Cpu.gif"));

    menu.add(actionDebugGo).setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
    menu.add(actionDebugStop).setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
    menu.add(actionDebugStep).setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));

    menu.add(actionDebugInto).setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
    menu.add(actionDebugReset);
    return menu;

  }

  private void createActions() {
    actionToolsInterrupt = new AbstractAction("Interrupt") {
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e) {
        performToolsInterrupt();
      }
    };

    actionToolsProfile = new AbstractAction("Opcodes") {
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e) {
        performToolsProfile();
      }
    };

    actionDebugInto = new AbstractAction("Step into", jmce.swing.Util.getIcon(this.getClass(), "stepinto.gif")) {
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e) {
        performStep();
      }

    };

    actionDebugStep = new AbstractAction("Step over", jmce.swing.Util.getIcon(this.getClass(), "step.gif")) {
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e) {
        performOver();
      }

    };

    actionDebugReset = new AbstractAction("Reset", jmce.swing.Util.getIcon(this.getClass(), "tools.gif")) {
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e) {
        performReset();
      }

    };

    actionDebugGo = new AbstractAction("Go", jmce.swing.Util.getIcon(this.getClass(), "play.gif")) {
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e) {
        performStart();
      }

    };

    actionDebugStop = new AbstractAction("Stop", jmce.swing.Util.getIcon(this.getClass(), "stop.gif")) {
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e) {
        performStop();
      }
    };

  }

  @Override
  protected void setEmulation(boolean mode) {
    super.setEmulation(mode);
    actionDebugReset.setEnabled(!mode);
    actionDebugGo.setEnabled(!mode);
    actionDebugStop.setEnabled(mode);
    actionDebugStep.setEnabled(!mode);
    actionDebugInto.setEnabled(!mode);

    info.setEmulation(mode);
    regs.setEmulation(mode);
    ass.setEmulation(mode);
    mem1.setEmulation(mode);
    wat.setEmulation(mode);
  }

  void update(CPU cpu) {
    swingTimer();

    try {
      info.update(cpu);
      regs.update(cpu);
      ass.update(cpu);
      mem1.update(cpu);
      wat.update(cpu);
    } catch (SIMException ex) {
    }
  }

  void performStop() {
    cpu.stop();
    cpu.setTill(-1);
    setEmulation(false);
    update(cpu);

  }

  void performStart() {
    cpu.start();
    setEmulation(true);
  }

  void performStep() {
    setEmulation(true);
    try {
      cpu.step();
    } catch (SIMException ignore) {
    }

    setEmulation(false);
    update(cpu);
  }

  private void showMessage(String s) {
    sts.append(s + "\n");

  }

  void performReset() {
    try {
      cpu.reset();
      update(cpu);
    } catch (Exception e) {
      showError(e);
    }

  }

  void performOver() {
    int till = -1;
    try {
      till = cpu.pc() + cpu.getLenghtAt(cpu.pc());
    } catch (SIMException ignore) {
    }

    setEmulation(true);

    cpu.setTill(till);
    cpu.start();

  }

  public void exceptionEvent(ExceptionEvent ev) {
    performStop();
    Throwable ex = ev.getEvent();

    sts.append(ex.toString() + "\n");
  }

  void performTree(String title, JTree tree) {
    class MyRenderer extends DefaultTreeCellRenderer {
      private static final long serialVersionUID = 1L;

      public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        Font font = getFont();

        if (font != null) {
          font = new Font("Monospaced", font.getStyle(), font.getSize());
          setFont(font);
        }

        return this;
      }

    }
    ;

    tree.setCellRenderer(new MyRenderer());
    Dimension size = getPreferredSize();
    size.width = size.width * 3 / 4;
    size.height = size.height * 3 / 4;

    JScrollPane sc = new JScrollPane(tree);

    sc.setPreferredSize(size);
    JDialog d = new JDialog(this, title, true);
    Point p = getLocation();
    p.x += size.width / 10;
    p.y += size.height / 10;
    d.setLocation(p);
    d.setContentPane(sc);
    d.pack();
    d.setVisible(true);
  }

  private void performToolsProfile(SortedLong sl, MultiOpcode mo) {
    for (int i = 0; i < 256; i++) {
      AbstractOpcode op = mo.getOpcode(i);

      if (op == null)
        continue;

      if (op instanceof MultiOpcode)
        performToolsProfile(sl, (MultiOpcode) op);
      else {
        if (op.getCounter() > 0)
          sl.put(op.getCounter(), StringUtil.expandTab(op.getDescription()));
      }

    }
  }

  /**
   * Perform tools profile.
   */
  private void performToolsProfile() {
    SortedLong sl = new SortedLong();
    performToolsProfile(sl, cpu.getOpcodes());
    performTree("Profile", sl.createTree());

  }

  /**
   * Perform tools interrupt
   */
  private void performToolsInterrupt() {
    SortedLong sl = new SortedLong();

    for (int i = 0; i < cpu.getInterruptCount(); i++) {
      if (cpu.getInterruptCounter(i) > 0)
        sl.put(cpu.getInterruptCounter(i), cpu.getInterruptName(i));

    }

    performTree("Interrupt", sl.createTree());

  }

  protected void performLoad() {
    super.performLoad();
    ass.purge();
    try {
      ass.update(cpu);
    } catch (Exception ignore) {
    }
  }

}
