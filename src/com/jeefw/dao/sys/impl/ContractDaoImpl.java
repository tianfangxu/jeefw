package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.ContractDao;
import com.jeefw.model.recode.param.ExportParkingDaoModel;
import com.jeefw.model.recode.param.ExportPropertyDaoModel;
import com.jeefw.model.recode.param.ExportPropertyRespModel;
import com.jeefw.model.sys.Contract;
import com.jeefw.model.sys.Role;
import com.jeefw.model.sys.SysUser;
import com.jeefw.model.sys.param.model.BigContractModel;
import com.jeefw.model.sys.param.model.SmallContractModel;

import core.dao.BaseDao;
import core.support.JqGridPageView;
import core.util.ConfigUtil;
import core.util.DateUnit;
import core.util.StringUnit;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 合同主体信息的数据持久层的实现类
 * @JC
 */
@Repository
public class ContractDaoImpl extends BaseDao<Contract> implements ContractDao {

	public ContractDaoImpl() {
		super(Contract.class);
	}


	@Override
	public String getMaxNumber(String number) {
		Query query = this.getSession().createSQLQuery("select sysnumber from t_contract where sysnumber like '"+number+"%' order by sysnumber desc");
		List<String> numberList =  query.list();
		if(numberList!=null&&numberList.size()>0){
			 return numberList.get(0);
		}
		return "";
	}

	@Override
	public JqGridPageView<Contract> getContractByCondition(BigContractModel model) {
		JqGridPageView<Contract> result = new JqGridPageView<Contract>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where deleteflg = '0' ");
		//等于查询
		if(model.getEqparam() != null){
			BigContractModel eqmodel = (BigContractModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getPartbname())){
				sb.append(" and partbname = '"+eqmodel.getPartbname()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getContype())){
				sb.append(" and contype = '"+eqmodel.getContype()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getSysnumber())){
				sb.append(" and sysnumber = '"+eqmodel.getSysnumber()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getId())){
				sb.append(" and id = '"+eqmodel.getId()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getPartbcode())){
				sb.append(" and partbcode = '"+eqmodel.getPartbcode()+"' ");
			}
		}
		//like查询
		if(model.getLikeparam() != null){
			BigContractModel likemodel = (BigContractModel) model.getLikeparam();
			if(!StringUnit.isNullOrEmpty(likemodel.getPartbname())){
				sb.append(" and partbname like '%"+likemodel.getPartbname()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getSysnumber())){
				sb.append(" and sysnumber like '%"+likemodel.getSysnumber()+"%' ");
			}
		}

		String roleKeyStr = "";
		Set<Role> set =  model.getLoginuser().getRoles();
		Iterator<Role> it = set.iterator();
		while(it.hasNext()){
			Role role = it.next();
			roleKeyStr += role.getRoleKey()+",";
		}
		try{
			if(roleKeyStr.indexOf(ConfigUtil.getConfig("kf"))>-1){
				sb.append(" and contype in ('1','3') ");
			}else if(roleKeyStr.indexOf(ConfigUtil.getConfig("aq"))>-1){
				sb.append(" and contype in ('2','3') ");
			}
		} catch(FileNotFoundException e){

		}


