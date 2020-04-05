package org.star_pet_house_commons.utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.*;
import jxl.write.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;


/*
 *@description:
 *@author jiafeng
 *@date 2020/4/4 0004 12:47
 */
public class ExcelUtil {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 导入excle,兼容xls和xlsx俩种格式
     * @param file
     * @param titles
     * @return
     */
    public static List<Map<String, String>> importExcel(File file, String[] titles, int sheetIndex) {
        List<Map<String, String>> res = new ArrayList<>();
        try {
            if (null != file && file.exists()) {
                org.apache.poi.ss.usermodel.Workbook book = WorkbookFactory.create(file);
                org.apache.poi.ss.usermodel.Sheet sheet = book.getSheetAt(sheetIndex);

                // 获取列数、行数
                int rowNum = sheet.getLastRowNum();
                for (int i = 1; i < rowNum + 1; i++) {
                    Row row = sheet.getRow(i);
                    // 非空行
                    if (row.getPhysicalNumberOfCells() > 0) {
                        Map<String, String> map = new HashMap<>();
                        for (int j = 0; j < titles.length; j++) {
                            Cell cell = row.getCell(j);

                            String key = titles[j];
                            String value = "";
                            if (cell != null) {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                value = cell.getStringCellValue() == null ? "" : cell.getStringCellValue();
                            }
                            map.put(key, value);
                        }
                        res.add(map);
                    }
                }

            }
        } catch (Exception e) {
            log.info("excel导入数据出错.", e);
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @author dell
     * @param os 文件输出流
     * @param list 页面查询返回的结果集
     * @param headerMap table表格的headItems
     * @param sheetName excel中的sheet名称
     */
    public static void export(OutputStream os, List<Map<String, Object>> list, Map<String, String> headerMap, String sheetName) {
        try {
            WritableWorkbook wbook = Workbook.createWorkbook(os);

            double maxSheetSize = 60000.0;
            int sheetNum = 1;// 默认为1,防止list为空时导出空excel后台报异常
            if (CollectionUtils.isNotEmpty(list)) {
                sheetNum = (int)Math.ceil(list.size() / maxSheetSize);
            }
            for (int num = 0; num < sheetNum; num++) {
                WritableSheet wsheet = wbook.createSheet(sheetName + "(" + (num + 1) + ")", num);
                wsheet.getSettings().setShowGridLines(false);
                wsheet.setColumnView(0, 2);// 设置第一列宽度

                Set<String> set = headerMap.keySet();
                String[] titles = set.toArray(new String[set.size()]);

                for (int i = 0; i < titles.length; i++) {
                    wsheet.setColumnView(i + 1, 20);// 设置列宽
                }
                // 循环写入表头内容
                for (int i = 0; i < titles.length; i++) {
                    String title =titles[i];

                    if(StringUtils.isNoneBlank(titles[i])){
                        String [] titlearray=titles[i].split(";");
                        title = titlearray[0];
                    }

                    wsheet.addCell(new Label(i + 1, 1, title, ExcelUtil.getHeaderCellStyle()));
                }

                // 写入数据
                for (int i = num * (int)maxSheetSize; i < (num + 1) * (int)maxSheetSize && i < list.size(); i++) {
                    Map<String, Object> m = list.get(i);
                    int temp = i - num * (int)maxSheetSize + 2;

                    for (int j = 0; j < titles.length; j++) {
                        Object objValue = m.get(headerMap.get(titles[j]));
                        String datatype="0";
                        if (objValue == null) {
                            objValue = "";
                        }
                        String value = objValue.toString();
                        String title = titles[j];
                        if(StringUtils.isNoneBlank(titles[j])){
                            String [] titlearray=titles[j].split(";");
                            title = titlearray[0];
                            if(titlearray.length>1){
                                if("1".equals(titlearray[1])){
                                    datatype="1";
                                    if(StringUtils.isBlank(value)){
                                        value="0";
                                    }

                                }else if("2".equals(titlearray[1])){
                                    datatype="2";
                                    if(StringUtils.isBlank(value)){
                                        value="0";
                                    }
                                }
                            }
                        }




                        if(StringUtils.isNotBlank(title)&&"数".equals(title.substring(title.length()-1))||"1".equals(datatype)||"2".equals(datatype)){
                            try{
                                if("1".equals(datatype)){
                                    Double  valuede=Double.parseDouble(value);
                                    wsheet.addCell(new jxl.write.Number(j + 1, temp, (int)Math.round(valuede),ExcelUtil.getBodyCellStyleNum(datatype)));
                                }else if("2".equals(datatype)){
                                    wsheet.addCell(new jxl.write.Number(j + 1, temp, Double.parseDouble(value),ExcelUtil.getBodyCellStyleNum(datatype)));

                                }else{
                                    wsheet.addCell(new jxl.write.Number(j + 1, temp, Double.parseDouble(value),ExcelUtil.getBodyCellStyle()));

                                }
                            }catch(Exception e){
                                wsheet.addCell(new Label(j + 1, temp, value, ExcelUtil.getBodyCellStyle()));
                            }
                        }else{
                            wsheet.addCell(new Label(j + 1, temp, value, ExcelUtil.getBodyCellStyle()));
                        }
                    }
                }
            }

            wbook.write();
            wbook.close();
            os.flush();
            os.close();
            os = null;

        } catch (Exception e) {
            log.info("exception heppended in " + ExcelUtil.class + " cause: ", e);
        }
    }

    /***
     * 解析格式为xls的excel文件
     * @author liaohui
     * @param file
     * @param titles
     * @return
     */
    public static List<Map<String, String>> importExcelXls(File file, String[] titles) {
        List<Map<String, String>> res = new ArrayList<Map<String, String>>();

        try {
            if (null != file && file.exists()) {
                Workbook book = Workbook.getWorkbook(file);
                Sheet[] sheets = book.getSheets();
                if (sheets.length > 0) {
                    Sheet sheet = sheets[0];
                    // 获取列数、行数
                    int colNum = sheet.getColumns();
                    int rowNum = sheet.getRows();
                    if (colNum == titles.length) {
                        // 从第二行获取表格内容
                        for (int i = 1; i < rowNum; i++) {
                            Map<String, String> map = new HashMap<String, String>();

                            for (int j = 0; j < colNum; j++) {
                                String key = titles[j];
                                String value = sheet.getCell(j, i).getContents();

                                map.put(key, value);
                            }
                            res.add(map);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    /***
     * 解析格式为xlsx的excel文件
     * @author liaohui
     * @param file
     * @param titles
     * @return
     */
    public static List<Map<String, String>> importExcelXlsx(File file, String[] titles) {
        List<Map<String, String>> res = new ArrayList<Map<String, String>>();

        try {
            if (null != file && file.exists()) {
                XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
                XSSFSheet sheet = xwb.getSheetAt(0);
                if (null != sheet) {
                    XSSFRow row;

                    int rowNum = sheet.getPhysicalNumberOfRows();
                    for (int i = 1; i < rowNum; i++) {
                        row = sheet.getRow(i);
                        int colNum = row.getPhysicalNumberOfCells();
                        if (colNum == titles.length) {
                            Map<String, String> map = new HashMap<String, String>();

                            for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
                                String key = titles[j];
                                String value = "";

                                if (Cell.CELL_TYPE_NUMERIC == row.getCell(j).getCellType()) {
                                    value = String.valueOf(new Double(row.getCell(j).getNumericCellValue()).intValue());
                                } else {
                                    value = row.getCell(j).getStringCellValue();
                                }

                                map.put(key, value);
                            }

                            res.add(map);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static WritableCellFormat getHeaderCellStyle() {
        WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat headerFormat = new WritableCellFormat(NumberFormats.TEXT);
        try {
            // 添加字体设置
            headerFormat.setFont(font);
            // 设置单元格背景色：表头为 灰色
            headerFormat.setBackground(Colour.GRAY_25);
            // 设置表头表格边框样式
            // 整个表格线为粗线、黑色
            headerFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            // 表头内容水平居中显示
            headerFormat.setAlignment(Alignment.CENTRE);
        } catch (WriteException e) {
            log.error("表头单元格样式设置失败！", e);
        }
        return headerFormat;
    }

    public static WritableCellFormat getBodyCellStyle() {
        WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat bodyFormat = new WritableCellFormat(font);

        try {
            // 设置单元格背景色：表体为白色
            bodyFormat.setBackground(Colour.WHITE);
            // 设置表头表格边框样式
            // 整个表格线为细线、黑色
            bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
        } catch (WriteException e) {
            // System.out.println("表体单元格样式设置失败！");
            log.error("表体单元格样式设置失败！", e);
        }
        return bodyFormat;
    }
    public static WritableCellFormat getBodyCellStyleNum(String type) {
        WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat bodyFormat = new WritableCellFormat(font);
        NumberFormat doubleFormat=new NumberFormat("0.00");
        if(type.equals("2")){
            bodyFormat=new WritableCellFormat(font,doubleFormat);
        }
        if(type.equals("1")){
            NumberFormat intFormat=new NumberFormat("0");
            bodyFormat=new WritableCellFormat(font,intFormat);
        }
        try {
            // 设置单元格背景色：表体为白色
            bodyFormat.setBackground(Colour.WHITE);
            // 设置表头表格边框样式
            // 整个表格线为细线、黑色
            bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
        } catch (WriteException e) {
            // System.out.println("表体单元格样式设置失败！");
            log.error("表体单元格样式设置失败！", e);
        }
        return bodyFormat;
    }

}
