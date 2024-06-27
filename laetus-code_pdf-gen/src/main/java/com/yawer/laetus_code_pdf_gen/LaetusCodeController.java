package com.yawer.laetus_code_pdf_gen;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.yawer.laetus_code_bars_gen.CodeType;
import com.yawer.laetus_code_pdf_gen_create.PdfFetcher;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LaetusCodeController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello Word!";
	}

	@GetMapping("/code/{codeNumber}/{codeType}")
	public String display(HttpServletResponse response, @PathVariable int codeNumber, @PathVariable CodeType codeType)
			throws IOException, DocumentException, InterruptedException {

		PdfFetcher.getPDF(response, codeNumber, codeType);

		return "Done";
	}

}
