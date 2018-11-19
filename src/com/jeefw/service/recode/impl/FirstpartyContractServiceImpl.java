package com.jeefw.service.recode.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jeefw.dao.recode.FirstpartyContractDao;
import com.jeefw.model.recode.BaseEntity;
import com.jeefw.model.recode.firstpartyContractEntity;
import com.jeefw.model.recode.param.firstpartyContractModel;
import com.jeefw.service.recode.FirstpartyContractService;

import core.support.JqGridPageView;

/**
 * 甲方合同主体
 * @author Administrator
 * @param <V>
 *
 */

@Service
public class FirstpartyContractServiceImpl<V> implements FirstpartyContractService{

	@Resource
	FirstpartyContractDao firstpartyContractDao;

	@Override
	public JqGridPageView<firstpartyContractEntity> getfirstpartyContractByCondition(
			firstpartyContractModel model) {
		return firstpartyContractDao.getfirstpartyContractByCondition(model);
	}

	@Override
	public void savefirstpartyContract(firstpartyContractModel model) {
		firstpartyContractEntity entity = new firstpartyContractEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setFlag("0");
		entity.setCreatetime(BaseEntity.gettime());
		firstpartyContractDao.savedata(entity);
	}

	@Override
	public String updfirstpartyContract(firstpartyContractModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		firstpartyContractEntity entity = firstpartyContractDao.get(model.getId());
		BeanUtils.copyProperties(model, entity);
		firstpartyContractDao.updatadata(entity);
		return "修改成功";
	}

	@Override
	public String deletefirstpartyContract(firstpartyContractModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		firstpartyContractDao.deletefirstpartyContract(model.getId());
		return "删除成功";
	}

}
