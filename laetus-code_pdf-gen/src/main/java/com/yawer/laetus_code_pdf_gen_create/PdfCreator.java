package com.yawer.laetus_code_pdf_gen_create;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;

//import org.apache.pdfbox.cos.COSName;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
//import org.apache.pdfbox.pdmodel.font.encoding.Encoding;

import com.yawer.laetus_code_bars_gen.Bar;
import com.yawer.laetus_code_bars_gen.BarCombinationGen;
import com.yawer.laetus_code_bars_gen.CodeType;

//import jakarta.servlet.ServletOutputStream;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

public class PdfCreator {

	private final static float UNIT_CONVERTER = 2.8346456692913385826771653543307f; // 1mm / (1/72) inch

	private static int cMtU(float value) {
		return (int) (value * UNIT_CONVERTER);
	}

	private static int cUtM(float value) {
		return (int) (value / UNIT_CONVERTER);
	}

	public static void createPdf(int codeValue, CodeType codeType)
			throws IOException, ClassNotFoundException, DocumentException {
//		public static void createPdf(int codeValue, CodeType codeType, HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {

//		   response.setContentType("application/pdf");
//		    response.setHeader("Content-disposition","attachment;filename="+ "testPDF.pdf");
//		    try {
//		        File f = new File("src/main/resources/static/pdfs/Pharmacode_831.pdf");
//		        FileInputStream fis = new FileInputStream(f);
//		        DataOutputStream os = new DataOutputStream(response.getOutputStream());
//		        response.setHeader("Content-Length",String.valueOf(f.length()));
//		        byte[] buffer = new byte[1024];
//		        int len = 0;
//		        while ((len = fis.read(buffer)) >= 0) {
//		            os.write(buffer, 0, len);
//		        }
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }

//		  try {
//	            Document document = new Document();
//	            // step 2
//	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	            PdfWriter.getInstance(document, baos);
//	            // step 3
//	            document.open();
//	            // step 4
//	            document.add(new Paragraph(String.format(
//	                "You have submitted the following text.",
//	                request.getMethod())));
//	            document.add(new Paragraph("text"));
//	            // step 5
//	            document.close();
//
//	            // setting some response headers
//	            response.setHeader("Expires", "0");
//	            response.setHeader("Cache-Control",
//	                "must-revalidate, post-check=0, pre-check=0");
//	            response.setHeader("Pragma", "public");
//	            // setting the content type
//	            response.setContentType("application/pdf");
//	            // the contentlength
//	            response.setContentLength(baos.size());
//	            // write ByteArrayOutputStream to the ServletOutputStream
//	            ServletOutputStream os = response.getOutputStream();
////	            OutputStream os = response.getOutputStream();
//	            baos.writeTo(os);
//	            os.flush();
//	            os.close();
//	        }
//	        catch(DocumentException e) {
//	            throw new IOException(e.getMessage());
//	        }
//		

//		PDDocument document = new PDDocument();
//		PDPage page = new PDPage(PDRectangle.A5);
//		document.addPage(page);
//
//		File fontFile = new File("src/main/resources/static/fonts/Roboto-Regular.ttf");
//		PDTrueTypeFont pdfTrueType = PDTrueTypeFont.load(document, fontFile,
//				Encoding.getInstance(COSName.WIN_ANSI_ENCODING));
//
//		PDPageContentStream contentStream = new PDPageContentStream(document, page);
//		contentStream.setFont(pdfTrueType, 12);
//
//		BarCombinationGen barsComb = new BarCombinationGen();
//		String text = "";
//		for (Bar b : barsComb.generateBarsCombination(codeValue, codeType)) {
//			contentStream.beginText();
//			text = b.toString();
//			contentStream.showText(text);
//
//			contentStream.endText();
//		}
//		contentStream.close();
//
//		document.save("src/main/resources/static/pdfs/Pharmacode_" + codeValue + ".pdf");
//		document.close();

		Document document = new Document();
		int pageWidth = 148;
		int pageHeight = 105;
		Rectangle pageSize = new Rectangle(pageWidth * UNIT_CONVERTER, pageHeight * UNIT_CONVERTER);
		document.setPageSize(pageSize);
		File file = new File("src/main/resources/static/pdfs/Pharmacode_" + codeValue + ".pdf");

		if (!file.exists()) {
			PdfWriter pdfWriter = PdfWriter.getInstance(document,
					new FileOutputStream("src/main/resources/static/pdfs/Pharmacode_" + codeValue + ".pdf"));

			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 48, BaseColor.BLACK);
//			for (Bar b : barsComb.generateBarsCombination(codeValue, codeType)) {
//				text += b.toString();
//
//			}
//			Chunk chunk = new Chunk(String.valueOf(codeValue), font);

			PdfContentByte cb = pdfWriter.getDirectContent();
			cb.saveState();
			cb.setColorFill(new CMYKColor(0f, 0f, 0f, 1f));
			BarCombinationGen barsCombGen = new BarCombinationGen(codeValue, codeType);
			List<Bar> barsComb = barsCombGen.getBarsCombination();
			float x = (pageWidth - barsCombGen.getCodeWidth()) * UNIT_CONVERTER / 2;
			float y = (pageHeight - barsCombGen.getCodeHeight()) * UNIT_CONVERTER / 2;
				System.out.println(x);
			for (Bar b : barsComb) {
				cb.rectangle(x, y, cMtU(b.getWidth()), cUtM(barsCombGen.getCodeHeight()));
				x += cMtU(b.getWidth() + b.getModule());
			}
			cb.fill();
			int fontSize = 24;
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			cb.beginText();
			cb.setFontAndSize(bf, fontSize);
			cb.moveText((pageWidth * UNIT_CONVERTER - bf.getWidthPoint(codeType.name(), fontSize)) / 2, 220);
			cb.showText(codeType.name());
			cb.endText();
			
			fontSize = 48;
			bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			cb.beginText();
			cb.setFontAndSize(bf, fontSize);
			cb.moveText((pageWidth * UNIT_CONVERTER - bf.getWidthPoint(String.valueOf(codeValue), fontSize)) / 2, 40);
			cb.showText(String.valueOf(codeValue));
			cb.endText();
			cb.restoreState();

//			document.add(chunk);
			document.close();
		}

		// credits to https://stackoverflow.com/users/1300472/amit
		if (file.toString().endsWith(".pdf"))
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
		else {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
		}

//		file.delete();
	}
}
