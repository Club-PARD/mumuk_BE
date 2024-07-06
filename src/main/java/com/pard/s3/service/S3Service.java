//package com.pard.s3.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.presigner.S3Presigner;
//import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
//import software.amazon.awssdk.services.s3.model.GetObjectRequest;
//
//import java.net.URL;
//import java.time.Duration;
//
//@Service
//public class S3Service {
//
//    private final S3Client s3Client;
//    private final S3Presigner s3Presigner;
//    private final String bucketName;
//
//    public S3Service(@Value("${aws.accessKeyId:}") String accessKeyId,
//                     @Value("${aws.secretAccessKey:}") String secretAccessKey,
//                     @Value("${aws.region}") String region,
//                     @Value("${aws.s3.bucketName}") String bucketName) {
//        this.bucketName = bucketName;
//
//        if (accessKeyId.isEmpty() || secretAccessKey.isEmpty()) {
//            // EC2 환경: IAM 역할을 사용
//            this.s3Client = S3Client.builder()
//                    .region(Region.of(region))
//                    .credentialsProvider(DefaultCredentialsProvider.create())
//                    .build();
//            this.s3Presigner = S3Presigner.builder()
//                    .region(Region.of(region))
//                    .credentialsProvider(DefaultCredentialsProvider.create())
//                    .build();
//        } else {
//            // 로컬 환경: 자격 증명 사용
//            AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
//            this.s3Client = S3Client.builder()
//                    .region(Region.of(region))
//                    .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
//                    .build();
//            this.s3Presigner = S3Presigner.builder()
//                    .region(Region.of(region))
//                    .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
//                    .build();
//        }
//    }
//
//    public URL generatePresignedUrl(String objectKey, Duration duration) {
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key(objectKey)
//                .build();
//
//        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
//                .getObjectRequest(getObjectRequest)
//                .signatureDuration(duration)
//                .build();
//
//        return s3Presigner.presignGetObject(getObjectPresignRequest).url();
//    }
//}
