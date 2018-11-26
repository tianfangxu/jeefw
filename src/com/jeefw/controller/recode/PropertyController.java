package com.jeefw.controller.recode;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.recode.PropertyEntity;
import com.jeefw.model.recode.param.PropertyModel;
import com.jeefw.service.recode.PropertyService;

import core.support.JqGridPageView;
import core.util.ParamUtils;

/***
 *楼宇内物业信息
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/recode/property")
public class PropertyController extends JavaEEFrameworkBaseController<PropertyEntity> implements Constant{

	@Resource
	PropertyService propertyService;
	
	/**
	 * 楼宇内物业信息
	 */
	@SuppressWarnings({"rawtypes" })
	@RequestMapping(value = "/getPropertyByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public void getPropertyByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			PropertyModel model = new ParamUtils<PropertyModel>().getparams(request, PropertyModel.class);
			JqGridPageView<PropertyModel> propertyModelJqGridPageView = null;
			propertyModelJqGridPageView = propertyService.getPropertyByCondition(model);
			writeJSON(response, propertyModelJqGridPageView);
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	} 
	
	/***
	 * 保存/修改楼宇内物业信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/saveOrupdProperty", method = { RequestMethod.POST })
	public void saveOrupdProperty(HttpServletRequest request, HttpServletResponse response,@RequestBody PropertyModel model) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			PropertyModel mo = new ParamUtils<PropertyModel>().getparams(request, PropertyModel.class);
			if(mo != null && mo.getLoginuser() != null){
				model.setLoginuser(mo.getLoginuser());
			}
			if(StringUtils.isEmpty(model.getId())){
				propertyService.saveProperty(model);
				map.put("message", "添加成功");
				writeJSON(response,map);
			}else{
				//修改
				String msg = propertyService.updateProperty(model);
				map.put("message", msg);
				writeJSON(response,map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	}
	
	/**
	 * 删除楼宇内物业信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delProperty", method = { RequestMethod.POST })
	public void delProperty(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			PropertyModel model = new ParamUtils<PropertyModel>().getparams(request, PropertyModel.class);
			String oper = request.getParameter("oper");
			if(oper != null && oper.equals("del")){
				//删除
				String string = propertyService.deleteProperty(model);
				map.put("message", string);
				writeJSON(response,map);
			}
		} catch (Exception e) {
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	}
}
