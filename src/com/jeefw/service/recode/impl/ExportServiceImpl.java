package com.jeefw.service.recode.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeefw.dao.sys.ContractDao;
import com.jeefw.model.recode.param.EnterPropertyModel;
import com.jeefw.model.recode.param.ExportPropertyDaoModel;
import com.jeefw.model.recode.param.ExportPropertyRespModel;
import com.jeefw.service.recode.ExportService;

import core.support.JqGridPageView;
import core.util.ArithmeticUtil;

@Service
@Transactional
public class ExportServiceImpl implements ExportService {
	
	@Resource
	ContractDao contractDao;
	
	/**
	 * 物业报表
	 * @throws Exception 
	 */
	@Override
	public JqGridPageView<EnterPropertyModel> getExportInfo(ExportPropertyRespModel model) throws Exception {
		JqGridPageView<EnterPropertyModel> result = new JqGridPageView<EnterPropertyModel>();
		List<ExportPropertyDaoModel> list = contractDao.getExportInfo(model);
		if(list == null || list.size() == 0){
			result.setRows(null);
			result.setTotal(0);
			return result;
		}
		ArrayList<EnterPropertyModel> rows = new ArrayList<EnterPropertyModel>();
		//处理每个合同的物业费，分月计算
		for (ExportPropertyDaoModel exportPropertyDaoModel : list) {
			try {
				rows.add(dealcontractByMonth(exportPropertyDaoModel,model.getYear()));
			} catch (Exception e) {
				throw new Exception("数据异常！");
			}
		}
		result.setRows(rows);
		result.setTotal(1);
		result.setTotalNumber(list.size());
		result.setCurrentPage(1);
		return result;
	}

	private EnterPropertyModel dealcontractByMonth(ExportPropertyDaoModel res,String paramyear) throws Exception {
		EnterPropertyModel model = new EnterPropertyModel();
		model.setArea(res.getTenantarea());
		model.setRoomnum(res.getPropertytext());
		model.setName(res.getPartbname());
		model.setDeadline(res.getEnddate());
		model.setType(res.getPaytype());
		model.setRemarks(res.getSubsidiary());
		String start = res.getStartdate(); //2018-12-08
		String end = res.getEnddate(); //2020-03-13
		int year = Integer.parseInt(paramyear);
		
		String[] ss = start.split("-");
		int sy = Integer.parseInt(ss[0]);
		int sm = Integer.parseInt(ss[1]);
		int sd = Integer.parseInt(ss[2]);
		String[] es = end.split("-");
		int ey = Integer.parseInt(es[0]);
		int em = Integer.parseInt(es[1]);
		int ed = Integer.parseInt(es[2]);
		
		if(year > sy && year < ey){
			//合同期限包括整年
			Class clazz = model.getClass();
			for (int i = 1; i < 13; i++) {
				Method method = clazz.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model, res.getPropertyfee());
			}
			model.setReceipt0(ArithmeticUtil.multiply(res.getPropertyfee(), "12"));
		}
		if(year == sy && year < ey){
			//合同期限在当年，并且在年后
			Class clazz = model.getClass();
			for(int i = sm+1 ; i < 13; i++){
				Method method = clazz.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model, res.getPropertyfee());
			}
			
			//开始当月
			Calendar c = Calendar.getInstance();
			c.set(sy, sm, 0); //输入类型为int类型
			int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
			int ts = dayOfMonth - sd;//当前应算多少天的费用
			String multiply = ArithmeticUtil.multiply(ArithmeticUtil.divide(ts+"", dayOfMonth+""),res.getPropertyfee());
			Method method = clazz.getDeclaredMethod("setReceipt"+sm+"", String.class);
			method.invoke(model, multiply);
		}
		if(sy == ey){
			//合同期限在当年，并且在年前
			Class clazz = model.getClass();
			for(int i = sm+1 ; i < em; i++){
				Method method = clazz.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model, res.getPropertyfee());
			}
			
			//开始当月
			Calendar c = Calendar.getInstance();
			c.set(sy, sm, 0); //输入类型为int类型
			int daystart = c.get(Calendar.DAY_OF_MONTH);
			int ts = daystart - sd;//当前应算多少天的费用
			String multiply = ArithmeticUtil.multiply(ArithmeticUtil.divide(ts+"", daystart+""),res.getPropertyfee());
			Method method = clazz.getDeclaredMethod("setReceipt"+sm+"", String.class);
			method.invoke(model, multiply);
			
			//结束当月
			c.set(ey, em, 0); //输入类型为int类型
			int dayend = c.get(Calendar.DAY_OF_MONTH);//当前应算多少天的费用
			String multiply2 = ArithmeticUtil.multiply(ArithmeticUtil.divide(ed+"",dayend+""),res.getPropertyfee());
			Method method2 = clazz.getDeclaredMethod("setReceipt"+em+"", String.class);
			method2.invoke(model, multiply2);
		}
		
		return model;
	}

}
