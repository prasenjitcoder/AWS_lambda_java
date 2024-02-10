package com.prasenjit.aws.lambda.input;

import java.io.Serializable;

public class HelloWorldInput implements Serializable{
	
	private static final long serialVersionUID = -6552124498264739845L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
