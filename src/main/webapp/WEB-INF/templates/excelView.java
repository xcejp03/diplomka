package cz.vse.utils;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.vse.entity.Person;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
@Component
public class ExcelBuilder extends AbstractExcelView {
    private final Logger l = Logger.getLogger(this.getClass());
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        l.warn("ExcelBuilder začátek ");
        // get data model which is passed by the Spring container
        List<Person> listBooks = (List<Person>) model.get("listBooks");

        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Java Books");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Book Title");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Author");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("ISBN");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Published Date");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Price");
        header.getCell(4).setCellStyle(style);

        // create data rows
        int rowCount = 1;

        for (Person aBook : listBooks) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(aBook.getName());
            aRow.createCell(1).setCellValue(aBook.getId());
            aRow.createCell(2).setCellValue(aBook.getPassword());
            aRow.createCell(3).setCellValue(aBook.getName());
            aRow.createCell(4).setCellValue(aBook.getUsername());
        }
    }

}