<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery-ui.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/ui.jqgrid.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery.gritter.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/datepicker.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap-timepicker.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/daterangepicker.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap-datetimepicker.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/select2.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/chartsreports/sb-admin.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/chartsreports/morris.css" />

<script type="text/javascript">
	
</script>
<style>
	.inputc{
		width: 80%;
		text-align: center;
	}
</style>
<body>
		<div class="row">
			
			<div class="col-xs-12">
				<div class="well well-sm">
					 <select class="select2 col-xs-2 " id="lyxx">
		                <option value="">--请选择楼宇--</option>
		                <option value="wy">巴士大厦</option>
		                <option value="cw">金宏大厦</option>
		                <option value="cw">天源大厦</option>
		                <option value="cw">天山</option>
		                <option value="cw">芦恒路</option>
		                <option value="cw">逸仙路</option>
		            </select>
					<div class="btn btn-info" onclick="$('#table_in').css('display','block')" style="    height: 30px;padding-top: 0px;">录入</div>
				</div>
				<table id="sample-table-1" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center">
								<label class="position-relative">
									<input type="checkbox" class="ace" />
									<span class="lbl"></span>
								</label>
							</th>
							<th>月份/（月）</th>
							<th>收入合计</th>
							<th >成本合计</th>

							<th>
								能源费合计
							</th>
							<th>办公总务合计</th>

							<th>业务外包合计</th>
							<th>修理费合计</th>
							<th>其他合计</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td class="center">
								<label class="position-relative">
									<input type="checkbox" class="ace" />
									<span class="lbl"></span>
								</label>
							</td>

							<td>
								1
							</td>
							<td>330999</td>
							<td >263315</td>
							<td>26485</td>

							<td>
								2648
							</td>

							<td>
								19006
							</td>
							<td>
								4000
							</td>
							<td>
								484
							</td>
							<td>
								<div class="btn btn-minier btn-purple" onclick="getmsg()">详情</div>
							</td>
						</tr>
						<tr>
							<td class="center">
								<label class="position-relative">
									<input type="checkbox" class="ace" />
									<span class="lbl"></span>
								</label>
							</td>

							<td>
								2
							</td>
							<td>330999</td>
							<td >263315</td>
							<td>26485</td>

							<td>
								2648
							</td>

							<td>
								19006
							</td>
							<td>
								4000
							</td>
							<td>
								484
							</td>
							<td>
								<div class="btn btn-minier btn-purple" onclick="getmsg()">详情</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div><!-- /.span -->
	</div>
<div class="well well-sm"></div>
			
