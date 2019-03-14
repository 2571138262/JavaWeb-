package org.imooc.servlet;


import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imooc.dto.ImportExcelParamDto;
import org.imooc.dto.ImportExcelResultDto;
import org.imooc.dto.ImportWordParamDto;
import org.imooc.dto.ParamDto;
import org.imooc.service.ExcelService;
import org.imooc.util.RequestUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImportExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ServletFileUpload.isMultipartContent(request)){// 判断是否是文件上传类型
            ParamDto dto = RequestUtil.parseParam(request);
            ImportExcelParamDto paramDto = new ImportExcelParamDto();
            paramDto.setTitle(dto.getParamMap().get("title"));
            paramDto.setExcel(dto.getFileMap().get("excel"));


            ExcelService service = new ExcelService();
            ImportExcelResultDto resultDto = service.imp(paramDto);
            request.setAttribute("result", resultDto);

        }else {

        }

        request.getRequestDispatcher("/WEB-INF/jsp/importExcelResult.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
