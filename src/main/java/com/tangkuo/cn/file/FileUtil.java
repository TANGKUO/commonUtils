package com.tangkuo.cn.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangkuo.cn.utils.DateUtil;


/** 
* @ClassName: FileUtil 
* @Description: 文件工具
* @author fubin
* @date 2015年10月13日 下午11:01:55 
*  
*/ 
public class FileUtil {
	
	private static final Logger log = LoggerFactory.getLogger(FileUtil.class);
	
	/** 
	* @Title: rename 
	* @Description: 文件重命名
	* @param @param srcFile
	* @param @param descFile
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/ 
	public static void rename(File srcFile, File destFile) throws Exception {
		if(destFile.exists()) {
			// 删除存在文件
			destFile.delete();
		}
		// 文件重命名
		FileUtils.copyFile(srcFile, destFile);
		// 删除源文件
		//srcFile.delete();
	}
	
	/** 
	* @Title: updateFilePermission 
	* @Description: 更新文件的权限
	* @param file
	* @param fpl
	* @return boolean   返回类型 
	* @throws null
	* @author Bill Huang
	*/
	public static boolean updateFilePermission(File file,FilePermissionLevel fpl) {
		if(file == null || fpl == null){
			if(log.isWarnEnabled())
				log.warn("file or File Permission level is null, so can not update file Permission.");
			return false;
		}
		String osName = System.getProperty("os.name");
		if(StringUtils.isEmpty(osName)){
			if(log.isWarnEnabled()) log.warn("the os name is empty string.");
			return false;
		}
		if(osName.toUpperCase().indexOf("WINDOWS") != -1){
			if(fpl == FilePermissionLevel.READ){
				return file.setReadable(true);
			}else if(fpl == FilePermissionLevel.WRITE){
				return file.setWritable(true);
			}else if(fpl == FilePermissionLevel.EXECUTE){
				return  file.setExecutable(true);
			}else{
				return file.setReadable(true) && file.setWritable(true) && file.setExecutable(true);
			}
		}else{
	        StringBuilder command = new StringBuilder("chmod ");
	        command.append(fpl.getValue()+" ");
	        command.append(file.getAbsolutePath());
	        Runtime runtime = Runtime.getRuntime();
	        try {
	            runtime.exec(command.toString());
	            return true;
	        } catch (IOException e) {
	        	if(log.isErrorEnabled()) log.error("the execute error is : "+e.getMessage(),e);
	            return false;
	        }
		}
	}
	
