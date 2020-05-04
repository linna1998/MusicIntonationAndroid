package com.musicintonation.core;

public enum Note {
  A4("A4", 440.0000),
  B4("B4", 493.8833),
  C5("C5", 523.2511),
  D5("D5", 587.3295),
  E5("E5", 659.2551);


  private final double hertz;
  private final String name;

  Note(String name, double hertz) {
    this.name = name;
    this.hertz = hertz;
  }

  public double getHertz() {
    return hertz;
  }

  public String getName() {
    return name;
  }
}
