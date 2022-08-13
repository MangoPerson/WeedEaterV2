package com.github.mangoperson.weedeaterv2.util;

public interface NConsumer<T> {
    void apply(T... args);
}
