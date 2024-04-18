package com.fpetrola.z80.instructions.tests;

import org.jetbrains.java.decompiler.main.extern.IResultSaver;

import java.util.jar.Manifest;

public class ResultSaverForTest implements IResultSaver {
  public String getContent() {
    return content;
  }

  private String content;

  @Override
  public void saveFolder(String path) {

  }

  @Override
  public void copyFile(String source, String path, String entryName) {

  }

  @Override
  public void saveClassFile(String path, String qualifiedName, String entryName, String content, int[] mapping) {
    this.content = content;
  }

  @Override
  public void createArchive(String path, String archiveName, Manifest manifest) {

  }

  @Override
  public void saveDirEntry(String path, String archiveName, String entryName) {

  }

  @Override
  public void copyEntry(String source, String path, String archiveName, String entry) {

  }

  @Override
  public void saveClassEntry(String path, String archiveName, String qualifiedName, String entryName, String content) {

  }

  @Override
  public void closeArchive(String path, String archiveName) {

  }
}
