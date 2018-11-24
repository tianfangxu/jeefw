package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.CompactDao;
import com.jeefw.model.sys.Compact;
import core.dao.BaseDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 合同的数据持久层的实现类
 * @JC
 */
@Repository
public class CompactDaoImpl extends BaseDao<Compact> implements CompactDao {

	public CompactDaoImpl() {
		super(Compact.class);
	}


	@Override
	public String getMaxNumber(String number) {
		Query query = this.getSession().createSQLQuery("select number from m_compact where number like '"+number+"%' order by number desc");
		List<String> numberList =  query.list();
		if(numberList!=null&&numberList.size()>0){
			 return numberList.get(0);
		}
		return "";
	}
}
