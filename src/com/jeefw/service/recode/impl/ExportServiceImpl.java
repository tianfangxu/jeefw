package com.jeefw.service.recode.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeefw.dao.sys.ContractDao;
import com.jeefw.model.recode.param.EnterPropertyModel;
import com.jeefw.model.recode.param.ExportParkingDaoModel;
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
			String sum = "0";
			//合同期限在当年，并且在年后
			Class clazz = model.getClass();
			for(int i = sm+1 ; i < 13; i++){
				Method method = clazz.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model, res.getPropertyfee());
				sum = ArithmeticUtil.add(sum,res.getPropertyfee());
			}
			
			//开始当月
			Calendar c = Calendar.getInstance();
			c.set(sy, sm, 0); //输入类型为int类型
			int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
			int ts = dayOfMonth - sd;//当前应算多少天的费用
			String multiply = ArithmeticUtil.multiply(ArithmeticUtil.divide(ts+"", dayOfMonth+""),res.getPropertyfee());
			Method method = clazz.getDeclaredMethod("setReceipt"+sm+"", String.class);
			method.invoke(model, multiply);
			
			sum = ArithmeticUtil.add(sum,multiply);
			model.setReceipt0(sum);
		}
		if(sy == ey){
			String sum = "0";
			//合同期限在当年，并且在年前
			Class clazz = model.getClass();
			for(int i = sm+1 ; i < em; i++){
				Method method = clazz.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model, res.getPropertyfee());
				sum = ArithmeticUtil.add(sum,res.getPropertyfee());
			}
			
			//开始当月
			Calendar c = Calendar.getInstance();
			c.set(sy, sm, 0); //输入类型为int类型
			int daystart = c.get(Calendar.DAY_OF_MONTH);
			int ts = daystart - sd;//当前应算多少天的费用
			String multiply = ArithmeticUtil.multiply(ArithmeticUtil.divide(ts+"", daystart+""),res.getPropertyfee());
			Method method = clazz.getDeclaredMethod("setReceipt"+sm+"", String.class);
			method.invoke(model, multiply);
			sum = ArithmeticUtil.add(sum,multiply);
			
			//结束当月
			c.set(ey, em, 0); //输入类型为int类型
			int dayend = c.get(Calendar.DAY_OF_MONTH);//当前应算多少天的费用
			String multiply2 = ArithmeticUtil.multiply(ArithmeticUtil.divide(ed+"",dayend+""),res.getPropertyfee());
			Method method2 = clazz.getDeclaredMethod("setReceipt"+em+"", String.class);
			method2.invoke(model, multiply2);
			sum = ArithmeticUtil.add(sum,multiply2);
			model.setReceipt0(sum);
		}
		if(year > sy && year == ey){
			String sum = "0";
			Class clazz = model.getClass();
			for (int i = 1; i < em; i++) {
				Method method = clazz.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model, res.getPropertyfee());
				sum = ArithmeticUtil.add(sum,res.getPropertyfee());
			}
			
			//结束当月
			Calendar c = Calendar.getInstance();
			c.set(ey, em, 0); //输入类型为int类型
			int dayend = c.get(Calendar.DAY_OF_MONTH);//当前应算多少天的费用
			String multiply2 = ArithmeticUtil.multiply(ArithmeticUtil.divide(ed+"",dayend+""),res.getPropertyfee());
			Method method2 = clazz.getDeclaredMethod("setReceipt"+em+"", String.class);
			method2.invoke(model, multiply2);
			sum = ArithmeticUtil.add(sum,multiply2);
			model.setReceipt0(sum);
		}
		
		return model;
	}
	
	/**
	 * 停车费报表
	 */

	@Override
	public JqGridPageView<EnterPropertyModel> getExportCarInfo(
			ExportPropertyRespModel model) throws Exception {
		JqGridPageView<EnterPropertyModel> result = new JqGridPageView<EnterPropertyModel>();
		List<ExportParkingDaoModel> list = contractDao.getExportCarInfo(model);
		if(list == null || list.size() == 0){
			result.setRows(null);
			result.setTotal(0);
			return result;
		}
		ArrayList<EnterPropertyModel> rows = new ArrayList<EnterPropertyModel>();
		//处理每个合同的停车费，分月计算
		for (ExportParkingDaoModel exportParkingDaoModel : list) {
			try {
				rows.addAll(dealcontractParkingByMonth(exportParkingDaoModel,model.getYear()));
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

	private List<EnterPropertyModel> dealcontractParkingByMonth(ExportParkingDaoModel res, String paramyear) throws Exception{
		ArrayList<EnterPropertyModel> rows = new ArrayList<EnterPropertyModel>();
		EnterPropertyModel model1 = new EnterPropertyModel();
		EnterPropertyModel model2 = new EnterPropertyModel();
		
		String id = UUID.randomUUID().toString();
		
		//地面
		model1.setId(id);
		model1.setRoomnum(res.getPropertytext());
		model1.setName(res.getPartbname());
		model1.setDeadline(res.getEnddate());
		model1.setType(res.getPrepay());
		model1.setRemarks(res.getSubsidiary());
		model1.setCarnum("（地面）");
		//地下
		model2.setId(id);
		model2.setRoomnum(res.getPropertytext());
		model2.setName(res.getPartbname());
		model2.setDeadline(res.getEnddate());
		model2.setType(res.getPrepay());
		model2.setRemarks(res.getSubsidiary());
		model2.setCarnum("（地下）");
		
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
			//地面
			Class clazz1 = model1.getClass();
			for (int i = 1; i < 13; i++) {
				Method method = clazz1.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model1, ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
			}
			model1.setReceipt0(ArithmeticUtil.multiply(ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()), "12"));
			//地下
			Class clazz2 = model2.getClass();
			for (int i = 1; i < 13; i++) {
				Method method = clazz2.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model2, ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
			}
			model2.setReceipt0(ArithmeticUtil.multiply(ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()), "12"));
		}
		if(year == sy && year < ey){
			String sum1 = "0";
			String sum2 = "0";
			//合同期限在当年，并且在年后
			//地面
			Class clazz1 = model1.getClass();
			for(int i = sm+1 ; i < 13; i++){
				Method method = clazz1.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model1, ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
				sum1 = ArithmeticUtil.add(sum1,ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
				
			}
			//地下
			Class clazz2 = model2.getClass();
			for(int i = sm+1 ; i < 13; i++){
				Method method = clazz2.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model2, ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
				sum2 = ArithmeticUtil.add(sum2,ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
			}
			
			//开始当月
			Calendar c = Calendar.getInstance();
			c.set(sy, sm, 0); //输入类型为int类型
			int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
			int ts = dayOfMonth - sd;//当前应算多少天的费用
			//地面
			String multiply = ArithmeticUtil.multiply(ArithmeticUtil.divide(ts+"", dayOfMonth+""),ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
			Method method = clazz1.getDeclaredMethod("setReceipt"+sm+"", String.class);
			method.invoke(model1, multiply);
			sum1 = ArithmeticUtil.add(sum1,multiply);
			//地下
			String multiply2 = ArithmeticUtil.multiply(ArithmeticUtil.divide(ts+"", dayOfMonth+""),ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
			Method method2 = clazz2.getDeclaredMethod("setReceipt"+sm+"", String.class);
			method2.invoke(model2, multiply2);
			sum2 = ArithmeticUtil.add(sum2,multiply2);
			
			model1.setReceipt0(sum1);
			model2.setReceipt0(sum2);
		}
		if(sy == ey){
			//合同期限在当年，并且在年前
			String sum1 = "0";
			String sum2 = "0";
			//地面
			Class clazz1 = model1.getClass();
			for(int i = sm+1 ; i < em; i++){
				Method method = clazz1.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model1,ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
				sum1 = ArithmeticUtil.add(sum1,ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
			}
			//地下
			Class clazz2 = model2.getClass();
			for(int i = sm+1 ; i < em; i++){
				Method method = clazz2.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model2, ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
				sum2 = ArithmeticUtil.add(sum2,ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
			}
			
			//开始当月
			Calendar c = Calendar.getInstance();
			c.set(sy, sm, 0); //输入类型为int类型
			int daystart = c.get(Calendar.DAY_OF_MONTH);
			int ts = daystart - sd;//当前应算多少天的费用
			//地面
			String multiply = ArithmeticUtil.multiply(ArithmeticUtil.divide(ts+"", daystart+""),ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
			Method method = clazz1.getDeclaredMethod("setReceipt"+sm+"", String.class);
			method.invoke(model1, multiply);
			sum1 = ArithmeticUtil.add(sum1,multiply);
			//地下
			String multiply2 = ArithmeticUtil.multiply(ArithmeticUtil.divide(ts+"", daystart+""),ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
			Method method2 = clazz2.getDeclaredMethod("setReceipt"+sm+"", String.class);
			method2.invoke(model2, multiply2);
			sum2 = ArithmeticUtil.add(sum2,multiply2);
			
			//结束当月
			c.set(ey, em, 0); //输入类型为int类型
			int dayend = c.get(Calendar.DAY_OF_MONTH);//当前应算多少天的费用
			//地面
			String multiply3 = ArithmeticUtil.multiply(ArithmeticUtil.divide(ed+"",dayend+""),ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
			Method method3 = clazz1.getDeclaredMethod("setReceipt"+em+"", String.class);
			method3.invoke(model1, multiply3);
			sum1 = ArithmeticUtil.add(sum1,multiply3);
			//地下
			String multiply4 = ArithmeticUtil.multiply(ArithmeticUtil.divide(ed+"",dayend+""),ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
			Method method4 = clazz2.getDeclaredMethod("setReceipt"+em+"", String.class);
			method4.invoke(model2, multiply4);
			sum2 = ArithmeticUtil.add(sum2,multiply4);
			
			model1.setReceipt0(sum1);
			model2.setReceipt0(sum2);
		}
		
		if(year > sy && year == ey){
			String sum1 = "0";
			String sum2 = "0";
			
			//地面
			Class clazz1 = model1.getClass();
			for(int i = 1 ; i < em; i++){
				Method method = clazz1.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model1,ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
				sum1 = ArithmeticUtil.add(sum1,ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
			}
			//地下
			Class clazz2 = model2.getClass();
			for(int i = 1 ; i < em; i++){
				Method method = clazz2.getDeclaredMethod("setReceipt"+i+"", String.class);
				method.invoke(model2, ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
				sum2 = ArithmeticUtil.add(sum2,ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
			}
			
			
			Calendar c = Calendar.getInstance();
			//结束当月
			c.set(ey, em, 0); //输入类型为int类型
			int dayend = c.get(Calendar.DAY_OF_MONTH);//当前应算多少天的费用
			//地面
			String multiply3 = ArithmeticUtil.multiply(ArithmeticUtil.divide(ed+"",dayend+""),ArithmeticUtil.multiply(res.getSurfaceunit(), res.getSurfacenumber()));
			Method method3 = clazz1.getDeclaredMethod("setReceipt"+em+"", String.class);
			method3.invoke(model1, multiply3);
			sum1 = ArithmeticUtil.add(sum1,multiply3);
			//地下
			String multiply4 = ArithmeticUtil.multiply(ArithmeticUtil.divide(ed+"",dayend+""),ArithmeticUtil.multiply(res.getUndergroundunit(), res.getUndergroundnumber()));
			Method method4 = clazz2.getDeclaredMethod("setReceipt"+em+"", String.class);
			method4.invoke(model2, multiply4);
			sum2 = ArithmeticUtil.add(sum2,multiply4);
			
			model1.setReceipt0(sum1);
			model2.setReceipt0(sum2);
		}
		
		rows.add(model1);
		rows.add(model2);
		return rows;
	}

}
