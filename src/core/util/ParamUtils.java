package core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.jeefw.model.recode.param.BaseModel;
import com.jeefw.model.sys.SysUser;

public class ParamUtils<T> {

	public T getparams(HttpServletRequest request,Class<T> clazz) throws Exception{
		T obj = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		//封装第一层普通的参数
		for (Field field : fields) {
			field.setAccessible(true);
			String param = request.getParameter(field.getName());
			if(param != null){
				Method method = null;
				try {
					method = clazz.getDeclaredMethod("set"+toUpperCaseFirstOne(field.getName()), String.class);
					method.invoke(obj, param);
				} catch (Exception e) {
					continue;
				}
			}
		}
		//封装filters里面的参数
		BaseModel<T,T> baseModel = new BaseModel<T, T>();
		Class baseclazz = baseModel.getClass();
		T eqinit = clazz.newInstance();
		T likeinit = clazz.newInstance();
		Method[] methods = baseclazz.getMethods();
		Method eqparam = null;
		Method likeparam = null;
		for (Method method : methods) {
			if("setEqparam".equals(method.getName())){
				eqparam = method;
			}
			if("setLikeparam".equals(method.getName())){
				likeparam = method;
			}
		}
		if(eqparam == null || likeparam == null){
			return obj;
		}
		String filters = request.getParameter("filters");
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
			Class eqclazz = eqinit.getClass();
			Class likeclazz = likeinit.getClass();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject result = (JSONObject) jsonArray.get(i);
				try {
					if (result.getString("op").equals("eq")) {
						Method method = eqclazz.getDeclaredMethod("set"+toUpperCaseFirstOne(result.getString("field")), String.class);
						method.invoke(eqinit, result.getString("data"));
					}
					if (result.getString("op").equals("cn")) {
						Method method = likeclazz.getDeclaredMethod("set"+toUpperCaseFirstOne(result.getString("field")), String.class);
						method.invoke(likeinit, result.getString("data"));
					}
				} catch (Exception e) {
					continue;
				}
			}
			eqparam.invoke(obj,eqinit);
			likeparam.invoke(obj,likeinit);
		}
		//封装用户信息
		Method userMethod = baseclazz.getDeclaredMethod("setLoginuser", SysUser.class);
		userMethod.invoke(obj, request.getAttribute("currentUser"));
		//封装分页字段
		Method rowsMethod = baseclazz.getDeclaredMethod("setRows", String.class);
		rowsMethod.invoke(obj, request.getParameter("rows"));
		Method pageMethod = baseclazz.getDeclaredMethod("setPage", String.class);
		pageMethod.invoke(obj, request.getParameter("page"));
		return obj;
	}

	public static String toUpperCaseFirstOne(String s){
		if(Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

}
