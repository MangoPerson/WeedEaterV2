package com.github.mangoperson.weedeaterv2.util.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface Cmd {
	String value();
}
