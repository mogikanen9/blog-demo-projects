package com.mogikanen9.rest.api.client;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

public class FileApiClient{

    private String apiUrl;
    
    public FileApiClient(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void downloadFile(final String fileNameToDownload,final File destFile){
        RestTemplate client = new RestTemplate();
        client.execute(apiUrl+"/"+fileNameToDownload, HttpMethod.GET, null, clientHttpResponse ->{            
            StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(destFile));
            return destFile;
        });
    }
}