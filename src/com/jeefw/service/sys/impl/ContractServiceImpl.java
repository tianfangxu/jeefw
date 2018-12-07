package com.jeefw.service.sys.impl;

import com.jeefw.dao.recode.BuildDao;
import com.jeefw.dao.recode.CustomerDao;
import com.jeefw.dao.recode.PropertyDao;
import com.jeefw.dao.sys.*;
import com.jeefw.model.recode.BuildEntity;
import com.jeefw.model.recode.CustomerEntity;
import com.jeefw.model.recode.PropertyEntity;
import com.jeefw.model.sys.*;
import com.jeefw.model.sys.param.model.BigContractModel;
import com.jeefw.model.sys.param.model.SmallContractModel;
import com.jeefw.service.sys.ContractService;
import core.service.BaseService;
import core.support.JqGridPageView;
import core.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 合同主体信息的业务逻辑层的实现
 * @JC
 */
@Service
public class ContractServiceImpl extends BaseService<Contract> implements ContractService {
	Logger logger = Logger.getLogger(ContractServiceImpl.class);
	private ContractDao contractDao;

	@Resource
	private CustomerDao customerDao;

	@Resource
	private BuildDao buildDao;

	@Resource
	private PropertyDao propertyDao;

	@Resource
	private ContractPropertyDao contractPropertyDao;

	@Resource
	private ContractParkingDao contractParkingDao;

	@Resource
	private ContractFileDao contractFileDao;

	@Resource
	private DictDao dictDao;

