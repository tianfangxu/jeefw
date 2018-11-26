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
import com.jeefw.model.recode.ParkingEntity;
import com.jeefw.model.recode.param.ParkingModel;
import com.jeefw.service.recode.ParkingService;

import core.support.JqGridPageView;
import core.util.ParamUtils;


/***
 *停车位信息
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/recode/parking")
public class ParkingController extends JavaEEFrameworkBaseController<ParkingEntity> implements Constant{

	@Resource
	ParkingService parkingService;
	
	/**
	 * 停车位信息
	 */
	@SuppressWarnings({"rawtypes" })
	@RequestMapping(value = "/getParkingByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public void getParkingByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			ParkingModel model = new ParamUtils<ParkingModel>().getparams(request, ParkingModel.class);
			JqGridPageView<ParkingModel> propertyModelJqGridPageView = null;
			propertyModelJqGridPageView = parkingService.getParkingByCondition(model);
			writeJSON(response, propertyModelJqGridPageView);
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	} 
	
	/***
	 * 保存/修改停车位信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/saveOrupdParking", method = { RequestMethod.POST })
	public void saveOrupdParking(HttpServletRequest request, HttpServletResponse response,@RequestBody ParkingModel model) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			ParkingModel mo = new ParamUtils<ParkingModel>().getparams(request, ParkingModel.class);
			if(mo != null && mo.getLoginuser() != null){
				model.setLoginuser(mo.getLoginuser());
			}
			if(StringUtils.isEmpty(model.getCode())){
				parkingService.saveParking(model);
				map.put("message", "添加成功");
				writeJSON(response,map);
			}else{
				//修改
				String msg = parkingService.updateParking(model);
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
	 * 删除停车位信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delParking", method = { RequestMethod.POST })
	public void delParking(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			ParkingModel model = new ParamUtils<ParkingModel>().getparams(request, ParkingModel.class);
			String oper = request.getParameter("oper");
			if(oper != null && oper.equals("del")){
				//删除
				String string = parkingService.deleteParking(model);
				map.put("message", string);
				writeJSON(response,map);
			}
		} catch (Exception e) {
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	}
}
