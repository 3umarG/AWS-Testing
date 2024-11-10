package com.edu.localstackdemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import static com.edu.localstackdemo.S3TestContainer.BUCKET_NAME;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LocalstackDemoApplication.class)
@ActiveProfiles({"test"})
public class S3Tests {

    private final static S3TestContainer s3TestContainer;

    static {
        s3TestContainer = S3TestContainer.getInstance();
        s3TestContainer.start();
    }

    @Autowired
    private S3Client s3Client;

    @Autowired
    private BackupStorageService backupStorageService;

    @BeforeEach
    void setupBucket() {
        s3Client.createBucket(CreateBucketRequest.builder().bucket(BUCKET_NAME).build());
    }

    @AfterEach
    void cleanupBucket() {
        ListObjectsV2Response listResponse = s3Client.listObjectsV2(ListObjectsV2Request.builder().bucket(BUCKET_NAME).build());
        for (S3Object s3Object : listResponse.contents()) {
            s3Client.deleteObject(DeleteObjectRequest.builder().bucket(BUCKET_NAME).key(s3Object.key()).build());
        }
        s3Client.deleteBucket(DeleteBucketRequest.builder().bucket(BUCKET_NAME).build());
    }


    @Test
    void uploadFile_Success() {
        String fileName = "test.json";
        byte[] content = "Test content".getBytes();

        assertDoesNotThrow(() -> backupStorageService.uploadFile(fileName, content));
        HeadObjectResponse headObjectResponse = s3Client.headObject(HeadObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(fileName)
                .build());
        assertNotNull(headObjectResponse);
    }






}
