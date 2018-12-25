package com.dqgb.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.dqgb.common.domain.ExcelData;

/**
 * @ClassName: FileUtil
 */
public class FileUtil {

	/**
	 * 获取文件类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtName(String fileName) {
		if (fileName != null) {
			int i = fileName.lastIndexOf('.');
			if (i > -1) {
				return fileName.substring(i + 1).toLowerCase();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param fileBytes
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	public static void uploadFile(byte[] fileBytes, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(fileBytes);
		out.flush();
		out.close();
	}

	/**
	 * 创建ZIP文件
	 * 
	 * @param sourcePath
	 *            文件或文件夹路径
	 * @param zipPath
	 *            生成的zip文件存在路径（包括文件名）
	 */
	public static void createZip(String sourcePath, String zipPath) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipPath);
			zos = new ZipOutputStream(fos);
			zos.setEncoding("gbk");// 此处修改字节码方式。
			writeZip(new File(sourcePath), "", zos);
		} catch (FileNotFoundException e) {

		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {

			}

		}
	}

	private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
		if (file.exists()) {
			if (file.isDirectory()) {// 处理文件夹
				parentPath += file.getName() + File.separator;
				File[] files = file.listFiles();
				if (files.length != 0) {
					for (File f : files) {
						writeZip(f, parentPath, zos);
					}
				} else { // 空目录则创建当前目录
					try {
						zos.putNextEntry(new ZipEntry(parentPath));
					} catch (IOException e) {

					}
				}
			} else {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					ZipEntry ze = new ZipEntry(parentPath + file.getName());
					zos.putNextEntry(ze);
					byte[] content = new byte[1024];
					int len;
					while ((len = fis.read(content)) != -1) {
						zos.write(content, 0, len);
						zos.flush();
					}

				} catch (FileNotFoundException e) {

				} catch (IOException e) {

				} finally {
					try {
						if (fis != null) {
							fis.close();
						}
					} catch (IOException e) {

					}
				}
			}
		}
	}

	/*********************************** 生成excel表格 **********************************/
	/**
	 * 
	 * @Description:创建excel表格，包含多个sheet页面.
	 *
	 * @author wenzhang
	 * @date:2018年2月13日 上午8:58:36
	 * @Title:create97Excel
	 * @param map
	 *            Map<sheet名称, List<Map<对应列中文名称, 列属性>>>
	 * @param fileExtName
	 *            文件后缀名
	 * @return
	 * @since JDK 1.8
	 */
	public static Workbook createExcel(Map<String, ExcelData> map, String fileExtName) {
		Workbook wb = null;
		if (null != map && !map.isEmpty()) {
			// map不为空，则按照不同版本创建表格
			wb = "xls".equals(fileExtName) ? new HSSFWorkbook() : new XSSFWorkbook();
			// 遍历map，建立不同sheet页，及命名
			for (String key : map.keySet()) {
				Sheet sheet = wb.createSheet(key);// 建立sheet页
				createSingleSheet(sheet, map.get(key));// 对sheet页表格赋值
			}
		}
		return wb;
	}

	/**
	 * 
	 * @Description:对每个sheet页进行表格赋值
	 *
	 * @author wenzhang
	 * @date:2018年2月13日 上午9:39:17
	 * @Title:createSingleSheet
	 * @param sheet
	 * @param list
	 * @since JDK 1.8
	 */
	private static void createSingleSheet(Sheet sheet, ExcelData excelData) {
		if (null != excelData) {
			createExcelHead(sheet, excelData.getHeadNameArr());
			createExcelBody(sheet, excelData.getBodyData());
		}
	}

	/**
	 * 
	 * @Description:创建表格表头.
	 *
	 * @author wenzhang
	 * @date:2018年2月13日 上午9:49:59
	 * @Title:createExcelHead
	 * @param sheet
	 *            sheet页
	 * @param map
	 *            表格表头内
	 * @since JDK 1.8
	 */
	private static void createExcelHead(Sheet sheet, String[] headNameArr) {
		// 表头
		Row headRow = sheet.createRow(0);
		if (null != headNameArr && headNameArr.length > 0) {
			headRow.createCell(0).setCellValue("序号");
			int i = 1;// 从左往右，表格列数
			for (String key : headNameArr) {
				headRow.createCell(i).setCellValue(key);
				i++;
			}
		}
	}

	/**
	 * 
	 * @Description:表体内容填充
	 *
	 * @author wenzhang
	 * @date:2018年2月13日 上午10:58:12
	 * @Title:createExcelBody
	 * @param sheet
	 * @param list
	 * @since JDK 1.8
	 */
	private static void createExcelBody(Sheet sheet, List<Object[]> bodyData) {
		if (null != bodyData && bodyData.size() > 0) {
			int rowIndex = 1;// 表头已建立，表体从1开始
			for (Object[] arr : bodyData) {
				if (null != arr && arr.length > 0) {
					// 创建该行单元格
					Row bodyRow = sheet.createRow(rowIndex);
					bodyRow.createCell(0).setCellValue(rowIndex);
					int colIndex = 1;// 从左往右，表格列数
					for (Object obj : arr) {
						bodyRow.createCell(colIndex).setCellValue(String.valueOf(obj));
						colIndex++;
					}
					rowIndex++;
				}
			}
		}
	}

	/**
	 * 
	 * @Description:读取excel表格，得到基本数据.
	 *
	 * @author wenzhang
	 * @date:2018年2月13日 下午2:00:44
	 * @Title:readExcel
	 * @param is
	 *            输入流
	 * @param fileExtName
	 *            文件后缀名
	 * @return Map<String, ExcelData>
	 * @since JDK 1.8
	 */
	public static Map<String, ExcelData> readExcel(InputStream is, String fileExtName) {
		try {
			Map<String, ExcelData> map = null;
			if (null != is) {
				// 根据文件后名缀选择创建Workbook的方式
				Workbook wb = null;
				wb = "xlsx".equals(fileExtName) ? new XSSFWorkbook(is) : new HSSFWorkbook(is);
				int totalSheetNumber = wb.getNumberOfSheets();// sheet页个数
				if (totalSheetNumber > 0) {
					map = new HashMap<String, ExcelData>();
					for (int i = 0; i < totalSheetNumber; i++) {
						Sheet sheet = wb.getSheetAt(i);
						String name = sheet.getSheetName();// sheet页名称
						ExcelData excelData = readSingleSheet(sheet);// 每个sheet页的内容
						map.put(name, excelData);
					}
				}
			}
			return map;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 
	 * @Description:读取单个sheet页面。必须包含表头，且在第一行.
	 *
	 * @author wenzhang
	 * @date:2018年2月13日 下午2:25:55
	 * @Title:readSingleSheet
	 * @param sheet
	 * @return
	 * @since JDK 1.8
	 */
	private static ExcelData readSingleSheet(Sheet sheet) {
		ExcelData excelData = new ExcelData();
		excelData.setHeadNameArr(readExcelHead(sheet));
		excelData.setBodyData(readExcelBody(sheet));
		return excelData;
	}

	/**
	 * 
	 * @Description:读取sheet页表头信息.
	 *
	 * @author wenzhang
	 * @date:2018年2月13日 下午2:28:45
	 * @Title:readExcelHead
	 * @param sheet
	 * @return
	 * @since JDK 1.8
	 */
	private static String[] readExcelHead(Sheet sheet) {
		String[] headNameArr = null;
		Row headRow = sheet.getRow(0);
		if (headRow != null) {
			headNameArr = new String[headRow.getLastCellNum()];
			for (int i = 0; i < headRow.getLastCellNum(); i++) {// getLastCellNum，是获取最后一个不为空的列是第几个
				if (null != headRow.getCell(i)) { // getCell 获取单元格数据
					headNameArr[i] = String.valueOf(headRow.getCell(i));
				} else {
					headNameArr[i] = "";
				}
			}
		}
		return headNameArr;
	}

	/**
	 * 
	 * @Description:读取sheet页表体内容
	 *
	 * @author wenzhang
	 * @date:2018年2月13日 下午2:29:01
	 * @Title:readExcelBody
	 * @param sheet
	 * @return
	 * @since JDK 1.8
	 */
	private static List<Object[]> readExcelBody(Sheet sheet) {
		List<Object[]> bodyData = new ArrayList<Object[]>();
		// 默认第一行是表头，从第二行开始读
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {// getLastRowNum，获取最后一行的行标
			Row row = sheet.getRow(i);
			if (null != row) {
				Object[] arr = new Object[row.getLastCellNum()];
				for (int j = 0; j < row.getLastCellNum(); j++) {// getLastCellNum，是获取最后一个不为空的列是第几个
					if (row.getCell(j) != null) { // getCell 获取单元格数据
						arr[j] = String.valueOf(row.getCell(j));
					} else {
						arr[j] = "";
					}
				}
				bodyData.add(arr);
			}
		}
		return bodyData;
	}

	/**
	 * 
	 * @Description:向文件地址，写入内容.
	 *
	 * @author wenzhang
	 * @date:2018年3月29日 下午5:02:28
	 * @Title:write
	 * @param path
	 * @param in
	 * @since JDK 1.8
	 */
	public static void write(String path, InputStream in) {
		FileOutputStream downloadFile = null;
		try {
			int index;
			byte[] bytes = new byte[1024];
			downloadFile = new FileOutputStream(path);
			while ((index = in.read(bytes)) != -1) {
				downloadFile.write(bytes, 0, index);
				downloadFile.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != downloadFile) {
					downloadFile.close();
				}
				if (null != in) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @Description:获取txt文件编码格式.
	 *
	 * @author wenzhang
	 * @date:2018年3月30日 上午10:09:39
	 * @Title:getEncode
	 * @param is
	 * @return
	 * @throws IOException
	 * @since JDK 1.8
	 */
	public static String getEncode(InputStream is) throws IOException {
		int p = (is.read() << 8) + is.read();
		is.close();
		String code = null;

		switch (p) {
		case 0xefbb:
			code = "UTF-8";
			break;
		case 0xfffe:
			code = "Unicode";
			break;
		case 0xfeff:
			code = "UTF-16BE";
			break;
		default:
			code = "GBK";
			break;
		}

		return code;
	}

}