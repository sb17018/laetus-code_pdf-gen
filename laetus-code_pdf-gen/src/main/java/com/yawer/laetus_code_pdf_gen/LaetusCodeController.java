package com.yawer.laetus_code_pdf_gen;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.itextpdf.text.DocumentException;
import com.yawer.laetus_code_bars_gen.BarCombinationGen;
import com.yawer.laetus_code_bars_gen.CodeType;
import com.yawer.laetus_code_pdf_gen_create.PdfCreator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LaetusCodeController {
	
	@GetMapping("/code")
	public String hello() {
		return "Hello Word!";
	}
	
	@PostMapping("/code")
	public String print(@RequestBody String typeAndCodeValue) throws IOException, ClassNotFoundException, DocumentException {
//		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//		HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
//		HttpServletRequest request = 
//		        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
//		                .getRequest();
//		System.out.println("From request: " + request.getAttributeNames().toString());
		
	    String regex = "[,]";
	    String[] codeParams = typeAndCodeValue.split(regex);
	    int codeValue = Integer.parseInt(codeParams[1]);
	    CodeType codeType = CodeType.valueOf(codeParams[0]);
		System.out.println(new BarCombinationGen().generateBarsCombination(codeValue, codeType));
		PdfCreator.createPdf(codeValue, codeType);
//		PdfCreator.createPdf(codeValue, codeType, request, response);
		System.out.println(codeValue);
		return "";
	}

}
