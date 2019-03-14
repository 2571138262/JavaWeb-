package org.imooc.dto;

import org.apache.commons.fileupload.FileItem;

import java.util.HashMap;
import java.util.Map;

public class ParamDto {
    private Map<String, String> paramMap;// 对应表单数据的Map
    private Map<String, FileItem> fileMap;// 对应文件的Map

    public ParamDto() {
        paramMap = new HashMap<>();
        fileMap = new HashMap<>();
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, FileItem> getFileMap() {
        return fileMap;
    }

    public void setFileMap(Map<String, FileItem> fileMap) {
        this.fileMap = fileMap;
    }
}
