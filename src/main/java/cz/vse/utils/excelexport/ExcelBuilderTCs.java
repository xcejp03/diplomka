//package cz.vse.utils.excelExport;
//
//import cz.vse.entity.Person;
//import org.apache.log4j.Logger;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Font;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.view.document.AbstractExcelView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.Map;
//
///**
// * This class builds an Excel spreadsheet document using Apache POI library.
// * @author www.codejava.net
// *
// */
//@Component
//public class ExcelBuilderTCs extends AbstractExcelView {
//    private final Logger l = Logger.getLogger(this.getClass());
//
//    public class MyExcelView extends AbstractExcelView
//    {
//        @SuppressWarnings("unchecked")
//        protected void buildExcelDocument(Map<String, Object> model,
//                                          HSSFWorkbook workbook,
//                                          HttpServletRequest request,
//                                          HttpServletResponse response)
//        {
//            //VARIABLES REQUIRED IN MODEL
//            String sheetName = (String)model.get("sheetname");
//            List<String> headers = (List<String>)model.get("headers");
//            List<List<String>> results = (List<List<String>>)model.get("results");
//            List<String> numericColumns = new ArrayList<String>();
//            if (model.containsKey("numericcolumns"))
//                numericColumns = (List<String>)model.get("numericcolumns");
//            //BUILD DOC
//            HSSFSheet sheet = workbook.createSheet(sheetName);
//            sheet.setDefaultColumnWidth((short) 12);
//            int currentRow = 0;
//            short currentColumn = 0;
//            //CREATE STYLE FOR HEADER
//            HSSFCellStyle headerStyle = workbook.createCellStyle();
//            HSSFFont headerFont = workbook.createFont();
//            headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//            headerStyle.setFont(headerFont);
//            //POPULATE HEADER COLUMNS
//            HSSFRow headerRow = sheet.createRow(currentRow);
//            for(String header:headers){
//                HSSFRichTextString text = new HSSFRichTextString(header);
//                HSSFCell cell = headerRow.createCell(currentColumn);
//                cell.setCellStyle(headerStyle);
//                cell.setCellValue(text);
//                currentColumn++;
//            }
//            //POPULATE VALUE ROWS/COLUMNS
//            currentRow++;//exclude header
//            for(List<String> result: results){
//                currentColumn = 0;
//                HSSFRow row = sheet.createRow(currentRow);
//                for(String value : result){//used to count number of columns
//                    HSSFCell cell = row.createCell(currentColumn);
//                    if (numericColumns.contains(headers.get(currentColumn))){
//                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//                        cell.setCellValue(NumUtils.extractDoubleOrZero(value));
//                    } else {
//                        HSSFRichTextString text = new HSSFRichTextString(value);
//                        cell.setCellValue(text);
//                    }
//                    currentColumn++;
//                }
//                currentRow++;
//            }
//        }
//    }
//
//
//    @Override
//    protected void buildExcelDocument(Map<String, Object> model,
//                                      HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        l.warn("ExcelBuilder začátek ");
//        // get data model which is passed by the Spring container
//        List<Person> listBooks = (List<Person>) model.get("listBooks");
//
//        // create a new Excel sheet
//        HSSFSheet sheet = workbook.createSheet("Java Books");
//        sheet.setDefaultColumnWidth(30);
//
//        // create style for header cells
//        CellStyle style = workbook.createCellStyle();
//        Font font = workbook.createFont();
//        font.setFontName("Arial");
//        style.setFillForegroundColor(HSSFColor.BLUE.index);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        font.setColor(HSSFColor.WHITE.index);
//        style.setFont(font);
//
//        // create header row
//        HSSFRow header = sheet.createRow(0);
//
//        header.createCell(0).setCellValue("Book Title");
//        header.getCell(0).setCellStyle(style);
//
//        header.createCell(1).setCellValue("Author");
//        header.getCell(1).setCellStyle(style);
//
//        header.createCell(2).setCellValue("ISBN");
//        header.getCell(2).setCellStyle(style);
//
//        header.createCell(3).setCellValue("Published Date");
//        header.getCell(3).setCellStyle(style);
//
//        header.createCell(4).setCellValue("Price");
//        header.getCell(4).setCellStyle(style);
//
//        // create data rows
//        int rowCount = 1;
//
//        for (Person aBook : listBooks) {
//            HSSFRow aRow = sheet.createRow(rowCount++);
//            aRow.createCell(0).setCellValue(aBook.getName());
//            aRow.createCell(1).setCellValue(aBook.getId());
//            aRow.createCell(2).setCellValue(aBook.getPassword());
//            aRow.createCell(3).setCellValue(aBook.getName());
//            aRow.createCell(4).setCellValue(aBook.getUsername());
//        }
//    }
//
//}