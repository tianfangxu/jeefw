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
		map.put("a1", "上海市交投物业管理有限公司");
		map.put("a2", "上海市徐汇区吴中东路555号8楼");
		map.put("a3", "021-34770192");
		map.put("a4", "邵重业");
		map.put("a5", "8859999");
		map.put("a6", "江川路浦发银行支行");
		map.put("a7", "阿健");
		map.put("a8", "591526xxxxxx261548");
		rows.add(map);
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
		map.put("a1", "100100");
		map.put("a2", "巴士大厦");
		map.put("a3", "建国东路525号");
		map.put("a4", "05556-544212");
		map.put("a5", "暂无介绍");
		rows.add(map);
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("a1", "100132");
		map2.put("a2", "建国大厦");
		map2.put("a3", "梅川路564号");
		map2.put("a4", "05556-564878");
		map2.put("a5", "暂无介绍");
		rows.add(map2);
		HashMap<String,String> map3 = new HashMap<String, String>();
		map3.put("a1", "100111");
		map3.put("a2", "帝国大厦");
		map3.put("a3", "梅川路788号");
		map3.put("a4", "05556-659847");
		map3.put("a5", "暂无介绍");
		rows.add(map3);
		HashMap<String,String> map4 = new HashMap<String, String>();
		map4.put("a1", "100110");
		map4.put("a2", "测试大厦");
		map4.put("a3", "梅川路111号");
		map4.put("a4", "05556-655825");
		map4.put("a5", "暂无介绍");
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
	 * 企业客户管理测试数据
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getTestkhglqy", method = { RequestMethod.POST, RequestMethod.GET })
	public void getTestkhgl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JqGridPageView<Object> informationListView = new JqGridPageView<Object>();
		List<Object> rows = new ArrayList<Object>();
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("a1", "100120");
		map.put("a2", "将夜测试企业");
		map.put("a3", "蓝露");
		map.put("a4", "55561-131415");
		map.put("a5", "返回路200号");
		map.put("a6", "458926");
		map.put("a7", "15487519931128xxxx");
		map.put("a8", "中国邮政储蓄");
		map.put("a9", "李吉娜");
		map.put("a10", "154856xxxxxx485926");
		map.put("a11", "484855");
		map.put("a12", "李吉娜");
		rows.add(map);
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("a1", "100126");
		map2.put("a2", "蓝鲫测试企业");
		map2.put("a3", "蓝露");
		map2.put("a4", "55561-485989");
		map2.put("a5", "返回路200号");
		map2.put("a6", "458926");
		map2.put("a7", "15487519931128xxxx");
		map2.put("a8", "中国邮政储蓄");
		map2.put("a9", "张丽");
		map2.put("a10", "154856xxxxxx485926");
		map2.put("a11", "484855");
		map2.put("a12", "张丽");
		rows.add(map2);
		HashMap<String,String> map3 = new HashMap<String, String>();
		map3.put("a1", "100128");
		map3.put("a2", "看空测试企业");
		map3.put("a3", "蓝露");
		map3.put("a4", "55561-263666");
		map3.put("a5", "返回路200号");
		map3.put("a6", "458926");
		map3.put("a7", "15487519931128xxxx");
		map3.put("a8", "中国邮政储蓄");
		map3.put("a9", "加蓝");
		map3.put("a10", "154856xxxxxx485926");
		map3.put("a11", "484855");
		map3.put("a12", "加蓝");
		rows.add(map3);
		HashMap<String,String> map4 = new HashMap<String, String>();
		map4.put("a1", "100156");
		map4.put("a2", "胡室测试企业");
		map4.put("a3", "蓝露");
		map4.put("a4", "55561-6665555");
		map4.put("a5", "返回路200号");
		map4.put("a6", "458926");
		map4.put("a7", "15487519931128xxxx");
		map4.put("a8", "中国邮政储蓄");
		map4.put("a9", "孔之");
		map4.put("a10", "154856xxxxxx485926");
		map4.put("a11", "585899");
		map4.put("a12", "孔之");
		rows.add(map4);
		informationListView.setRows(rows );
		informationListView.setMaxResults(10);
		informationListView.setRecords(0);
		writeJSON(response, informationListView);
	}
	
	/**
	 * 个人客户管理测试数据
	 * @throws IOException 
	 */
	@RequestMapping(value = "/getTestkhgl", method = { RequestMethod.POST, RequestMethod.GET })
	public void getTestkhglqy(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JqGridPageView<Object> informationListView = new JqGridPageView<Object>();
		List<Object> rows = new ArrayList<Object>();
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("a1", "101");
		map.put("a2", "张三");
		map.put("a3", "男");
		map.put("a4", "蓝屏路43号344室");
		map.put("a5", "00523-34324");
		map.put("a6", "458926");
		map.put("a7", "15487519931128xxxx");
		map.put("a8", "15755502377@163.com");
		rows.add(map);
		HashMap<String,String> map2 = new HashMap<String, String>();
		map2.put("a1", "112");
		map2.put("a2", "张静");
		map2.put("a3", "女");
		map2.put("a4", "顾江路44号102室");
		map2.put("a5", "00523-485615");
		map2.put("a6", "263636");
		map2.put("a7", "45152619931025xxxx");
		map2.put("a8", "15755502377@163.com");
		rows.add(map2);
		HashMap<String,String> map3 = new HashMap<String, String>();
		map3.put("a1", "122");
		map3.put("a2", "王凯");
		map3.put("a3", "男");
		map3.put("a4", "富康路110号104室");
		map3.put("a5", "00523-4714215");
		map3.put("a6", "151526");
		map3.put("a7", "26154819951026xxxx");
		map3.put("a8", "15755502377@163.com");
		rows.add(map3);
		HashMap<String,String> map4 = new HashMap<String, String>();
		map4.put("a1", "322");
		map4.put("a2", "李静");
		map4.put("a3", "女");
		map4.put("a4", "粉饼路899号404室");
		map4.put("a5", "00523-485628");
		map4.put("a6", "262659");
		map4.put("a7", "26154819951026xxxx");
		map4.put("a8", "15755502377@163.com");
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
