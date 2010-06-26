package com.done.fckeditor;

import java.io.IOException;
import java.io.InputStream;

import net.fckeditor.connector.exception.InvalidCurrentFolderException;
import net.fckeditor.connector.exception.WriteException;
import net.fckeditor.connector.impl.LocalConnector;
import net.fckeditor.handlers.ResourceType;
import bingosoft.jcf.sql.ConfigCode;

/**
 * 文件上传的操作在ContextConnector类中，这里只是对其进行重命名操作，其实完全可以重新自己写上传的代码
 */
public class MyContextConnector extends LocalConnector {
    private Integer MAX_FILE_SIZE = Integer.parseInt(ConfigCode.getInstance().getCode("config.MaxFileSize"));

    //此方法中可以对文件重命名
    @Override
    public String fileUpload(ResourceType type, String currentFolder, String fileName, InputStream inputStream)
            throws InvalidCurrentFolderException, WriteException {
        //fileName = fileName;//重命名操作在这里进行
        try {
            int size = inputStream.available();
            if ((size < 0) || (size > MAX_FILE_SIZE)) {
                throw new RuntimeException(size + "File Size exceeded MAX_FILE_SIZE");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String result = super.fileUpload(type, currentFolder, fileName, inputStream);
        return result;
    }

}
