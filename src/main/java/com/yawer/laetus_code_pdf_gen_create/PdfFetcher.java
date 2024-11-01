package com.yawer.laetus_code_pdf_gen_create;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.itextpdf.text.DocumentException;
import com.yawer.laetus_code_bars_gen.CodeType;


public class PdfFetcher {

	public final static byte[] getPDF(int codeValue, CodeType codeType)
			throws DocumentException, InterruptedException, IOException {

		PdfCreator pdfCreated = new PdfCreator(codeValue, codeType);

		File file = pdfCreated.createPdf();

		String fileSource = file.getPath();

		Path pdfPath = Paths.get(fileSource);
		byte[] pdfData = Files.readAllBytes(pdfPath);
		
		file.delete();
		
		return pdfData;
	}
}
