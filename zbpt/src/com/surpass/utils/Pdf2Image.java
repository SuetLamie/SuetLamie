package com.surpass.utils;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import com.itextpdf.text.DocumentException;

public class Pdf2Image {
	
	public static void pdf2Image(String pdfPath, String impPath) throws IOException {
		Document document = null;
		document = new Document();
		document.setFile(pdfPath);
		float scale = 3f;
		float rotation = 0f;
		BufferedImage image = (BufferedImage) document.getPageImage(0, GraphicsRenderingHints.SCREEN,
				Page.BOUNDARY_CROPBOX, rotation, scale);
		RenderedImage rendImage = image;
		File file = new File(impPath);
		ImageIO.write(rendImage, "jpg", file);
		image.flush();
		document.dispose();
	}

	public static void main(String[] args) {
		String pdfPath = "E:\\work\\create file\\qzcs.pdf";
		String impPath = "E:\\work\\create file\\qzcs.jpg";
		try {
			pdf2Image(pdfPath, impPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pdfPath2 = "E:\\work\\create file\\jycx.pdf";
		String impPath2 = "E:\\work\\create file\\jycx.jpg";
		try {
			pdf2Image(pdfPath2, impPath2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}