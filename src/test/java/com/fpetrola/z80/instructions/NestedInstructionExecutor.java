package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;

import java.util.*;

public class NestedInstructionExecutor {
  public class TreeNode<T> {
    private T value;
    private List<TreeNode<T>> children;
    private TreeNode<T> parent;

    public void setValue(T value) {
      this.value = value;
    }

    public TreeNode() {
      this.value = value;
      this.children = new ArrayList<>();
    }

    public TreeNode(T value) {
      this.value = value;
      this.children = new ArrayList<>();
    }

    public void addChild(TreeNode<T> child) {
      children.add(child);
      child.parent = this;
    }

    public TreeNode<T> addChild(T childValue) {
      TreeNode<T> e = new TreeNode<>(childValue);
      children.add(e);
      e.parent = this;
      return e;
    }

    public T getValue() {
      return value;
    }

    public List<TreeNode<T>> getChildren() {
      return children;
    }

    public TreeNode<T> find(T instruction) {
      return instruction == value ? this : parent != null ? parent.find(instruction) : null;
    }
  }

  TreeNode<Instruction> root = new TreeNode<>();

  public Optional<Boolean> execute(Instruction instruction) {
    TreeNode<Instruction> instructionTreeNode = root.find(instruction);
    if (instructionTreeNode != null)
      return Optional.empty();
    else {
      instruction.execute();
      if (root == null)
        root = new TreeNode<Instruction>(instruction);
      else {
        root = root.addChild(instruction);
      }

      return Optional.of(true);
    }
  }

  public void evicted(Instruction instruction) {
    TreeNode<Instruction> instructionTreeNode = root.find(instruction);
    TreeNode<Instruction> parent = instructionTreeNode;
    if (parent != null) {
      parent.getChildren().clear();
      root = parent;
    } else root = new TreeNode<>();
  }
}
