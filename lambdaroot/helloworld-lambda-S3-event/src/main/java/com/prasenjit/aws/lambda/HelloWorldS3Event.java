package com.prasenjit.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class HelloWorldS3Event implements RequestHandler<S3Event, String> {

	@Override
	public String handleRequest(S3Event s3Event, Context context) {
		
		LambdaLogger LOG  = context.getLogger();
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.registerModule(new JodaModule());
			String s3EventCOntent = objectMapper.writeValueAsString(s3Event);
			LOG.log("s3EventCOntent : "+s3EventCOntent+"\n");
		} catch (JsonProcessingException e) {
			LOG.log("Error while java to json");
			e.printStackTrace();
		}
		
		S3EventNotificationRecord record = s3Event.getRecords().get(0);
		LOG.log("Input event : "+record.toString()+"\n\n");
		String bucketName = record.getS3().getBucket().getName();
		String fileKey = record.getS3().getObject().getUrlDecodedKey();
		
		LOG.log("bucketName : "+bucketName+"\n");
		LOG.log("fileKey : "+fileKey+"\n");
		
		return bucketName;
	}

}
