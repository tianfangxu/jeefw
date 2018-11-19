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
import com.jeefw.model.recode.firstpartyContractEntity;
import com.jeefw.model.recode.param.firstpartyContractModel;
import com.jeefw.service.recode.FirstpartyContractService;

import core.support.JqGridPageView;

/**
 * 甲方合同主体
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/recode/firstpartyContract")
public class FirstpartyContractController extends JavaEEFrameworkBaseController<firstpartyContractEntity> implements Constant{
	
	@Resource
	FirstpartyContractService firstpartyContractService;
	
	/**
	 * 获取甲方合同数据
	 */
	@RequestMapping(value = "/getfirstpartyContractByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public void getfirstpartyContractByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			firstpartyContractModel model = getparam(request);
			JqGridPageView<firstpartyContractEntity> firstpartyContract = null;
			firstpartyContract = firstpartyContractService.getfirstpartyContractByCondition(model);
			writeJSON(response, firstpartyContract);
		} catch (Exception e) {
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	}
	
	/**
	 * 修改/保存甲方合同数据
	 */
	@RequestMapping(value = "/savefirstpartyContract", method = { RequestMethod.POST })
	public void savefirstpartyContract(HttpServletRequest request, HttpServletResponse response,
			@RequestBody firstpartyContractModel model) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			if(StringUtils.isEmpty(model.getId())){
				firstpartyContractService.savefirstpartyContract(model);
				map.put("message", "添加成功");
				writeJSON(response,map);
			}else{
				//修改
				String msg = firstpartyContractService.updfirstpartyContract(model);
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
	 * 删除-甲方合同数据
	 */
	@RequestMapping(value = "/updfirstpartyContract", method = { RequestMethod.POST })
	public void updfirstpartyContract(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HashMap<String,String> map = new HashMap<String, String>();
		try {
			firstpartyContractModel model = getparam(request);
			String oper = request.getParameter("oper");
			if(oper != null && oper.equals("del")){
				//删除
				String string = firstpartyContractService.deletefirstpartyContract(model);
				map.put("message", string);
				writeJSON(response,map);
			}
			
		} catch (Exception e) {
			map.put("message", "系统出错，请稍后重试");
			writeJSON(response, map);
		}
	}
	
	public firstpartyContractModel getparam(HttpServletRequest request){
		firstpartyContractModel model = new firstpartyContractModel();
		model.setPage(request.getParameter("page"));
		model.setRows(request.getParameter("rows"));
		model.setId(request.getParameter("id"));
		return model;
	}
}
