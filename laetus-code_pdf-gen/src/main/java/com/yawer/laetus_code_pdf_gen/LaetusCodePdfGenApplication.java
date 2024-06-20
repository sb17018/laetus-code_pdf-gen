package com.yawer.laetus_code_pdf_gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletResponse;

@SpringBootApplication
public class LaetusCodePdfGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaetusCodePdfGenApplication.class, args);
	}

}
