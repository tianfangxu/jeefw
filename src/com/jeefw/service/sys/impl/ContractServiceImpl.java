package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.ContractDao;
import com.jeefw.dao.sys.ContractFileDao;
import com.jeefw.dao.sys.ContractPropertyDao;
import com.jeefw.model.sys.Contract;
import com.jeefw.service.sys.ContractService;
import core.service.BaseService;
import core.util.DateUnit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 合同主体信息的业务逻辑层的实现
 * @JC
 */
@Service
public class ContractServiceImpl extends BaseService<Contract> implements ContractService {
	private ContractDao contractDao;
	private ContractPropertyDao contractPropertyDao;
	private ContractFileDao contractFileDao;

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

}
