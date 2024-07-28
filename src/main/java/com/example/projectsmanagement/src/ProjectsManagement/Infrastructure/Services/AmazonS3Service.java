package com.example.projectsmanagement.src.ProjectsManagement.Infrastructure.Services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class AmazonS3Service {
    private AmazonS3 s3client;

    private String ENDPOINT_URL = "https://toposmart-projects.s3.us-east-2.amazonaws.com";
    private String BUCKET_NAME = "toposmart-projects";
    private String ACCESS_KEY = "AKIAU6GD2BYP55MKH3FQ";
    private String SECRET_KEY = "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";

    public String uploadFile(MultipartFile multipartFile, String name, String category) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            // Reemplazar espacios en el nombre y categoría
            String sanitizedCategory = category.replace(" ", "_");
            String sanitizedName = name.replace(" ", "_");
            String fileName = generateFileName(file);
            fileUrl = ENDPOINT_URL + "/" + sanitizedCategory + "/" + sanitizedName + "/" + fileName;
            uploadFileToS3Bucket(sanitizedCategory + "/" + sanitizedName + "/" + fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    public void deleteFileByUrl(String fileUrl) {
        String fileKey = fileUrl.replace(ENDPOINT_URL + "/", "");
        s3client.deleteObject(BUCKET_NAME, fileKey);
    }

    public String updateFileByUrl(MultipartFile multipartFile, String fileUrl) {
        deleteFileByUrl(fileUrl);
        String[] parts = fileUrl.replace(ENDPOINT_URL + "/", "").split("/");
        String category = parts[0];
        String name = parts[1];
        return uploadFile(multipartFile, name, category);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(file.getBytes());
        }
        return convertFile;
    }

    private String generateFileName(File file) {
        return file.getName().replace(" ", "_");
    }

    private void uploadFileToS3Bucket(String fileName, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        s3client.putObject(putObjectRequest);
    }

    @PostConstruct
    public void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();
    }
}
