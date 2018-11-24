package core.util;

import org.apache.commons.lang.StringUtils;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Properties;


public class ConfigUtil {
    public static String getConfig(String param) throws FileNotFoundException {
        Properties prop = new Properties();
        try {
            String path = ConfigUtil.class.getClassLoader().getResource("").toURI().getPath();
            path = path.substring(1, path.length()) + "globalSetting.properties";
            File f = new File(path);
            InputStream in = new FileInputStream(f);
            prop.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String serverStr = prop.getProperty(param);
        if (StringUtils.isEmpty(serverStr)) {
            return "";
        }
        return serverStr.trim();
    }
}


