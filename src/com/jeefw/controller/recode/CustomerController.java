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
import com.jeefw.model.recode.CustomerEntity;
import com.jeefw.model.recode.param.CustomerModel;
import com.jeefw.service.recode.CustomerService;

import core.support.JqGridPageView;
import core.util.ParamUtils;

/***
 *客户信息
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/recode/customer")
public class CustomerController extends JavaEEFrameworkBaseController<CustomerEntity> implements Constant{
	@Resource
	CustomerService customerService;
	
	/**
	 * 客户信息
	 */
	@SuppressWarnings({"rawtypes" })
	@RequestMapping(value = "/geCustomerByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public void geCustomerByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			CustomerModel model = new ParamUtils<CustomerModel>().getparams(request, CustomerModel.class);
			JqGridPageView<CustomerEntity> propertyModelJqGridPageView = null;
			propertyModelJqGridPageView = customerService.getCustomerByCondition(model);
			writeJSON(response, propertyModelJqGridPageView);
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	} 
	
	/***
	 * 保存/修改客户信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/saveOrupdCustomer", method = { RequestMethod.POST })
	public void saveOrupdCustomer(HttpServletRequest request, HttpServletResponse response,@RequestBody CustomerModel model) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			CustomerModel mo = new ParamUtils<CustomerModel>().getparams(request, CustomerModel.class);
			if(mo != null && mo.getLoginuser() != null){
				model.setLoginuser(mo.getLoginuser());
			}
			if(StringUtils.isEmpty(model.getCode())){
				customerService.saveProperty(model);
				map.put("message", "添加成功");
				writeJSON(response,map);
			}else{
				//修改
				String msg = customerService.updateProperty(model);
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
	 * 删除客户信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delCustomer", method = { RequestMethod.POST })
	public void delCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			CustomerModel model = new ParamUtils<CustomerModel>().getparams(request, CustomerModel.class);
			String oper = request.getParameter("oper");
			if(oper != null && oper.equals("del")){
				//删除
				String string = customerService.deleteProperty(model);
				map.put("message", string);
				writeJSON(response,map);
			}
		} catch (Exception e) {
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	}
}
