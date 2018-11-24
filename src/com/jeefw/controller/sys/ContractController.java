package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.*;
import com.jeefw.service.sys.CompactService;
import com.jeefw.service.sys.ContractFileService;
import com.jeefw.service.sys.ContractPropertyService;
import com.jeefw.service.sys.ContractService;
import core.support.ExtJSBaseParameter;
import core.util.*;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 合同管理的控制层
 * @JC
 */
@Controller
@RequestMapping("/sys/compact")
public class ContractController extends JavaEEFrameworkBaseController<Contract> implements Constant {

	@Resource
	private ContractService contractService;

	@Resource
	private ContractPropertyService contractPropertyService;

	@Resource
	private ContractFileService contractFileService;

	// 查询信息发布的表格，包括分页、搜索和排序
	@RequestMapping(value = "/getCompact", method = { RequestMethod.POST, RequestMethod.GET })
	public void getCompact(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*Integer firstResult = Integer.valueOf(request.getParameter("page"));
		Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		String sortedObject = request.getParameter("sidx");
		String sortedValue = request.getParameter("sord");
		String filters = request.getParameter("filters");
		Information information = new Information();
		if (StringUtils.isNotBlank(filters)) {
			JSONObject jsonObject = JSONObject.fromObject(filters);
			JSONArray jsonArray = (JSONArray) jsonObject.get("rules");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject result = (JSONObject) jsonArray.get(i);
				if (result.getString("field").equals("title") && result.getString("op").equals("cn")) {
					information.set$like_title(result.getString("data"));
				}
			}
			if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
				information.setFlag("OR");
			} else {
				information.setFlag("AND");
			}
		}
		information.setFirstResult((firstResult - 1) * maxResults);
		information.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		information.setSortedConditions(sortedCondition);
		QueryResult<Information> queryResult = compactService.doPaginationQuery(information);
		JqGridPageView<Information> informationListView = new JqGridPageView<Information>();
		informationListView.setMaxResults(maxResults);
		List<Information> informationHTMLList = compactService.queryInformationHTMLList(queryResult.getResultList());
		informationListView.setRows(informationHTMLList);
		informationListView.setRecords(queryResult.getTotalCount());
		writeJSON(response, informationListView);*/
	}

	// 全文检索信息
	@RequestMapping(value = "/getCompactHibernateSearch", method = { RequestMethod.POST, RequestMethod.GET })
	public void getInformationHibernateSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*String luceneName = request.getParameter("luceneName");
		// Integer firstResult = Integer.valueOf(request.getParameter("page"));
		// Integer maxResults = Integer.valueOf(request.getParameter("rows"));
		Integer firstResult = 1;
		Integer maxResults = 10;
		Information information = new Information();
		information.setFirstResult((firstResult - 1) * maxResults);
		information.setMaxResults(maxResults);
		JqGridPageView<Information> informationListView = new JqGridPageView<Information>();
		informationListView.setMaxResults(maxResults);
		List<Information> informationLuceneList = compactService.queryByInformationName(luceneName);
		informationListView.setRows(informationLuceneList);
		informationListView.setRecords(informationLuceneList.size());
		writeJSON(response, informationListView);*/
	}

	// 保存信息发布的实体Bean

	@RequestMapping(value = "/saveCompact", method = { RequestMethod.POST, RequestMethod.GET })
	public void doSave(Contract entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		if (CMD_EDIT.equals(parameter.getCmd())) {
			contractService.merge(entity);
		} else if (CMD_NEW.equals(parameter.getCmd())) {
			contractService.persist(entity);
		}
		parameter.setSuccess(true);
		writeJSON(response, parameter);
	}

	// 操作合同管理的删除、导出Excel、字段判断和保存
	@RequestMapping(value = "/operateCompact", method = { RequestMethod.POST, RequestMethod.GET })
	public void operateInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");
		if (oper.equals("del")) {
			String[] ids = id.split(",");
			deleteCompact(request, response, (Long[]) ConvertUtils.convert(ids, Long.class));
		} else if (oper.equals("excel")) {
			response.setContentType("application/msexcel;charset=UTF-8");
			try {
				response.addHeader("Content-Disposition", "attachment;filename=file.xls");
				OutputStream out = response.getOutputStream();
				out.write(URLDecoder.decode(request.getParameter("csvBuffer"), "UTF-8").getBytes());
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Contract contract = new Contract();
			ContractProperty contractProperty = new ContractProperty();
			String number = request.getParameter("number");
			int type = Integer.parseInt(request.getParameter("type"));
			if (StringUtils.isNotBlank(id)) {
				contract.setCode(id);
				contract.setSysnumber(number);
				contract.setCmd("edit");
				doSave(contract, request, response);
			} else {
				contract.setCmd("new");
				contract.setCode(createUUID());
				//生成新的合同编号
				String newNumber = contractService.createNewContractNumber(type);
				contract.setSysnumber(newNumber);
				//组织合同主体信息 保存
				this.setContractEntity(request,contract);
				contractService.persist(contract);
				Map<String, Object> map = null;
				if(type==1){
					//组织物业合同数据 保存
					contractProperty.setCode(createUUID());
					this.setContractPropertyEntity(request,contractProperty,contract.getCode());
					contractPropertyService.persist(contractProperty);
					//组织物业合同模板数据
					map = generateWyWordMap(contract,contractProperty);

				}else if(type==2){
					//组织停车合同数据 保存
					//组织停车合同模板数据
				}

				if("1,2".indexOf(type+"")>-1){
					//生成word
					String wordPath = createWordFile(contract,map);
					//生成paf
					String pdfPath = createpdfFile(wordPath);
					this.saveContractFile(1,wordPath,contract.getCode());
					this.saveContractFile(2,pdfPath,contract.getCode());
				}

			}
		}
	}



	// 删除信息发布
	@RequestMapping("/deleteCompact")
	public void deleteCompact(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = contractService.deleteByPK(ids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}

	/**
	 * 生成word文件
	 * @param contract
	 * @param map
	 * @return
	 */
	public String createWordFile(Contract contract,Map<String, Object> map){
		try {
			String compactName = "";
			String templateName = "";
			if(contract.getContype()==1){
				compactName = contract.getSysnumber()+"_物业管理合同";
				templateName = ConfigUtil.getConfig("wyTemplate");
			}else if(contract.getContype()==2){
				compactName = contract.getSysnumber()+"停车协议合同";
				templateName = ConfigUtil.getConfig("tcTemplate");
			}
			String wordUrl = WordUtils.createDoc(map,compactName,templateName);
			return wordUrl;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 生成pdf文件
	 * @param wordPath
	 * @return
	 */
	public String createpdfFile(String wordPath){
		WordToPdf.wordToPDF(wordPath,wordPath.replace("docx","pdf"));
		return wordPath.replace("docx","pdf");
	}


	private void saveContractFile(int type,String url,String contractcode){
		ContractFile contractFile = new ContractFile();
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(SESSION_SYS_USER);
		contractFile.setCode(createUUID());
		contractFile.setContractcode(contractcode);
		contractFile.setCreatetime(new Date());
		contractFile.setCreateuser(sysUser.getId()+"");
		contractFile.setDeleteflg(false);
		contractFile.setFiletype(type);
		contractFile.setFilerurl(url);
		contractFileService.persist(contractFile);
	}

	/**
	 * 组织生成物业合同docx格式文件所需要的map数据
	 * @param contract 合同主体实体类
	 * @param contractProperty 合同物业实体类
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> generateWyWordMap(Contract contract,ContractProperty contractProperty) throws ParseException{
		//获得数据
		Map<String, Object> map = new HashMap<String, Object>();
		//通过firstpartyid获取甲方信息
		map.put("firstparty_name","测试物业管理方名字");
		map.put("firstparty_address","测试物业管理方地址");
		map.put("firstparty_linkname","测试物业管理方法定代表人");
		map.put("firstparty_linkphone","测试物业管理方联系电话");
		//通过Customerid获取客户信息
		map.put("c_name","测试物业承租方名字");
		map.put("c_address","测试物业承租方地址");
		map.put("c_linkname","测试物业承租方法定代表人");
		map.put("c_phone","测试物业承租方联系电话");
		//通过buildid获取房屋信息
		map.put("area", "测试1");
		map.put("road", "测试2");
		map.put("house", "测试3");
		map.put("buildname", "测试4");
		map.put("roomname", "测试5");

		map.put("buildarera", contractProperty.getBuildarea());
		String startTime = new SimpleDateFormat("YYYY-MM-dd").format(contract.getStartdate());
		String endTime = new SimpleDateFormat("YYYY-MM-dd").format(contract.getEnddate());


		map.put("time_year", DateUnit.yearsBetween(startTime,endTime));
		map.put("time", DateUnit.monthsBetween(startTime,endTime));
		map.put("starttime_year", startTime.split("-")[0]);
		map.put("starttime_month", startTime.split("-")[1]);
		map.put("starttime_day", startTime.split("-")[2]);
		map.put("endtime_year", endTime.split("-")[0]);
		map.put("endtime_month", endTime.split("-")[1]);
		map.put("endtime_day", endTime.split("-")[2]);

		map.put("tenantarea", contractProperty.getTenantarea());
		map.put("managefee", contractProperty.getPropertyfee());
		map.put("managefee_china", NumberToCN.number2CNMontrayUnit(contractProperty.getPropertyfee()));
		map.put("payway", contractProperty.getPaytype());
		map.put("prodeposit", contractProperty.getDeposit());
		map.put("prodeposit_china", NumberToCN.number2CNMontrayUnit(contractProperty.getDeposit()));
		map.put("eleprice", contractProperty.getElectric());

		map.put("firstparty_bankname", "测试6");
		map.put("firstparty_bank", "测试7");
		map.put("firstparty_dutyparagraph", "测试8");

		map.put("supplementaryterms", contract.getSubsidiary());

		return map;
	}

	/**
	 * 封装请求参数到合同主体信息实体类
	 * @param request
	 * @param contract
	 * @throws ParseException
	 */
	public void setContractEntity(HttpServletRequest request,Contract contract) throws ParseException {
		int type = Integer.parseInt(request.getParameter("type"));  //合同类型
		String htsj = request.getParameter("htsj");    //合同时间
		String price = request.getParameter("price");  //合同金额
		String firstpartyid = request.getParameter("firstpartyid"); //甲方id
		String customerid = request.getParameter("customerid");   //乙方id
		String supplementaryterms = request.getParameter("supplementaryterms");     //补充条款
		int auditstate = Integer.parseInt(request.getParameter("auditstate"));      //合同审核状态

		contract.setStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(DateUnit.getDateByMMddyyyy(htsj.split("-")[0])));
		contract.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(DateUnit.getDateByMMddyyyy(htsj.split("-")[1])));
		contract.setContype(type);
		contract.setTotalamount(new BigDecimal(price).setScale(2,BigDecimal.ROUND_HALF_UP));
		contract.setPartacode(firstpartyid);
		contract.setPartbcode(customerid);
		contract.setSubsidiary(supplementaryterms);
		contract.setAuditstate(auditstate);
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(SESSION_SYS_USER);
		contract.setCreateuser(sysUser.getId()+"");
		contract.setCreatetime(new Date());
		contract.setDeleteflg(false);
	}



	/**
	 * 封装请求参数到合同物业信息实体类
	 * @param request
	 * @param contractProperty
	 * @throws ParseException
	 */
	public void setContractPropertyEntity(HttpServletRequest request, ContractProperty contractProperty,String contractCode) throws ParseException {
		String buildarera = request.getParameter("buildarera");
		String tenantarea = request.getParameter("tenantarea");
		String managefee = request.getParameter("managefee");
		String payway = request.getParameter("payway");
		String prodeposit = request.getParameter("prodeposit");
		String eleprice = request.getParameter("eleprice");

		contractProperty.setContractcode(contractCode);
		contractProperty.setBuildarea(new BigDecimal(buildarera).setScale(2,BigDecimal.ROUND_HALF_UP));
		contractProperty.setTenantarea(new BigDecimal(tenantarea).setScale(2,BigDecimal.ROUND_HALF_UP));
		contractProperty.setPropertyfee(new BigDecimal(managefee).setScale(2,BigDecimal.ROUND_HALF_UP));
		contractProperty.setPaytype(payway);
		contractProperty.setDeposit(new BigDecimal(prodeposit).setScale(2,BigDecimal.ROUND_HALF_UP));
		contractProperty.setElectric(new BigDecimal(eleprice).setScale(2,BigDecimal.ROUND_HALF_UP));

	}



	 private String createUUID(){
		 String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		 return uuid;
	 }


}
