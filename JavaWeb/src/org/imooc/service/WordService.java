package org.imooc.service;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.imooc.dto.ImportWorResultDto;
import org.imooc.dto.ImportWordParamDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WordService {
    public ImportWorResultDto imp(ImportWordParamDto dto){
        ImportWorResultDto result = new ImportWorResultDto();
        result.setContent(dto.getTitle());
        // 解析03版Word
        HWPFDocument doc = null;
        try {
            doc = new HWPFDocument(dto.getWord().getInputStream());// 执行文件流(FileInputStream)，不保存文件(File)
            result.setContent(doc.getDocumentText().replace("\r", "<br/>"));// 这里只是单纯的解析文件中的内容，不解析文件中的格式
        } catch (OfficeXmlFileException e){
            System.out.println("这可能是一个07版的Word");
        }catch (Exception e) {
            result.setMsg("这可能不是一个Word");
            System.out.println("这可能不是一个Word");
            return result;
        } finally {
            if (doc != null){
                try {
                    doc.close();
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        XWPFDocument docx = null;
        try {
            docx = new XWPFDocument(dto.getWord().getInputStream());

            List<XWPFParagraph> paragraphList = docx.getParagraphs();
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < paragraphList.size(); i ++){
                if (i != 0){
                    content.append("<br/>");
                }
                content.append(paragraphList.get(i).getText());
            }
            result.setContent(content.toString());
        } catch (Exception e) {
            result.setMsg("这可能不是一个Word");
            System.out.println("这可能不是一个Word");
            return result;
        } finally {
            if (docx != null){
                try {
                    docx.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 导出03版Word
     */
    public HWPFDocument export03(Map<String,String> replaceContent) {
        HWPFDocument doc = null;
        try {
            doc = new HWPFDocument(new FileInputStream("e:\\upload\\templete\\template_03.doc"));
            Range range = doc.getRange();
            for(Map.Entry<String,String> entry : replaceContent.entrySet()) {
                range.replaceText(entry.getKey(),entry.getValue());
            }
        } catch (Exception e) {
            return null;
        }
        return doc;
    }

    /**
     * 导出07版Word
     */
    public XWPFDocument export07(Map<String,String> replaceContent) {
        XWPFDocument docx = null;
        try {
            docx = new XWPFDocument(new FileInputStream("e:\\upload\\template_07.docx"));
            List<XWPFParagraph> paragraphList = docx.getParagraphs();
            for(XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                for(XWPFRun run : runs) {
                    String str = run.getText(run.getTextPosition());
                    for(Map.Entry<String,String> entry : replaceContent.entrySet()) {
                        str = str.replace(entry.getKey(),entry.getValue());
                    }
                    run.setText(str,0);
                }
            }
        } catch (IOException e) {
            return null;
        }
        return docx;
    }
}
