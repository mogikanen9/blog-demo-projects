package com.mogikanen9.rest.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;

public class FileApiClient {

    private String apiUrl;

    public FileApiClient(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void uploadFile(Path fileToUpload) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.apiUrl+"/uploadFile"))
                .POST(BodyPublishers.ofString("data")).build();

        HttpResponse<?> response = client.send(request, BodyHandlers.discarding());
        System.out.println(response.statusCode());
    }

    public void downloadFile(String fileNameToDownload, Path destFile) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.apiUrl + "/download/" + fileNameToDownload)).build();

        HttpResponse<Path> response = client.send(request, BodyHandlers.ofFile(destFile));

        System.out.println("Response in file:" + response.body());
    }

}