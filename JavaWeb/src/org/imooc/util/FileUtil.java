package org.imooc.util;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

public class FileUtil {
    // 上传文件的保存路径
    public static final String SAVE_PATH = "e:/upload/";

    /**
     * 保存上传的文件
     * @param fileItem
     * @param path
     * @return
     * @throws Exception
     */
    public static String save(FileItem fileItem, String path)throws Exception{
        String fileName = System.currentTimeMillis() + "_" + fileItem.getName();
        fileItem.write(new File(path + fileName));
        return fileName;
    }
}