		Query query = session.createQuery("from Contract "
				+ sb.toString());
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));

		List<Contract> list = query.list();

		Object cout = session.createSQLQuery(
				"select count(1) from t_contract " + sb.toString())
				.uniqueResult();
		long count = Long.parseLong(cout.toString());

		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setTotalNumber((int)count);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}

	@Override
	public void deleteEntity(String id, SysUser sysUser) {
		Query query = this.getSession().createSQLQuery(
				"update t_contract set deleteflg = '1' ,deleteuser = '"+sysUser.getId()+"',deletetime = '"+ DateUnit.getTime14()+"',updateuser = '"+sysUser.getId()+"',updatetime = '"+DateUnit.getTime14()+"'  where id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public JqGridPageView<BigContractModel> getContractWithInfoById(BigContractModel model) {
		JqGridPageView<BigContractModel> result = new JqGridPageView<BigContractModel>();
		Session session = this.getSession();
		StringBuffer columns = new StringBuffer(" a.id,a.startdate,a.enddate,a.contype,a.totalamount,a.partacode,a.partaname,");
		columns.append(" a.partaaddress,a.partalegalperson,a.partancontact,a.partaaccount,a.partaaccountname,a.bankname,a.partataxnumber,");
		columns.append(" a.partbcode,a.partbtype,a.partbname,a.partbaddress,a.partblegalperson,a.partbcontact,a.partbtaxnumber,a.subsidiary,");

		columns.append(" b.id parkingid,b.address parkingaddress,b.manager,b.undergroundunit,b.undergroundnumber,b.surfaceunit,b.surfacenumber,b.rent,b.prepay,b.cardfee,b.reissuecardfee,");
		columns.append(" c.id propertyid,c.address propertyaddress,c.tenantarea,c.buildarea,c.propertyfee,c.paytype,c.deposit,c.electric,a.buildcode buildid,a.propertytext propertyids,c.paytypecode,a.partbaccount,a.partbaccountname,a.partbbankname,");
		columns.append(" a.othercontype othercontype,a.otherpaytype otherpaytype");
		StringBuffer sb = new StringBuffer(" select "+columns.toString()+" from t_contract a left join t_contract_property c on a.id = c.contractcode  left join t_contract_parking b on a.id =b.contractcode  where a.id = '"+model.getId()+"'");
		Query query =  session.createSQLQuery(sb.toString()).addScalar("id", StandardBasicTypes.STRING)
				.addScalar("startdate",StandardBasicTypes.DATE).addScalar("enddate",StandardBasicTypes.DATE)
				.addScalar("contype",StandardBasicTypes.STRING).addScalar("totalamount",StandardBasicTypes.STRING)
				.addScalar("partacode",StandardBasicTypes.STRING).addScalar("partaname",StandardBasicTypes.STRING)
				.addScalar("partaaddress",StandardBasicTypes.STRING).addScalar("partalegalperson",StandardBasicTypes.STRING)
				.addScalar("partancontact",StandardBasicTypes.STRING).addScalar("partaaccount",StandardBasicTypes.STRING)
				.addScalar("partaaccountname",StandardBasicTypes.STRING).addScalar("bankname",StandardBasicTypes.STRING)
				.addScalar("partataxnumber",StandardBasicTypes.STRING).addScalar("partbcode",StandardBasicTypes.STRING)
				.addScalar("partbtype",StandardBasicTypes.STRING).addScalar("partbname",StandardBasicTypes.STRING)
				.addScalar("partbaddress",StandardBasicTypes.STRING).addScalar("partblegalperson",StandardBasicTypes.STRING)
				.addScalar("partbcontact",StandardBasicTypes.STRING).addScalar("partbtaxnumber",StandardBasicTypes.STRING)
				.addScalar("subsidiary",StandardBasicTypes.STRING).addScalar("parkingid",StandardBasicTypes.STRING)
				.addScalar("parkingaddress",StandardBasicTypes.STRING).addScalar("manager",StandardBasicTypes.STRING)
				.addScalar("undergroundunit",StandardBasicTypes.STRING).addScalar("undergroundnumber",StandardBasicTypes.STRING)
				.addScalar("surfaceunit",StandardBasicTypes.STRING).addScalar("surfacenumber",StandardBasicTypes.STRING)
				.addScalar("rent",StandardBasicTypes.STRING).addScalar("prepay",StandardBasicTypes.STRING)
				.addScalar("cardfee",StandardBasicTypes.STRING).addScalar("reissuecardfee",StandardBasicTypes.STRING)
				.addScalar("propertyid",StandardBasicTypes.STRING).addScalar("propertyaddress",StandardBasicTypes.STRING)
				.addScalar("tenantarea",StandardBasicTypes.STRING).addScalar("buildarea",StandardBasicTypes.STRING)
				.addScalar("propertyfee",StandardBasicTypes.STRING).addScalar("paytype",StandardBasicTypes.STRING)
				.addScalar("deposit",StandardBasicTypes.STRING).addScalar("electric",StandardBasicTypes.STRING)
				.addScalar("buildid",StandardBasicTypes.STRING).addScalar("propertyids",StandardBasicTypes.STRING)
				.addScalar("paytypecode",StandardBasicTypes.STRING).addScalar("partbaccount",StandardBasicTypes.STRING)
				.addScalar("partbaccountname",StandardBasicTypes.STRING).addScalar("partbbankname",StandardBasicTypes.STRING)
				.addScalar("othercontype",StandardBasicTypes.STRING).addScalar("otherpaytype",StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(BigContractModel.class));
		 List<BigContractModel> list = query.list();
		result.setRows(list);
		return result;
	}

	@Override
	public JqGridPageView<SmallContractModel> getContractByAudit(BigContractModel model) {
		JqGridPageView<SmallContractModel> result = new JqGridPageView<SmallContractModel>();
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" where a.deleteflg = '0' ");
		//等于查询
		if(model.getEqparam() != null){
			BigContractModel eqmodel = (BigContractModel) model.getEqparam();
			if(!StringUnit.isNullOrEmpty(eqmodel.getPartbname())){
				sb.append(" and a.partbname = '"+eqmodel.getPartbname()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getContype())){
				sb.append(" and a.contype = '"+eqmodel.getContype()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getSysnumber())){
				sb.append(" and a.sysnumber = '"+eqmodel.getSysnumber()+"' ");
			}
			if(!StringUnit.isNullOrEmpty(eqmodel.getId())){
				sb.append(" and a.id = '"+eqmodel.getId()+"' ");
			}
		}
		//like查询
		if(model.getLikeparam() != null){
			BigContractModel likemodel = (BigContractModel) model.getLikeparam();
			if(!StringUnit.isNullOrEmpty(likemodel.getPartbname())){
				sb.append(" and a.partbname like '%"+likemodel.getPartbname()+"%' ");
			}
			if(!StringUnit.isNullOrEmpty(likemodel.getSysnumber())){
				sb.append(" and a.sysnumber like '%"+likemodel.getSysnumber()+"%' ");
			}
		}
		String roleKeyStr = "";
		Set<Role> set =  model.getLoginuser().getRoles();
		Iterator<Role> it = set.iterator();
		while(it.hasNext()){
			Role role = it.next();
			roleKeyStr += "'"+role.getRoleKey()+"',";
		}
		if(!roleKeyStr.equals("")){
			roleKeyStr = "("+roleKeyStr.substring(0,roleKeyStr.length()-1)+")";
		}
		if(model.getSelectState().equals("1")){
			//查看等待客服或者安全人员处理的单子
			sb.append(" and  (b.nexthandler in "+roleKeyStr+" or b.nexthandler = '"+model.getLoginuser().getId()+"' )");
		}else if(model.getSelectState().equals("2")){
			sb.append(" and a.auditstate = 2 and  (a.dealusers like '%,"+model.getLoginuser().getId()+"%' or a.dealusers like '%"+model.getLoginuser().getId()+",%' )");
		}else if(model.getSelectState().equals("3")){
			sb.append(" and a.auditstate = 3 and (a.dealusers like '%,"+model.getLoginuser().getId()+"%' or a.dealusers like '%"+model.getLoginuser().getId()+",%' )");
		}
		Query query = session.createSQLQuery(" select a.id,a.sysnumber,a.partaname,a.partbname,a.contype ,a.partbcontact,a.startdate,a.enddate,a.auditstate from t_contract a ,t_contract_flow b " + sb.toString()+" and a.id = b.contractcode order by a.createtime desc ")
				.addScalar("id",StandardBasicTypes.STRING).addScalar("sysnumber",StandardBasicTypes.STRING)
				.addScalar("partaname",StandardBasicTypes.STRING).addScalar("partbname",StandardBasicTypes.STRING)
				.addScalar("contype",StandardBasicTypes.INTEGER).addScalar("partbcontact",StandardBasicTypes.STRING)
				.addScalar("startdate",StandardBasicTypes.DATE).addScalar("enddate",StandardBasicTypes.DATE)
				.addScalar("auditstate",StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(SmallContractModel .class));
		query.setFirstResult((Integer.parseInt(model.getPage()) - 1)
				* Integer.parseInt(model.getRows()));
		query.setMaxResults(Integer.parseInt(model.getRows()));

		List<SmallContractModel> list = query.list();

		Object cout = session.createSQLQuery(" select count(1) from t_contract a ,t_contract_flow b " + sb.toString()+" and  a.id = b.contractcode  ").uniqueResult();
		long count = Long.parseLong(cout.toString());
		result.setRows(list);
		result.setTotal(count/Integer.parseInt(model.getRows())+1);
		result.setTotalNumber((int)count);
		result.setCurrentPage(Integer.parseInt(model.getPage()));
		return result;
	}


	/***
	 * 物业费收入
	 */
	@Override
	public List<ExportPropertyDaoModel> getExportInfo(
			ExportPropertyRespModel model) {
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" c.deleteflg = '0' ");
		if(!StringUnit.isNullOrEmpty(model.getBuildcode())){
			sb.append(" and c.buildcode = '"+model.getBuildcode()+"' ");
		}else{
			return null;
		}
		if(!StringUnit.isNullOrEmpty(model.getYear())){
			sb.append(" and (left(c.startdate,4)+0) <= "+model.getYear()+" and (left(c.enddate,4)+0) >= "+model.getYear());
		}
		String sql = 	"select cp.tenantarea,cp.buildarea,cp.propertyfee,cp.paytype,cp.electric,"+
						"c.sysnumber,c.startdate,c.enddate,c.partaname,c.partbname,c.subsidiary,c.buildcode,c.propertytext "+
						"from t_contract_property cp LEFT JOIN t_contract c  on c.id = cp.contractcode "+
						"where c.auditstate ='3' and c.contype = '1' and "+ sb.toString();
		Query query = session.createSQLQuery(sql)
				.addScalar("tenantarea",StandardBasicTypes.STRING)
				.addScalar("buildarea",StandardBasicTypes.STRING)
				.addScalar("propertyfee",StandardBasicTypes.STRING)
				.addScalar("paytype",StandardBasicTypes.STRING)
				.addScalar("electric",StandardBasicTypes.STRING)
				.addScalar("sysnumber",StandardBasicTypes.STRING)
				.addScalar("startdate",StandardBasicTypes.STRING)
				.addScalar("enddate",StandardBasicTypes.STRING)
				.addScalar("partaname",StandardBasicTypes.STRING)
				.addScalar("partbname",StandardBasicTypes.STRING)
				.addScalar("subsidiary",StandardBasicTypes.STRING)
				.addScalar("buildcode",StandardBasicTypes.STRING)
				.addScalar("propertytext",StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(ExportPropertyDaoModel.class));
		List<ExportPropertyDaoModel> list = query.list();
		return list;
	}


	@Override
	public List<ExportParkingDaoModel> getExportCarInfo(
			ExportPropertyRespModel model) {
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer(" c.deleteflg = '0' ");
		if(!StringUnit.isNullOrEmpty(model.getBuildcode())){
			sb.append(" and c.buildcode = '"+model.getBuildcode()+"' ");
		}else{
			return null;
		}
		if(!StringUnit.isNullOrEmpty(model.getYear())){
			sb.append(" and (left(c.startdate,4)+0) <= "+model.getYear()+" and (left(c.enddate,4)+0) >= "+model.getYear());
		}
		String sql = 	"select cp.undergroundunit,cp.undergroundnumber,cp.surfaceunit,cp.surfacenumber,cp.rent,cp.prepay,cp.cardfee,cp.reissuecardfee,"+
						"c.sysnumber,c.startdate,c.enddate,c.partaname,c.partbname,c.subsidiary,c.buildcode,c.propertytext "+
						"from t_contract_parking cp LEFT JOIN t_contract c  on c.id = cp.contractcode "+
						"where c.auditstate ='3' and c.contype = '2' and "+ sb.toString();
		Query query = session.createSQLQuery(sql)
				.addScalar("undergroundunit",StandardBasicTypes.STRING)
				.addScalar("undergroundnumber",StandardBasicTypes.STRING)
				.addScalar("surfaceunit",StandardBasicTypes.STRING)
				.addScalar("surfacenumber",StandardBasicTypes.STRING)
				.addScalar("rent",StandardBasicTypes.STRING)
				.addScalar("prepay",StandardBasicTypes.STRING)
				.addScalar("cardfee",StandardBasicTypes.STRING)
				.addScalar("reissuecardfee",StandardBasicTypes.STRING)
				
				.addScalar("sysnumber",StandardBasicTypes.STRING)
				.addScalar("startdate",StandardBasicTypes.STRING)
				.addScalar("enddate",StandardBasicTypes.STRING)
				.addScalar("partaname",StandardBasicTypes.STRING)
				.addScalar("partbname",StandardBasicTypes.STRING)
				.addScalar("subsidiary",StandardBasicTypes.STRING)
				.addScalar("buildcode",StandardBasicTypes.STRING)
				.addScalar("propertytext",StandardBasicTypes.STRING)
				.setResultTransformer(Transformers.aliasToBean(ExportParkingDaoModel.class));
		List<ExportParkingDaoModel> list = query.list();
		return list;
	}
	
	
}
