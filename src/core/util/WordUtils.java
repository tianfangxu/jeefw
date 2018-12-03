package core.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

public class WordUtils {
    //配置信息,代码本身写的还是很可读的,就不过多注解了
    private static Configuration configuration = null;
    //这里注意的是利用WordUtils的类加载器动态获得模板文件的位置
    private static  String templateFolder =  WordUtils.class.getClassLoader().getResource("../../").getPath();
    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        try {
            templateFolder =  templateFolder.substring(1,templateFolder.length());
            configuration.setDirectoryForTemplateLoading(new File(templateFolder+"/static/json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private WordUtils() {
        throw new AssertionError();
    }

    public static String createDoc(Map<?, ?> dataMap, String compactName,String templateName) throws IOException {
        String name =  templateFolder+"static/word/"+compactName+".docx";
        File f = new File(name);
        if(f.exists()){
            try{
                f.delete();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        Template t = configuration.getTemplate(templateName);
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return name;
    }

}