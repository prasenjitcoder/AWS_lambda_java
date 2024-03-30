package com.prasenjit.aws.s3;

import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

/**
 * Class UploadFileToS3
 *
 * @author prasenjit
 * @version lambdaroot
 * @since 30/03/2024 - 16:48
 */
public class UploadFileToS3 {
    public static String bucketName = "prasenjittest1";
    public static String filePath = "/home/prasenjit/Downloads/s3/file.txt";
    public static String fileName = "file.txt";

    public static void main(String[] args) {

        Region myRegion = Region.US_EAST_1;
        AwsCredentials credentials = AwsBasicCredentials.create("12356","7896");
        S3Client s3client = S3Client.builder().credentialsProvider(StaticCredentialsProvider.create(credentials)).region(myRegion).build();

        PutObjectRequest putObject = PutObjectRequest.builder().bucket(bucketName).key(fileName).build();
        s3client.putObject(putObject,RequestBody.fromFile(new File(filePath)));
    }


}
