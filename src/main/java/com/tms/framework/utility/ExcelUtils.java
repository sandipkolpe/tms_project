package com.tms.framework.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    private static final String EXCEL_FILE_PATH = "C:\\Users\\sunday\\eclipse-workspace\\Sandip\\TMS-Project\\excelFile\\Excel_Data.xlsx"; // Update with your file path

    public static List<String[]> getUserData() throws IOException {
        FileInputStream fis = new FileInputStream(EXCEL_FILE_PATH);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming user data is in the first sheet

        List<String[]> userList = new ArrayList<>();

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from 1 to skip header
            Row row = sheet.getRow(i);
            
            // Check if row is null to avoid NullPointerException
            if (row == null) {
                System.out.println("Skipping empty row: " + i);
                continue; // Skip this row if it's null
            }

            String firstName = getCellValueAsString(row.getCell(0));
            String lastName = getCellValueAsString(row.getCell(1));
            String email = getCellValueAsString(row.getCell(2));
            String mobileNo = getCellValueAsString(row.getCell(3));
            String privilege = getCellValueAsString(row.getCell(4));
            String clientName = getCellValueAsString(row.getCell(5));
            String password = getCellValueAsString(row.getCell(6));
            String reEnterPassword = getCellValueAsString(row.getCell(7));
            String domainName = getCellValueAsString(row.getCell(8));

            userList.add(new String[]{firstName, lastName, email, mobileNo, privilege, clientName, password, reEnterPassword, domainName});
        }

        workbook.close();
        fis.close();

        return userList;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return ""; // Return empty string for null cells
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Convert numeric cell value to string
                double numericValue = cell.getNumericCellValue();
                // Check if the number is an integer (no decimal part)
                if (numericValue == Math.floor(numericValue)) {
                    // Return as an integer
                    return String.valueOf((long) numericValue);
                } else {
                    // Return the full numeric value as a string
                    return String.valueOf(numericValue);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return ""; // Return empty string for other cell types
        }
    }
}
