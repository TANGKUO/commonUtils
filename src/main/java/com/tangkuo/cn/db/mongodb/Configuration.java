package com.tangkuo.cn.db.mongodb;

import java.io.FileNotFoundException;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.util.Properties;   
/**
 * 加载配置文件信息
 * @author 61650
 *
 */
public class Configuration {   
     private static Properties pro;   
     private static FileOutputStream outputFile;   
  
     static{
    	 pro = new Properties();   
         try {   
             pro.load(Configuration.class.getResourceAsStream("/config.properties"));   
         } catch (FileNotFoundException e) {   
             e.printStackTrace();   
         } catch (IOException e) {   
             e.printStackTrace();   
         }   
     }
     

     public static String getValue(String key){   
         if(pro.containsKey(key)){   
             String value = pro.getProperty(key);   
             return value;   
         }else{   
             return "";   
         }   
     }   
  

     
     public static void clear(){   
         pro.clear();   
     }   
  

     
     public static void setValue(String key, String value){   
         pro.setProperty(key, value);   
     }   
  

     
     public static void saveFile(String fileName, String description){   
         try {   
             outputFile = new FileOutputStream(fileName);   
             pro.store(outputFile, description);   
             outputFile.close();   
         } catch (FileNotFoundException e) {   
             e.printStackTrace();   
         } catch (IOException ioe){   
             ioe.printStackTrace();   
         }   
     }   
  
} 
