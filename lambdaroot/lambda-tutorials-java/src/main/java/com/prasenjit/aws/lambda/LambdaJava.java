package com.prasenjit.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

public class LambdaJava implements RequestHandler<Object, String> {
	@Override
	public String handleRequest(Object input, Context context) {
		LambdaLogger LOG  = context.getLogger();
		//Start - Access environment variables
		String url = System.getenv("API_URL");
		String urlKey = System.getenv("API_KEY");
		LOG.log("\n");
		LOG.log("url : "+url+"\n");
		LOG.log("urlKey : "+urlKey+"\n");
		//End - Access environment variables

		//STart -SSM
		SsmClient ssm = SsmClient.builder()
				.region(Region.US_EAST_1)
				.credentialsProvider(DefaultCredentialsProvider.create())
				.build();
		String key = "DBPass";
		GetParameterRequest parameterRequest = GetParameterRequest.builder().name(key).build();
		GetParameterResponse parameterResponse = ssm.getParameter(parameterRequest);
		String ssmParmvalue =  parameterResponse.parameter().value();
		LOG.log("ssmParmvalue : "+ssmParmvalue+"\n");
		//End SSM

		return "Okay";
	}
}
