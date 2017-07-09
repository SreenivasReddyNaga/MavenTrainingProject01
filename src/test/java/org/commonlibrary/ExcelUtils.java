package org.commonlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

public class ExcelUtils {

	public Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {

		Workbook workbook;

		if (excelFilePath.endsWith(".xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("Specified file type is not Excel file");
		}

		return workbook;
	}

	public Object[][] readExcelData(String excelFilePath, String sheetName) {
		List<Map<String, String>> listOfMaps = new ArrayList<Map<String, String>>();
		try {
			
			String value = null;			
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

			Workbook workbook = getWorkbook(inputStream, excelFilePath);
			Sheet sheet = workbook.getSheet(sheetName);

			Row headerRow = sheet.getRow(0);
			if (headerRow == null) {
				inputStream.close();
				workbook.close();
				return null;
			}

			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Map<String, String> data = new TreeMap<String, String>();
				Row currentRow = sheet.getRow(i);
				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
					Cell currentCell = currentRow.getCell(j);
					switch (currentCell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = currentCell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						Long longVal = (long) currentCell.getNumericCellValue();
						value = Long.valueOf(longVal).toString();
						break;
					}
					data.put(headerRow.getCell(j).getStringCellValue(), value);
					// Reporter.log(value,true);
				}
				if (data.size() > 0) {
					listOfMaps.add(data);
				} else {
					Reporter.log("Data not available in Map Row" + i);
				}
			}

			inputStream.close();
			workbook.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}		
		return convertToArray(listOfMaps);
	}

	public Object[][] convertToArray(List<Map<String, String>> dataMaps) {
		Reporter.log("Converting results to an Array for TestNG");
		Object[][] testData = new Object[dataMaps.size()][1];
		for (int index = 0; index < dataMaps.size(); index++) {
			testData[index][0] = dataMaps.get(index);
		}
		Reporter.log("Finished converting results to an Array for TestNG");
		return testData;
	}

}