<div style="display: none" id="table_in">
       <div class="col-xs-12 col-sm-12" >
           <div class="widget-box">
               <div class="widget-header">
                   <h4 class="widget-title">收入</h4>
               </div>

               <div class="widget-body">
                   <div class="widget-main">
                       <table class="table table-striped table-bordered table-hover" style="text-align: center;" id="income">
                           <tr>
                               <td colspan="2" style="text-align: center;">项目</td>
                               <td style="text-align: center;width: 22%">全年预算</td>
                               <td style="text-align: center;width: 22%">当月实绩</td>                           
                               <td style="text-align: center;width: 22%">备注</td>
                           </tr>
                           <tr>
                               <td colspan="2">物业管理费</td>
                                <td>4343000.00 </td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td rowspan="2" style="vertical-align: middle;">停车费</td>
                               <td>固定停车费	</td>
                               <td>370000.00 </td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td>临时停车费</td>
                                <td>0.00 </td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="2">服务费</td>
                                <td>84315.00</td>
                                <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr><tr>
                           <td colspan="2">广告费</td>
                           <td>423000.00 </td>
                            <td><input type="text" class="inputc"></td>
                            <td><input type="text" class="inputc"></td>
                       </tr><tr>
                           <td colspan="2">仓储费/经营开发</td>
                           <td>35000.00</td>
                            <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                       </tr><tr>
                           <td colspan="2">租线费</td>
                           <td>70000.00</td>
                           <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                       </tr><tr>
                           <td colspan="2">电费</td>
                           <td>1200000.00 </td>
                           <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                       </tr>
                       
                       <tr>
                           <td colspan="5" style="text-align: left;">合计：<span style="color:red">0.00</span></td>
                       </tr>
                       </table>
                   </div>
               </div>
           </div>
       </div>
       <div class="col-xs-12 col-sm-12">
           <div class="widget-box">
               <div class="widget-header">
                   <h4 class="widget-title">成本</h4>
               </div>

               <div class="widget-body">
                   <div class="widget-main">
                       <table class="table table-striped table-bordered table-hover" style="text-align: center;">
                           <tr>
                               <td rowspan="3" style="vertical-align: middle;">能源费</td>
                               <td colspan="2">水费</td>
                               <td style="width: 22%">80000.00 </td>
                                <td style="width: 22%"><input type="text" class="inputc"></td>
                               <td style="width: 22%"><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="2">电费</td>
                               <td>2320000.00 </td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="2">燃气费</td>
                               <td>300000.00 </td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="6" style="text-align: left;">合计:<span style="color:red;">0.00</span> </td>
                           </tr>
                       </table>
                       <table class="table table-striped table-bordered table-hover" style="text-align: center;">
                           <tr>
                               <td rowspan="16" style="vertical-align: middle;">办公总务</td>
                               <td colspan="2">办公用品</td>
                                <td style="width: 22%">30000.00</td>
                               <td style="width: 22%"><input type="text" class="inputc"></td>
                               <td style="width: 22%"><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="2">电话、通信费等</td>
                               <td>6000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="2">饮用水等	</td>
                               <td>15000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="2">门牌制作等	</td>
                               <td>1200</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">节假日布置		</td>
                               <td>3000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">保洁用品	</td>
                               <td>9000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">绿化费	</td>
                               <td>14000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">劳防用品	</td>
                               <td>400</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">垃圾清运费	</td>
                               <td>8000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">应急物资	</td>
                               <td>1000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">外墙、水箱清洗	</td>
                               <td>5000</td>
                              <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">报警服务费	</td>
                               <td>4400</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">灭虫害服务费	</td>
                               <td>1300</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">污水处理（疏通）费	</td>
                               <td>15000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">地坪保养费	</td>
                               <td>3000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="2">其他	</td>
                               <td>0</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>

                           <tr>
                               <td colspan="6" style="text-align: left;">合计:<span style="color:red;">0.00 </span> </td>
                           </tr>
                       </table>
                       <table class="table table-striped table-bordered table-hover" style="text-align: center;">
                           <tr>
                               <td rowspan="3" style="vertical-align: middle;">业务外包</td>
                               <td colspan="2">保安服务费</td>
                               <td style="width: 22%">100</td>
                               <td style="width: 22%"><input type="text" class="inputc"></td>
                               <td style="width: 22%"><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="2">保洁服务费</td>
                                <td>390</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="2">工程外包</td>
                                <td>3000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="6" style="text-align: left;">合计:<span style="color:red;">0.00</span> </td>
                           </tr>
                       </table>
                       <table class="table table-striped table-bordered table-hover" style="text-align: center;">
                           <tr>
                               <td rowspan="8" style="vertical-align: middle;">修理费</td>
                               <td colspan="2">保安服务费</td>
                                <td style="width: 22%">100</td>
                                <td style="width: 22%"><input type="text" class="inputc"></td>
                               <td style="width: 22%"><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="2">大修理费用</td>
                                <td>340</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td rowspan="3" style="vertical-align: middle;">设备设施维护</td>
                               <td>消防设备设施维护费</td>
                                <td>301</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td>工程设备设施维护费</td>
                                <td>1000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td>设备设施检测费</td>
                                <td>200</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td rowspan="3" style="vertical-align: middle;">日常修理费用</td>
                               <td>材料费</td>
                                <td>100</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td>灭火机</td>
                                <td>3000</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td>日常维修费</td>
                               <td>790</td>
                               <td><input type="text" class="inputc"></td>
                               <td><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="6" style="text-align: left;">合计:<span style="color:red;">0.00</span> </td>
                           </tr>
                       </table>
                       <table class="table table-striped table-bordered table-hover" style="text-align: center;">
                           <tr>
                               <td>其他</td>
                               <td style="width: 22%">4000</td>
                               <td style="width: 22%"><input type="text" class="inputc"></td>
                               <td style="width: 22%"><input type="text" class="inputc"></td>
                           </tr>
                           <tr>
                               <td colspan="4" style="text-align: left;">合计:<span style="color:red;">0.00</span> </td>
                           </tr>
                       </table>
                   </div>
               </div>
           </div>
       </div>
       <div class="col-xs-12 col-sm-12">
       		<div class="col-xs-12 col-sm-6">
	       		<div style="color: red;font-size: 24px">
	       			总计：<span>0.00</span>
	       		</div>
       		</div>
       		<div class="col-xs-12 col-sm-6" style="text-align: right;">
       			<div class="btn btn-success btn-lg" onclick="$('#table_in').css('display','none')">确定</div>
       		</div>
       </div>
  </div>
  
  <div id="modal-htxx_test" class="modal fade" tabindex="-1" data-backdrop="static">
    <div class="modal-dialog" style="min-width: 1200px;">
        <form id="informationForm">
            <div class="modal-content">
                <div class="modal-header no-padding">
                    <div class="table-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            <span class="white">&times;</span>
                        </button>
                        实绩表
                    </div>
                </div>
                <div class="modal-body" style="max-height: 700px;overflow-y: scroll;">
                    <div class="col-xs-12 col-sm-12">
                        <table class="table table-striped table-bordered table-hover">
                            <tr><td>合计：<span style="color:green;">2969600.00 </span></td></tr>
                        </table>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <div class="widget-box">
                            <div class="widget-header">
                                <h4 class="widget-title">收入</h4>
                            </div>

                            <div class="widget-body">
                                <div class="widget-main">
                                    <table class="table table-striped table-bordered table-hover">
                                        <tr>
                                            <td colspan="2" style="text-align: center;">项目</td>
                                            <td style="text-align: center;">当月实绩</td>
                                            <td style="text-align: center;">备注</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">物业管理费</td>
                                            <td>4343000.00 </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td rowspan="2" style="vertical-align: middle;">停车费</td>
                                            <td>固定停车费	</td>
                                            <td>370000.00 </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>临时停车费</td>
                                            <td>0.00 </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">服务费</td>
                                            <td>84315.00</td>
                                            <td>含基站租赁</td>
                                        </tr><tr>
                                        <td colspan="2">广告费</td>
                                        <td>423000.00 </td>
                                        <td></td>
                                    </tr><tr>
                                        <td colspan="2">仓储费/经营开发</td>
                                        <td>35000.00</td>
                                        <td>有服务合同</td>
                                    </tr><tr>
                                        <td colspan="2">租线费</td>
                                        <td>70000.00</td>
                                        <td>备注</td>
                                    </tr><tr>
                                        <td colspan="2">电费</td>
                                        <td>1200000.00 </td>
                                        <td></td>
                                    </tr><tr>
                                        <td colspan="4">合计：<span style="color:red">6525315.00</span></td>

                                    </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <div class="widget-box">
                            <div class="widget-header">
                                <h4 class="widget-title">成本</h4>
                            </div>

                            <div class="widget-body">
                                <div class="widget-main">
                                    <table class="table table-striped table-bordered table-hover">
                                        <tr>
                                            <td colspan="3" style="text-align: center;">项目</td>
                                            <td style="text-align: center;">当月实绩</td>
                                            <td style="text-align: center;">备注</td>
                                        </tr>
                                        <tr>
                                            <td rowspan="3" style="vertical-align: middle;">能源费</td>
                                            <td colspan="2">水费</td>
                                            <td>80000.00 </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">电费</td>
                                            <td>2320000.00 </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">燃气费</td>
                                            <td>300000.00 </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="5">合计:<span style="color:red;">2700000.00</span> </td>
                                        </tr>
                                    </table>
                                    <table class="table table-striped table-bordered table-hover">
                                        <tr>
                                            <td colspan="3" style="text-align: center;">项目</td>
                                            <td style="text-align: center;">当月实绩</td>
                                            <td style="text-align: center;">备注</td>
                                        </tr>
                                        <tr>
                                            <td rowspan="16" style="vertical-align: middle;">办公总务</td>
                                            <td colspan="2">办公用品</td>
                                            <td>30000.00</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">电话、通信费等</td>
                                            <td>6000</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">饮用水等	</td>
                                            <td>15000</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">门牌制作等	</td>
                                            <td>5000</td>
                                            <td></td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">节假日布置		</td>
                                            <td>4000</td>
                                            <td></td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">保洁用品	</td>
                                            <td>10000</td>
                                            <td></td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">绿化费	</td>
                                            <td>36000</td>
                                            <td>有服务合同</td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">劳防用品	</td>
                                            <td>1400</td>
                                            <td>350*4</td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">垃圾清运费	</td>
                                            <td>62000</td>
                                            <td>有服务合同</td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">应急物资	</td>
                                            <td>10000</td>
                                            <td></td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">外墙、水箱清洗	</td>
                                            <td>23000</td>
                                            <td>有服务合同</td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">报警服务费	</td>
                                            <td>3000</td>
                                            <td></td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">灭虫害服务费	</td>
                                            <td>4200</td>
                                            <td>有服务合同</td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">污水处理（疏通）费	</td>
                                            <td>4200</td>
                                            <td>有服务合同</td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">地坪保养费	</td>
                                            <td></td>
                                            <td></td>
                                        </tr>

                                        <tr>
                                            <td colspan="2">其他	</td>
                                            <td></td>
                                            <td></td>
                                        </tr>

                                        <tr>
                                            <td colspan="5">合计:<span style="color:red;">209600.00 </span> </td>
                                        </tr>
                                    </table>
                                    <table class="table table-striped table-bordered table-hover">
                                        <tr>
                                            <td colspan="3" style="text-align: center;">项目</td>
                                            <td style="text-align: center;">当月实绩</td>
                                            <td style="text-align: center;">备注</td>
                                        </tr>
                                        <tr>
                                            <td rowspan="3" style="vertical-align: middle;">业务外包</td>
                                            <td colspan="2">保安服务费</td>
                                            <td></td>
                                            <td>有服务合同</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">保洁服务费</td>
                                            <td></td>
                                            <td>有服务合同</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">工程外包</td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="5">合计:<span style="color:red;">0.00</span> </td>
                                        </tr>
                                    </table>
                                    <table class="table table-striped table-bordered table-hover">
                                        <tr>
                                            <td colspan="3" style="text-align: center;">项目</td>
                                            <td style="text-align: center;">当月实绩</td>
                                            <td style="text-align: center;">备注</td>
                                        </tr>
                                        <tr>
                                            <td rowspan="8" style="vertical-align: middle;">修理费</td>
                                            <td colspan="2">保安服务费</td>
                                            <td></td>
                                            <td>有服务合同</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">大修理费用</td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td rowspan="3" style="vertical-align: middle;">设备设施维护</td>
                                            <td>消防设备设施维护费</td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>工程设备设施维护费</td>
                                            <td></td>
                                            <td>含高压维保费等</td>
                                        </tr>
                                        <tr>
                                            <td>设备设施检测费</td>
                                            <td></td>
                                            <td>含避雷检测费、消防检测费等</td>
                                        </tr>
                                        <tr>
                                            <td rowspan="3" style="vertical-align: middle;">日常修理费用</td>
                                            <td>材料费</td>
                                            <td>20000</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>灭火机</td>
                                            <td>20000</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>日常维修费</td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="5">合计:<span style="color:red;">40000.00</span> </td>
                                        </tr>
                                    </table>
                                    <table class="table table-striped table-bordered table-hover">
                                        <tr>
                                            <td>其他</td>
                                            <td>20000</td>
                                            <td>有服务合同（电话维护）</td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">合计:<span style="color:red;">20000.00</span> </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer no-margin-top">
                    <div class="text-center">
                        <button class="btn btn-app btn-pink btn-xs" data-dismiss="modal">
                            <i class="ace-icon fa fa-share bigger-160"></i>
                            取消
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
  
