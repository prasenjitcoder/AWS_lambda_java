package com.prasenjit.aws.lambda.output;

import java.io.Serializable;

public class HelloWorldOutPut  implements Serializable{

	private static final long serialVersionUID = -7902016652738100848L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
