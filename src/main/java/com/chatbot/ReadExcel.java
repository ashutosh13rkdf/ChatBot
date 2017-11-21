package com.chatbot;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static Financial getData(String duName, String type, String query,
			Map<String, List<Map<String, Financial>>> parentMap) {
		String data = "";
		Financial fin = new Financial();
		// System.out.println("method called: "+duName);
		List<Map<String, Financial>> val = parentMap.get("ERS-SDES-ONLINE-"+duName);
		for (Map<String, Financial> map : val) {
			String typeKey = (String) map.keySet().toArray()[0];
			if (typeKey.equalsIgnoreCase(type)) {
				
				fin = map.get(typeKey);
				//data = "Revenue : $" + fin.getRevenue()  + "  PM : $" + fin.getPm() + "  PM% : " + fin.getPm_percent();
				// data=fin.getRevenue();
			}
		}
		return fin;
	}

	@SuppressWarnings("deprecation")
	public static Map<String, List<Map<String, Financial>>> readFile(String fileType) {
		Map<String, List<Map<String, Financial>>> parentMap = new HashMap<String, List<Map<String, Financial>>>();
		try {
			FileInputStream file = new FileInputStream(new File("D:\\Download\\Budget_FY-18.xlsx"));
			//FileInputStream file = new FileInputStream(new File(new URI("\\\\10.98.134.81\\sites\\Online\\Finance\\Budget\\Budget_FY-18.xlsx")));
			//https://kx.hcl.com/sites/Online/SitePages/BOTS.aspx
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(Integer.parseInt(fileType));

			// Iterate through each rows one by onefgfg
			Iterator<Row> rowIterator = sheet.iterator();
			int rowCount = 1;

			String keyDU = "";
			String keyType = "";
			Financial financial = new Financial();
			while (rowIterator.hasNext()) {
				List<Map<String, Financial>> list = new ArrayList<Map<String, Financial>>();
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				int cellCount = 1;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (rowCount > 2) {

						if (cellCount == 1) {
							keyDU = cell.getStringCellValue();
						}
						if (cellCount > 1 && cellCount < 5) {
							if("0".equalsIgnoreCase(fileType)){
							keyType = "OWN";
							}else{
							keyType = "AMJ'17";	
							}
							Map<String, Financial> childMap = new HashMap<String, Financial>();
							Double formulaCellValue = 0.0;
							String cellValue = "";
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								cellValue = cell.getNumericCellValue() + "";
								break;
							case Cell.CELL_TYPE_STRING:
								cellValue = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_FORMULA:
								switch (cell.getCachedFormulaResultType()) {
								case Cell.CELL_TYPE_NUMERIC:
									formulaCellValue = cell.getNumericCellValue();
									break;
								case Cell.CELL_TYPE_STRING:
									cellValue = cell.getRichStringCellValue() + "";
									break;
								}
							}
							if (cellCount == 2) {
								if (cellValue.equalsIgnoreCase(""))
									cellValue = formulaCellValue + "";
								financial.setRevenue(cellValue);
							}
							if (cellCount == 3) {
								if (cellValue.equalsIgnoreCase(""))
									cellValue = formulaCellValue + "";
								financial.setPm(cellValue);
							}
							if (cellCount == 4) {
								formulaCellValue = (formulaCellValue * 100);
								DecimalFormat f = new DecimalFormat("##.0");
								cellValue = f.format(formulaCellValue) + "%";
								financial.setPm_percent(cellValue);
								childMap.put(keyType, financial);
								list.add(childMap);
								financial = new Financial();

							}
						}
						if (cellCount > 4 && cellCount < 8) {
							if("0".equalsIgnoreCase(fileType)){
								keyType = "DBBL-PTS";
								}else{
								keyType = "JAS'17";	
								}
							
							Map<String, Financial> childMap = new HashMap<String, Financial>();
							String cellValue = "";
							Double formulaCellValue = 0.0;
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								// System.out.print(cell.getNumericCellValue() +
								// "t");
								cellValue = cell.getNumericCellValue() + "";
								break;
							case Cell.CELL_TYPE_STRING:
								// System.out.print(cell.getStringCellValue() +
								// "t");
								cellValue = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_FORMULA:
								switch (cell.getCachedFormulaResultType()) {
								case Cell.CELL_TYPE_NUMERIC:
									formulaCellValue = cell.getNumericCellValue();
									break;
								case Cell.CELL_TYPE_STRING:
									cellValue = cell.getRichStringCellValue() + "";
									break;
								}
							}
							if (cellCount == 5) {
								if (cellValue.equalsIgnoreCase(""))
									cellValue = formulaCellValue + "";
								financial.setRevenue(cellValue);
							}
							if (cellCount == 6) {
								if (cellValue.equalsIgnoreCase(""))
									cellValue = formulaCellValue + "";
								financial.setPm(cellValue);
							}
							if (cellCount == 7) {
								formulaCellValue = (formulaCellValue * 100);
								DecimalFormat f = new DecimalFormat("##.0");
								cellValue = f.format(formulaCellValue) + "%";
								financial.setPm_percent(cellValue);
								childMap.put(keyType, financial);
								list.add(childMap);
								financial = new Financial();
							}
						}
						if (cellCount > 7 && cellCount < 11) {
							if("0".equalsIgnoreCase(fileType)){
								keyType = "DBBL-Internal";
								}else{
								keyType = "OND'17";	
								}
							
							Map<String, Financial> childMap = new HashMap<String, Financial>();
							String cellValue = "";
							Double formulaCellValue = 0.0;
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								// System.out.print(cell.getNumericCellValue() +
								// "t");
								cellValue = cell.getNumericCellValue() + "";
								break;
							case Cell.CELL_TYPE_STRING:
								// System.out.print(cell.getStringCellValue() +
								// "t");
								cellValue = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_FORMULA:
								switch (cell.getCachedFormulaResultType()) {
								case Cell.CELL_TYPE_NUMERIC:
									formulaCellValue = cell.getNumericCellValue();
									break;
								case Cell.CELL_TYPE_STRING:
									cellValue = cell.getRichStringCellValue() + "";
									break;
								}
							}
							if (cellCount == 8) {
								if (cellValue.equalsIgnoreCase(""))
									cellValue = formulaCellValue + "";
								financial.setRevenue(cellValue);
							}
							if (cellCount == 9) {
								if (cellValue.equalsIgnoreCase(""))
									cellValue = formulaCellValue + "";
								financial.setPm(cellValue);
							}
							if (cellCount == 10) {
								formulaCellValue = (formulaCellValue * 100);
								DecimalFormat f = new DecimalFormat("##.0");
								cellValue = f.format(formulaCellValue) + "%";
								financial.setPm_percent(cellValue);
								childMap.put(keyType, financial);
								list.add(childMap);
								financial = new Financial();
							}
						}
						if (cellCount > 10 && cellCount < 14) {
							
							if("0".equalsIgnoreCase(fileType)){
								keyType = "Total";
								}else{
								keyType = "JFM'18";	
								}
							

							Map<String, Financial> childMap = new HashMap<String, Financial>();
							String cellValue = "";
							Double formulaCellValue = 0.0;
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								// System.out.print(cell.getNumericCellValue() +
								// "t");
								cellValue = cell.getNumericCellValue() + "";
								break;
							case Cell.CELL_TYPE_STRING:
								// System.out.print(cell.getStringCellValue() +
								// "t");
								cellValue = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_FORMULA:
								switch (cell.getCachedFormulaResultType()) {
								case Cell.CELL_TYPE_NUMERIC:
									formulaCellValue = cell.getNumericCellValue();
									break;
								case Cell.CELL_TYPE_STRING:
									cellValue = cell.getRichStringCellValue() + "";
									break;
								}
							}
							if (cellCount == 11) {
								if (cellValue.equalsIgnoreCase(""))
									cellValue = formulaCellValue + "";
								financial.setRevenue(cellValue);
							}
							if (cellCount == 12) {
								if (cellValue.equalsIgnoreCase(""))
									cellValue = formulaCellValue + "";
								financial.setPm(cellValue);
							}
							if (cellCount == 13) {
								formulaCellValue = (formulaCellValue * 100);
								DecimalFormat f = new DecimalFormat("##.0");
								cellValue = f.format(formulaCellValue) + "%";
								financial.setPm_percent(cellValue);
								childMap.put(keyType, financial);
								list.add(childMap);
								financial = new Financial();
							}
						}

					}
					cellCount++;
				}
				// System.out.println("method called");
				parentMap.put(keyDU, list);
				rowCount++;
			}
			// System.out.println("parentMap size = " + parentMap.size());
			/*
			 * List<Map<String, Financial>> val =
			 * parentMap.get("ERS-SDES-ONLINE-M&E"); for(Map<String, Financial>
			 * map : val){ System.out.println(map.entrySet()); }
			 */

			file.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return parentMap;

	}
	/*
	 * public static void main(String[] args) { Map<String, List<Map<String,
	 * Financial>>> parentMap = readFile(); System.out.println("Data : " +
	 * ReadExcel.getData("ERS-SDES-ONLINE-M&E", "DBBL-PTS", "pm", parentMap)); }
	 */

}
