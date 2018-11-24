package core.util;

import java.io.File;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;



public class WordToPdf {
    private static final int wdFormatPDF = 17;// PDF 格式
    public static void wordToPDF(String inputFilePath,String outputFilePath){

        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
            Dispatch docs = app.getProperty("Documents").toDispatch();
            doc = Dispatch.call(docs,  "Open" , inputFilePath).toDispatch();
            File tofile = new File(outputFilePath);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(doc,"SaveAs", outputFilePath, wdFormatPDF);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Dispatch.call(doc,"Close",false);
            if (app != null)
                app.invoke("Quit", new Variant[] {});
        }
        //结束后关闭进程
        ComThread.Release();
    }
}





