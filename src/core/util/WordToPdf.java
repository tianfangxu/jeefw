package core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.log4j.Logger;


public class WordToPdf {
    private static final Logger logger = Logger.getLogger(WordToPdf.class);

    private static final int wdFormatPDF = 17;// PDF 格式
    public static void wordToPDF2(String inputFilePath,String outputFilePath){

        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
            logger.error("2222"+app.toString());
            Dispatch docs = app.getProperty("Documents").toDispatch();
            logger.error("3333"+docs.toString());
            doc = Dispatch.call(docs,  "Open" , inputFilePath).toDispatch();
            logger.error("4444"+doc.toString());
            File tofile = new File(outputFilePath);
            logger.error("55555");
            if (tofile.exists()) {
                tofile.delete();
            }
            logger.error("66666");
            Dispatch.call(doc,"SaveAs", outputFilePath, wdFormatPDF);
            logger.error("7777");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        } finally {
            if(doc!=null){
                Dispatch.call(doc,"Close",false);
                logger.error("99999");
            }
            if (app != null)
                app.invoke("Quit", new Variant[] {});
            logger.error("1010110");

        }
        //结束后关闭进程
        ComThread.Release();
    }

    public static boolean getLicense() {
        boolean result = false;
        try {
            String path = ConfigUtil.class.getClassLoader().getResource("").toURI().getPath();
            path = path.substring(1, path.length()) + "license.xml";
            File file = new File(path);
            InputStream is = new FileInputStream(file); // license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void wordToPDF(String inputFilePath, String outputFilePath) {
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            return;
        }
        try {
            long old = System.currentTimeMillis();
            File file = new File(outputFilePath); // 新建一个空白pdf文档
            File tofile = new File(outputFilePath);
            if (tofile.exists()) {
                tofile.delete();
            }
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inputFilePath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





