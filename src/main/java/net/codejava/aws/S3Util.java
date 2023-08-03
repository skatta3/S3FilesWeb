package net.codejava.aws;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;
 
public class S3Util {
    private static final String BUCKET_AP = "arn:aws:s3:us-east-1:618384896736:accesspoint/test";
    private static final String BUCKET_NAME = "s3-file-upload";
    
    public static void uploadFile(String fileName, InputStream inputStream,String[] TO, String description)
            throws S3Exception, AwsServiceException, SdkClientException, IOException, SQLException {
         
        S3Client client = S3Client.builder().build();
        
         
        PutObjectRequest request = PutObjectRequest.builder()
                                .bucket(BUCKET_AP)
                                .key(fileName)
                                .build();
        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
        
        String objectURL = "https://" + BUCKET_NAME + ".s3.amazonaws.com/" + fileName;
        S3Waiter waiter = client.waiter();
        HeadObjectRequest waitRequest = HeadObjectRequest.builder().bucket(BUCKET_AP).key(fileName).build();
        WaiterResponse<HeadObjectResponse> waitResponse = waiter.waitUntilObjectExists(waitRequest);
        waitResponse.matched().response().ifPresent(response -> {
        		System.out.println("Please access the file at : " + objectURL);       
        }); 
		System.out.println("TO: "  + TO);

		//Send Email
        SendEmail mailSend = new SendEmail();
        mailSend.emailSend(TO, objectURL);
        
        // Insert into DB
        File file = new File();
        file.setName(fileName);
        file.setDescription(description);
        file.setEmail1(TO[0]);
        file.setEmail2(TO[1]);
        file.setEmail3(TO[2]);
        file.setEmail4(TO[3]);
        file.setEmail5(TO[4]);
        
        InsertDB insert = new InsertDB();
        insert.insertFileInfo(file);
    }
}