</body>
<script type="text/javascript">
        var $path_base = "${contextPath}/static";
    </script>
<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, "${contextPath}/static/assets/js/jqGrid/jquery.jqGrid.js", "${contextPath}/static/assets/js/jqGrid/i18n/grid.locale-cn.js", "${contextPath}/static/assets/js/jquery-ui.custom.js",
        "${contextPath}/static/assets/js/jquery.ui.touch-punch.js", "${contextPath}/static/assets/js/markdown/markdown.js", "${contextPath}/static/assets/js/markdown/bootstrap-markdown.js",
        "${contextPath}/static/assets/js/jquery.hotkeys.js", "${contextPath}/static/assets/js/bootstrap-wysiwyg.js", "${contextPath}/static/assets/js/bootbox.js",
        "${contextPath}/static/assets/js/jquery.gritter.js", "${contextPath}/static/assets/js/date-time/bootstrap-datepicker.js", "${contextPath}/static/assets/js/date-time/bootstrap-timepicker.js",
        "${contextPath}/static/assets/js/date-time/moment.js", "${contextPath}/static/assets/js/date-time/daterangepicker.js", "${contextPath}/static/assets/js/date-time/bootstrap-datetimepicker.js",
        "${contextPath}/static/assets/js/select2.js","${contextPath}/static/assets/js/echarts.js",null]
    $(".page-content-area").ace_ajax("loadScripts", scripts, function () {
        // inline scripts related to this page
        jQuery(function ($) {
            $('.select2').select2({allowClear:true})

            var grid_selector = "#grid-table";
            var pager_selector = "#grid-pager";

            // resize to fit page size
            $(window).on("resize.jqGrid", function () {
                $(grid_selector).jqGrid("setGridWidth", $(".page-content").width());
            })
            // resize on sidebar collapse/expand
            var parent_column = $(grid_selector).closest("[class*='col-']");
            $(document).on("settings.ace.jqGrid", function (ev, event_name, collapsed) {
                if (event_name === "sidebar_collapsed" || event_name === "main_container_fixed") {
                    // setTimeout is for webkit only to give time for DOM changes and then redraw!!!
                    setTimeout(function () {
                        $(grid_selector).jqGrid("setGridWidth", parent_column.width());
                    }, 0);
                }
            })

            $(document).keydown(function (event) {
                var key = window.event ? event.keyCode : event.which;
                if (key == 13) {
                    if ($("#search-input").val() == "") {
                        $.gritter.add({
                            title: "系统信息",
                            text: "请输入检索词",
                            class_name: "gritter-info gritter-center"
                        });
                        return;
                    } else {
                        $.ajax({
                            dataType: "json",
                            url: "${contextPath}/sys/buliding/getInformationHibernateSearch",
                            type: "post",
                            data: {
                                luceneName: $("#search-input").val()
                            },
                            complete: function (response) {
                                var result = eval("(" + response.responseText + ")");
                                jQuery(grid_selector)[0].addJSONData(result);
                            }
                        });
                    }
                }
            });

            jQuery(grid_selector).jqGrid({
                subGrid: false,
                url: "${contextPath}/static/json/budgetReport.json",
                datatype: "json",
                height: 450,
                colNames: ["ID", "单位", "收入预算合计", "成本预算合计", "能源费合计", "办公总务合计", "业务外包合计", "修理费合计", "其他合计", "操作"],
                colModel: [{
                    name: "id",
                    index: "id",
                    label: "ID",
                    width: 50,
                    sorttype: "long",
                    search: false
                }, {
                    name: "czf",
                    index: "czf",
                    label: "单位",
                    width: 100,
                    editable: true,
                    editoptions: {size: "20", maxlength: "50"},
                    editrules: {required: true},
                    searchoptions: {sopt: ["cn"]}
                }, {
                    name: "srhj",
                    index: "srhj",
                    label: "收入预算合计",
                    width: 100,
                    editable: true,
                    editoptions: {size: "20", maxlength: "100"},
                    searchoptions: {sopt: ["cn"]}
                }, {
                    name: "cbhj",
                    index: "cbhj",
                    label: "成本预算合计",
                    width: 100,
                    editable: true,
                    editoptions: {size: "20", maxlength: "15"},
                    search: false,
                }, {
                    name: "nyfhj",
                    index: "nyfhj",
                    label: "能源费合计",
                    width: 100,
                    editable: true,
                    editoptions: {size: "20", maxlength: "15"},
                    search: false,
                },
                 {name: "bgzwhj", index: "bgzwhj", width: 100, label: "办公总务合计", search: false},
                 {name: "ywwbhj", index: "ywwbhj", width: 100, label: "业务外包合计", search: false},
                 {name: "xlfhj", index: "xlfhj", width: 100, label: "修理费合计", search: false},
                 {name: "qthj", index: "qthj", width: 100, label: "其他合计", search: false},
                 {   name: "zj5",
                     index: "zj5",
                     width: 70,
                     label: "操作",
                     formatter:function(){
                         return '<a class="btn btn-info btn-sm" onclick="getmsg()">详情</a>';
                     }
                 }
                ],
                //scroll : 1, // set the scroll property to 1 to enable paging with scrollbar - virtual loading of records
                sortname: "id",
                sortorder: "asc",
                viewrecords: true,
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: pager_selector,
                altRows: true,
                //toppager : true,
                multiselect: true,
                //multikey : "ctrlKey",
                multiboxonly: true,
                loadComplete: function () {
                    var table = this;
                    setTimeout(function () {
                        styleCheckbox(table);
                        updateActionIcons(table);
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },
                editurl: "${contextPath}/sys/buliding/operateInformation",
                onSelectRow: function (id){
                    showEcharts(id);
                }
                //caption : "用户管理列表",
                //autowidth : true,
                /**
                 grouping : true,
                 groupingView : {
        				 groupField : ["name"],
        				 groupDataSorted : true,
        				 plusicon : "fa fa-chevron-down bigger-110",
        				 minusicon : "fa fa-chevron-up bigger-110"
        			},
                 */
            });

            $(window).triggerHandler("resize.jqGrid");// trigger window resize to make the grid get the correct size

            // enable search/filter toolbar
            // jQuery(grid_selector).jqGrid("filterToolbar",{defaultSearch:true,stringResult:true})
            // jQuery(grid_selector).filterToolbar({});
            // switch element when editing inline
            function aceSwitch(cellvalue, options, cell) {
                setTimeout(function () {
                    $(cell).find("input[type=checkbox]").addClass("ace ace-switch ace-switch-5").after("<span class='lbl'></span>");
                }, 0);
            }

            $("#survey").ace_wysiwyg({
                toolbar:
                    [
                        "font",
                        null,
                        "fontSize",
                        null,
                        {name: "bold", className: "btn-info"},
                        {name: "italic", className: "btn-info"},
                        {name: "strikethrough", className: "btn-info"},
                        {name: "underline", className: "btn-info"},
                        null,
                        {name: "insertunorderedlist", className: "btn-success"},
                        {name: "insertorderedlist", className: "btn-success"},
                        {name: "outdent", className: "btn-purple"},
                        {name: "indent", className: "btn-purple"},
                        null,
                        {name: "justifyleft", className: "btn-primary"},
                        {name: "justifycenter", className: "btn-primary"},
                        {name: "justifyright", className: "btn-primary"},
                        {name: "justifyfull", className: "btn-inverse"},
                        null,
                        {name: "createLink", className: "btn-pink"},
                        {name: "unlink", className: "btn-pink"},
                        null,
                        {name: "insertImage", className: "btn-success"},
                        null,
                        "foreColor",
                        null,
                        {name: "undo", className: "btn-grey"},
                        {name: "redo", className: "btn-grey"}
                    ],
                "wysiwyg": {
                    fileUploadError: showErrorAlert
                }
            }).prev().addClass("wysiwyg-style3");

            function showErrorAlert(reason, detail) {
                var msg = "";
                if (reason === "unsupported-file-type") {
                    msg = "Unsupported format " + detail;
                } else {
                    // console.log("error uploading file", reason, detail);
                }
                $("<div class='alert'> <button type='button' class='close' data-dismiss='alert'>&times;</button>" + "<strong>File upload error</strong> " + msg + " </div>").prependTo("#alerts");
            }

            $("#addInformationButton").bind("click", function () {
                $("#modal-table").modal("toggle");
                $("#bulidingForm")[0].reset();
                $("#editor").html("");
                $("#modal-tip").html("");
            });

            $('input[name=date-range-picker]').daterangepicker({
                'applyClass': 'btn-sm btn-success',
                'cancelClass': 'btn-sm btn-default',
                locale: {
                    applyLabel: 'Apply',
                    cancelLabel: 'Cancel',
                }
            }).prev().on(ace.click_event, function () {
                $(this).next().focus();
            });

            // navButtons
            jQuery(grid_selector).jqGrid("navGrid", pager_selector, { // navbar options
                edit: false,
                editicon: "ace-icon fa fa-pencil blue",
                add: false,
                addicon: "ace-icon fa fa-plus-circle purple",
                del: <shiro:hasPermission name="${ROLE_KEY}:buliding:delete">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:buliding:delete">false</shiro:lacksPermission>,
                delicon: "ace-icon fa fa-trash-o red",
                search: <shiro:hasPermission name="${ROLE_KEY}:buliding:search">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:buliding:search">false</shiro:lacksPermission>,
                searchicon: "ace-icon fa fa-search orange",
                refresh: true,
                refreshicon: "ace-icon fa fa-refresh blue",
                view: <shiro:hasPermission name="${ROLE_KEY}:buliding:view">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:buliding:view">false</shiro:lacksPermission>,
                viewicon: "ace-icon fa fa-search-plus grey"
            }, {
                // edit record form
                // closeAfterEdit: true,
                // width: 700,
                recreateForm: true,
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-titlebar").wrapInner("<div class='widget-header' />")
                    style_edit_form(form);
                },
                errorTextFormat: function (response) {
                    var result = eval("(" + response.responseText + ")");
                    return result.message;
                }
            }, {
                // new record form
                // width: 700,
                closeAfterAdd: true,
                recreateForm: true,
                viewPagerButtons: false,
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-titlebar").wrapInner("<div class='widget-header' />")
                    style_edit_form(form);
                },
                errorTextFormat: function (response) {
                    var result = eval("(" + response.responseText + ")");
                    return result.message;
                }
            }, {
                // delete record form
                recreateForm: true,
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    if (form.data("styled"))
                        return false;
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-titlebar").wrapInner("<div class='widget-header' />")
                    style_delete_form(form);
                    form.data("styled", true);
                },
                onClick: function (e) {
                    //alert(1);
                }
            }, {
                // search form
                recreateForm: true,
                afterShowSearch: function (e) {
                    var form = $(e[0]);
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-title").wrap("<div class='widget-header' />")
                    style_search_form(form);
                },
                afterRedraw: function () {
                    style_search_filters($(this));
                },
                multipleSearch: true
                /**
                 * multipleGroup:true, showQuery: true
                 */
            }, {
                // view record form
                recreateForm: true,
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-title").wrap("<div class='widget-header' />")
                }
            })

            // add custom button to export the data to excel
            if (<shiro:hasPermission name="${ROLE_KEY}:buliding:export">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:buliding:export">false</shiro:lacksPermission>) {
                jQuery(grid_selector).jqGrid("navButtonAdd", pager_selector, {
                    caption: "",
                    title: "导出Excel",
                    buttonicon: "ace-icon fa fa-file-excel-o green",
                    onClickButton: function () {
                        var keys = [], ii = 0, rows = "";
                        var ids = $(grid_selector).getDataIDs(); // Get All IDs
                        var row = $(grid_selector).getRowData(ids[0]); // Get First row to get the labels
                        //var label = $(grid_selector).jqGrid("getGridParam","colNames");
                        for (var k in row) {
                            keys[ii++] = k; // capture col names
                            rows = rows + k + "\t"; // output each Column as tab delimited
                        }
                        rows = rows + "\n"; // Output header with end of line
                        for (i = 0; i < ids.length; i++) {
                            row = $(grid_selector).getRowData(ids[i]); // get each row
                            for (j = 0; j < keys.length; j++)
                                rows = rows + row[keys[j]] + "\t"; // output each Row as tab delimited
                            rows = rows + "\n"; // output each row with end of line
                        }
                        rows = rows + "\n"; // end of line at the end
                        var form = "<form name='csvexportform' action='${contextPath}/sys/buliding/operateInformation?oper=excel' method='post'>";
                        form = form + "<input type='hidden' name='csvBuffer' value='" + encodeURIComponent(rows) + "'>";
                        form = form + "</form><script>document.csvexportform.submit();</sc" + "ript>";
                        OpenWindow = window.open("", "");
                        OpenWindow.document.write(form);
                        OpenWindow.document.close();
                    }
                });
            }

            function style_edit_form(form) {
                // form.find("input[name=statusCn]").addClass("ace ace-switch ace-switch-5").after("<span class="lbl"></span>");
                // don"t wrap inside a label element, the checkbox value won"t be submitted (POST"ed)
                // .addClass("ace ace-switch ace-switch-5").wrap("<label class="inline" />").after("<span class="lbl"></span>");

                // update buttons classes
                var buttons = form.next().find(".EditButton .fm-button");
                buttons.addClass("btn btn-sm").find("[class*='-icon']").hide();// ui-icon, s-icon
                buttons.eq(0).addClass("btn-primary").prepend("<i class='ace-icon fa fa-check'></i>");
                buttons.eq(1).prepend("<i class='ace-icon fa fa-times'></i>")

                buttons = form.next().find(".navButton a");
                buttons.find(".ui-icon").hide();
                buttons.eq(0).append("<i class='ace-icon fa fa-chevron-left'></i>");
                buttons.eq(1).append("<i class='ace-icon fa fa-chevron-right'></i>");
            }

            function style_delete_form(form) {
                var buttons = form.next().find(".EditButton .fm-button");
                buttons.addClass("btn btn-sm btn-white btn-round").find("[class*='-icon']").hide();// ui-icon, s-icon
                buttons.eq(0).addClass("btn-danger").prepend("<i class='ace-icon fa fa-trash-o'></i>");
                buttons.eq(1).addClass("btn-default").prepend("<i class='ace-icon fa fa-times'></i>")
            }

            function style_search_filters(form) {
                form.find(".delete-rule").val("X");
                form.find(".add-rule").addClass("btn btn-xs btn-primary");
                form.find(".add-group").addClass("btn btn-xs btn-success");
                form.find(".delete-group").addClass("btn btn-xs btn-danger");
            }

            function style_search_form(form) {
                var dialog = form.closest(".ui-jqdialog");
                var buttons = dialog.find(".EditTable");
                buttons.find(".EditButton a[id*='_reset']").addClass("btn btn-sm btn-info").find(".ui-icon").attr("class", "ace-icon fa fa-retweet");
                buttons.find(".EditButton a[id*='_query']").addClass("btn btn-sm btn-inverse").find(".ui-icon").attr("class", "ace-icon fa fa-comment-o");
                buttons.find(".EditButton a[id*='_search']").addClass("btn btn-sm btn-purple").find(".ui-icon").attr("class", "ace-icon fa fa-search");
            }

            function beforeDeleteCallback(e) {
                var form = $(e[0]);
                if (form.data("styled"))
                    return false;
                form.closest(".ui-jqdialog").find(".ui-jqdialog-titlebar").wrapInner("<div class='widget-header' />")
                style_delete_form(form);
                form.data("styled", true);
            }

            function beforeEditCallback(e) {
                var form = $(e[0]);
                form.closest(".ui-jqdialog").find(".ui-jqdialog-titlebar").wrapInner("<div class='widget-header' />")
                style_edit_form(form);
            }

            // it causes some flicker when reloading or navigating grid
            // it may be possible to have some custom formatter to do this as the grid is being created to prevent this
            // or go back to default browser checkbox styles for the grid
            function styleCheckbox(table) {
                /**
                 * $(table).find("input:checkbox").addClass("ace") .wrap("<label />") .after("<span class="lbl align-top" />") $(".ui-jqgrid-labels th[id*="_cb"]:first-child")
                 * .find("input.cbox[type=checkbox]").addClass("ace") .wrap("<label />").after("<span class="lbl align-top" />");
                 */
            }

            // unlike navButtons icons, action icons in rows seem to be hard-coded
            // you can change them like this in here if you want
            function updateActionIcons(table) {
                /**
                 * var replacement = { "ui-ace-icon fa fa-pencil" : "ace-icon fa fa-pencil blue", "ui-ace-icon fa fa-trash-o" : "ace-icon fa fa-trash-o red", "ui-icon-disk" : "ace-icon fa fa-check green", "ui-icon-cancel" :
                 * "ace-icon fa fa-times red" }; $(table).find(".ui-pg-div span.ui-icon").each(function(){ var icon = $(this); var $class = $.trim(icon.attr("class").replace("ui-icon", "")); if($class in replacement)
                 * icon.attr("class", "ui-icon "+replacement[$class]); })
                 */
            }

            // replace icons with FontAwesome icons like above
            function updatePagerIcons(table) {
                var replacement = {
                    "ui-icon-seek-first": "ace-icon fa fa-angle-double-left bigger-140",
                    "ui-icon-seek-prev": "ace-icon fa fa-angle-left bigger-140",
                    "ui-icon-seek-next": "ace-icon fa fa-angle-right bigger-140",
                    "ui-icon-seek-end": "ace-icon fa fa-angle-double-right bigger-140"
                };
                $(".ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon").each(function () {
                    var icon = $(this);
                    var $class = $.trim(icon.attr("class").replace("ui-icon", ""));

                    if ($class in replacement)
                        icon.attr("class", "ui-icon " + replacement[$class]);
                })
            }

            function enableTooltips(table) {
                $(".navtable .ui-pg-button").tooltip({
                    container: "body"
                });
                $(table).find(".ui-pg-div").tooltip({
                    container: "body"
                });
            }

            // var selr = jQuery(grid_selector).jqGrid("getGridParam","selrow");

            $(document).one("ajaxloadstart.page", function (e) {
                $(grid_selector).jqGrid("GridUnload");
                $(".ui-jqdialog").remove();
            });

            function toastMessage(title, text) {
                $.gritter.add({
                    title: title,
                    text: text,
                    class_name: 'gritter-info gritter-center'
                });
                return;
            }



            $("#downloadReportButton").bind("click", function () {
                var selectedId = $(grid_selector).jqGrid("getGridParam", "selrow");
                if (null == selectedId) {
                    $.gritter.add({
                        title: "系统信息",
                        text: "请选择记录",
                        class_name: "gritter-info gritter-center"
                    });
                } else {
                    window.location.href="${contextPath}/static/word/客户物业费情况表.xls";
                }
            });




            function showEcharts(rowid) {
                $('#div1').css("display","");
                $('#div2').css("display","");
                var myChart = echarts.init(document.getElementById('main'));
                var option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'right',
                        data:['收入预算','能源费','办公总务费','业务外包费','日常修理费','其他']
                    },
                    series: [
                        {
                            name:'预算',
                            type:'pie',
                            selectedMode: 'single',
                            radius: [0, '30%'],
                            label: {
                                normal: {
                                    position: 'inner'
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data:[
                                {value:5891818, name:'收入预算', selected:true},
                                {value:4891352, name:'支出预算'}
                            ]
                        },
                        {
                            name:'预算',
                            type:'pie',
                            radius: ['40%', '55%'],
                            label: {
                                normal: {
                                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                                    backgroundColor: '#eee',
                                    borderColor: '#aaa',
                                    borderWidth: 1,
                                    borderRadius: 4,
                                    rich: {
                                        a: {
                                            color: '#999',
                                            lineHeight: 22,
                                            align: 'center'
                                        },
                                        hr: {
                                            borderColor: '#aaa',
                                            width: '100%',
                                            borderWidth: 0.5,
                                            height: 0
                                        },
                                        b: {
                                            fontSize: 16,
                                            lineHeight: 33
                                        },
                                        per: {
                                            color: '#eee',
                                            backgroundColor: '#334455',
                                            padding: [2, 4],
                                            borderRadius: 2
                                        }
                                    }
                                }
                            },
                            data:[
                                {value:5891818, name:'收入预算'},
                                {value:3054201, name:'能源费'},
                                {value:144350, name:'办公总务费'},
                                {value:1672800, name:'业务外包费'},
                                {value:20000, name:'日常修理费'},
                                {value:0, name:'其他'}
                            ]
                        }
                    ]
                };
                myChart.setOption(option);

                var myChart2 = echarts.init(document.getElementById('main2'));
                var option2 = {
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['物业管理费','固定停车费','临时停车费','服务费(含基站）','广告费','仓储费/经营开发'
                        ,'租线费','电费收入','水费收入']
                    },
                    series : [
                        {
                            name: '收入预算',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:[
                                {value:2000000, name:'物业管理费'},
                                {value:268800, name:'固定停车费'},
                                {value:443000, name:'临时停车费'},
                                {value:70444, name:'服务费(含基站）'},
                                {value:42400, name:'广告费'},
                                {value:0, name:'仓储费/经营开发'},
                                {value:0, name:'租线费'},
                                {value:2854369, name:'电费收入'},
                                {value:212805, name:'水费收入'}
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                myChart2.setOption(option2);


                var myChart3 = echarts.init(document.getElementById('main3'));
                var option3 = {
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['水费','电费','燃气费']
                    },
                    series : [
                        {
                            name: '收入预算',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:[
                                {value:228297, name:'水费'},
                                {value:2825905, name:'电费'},
                                {value:0, name:'燃气费'}
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                myChart3.setOption(option3);

                var myChart4 = echarts.init(document.getElementById('main4'));
                var option4 = {
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['办公用品','电话、通信费','饮用水','门牌制作','节假日布置','保洁用品'
                            ,'绿化费','劳防用品','垃圾清运费','应急物资','外墙、水箱清洗','报警服务费'
                            ,'灭虫害服务费','污水处理（疏通）费','地坪保养费','其他']
                    },
                    series : [
                        {
                            name: '收入预算',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:[
                                {value:30000, name:'办公用品'},
                                {value:3330, name:'电话、通信费'},
                                {value:15000, name:'饮用水'},
                                {value:2000, name:'门牌制作'},
                                {value:4000, name:'节假日布置'},
                                {value:6000, name:'保洁用品'},
                                {value:36000, name:'绿化费'},
                                {value:1400, name:'劳防用品'},
                                {value:4000, name:'垃圾清运费'},
                                {value:10000, name:'应急物资'},
                                {value:25980, name:'外墙、水箱清洗'},
                                {value:2640, name:'报警服务费'},
                                {value:0, name:'灭虫害服务费'},
                                {value:4000, name:'污水处理（疏通）费'},
                                {value:0, name:'地坪保养费'},
                                {value:0, name:'其他'}
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                myChart4.setOption(option4);


                var myChart5 = echarts.init(document.getElementById('main5'));
                var option5 = {
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['保安服务费','保洁服务费','工程外包']
                    },
                    series : [
                        {
                            name: '收入预算',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:[
                                {value:1058400, name:'保安服务费'},
                                {value:336000, name:'保洁服务费'},
                                {value:278400, name:'工程外包'}
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                myChart5.setOption(option5);

                var myChart6 = echarts.init(document.getElementById('main6'));
                var option6 = {
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['材料费','灭火器']
                    },
                    series : [
                        {
                            name: '收入预算',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:[
                                {value:20000, name:'材料费'},
                                {value:0, name:'灭火器'}
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                myChart6.setOption(option6);

            }
        });
    });
    function getmsg() {
        $("#modal-htxx_test").modal("show");
    }
</script>
