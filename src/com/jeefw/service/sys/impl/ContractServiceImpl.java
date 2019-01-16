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
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 合同主体信息的业务逻辑层的实现
 *
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

    public String createNewContractNumber(int type) {
        String selectNumber = "";
        switch (type) {
            case 1:
                selectNumber = "WYHT" + DateUnit.getTime8();
                break;
            case 2:
                selectNumber = "TCHT" + DateUnit.getTime8();
                break;
            case 3:
                selectNumber = "QTHT" + DateUnit.getTime8();
                break;
        }
        String maxNumber = contractDao.getMaxNumber(selectNumber);
        if (maxNumber.equals("")) {
            return selectNumber + "0001";
        } else {
            return selectNumber + String.format("%04d", (1 + Integer.parseInt(maxNumber.split(selectNumber)[1])));
        }
    }

    @Override
    public void saveContract(BigContractModel model) throws ParseException {
        if (CommonUtil.isNull(model.getId())) {
            String conType = model.getContype();
            //生成新的合同编号
            String newNumber = this.createNewContractNumber(Integer.parseInt(conType));
            //组织合同主体信息
            Contract contract = new Contract();
            contract.setId(createUUID());
            contract.setSysnumber(newNumber);
            contract.setContype(Integer.parseInt(conType));
            contract = this.generateContract(model, contract);
            String ss = model.getTotalamount();
            contractDao.persist(contract);
            Map<String, Object> map = null;
            if (conType.equals("1")) {
                //新增物业合同
                ContractProperty contractProperty = new ContractProperty();
                contractProperty.setContractcode(contract.getId());
                contractProperty.setId(createUUID());
                BuildEntity buildEntity = buildDao.get(model.getBuildid());
                //String[] propertyIds = model.getPropertyid().split(",");
                String address = buildEntity.getAddress() + model.getPropertyText();
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
                if (CommonUtil.isNotNull(model.getTenantarea())) {
                    contractProperty.setTenantarea(new BigDecimal(model.getTenantarea()));
                }
                contractProperty.setPropertyfee(new BigDecimal(model.getPropertyfee()));
                contractProperty.setDeposit(new BigDecimal(model.getDeposit()));
                if (CommonUtil.isNotNull(model.getElectric())) {
                    contractProperty.setElectric(new BigDecimal(model.getElectric()));
                }
                contractProperty.setPaytype(dictDao.get(Long.valueOf(model.getPaytype())).getDictValue());
                contractProperty.setPaytypecode(model.getPaytype());
                contractPropertyDao.persist(contractProperty);
                //组织物业合同模板数据
                map = generateWyWordMap(contract, contractProperty);

            } else if (conType.equals("2")) {
                //新增停车合同
                ContractParking contractParking = new ContractParking();
                contractParking.setContractcode(contract.getId());
                contractParking.setId(createUUID());
                BuildEntity buildEntity = buildDao.get(model.getBuildid());
                String address = buildEntity.getAddress() + buildEntity.getName();
                contractParking.setAddress(address);
                contractParking.setManager(model.getManager());
                if(CommonUtil.isNotNull(model.getUndergroundunit())){
                    contractParking.setUndergroundunit(new BigDecimal(model.getUndergroundunit()));
                }
                if(CommonUtil.isNotNull(model.getUndergroundnumber())){
                    contractParking.setUndergroundnumber(Integer.parseInt(model.getUndergroundnumber()));
                }
                if(CommonUtil.isNotNull(model.getSurfaceunit())){
                    contractParking.setSurfaceunit(new BigDecimal(model.getSurfaceunit()));
                }
                if(CommonUtil.isNotNull(model.getSurfacenumber())){
                    contractParking.setSurfacenumber(Integer.parseInt(model.getSurfacenumber()));
                }
                if(CommonUtil.isNotNull(model.getRent())){
                    contractParking.setRent(new BigDecimal(model.getRent()));
                }
                if(CommonUtil.isNotNull(model.getPrepay())){
                    contractParking.setPrepay(Integer.parseInt(model.getPrepay()));
                }
                if(CommonUtil.isNotNull(model.getCardfee())){
                    contractParking.setCardfee(new BigDecimal(model.getCardfee()));
                }
                if(CommonUtil.isNotNull(model.getReissuecardfee())){
                    contractParking.setReissuecardfee(new BigDecimal(model.getReissuecardfee()));
                }

                contractParkingDao.persist(contractParking);
                //组织停车合同模板数据
                map = generateTcWordMap(contract, contractParking, buildEntity.getName());
            }
            if ("1,2".indexOf(conType) > -1) {
                //生成word
                String wordPath = createWordFile(contract, map);
                //生成paf
                String pdfPath = createpdfFile(wordPath);
                this.saveContractFile(1, wordPath, contract.getId(), model.getLoginuser());
                this.saveContractFile(2, pdfPath, contract.getId(), model.getLoginuser());
            }

        }
    }

    @Override
    public void updateContract(BigContractModel model) throws ParseException {
        //更新合同主表
        updateContract_edit(model);
        Contract contract = contractDao.get(model.getId());
        Map<String, Object> map = null;
        boolean flag = false;
        if (model.getContype().equals("1")) {
            //更新物业合同
            ContractProperty contractProperty = contractPropertyDao.getContractPropertyByContractId(model.getId());

            if(contractProperty==null){
                contractProperty = new ContractProperty();
                contractProperty.setContractcode(model.getId());
                contractProperty.setId(createUUID());
                flag = true;
            }
            BuildEntity buildEntity = buildDao.get(model.getBuildid());
            //String[] propertyIds = model.getPropertyid().split(",");
            String address = buildEntity.getAddress() + model.getPropertyText();
			/*String names = "";
			for(int i=0;i<propertyIds.length;i++){
				PropertyEntity propertyEntity =  propertyDao.get(propertyIds[i]);
				names += propertyEntity.getName()+",";
			}
			if(!names.equals("")){
				address  = address + names.substring(0,names.length()-1);
			}*/
			if(CommonUtil.isNotNull(address))   {
                contractProperty.setAddress(address);
            }

            if(CommonUtil.isNotNull(model.getBuildarea())) {
                contractProperty.setBuildarea(new BigDecimal(model.getBuildarea()));
            }

            if(CommonUtil.isNotNull(model.getTenantarea())) {
                contractProperty.setTenantarea(new BigDecimal(model.getTenantarea()));
            }
            if(CommonUtil.isNotNull(model.getPropertyfee())){
                contractProperty.setPropertyfee(new BigDecimal(model.getPropertyfee()));
            }
            if(CommonUtil.isNotNull(model.getDeposit())){
                contractProperty.setDeposit(new BigDecimal(model.getDeposit()));
            }
            if(CommonUtil.isNotNull(model.getElectric())){
                contractProperty.setElectric(new BigDecimal(model.getElectric()));
            }
            contractProperty.setPaytype(dictDao.get(Long.valueOf(model.getPaytype())).getDictValue());
            contractProperty.setPaytypecode(model.getPaytype());

            if(flag){
                contractPropertyDao.persist(contractProperty);
            }  else{
                contractPropertyDao.update(contractProperty);
            }
            //组织物业合同模板数据
            map = generateWyWordMap(contract, contractProperty);
        } else if (model.getContype().equals("2")) {
            //停车合同
            ContractParking contractParking = contractParkingDao.getContractParkingByContractId(model.getId());
            if(contractParking==null){
                contractParking = new ContractParking();
                contractParking.setContractcode(model.getId());
                contractParking.setId(createUUID());
                flag=true;
            }
            BuildEntity buildEntity = buildDao.get(model.getBuildid());
            String address = buildEntity.getAddress() + buildEntity.getName();
            contractParking.setAddress(address);
            contractParking.setManager(model.getManager());
            if(CommonUtil.isNotNull(model.getUndergroundunit())){
                contractParking.setUndergroundunit(new BigDecimal(model.getUndergroundunit()));
            }
            if(CommonUtil.isNotNull(model.getUndergroundnumber())){
                contractParking.setUndergroundnumber(Integer.parseInt(model.getUndergroundnumber()));
            }
            if(CommonUtil.isNotNull(model.getSurfaceunit())){
                contractParking.setSurfaceunit(new BigDecimal(model.getSurfaceunit()));
            }
            if(CommonUtil.isNotNull(model.getSurfacenumber())){
                contractParking.setSurfacenumber(Integer.parseInt(model.getSurfacenumber()));
            }
            if(CommonUtil.isNotNull(model.getRent())){
                contractParking.setRent(new BigDecimal(model.getRent()));
            }
            if(CommonUtil.isNotNull(model.getPrepay())){
                contractParking.setPrepay(Integer.parseInt(model.getPrepay()));
            }
            if(CommonUtil.isNotNull(model.getCardfee())){
                contractParking.setCardfee(new BigDecimal(model.getCardfee()));
            }
            if(CommonUtil.isNotNull(model.getReissuecardfee())){
                contractParking.setReissuecardfee(new BigDecimal(model.getReissuecardfee()));
            }
            if(flag){
                contractParkingDao.persist(contractParking);
            }  else{
                contractParkingDao.update(contractParking);
            }
            //组织停车合同模板数据
            map = generateTcWordMap(contract, contractParking, buildEntity.getName());
        }

        if ("1,2".indexOf(model.getContype()) > -1) {
            //生成word
            String wordPath = createWordFile(contract, map);
            //生成pdf
            String pdfPath = createpdfFile(wordPath);
            //更新pdf以及word的file表
            List<ContractFile> list = contractFileDao.getContractFileByContractIdAndType(model.getId(), "1");
            if (list == null || list.size() == 0) {
                this.saveContractFile(1, wordPath, contract.getId(), model.getLoginuser());

            }
            list = contractFileDao.getContractFileByContractIdAndType(model.getId(), "2");
            if (list == null || list.size() == 0) {
                this.saveContractFile(2, pdfPath, contract.getId(), model.getLoginuser());
            }
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
        if (StringUtils.isEmpty(model.getId())) {
            return "id为空";
        }
        String[] strs = model.getId().split(",");
        for (String id : strs) {
            contractDao.deleteEntity(id, model.getLoginuser());
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

    @Override
    public void uploadFile(HttpServletRequest request, String dstFileName, BigContractModel model) {
        String filePath = WordUtils.class.getClassLoader().getResource("../../").getPath();
        filePath = filePath.substring(1, filePath.length());
        filePath = filePath + "static/upload/" + model.getId();
        //判断保存文件的路径是否存在
        File fileUploadPath = new File(filePath);
        if (!fileUploadPath.exists()) {
            fileUploadPath.mkdir();
        }

        if (ServletFileUpload.isMultipartContent(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles(dstFileName);
            for (MultipartFile item : fileList) {
                String fileName = "";        //当前上传文件全名称
                String fileType = "";        //当前上传文件类型
                String saveFileName = "";    //保存到服务器目录的文件名称
                String reportAddr = "";      //保存到服务器目录的文件全路径
                try {
                    fileName = item.getOriginalFilename();
                    fileType = item.getContentType();
                    saveFileName = DateUnit.getTime14() + "_" + fileName;
                    reportAddr = fileUploadPath + "/" + saveFileName;
                    reportAddr = reportAddr.replace("/", File.separator).replace("\\", File.separator);
                    File savedFile = new File(fileUploadPath, saveFileName);
                    item.transferTo(savedFile);
                    //上传文件成功，保存文件信息到表reportDetail
                    ContractFile contractFile = new ContractFile();
                    contractFile.setContractcode(model.getId());
                    contractFile.setFiletype(3);
                    contractFile.setFileurl(reportAddr);
                    contractFile.setDeleteflg(false);
                    contractFile.setCreateuser(model.getLoginuser().getId() + "");
                    contractFile.setCreatetime(new Date());
                    contractFileDao.persist(contractFile);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    @Override
    public HashMap<String, String> downloadFile(String id) {
        HashMap<String, String> map = new HashMap<>();
        List<ContractFile> list = contractFileDao.getContractFileByContractId(id);
        if (list == null || list.size() == 0) {
            map.put("success", "failure");
            map.put("message", "该合同不存在附件！");
            return map;
        }
        String filePath = WordUtils.class.getClassLoader().getResource("../../").getPath();
        filePath = filePath.substring(1, filePath.length());
        filePath = filePath + "static/upload/" + id;
        String zipFilePath = filePath.split("upload")[0] + "upload/";
        boolean flag = FileToZip.fileToZip(filePath, zipFilePath, id);
        if (flag) {
            map.put("success", "success");
            map.put("message", zipFilePath + id + ".zip");
            return map;
        } else {
            map.put("success", "failure");
            map.put("message", "下载失败，稍后再试！");
            return map;
        }
    }


    private String createUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }

    public Contract generateContract(BigContractModel model, Contract contract) {
        try {
            //组织合同主体信息
            BeanUtils.copyPropertiesIgnoreNull(model, contract);
            String htsj = model.getHtsj();
            contract.setTotalamount(new BigDecimal(model.getTotalamount()));
            contract.setStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(DateUnit.getDateByMMddyyyy(htsj.split("-")[0])));
            contract.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(DateUnit.getDateByMMddyyyy(htsj.split("-")[1])));
            contract.setBuildcode(model.getBuildid());
            contract.setPropertycodes(model.getPropertyid());
            contract.setPropertytext(model.getPropertyText());
            if (CommonUtil.isNull(model.getPartbcode())) {
                //生成乙方信息
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setId(createUUID());
                customerEntity.setType(CommonUtil.isNotNull(model.getPartbtype())?model.getPartbtype():"" );
                customerEntity.setAddress(CommonUtil.isNotNull(model.getPartbaddress())?model.getPartbaddress():"" );
                customerEntity.setName(CommonUtil.isNotNull(model.getPartbname())?model.getPartbname():"" );
                customerEntity.setContactname(CommonUtil.isNotNull(model.getPartblegalperson())?model.getPartblegalperson():"" );
                customerEntity.setContactnumber(CommonUtil.isNotNull(model.getPartbcontact())?model.getPartbcontact():"" );
                customerEntity.setTaxnumber(CommonUtil.isNotNull(model.getPartbtaxnumber())?model.getPartbtaxnumber():"" );
                customerEntity.setAccount(CommonUtil.isNotNull(model.getPartbaccount())?model.getPartbaccount():"" );
                customerEntity.setAccountname(CommonUtil.isNotNull( model.getPartbaccountname())? model.getPartbaccountname():"");
                customerEntity.setBankname(CommonUtil.isNotNull(model.getPartbbankname())?model.getPartbbankname():"" );
                customerEntity.setCreateuser(model.getLoginuser().getUserName());
                customerEntity.setCreatetime(DateUnit.getTime14());
                customerEntity.setDeleteflg("0");
                customerEntity.setIdtype(CommonUtil.isNotNull(model.getPartbzjzl())?model.getPartbzjzl():"" );
                customerEntity.setIdnumber(CommonUtil.isNotNull(model.getPartbzjhm())?model.getPartbzjhm():"" );
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
                contract.setPartbzjzl(customerEntity.getIdtype());
                contract.setPartbzjhm(customerEntity.getIdnumber());
            }
            //楼宇物业信息
            if (model.getContype().equals("1")) {
                //通过楼宇id和单元文字查找楼宇物业表
                PropertyEntity propertyEntity = propertyDao.getPropertyEntity(model.getBuildid(), model.getPropertyText());
                if (propertyEntity == null) {
                    propertyEntity = new PropertyEntity();
                    propertyEntity.setBuild(model.getBuildid());
                    propertyEntity.setArea(model.getBuildarea());
                    propertyEntity.setName(model.getPropertyText());
                    propertyEntity.setRent(model.getPropertyfee());
                    propertyEntity.setCreatetime(DateUnit.getTime14());
                    propertyEntity.setDeleteflg("0");
                    propertyEntity.setCreateuser(model.getLoginuser().getUserName());
                    propertyDao.persist(propertyEntity);
                }
            }
            contract.setCreatetime(new Date());
            contract.setCreateuser(model.getLoginuser().getId() + "");
            contract.setUpdatetime(new Date());
            contract.setUpdateuser(model.getLoginuser().getId() + "");
            contract.setDeleteflg(false);
            return contract;
        } catch (Exception e) {
            System.out.println("11111");
        }
        return null;
    }

    public Map<String, Object> generateWyWordMap(Contract contract, ContractProperty contractProperty) throws ParseException {
        //获得数据
        Map<String, Object> map = new HashMap<String, Object>();
        //通过firstpartyid获取甲方信息
        map.put("firstparty_name", CommonUtil.isNotNull(contract.getPartaname()) ? contract.getPartaname() : "  ");
        map.put("firstparty_address", CommonUtil.isNotNull(contract.getPartaaddress()) ? contract.getPartaaddress() : "  ");
        map.put("firstparty_linkname", CommonUtil.isNotNull(contract.getPartalegalperson()) ? contract.getPartalegalperson() : "  ");
        map.put("firstparty_linkphone", CommonUtil.isNotNull(contract.getPartancontact()) ? contract.getPartancontact() : "  ");

        //通过Customerid获取客户信息
        map.put("c_name", CommonUtil.isNotNull(contract.getPartbname()) ? contract.getPartbname() : "  ");
        map.put("c_address", CommonUtil.isNotNull(contract.getPartbaddress()) ? contract.getPartbaddress() : "  ");
        map.put("c_linkname", CommonUtil.isNotNull(contract.getPartblegalperson()) ? contract.getPartblegalperson() : "  ");
        map.put("c_phone", CommonUtil.isNotNull(contract.getPartbcontact()) ? contract.getPartbcontact() : "  ");

        //通过buildid获取房屋信息
        map.put("address", CommonUtil.isNotNull(contractProperty.getAddress()) ? contractProperty.getAddress() : "  ");
        map.put("buildarera", CommonUtil.isNotNull(contractProperty.getBuildarea()) ? contractProperty.getBuildarea() : "  ");
        String startTime = new SimpleDateFormat("YYYY-MM-dd").format(contract.getStartdate());
        String endTime = new SimpleDateFormat("YYYY-MM-dd").format(contract.getEnddate());

        map.put("time_year", DateUnit.yearsBetween(startTime, endTime));
        map.put("time", DateUnit.monthsBetween(startTime, endTime));
        map.put("starttime_year", startTime.split("-")[0]);
        map.put("starttime_month", startTime.split("-")[1]);
        map.put("starttime_day", startTime.split("-")[2]);
        map.put("endtime_year", endTime.split("-")[0]);
        map.put("endtime_month", endTime.split("-")[1]);
        map.put("endtime_day", endTime.split("-")[2]);

        map.put("tenantarea", CommonUtil.isNotNull(contractProperty.getTenantarea()) ? contractProperty.getTenantarea() : "  ");
        map.put("managefee", CommonUtil.isNotNull(contractProperty.getPropertyfee()) ? contractProperty.getPropertyfee() : "  ");

        if (CommonUtil.isNotNull(contractProperty.getPropertyfee())) {
            map.put("managefee_china", NumberToCN.number2CNMontrayUnit(contractProperty.getPropertyfee()));
        } else {
            map.put("managefee_china", "  ");
        }

        map.put("payway", CommonUtil.isNotNull(contractProperty.getPaytype()) ? contractProperty.getPaytype() : "  ");
        map.put("prodeposit", CommonUtil.isNotNull(contractProperty.getDeposit()) ? contractProperty.getDeposit() : "  ");
        if (CommonUtil.isNotNull(contractProperty.getDeposit())) {
            map.put("prodeposit_china", NumberToCN.number2CNMontrayUnit(contractProperty.getDeposit()));
        } else {
            map.put("prodeposit_china","  ");
        }
        map.put("eleprice", CommonUtil.isNotNull(contractProperty.getElectric()) ? contractProperty.getElectric() : "  ");

        map.put("firstparty_bankname", CommonUtil.isNotNull(contract.getBankname()) ? contract.getBankname() : "  ");
        map.put("firstparty_bank", CommonUtil.isNotNull(contract.getPartaaccountname()) ? contract.getPartaaccountname() : "  ");
        map.put("firstparty_dutyparagraph", CommonUtil.isNotNull(contract.getPartaaccount()) ? contract.getPartaaccount() : "  ");
        map.put("supplementaryterms", CommonUtil.isNotNull(contract.getSubsidiary()) ? contract.getSubsidiary() : "  ");

        return map;
    }

    public Map<String, Object> generateTcWordMap(Contract contract, ContractParking contractParking, String build) throws ParseException {
        //获得数据
        Map<String, Object> map = new HashMap<String, Object>();
        //通过firstpartyid获取甲方信息
        map.put("firstparty_name", CommonUtil.isNotNull(contract.getPartaname()) ? contract.getPartaname() : "  ");
        map.put("firstparty_address", CommonUtil.isNotNull(contract.getPartaaddress()) ? contract.getPartaaddress() : "  ");
        map.put("firstparty_linkname", CommonUtil.isNotNull(contract.getPartalegalperson()) ? contract.getPartalegalperson() : "  ");
        map.put("firstparty_linkphone", CommonUtil.isNotNull(contract.getPartancontact()) ? contract.getPartancontact() : "  ");

        map.put("build", build);

        //通过Customerid获取客户信息
        map.put("c_name", CommonUtil.isNotNull(contract.getPartbname()) ? contract.getPartbname() : " ");
        map.put("c_address", CommonUtil.isNotNull(contract.getPartbaddress()) ? contract.getPartbaddress() : "  ");
        map.put("c_linkname", CommonUtil.isNotNull(contract.getPartblegalperson()) ? contract.getPartblegalperson() : "  ");
        map.put("c_phone", CommonUtil.isNotNull(contract.getPartbcontact()) ? contract.getPartbcontact() : "  ");

        //通过buildid获取房屋信息
        map.put("address", CommonUtil.isNotNull(contractParking.getAddress()) ? contractParking.getAddress() : "  ");
        map.put("manager", CommonUtil.isNotNull(contractParking.getManager()) ? contractParking.getManager() : "  ");
        String startTime = new SimpleDateFormat("YYYY-MM-dd").format(contract.getStartdate());
        String endTime = new SimpleDateFormat("YYYY-MM-dd").format(contract.getEnddate());

        map.put("time_year", DateUnit.yearsBetween(startTime, endTime));
        map.put("time", DateUnit.monthsBetween(startTime, endTime));
        map.put("starttime_year", startTime.split("-")[0]);
        map.put("starttime_month", startTime.split("-")[1]);
        map.put("starttime_day", startTime.split("-")[2]);
        map.put("endtime_year", endTime.split("-")[0]);
        map.put("endtime_month", endTime.split("-")[1]);
        map.put("endtime_day", endTime.split("-")[2]);

        map.put("undergroundunit", CommonUtil.isNotNull(contractParking.getUndergroundunit()) ? contractParking.getUndergroundunit() : "  ");
        map.put("surfaceunit", CommonUtil.isNotNull(contractParking.getSurfaceunit()) ? contractParking.getSurfaceunit() : " ");
        map.put("undergroundnumber", CommonUtil.isNotNull(contractParking.getUndergroundnumber()) ? contractParking.getUndergroundnumber() + "" : "  ");
        map.put("surfacenumber", CommonUtil.isNotNull(contractParking.getSurfacenumber()) ? contractParking.getSurfacenumber() + "" : "");
        map.put("rent", CommonUtil.isNotNull(contractParking.getRent()) ? contractParking.getRent() : "  ");
        map.put("prepay", CommonUtil.isNotNull(contractParking.getPrepay()) ? contractParking.getPrepay() : " ");
        map.put("cardfee", CommonUtil.isNotNull(contractParking.getCardfee()) ? contractParking.getCardfee() : "  ");
        map.put("reissuecardfee", CommonUtil.isNotNull(contractParking.getReissuecardfee()) ? contractParking.getReissuecardfee() : "  ");
        map.put("supplementaryterms", CommonUtil.isNotNull(contract.getSubsidiary()) ? contract.getSubsidiary() : "  ");

        return map;
    }

    /**
     * 生成word文件
     *
     * @param contract
     * @param map
     * @return
     */
    public String createWordFile(Contract contract, Map<String, Object> map) {
        try {
            String compactName = "";
            String templateName = "";
            if (contract.getContype() == 1) {
                compactName = contract.getSysnumber() + "_物业管理合同";
                templateName = ConfigUtil.getConfig("wyTemplate");
            } else if (contract.getContype() == 2) {
                compactName = contract.getSysnumber() + "停车协议合同";
                templateName = ConfigUtil.getConfig("tcTemplate");
            }
            String wordUrl = WordUtils.createDoc(map, compactName, templateName);
            return wordUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成pdf文件
     *
     * @param wordPath
     * @return
     */
    public String createpdfFile(String wordPath) {
        WordToPdf.wordToPDF(wordPath, wordPath.replace("docx", "pdf"));
        return wordPath.replace("docx", "pdf");
    }

    private void saveContractFile(int type, String url, String contractcode, SysUser sysUser) {
        ContractFile contractFile = new ContractFile();
        contractFile.setId(createUUID());
        contractFile.setContractcode(contractcode);
        contractFile.setCreatetime(new Date());
        contractFile.setCreateuser(sysUser.getId() + "");
        contractFile.setDeleteflg(false);
        contractFile.setFiletype(type);
        contractFile.setFileurl(url);
        contractFileDao.persist(contractFile);
    }

    private void updateContract_edit(BigContractModel model) throws ParseException {
        String htsj = model.getHtsj();
        if (CommonUtil.isNull(model.getPartbcode())) {
            //生成乙方信息
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setId(createUUID());
            customerEntity.setType(CommonUtil.isNotNull(model.getPartbtype())?model.getPartbtype():"" );
            customerEntity.setAddress(CommonUtil.isNotNull(model.getPartbaddress())?model.getPartbaddress():"" );
            customerEntity.setName(CommonUtil.isNotNull(model.getPartbname())?model.getPartbname():"" );
            customerEntity.setContactname(CommonUtil.isNotNull(model.getPartblegalperson())?model.getPartblegalperson():"" );
            customerEntity.setContactnumber(CommonUtil.isNotNull(model.getPartbcontact())?model.getPartbcontact():"" );
            customerEntity.setTaxnumber(CommonUtil.isNotNull(model.getPartbtaxnumber())?model.getPartbtaxnumber():"" );
            customerEntity.setAccount(CommonUtil.isNotNull(model.getPartbaccount())?model.getPartbaccount():"" );
            customerEntity.setAccountname(CommonUtil.isNotNull(model.getPartbaccountname())?model.getPartbaccountname():"" );
            customerEntity.setBankname(CommonUtil.isNotNull(model.getPartbbankname())?model.getPartbbankname():"" );
            customerEntity.setCreateuser(model.getLoginuser().getUserName());
            customerEntity.setCreatetime(DateUnit.getTime14());
            customerEntity.setDeleteflg("0");
            customerEntity.setIdtype(CommonUtil.isNotNull(model.getPartbzjzl())?model.getPartbzjzl():"" );
            customerEntity.setIdnumber(CommonUtil.isNotNull(model.getPartbzjhm())?model.getPartbzjhm():"" );
            customerDao.persist(customerEntity);
            model.setPartbcode(customerEntity.getId());
        }
        //楼宇物业信息
        if (model.getContype().equals("1")) {
            //通过楼宇id和单元文字查找楼宇物业表
            PropertyEntity propertyEntity = propertyDao.getPropertyEntity(model.getBuildid(), model.getPropertyText());
            if (propertyEntity == null) {
                propertyEntity = new PropertyEntity();
                propertyEntity.setBuild(model.getBuildid());
                propertyEntity.setArea(model.getBuildarea());
                propertyEntity.setName(model.getPropertyText());
                propertyEntity.setRent(model.getPropertyfee());
                propertyEntity.setCreatetime(DateUnit.getTime14());
                propertyEntity.setDeleteflg("0");
                propertyEntity.setCreateuser(model.getLoginuser().getUserName());
                propertyDao.persist(propertyEntity);
            }
        }
        Contract contract = contractDao.get(model.getId());
        BeanUtils.copyPropertiesIgnoreNull(model, contract);
        contract.setTotalamount(new BigDecimal(model.getTotalamount()));
        contract.setStartdate(new SimpleDateFormat("yyyy-MM-dd").parse(DateUnit.getDateByMMddyyyy(htsj.split("-")[0])));
        contract.setEnddate(new SimpleDateFormat("yyyy-MM-dd").parse(DateUnit.getDateByMMddyyyy(htsj.split("-")[1])));
        contract.setBuildcode(model.getBuildid());
        contract.setPropertycodes(model.getPropertyid());
        contract.setUpdatetime(new Date());
        contract.setUpdateuser(model.getLoginuser().getId() + "");
        contract.setPropertytext(model.getPropertyText());
        contractDao.update(contract);
    }
}
