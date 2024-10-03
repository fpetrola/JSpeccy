package com.fpetrola.z80.bytecode.examples;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReplacer {
  public static String replace(String input, Pattern regex, Function<Matcher, String> callback) {
    StringBuilder resultString = new StringBuilder();
    Matcher regexMatcher = regex.matcher(input);
    while (regexMatcher.find()) {
      regexMatcher.appendReplacement(resultString, callback.apply(regexMatcher));
    }
    regexMatcher.appendTail(resultString);

    return resultString.toString();
  }
}