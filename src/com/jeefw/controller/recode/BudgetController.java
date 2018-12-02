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
import com.jeefw.model.recode.BudgetEntity;
import com.jeefw.model.recode.param.BudgetModel;
import com.jeefw.service.recode.BudgetService;

import core.support.JqGridPageView;
import core.util.ParamUtils;

/***
 * 年度预算信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/recode/budget")
public class BudgetController extends JavaEEFrameworkBaseController<BudgetEntity> implements Constant{

	@Resource
	BudgetService budgetService;
	
	/**
	 * 查询年度预算信息
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value = "/getBudgetByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public void getBudgetByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			BudgetModel model = new ParamUtils<BudgetModel>().getparams(request, BudgetModel.class);
			JqGridPageView<BudgetModel> entityJqGridPageView = null;
			entityJqGridPageView = budgetService.getBudgetByCondition(model);
			writeJSON(response, entityJqGridPageView);
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	} 
	
	/***
	 * 保存/修改年度预算信息
	 */
	@RequestMapping(value = "/saveOrupdatBudget", method = { RequestMethod.POST })
	public void saveBudget(HttpServletRequest request, HttpServletResponse response,@RequestBody BudgetModel model) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			BudgetModel mo = new ParamUtils<BudgetModel>().getparams(request, BudgetModel.class);
			if(mo != null && mo.getLoginuser() != null){
				model.setLoginuser(mo.getLoginuser());
			}
			if(StringUtils.isEmpty(model.getId())){
				budgetService.saveBudget(model);
				map.put("message", "添加成功");
				writeJSON(response,map);
			}else{
				//修改
				String msg = budgetService.updateBudget(model);
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
	 * 删除信息
	 */
	@RequestMapping(value = "/delBudget", method = { RequestMethod.POST })
	public void updBudget(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			BudgetModel model = new ParamUtils<BudgetModel>().getparams(request, BudgetModel.class);
			String oper = request.getParameter("oper");
			if(oper != null && oper.equals("del")){
				//删除
				String string = budgetService.deleteBudget(model);
				map.put("message", string);
				writeJSON(response,map);
			}
			
		} catch (Exception e) {
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	}
}
