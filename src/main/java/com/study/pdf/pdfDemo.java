package com.study.pdf;



import java.io.FileOutputStream;
import java.util.Map;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.study.topic.TopicListAction;


public class pdfDemo {
    private static Font headfont;// 设置字体大小
    private static Font keyfont;// 设置字体大小
    private static Font textfont;// 设置字体大小

    static {
        BaseFont bfChinese;
        try {
            bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
           // bfChinese =  BaseFont.createFont("C:/WINDOWS/Fonts/simhei.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            headfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
            keyfont = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
            textfont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeSimplePdf() throws Exception {
        //1.新建document对象
        //第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        //2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        //创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\ITextTest.pdf"));
        //3.打开文档
        document.open();
        //4.向文档中添加内容
        //通过 com.lowagie.text.Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
        document.add(new Paragraph("First page of the document."));
        document.add(new Paragraph("Some more text on the first page with different color and font type.",
                FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new BaseColor(255, 150, 200))));
        //5.关闭文档
        document.close();
    }

    public void writeCharpter() throws Exception {
        //新建document对象  第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        //建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:\\ITextTest1.pdf"));
        //打开文件
        document.open();
        //标题
        document.add(new Paragraph("\n11111111111111111111111111111111111111ahnzi汉字"));

        document.addTitle("Hello mingri example");
        //作者
        document.addAuthor("wolf");
        //主题
        document.addSubject("This example explains how to add metadata.");
        document.addKeywords("iText, Hello mingri");
        document.addCreator("My program using iText");
        // document.newPage();
        //向文档中添加内容
        document.add(new Paragraph("\n22222222222222222222222222222222222222222222222222222222222222222"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("First page of the document.", keyfont));
        document.add(new Paragraph("First page of the document."));
        document.add(new Paragraph("First page of the document."));
        document.add(new Paragraph("First page of the document."));
        document.add(new Paragraph("Some more text on the first page with different color and font type.",
                FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new BaseColor(0, 0, 0))));
        Paragraph title1 = new Paragraph("Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new BaseColor(0, 0, 255)));
        //新建章节
        Chapter chapter1 = new Chapter(title1, 1);
        chapter1.setNumberDepth(0);
        Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new BaseColor(255, 0, 0)));
        Section section1 = chapter1.addSection(title11);
        Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");
        section1.add(someSectionText);
        //someSectionText = new Paragraph("Following is a 3 X 2 table.");
        someSectionText = new Paragraph("Following is a 3 X 2 table. 测试下汉字  和空格   是否会有乱码wwwwww");
        section1.add(someSectionText);
        document.add(chapter1);
        //关闭文档
        document.close();
    }

    public void writePdf(String title, String cont, String createTime, String authorName ,Map listMap) throws Exception {
        //1.新建document对象
        //第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        //2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        //创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\ITextTest.pdf"));
        //3.打开文档
        document.open();
        //4.向文档中添加内容
        //通过 com.lowagie.text.Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
        Paragraph pt = new Paragraph("zhong:-" + title, keyfont);//设置字体样式
        pt.setAlignment(1);//设置文字居中 0靠左   1，居中     2，靠右
        document.add(pt);
        document.add(new Paragraph("\n"));
        pt = new Paragraph(createTime + "\t\t\t\t\t\t" + authorName, keyfont);
        pt.setAlignment(2);
        document.add(pt);

        Object topiclist = listMap.get("topiclist");
        document.add(new Paragraph("\n"));
        document.add(new Paragraph(createTime + "\t\t\t\t\t\t" + authorName, keyfont));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Some more text on the 胜多负少的身份的分公司的风格发的电饭锅的分公司的分公司的的分公司电饭锅是的分公司的风格的分公司的分公司的复合弓好几顿饭发的寡鹄单凫过好地方风格和的发干活的风格和发干活的风格和地方过电饭锅好地方干活的风格和电饭锅好地方干活负少的身份的分公司的风格发的电饭锅的分公司的分公司的的分公司电饭锅是的分公司的风格的分公司的分公司的复合弓好几顿饭发的寡鹄单凫过好地方风格和的发干活的风格和发干活的风格和地方过电饭锅好地方干活的风格和电饭锅好地方干活负少的身份的分公司的风格发的电饭锅的分公司的分公司的的分公司电饭锅是的分公司的风格的分公司的分公司的复合弓好几顿饭发的寡鹄单凫过好地方风格和的发干活的风格和发干活的风格和地方过电饭锅好地方干活的风格和电饭锅好地方干活的风格和符合斯蒂夫 first page with different color andsdfsadfffffffffffffffffffffffffff font type.",
                keyfont));

        document.add(new Paragraph("\n11111111111111111111111111111111111111ahnzi汉字",textfont));
        document.add(new Paragraph("\n22222222222222222222222222222222222222222222222222222222222222222"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("First page of the document.", keyfont));
        document.add(new Paragraph("First page of the document."));
        document.add(new Paragraph("First page of the document."));
        document.add(new Paragraph("First page of the document."));
        document.add(new Paragraph("Some more text on the first page with different color and font type.",
                FontFactory.getFont(FontFactory.defaultEncoding, 10, Font.BOLD, new BaseColor(0, 0, 0))));
        Paragraph title1 = new Paragraph("Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new BaseColor(0, 0, 255)));
        //新建章节
        Chapter chapter1 = new Chapter(title1, 1);
        chapter1.setNumberDepth(0);
        Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new BaseColor(255, 0, 0)));
        Section section1 = chapter1.addSection(title11);
        Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");
        section1.add(someSectionText);
        //someSectionText = new Paragraph("Following is a 3 X 2 table.");
        someSectionText = new Paragraph("Following is a 3 X 2 table. 测试下汉字  和空格   是否会有乱码wwwwww",textfont);
        section1.add(someSectionText);
        document.add(chapter1);
        //5.关闭文档
        document.close();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("begin");
        Map<String, Object> execute = TopicListAction.execute();

        pdfDemo ppt = new pdfDemo();
        ppt.writePdf("fgh--标题--dfg23fgh", "sdfsdfasdfasdfsdfasdf", "时间", "作者" ,execute);
        //ppt.writeCharpter();
        System.out.println("end");
    }

}