package com.yawer.laetus_code_pdf_gen_create;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.yawer.laetus_code_bars_gen.Bar;
import com.yawer.laetus_code_bars_gen.BarCombinationGen;
import com.yawer.laetus_code_bars_gen.CodeType;

public class PdfCreator {
	
	private final int CODE_VALUE;
	private final CodeType CODE_TYPE;
	private String filePath;

	private final static float UNIT_CONVERTER = 2.8346456692913385826771653543307f; // 1mm / (1/72) inch
	
	public PdfCreator(int codeValue, CodeType codeType) {
		this.CODE_VALUE = codeValue;
		this.CODE_TYPE = codeType;
		this.filePath = "src/main/resources/pdfs/Pharmacode_" + codeValue + "_" + codeType + ".pdf";
	}

	public File createPdf()
			throws IOException, DocumentException, InterruptedException {

		File file = new File(this.filePath);

		if (!file.exists()) {

			Document document = new Document();
			int pageWidth = 105;
			int pageHeight = 148;
			Rectangle pageSize = new Rectangle(pageWidth * UNIT_CONVERTER, pageHeight * UNIT_CONVERTER);
			document.setPageSize(pageSize);

			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(this.filePath));

			document.open();

			LineSeparator ls = new LineSeparator(1.0f, 110.0f, BaseColor.BLACK, 100, -20.0f);
			document.add(new Chunk(ls));

			PdfContentByte cb = pdfWriter.getDirectContent();
			cb.saveState();

			cb.setColorFill(new CMYKColor(0f, 0f, 0f, 1f));

			// adding bars combinations
			BarCombinationGen barsCombGen = new BarCombinationGen(this.CODE_VALUE, this.CODE_TYPE);
			List<Bar> barsComb = barsCombGen.getBarsCombination();
			float x = (pageWidth - barsCombGen.getCodeWidth()) * UNIT_CONVERTER / 2;
			float y = (pageHeight - barsCombGen.getCodeHeight()) * UNIT_CONVERTER / 2 - 20;
			float barWidth;
			float barHeight = barsCombGen.getCodeHeight() * UNIT_CONVERTER;
			float module = barsCombGen.getModule() * UNIT_CONVERTER;
			for (Bar b : barsComb) {
				barWidth = b.getWidth() * UNIT_CONVERTER;
				cb.rectangle(x, y, barWidth, barHeight);
				x = x + barWidth + module;
			}

			cb.fill();

			int fontSize = 12;
			BaseFont bf = BaseFont.createFont("src/main/resources/fonts/Roboto-Black.ttf", BaseFont.WINANSI,
					BaseFont.EMBEDDED);
			cb.beginText();
			cb.setFontAndSize(bf, fontSize);
			cb.moveText(25, 370);
			cb.showText("Pharmacode:");
			cb.endText();

			fontSize = 18;
			cb.beginText();
			cb.setFontAndSize(bf, fontSize);
			String codeTypeFormatted = this.CODE_TYPE.name();
			cb.moveText((pageWidth * UNIT_CONVERTER - bf.getWidthPoint(codeTypeFormatted, fontSize)) / 2, 270);
			cb.showText(codeTypeFormatted);
			cb.endText();

			fontSize = 36;
			BaseFont bfb = BaseFont.createFont("src/main/resources/fonts/Roboto-Bold.ttf", BaseFont.WINANSI,
					BaseFont.EMBEDDED);
			cb.beginText();
			cb.setFontAndSize(bfb, fontSize);
			cb.moveText((pageWidth * UNIT_CONVERTER - bf.getWidthPoint(String.valueOf(this.CODE_VALUE), fontSize)) / 2, 70);
			cb.showText(String.valueOf(this.CODE_VALUE));
			cb.endText();

			cb.restoreState();

			document.close();
		}
		return file;
	}
}
