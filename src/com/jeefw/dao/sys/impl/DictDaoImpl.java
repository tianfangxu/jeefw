package com.jeefw.dao.sys.impl;

import com.jeefw.model.sys.param.DicModel;
import core.support.JqGridPageView;
import core.util.StringUnit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeefw.dao.sys.DictDao;
import com.jeefw.model.sys.Dict;

import core.dao.BaseDao;

import java.util.List;

/**
 * 字典的数据持久层的实现类
 * @JC
 */
@Repository
public class DictDaoImpl extends BaseDao<Dict> implements DictDao {

	public DictDaoImpl() {
		super(Dict.class);
	}

	@Override
	public JqGridPageView<Dict> getDicByCondition(DicModel model) {
		JqGridPageView<Dict> result = new JqGridPageView<Dict>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer("where 1=1 ");

		//等于查询
		if(model.getEqparam() != null){
			DicModel eqmodel = (DicModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getDictKey())){
				sb.append(" and dictkey = '"+eqmodel.getDictKey()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getDictValue())){
				sb.append(" and dicValue = '"+eqmodel.getDictValue()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getId())){
				sb.append(" and id = '"+eqmodel.getId()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getParentDictkey())){
				sb.append(" and parentDictkey = '"+eqmodel.getParentDictkey()+"' ");
			}
		}
		//like查询
		if(model.getLikeparam() != null){
			DicModel likemodel = (DicModel) model.getLikeparam();
			if(!StringUnit.isNullOrEmpty(likemodel.getDictKey())){
				sb.append(" and dictkey like '%"+likemodel.getDictKey()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getDictValue())){
				sb.append(" and dicValue like '%"+likemodel.getDictValue()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getParentDictkey())){
				sb.append(" and parentDictkey like '%"+likemodel.getParentDictkey()+"%' ");
			}
		}

		Query query = session.createQuery("from Dict "
				+ sb.toString());
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));

		List<Dict> list = query.list();

		Object cout = session.createQuery(
				"from Dict " + sb.toString())
				.list().size();
		long count = Long.parseLong(cout.toString());
		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setTotalNumber((int)count);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}

}
