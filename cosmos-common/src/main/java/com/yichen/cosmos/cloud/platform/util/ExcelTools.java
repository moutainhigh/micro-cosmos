package com.yichen.cosmos.cloud.platform.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by hzsj on 2017/6/12.
 */

public class ExcelTools {

    private Workbook wb;
    private int sheetNumber = 0;

    public ExcelTools() {
    }

    public ExcelTools(String filepath) {
        if (filepath == null) {
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if (".xls".equals(ext))
                this.wb = new HSSFWorkbook(is);
            else if (".xlsx".equals(ext))
                this.wb = new XSSFWorkbook(is);
            else
                this.wb = null;
            this.sheetNumber = wb.getNumberOfSheets();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean readFileInputStream(InputStream stream, String fileType) throws Exception {
        boolean flag = false;
        try {
            if (".xls".equals(fileType)) {
                this.wb = new HSSFWorkbook(stream);
                flag = true;
            } else if (".xlsx".equals(fileType)) {
                this.wb = new XSSFWorkbook(stream);
                flag = true;
            }
            this.sheetNumber = wb.getNumberOfSheets();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != stream)
                stream.close();
        }
        return flag;
    }

    /**
     * 读取title
     *
     * @return
     * @throws Exception
     */
    public List readExcelTitle(int sheetNumber) throws Exception {
        if (this.wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        Sheet sheet = this.wb.getSheetAt(sheetNumber);
        if (null == sheet) return null;
        Row row = sheet.getRow(0);
        int colNum = row.getLastCellNum();
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            if (null != row.getCell(i))
                title[i] = row.getCell(i).getStringCellValue();
        }
        return Arrays.asList(title);
    }

    /**
     * 读取内容
     *
     * @return
     * @throws Exception
     */
    public List<List<Object>> readExcelContent(int sheetNumber) throws Exception {
        if (this.wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        List content = new ArrayList();
        Sheet sheet = this.wb.getSheetAt(sheetNumber);

        int rowNum = sheet.getLastRowNum();
        Row row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();

        for (int i = 1; i <= rowNum; i++) {
            List objects = new ArrayList();
            row = sheet.getRow(i);
            int j = 0;
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                objects.add(obj);
                j++;
            }
            content.add(objects);
        }
        return content;
    }

    private Object getCellFormatValue(Cell cell) {
        DecimalFormat df = new DecimalFormat("0.00");
        Object cellvalue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case 0:
                    cellvalue = df.format(cell.getNumericCellValue());
                    break;
                case 2:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {
                        cellvalue = String.valueOf(df.format(cell.getNumericCellValue()));
                    }
                    break;
                case 1:
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                case 4:
                    cellvalue = Boolean.valueOf(cell.getBooleanCellValue());
                case 3:
            }
        }
        return cellvalue;
    }

    /**
     * 往excel写入数据
     *
     * @param list
     * @param startRow 从第几行开始写
     * @return
     */
    public Workbook writeContent(List<List<String>> list, int startRow, int sheetNumber) {
        if (null == wb) return null;
        Sheet sheet = this.wb.getSheetAt(sheetNumber);
        for (int rowNum = 0; rowNum < list.size(); rowNum++) {
            List contents = (List) list.get(rowNum);
            Row row = sheet.createRow(rowNum + startRow);
            for (int i = 0; i < contents.size(); i++) {
                Cell cell = row.createCell(i, 1);
                cell.setCellValue((String) contents.get(i));
            }
        }
        return wb;
    }

    /**
     * 新建excel 并写入数据
     *
     * @param list  第一行为 title  之后为content
     * @param width
     * @return
     */
    public Workbook createExcelFile(List<List<String>> list, int width, String sheetName, Workbook workbook) {
        try {
            if (workbook == null)
                workbook = new XSSFWorkbook();
            if (workbook != null) {
                Sheet sheet = workbook.createSheet(sheetName);
                Row row0 = sheet.createRow(0);
                for (int i = 0; i < ((List) list.get(0)).size(); i++) {
                    Cell cell_1 = row0.createCell(i, 1);
                    if (null != ((List) list.get(0)).get(i)) {
                        cell_1.setCellValue((String) ((List) list.get(0)).get(i));
                        sheet.setColumnWidth(i, width);
                    }
                }
                for (int rowNum = 1; rowNum < list.size(); rowNum++) {
                    List contents = (List) list.get(rowNum);
                    Row row = sheet.createRow(rowNum);
                    for (int i = 0; i < contents.size(); i++) {
                        Cell cell = row.createCell(i, 1);
                        cell.setCellValue((String) contents.get(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public String getSheetName(int number) {
        return this.wb.getSheetName(number);
    }

    public int getSheetNumber() {
        return sheetNumber;
    }

    public void setSheetNumber(int sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

}