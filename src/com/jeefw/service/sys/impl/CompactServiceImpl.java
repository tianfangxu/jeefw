package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.CompactDao;
import com.jeefw.model.sys.Compact;
import com.jeefw.service.sys.CompactService;
import core.service.BaseService;
import core.util.DateUnit;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * 合同的业务逻辑层的实现
 * @JC
 */
@Service
public class CompactServiceImpl extends BaseService<Compact> implements CompactService {
	private CompactDao compactDao;

	@Resource
	public void setContractDao(CompactDao compactDao) {
		this.compactDao = compactDao;
		this.dao = compactDao;
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
		String maxNumber = compactDao.getMaxNumber(selectNumber);
		if(maxNumber.equals("")){
			return selectNumber+"0001";
		}else{
			return selectNumber+(1+Integer.parseInt(maxNumber.split(selectNumber)[1]));
		}
	}

}
