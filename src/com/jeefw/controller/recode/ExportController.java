package com.jeefw.controller.recode;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.recode.param.EnterPropertyModel;
import com.jeefw.model.recode.param.ExportPropertyRespModel;
import com.jeefw.service.recode.ExportService;

import core.support.JqGridPageView;
import core.util.ParamUtils;

/**
 * 报表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/recode/export")
public class ExportController extends JavaEEFrameworkBaseController implements Constant{
	
	
	@Resource
	ExportService exportService;
	
	/***
	 * 物业报表
	 */
	@RequestMapping(value = "/getExportInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public void getExportInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			ExportPropertyRespModel model = new ParamUtils<ExportPropertyRespModel>().getparams(request, ExportPropertyRespModel.class);
			JqGridPageView<EnterPropertyModel> result = exportService.getExportInfo(model);
			writeJSON(response, result);
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	} 
}
