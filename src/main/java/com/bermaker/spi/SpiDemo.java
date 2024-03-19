package com.bermaker.spi;

import java.util.ServiceLoader;

public class SpiDemo {
  public static void main(String[] args) {
    ServiceLoader<GreetingService> loader = ServiceLoader.load(GreetingService.class);
    for (GreetingService service : loader) {
      System.out.println(service.greet("World"));
    }
  }
}
