package com.natwest.senderAPi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Value("${recieverapi.path}")
    private String recieverApi ;

    public TransactionController() {
    }

    @PostMapping(value="/", consumes = {"*/*"}, produces = {"*/*"})
    public  String  postingTransaction (@RequestBody Transaction newTransaction){

        return encryptData(newTransaction);
    }

    public String  encryptData(Transaction transaction){
        EncryptData encryptData = new EncryptData(transaction.toString());
        String encryptedData = encryptData.encodeBase64(transaction.toString());
        log.info("Data is encrypted");
        return sendEncryptedData(encryptedData);
    }

    @ResponseBody
    public String sendEncryptedData(String encryptedData){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(encryptedData,headers);

        RestTemplate restTemplate = new RestTemplate();
        Transaction response = restTemplate.postForObject(recieverApi,httpEntity,Transaction.class);

        log.info("API is posted & response is"+response);
        return  response.toString()+" is persisted";
    }
}
