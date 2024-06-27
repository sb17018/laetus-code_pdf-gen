package com.yawer.laetus_code_pdf_gen_create;

import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.itextpdf.text.DocumentException;
import com.yawer.laetus_code_bars_gen.CodeType;

import jakarta.servlet.http.HttpServletResponse;

public class PdfFetcher {

	public final static void getPDF(HttpServletResponse response, int codeValue, CodeType codeType)
			throws IOException, DocumentException, InterruptedException {

		PdfCreator pdfCreated = new PdfCreator(codeValue, codeType);

		File file = pdfCreated.createPdf();

		String fileSource = file.getPath();

		Path pdfPath = Paths.get(fileSource);
		byte[] pdfData = Files.readAllBytes(pdfPath);


		// Initialize response.
		// Some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
		response.reset();
		
		// Check http://www.iana.org/assignments/media-types for all types.
		// Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
		response.setContentType("application/pdf"); 
		
		// The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE,
		// it will use current request URL as filename instead.
		response.setHeader("Content-disposition", "attachment; filename=" + file.getName());

		// Write file to response.
		OutputStream output = response.getOutputStream();
		output.write(pdfData);
		output.close();

		response.flushBuffer();
		
		// file not needed anymore
		file.delete();
	}
}
