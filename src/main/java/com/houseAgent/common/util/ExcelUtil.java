package com.houseAgent.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	/**
	 * 读取Excel文件中的数据
	 * @param file  Excel文件
	 * @param isExcel2003  Excel版本
	 * @return 数据集合
	 */
	@SuppressWarnings("finally")
	public static  List getData(File file, boolean isExcel2003) {
		//result 第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
		List<List> result = new ArrayList<List>();
		try {
			//根据excel版本，获取工作薄workbook
			Workbook workbook = null;
	        if (isExcel2003) {
	        	workbook = new HSSFWorkbook(new FileInputStream(file));
	        } else {
	        	workbook = new XSSFWorkbook(new FileInputStream(file));
	        }
	        
			//获得 sheet总数
			int sheetCount = workbook.getNumberOfSheets();
			
			//遍历sheet
			for (int sheetIndex = 0; sheetIndex < sheetCount;sheetIndex++) {
				//获得指定的sheet对象
				Sheet sheet = workbook.getSheetAt(sheetIndex);
				//获得本sheet的总行数
				int rowCount =sheet.getLastRowNum();
				System.out.println(rowCount);
				//如果没有数据
				if(rowCount < 1){
					return result;
				}
				//如果有数据 进入下边
				
				//第0行规定为字段、属性名
				for (int rowIndex = 0; rowIndex<= rowCount; rowIndex++) {
					//准备rowData 收集每一行数据
					List<Object> rowData = new ArrayList<Object>();
					//获得行row对象
					Row row =sheet.getRow(rowIndex);
					//如果此行为空，则进入下一个循环
					if (row == null) {
						continue;
					}
					//如果此行为不为空进入以下逻辑
					//获得本行中单元格的个数
					int cellCount =row.getLastCellNum();
					//遍历每个单元格cell
					for (int cellIndex = 0;cellIndex < cellCount; cellIndex++) {
						//获得单元格cell对象
						Cell cell =row.getCell(cellIndex);
						//获得指定单元格中的数据
						Object cellContent =ExcelUtil.getCellContent(cell);
						//将单元格内容放入 行数据中
						rowData.add(cellContent);
					}
					
					//将每行的数据rowData 放入结果集中
					result.add(rowData);
				}
		    	//将每个sheet的结果 此处忽略掉收集了
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//遍历完整个excel之后
			return result;
		}
	}

	/*** 获取一个cell的数据类型
	  CELL_TYPE_STRING：字符型  
	  CELL_TYPE_NUMERIC：数值型   
	  CELL_TYPE_BOOLEAN：布尔型   
	  CELL_TYPE_FORMULA：公式型   
	  CELL_TYPE_BLANK：空值  
	 * @param cell
	 * @return
	 */
	protected static Object getCellContent(Cell cell) {
		Object result = null;
		//如果此单元格为空，则返回null;
		if (cell == null) {
			return result;
		}
	    //单元格对象不为空
	    //单元格类型：Numeric:0, String:1,Formula:2, Blank:3, Boolean:4, Error:5
		int cellType =cell.getCellType();
	    //判断单元格类型
		switch (cellType) {
			case HSSFCell.CELL_TYPE_STRING://CELL_TYPE_STRING：字符型 
		    	String tempResult=cell.getRichStringCellValue().getString();
		        result=ExcelUtil.rightTrim(tempResult);
		        break;
		    case HSSFCell.CELL_TYPE_NUMERIC://CELL_TYPE_NUMERIC：数值型  
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					result = cell.getDateCellValue();
				}else{
					result = cell.getNumericCellValue();  
				}
				break;
		    case HSSFCell.CELL_TYPE_FORMULA://CELL_TYPE_FORMULA：公式型
	            result =cell.getNumericCellValue();
	            break;
		    case HSSFCell.CELL_TYPE_BOOLEAN://CELL_TYPE_BOOLEAN：布尔型 
	            result =cell.getBooleanCellValue();
	            break;
		    case HSSFCell.CELL_TYPE_BLANK:
	            result = null;
	            break;
		    case HSSFCell.CELL_TYPE_ERROR:
	            result = null;
	            break;
		    default:
	           System.out.println("枚举了所有类型");
	            break;
	    }
	    return result;
     }

	/**
	 * 
	 * 去掉字符串右边的空格
	 * 
	 * @param str 要处理的字符串
	 * 
	 * @return 处理后的字符串
	 * 
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}
}
