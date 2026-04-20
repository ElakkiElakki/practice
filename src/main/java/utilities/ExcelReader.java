package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {

    static Workbook workbook;
    static Sheet sheet;

    public static void setExcelFile(String path, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    public static String getCellData(int row, int col) {
        Cell cell = sheet.getRow(row).getCell(col);

        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }

    public static int getRowCount() {
        return sheet.getLastRowNum();
    }
}