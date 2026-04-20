package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

    private static final String FILE_PATH =
            "src/test/resources/testdata/FlightHotelData.xlsx";

    private static final String SHEET_NAME = "Sheet1";

    // =========================
    // 🔹 READ SINGLE CELL
    // =========================
    public static String getCellData(int rowNum, int colNum) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet(SHEET_NAME);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // =========================
    // 🔥 READ ALL TEST DATA
    // =========================
    public static List<Map<String, String>> getAllData() {

        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet(SHEET_NAME);

            Row header = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                // ⚠️ Skip empty rows
                if (row == null) continue;

                Map<String, String> data = new HashMap<>();

                for (int j = 0; j < header.getLastCellNum(); j++) {

                    DataFormatter formatter = new DataFormatter();

                    String key = formatter.formatCellValue(header.getCell(j));
                    String value = formatter.formatCellValue(row.getCell(j));

                    data.put(key, value);
                }

                dataList.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
}