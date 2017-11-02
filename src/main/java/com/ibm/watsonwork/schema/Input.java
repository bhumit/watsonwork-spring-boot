package com.ibm.watsonwork.schema;

import java.io.Serializable;

public final class Input<T> implements Serializable {

  private final T value;
  private final boolean defined;

  public static <T> Input<T> value( T value) {
    return new Input<>(value, true);
  }

  public static <T> Input<T> optional( T value) {
    return value != null ? value(value) : Input.undefined();
  }

  public static <T> Input<T> undefined() {
    return new Input<>(null, false);
  }

  private Input(T value, boolean defined) {
    this.value = value;
    this.defined = defined;
  }

  public T getValue() {
    return value;
  }

  public boolean isDefined() {
    return defined;
  }
}