	@Resource
	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
		this.dao = contractDao;
	}

	public String createNewContractNumber(int type){
		String selectNumber = "";
		switch (type) {
			case 1:
				selectNumber = "WYHT"+ DateUnit.getTime8();
				break;
			case 2:
				selectNumber = "TCHT"+DateUnit.getTime8();
				break;
			case 3:
				selectNumber = "QTHT"+DateUnit.getTime8();
				break;
		}
		String maxNumber = contractDao.getMaxNumber(selectNumber);
		if(maxNumber.equals("")){
			return selectNumber+"0001";
		}else{
			return selectNumber+String.format("%04d",(1+Integer.parseInt(maxNumber.split(selectNumber)[1])))   ;
		}
	}

	@Override
	public void saveContract(BigContractModel model) throws ParseException {
		if(CommonUtil.isNull(model.getId())){
			String conType = model.getContype();
			//生成新的合同编号
			String newNumber = this.createNewContractNumber(Integer.parseInt(conType));
			//组织合同主体信息
			Contract contract = new Contract();
			contract.setId(createUUID());
			contract.setSysnumber(newNumber);
			contract.setContype(Integer.parseInt(conType));
			contract =  this.generateContract(model,contract);
			String ss = model.getTotalamount();
			contractDao.persist(contract);
			Map<String, Object> map = null;
			if(conType.equals("1")){
				//新增物业合同
				ContractProperty contractProperty = new ContractProperty();
				contractProperty.setContractcode(contract.getId());
				contractProperty.setId(createUUID());
				BuildEntity buildEntity =  buildDao.get(model.getBuildid());
				//String[] propertyIds = model.getPropertyid().split(",");
				String address =buildEntity.getAddress()+model.getPropertyText();
				/*String names = "";
				for(int i=0;i<propertyIds.length;i++){
					PropertyEntity propertyEntity =  propertyDao.get(propertyIds[i]);
					names += propertyEntity.getName()+",";
				}
				if(!names.equals("")){
					 address  = address + names.substring(0,names.length()-1);
				}*/
				contractProperty.setAddress(address);
				contractProperty.setBuildarea(new BigDecimal(model.getBuildarea()));
				contractProperty.setTenantarea(new BigDecimal(model.getTenantarea()));
				contractProperty.setPropertyfee(new BigDecimal(model.getPropertyfee()));
				contractProperty.setDeposit(new BigDecimal(model.getDeposit()));
				contractProperty.setElectric(new BigDecimal(model.getElectric()));
				contractProperty.setPaytype(dictDao.get(Long.valueOf(model.getPaytype())).getDictValue());
				contractProperty.setPaytypecode(model.getPaytype());
				contractPropertyDao.persist(contractProperty);
				//组织物业合同模板数据
				map = generateWyWordMap(contract,contractProperty);

			}else if(conType.equals("2")){
				//新增停车合同
				ContractParking contractParking = new ContractParking();
				contractParking.setContractcode(contract.getId());
				contractParking.setId(createUUID());
				BuildEntity buildEntity =  buildDao.get(model.getBuildid());
				String address =buildEntity.getAddress()+buildEntity.getName();
				contractParking.setAddress(address);
				contractParking.setManager(model.getManager());
				contractParking.setUndergroundunit(new BigDecimal(model.getUndergroundunit()));
				contractParking.setUndergroundnumber(Integer.parseInt(model.getUndergroundnumber()));
				contractParking.setSurfaceunit(new BigDecimal(model.getSurfaceunit()));
				contractParking.setSurfacenumber(Integer.parseInt(model.getSurfacenumber()));
				contractParking.setRent(new BigDecimal(model.getRent()));
				contractParking.setPrepay(Integer.parseInt(model.getPrepay()));
				contractParking.setCardfee(new BigDecimal(model.getCardfee()));
				contractParking.setReissuecardfee(new BigDecimal(model.getReissuecardfee()));
				contractParkingDao.persist(contractParking);
				//组织停车合同模板数据
				map = generateTcWordMap(contract,contractParking,buildEntity.getName());
			}
			if("1,2".indexOf(conType)>-1){
				//生成word
				String wordPath = createWordFile(contract,map);
				//生成paf
				String pdfPath = createpdfFile(wordPath);
				this.saveContractFile(1,wordPath,contract.getId(),model.getLoginuser());
				this.saveContractFile(2,pdfPath,contract.getId(),model.getLoginuser());
			}

		}
	}

	@Override
	public void updateContract(BigContractModel model) throws ParseException {
		//更新合同主表
		updateContract_edit(model);
		Contract contract = contractDao.get(model.getId());
		Map<String, Object> map = null;
		if(model.getContype().equals("1")){
			//更新物业合同
			ContractProperty contractProperty = contractPropertyDao. getContractPropertyByContractId(model.getId());
			BuildEntity buildEntity =  buildDao.get(model.getBuildid());
			//String[] propertyIds = model.getPropertyid().split(",");
			String address =buildEntity.getAddress()+model.getPropertyText();
			/*String names = "";
			for(int i=0;i<propertyIds.length;i++){
				PropertyEntity propertyEntity =  propertyDao.get(propertyIds[i]);
				names += propertyEntity.getName()+",";
			}
			if(!names.equals("")){
				address  = address + names.substring(0,names.length()-1);
			}*/
			contractProperty.setAddress(address);
			contractProperty.setBuildarea(new BigDecimal(model.getBuildarea()));
			contractProperty.setTenantarea(new BigDecimal(model.getTenantarea()));
			contractProperty.setPropertyfee(new BigDecimal(model.getPropertyfee()));
			contractProperty.setDeposit(new BigDecimal(model.getDeposit()));
			contractProperty.setElectric(new BigDecimal(model.getElectric()));
			contractProperty.setPaytype(dictDao.get(Long.valueOf(model.getPaytype())).getDictValue());
			contractProperty.setPaytypecode(model.getPaytype());
			contractPropertyDao.update(contractProperty);
			//组织物业合同模板数据
			map = generateWyWordMap(contract,contractProperty);
		}else if(model.getContype().equals("2")){
			//停车合同
			ContractParking contractParking = contractParkingDao.getContractParkingByContractId(model.getId());
			BuildEntity buildEntity =  buildDao.get(model.getBuildid());
			String address = buildEntity.getAddress()+buildEntity.getName();
			contractParking.setAddress(address);
			contractParking.setManager(model.getManager());
			contractParking.setUndergroundunit(new BigDecimal(model.getUndergroundunit()));
			contractParking.setUndergroundnumber(Integer.parseInt(model.getUndergroundnumber()));
			contractParking.setSurfaceunit(new BigDecimal(model.getSurfaceunit()));
			contractParking.setSurfacenumber(Integer.parseInt(model.getSurfacenumber()));
			contractParking.setRent(new BigDecimal(model.getRent()));
			contractParking.setPrepay(Integer.parseInt(model.getPrepay()));
			contractParking.setCardfee(new BigDecimal(model.getCardfee()));
			contractParking.setReissuecardfee(new BigDecimal(model.getReissuecardfee()));
			contractParkingDao.update(contractParking);
			//组织停车合同模板数据
			map = generateTcWordMap(contract,contractParking,buildEntity.getName());
		}

		if("1,2".indexOf(model.getContype())>-1){
			//生成word
			String wordPath = createWordFile(contract,map);
			//生成pdf
			String pdfPath = createpdfFile(wordPath);
		}

	}

	@Override
	public JqGridPageView<Contract> getContractByCondition(BigContractModel model) {
		return contractDao.getContractByCondition(model);
	}

	@Override
	public JqGridPageView<ContractFile> getContractFileByCondition(BigContractModel model) {
		return contractFileDao.getContractFileByCondition(model);
	}

	@Override
	public String deleteCompact(BigContractModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		String[] strs = model.getId().split(",");
		for (String id : strs) {
			contractDao.deleteEntity(id,model.getLoginuser());
		}
		return "删除成功";
	}

	@Override
	public JqGridPageView<BigContractModel> getContractWithInfoById(BigContractModel model) {
		return contractDao.getContractWithInfoById(model);
	}

	@Override
	public JqGridPageView<SmallContractModel> getContractByAudit(BigContractModel model) {
		return contractDao.getContractByAudit(model);
	}


	private String createUUID() {
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid;
	}

	public Contract generateContract(BigContractModel model,Contract contract){
		try{
			//组织合同主体信息
			BeanUtils.copyPropertiesIgnoreNull(model,contract);
			String htsj = model.getHtsj();
			contract.setTotalamount(new BigDecimal(model.getTotalamount()));
			contract.setStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(DateUnit.getDateByMMddyyyy(htsj.split("-")[0])));
			contract.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(DateUnit.getDateByMMddyyyy(htsj.split("-")[1])));
			contract.setBuildcode(model.getBuildid());
			contract.setPropertycodes(model.getPropertyid());
			contract.setPropertytext(model.getPropertyText());
			if(CommonUtil.isNull(model.getPartbcode())){
				//生成乙方信息
				CustomerEntity customerEntity = new CustomerEntity();
				customerEntity.setId(createUUID());
				customerEntity.setType(model.getPartbtype());
				customerEntity.setAddress(model.getPartbaddress());
				customerEntity.setName(model.getPartbname());
				customerEntity.setContactname(model.getPartblegalperson());
				customerEntity.setContactnumber(model.getPartbcontact());
				customerEntity.setTaxnumber(model.getPartbtaxnumber());
				customerEntity.setAccount(model.getPartbaccount());
				customerEntity.setAccountname(model.getPartbaccountname());
				customerEntity.setBankname(model.getPartbbankname());
				customerEntity.setCreateuser(model.getLoginuser().getUserName());
				customerEntity.setCreatetime(DateUnit.getTime14());
				customerEntity.setDeleteflg("0");
				customerDao.persist(customerEntity);
				contract.setPartbcode(customerEntity.getId());
				contract.setPartbtype(customerEntity.getType());
				contract.setPartbaddress(customerEntity.getAddress());
				contract.setPartblegalperson(customerEntity.getContactname());
				contract.setPartbtaxnumber(customerEntity.getTaxnumber());
				contract.setPartbcontact(customerEntity.getContactnumber());
				contract.setPartbname(customerEntity.getName());
				contract.setPartbaccount(customerEntity.getAccount());
				contract.setPartbaccountname(customerEntity.getAccountname());
				contract.setPartbbankname(customerEntity.getBankname());
			}
			contract.setCreatetime(new Date());
			contract.setCreateuser(model.getLoginuser().getId()+"");
			contract.setUpdatetime(new Date());
			contract.setUpdateuser(model.getLoginuser().getId()+"");
			contract.setDeleteflg(false);
			return contract;
		}catch(Exception e){
			System.out.println("11111");
		}
		return null;
	}

	public Map<String, Object> generateWyWordMap(Contract contract, ContractProperty contractProperty) throws ParseException {
		//获得数据
		Map<String, Object> map = new HashMap<String, Object>();
		//通过firstpartyid获取甲方信息
		map.put("firstparty_name",contract.getPartaname());
		map.put("firstparty_address",contract.getPartaaddress());
		map.put("firstparty_linkname",contract.getPartalegalperson());
		map.put("firstparty_linkphone",contract.getPartancontact());

		//通过Customerid获取客户信息
		map.put("c_name",contract.getPartbname());
		map.put("c_address",contract.getPartbaddress());
		map.put("c_linkname",contract.getPartblegalperson());
		map.put("c_phone",contract.getPartbcontact());

		//通过buildid获取房屋信息
		map.put("address", contractProperty.getAddress());
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

		map.put("firstparty_bankname", contract.getBankname());
		map.put("firstparty_bank",contract.getPartaaccountname());
		map.put("firstparty_dutyparagraph", contract.getPartaaccount());
		map.put("supplementaryterms", contract.getSubsidiary());

		return map;
	}

	public Map<String, Object> generateTcWordMap(Contract contract, ContractParking contractParking,String build) throws ParseException {
		//获得数据
		Map<String, Object> map = new HashMap<String, Object>();
		//通过firstpartyid获取甲方信息
		map.put("firstparty_name",contract.getPartaname());
		map.put("firstparty_address",contract.getPartaaddress());
		map.put("firstparty_linkname",contract.getPartalegalperson());
		map.put("firstparty_linkphone",contract.getPartancontact());

		map.put("build",build);

		//通过Customerid获取客户信息
		map.put("c_name",contract.getPartbname());
		map.put("c_address",contract.getPartbaddress());
		map.put("c_linkname",contract.getPartblegalperson());
		map.put("c_phone",contract.getPartbcontact());

		//通过buildid获取房屋信息
		map.put("address", contractParking.getAddress());
		map.put("manager", contractParking.getManager());
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

		map.put("undergroundunit", contractParking.getUndergroundunit());
		map.put("surfaceunit", contractParking.getSurfaceunit());
		map.put("undergroundnumber", contractParking.getUndergroundnumber()+"");
		map.put("surfacenumber", contractParking.getUndergroundunit()+"");
		map.put("rent", contractParking.getRent());
		map.put("prepay", contractParking.getPrepay());
		map.put("cardfee", contractParking.getCardfee());
		map.put("reissuecardfee", contractParking.getReissuecardfee());
		map.put("supplementaryterms", contract.getSubsidiary());

		return map;
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

	private void saveContractFile(int type,String url,String contractcode,SysUser sysUser){
		ContractFile contractFile = new ContractFile();
		contractFile.setId(createUUID());
		contractFile.setContractcode(contractcode);
		contractFile.setCreatetime(new Date());
		contractFile.setCreateuser(sysUser.getId()+"");
		contractFile.setDeleteflg(false);
		contractFile.setFiletype(type);
		contractFile.setFileurl(url);
		contractFileDao.persist(contractFile);
	}

	private void  updateContract_edit(BigContractModel model) throws  ParseException{
		String htsj = model.getHtsj();
		if(CommonUtil.isNull(model.getPartbcode())){
			//生成乙方信息
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setId(createUUID());
			customerEntity.setType(model.getPartbtype());
			customerEntity.setAddress(model.getPartbaddress());
			customerEntity.setName(model.getPartbname());
			customerEntity.setContactname(model.getPartblegalperson());
			customerEntity.setContactnumber(model.getPartbcontact());
			customerEntity.setTaxnumber(model.getPartbtaxnumber());
			customerEntity.setAccount(model.getPartbaccount());
			customerEntity.setAccountname(model.getPartbaccountname());
			customerEntity.setBankname(model.getPartbbankname());
			customerEntity.setCreateuser(model.getLoginuser().getUserName());
			customerEntity.setCreatetime(DateUnit.getTime14());
			customerEntity.setDeleteflg("0");
			customerDao.persist(customerEntity);
			model.setPartbcode(customerEntity.getId());
		}
		Contract contract = contractDao.get(model.getId());
		BeanUtils.copyPropertiesIgnoreNull(model,contract);
		contract.setTotalamount(new BigDecimal(model.getTotalamount()));
		contract.setStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(DateUnit.getDateByMMddyyyy(htsj.split("-")[0])));
		contract.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(DateUnit.getDateByMMddyyyy(htsj.split("-")[1])));
		contract.setBuildcode(model.getBuildid());
		contract.setPropertycodes(model.getPropertyid());
		contract.setUpdatetime(new Date());
		contract.setUpdateuser(model.getLoginuser().getId()+"");
		contract.setPropertytext(model.getPropertyText());
		contractDao.update(contract);
	}
}
