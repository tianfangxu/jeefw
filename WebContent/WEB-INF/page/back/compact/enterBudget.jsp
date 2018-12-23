<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<link rel="stylesheet"
	href="${contextPath}/static/assets/css/jquery-ui.css" />
<link rel="stylesheet"
	href="${contextPath}/static/assets/css/ui.jqgrid.css" />
<link rel="stylesheet"
	href="${contextPath}/static/assets/css/jquery.gritter.css" />
<link rel="stylesheet"
	href="${contextPath}/static/assets/css/datepicker.css" />
<link rel="stylesheet"
	href="${contextPath}/static/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet"
	href="${contextPath}/static/assets/css/daterangepicker.css" />
<link rel="stylesheet"
	href="${contextPath}/static/assets/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet"
	href="${contextPath}/static/assets/css/select2.css" />
<link rel="stylesheet"
	href="${contextPath}/static/assets/css/chartsreports/sb-admin.css" />
<link rel="stylesheet"
	href="${contextPath}/static/assets/css/chartsreports/morris.css" />

<script type="text/javascript">
	$('.ajax-loading-overlay').css('display','none');
</script>
<style>
.inputc {
	width: 80%;
	text-align: center;
}
</style>
<body>
	<div class="row">
		<div class="col-xs-12">
			<div class="well well-sm">
				<select class="col-xs-2 " id="lyxx" onchange="getbudgetMsgxx()"
					style="margin-right: 20px;">

				</select>

				<shiro:hasPermission name="${ROLE_KEY}:enterBudget:add">
					<a id="addInformationButton" role="button"
						class="btn btn-info btn-sm" data-toggle="modal"> 录入 </a>
				</shiro:hasPermission>
				<shiro:lacksPermission name="${ROLE_KEY}:enterBudget:add">
					<a id="addInformationButton" disabled="disabled" role="button"
						class="btn btn-info btn-sm" data-toggle="modal"> 录入 </a>
				</shiro:lacksPermission>
				<shiro:hasPermission name="${ROLE_KEY}:enterBudget:edit">
					<a id="editInformationButton" role="button"
						class="btn btn-purple btn-sm" data-toggle="modal"> 编辑 </a>
				</shiro:hasPermission>
				<shiro:lacksPermission name="${ROLE_KEY}:enterBudget:edit">
					<a id="editInformationButton" role="button" disabled="disabled"
						class="btn btn-purple btn-sm" data-toggle="modal"> 编辑 </a>
				</shiro:lacksPermission>
			</div>
			<table id="grid-table"></table>
			<table id="grid-pager"></table>
			<div class="row" style="display: none" id="div1">
				<div class="col-xs-4">
					<div class="panel panel-green">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-pie-chart"></i>收入支出预算占比图
							</h3>
						</div>
						<div class="panel-body">
							<div class="flot-chart">
								<div class="flot-chart-content" id="main"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="panel panel-green">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-pie-chart"></i>收入明细图
							</h3>
						</div>
						<div class="panel-body">
							<div class="flot-chart">
								<div class="flot-chart-content" id="main2"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="panel panel-green">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-pie-chart"></i>能源费明细图
							</h3>
						</div>
						<div class="panel-body">
							<div class="flot-chart">
								<div class="flot-chart-content" id="main3"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row" style="display: none" id="div2">
				<div class="col-xs-4">
					<div class="panel panel-green">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-pie-chart"></i>办公总务明细图
							</h3>
						</div>
						<div class="panel-body">
							<div class="flot-chart">
								<div class="flot-chart-content" id="main4"></div>
							</div>
						</div>
					</div>
				</div>
				<%--<div class="col-xs-4">
					<div class="panel panel-green">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-pie-chart"></i>业务外包明细图
							</h3>
						</div>
						<div class="panel-body">
							<div class="flot-chart">
								<div class="flot-chart-content" id="main5"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-4">
					<div class="panel panel-green">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-pie-chart"></i>日常修理明细图
							</h3>
						</div>
						<div class="panel-body">
							<div class="flot-chart">
								<div class="flot-chart-content" id="main6"></div>
							</div>
						</div>
					</div>
				</div>--%>
			</div>
		</div>
	</div>


	<%--<div style="display: none" id="table_in">--%>
	<div id="table_in" class="modal fade" tabindex="-1"
		data-backdrop="static">
		<div class="modal-dialog" style="min-width: 820px;">
			<form id="">
				<div class="modal-content">
					<div class="modal-header no-padding">
						<div class="table-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">
								<span class="white">&times;</span>
							</button>
							信息新增
						</div>
					</div>
					<div class="modal-body"
						style="max-height: 500px; overflow-y: scroll;">

						<div class="col-xs-12 col-sm-12">
							<span style="font-size: large">年份：</span><input type="text"
								style="text-align: center; margin-left: 8px; border: none; border-bottom: 1px solid #d5d5d5;"
								id="year" />
							<div class="widget-box">
								<div class="widget-header">
									<h4 class="widget-title">收入</h4>
								</div>
								<input type="hidden" id="id">
								<div class="widget-body">
									<div class="widget-main">
										<table class="table table-striped table-bordered table-hover"
											style="text-align: center;" id="income">
											<tr>
												<td colspan="2" style="text-align: center;">项目</td>
												<td style="text-align: center; width: 33%">全年预算</td>
												<td style="text-align: center; width: 33%">备注</td>
											</tr>
											<tr>
												<td colspan="2">租金</td>
												<td><input id="rent" type="text" class="inputc num"></td>
												<td><input id="rentps" type="text" class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="2">物业管理费</td>
												<td><input id="property" type="text" class="inputc num"></td>
												<td><input id="propertyps" type="text"
													class="inputc ps"></td>
											</tr>
											<tr>
												<td rowspan="2" style="vertical-align: middle;">停车费</td>
												<td>固定停车费</td>
												<td><input id="fixedparking" type="text"
													class="inputc num"></td>
												<td><input id="fixedparkingps" type="text"
													class="inputc ps"></td>
											</tr>
											<tr>
												<td>临时停车费</td>
												<td><input id="tempparking" type="text"
													class="inputc num"></td>
												<td><input id="tempparkingps" type="text"
													class="inputc ps"></td>
											</tr>
											<%--<tr>
                                               <td colspan="2">服务费</td>
                                                <td><input id="service" type="text" class="inputc num"></td>
                                               <td><input type="text" class="inputc"></td>
                                           </tr>--%>

											<tr>
												<td colspan="2">广告费</td>
												<td><input id="advertising" type="text"
													class="inputc num"></td>
												<td><input id="advertisingps" type="text"
													class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="2">其他经营收入</td>
												<td><input id="rest" type="text" class="inputc num"></td>
												<td><input id="restps" type="text" class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="2">维修材料</td>
												<td><input id="servicing" type="text"
													class="inputc num"></td>
												<td><input id="servicingps" type="text"
													class="inputc ps"></td>
											</tr>
											<%--<tr>
                                           <td colspan="2">仓储费/经营开发</td>
                                            <td><input id="warehouse" type="text" class="inputc num"></td>
                                               <td><input type="text" class="inputc"></td>
                                       </tr><tr>
                                           <td colspan="2">租线费</td>
                                           <td><input id="rental" type="text" class="inputc num"></td>
                                               <td><input type="text" class="inputc"></td>
                                       </tr>--%>
											<tr>
												<td colspan="2">电费</td>
												<td><input id="electricin" type="text"
													class="inputc num"></td>
												<td><input id="electricinps" type="text"
													class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="2">水费</td>
												<td><input id="waterin" type="text" class="inputc num"></td>
												<td><input id="waterinps" type="text" class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="4" style="text-align: left;">小计：<span
													id="sumincome" style="color: red">0.00</span></td>
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
										<table class="table table-striped table-bordered table-hover"
											style="text-align: center;">
											<tr>
												<td rowspan="3" style="vertical-align: middle;">能源费</td>
												<td colspan="2">水费</td>
												<td style="width: 33%"><input id="water" type="text"
													class="inputc num"></td>
												<td style="width: 33%"><input id="waterps" type="text"
													class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="2">电费</td>
												<td><input id="electricout" type="text"
													class="inputc num"></td>
												<td><input id="electricoutps" type="text"
													class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="2">燃气费</td>
												<td><input id="gas" type="text" class="inputc num"></td>
												<td><input id="gasps" type="text" class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="5" style="text-align: left;">小计:<span
													id="sum0" style="color: red;">0.00</span>
												</td>
											</tr>
										</table>
										<table class="table table-striped table-bordered table-hover"
											style="text-align: center;">
											<tr>
												<td rowspan="16" style="vertical-align: middle;">办公总务</td>
												<td colspan="2">办公用品</td>
												<td style="width: 33%"><input id="stationery"
													type="text" class="inputc num"></td>
												<td style="width: 33%"><input id="stationeryps"
													type="text" class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="2">电话、通信费等</td>
												<td><input id="communication" type="text"
													class="inputc num"></td>
												<td><input id="communicationps" type="text"
													class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="2">饮用水等</td>
												<td><input id="drinkwater" type="text"
													class="inputc num"></td>
												<td><input id="drinkwaterps" type="text"
													class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="2">门牌制作等</td>
												<td><input id="doorplate" type="text"
													class="inputc num"></td>
												<td><input id="doorplateps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">节假日布置</td>
												<td><input id="decorate" type="text" class="inputc num"></td>
												<td><input id="decorateps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">保洁用品</td>
												<td><input id="cleanser" type="text" class="inputc num"></td>
												<td><input id="cleanserps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">绿化费</td>
												<td><input id="afforestation" type="text"
													class="inputc num"></td>
												<td><input id="afforestationps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">劳防用品</td>
												<td><input id="ppe" type="text" class="inputc num"></td>
												<td><input id="ppeps" type="text" class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">垃圾清运费</td>
												<td><input id="trashcleaning" type="text"
													class="inputc num"></td>
												<td><input id="trashcleaningps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">应急物资</td>
												<td><input id="emergencymaterial" type="text"
													class="inputc num"></td>
												<td><input id="emergencymaterialps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">外墙、水箱清洗</td>
												<td><input id="wallwashing" type="text"
													class="inputc num"></td>
												<td><input id="wallwashingps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">报警服务费</td>
												<td><input id="alarmservice" type="text"
													class="inputc num"></td>
												<td><input id="alarmserviceps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">灭虫害服务费</td>
												<td><input id="pestcontrol" type="text"
													class="inputc num"></td>
												<td><input id="pestcontrolps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">污水处理（疏通）费</td>
												<td><input id="sewerage" type="text" class="inputc num"></td>
												<td><input id="sewerageps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">地坪保养费</td>
												<td><input id="maintenance" type="text"
													class="inputc num"></td>
												<td><input id="maintenanceps" type="text"
													class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="2">其他</td>
												<td><input id="office" type="text" class="inputc num"></td>
												<td><input id="officeps" type="text" class="inputc ps"></td>
											</tr>

											<tr>
												<td colspan="5" style="text-align: left;">小计:<span
													id="sum1" style="color: red;">0.00 </span>
												</td>
											</tr>
										</table>
										<%-- <table class="table table-striped table-bordered table-hover" style="text-align: center;">
                                           <tr>
                                               <td rowspan="3" style="vertical-align: middle;">业务外包</td>
                                               <td colspan="2">保安服务费</td>
                                               <td style="width: 33%"><input id="security" type="text" class="inputc num"></td>
                                               <td style="width: 33%"><input type="text" class="inputc"></td>
                                           </tr>
                                           <tr>
                                               <td colspan="2">保洁服务费</td>
                                               <td style="width: 33%"><input id="cleansing" type="text" class="inputc num"></td>
                                               <td style="width: 33%"><input type="text" class="inputc"></td>
                                           </tr>
                                           <tr>
                                               <td colspan="2">工程外包</td>
                                               <td style="width: 33%"><input id="projectout" type="text" class="inputc num"></td>
                                               <td style="width: 33%"><input type="text" class="inputc"></td>
                                           </tr>
                                           <tr>
                                               <td colspan="5" style="text-align: left;">合计:<span id="sum2" style="color:red;">0.00</span> </td>
                                           </tr>
                                       </table>
                                       <table class="table table-striped table-bordered table-hover" style="text-align: center;">
                                           <tr>
                                               <td colspan="2">大修理费用</td>
                                               <td style="width: 33%"><input id="repair" type="text" class="inputc num"></td>
                                               <td style="width: 33%"><input type="text" class="inputc"></td>
                                           </tr>
                                           <tr>
                                               <td rowspan="3" style="vertical-align: middle;">设备设施维护</td>
                                               <td>消防设备设施维护费</td>
                                               <td style="width: 33%"><input id="firefighting" type="text" class="inputc num"></td>
                                               <td style="width: 33%"><input type="text" class="inputc"></td>
                                           </tr>
                                           <tr>
                                               <td>工程设备设施维护费</td>
                                               <td style="width: 33%"><input id="engineering" type="text" class="inputc num"></td>
                                               <td style="width: 33%"><input type="text" class="inputc"></td>
                                           </tr>
                                           <tr>
                                               <td>设备设施检测费</td>
                                               <td style="width: 33%"><input id="equipmenttesting" type="text" class="inputc num"></td>
                                               <td style="width: 33%"><input type="text" class="inputc"></td>
                                           </tr>
                                           <tr>
                                               <td rowspan="3" style="vertical-align: middle;">日常修理费用</td>
                                               <td>材料费</td>
                                               <td style="width: 33%"><input id="material" type="text" class="inputc num"></td>
                                               <td style="width: 33%"><input type="text" class="inputc"></td>
                                           </tr>
                                           <tr>
                                               <td>灭火机</td>
                                               <td style="width: 33%"><input id="extinguisher" type="text" class="inputc num"></td>
                                               <td style="width: 33%"><input type="text" class="inputc"></td>
                                           </tr>
                                           <tr>
                                               <td>日常维修费</td>
                                               <td style="width: 33%"><input id="upkeep" type="text" class="inputc num"></td>
                                               <td style="width: 33%"><input type="text" class="inputc"></td>
                                           </tr>
                                           <tr>
                                               <td colspan="5" style="text-align: left;">合计:<span id="sum3" style="color:red;">0.00</span> </td>
                                           </tr>
                                       </table>--%>
										<table class="table table-striped table-bordered table-hover"
											style="text-align: center;">
											<tr>
												<td>其他</td>
												<td style="width: 33%"><input id="other" type="text"
													class="inputc num"></td>
												<td style="width: 33%"><input id="otherps" type="text"
													class="inputc ps"></td>
											</tr>
											<tr>
												<td colspan="3" style="text-align: left;">小计:<span
													id="sum2" style="color: red;">0.00</span>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-12">
							<div class="col-xs-12 col-sm-6">
								<div style="color: red; font-size: 24px">
									毛利润：<span id="sum5">0.00</span>
								</div>
							</div>
							<div class="col-xs-12 col-sm-6" style="text-align: right;">
								<div class="btn btn-success btn-lg" onclick="submitBudget()">确定</div>
							</div>
						</div>
					</div>
					<div class="modal-footer no-margin-top">
						<div class="text-center">
							<button class="btn btn-app btn-pink btn-xs" data-dismiss="modal">
								<i class="ace-icon fa fa-share bigger-160"></i> 取消
							</button>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</form>
		</div>
		<!-- /.modal-dialog -->
	</div>

	<div id="modal-htxx_test" class="modal fade" tabindex="-1"
		data-backdrop="static">
		<div class="modal-dialog" style="min-width: 1200px;">
			<form id="informationForm">
				<div class="modal-content">
					<div class="modal-header no-padding">
						<div class="table-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">
								<span class="white">&times;</span>
							</button>
							预算成本表
						</div>
					</div>
					<div class="modal-body"
						style="max-height: 700px; overflow-y: scroll;">
						<div class="col-xs-12 col-sm-12">
							<table class="table table-striped table-bordered table-hover">
								<tr>
									<td>毛利润：<span style="color: green;" id="sum_info">2969600.00
									</span></td>
								</tr>
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
												<td style="text-align: center;">全年预算</td>
												<td style="text-align: center;">备注</td>
											</tr>
											<tr>
												<td colspan="2">租金</td>
												<td id="rent_info" class="cass"></td>
												<td id="rentps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="2">物业管理费</td>
												<td id="property_info" class="cass"></td>
												<td id="propertyps_info" class="cass"></td>
											</tr>
											<tr>
												<td rowspan="2" style="vertical-align: middle;">停车费</td>
												<td>固定停车费</td>
												<td id="fixedparking_info" class="cass"></td>
												<td id="fixedparkingps_info" class="cass"></td>
											</tr>
											<tr>
												<td>临时停车费</td>
												<td id="tempparking_info" class="cass"></td>
												<td id="tempparkingps_info" class="cass"></td>
											</tr>
											<%--<tr>
                                            <td colspan="2">服务费</td>
                                            <td id="service_info">84315.00</td>
                                            <td>含基站租赁</td>
                                        </tr>--%>
											<tr>
												<td colspan="2">广告费</td>
												<td id="advertising_info" class="cass"></td>
												<td id="advertisingps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="2">其他经营收入</td>
												<td id="rest_info" class="cass"></td>
												<td id="restps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="2">维修材料</td>
												<td id="servicing_info" class="cass"></td>
												<td id="servicingps_info" class="cass"></td>
											</tr>
											<%--<tr>
                                            <td colspan="2">仓储费/经营开发</td>
                                            <td id="warehouse_info">35000.00</td>
                                            <td>有服务合同</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">租线费</td>
                                            <td id="rental_info">70000.00</td>
                                            <td>备注</td>
                                        </tr>--%>
											<tr>
												<td colspan="2">电费</td>
												<td id="electricin_info" class="cass"></td>
												<td id="electricinps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="2">水费</td>
												<td id="waterin_info" class="cass"></td>
												<td id="waterinps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="4">小计：<span style="color: red" id="sum0_info"></span></td>

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
												<td style="text-align: center;">全年预算</td>
												<td style="text-align: center;">备注</td>
											</tr>
											<tr>
												<td rowspan="3" style="vertical-align: middle;">能源费</td>
												<td colspan="2">水费</td>
												<td id="water_info" class="cass"></td>
												<td id="waterps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="2">电费</td>
												<td id="electricout_info" class="cass"></td>
												<td id="electricoutps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="2">燃气费</td>
												<td id="gas_info" class="cass"></td>
												<td id="gasps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="5">小计:<span style="color: red;" id="sum1_info"></span>
												</td>
											</tr>
										</table>
										<table class="table table-striped table-bordered table-hover">
											<tr>
												<td colspan="3" style="text-align: center;">项目</td>
												<td style="text-align: center;">全年预算</td>
												<td style="text-align: center;">备注</td>
											</tr>
											<tr>
												<td rowspan="16" style="vertical-align: middle;">办公总务</td>
												<td colspan="2">办公用品</td>
												<td id="stationery_info" class="cass"></td>
												<td id="stationeryps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="2">电话、通信费等</td>
												<td id="communication_info" class="cass"></td>
												<td id="communicationps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="2">饮用水等</td>
												<td id="drinkwater_info" class="cass"></td>
												<td id="drinkwaterps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="2">门牌制作等</td>
												<td id="doorplate_info" class="cass"></td>
												<td id="doorplateps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">节假日布置</td>
												<td id="decorate_info" class="cass"></td>
												<td id="decorateps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">保洁用品</td>
												<td id="cleanser_info" class="cass"></td>
												<td id="cleanserps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">绿化费</td>
												<td id="afforestation_info" class="cass"></td>
												<td id="afforestationps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">劳防用品</td>
												<td id="ppe_info" class="cass"></td>
												<td id="ppeps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">垃圾清运费</td>
												<td id="trashcleaning_info" class="cass"></td>
												<td id="trashcleaningps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">应急物资</td>
												<td id="emergencymaterial_info" class="cass"></td>
												<td id="emergencymaterialps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">外墙、水箱清洗</td>
												<td id="wallwashing_info" class="cass"></td>
												<td id="wallwashingps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">报警服务费</td>
												<td id="alarmservice_info" class="cass"></td>
												<td id="alarmserviceps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">灭虫害服务费</td>
												<td id="pestcontrol_info" class="cass"></td>
												<td id="pestcontrolps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">污水处理（疏通）费</td>
												<td id="sewerage_info" class="cass"></td>
												<td id="sewerageps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">地坪保养费</td>
												<td id="maintenance_info" class="cass"></td>
												<td id="maintenanceps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="2">其他</td>
												<td id="office_info" class="cass"></td>
												<td id="officeps_info" class="cass"></td>
											</tr>

											<tr>
												<td colspan="5">小计:<span style="color: red;" id="sum2_info">
												</span>
												</td>
											</tr>
										</table>
										<%--<table class="table table-striped table-bordered table-hover">
                                        <tr>
                                            <td colspan="3" style="text-align: center;">项目</td>
                                            <td style="text-align: center;">全年预算</td>
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
                                            <td colspan="5">小计:<span style="color:red;">0.00</span> </td>
                                        </tr>
                                    </table>
                                    <table class="table table-striped table-bordered table-hover">
                                        <tr>
                                            <td colspan="3" style="text-align: center;">项目</td>
                                            <td style="text-align: center;">全年预算</td>
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
                                    </table>--%>
										<table class="table table-striped table-bordered table-hover">
											<tr>
												<td>其他</td>
												<td id="other_info" class="cass"></td>
												<td id="otherps_info" class="cass"></td>
											</tr>
											<tr>
												<td colspan="3">小计:<span style="color: red;" id="sum3_info"></span>
												</td>
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
								<i class="ace-icon fa fa-share bigger-160"></i> 取消
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, "${contextPath}/static/assets/js/jqGrid/jquery.jqGrid.js", "${contextPath}/static/assets/js/jqGrid/i18n/grid.locale-cn.js", "${contextPath}/static/assets/js/jquery-ui.custom.js",
        "${contextPath}/static/assets/js/jquery.ui.touch-punch.js", "${contextPath}/static/assets/js/markdown/markdown.js", "${contextPath}/static/assets/js/markdown/bootstrap-markdown.js",
        "${contextPath}/static/assets/js/jquery.hotkeys.js", "${contextPath}/static/assets/js/bootstrap-wysiwyg.js", "${contextPath}/static/assets/js/bootbox.js",
        "${contextPath}/static/assets/js/jquery.gritter.js", "${contextPath}/static/assets/js/date-time/bootstrap-datepicker.js", "${contextPath}/static/assets/js/date-time/bootstrap-timepicker.js",
        "${contextPath}/static/assets/js/date-time/moment.js", "${contextPath}/static/assets/js/date-time/daterangepicker.js", "${contextPath}/static/assets/js/date-time/bootstrap-datetimepicker.js",
        "${contextPath}/static/assets/js/select2.js","${contextPath}/static/assets/js/echarts.js",null]
    
    $(".page-content-area").ace_ajax("loadScripts", scripts, function() {
        // inline scripts related to this page
        jQuery(function($) {

            var grid_selector = "#grid-table";
            var pager_selector = "#grid-pager";

            // resize to fit page size
            $(window).on("resize.jqGrid", function() {
                $(grid_selector).jqGrid("setGridWidth", $(".page-content").width());
            })
            // resize on sidebar collapse/expand
            var parent_column = $(grid_selector).closest("[class*='col-']");
            $(document).on("settings.ace.jqGrid", function(ev, event_name, collapsed) {
                if (event_name === "sidebar_collapsed" || event_name === "main_container_fixed") {
                    // setTimeout is for webkit only to give time for DOM changes and then redraw!!!
                    setTimeout(function() {
                        $(grid_selector).jqGrid("setGridWidth", parent_column.width());
                    }, 0);
                }
            })

            //楼宇信息初始化
            getbuildMsg();
            //判断输入的是否是数字
            isnumber();
            //合计计算
            sumnumber();
            //日期初始化
            $('#year').val(new Date().getFullYear());

            $(document).keydown(function(event) {
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
                            dataType : "json",
                            url : "${contextPath}/sys/information/getInformationHibernateSearch",
                            type : "post",
                            data : {
                                luceneName : $("#search-input").val()
                            },
                            complete : function(response) {
                                var result = eval("("+response.responseText+")");
                                jQuery(grid_selector)[0].addJSONData(result);
                            }
                        });
                    }
                }
            });

            jQuery(grid_selector).jqGrid({
                subGrid : false,
                url: "${contextPath}//recode/budget/getBudgetByCondition",
                datatype: "json",
                height: 450,
                colNames: ["ID","楼宇", "年份", "收入预算合计", "成本预算合计", "能源费合计", "办公总务合计", "业务外包合计", "修理费合计", "其他合计", "操作"],
                colModel: [{
                    name: "id",
                    width: 50,
                    hidden:true,
                },
                    {
                        name: "buildname",
                        width: 100,
                        searchoptions : {sopt : ["cn","eq"]},
                    },
                    {
                        name: "year",
                        width: 100,
                        search:false,
                    },
                    {
                        name: "sumincome",
                        width: 100,
                        search:false,
                    },
                    {
                        name: "sumcost",
                        width: 100,
                        search:false,
                    },
                    {
                        name: "sumenergy",
                        width: 100,
                        search:false,
                    },
                    {
                        name: "sumoffice",
                        width: 100,
                        search:false,
                    },
                    {
                        name: "sumbusiness",
                        width: 100,
                        search:false,
                        hidden:true,
                    },
                    {
                        name: "sumfixed",
                        width: 100,
                        search:false,
                        hidden:true,
                    },
                    {
                        name: "sumelsed",
                        width: 100,
                        search:false,
                    },
                    {   name: "",
                        width: 70,
                        search:false,
                        formatter:function(value,options,rows){
                            return '<a class="btn btn-info btn-sm" onclick="getmsg(\''+rows.id+'\',\'info\')">详情</a>';
                        }
                    }
                ],
                //scroll : 1, // set the scroll property to 1 to enable paging with scrollbar - virtual loading of records
                sortname : "id",
                sortorder : "asc",
                viewrecords : true,
                rowNum : 10,
                rowList : [ 10, 20, 30 ],
                pager : pager_selector,
                altRows : true,
                //toppager : true,
                multiselect : true,
                //multikey : "ctrlKey",
                multiboxonly : true,
                loadComplete : function() {
                    var table = this;
                    setTimeout(function(){
                        styleCheckbox(table);
                        updateActionIcons(table);
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },
                editurl : "${contextPath}/recode/budget/delBudget",
                onSelectRow: function (id){
                	return;
                    rentPhotot(id);
                }
            });

            $(window).triggerHandler("resize.jqGrid");// trigger window resize to make the grid get the correct size

            // enable search/filter toolbar
            // jQuery(grid_selector).jqGrid("filterToolbar",{defaultSearch:true,stringResult:true})
            // jQuery(grid_selector).filterToolbar({});
            // switch element when editing inline
            function aceSwitch(cellvalue, options, cell) {
                setTimeout(function() {
                    $(cell).find("input[type=checkbox]").addClass("ace ace-switch ace-switch-5").after("<span class='lbl'></span>");
                }, 0);
            }

            $("#editor").ace_wysiwyg({
                toolbar:
                    [
                        "font",
                        null,
                        "fontSize",
                        null,
                        {name:"bold", className:"btn-info"},
                        {name:"italic", className:"btn-info"},
                        {name:"strikethrough", className:"btn-info"},
                        {name:"underline", className:"btn-info"},
                        null,
                        {name:"insertunorderedlist", className:"btn-success"},
                        {name:"insertorderedlist", className:"btn-success"},
                        {name:"outdent", className:"btn-purple"},
                        {name:"indent", className:"btn-purple"},
                        null,
                        {name:"justifyleft", className:"btn-primary"},
                        {name:"justifycenter", className:"btn-primary"},
                        {name:"justifyright", className:"btn-primary"},
                        {name:"justifyfull", className:"btn-inverse"},
                        null,
                        {name:"createLink", className:"btn-pink"},
                        {name:"unlink", className:"btn-pink"},
                        null,
                        {name:"insertImage", className:"btn-success"},
                        null,
                        "foreColor",
                        null,
                        {name:"undo", className:"btn-grey"},
                        {name:"redo", className:"btn-grey"}
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

            $("#addInformationButton").bind("click", function() {
            	
                if($('#lyxx').val() == '' || $('#lyxx').val() == null){
                    $.gritter.add({
                        title: '错误',
                        text: '请先选择楼宇',
                        class_name:"gritter-error  gritter-light"
                    });
                    return;
                }
                $("#id").val('');
                $("#table_in").modal("toggle");
                $("#informationForm")[0].reset();
                $('.num').each(function(i){
                    $(this).val('');
                });
                $('.ps').each(function(){
                    $(this).val('');
                });
                $("#editor").html("");
                $("#modal-tip").html("");
            });

            $("#editInformationButton").bind("click", function() {
                var selectedId = $(grid_selector).jqGrid("getGridParam", "selrow");
                if(null == selectedId){
                    $.gritter.add({
                        title: "系统信息",
                        text: "请选择记录",
                        class_name: "gritter-info gritter-center"
                    });
                }else{
                    $("#table_in").modal("toggle");
                    budgetInfoToview(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).id,'edit');
                }
            });

            $("#informationForm").on("submit", function(e) {
                var data = new Object();
                data.name=$("#name").val();
                data.build=$("#buil").val();
                data.area=$("#area").val();
                data.price=$("#price").val();
                data.used=$("input[name='used']:checked").val();
                data.type=$("input[name='type']:checked").val();
                if($("#id").val() == '' || $("#id").val() == null || $("#id").val() == undefined){
                }else{
                    data.id = $("#id").val();
                }
                $.ajax({
                    dataType : "json",
                    url : "${contextPath}/recode/parking/saveOrupdParking",
                    type : "post",
                    contentType: 'application/json',
                    data :JSON.stringify(data),
                    complete : function(xmlRequest) {
                        $("#modal-table").modal("toggle");
                        jQuery(grid_selector).trigger("reloadGrid");
                    }
                });
            });

            // navButtons
            jQuery(grid_selector).jqGrid("navGrid", pager_selector, { // navbar options
                edit : false,
                editicon : "ace-icon fa fa-pencil blue",
                add : false,
                addicon : "ace-icon fa fa-plus-circle purple",
                del : <shiro:hasPermission name="${ROLE_KEY}:enterBudget:delete">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:enterBudget:delete">false</shiro:lacksPermission>,
                delicon : "ace-icon fa fa-trash-o red",
                search : <shiro:hasPermission name="${ROLE_KEY}:enterBudget:search">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:enterBudget:search">false</shiro:lacksPermission>,
                searchicon : "ace-icon fa fa-search orange",
                refresh : true,
                refreshicon : "ace-icon fa fa-refresh blue",
                view : <shiro:hasPermission name="${ROLE_KEY}:enterBudget:view">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:enterBudget:view">false</shiro:lacksPermission>,
                viewicon : "ace-icon fa fa-search-plus grey"
            }, {
                // edit record form
                // closeAfterEdit: true,
                // width: 700,
                recreateForm : true,
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-titlebar").wrapInner("<div class='widget-header' />")
                    style_edit_form(form);
                },
                errorTextFormat: function (response) {
                    var result = eval("("+response.responseText+")");
                    return result.message;
                }
            }, {
                // new record form
                // width: 700,
                closeAfterAdd : true,
                recreateForm : true,
                viewPagerButtons : false,
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-titlebar").wrapInner("<div class='widget-header' />")
                    style_edit_form(form);
                },
                errorTextFormat: function (response) {
                    var result = eval("("+response.responseText+")");
                    return result.message;
                }
            }, {
                // delete record form
                recreateForm : true,
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    if (form.data("styled"))
                        return false;
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-titlebar").wrapInner("<div class='widget-header' />")
                    style_delete_form(form);
                    form.data("styled", true);
                },
                onClick : function(e) {
                    // alert(1);
                }
            }, {
                // search form
                recreateForm : true,
                afterShowSearch : function(e) {
                    var form = $(e[0]);
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-title").wrap("<div class='widget-header' />")
                    style_search_form(form);
                },
                afterRedraw : function() {
                    style_search_filters($(this));
                },
                multipleSearch : true
                /**
                 * multipleGroup:true, showQuery: true
                 */
            }, {
                // view record form
                recreateForm : true,
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-title").wrap("<div class='widget-header' />")
                }
            })

            // add custom button to export the data to excel
            if(<shiro:hasPermission name="${ROLE_KEY}:enterBudget:export">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:enterBudget:export">false</shiro:lacksPermission>){
                jQuery(grid_selector).jqGrid("navButtonAdd", pager_selector,{
                    caption : "",
                    title : "导出Excel",
                    buttonicon : "ace-icon fa fa-file-excel-o green",
                    onClickButton : function () {
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
                        var form = "<form name='csvexportform' action='${contextPath}/sys/information/operateInformation?oper=excel' method='post'>";
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
                    "ui-icon-seek-first" : "ace-icon fa fa-angle-double-left bigger-140",
                    "ui-icon-seek-prev" : "ace-icon fa fa-angle-left bigger-140",
                    "ui-icon-seek-next" : "ace-icon fa fa-angle-right bigger-140",
                    "ui-icon-seek-end" : "ace-icon fa fa-angle-double-right bigger-140"
                };
                $(".ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon").each(function() {
                    var icon = $(this);
                    var $class = $.trim(icon.attr("class").replace("ui-icon", ""));

                    if ($class in replacement)
                        icon.attr("class", "ui-icon " + replacement[$class]);
                })
            }

            function enableTooltips(table) {
                $(".navtable .ui-pg-button").tooltip({
                    container : "body"
                });
                $(table).find(".ui-pg-div").tooltip({
                    container : "body"
                });
            }

            // var selr = jQuery(grid_selector).jqGrid("getGridParam","selrow");

            $(document).one("ajaxloadstart.page", function(e) {
                $(grid_selector).jqGrid("GridUnload");
                $(".ui-jqdialog").remove();
            });

        });
    });


    function getmsg(id,mark) {
        console.log(mark);
        //信息回显
        budgetInfoToview(id,mark);

        $("#modal-htxx_test").modal("show");
    }

    //信息回显
    function  budgetInfoToview(id,mark){
        $.ajax({
            dataType : "json",
            url : "${contextPath}//recode/budget/getBudgetByCondition?page=1&rows=1&id="+id,
            type : "post",
            contentType: 'application/json',
            data :null,
            complete : function(xmlRequest) {
                console.log(xmlRequest);
                if(xmlRequest.status == 200){
                    var data = JSON.parse(xmlRequest.responseText).rows;
                    console.log(mark);
                    if(mark == 'info'){
                        console.log(mark);
                        //详情
                        infobudgetInfoToview(data);
                    }else if(mark == 'edit'){
                        //修改
                        $('#id').val(id);
                        editbudgetInfoToview(data);
                    }

                }

            }
        });
    }

    //详情
    function infobudgetInfoToview(list){
        var data =  list[0];
        $('#sum0_info').html(data.sumincome);
        $('#sum1_info').html(data.sumenergy);
        $('#sum2_info').html(data.sumoffice);
        $('#sum3_info').html(data.sumelsed);
        $('#sum_info').html(parseFloat(data.sumincome) - parseFloat(data.sumcost));
        for(var param in data){
            $('.cass').each(function(i){
                console.log($(this).attr('id').split('_info')[0]);
                if($(this).attr('id').split('_info')[0] == param){
                    $(this).html(data[param]);
                    return false; //跳出循环
                }
            });
        }
    }
    //修改
    function editbudgetInfoToview(list){
        var data =  list[0];
        sessionStorage['build'] = data.build;
        $('#year').val(data.year);
        $('#sumincome').html(data.sumincome);
        $('#sum0').html(data.sumenergy);
        $('#sum1').html(data.sumoffice);
        $('#sum2').html(data.sumelsed);
        $('#sum5').html(parseFloat(data.sumincome) - parseFloat(data.sumcost));
        for(var param in data){
            $('.num').each(function(i){
                if($(this).attr('id') == param){
                    $(this).val(data[param]);
                    return false; //跳出循环
                }
            });
            $('.ps').each(function(){
                if($(this).attr('id') == param){
                    $(this).val(data[param]);
                    return false; //跳出循环
                }
            });
        }
    }
    
    function getbuildMsg(){
    	/* $.ajax({
			dataType : "json",
			url : "${contextPath}/recode/build/getBuildByCondition?page=1&rows=10000",
			type : "post",
			contentType: 'application/json',
  			data :null,
			complete : function(xmlRequest) {
				console.log(xmlRequest);
				if(xmlRequest.status == 200){
					var data = JSON.parse(xmlRequest.responseText).rows;
   					var html = '<option value="">请选择楼宇名称</option>';
   					if(data.length >0){
   						for(var i = 0;i < data.length ;i++){
   							html+='<option value="'+data[i].id+'">'+data[i].name+'</option>';
   						}
   					}
   					$('#lyxx').html(html);
   					
				}
				
			}
		}); */
    	$('#lyxx').select2({
            ajax: {
                type:"get",
                url : "${contextPath}/recode/build/getBuildByCondition",
                contentType: 'application/json',
                dataType:"JSON",
                delay: 550,
                data: function (params) {
                	params.page = 1;
                	params.rows = 10;
                	params.filters = '{"groupOp":"AND","rules":[{"field":"name","op":"cn","data":"'+checknull(params.term)+'"}]}';
                    return params;
                },
                processResults: function (data, params) {
                    params.page = params.page ? 1 : params.page;
                    var itemList = [];
                    var row = data.rows;
                    for(var i=0;i<row.length;i++){
                        itemList.push({id: row[i].id, text: row[i].name});
                    }
                    return {
                        results: itemList,
                        pagination: {
                            more: (params.page * 10) < data.totalNumber
                        }
                    };
                },
                cache: true
            },
            multiBoolean:false,
            language: "zh-CN",
            placeholder:'--请选择--',//默认文字提示
            allowClear: true,//允许清空
            escapeMarkup: function (markup) { return markup; }, // 自定义格式化防止xss注入
            templateResult: function formatRepo(repo){return repo.text;}, // 函数用来渲染结果
            templateSelection: function formatRepoSelection(repo){return repo.text;} // 函数用于呈现当前的选择
        });
    }
    
    function isnumber(){
    	$('.num').each(function(i){
    		$(this).blur(function(){
				var num = $(this).val();
				if(num == ''){
					$(this).val("0.00");
					return true;
				}
				if(num.indexOf(".") == -1){
					if(checknumber(num) && num.length < 11){
						$(this).val(num+".00");
						return true;
					}else{
						errormsg();
						$(this).focus();
					}
				}else{
					var arrs = num.split(".");
					if(arrs.length != 2){
						errormsg();
						$(this).focus();
					}else{
						if(checknumber(arrs[0]) && arrs[0].length < 11){
							
						}else{
							errormsg();
							$(this).focus();
						}
						
						if(checknumber(arrs[1]) && arrs[1].length < 3){
							
						}else{
							errormsg();
							$(this).focus();
						}
					}
				}

			});
    	});
    }
    
    
    function checknumber(num){
		var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/ 
		if (!re.test(num)) {
			return false;
		}
		return true;
	}
	function errormsg(){
		$.gritter.add({
			title: '输入错误',
			text: '请输入有效数字',
			class_name:"gritter-error  gritter-light"
		});
	}
	
	function sumnumber(){
		$('.num').each(function(i){
			$(this).bind('input',function(){
                if(i <9){
                    $('#sumincome').html(getsumToIndex(0,8));
                }else if(i<12){
                    $('#sum0').html(getsumToIndex(9,11));
                }else if(i<28){
                    $('#sum1').html(getsumToIndex(12,27));
                }else if(i<31){
                    $('#sum2').html(getsumToIndex(28,28));
                }
                    $('#sum5').html(getsumToIndex(0,8)-getsumToIndex(8,31));
			});
		});
	}
	
	function getsumToIndex(first,end){
		var c = 0;
		$('.num').each(function(i){
			if(i>=first && i <= end){
			    if(checknumber($(this).val())){
                    c = (parseFloat(c) + parseFloat($(this).val())).toFixed(2);
                }
			}
		});
		return c;
	}
	
	//提交预算
	function submitBudget(){

        if($('#lyxx').val() == null && $('#id').val() == '' ){
            $.gritter.add({
                title: '错误',
                text: '请先选择楼宇',
                class_name:"gritter-error  gritter-light"
            });
            return;
        }
        var build;
        if($('#lyxx').val() != null && $('#lyxx').val() !=  ''){
        	build = $('#lyxx').val();
        }else if(sessionStorage['build'] != '' && sessionStorage['build'] != null){
        	build = sessionStorage['build'];
        	sessionStorage['build'] = '';
        }
        var params = '{"build":"'+build+'",';
        $('.num').each(function(i){
            if($(this).val() == ''){
                $(this).val('0.00');
            }
            params += '"'+$(this).attr('id')+'":"'+$(this).val()+'",';
        });
        $('.ps').each(function(){
            params += '"'+$(this).attr('id')+'":"'+$(this).val()+'",';
        });
        params += '"year":"'+$('#year').val()+'","id":"'+$('#id').val()+'"}';
        $.ajax({
            dataType : "json",
            url : "${contextPath}//recode/budget/saveOrupdatBudget",
            type : "post",
            contentType: 'application/json',
            data :params,
            complete : function(xmlRequest) {
                $('#table_in').modal("toggle");
                jQuery("#grid-table").trigger("reloadGrid");
            }
        });
	}

	//获取某一楼宇的预算表
    function getbudgetMsgxx(){
        jQuery("#grid-table").jqGrid('setGridParam',{
            datatype:'json',
            postData:{'build':$("#lyxx").val()}, //发送数据
            page:1
        }).trigger("reloadGrid"); //重新载入
    }

    //打开新增modal
    function budgetInto(){
        if($('#lyxx').val() == '' || $('#lyxx').val() == null){
            $.gritter.add({
                title: '错误',
                text: '请先选择楼宇',
                class_name:"gritter-error  gritter-light"
            });
        }else{
            $('#table_in').modal("toggle");
        }
    }


    //图标信息
	function rentPhotot(id){
        $.ajax({
            dataType : "json",
            url : "${contextPath}//recode/budget/getBudgetByCondition?page=1&rows=1&id="+id,
            type : "post",
            contentType: 'application/json',
            data :null,
            complete : function(xmlRequest) {
                console.log(xmlRequest);
                if(xmlRequest.status == 200){
                    var data = JSON.parse(xmlRequest.responseText).rows;
                    showEcharts(data[0]);
                }

            }
        });
	}

	//图标
    function showEcharts(data) {
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
                        {value:data.sumincome, name:'收入预算', selected:true},
                        {value:data.sumcost, name:'支出预算'}
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
                        {value:data.sumincome, name:'收入预算'},
                        {value:data.sumenergy, name:'能源费'},
                        {value:data.sumoffice, name:'办公总务费'},
                        {value:data.sumelsed, name:'其他'}
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
                data: ['租金','物业管理费','固定停车费','临时停车费','广告费','其他经营收入'
                    ,'维修材料','电费收入','水费收入']
            },
            series : [
                {
                    name: '收入预算',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:data.rent, name:'租金'},
                        {value:data.property, name:'物业管理费'},
                        {value:data.fixedparking, name:'固定停车费'},
                        {value:data.tempparking, name:'临时停车费'},
                        {value:data.advertising, name:'广告费'},
                        {value:data.rest, name:'其他经营收入'},
                        {value:data.servicing, name:'维修材料'},
                        {value:data.electricin, name:'电费收入'},
                        {value:data.waterin, name:'水费收入'}
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
                        {value:data.water, name:'水费'},
                        {value:data.electricout, name:'电费'},
                        {value:data.gas, name:'燃气费'}
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
                        {value:data.stationery, name:'办公用品'},
                        {value:data.communication, name:'电话、通信费'},
                        {value:data.drinkwater, name:'饮用水'},
                        {value:data.doorplate, name:'门牌制作'},
                        {value:data.decorate, name:'节假日布置'},
                        {value:data.cleanser, name:'保洁用品'},
                        {value:data.afforestation, name:'绿化费'},
                        {value:data.ppe, name:'劳防用品'},
                        {value:data.trashcleaning, name:'垃圾清运费'},
                        {value:data.emergencymaterial, name:'应急物资'},
                        {value:data.wallwashing, name:'外墙、水箱清洗'},
                        {value:data.alarmservice, name:'报警服务费'},
                        {value:data.pestcontrol, name:'灭虫害服务费'},
                        {value:data.sewerage, name:'污水处理（疏通）费'},
                        {value:data.maintenance, name:'地坪保养费'},
                        {value:data.office, name:'其他'}
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

        /*myChart5.setOption(option5);

        myChart6.setOption(option6);*/

    }
    function checknull(val){
		if(val == null || val == undefined || val == "undefined" || val == 'null' ){
			return '';
		}
		return val;
	}
</script>
