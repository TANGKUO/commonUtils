package com.tangkuo.cn.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tangkuo.cn.exception.TtyException;

/**
 * 文件写入器
 */
@Service("fileWriter")
public class FileWriter {

    private Logger log = LoggerFactory.getLogger(FileWriter.class);
    
    @Async
    public void writeData(byte[] data, String storePath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new ByteArrayInputStream(data);
            out = new FileOutputStream(new File(storePath));
            int b = 0;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } catch (IOException e) {
            log.error("文件写入异常", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }
    
    @Async
    public void writeFile(FileItem item, String path, String fileName) {
        try{
            File uploadedFile = new File(path, fileName);
            item.write(uploadedFile);
        }catch(Exception e){
            throw new TtyException("file-upload-err", "上传文件异常。");
        }
    }

}
