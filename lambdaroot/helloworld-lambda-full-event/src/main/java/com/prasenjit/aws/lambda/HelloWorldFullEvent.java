package com.prasenjit.aws.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class HelloWorldFullEvent implements RequestStreamHandler{

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		LambdaLogger LOG  = context.getLogger();
		
		JSONParser parser = new JSONParser();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	    
	    JSONObject responseJson = new JSONObject();
	    
	    JSONObject responseBody = new JSONObject();
	    
	    String name = null;
	    
	    try {
	        JSONObject event = (JSONObject) parser.parse(reader);
	        LOG.log("Input event : "+event.toJSONString()+"\n\n");
	        
	        //Retrieve the name from Path param
	        if (event.get("pathParameters") != null) {
	            JSONObject pps = (JSONObject) event.get("pathParameters");
	            if (pps.get("name") != null) {
	                 name = (String) pps.get("name");
	            }
	        }
	        
	        //Set name in the response
	        responseBody.put("message", "Hello "+name);
	        
	        responseJson.put("statusCode", 200);
	        responseJson.put("body", responseBody.toString());
	        
	        
	    }catch(Exception ex) {
	    	responseJson.put("statusCode", 500);
	    	 responseJson.put("exception", ex);
	    }
	    
	    OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
	    writer.write(responseJson.toString());
	    writer.close();
		
	}

	
}
