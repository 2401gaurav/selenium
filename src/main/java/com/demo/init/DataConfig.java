package com.demo.init;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataConfig {
	ArrayList<String> list;
	XSSFWorkbook objExcel = null;
	XSSFSheet sheetobj;

	public DataConfig(String excelPath) {
		try {
			File src = new File(excelPath);

			FileInputStream fis = new FileInputStream(src);

			objExcel = new XSSFWorkbook(fis);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<String> fetchData(String sheetName) {
		list = new ArrayList<String>();
		sheetobj = objExcel.getSheet(sheetName);
		int row = objExcel.getSheet(sheetName).getLastRowNum();
		int col = sheetobj.getRow(0).getPhysicalNumberOfCells();

		for (int i = 1; i < row + 1; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter fmt = new DataFormatter();
				String val = fmt.formatCellValue(sheetobj.getRow(i).getCell(j));
				list.add(val);
			}
		}

		return list;
	}

	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd_MMM_yyyy h:mm");
		df.setTimeZone(TimeZone.getTimeZone("IST"));
		return df.format(new Date());
	}
  

    public void WritingToExcelResults(String action, String expectedResult, int rownumber, XSSFWorkbook workbook,
			XSSFSheet worksheet, boolean firstRow) {
		try {

			XSSFCell actionDataCell;
			XSSFFont scenarioCellfont = workbook.createFont();
			scenarioCellfont.setBold(true);
			scenarioCellfont.setItalic(true);

			XSSFFont headerCellfont = workbook.createFont();
			headerCellfont.setBold(true);
			headerCellfont.setColor(IndexedColors.WHITE.getIndex());

			// ****************************For Row Headings*******************//
			if (firstRow == true) {

				XSSFRow headerDataRow = worksheet.createRow((short) 0);
				XSSFCellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
				headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				headerCellStyle.setBorderLeft(BorderStyle.MEDIUM);
				headerCellStyle.setBorderTop(BorderStyle.MEDIUM);
				headerCellStyle.setBorderRight(BorderStyle.MEDIUM);
				headerCellStyle.setBorderBottom(BorderStyle.MEDIUM);
				headerCellStyle.setFont(headerCellfont);

				XSSFCell headerActionCell = headerDataRow.createCell((short) 0);
				headerActionCell.setCellValue("Test Case");
				headerCellStyle.setWrapText(true);
				headerActionCell.setCellStyle(headerCellStyle);

				XSSFCell headerExpectedResultCell = headerDataRow.createCell((short) 1);
				headerExpectedResultCell.setCellValue("Test Result");
				headerCellStyle.setWrapText(true);
				headerExpectedResultCell.setCellStyle(headerCellStyle);

				XSSFCell headerStatusCell = headerDataRow.createCell((short) 2);
				headerStatusCell.setCellValue("Status");
				headerCellStyle.setWrapText(true);
				headerStatusCell.setCellStyle(headerCellStyle);

				XSSFCell headerExecutionTimeCell = headerDataRow.createCell((short) 3);
				headerExecutionTimeCell.setCellValue("Date and Time of Execution");
				headerCellStyle.setWrapText(true);
				headerExecutionTimeCell.setCellStyle(headerCellStyle);

			}
			// ********************Dynamic data*******************//
			if (firstRow == false) {

				XSSFRow testStepDataRow = worksheet.createRow((short) rownumber);
				XSSFCellStyle testStepDataCellstyle = workbook.createCellStyle();
				testStepDataCellstyle.setWrapText(true);
				testStepDataCellstyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
				testStepDataCellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				testStepDataCellstyle.setBorderLeft(BorderStyle.MEDIUM);
				testStepDataCellstyle.setBorderTop(BorderStyle.MEDIUM);
				testStepDataCellstyle.setBorderRight(BorderStyle.MEDIUM);
				testStepDataCellstyle.setBorderBottom(BorderStyle.MEDIUM);

				XSSFCellStyle statusDataCellstyle = workbook.createCellStyle();
				statusDataCellstyle.setWrapText(true);
				statusDataCellstyle.setBorderLeft(BorderStyle.MEDIUM);
				statusDataCellstyle.setBorderTop(BorderStyle.MEDIUM);
				statusDataCellstyle.setBorderRight(BorderStyle.MEDIUM);
				statusDataCellstyle.setBorderBottom(BorderStyle.MEDIUM);

				if (action.toLowerCase().contains("scenario")) {

					actionDataCell = testStepDataRow.createCell((short) 0);
					actionDataCell.setCellValue(action);
					worksheet.addMergedRegion(new CellRangeAddress(rownumber, rownumber, 0, 3));
					testStepDataCellstyle.setFont(scenarioCellfont);
					testStepDataCellstyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
					actionDataCell.setCellStyle(testStepDataCellstyle);
				} else {
					actionDataCell = testStepDataRow.createCell((short) 0);
					actionDataCell.setCellValue(action);		
					actionDataCell.setCellStyle(testStepDataCellstyle);
				}

				if (!action.toLowerCase().contains("scenario")) {
					XSSFCell expectedResultDataCell = testStepDataRow.createCell((short) 1);
					expectedResultDataCell.setCellValue(expectedResult);
					expectedResultDataCell.setCellStyle(testStepDataCellstyle);
					XSSFCell statusDataCell = testStepDataRow.createCell((short) 2);
					if (expectedResult.toLowerCase().contains("error occured")
							|| expectedResult.toLowerCase().contains("exception")) {
						statusDataCell.setCellValue("Fail");
						statusDataCellstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
						statusDataCellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						statusDataCell.setCellStyle(statusDataCellstyle);

					} else if (expectedResult.toUpperCase().contains("N/A")) {
						statusDataCell.setCellValue("N/A");		
						statusDataCellstyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
						statusDataCellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						statusDataCell.setCellStyle(statusDataCellstyle);

					} else {
						statusDataCell.setCellValue("Pass");
						statusDataCellstyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
						statusDataCellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						statusDataCell.setCellStyle(statusDataCellstyle);
					}

					XSSFCell executionTimeDataCell = testStepDataRow.createCell((short) 3);
					executionTimeDataCell.setCellValue(getDate());
					executionTimeDataCell.setCellStyle(testStepDataCellstyle);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public HashMap<String, String> fetchInputData(String sheetName) {
		String keys = "";
		String value = "";
		HashMap<String, String> list = new HashMap<String, String>();
		sheetobj = objExcel.getSheet(sheetName);
		int row = objExcel.getSheet(sheetName).getLastRowNum();
		int col = sheetobj.getRow(0).getPhysicalNumberOfCells();

		for (int i = 0; i <= row; i++) {
			for (int j = 0; j < col; j++) {
				DataFormatter fmt = new DataFormatter();
				if (j == 0) {
					keys = fmt.formatCellValue(sheetobj.getRow(i).getCell(j));// System.out.println(fmt.formatCellValue(sheetobj.getRow(i).getCell(j)));
				} else {
					value = fmt.formatCellValue(sheetobj.getRow(i).getCell(j));// System.out.println(fmt.formatCellValue(sheetobj.getRow(i).getCell(j)));
				}
			}
			list.put(keys, value);
		}
		return list;
	}
}