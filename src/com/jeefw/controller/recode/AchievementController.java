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
import com.jeefw.model.recode.AchievementEntity;
import com.jeefw.model.recode.param.AchievementModel;
import com.jeefw.service.recode.AchievementService;

import core.support.JqGridPageView;
import core.util.ParamUtils;

/**
 * 月度实绩
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/recode/achievement")
public class AchievementController extends JavaEEFrameworkBaseController<AchievementEntity> implements Constant {

	@Resource
	AchievementService achievementService;
	
	/**
	 * 查询月度实绩
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value = "/getAchievementByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public void getachievementByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			AchievementModel model = new ParamUtils<AchievementModel>().getparams(request, AchievementModel.class);
			JqGridPageView<AchievementModel> entityJqGridPageView = null;
			entityJqGridPageView = achievementService.getAchievementByCondition(model);
			writeJSON(response, entityJqGridPageView);
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	} 
	
	/***
	 * 保存/修改月度实绩
	 */
	@RequestMapping(value = "/saveOrupdatAchievement", method = { RequestMethod.POST })
	public void saveachievement(HttpServletRequest request, HttpServletResponse response,@RequestBody AchievementModel model) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			AchievementModel mo = new ParamUtils<AchievementModel>().getparams(request, AchievementModel.class);
			if(mo != null && mo.getLoginuser() != null){
				model.setLoginuser(mo.getLoginuser());
			}
			if(StringUtils.isEmpty(model.getId())){
				achievementService.saveAchievement(model);
				map.put("message", "添加成功");
				writeJSON(response,map);
			}else{
				//修改
				String msg = achievementService.updateAchievement(model);
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
	@RequestMapping(value = "/delAchievement", method = { RequestMethod.POST })
	public void updachievement(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			AchievementModel model = new ParamUtils<AchievementModel>().getparams(request, AchievementModel.class);
			String oper = request.getParameter("oper");
			if(oper != null && oper.equals("del")){
				//删除
				String string = achievementService.deleteAchievement(model);
				map.put("message", string);
				writeJSON(response,map);
			}
			
		} catch (Exception e) {
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	}
}
