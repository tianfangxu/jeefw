package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.Compact;
import com.jeefw.model.sys.Information;
import com.jeefw.model.sys.SysUser;
import com.jeefw.service.sys.CompactService;
import com.jeefw.service.sys.InformationService;
import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import core.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.*;

/**
 * 合同管理的控制层
 * @JC
 */
@Controller
@RequestMapping("/sys/compact1")
public class CompactController extends JavaEEFrameworkBaseController<Compact> implements Constant {

	@Resource
	private CompactService compactService;

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
	public void doSave(Compact entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		if (CMD_EDIT.equals(parameter.getCmd())) {
			compactService.merge(entity);
		} else if (CMD_NEW.equals(parameter.getCmd())) {
			compactService.persist(entity);
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
			Compact entity = new Compact();
			String number = request.getParameter("number");
			int type = Integer.parseInt(request.getParameter("type"));
			this.setCompactEntity(request,entity);
			if (StringUtils.isNotBlank(id)) {
				entity.setId(Long.valueOf(id));
				entity.setNumber(number);
				entity.setCmd("edit");
				doSave(entity, request, response);
			} else {
				entity.setCmd("new");
				//生成新的合同编号
				String newNumber = compactService.createNewContractNumber(type);
				entity.setNumber(newNumber);
				if("1,2".indexOf(entity.getType()+"")>-1){
					String wordUrl = createWordFile(entity);
                    entity.setWordurl(wordUrl);
                    //生成pdf
					String pdfurl = createpdfFile(entity);
					entity.setPdfurl(pdfurl);
				}
				doSave(entity, request, response);
			}
		}
	}

	// 删除信息发布
	@RequestMapping("/deleteCompact")
	public void deleteCompact(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
		boolean flag = compactService.deleteByPK(ids);
		if (flag) {
			writeJSON(response, "{success:true}");
		} else {
			writeJSON(response, "{success:false}");
		}
	}

	/**
	 * 生成word文件
	 * @param compact
	 * @return
	 */
	public String createWordFile(Compact compact){
		try {
			Map<String, Object> map = generateWyWordMap(compact);
			String compactName = "";
			String templateName = "";
			if(compact.getType()==1){
				compactName = compact.getNumber()+"_物业管理合同";
				templateName = ConfigUtil.getConfig("wyTemplate");
			}else if(compact.getType()==2){
				compactName = compact.getNumber()+"停车协议合同";
				templateName = ConfigUtil.getConfig("tcTemplate");
			}
			String wordUrl = WordUtils.createDoc(map,compactName,templateName);
			return wordUrl;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e){

		}
		return "";
	}

