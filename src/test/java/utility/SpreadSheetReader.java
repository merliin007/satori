/**
 * Created by Miguel Angel Aguilar
 * maaguilar@sciodev.com - feb 2019
 **/

package utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpreadSheetReader {

    public List<String> getRoleRowFromSpreadSheet(String role) throws IOException {
        File excelFile = new File("files/ReportPermissions.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        List<String> permissions = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIt = sheet.iterator();

        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (!cell.toString().toLowerCase().equals(role)) break;
                else {

                    while (cellIterator.hasNext()) {
                        Cell cel = cellIterator.next();
                        if(cel.toString().equals("x")) {
                            permissions.add(cel.toString().toLowerCase());

                        }
                    }
                    return permissions;
                }
            }
        }
        workbook.close();
        fis.close();
        return null;
    }


}
