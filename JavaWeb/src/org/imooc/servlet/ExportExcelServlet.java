package org.imooc.servlet;

import org.apache.poi.ss.usermodel.Workbook;
import org.imooc.service.ExcelService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExportExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExcelService service = new ExcelService();
        Workbook workbook = service.export(true);
        response.setHeader("Content-Disposition", "attachment;filename=export.xlsx");
        ServletOutputStream outputStream = response.getOutputStream();

        FileOutputStream fileOutputStream = new FileOutputStream("e:/upload/export.xlsx");

//        workbook.write(outputStream);//写入到流中

        workbook.write(fileOutputStream);// 写入到文件

        FileInputStream fileInputStream = new FileInputStream("e://upload/export.xlsx");
        byte[] bytes = new byte[fileInputStream.available()];// fileInputStream.available()文件可读取的大小
        fileInputStream.read(bytes);
        outputStream.write(bytes);

        outputStream.flush();
        outputStream.close();
        workbook.close();


//        request.getRequestDispatcher("/WEB-INF/jsp/exportExcel.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
