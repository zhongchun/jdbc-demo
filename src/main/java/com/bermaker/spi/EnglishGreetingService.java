package com.bermaker.spi;

public class EnglishGreetingService implements GreetingService {
  @Override
  public String greet(String name) {
    return "Hello, " + name + "!";
  }
}
