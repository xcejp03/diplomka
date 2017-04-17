package cz.vse.utils.excelexport;

import cz.vse.dto.ProjectStatsDTO;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 *
 * @author www.codejava.net
 */
@Component
public class ExcelBuilderProjects extends AbstractExcelView {
    private final Logger l = Logger.getLogger(this.getClass());

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        l.warn("ExcelBuilder začátek ");
        // get data model which is passed by the Spring container
        List<ProjectStatsDTO> projects = (List<ProjectStatsDTO>) model.get("projects");

        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Projects");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.OLIVE_GREEN.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(1).setCellValue("ID");
        header.getCell(1).setCellStyle(style);

        header.createCell(0).setCellValue("Název");
        header.getCell(0).setCellStyle(style);

        header.createCell(2).setCellValue("Vlastník");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Passed");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Failed");
        header.getCell(4).setCellStyle(style);

        header.createCell(5).setCellValue("Norun");
        header.getCell(5).setCellStyle(style);

        header.createCell(6).setCellValue("Celkem TC");
        header.getCell(6).setCellStyle(style);


        // create data rows
        int rowCount = 1;

        for (ProjectStatsDTO record : projects) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getName());
            aRow.createCell(1).setCellValue(record.getProject_id());
            aRow.createCell(2).setCellValue(record.getProjectOwner_name());
            aRow.createCell(3).setCellValue(record.getNumberOfPassedTCs());
            aRow.createCell(4).setCellValue(record.getNumberOfFailedTCs());
            aRow.createCell(5).setCellValue(record.getNumberOfNorunTCs());
            aRow.createCell(6).setCellValue(record.getNumberOfTCs());

        }
    }

}