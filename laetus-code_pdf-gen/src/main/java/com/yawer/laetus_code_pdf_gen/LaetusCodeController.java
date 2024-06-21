package com.yawer.laetus_code_pdf_gen;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import com.yawer.laetus_code_bars_gen.CodeType;
import com.yawer.laetus_code_pdf_gen_create.PdfCreator;

@RestController
public class LaetusCodeController {

	@GetMapping("/code")
	public String hello() {
		return "Hello Word!";
	}

	@PostMapping("/code")
	public String print(@RequestBody String typeAndCodeValue)
			throws IOException, DocumentException, InterruptedException {
		String regex = "[,]";
		String[] codeParams = typeAndCodeValue.split(regex);
		int codeValue = Integer.parseInt(codeParams[1]);
		CodeType codeType = CodeType.valueOf(codeParams[0]);
		PdfCreator.createPdf(codeValue, codeType);
		return "";
	}

}
