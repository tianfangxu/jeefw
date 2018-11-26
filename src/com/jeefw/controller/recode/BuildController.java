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
import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.param.BuildModel;
import com.jeefw.model.recode.param.MasterModel;
import com.jeefw.service.recode.BuildService;

import core.support.JqGridPageView;
import core.util.ParamUtils;

/***
 * 楼宇信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/recode/build")
public class BuildController extends JavaEEFrameworkBaseController<BuildEntity> implements Constant{
	
	@Resource
	BuildService buildService;
	
	/**
	 * 查询楼宇信息
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value = "/getBuildByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public void getBuildByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			BuildModel model = new ParamUtils<BuildModel>().getparams(request, BuildModel.class);
			JqGridPageView<BuildEntity> buildEntityJqGridPageView = null;
			buildEntityJqGridPageView = buildService.getBuildByCondition(model);
			writeJSON(response, buildEntityJqGridPageView);
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	} 
	
	/***
	 * 保存/修改楼宇信息
	 */
	@RequestMapping(value = "/saveOrupdatBuild", method = { RequestMethod.POST })
	public void saveBuild(HttpServletRequest request, HttpServletResponse response,@RequestBody BuildModel model) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			BuildModel mo = new ParamUtils<BuildModel>().getparams(request, BuildModel.class);
			if(mo != null && mo.getLoginuser() != null){
				model.setLoginuser(mo.getLoginuser());
			}
			if(StringUtils.isEmpty(model.getId())){
				buildService.saveBuild(model);
				map.put("message", "添加成功");
				writeJSON(response,map);
			}else{
				//修改
				String msg = buildService.updateBuild(model);
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
	@RequestMapping(value = "/delBuild", method = { RequestMethod.POST })
	public void updBuild(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			BuildModel model = new ParamUtils<BuildModel>().getparams(request, BuildModel.class);
			String oper = request.getParameter("oper");
			if(oper != null && oper.equals("del")){
				//删除
				String string = buildService.deleteBuild(model);
				map.put("message", string);
				writeJSON(response,map);
			}
			
		} catch (Exception e) {
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	}
}
