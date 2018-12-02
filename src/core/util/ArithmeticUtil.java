package core.util;

import java.math.BigDecimal;

public class ArithmeticUtil {
	
	public static String add(String... arrs){
		BigDecimal init = new BigDecimal(0);
		for (String val : arrs) {
			init = init.add(new BigDecimal(val));
		}
		return init.toString();
	}

}