	public static List<String> readLineToList(File file){
		if(file == null || !file.exists()) {
			if(log.isWarnEnabled())	log.warn("the file is null.");
			return Collections.emptyList();
		}
		List<String> lines = new LinkedList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				lines.add(tempString);
			}
		} catch (IOException e) {
			if(log.isErrorEnabled()) log.error("the execute error is : "+e.getMessage(),e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
		return lines;
	}
	
	/** 
	* @Title: readLineToList 
	* @Description: 一行一行的模式读取文件
	* @param fileName
	* @return List<String>   返回类型 
	* @throws null
	* @author Bill Huang
	*/
	public static List<String> readLineToList(String fileName) {
		if(StringUtils.isEmpty(fileName)){
			if(log.isWarnEnabled())
				log.warn("the file path string is empty string.");
			return Collections.emptyList();
		}
		File file = new File(fileName);
		if(!file.exists()){
			if(log.isWarnEnabled()){				
				log.warn("the file is null.");
			}
			return Collections.emptyList();
		}
		return readLineToList(file);
	}
	
	/** 
	* @Title: readLineToAarray 
	* @Description: 一行一行的模式读取文件
	* @param fileName
	* @return String[]   返回类型 
	* @throws null
	* @author Bill Huang
	*/
	public static String[] readLineToAarray(String fileName) {
		List<String> lines = readLineToList(fileName);
		if (lines == null || lines.size() == 0){
			return new String[]{};
		}else{
			String[] strs = new String[lines.size()];
			return lines.toArray(strs);
		}
	}
	
	/** 
	* @Title: readLineToAarray 
	* @Description: 一行一行的模式读取文件
	* @param file
	* @return String[]   返回类型 
	* @throws null
	* @author Bill Huang
	*/
	public static String[] readLineToAarray(File file) {
		List<String> lines = readLineToList(file);
		if (lines == null || lines.size() == 0){
			return new String[]{};
		}else{
			String[] strs = new String[lines.size()];
			return lines.toArray(strs);
		}
	}
	
	public static String[] readExcelLineToArray(File file) {
		//int startRowNum = 6,rowLen = 35;
		return readExcelLineToArray(file, 6, 35);
	}
	
	public static String[] readExcelLineToArray(File file,int startRowIndex,int rowlen) {
		List<String> lines = readExcelLineToList(file,startRowIndex,rowlen);
		if(lines == null || lines.size() == 0) {
			return new String[]{};
		} else {
			String[] strs = new String[lines.size()];
			return lines.toArray(strs);
		}
	}
	
	public static List<String> readExcelLineToList(File file) {
		return readExcelLineToList(file, 6, 35);
	}
	
	public static void appendValue(StringBuilder container,String value){
		if (StringUtils.isEmpty(value)) {
			container.append("NULL");
		} else {
			container.append(value);
		}
		container.append("|");
	}

	public static String getValue(HSSFCell hssfCell) {
		if(hssfCell == null) {
			return StringUtils.EMPTY;
		}
		if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
				Date date = hssfCell.getDateCellValue();
				if (date != null) {
					return DateUtil.format(date, DateUtil.YYYY_MM_DD_HH_MM_SS); 
				} else {
					return StringUtils.EMPTY;
				}
			} else {
				return new DecimalFormat("##.##").format(hssfCell.getNumericCellValue());
			}
		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			return hssfCell.getStringCellValue();
		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
			if (StringUtils.isNotEmpty(hssfCell.getStringCellValue())) {
				return hssfCell.getStringCellValue();
			} else {
				return String.valueOf(hssfCell.getNumericCellValue());
			}
		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			return hssfCell.getStringCellValue();
		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_ERROR) {
			return hssfCell.getStringCellValue();
		} else {
			return hssfCell.getStringCellValue();
		}
	}
	
	public static List<String> readExcelLineToList(File file,int startRowNum,int rowLen) {
		if(file == null || !file.exists()) {
			if(log.isWarnEnabled())	log.warn("the file is null.");
			return Collections.emptyList();
		}
		//int startRowNum = 6,rowLen = 35;
		if(startRowNum <= 0) {
			startRowNum = 0;
		}
		if(rowLen <= 0){
			rowLen = 0;
		}
		if(rowLen == 0){
			return Collections.emptyList();
		}
		List<String> list = new LinkedList<String>();
		try {
			InputStream is = new FileInputStream(file);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				for (int rowNum = startRowNum; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						StringBuilder container = new StringBuilder();
						for(int i = 0; i < rowLen; i ++) {
							String str = getValue(hssfRow.getCell(i));
							appendValue(container, str);
						}
						if (container.lastIndexOf("|") != -1) {
							container.deleteCharAt(container.length()-1);
							container.append("^^\n");
						}
						list.add(container.toString());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			if(log.isErrorEnabled()) log.error(e.getMessage(), e);
		}
		return list;
	}
	
	public static void copyFileWithCharset(String destFilePath,String charset) throws Exception {
		if(StringUtils.isEmpty(destFilePath)){
			return ;
		}
		destFilePath = formatPathUrl(destFilePath);
		if(StringUtils.isEmpty(charset)){
			charset = "GBK";
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(destFilePath)),charset));
		List<String> list = new LinkedList<String>();
		String tempString = StringUtils.EMPTY;
		while ((tempString = br.readLine()) != null) {
			list.add(tempString);
		}
		br.close();
		
		BufferedWriter output = new BufferedWriter(new FileWriter(new File(destFilePath)));
        for (String s : list) {
            output.write(s, 0, s.length());
            output.newLine();
        }
        output.close();
	}
	
	public static String formatPathUrl (String source) {
		if(StringUtils.isEmpty(source)) {
			return StringUtils.EMPTY;
		}
		if(log.isDebugEnabled()){
			log.debug("The Before Format Path Url is : "+source);
		}
		String[] _str = new String[]{"\\/","//","\\\\"};
		for(String str : _str){
			Pattern p = Pattern.compile(str);
			Matcher m = p.matcher(source);
			while (m.find()) {
				source= m.replaceAll("/");
			}
		}
		boolean isContinue = true;
		for(String str : _str){
			if(source.indexOf(str) != -1){				
				isContinue = isContinue && false;
				break ;
			};
		}
		if(!isContinue) {
			source = formatPathUrl(source);
		}
		if(source.indexOf("://") == -1 && source.indexOf(":/") != -1){
			Pattern p = Pattern.compile(":/");
			Matcher m = p.matcher(source);
			while (m.find()) {
				source= m.replaceAll("://");
			}
		}
		if(log.isDebugEnabled()){
			log.debug("The After Format Path Url is : "+source);
		}
		return source;
	}
	
	public static void copyFileWithByteStream(String sourceFileUrl,String destFilePath) throws Exception{
		if (StringUtils.isEmpty(sourceFileUrl)
				|| StringUtils.isEmpty(destFilePath)) {
			log.warn("the source file or dest file is empty.");
			return ;
		}
		sourceFileUrl = formatPathUrl(sourceFileUrl);
		destFilePath = formatPathUrl(destFilePath);
		if(log.isDebugEnabled()){
			log.debug(sourceFileUrl);
		}
		
		File file = new File(destFilePath);
		FileUtil.updateFilePermission(file.getParentFile(), FilePermissionLevel.ALL);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		if(file.exists()){
			file.delete();
		}
		FileUtil.updateFilePermission(file, FilePermissionLevel.ALL);
		file.createNewFile();
		
		URL url;
		URLConnection uc;
		InputStream is = null;
		OutputStream os = null;
		try {
			url = new URL(sourceFileUrl);
			uc = url.openConnection();
			is = uc.getInputStream();
			// 1K的数据缓冲 
			byte[] bs = new byte[1024]; 
			// 读取到的数据长度 
			int len;
			// 输出的文件流 
			os = new FileOutputStream(destFilePath);
			// 开始读取 
			while ((len = is.read(bs)) != -1) { 
				os.write(bs, 0, len); 
			}
			os.flush();
			// 完毕，关闭所有链接 
			if(os != null) os.close(); 
			if(is != null) is.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
	
	public static enum FilePermissionLevel {
		
		READ("754"),WRITE("752"),EXECUTE("751"),ALL("777");
		
		private String value;
		
		private FilePermissionLevel(String value){
			this.value = value;
		}
		
		public void setValue(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
		
		public static FilePermissionLevel enumOf(String value){
			for (FilePermissionLevel fpl : values()) {
				if (StringUtils.equalsIgnoreCase(fpl.getValue(),value)) {
					return fpl;
				}
			}
			return null;
		}
		
	}
	
}
