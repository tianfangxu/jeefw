package com.jeefw.dao.recode.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.util.StringUtils;
import com.jeefw.dao.recode.FirstpartyContractDao;
import com.jeefw.model.recode.firstpartyContractEntity;
import com.jeefw.model.recode.param.firstpartyContractModel;

import core.dao.BaseDao;
import core.support.JqGridPageView;

@Repository
public class FirstpartyContractDaoImpl extends
		BaseDao<firstpartyContractEntity> implements FirstpartyContractDao {

	public FirstpartyContractDaoImpl() {
		super(firstpartyContractEntity.class);
	}

	@Override
	public JqGridPageView<firstpartyContractEntity> getfirstpartyContractByCondition(
			firstpartyContractModel model) {
		JqGridPageView<firstpartyContractEntity> result = new JqGridPageView<firstpartyContractEntity>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		if (!StringUtils.isEmpty(model.getVflag())) {
			sb.append(" and flag = '" + model.getVflag() + "' ");
		} else {
			sb.append(" and flag = '0' ");
		}
		if (session == null) {
			System.out.println(11);
		}
		Query query = session.createQuery("from firstpartyContractEntity "
				+ sb.toString());
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));
		List<firstpartyContractEntity> list = query.list();

		Object cout = session.createSQLQuery(
				"select count(1) from m_firstparty_contract " + sb.toString())
				.uniqueResult();
		long count = Long.parseLong(cout.toString());

		result.setRows(list);
		result.setTotal(count);
		return result;
	}

	@Override
	public void savedata(firstpartyContractEntity entity) {
		Query query = this.getSession().createSQLQuery(
				"insert into m_firstparty_contract values " + "(?,?,?,?,?,?,?,?,?,?,?,?)");
		query.setParameter(0, UUID.randomUUID().toString().replaceAll("-", ""));
		query.setParameter(1, entity.getNamea());
		query.setParameter(2, entity.getAddress());
		query.setParameter(3, entity.getLinkname());
		query.setParameter(4, entity.getLinkphone());
		query.setParameter(5, entity.getDutyparagraph());
		query.setParameter(6, entity.getBank());
		query.setParameter(7, entity.getBankname());
		query.setParameter(8, entity.getBanknumber());
		query.setParameter(9, entity.getCreatetime());
		query.setParameter(10, entity.getFlag());
		query.setParameter(11, "0");
		query.executeUpdate();

	}

	@Override
	public void deletefirstpartyContract(String id) {
		Query query = this.getSession().createSQLQuery(
				"update m_firstparty_contract set flag = '1' where id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public void updatadata(firstpartyContractEntity entity) {
		Query query = this
				.getSession()
				.createSQLQuery(
						"update m_firstparty_contract set namea=?,address=?,"
								+ "linkname=?,linkphone=?,dutyparagraph=?,bank=?,bankname=?,banknumber=? where id = ?");
		query.setParameter(0, entity.getNamea());
		query.setParameter(1, entity.getAddress());
		query.setParameter(2, entity.getLinkname());
		query.setParameter(3, entity.getLinkphone());
		query.setParameter(4, entity.getDutyparagraph());
		query.setParameter(5, entity.getBank());
		query.setParameter(6, entity.getBankname());
		query.setParameter(7, entity.getBanknumber());
		query.setParameter(8, entity.getId());
		query.executeUpdate();
	}

}
