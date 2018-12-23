package core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcleReportUtil {  
	
	public void gettemp(ArrayList<String> list,String  path1,String  path2) {
		InputStream inputStream = null;
    	File file = new File(path1);
    	File report = new File(path2);
        
        Workbook workbook = null;
        int count = 0;
        try {
            inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            
            sheet.setForceFormulaRecalculation(true);
            
            for(int i = 3; i <= 12 ;i++){
            	for(int j = 4;j<= 8;j++){
            		if(j>=6 && j<=8){
            			try {
            				sheet.getRow(i).getCell(j).setCellValue( ArithmeticUtil.multiply(list.get(count),"100")+"%");
						} catch (Exception e) {
							
						}
            			
            		}else{
            			sheet.getRow(i).getCell(j).setCellValue(list.get(count));
            		}
            		
            		count++;
            	}
            }
            
            sheet.getRow(13).getCell(4).setCellValue(list.get(count));
            count++;
            for(int i = 14; i <= 36 ;i++){
            	for(int j = 4;j<= 8;j++){
            		if(j>=6 && j<=8){
            			try {
            				sheet.getRow(i).getCell(j).setCellValue( ArithmeticUtil.multiply(list.get(count),"100")+"%");
						} catch (Exception e) {
							
						}
            		}else{
            			sheet.getRow(i).getCell(j).setCellValue(list.get(count));
            		}
            		count++;
            	}
            }
          
 
            //将修改好的数据保存
            OutputStream out = new FileOutputStream(report);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		
	}
	
}
