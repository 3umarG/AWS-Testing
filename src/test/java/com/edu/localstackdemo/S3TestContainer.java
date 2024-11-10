package com.edu.localstackdemo;

import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

public class S3TestContainer extends LocalStackContainer {

    private static final String IMAGE_VERSION = "localstack/localstack:3.8.1";
    private static S3TestContainer container;
    public static final String BUCKET_NAME = "test-bucket";

    private S3TestContainer() {
        super(DockerImageName.parse(IMAGE_VERSION));
    }

    public static S3TestContainer getInstance() {
        if (container == null) {
            container = new S3TestContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("aws.s3.endpoint", getEndpointOverride(S3).toString());
        System.setProperty("aws.s3.accessKey", getAccessKey());
        System.setProperty("aws.s3.secretKey", getSecretKey());
        System.setProperty("aws.s3.region", getRegion());
        System.setProperty("aws.s3.bucketName", BUCKET_NAME);
    }

}
