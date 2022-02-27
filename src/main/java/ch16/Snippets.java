package ch16;

import java.io.*;

public class Snippets {
  void listDirectory(File dir) {
    if (dir.isDirectory()) {
      String[] dirContents = dir.list();
      for (String dirContent : dirContents) {
        System.out.println(dirContent);
      }
    }
  }
}
