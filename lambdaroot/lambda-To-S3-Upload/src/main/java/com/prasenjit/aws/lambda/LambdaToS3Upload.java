package com.prasenjit.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

/**
 * Class LambdaToS3Upload
 *
 * @author prasenjit
 * @version lambdaroot
 * @since 31/03/2024 - 14:42
 */
public class LambdaToS3Upload implements RequestHandler<Object, String> {

    public static String bucketName = "prasenjittest1";
    public static String filePath = "file.txt";
    public static String fileName = "file.txt";

    @Override
    public String handleRequest(Object input, Context context) {

        S3Client s3client = S3Client.builder().region(Region.US_EAST_1).build();

        PutObjectRequest putObject = PutObjectRequest.builder().bucket(bucketName).key(fileName).build();

        s3client.putObject(putObject,RequestBody.fromFile(new File(filePath)));

        return "OK";
    }
}
