package com.jeefw.controller.sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.SysUser;

import core.support.JqGridPageView;

@Controller
@RequestMapping("/sys/test")
public class TestDataCntroller<V> extends JavaEEFrameworkBaseController<SysUser> implements Constant{
	
	/**
	 * 甲方合同测试数据
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getTestjfht", method = { RequestMethod.POST, RequestMethod.GET })
	public void getTestjfht(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JqGridPageView<Object> informationListView = new JqGridPageView<Object>();
		List<Object> rows = new ArrayList<Object>();
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("a1", "张三");
		map.put("a2", "顾戴路1920号");
		map.put("a3", "05556-544212");
		map.put("a4", "Sk734757435h34");
		map.put("a5", "5570000334xxxxxx324");
		rows.add(map);
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("a1", "李四");
		map2.put("a2", "手牛路1460号");
		map2.put("a3", "03445-234234");
		map2.put("a4", "432443243243");
		map2.put("a5", "432434345xxxx324543");
		rows.add(map2);
		HashMap<String,String> map3 = new HashMap<String, String>();
		map3.put("a1", "王五");
		map3.put("a2", "枢纽路7888号");
		map3.put("a3", "0434-55567");
		map3.put("a4", "15755502377");
		map3.put("a5", "2343424432XXXXXXXXXX324");
		rows.add(map3);
		HashMap<String,String> map4 = new HashMap<String, String>();
		map4.put("a1", "樱木花道");
		map4.put("a2", "三川路23423号");
		map4.put("a3", "0669-3345");
		map4.put("a4", "18832434224");
		map4.put("a5", "467328647xxxxxxxxx234432");
		rows.add(map4);
		informationListView.setRows(rows );
		informationListView.setMaxResults(10);
		informationListView.setRecords(1);
		informationListView.setTotal(4);
		writeJSON(response, informationListView);
	}
	
	
	/**
	 * 楼宇信息测试数据
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getTestlyxx", method = { RequestMethod.POST, RequestMethod.GET })
	public void getTestlyxx(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JqGridPageView<Object> informationListView = new JqGridPageView<Object>();
		List<Object> rows = new ArrayList<Object>();
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("a1", "43243432");
		map.put("a2", "张三");
		map.put("a3", "顾戴路1920号");
		map.put("a4", "05556-544212");
		map.put("a5", "暂无介绍");
		rows.add(map);
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("a1", "43243243");
		map2.put("a2", "李四");
		map2.put("a3", "手牛路1460号");
		map2.put("a4", "03445-234234");
		map2.put("a5", "暂无介绍--暂无介绍");
		rows.add(map2);
		HashMap<String,String> map3 = new HashMap<String, String>();
		map3.put("a1", "43243243");
		map3.put("a2", "王五");
		map3.put("a3", "枢纽路7888号");
		map3.put("a4", "0434-55567");
		map3.put("a5", "暂无介绍--暂无介绍-暂无介绍--暂无介绍");
		rows.add(map3);
		HashMap<String,String> map4 = new HashMap<String, String>();
		map4.put("a1", "43243244");
		map4.put("a2", "樱木花道");
		map4.put("a3", "三川路23423号");
		map4.put("a4", "0669-3345");
		map4.put("a5", "暂无介绍--暂无介绍-暂无介绍--暂无介绍");
		rows.add(map4);
		informationListView.setRows(rows );
		informationListView.setMaxResults(10);
		informationListView.setRecords(0);
		writeJSON(response, informationListView);
	}
	
	
	/**
	 * 楼宇物业信息测试数据
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getTestlywyxx", method = { RequestMethod.POST, RequestMethod.GET })
	public void getTestlywyxx(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JqGridPageView<Object> informationListView = new JqGridPageView<Object>();
		List<Object> rows = new ArrayList<Object>();
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("a1", "43243432");
		map.put("a2", "203室");
		map.put("a3", "顾戴路1920号");
		map.put("a4", "1223");
		map.put("a5", "暂无介绍");
		rows.add(map);
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("a1", "43243243");
		map2.put("a2", "204室");
		map2.put("a3", "手牛路1460号");
		map2.put("a4", "2344");
		map2.put("a5", "暂无介绍--暂无介绍");
		rows.add(map2);
		HashMap<String,String> map3 = new HashMap<String, String>();
		map3.put("a1", "43243243");
		map3.put("a2", "205室");
		map3.put("a3", "枢纽路7888号");
		map3.put("a4", "1233");
		map3.put("a5", "暂无介绍--暂无介绍-暂无介绍--暂无介绍");
		rows.add(map3);
		HashMap<String,String> map4 = new HashMap<String, String>();
		map4.put("a1", "43243244");
		map4.put("a2", "207室");
		map4.put("a3", "三川路23423号");
		map4.put("a4", "1232");
		map4.put("a5", "暂无介绍--暂无介绍-暂无介绍--暂无介绍");
		rows.add(map4);
		informationListView.setRows(rows );
		informationListView.setMaxResults(10);
		informationListView.setRecords(0);
		writeJSON(response, informationListView);
	}
	
	
	/**
	 * 楼宇停车位信息测试数据
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getTestlytcwxx", method = { RequestMethod.POST, RequestMethod.GET })
	public void getTestlytcwxx(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JqGridPageView<Object> informationListView = new JqGridPageView<Object>();
		List<Object> rows = new ArrayList<Object>();
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("a1", "101");
		map.put("a2", "西北角车库");
		map.put("a3", "大众");
		map.put("a4", "8");
		map.put("a5", "是");
		rows.add(map);
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("a1", "101");
		map2.put("a2", "西北角车库");
		map2.put("a3", "大众");
		map2.put("a4", "8");
		map2.put("a5", "是");
		rows.add(map2);
		HashMap<String,String> map3 = new HashMap<String, String>();
		map3.put("a1", "101");
		map3.put("a2", "西北角车库");
		map3.put("a3", "大众");
		map3.put("a4", "8");
		map3.put("a5", "是");
		rows.add(map3);
		HashMap<String,String> map4 = new HashMap<String, String>();
		map4.put("a1", "101");
		map4.put("a2", "西北角车库");
		map4.put("a3", "大众");
		map4.put("a4", "8");
		map4.put("a5", "是");
		rows.add(map4);
		informationListView.setRows(rows );
		informationListView.setMaxResults(10);
		informationListView.setRecords(0);
		writeJSON(response, informationListView);
	}
	
	/**
	 * 客户管理测试数据
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getTestkhgl", method = { RequestMethod.POST, RequestMethod.GET })
	public void getTestkhgl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JqGridPageView<Object> informationListView = new JqGridPageView<Object>();
		List<Object> rows = new ArrayList<Object>();
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("a1", "101");
		map.put("a2", "张三");
		map.put("a3", "00523-34324");
		map.put("a4", "34500543598");
		map.put("a5", "2343244432xxxxx234324");
		rows.add(map);
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("a1", "101");
		map2.put("a2", "李四");
		map2.put("a3", "00523-34324");
		map2.put("a4", "3443243598");
		map2.put("a5", "432444332xxxxx234324");
		rows.add(map2);
		HashMap<String,String> map3 = new HashMap<String, String>();
		map3.put("a1", "101");
		map3.put("a2", "五五");
		map3.put("a3", "00523-34324");
		map3.put("a4", "3432498");
		map3.put("a5", "4324324232xxxxx234324");
		rows.add(map3);
		HashMap<String,String> map4 = new HashMap<String, String>();
		map4.put("a1", "101");
		map4.put("a2", "琪琪");
		map4.put("a3", "00523-34324");
		map4.put("a4", "4324434233498");
		map4.put("a5", "4324324242xxxxx234324");
		rows.add(map4);
		informationListView.setRows(rows );
		informationListView.setMaxResults(10);
		informationListView.setRecords(0);
		writeJSON(response, informationListView);
	}
	
	
	/**
	 * 合同绑定测试数据
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getTesthtbd", method = { RequestMethod.POST, RequestMethod.GET })
	public void getTesthtbd(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JqGridPageView<Object> informationListView = new JqGridPageView<Object>();
		List<Object> rows = new ArrayList<Object>();
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("a1", "张三");
		map.put("a2", "13334324424");
		map.put("a3", "顾戴路399号");
		map.put("a4", "4433");
		map.put("a5", "789879");
		map.put("a6", "2354");
		map.put("a7", "545");
		map.put("a8", "2121");
		map.put("a9", "76577657");
		rows.add(map);
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("a1", "李四");
		map2.put("a2", "13334324424");
		map2.put("a3", "顾戴路399号");
		map2.put("a4", "45646");
		map2.put("a5", "987987");
		map2.put("a6", "5435");
		map2.put("a7", "454");
		map2.put("a8", "1434");
		map2.put("a9", "7657657");
		rows.add(map2);
		HashMap<String,String> map3 = new HashMap<String, String>();
		map3.put("a1", "五五");
		map3.put("a2", "13334324424");
		map3.put("a3", "顾戴路399号");
		map3.put("a4", "4565464");
		map3.put("a5", "98796");
		map3.put("a6", "435435");
		map3.put("a7", "766");
		map3.put("a8", "2313");
		map3.put("a9", "9879879");
		rows.add(map3);
		HashMap<String,String> map4 = new HashMap<String, String>();
		map4.put("a1", "琪琪");
		map4.put("a2", "13334324424");
		map4.put("a3", "顾戴路399号");
		map4.put("a4", "89789");
		map4.put("a5", "5465564");
		map4.put("a6", "54545");
		map4.put("a7", "889");
		map4.put("a8", "2443");
		map4.put("a9", "63543554");
		rows.add(map4);
		informationListView.setRows(rows );
		informationListView.setMaxResults(10);
		informationListView.setRecords(0);
		writeJSON(response, informationListView);
	}

}
