package com.surpass.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcleStyle {

	private HSSFCellStyle TITLE;
	private HSSFCellStyle H_HEAD;
	private HSSFCellStyle V_HEAD;
	private HSSFCellStyle V_CLASS;
	private HSSFCellStyle COMMON;
	private HSSFCellStyle PERCENT;
	private HSSFCellStyle DECIMAL;
	private HSSFCellStyle DECIMAL_3;
	private HSSFCellStyle DOUBLE;
	private HSSFCellStyle INTEGER;
	private HSSFCellStyle TEXT;
	private HSSFCellStyle BORDER;

	public ExcleStyle(HSSFWorkbook wb) {
		HSSFDataFormat dataFormat = wb.createDataFormat();
		HSSFFont Ftitle = wb.createFont();
		HSSFFont Fhead = wb.createFont();
		HSSFFont Fclass = wb.createFont();
		HSSFFont Fother = wb.createFont();
		HSSFPalette palette = wb.getCustomPalette();

		TITLE = wb.createCellStyle();
		H_HEAD = wb.createCellStyle();
		V_HEAD = wb.createCellStyle();
		V_CLASS = wb.createCellStyle();
		COMMON = wb.createCellStyle();
		PERCENT = wb.createCellStyle();
		DECIMAL = wb.createCellStyle();
		DECIMAL_3 = wb.createCellStyle();
		DOUBLE = wb.createCellStyle();
		INTEGER = wb.createCellStyle();
		TEXT = wb.createCellStyle();
		BORDER = wb.createCellStyle();

		Ftitle.setFontHeightInPoints((short) 18);
		Ftitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		Fhead.setFontHeightInPoints((short) 12);
		Fhead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		Fother.setFontHeightInPoints((short) 8);

		Fclass.setFontHeightInPoints((short) 8);
		Fclass.setColor(HSSFFont.COLOR_RED);

		TITLE.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		TITLE.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		TITLE.setBorderRight(HSSFCellStyle.BORDER_THIN);
		TITLE.setBorderTop(HSSFCellStyle.BORDER_THIN);
		TITLE.setBottomBorderColor(HSSFColor.BLACK.index);
		TITLE.setLeftBorderColor(HSSFColor.BLACK.index);
		TITLE.setRightBorderColor(HSSFColor.BLACK.index);
		TITLE.setTopBorderColor(HSSFColor.BLACK.index);
		TITLE.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		TITLE.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		TITLE.setWrapText(true);
		TITLE.setFont(Ftitle);

		H_HEAD.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		H_HEAD.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		H_HEAD.setBorderRight(HSSFCellStyle.BORDER_THIN);
		H_HEAD.setBorderTop(HSSFCellStyle.BORDER_THIN);
		H_HEAD.setBottomBorderColor(HSSFColor.BLACK.index);
		H_HEAD.setLeftBorderColor(HSSFColor.BLACK.index);
		H_HEAD.setRightBorderColor(HSSFColor.BLACK.index);
		H_HEAD.setTopBorderColor(HSSFColor.BLACK.index);
		H_HEAD.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		H_HEAD.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		H_HEAD.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		H_HEAD.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
		palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 204, (byte) 255, (byte) 204);
		H_HEAD.setWrapText(true);
		H_HEAD.setFont(Fhead);

		V_HEAD.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		V_HEAD.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		V_HEAD.setBorderRight(HSSFCellStyle.BORDER_THIN);
		V_HEAD.setBorderTop(HSSFCellStyle.BORDER_THIN);
		V_HEAD.setBottomBorderColor(HSSFColor.BLACK.index);
		V_HEAD.setLeftBorderColor(HSSFColor.BLACK.index);
		V_HEAD.setRightBorderColor(HSSFColor.BLACK.index);
		V_HEAD.setTopBorderColor(HSSFColor.BLACK.index);
		V_HEAD.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		V_HEAD.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		V_HEAD.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		V_HEAD.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
		palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 204, (byte) 255, (byte) 204);
		V_HEAD.setWrapText(true);
		V_HEAD.setFont(Fother);

		V_CLASS.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		V_CLASS.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		V_CLASS.setBorderRight(HSSFCellStyle.BORDER_THIN);
		V_CLASS.setBorderTop(HSSFCellStyle.BORDER_THIN);
		V_CLASS.setBottomBorderColor(HSSFColor.BLACK.index);
		V_CLASS.setLeftBorderColor(HSSFColor.BLACK.index);
		V_CLASS.setRightBorderColor(HSSFColor.BLACK.index);
		V_CLASS.setTopBorderColor(HSSFColor.BLACK.index);
		V_CLASS.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		V_CLASS.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		V_CLASS.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		V_CLASS.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
		palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 204, (byte) 255, (byte) 204);
		V_CLASS.setWrapText(true);
		V_CLASS.setFont(Fclass);

		COMMON.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		COMMON.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		COMMON.setBorderRight(HSSFCellStyle.BORDER_THIN);
		COMMON.setBorderTop(HSSFCellStyle.BORDER_THIN);
		COMMON.setBottomBorderColor(HSSFColor.BLACK.index);
		COMMON.setLeftBorderColor(HSSFColor.BLACK.index);
		COMMON.setRightBorderColor(HSSFColor.BLACK.index);
		COMMON.setTopBorderColor(HSSFColor.BLACK.index);
		COMMON.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		COMMON.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		COMMON.setWrapText(true);
		COMMON.setFont(Fother);

		PERCENT.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		PERCENT.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		PERCENT.setBorderRight(HSSFCellStyle.BORDER_THIN);
		PERCENT.setBorderTop(HSSFCellStyle.BORDER_THIN);
		PERCENT.setBottomBorderColor(HSSFColor.BLACK.index);
		PERCENT.setLeftBorderColor(HSSFColor.BLACK.index);
		PERCENT.setRightBorderColor(HSSFColor.BLACK.index);
		PERCENT.setTopBorderColor(HSSFColor.BLACK.index);
		PERCENT.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
		PERCENT.setWrapText(true);
		PERCENT.setFont(Fother);

		DECIMAL.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		DECIMAL.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		DECIMAL.setBorderRight(HSSFCellStyle.BORDER_THIN);
		DECIMAL.setBorderTop(HSSFCellStyle.BORDER_THIN);
		DECIMAL.setBottomBorderColor(HSSFColor.BLACK.index);
		DECIMAL.setLeftBorderColor(HSSFColor.BLACK.index);
		DECIMAL.setRightBorderColor(HSSFColor.BLACK.index);
		DECIMAL.setTopBorderColor(HSSFColor.BLACK.index);
		DECIMAL.setDataFormat(dataFormat.getFormat("###0.0000"));
		DECIMAL.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		DECIMAL.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		DECIMAL.setWrapText(true);
		DECIMAL.setFont(Fother);

		DECIMAL_3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		DECIMAL_3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		DECIMAL_3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		DECIMAL_3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		DECIMAL_3.setBottomBorderColor(HSSFColor.BLACK.index);
		DECIMAL_3.setLeftBorderColor(HSSFColor.BLACK.index);
		DECIMAL_3.setRightBorderColor(HSSFColor.BLACK.index);
		DECIMAL_3.setTopBorderColor(HSSFColor.BLACK.index);
		DECIMAL_3.setDataFormat(dataFormat.getFormat("###0.000"));
		DECIMAL_3.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		DECIMAL_3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		DECIMAL_3.setWrapText(true);
		DECIMAL_3.setFont(Fother);

		DOUBLE.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		DOUBLE.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		DOUBLE.setBorderRight(HSSFCellStyle.BORDER_THIN);
		DOUBLE.setBorderTop(HSSFCellStyle.BORDER_THIN);
		DOUBLE.setBottomBorderColor(HSSFColor.BLACK.index);
		DOUBLE.setLeftBorderColor(HSSFColor.BLACK.index);
		DOUBLE.setRightBorderColor(HSSFColor.BLACK.index);
		DOUBLE.setTopBorderColor(HSSFColor.BLACK.index);
		DOUBLE.setDataFormat(dataFormat.getFormat("###0.00"));
		DOUBLE.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		DOUBLE.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		DOUBLE.setWrapText(true);
		DOUBLE.setFont(Fother);

		INTEGER.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		INTEGER.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		INTEGER.setBorderRight(HSSFCellStyle.BORDER_THIN);
		INTEGER.setBorderTop(HSSFCellStyle.BORDER_THIN);
		INTEGER.setBottomBorderColor(HSSFColor.BLACK.index);
		INTEGER.setLeftBorderColor(HSSFColor.BLACK.index);
		INTEGER.setRightBorderColor(HSSFColor.BLACK.index);
		INTEGER.setTopBorderColor(HSSFColor.BLACK.index);
		INTEGER.setDataFormat(dataFormat.getFormat("###0"));
		INTEGER.setWrapText(true);
		INTEGER.setFont(Fother);

		TEXT.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		TEXT.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		TEXT.setBorderRight(HSSFCellStyle.BORDER_THIN);
		TEXT.setBorderTop(HSSFCellStyle.BORDER_THIN);
		TEXT.setBottomBorderColor(HSSFColor.BLACK.index);
		TEXT.setLeftBorderColor(HSSFColor.BLACK.index);
		TEXT.setRightBorderColor(HSSFColor.BLACK.index);
		TEXT.setTopBorderColor(HSSFColor.BLACK.index);
		TEXT.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
		TEXT.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		TEXT.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		TEXT.setWrapText(true);
		TEXT.setFont(Fother);

		BORDER.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		BORDER.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		BORDER.setBorderRight(HSSFCellStyle.BORDER_THIN);
		BORDER.setBorderTop(HSSFCellStyle.BORDER_THIN);
		BORDER.setBottomBorderColor(HSSFColor.BLACK.index);
		BORDER.setLeftBorderColor(HSSFColor.BLACK.index);
		BORDER.setRightBorderColor(HSSFColor.BLACK.index);
		BORDER.setTopBorderColor(HSSFColor.BLACK.index);
	}

	public HSSFCellStyle getDECIMAL_3() {
		return DECIMAL_3;
	}

	public HSSFCellStyle getTITLE() {
		return TITLE;
	}

	public void setTITLE(HSSFCellStyle tITLE) {
		TITLE = tITLE;
	}

	public HSSFCellStyle getH_HEAD() {
		return H_HEAD;
	}

	public void setH_HEAD(HSSFCellStyle hHEAD) {
		H_HEAD = hHEAD;
	}

	public HSSFCellStyle getV_HEAD() {
		return V_HEAD;
	}

	public void setV_HEAD(HSSFCellStyle vHEAD) {
		V_HEAD = vHEAD;
	}

	public HSSFCellStyle getCOMMON() {
		return COMMON;
	}

	public void setCOMMON(HSSFCellStyle cOMMON) {
		COMMON = cOMMON;
	}

	public HSSFCellStyle getPERCENT() {
		return PERCENT;
	}

	public void setPERCENT(HSSFCellStyle pERCENT) {
		PERCENT = pERCENT;
	}

	public HSSFCellStyle getDECIMAL() {
		return DECIMAL;
	}

	public void setDECIMAL(HSSFCellStyle dECIMAL) {
		DECIMAL = dECIMAL;
	}

	public HSSFCellStyle getTEXT() {
		return TEXT;
	}

	public void setTEXT(HSSFCellStyle tEXT) {
		TEXT = tEXT;
	}

	public HSSFCellStyle getBORDER() {
		return BORDER;
	}

	public void setBORDER(HSSFCellStyle bORDER) {
		BORDER = bORDER;
	}

	public HSSFCellStyle getV_CLASS() {
		return V_CLASS;
	}

	public void setV_CLASS(HSSFCellStyle vCLASS) {
		V_CLASS = vCLASS;
	}

	public HSSFCellStyle getINTEGER() {
		return INTEGER;
	}

	public void setINTEGER(HSSFCellStyle iNTEGER) {
		INTEGER = iNTEGER;
	}

	public HSSFCellStyle getDOUBLE() {
		return DOUBLE;
	}

	public void setDOUBLE(HSSFCellStyle dOUBLE) {
		DOUBLE = dOUBLE;
	}

}