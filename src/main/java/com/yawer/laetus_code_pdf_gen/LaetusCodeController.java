package com.yawer.laetus_code_pdf_gen;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.yawer.laetus_code_bars_gen.CodeType;
import com.yawer.laetus_code_pdf_gen_create.PdfFetcher;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class LaetusCodeController {

	
	@GetMapping("/hello")
	public String hello() {
		return "Hello Word!";
	}

	@GetMapping("/code/{codeNumber}/{codeType}")
	@CrossOrigin(origins="*")
	public ResponseEntity<byte[]> display( @PathVariable int codeNumber, @PathVariable CodeType codeType)
			throws IOException, DocumentException, InterruptedException {
		
		    byte[] contents = PdfFetcher.getPDF(codeNumber, codeType);

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_PDF);
		    
		    // Here you have to set the actual filename of your pdf
		    String filename = "Pharmacode_" + codeNumber + "_" + codeType + ".pdf";
		    headers.setContentDispositionFormData("filename", filename);
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		    return response;

	}

}
