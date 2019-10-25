package com.mogikanen9.rest.api.client;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DemoApplication.class).web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
				.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Downloading file ...");
		FileApiClient client = new FileApiClient("http://localhost:8080/file/download");
		client.downloadFile("eag_summary_report_by_period.rptdesign", new File("c:/opt/my.rptdesign"));
		System.out.println("Done");
	}

}
