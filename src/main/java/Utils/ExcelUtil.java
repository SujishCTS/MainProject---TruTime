package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static void write(ArrayList<String> trutimeDateList, ArrayList<String> systemDates) throws IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Detailss.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sh = workbook.createSheet("datess");

		//Set bold font
		Font font = workbook.createFont();
		font.setBold(true);
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		
		Row header = sh.createRow(0);
		Cell columnTitle1 = header.createCell(0);
		Cell columnTitle2 = header.createCell(1);
		columnTitle1.setCellValue("TruTime Date");
		columnTitle2.setCellValue("System Date");
		columnTitle1.setCellStyle(style);
		columnTitle2.setCellStyle(style);

		for (int i = 0; i < trutimeDateList.size(); i++) {
			Row row = sh.createRow(i + 1);
			row.createCell(0).setCellValue(trutimeDateList.get(i));
			row.createCell(1).setCellValue(systemDates.get(i));
			sh.autoSizeColumn(i);
		}
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		workbook.close();
	}

}
