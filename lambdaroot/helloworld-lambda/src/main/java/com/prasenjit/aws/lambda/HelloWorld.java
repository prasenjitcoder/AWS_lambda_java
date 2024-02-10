package com.prasenjit.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.prasenjit.aws.lambda.input.HelloWorldInput;
import com.prasenjit.aws.lambda.output.HelloWorldOutPut;

public class HelloWorld implements RequestHandler<HelloWorldInput, Object>{

	@Override
	public Object handleRequest(HelloWorldInput input, Context context) {
		
		LambdaLogger LOG  = context.getLogger();
		LOG.log("Input Name : "+input.getName()+"\n");
		//Business Logic
		
		HelloWorldOutPut returnMessage = new HelloWorldOutPut();
		returnMessage.setMessage("Hello "+input.getName());
		
		return returnMessage;
	}

}
