package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {
	// Reading data from Excel
	//File input stream is imp
	//XSSFRow type row object and getPhysicalNumberOfRows instead of getLastRowNum()
	//workbook is collection of sheets
	//Sheet is collections of rows
	//rows is collection of cell/col
	public static Object[][] readExcelData(String Path, String SheetName) throws IOException {
		FileInputStream FIS = new FileInputStream(new File(Path));
		Object[][] data = null;
		try (XSSFWorkbook workbook = new XSSFWorkbook(FIS)) {
			XSSFSheet sheet = workbook.getSheet(SheetName);
			int rowsCount = sheet.getPhysicalNumberOfRows();
			XSSFRow row = sheet.getRow(0);
			int colCount = row.getPhysicalNumberOfCells();
			data = new Object[rowsCount][colCount];
			for (int i = 0; i < rowsCount; i++) {
				row = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					data[i][j] = row.getCell(j).getStringCellValue().trim();
				}
			}
		}
		return data;
	}
}
