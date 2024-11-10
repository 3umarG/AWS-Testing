package com.edu.localstackdemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class BackupStorageService {
    private final S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucket;

    private final static String CONTENT_TYPE = "application/json";

    public void uploadFile(String fileName, byte[] content) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(fileName)
                    .contentType(CONTENT_TYPE)
                    .contentLength((long) content.length)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            RequestBody requestBody = RequestBody.fromBytes(content);
            s3Client.putObject(putObjectRequest, requestBody);
        } catch (Exception e) {
            log.error("Error during upload file with message :: {}", e.getMessage());
        }
    }

}
