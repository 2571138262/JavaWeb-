package org.imooc.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imooc.dto.ParamDto;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class RequestUtil {
    /**
     * 从request流中解析参数与上传的文件
     * @param request
     */
    public static ParamDto parseParam(HttpServletRequest request){
        ParamDto result = new ParamDto();
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());// 创建文件上传对象
        upload.setHeaderEncoding("utf-8");
        try {
            List<FileItem> fileItemList = upload.parseRequest(request); // 获取文件上传的所有内容
            for (FileItem fileItem : fileItemList){
                if (fileItem.isFormField()){// 是普通的表单字段
                    result.getParamMap().put(fileItem.getFieldName(), fileItem.getString("utf-8"));
//                    System.out.println(fileItem.getFieldName() + "," + fileItem.getString("utf-8"));
                } else { // 是文件
                    result.getFileMap().put(fileItem.getFieldName(), fileItem);
//                    System.out.println(fileItem.getFieldName());
//                    fileItem.write(new File("e:/upload/" + fileItem.getName()));
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
