package org.imooc.servlet;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imooc.dto.*;
import org.imooc.service.WordService;
import org.imooc.util.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImportWordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)){// 判断是否是文件上传类型
            ParamDto dto = RequestUtil.parseParam(request);
            ImportWordParamDto paramDto = new ImportWordParamDto();
            paramDto.setTitle(dto.getParamMap().get("title"));
            paramDto.setWord(dto.getFileMap().get("word"));


            WordService service = new WordService();
            ImportWorResultDto resultDto = service.imp(paramDto);
            request.setAttribute("result", resultDto);

        }else {

        }
        request.getRequestDispatcher("/WEB-INF/jsp/importWordResult.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
