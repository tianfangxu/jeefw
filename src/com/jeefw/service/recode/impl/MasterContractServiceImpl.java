package com.jeefw.service.recode.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeefw.dao.recode.MasterContractDao;
import com.jeefw.model.recode.BaseEntity;
import com.jeefw.model.recode.MasterEntity;
import com.jeefw.model.recode.param.MasterModel;
import com.jeefw.service.recode.MasterContractService;

import core.support.JqGridPageView;
import core.util.DateUnit;
import core.util.MD5;

/**
 * 甲方合同主体
 * @author Administrator
 * @param <V>
 *
 */

@Service
@Transactional
public class MasterContractServiceImpl<V> implements MasterContractService{

	@Resource
	MasterContractDao firstpartyContractDao;

	@Override
	public JqGridPageView<MasterEntity> getfirstpartyContractByCondition(
			MasterModel model) {
		return firstpartyContractDao.getfirstpartyContractByCondition(model);
	}

	@Override
	public void savefirstpartyContract(MasterModel model) {
		MasterEntity entity = new MasterEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setDeleteflg("0");
		entity.setCreatetime(DateUnit.getTime14());
		entity.setCreateuser(model.getLoginuser().getId().toString());
		firstpartyContractDao.persist(entity);
		
	}

	@Override
	public String updfirstpartyContract(MasterModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		MasterEntity entity = firstpartyContractDao.get(model.getId());
		if(entity != null){
			model.setDeleteflg(entity.getDeleteflg());
			model.setCreatetime(entity.getCreatetime());
			model.setCreateuser(entity.getCreateuser());
			BeanUtils.copyProperties(model, entity);
			entity.setUpdatetime(DateUnit.getTime14());
			entity.setUpdateuser(model.getLoginuser().getId().toString());
			firstpartyContractDao.merge(entity);
			return "修改成功";
		}
		return "未查询到数据";
	}

	@Override
	public String deletefirstpartyContract(MasterModel model) {
		if(StringUtils.isEmpty(model.getId())){
			return "id为空";
		}
		if(model.getId().indexOf(",") != -1){
			String[] strs = model.getId().split(",");
			for (String id : strs) {
				firstpartyContractDao.deletefirstpartyContract(id,model.getLoginuser().getId().toString());
			}
		}else{
			firstpartyContractDao.deletefirstpartyContract(model.getId(),model.getLoginuser().getId().toString());
		}
		return "删除成功";
	}

}