	/**
	 * 生成pdf文件
	 * @param compact
	 * @return
	 */
	public String createpdfFile(Compact compact){
		if(StringUtils.isNotEmpty(compact.getPdfurl())){
			//删除word文件，因为会生成新的文件
			File file = new File(compact.getPdfurl());
			if(file.isFile()&&file.exists()){
				try{
					file.delete();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
        WordToPdf.wordToPDF(compact.getWordurl(),compact.getWordurl().replace("docx","pdf"));
		return compact.getWordurl().replace("docx","pdf");
	}

	/**
	 * 组织生成物业合同docx格式文件所需要的map数据
	 * @param compact 合同实体类
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> generateWyWordMap(Compact compact) throws ParseException{
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

		map.put("buildarera", compact.getBuildarera());

		map.put("time_year", DateUnit.yearsBetween(compact.getStarttime().replaceAll("/","-"),compact.getEndtime().replaceAll("/","-")));
		map.put("time", compact.getTime());
		map.put("starttime_year", compact.getStarttime().split("/")[0]);
		map.put("starttime_month", compact.getStarttime().split("/")[1]);
		map.put("starttime_day", compact.getStarttime().split("/")[2]);
		map.put("endtime_year", compact.getEndtime().split("/")[0]);
		map.put("endtime_month", compact.getEndtime().split("/")[1]);
		map.put("endtime_day", compact.getEndtime().split("/")[2]);

		map.put("tenantarea", compact.getTenantarea());
		map.put("managefee", compact.getManagefee());
		map.put("managefee_china", NumberToCN.number2CNMontrayUnit(new BigDecimal(compact.getManagefee())));
		map.put("payway", compact.getPayway());
		map.put("prodeposit", compact.getProdeposit());
		map.put("prodeposit_china", NumberToCN.number2CNMontrayUnit(new BigDecimal(compact.getProdeposit())));
		map.put("eleprice", compact.getEleprice());

		map.put("firstparty_bankname", "测试6");
		map.put("firstparty_bank", "测试7");
		map.put("firstparty_dutyparagraph", "测试8");

		map.put("supplementaryterms", compact.getSupplementaryterms());

		return map;
	}

	/**
	 * 封装请求参数到合同实体类
	 * @param request
	 * @param entity
	 * @throws ParseException
	 */
	public void setCompactEntity(HttpServletRequest request,Compact entity) throws ParseException {
		String number = request.getParameter("number");
		int type = Integer.parseInt(request.getParameter("type"));
		String htsj = request.getParameter("htsj");
		String price = request.getParameter("price");
		String buildid = request.getParameter("buildid");
		String propertyid = request.getParameter("propertyid");
		String firstpartyid = request.getParameter("firstpartyid");
		String customerid = request.getParameter("customerid");
		String supplementaryterms = request.getParameter("supplementaryterms");
		String buildarera = request.getParameter("buildarera");
		String tenantarea = request.getParameter("tenantarea");
		String managefee = request.getParameter("managefee");
		String payway = request.getParameter("payway");
		String prodeposit = request.getParameter("prodeposit");
		String eleprice = request.getParameter("eleprice");
		String parkowner = request.getParameter("parkowner");
		String downparkprice = request.getParameter("downparkprice");
		String upparkprice = request.getParameter("upparkprice");
		String downparknum = request.getParameter("downparknum");
		String upparknum = request.getParameter("upparknum");
		String rentalfee = request.getParameter("rentalfee");
		String parkpayway = request.getParameter("parkpayway");
		String licensefee = request.getParameter("licensefee");
		String permitfee = request.getParameter("permitfee");
		int auditstate = Integer.parseInt(request.getParameter("auditstate"));
		entity.setType(type);
		entity.setStarttime(DateUnit.getTimeByStr(htsj.split("-")[0],"MM/dd/YYYY"));
		entity.setEndtime(DateUnit.getTimeByStr(htsj.split("-")[1],"MM/dd/YYYY"));
		entity.setPrice(price);
		entity.setTime(""+DateUnit.monthsBetween(DateUnit.getTimeByStr(htsj.split("-")[0],"MM/dd/YYYY").replaceAll("/","-"),DateUnit.getTimeByStr(htsj.split("-")[1],"MM/dd/YYYY").replaceAll("/","-"))) ;
		entity.setBuildid(buildid);
		entity.setPropertyid(propertyid);
		entity.setFirstpartyid(firstpartyid);
		entity.setCustomerid(customerid);
		entity.setSupplementaryterms(supplementaryterms);
		entity.setBuildarera(buildarera);
		entity.setTenantarea(tenantarea);
		entity.setManagefee(managefee);
		entity.setPayway(payway);
		entity.setProdeposit(prodeposit);
		entity.setEleprice(eleprice);
		entity.setParkowner(parkowner);
		entity.setDownparkprice(downparkprice);
		entity.setUpparkprice(upparkprice);
		if(StringUtils.isNotEmpty(downparknum)){
			entity.setDownparknum(Integer.parseInt(downparknum));
		}
		if(StringUtils.isNotEmpty(upparknum)){
			entity.setDownparknum(Integer.parseInt(upparknum));
		}
		entity.setRentalfee(rentalfee);
		entity.setParkpayway(parkpayway);
		entity.setLicensefee(licensefee);
		entity.setPermitfee(permitfee);
		entity.setAuditstate(auditstate);
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(SESSION_SYS_USER);
		entity.setAddtime(DateUnit.getTime14());
		entity.setAdduser(sysUser.getId()+"");
		entity.setUpdatetime(DateUnit.getTime14());
		entity.setUpdateuser(sysUser.getId()+"");
		entity.setDelflag("0");
	}



}
