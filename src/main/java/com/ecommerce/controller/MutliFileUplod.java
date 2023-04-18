package com.ecommerce.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mutliFileUplod")
public class MutliFileUplod {
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String mutliFileUplod(@RequestParam(value = "files") MultipartFile[] files){
	    for(MultipartFile file : files){
	        System.out.println(file.getOriginalFilename());
	        try {
				System.out.println(file.getInputStream().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return "multiple files upload successfully";
	}
	
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<Object> downloadFile() throws FileNotFoundException {
	    String fileName = "C:/Users/wds/Desktop/test.txt";
	    File file = new File(fileName);
	    InputStreamResource resource = new InputStreamResource(new FileInputStream((file)));

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition",String.format("attachment;filename=\"%s\"",file.getName()));
	    headers.add("Cache-Control","no-cache,no-store,must-revalidate");
	    headers.add("Pragma","no-cache");
	    headers.add("Expires","0");

	    ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
	            .contentType(MediaType.parseMediaType("application/text")).body(resource);
	    return responseEntity;
	}
	
}
