<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery-ui.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery.gritter.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/datepicker.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap-timepicker.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/daterangepicker.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap-datetimepicker.css"/>
<link rel="stylesheet" href="${contextPath}/static/assets/css/select2.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/load.css" />

<body>

	<div class="row">
	<div class="col-xs-12">

		<div class="well well-sm">
			<a id="shInformationButton" role="button" class="btn btn-info btn-sm" data-toggle="modal">
				查看审核
			</a>
			<a id="viewCompactButton" role="button" class="btn btn-inverse btn-sm" data-toggle="modal">
				合同预览
			</a>
		</div>

		<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
			<li class="active">
				<a data-toggle="tab" href="#dsh">待审核</a>
			</li>

			<li>
				<a data-toggle="tab" href="#shz">审核中</a>
			</li>

			<li>
				<a data-toggle="tab" href="#ysh">已完成</a>
			</li>
		</ul>
		<div class="tab-content">
			<div id="dsh" class="tab-pane in active">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div>

			<div id="shz" class="tab-pane">
				<table id="grid-table—shz"></table>
				<div id="grid-pager—shz"></div>
			</div>

			<div id="ysh" class="tab-pane">
				<table id="grid-table—ysh"></table>
				<div id="grid-pager—ysh"></div>
			</div>
		</div>

		<script type="text/javascript">
			var $path_base = "${contextPath}/static";//in Ace demo this will be used for editurl parameter
		</script>

	</div>
</div>

	<div id="modal-table1" class="modal fade" data-backdrop="static">
	<div class="modal-dialog" style="width: 90%;height: 90%">
		<div class="modal-content">
			<div class="table-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					<span class="white">&times;</span>
				</button>
				合同预览
			</div>
			<div id="handout_wrap_inner"></div>
		</div>
	</div>
</div>

	<div id="modal-table-edit" class="modal fade" data-backdrop="static">
	<div class="modal-dialog" style="min-width: 820px;">
		<form id="compactForm_Edit" class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						编辑合同信息
					</div>
				</div>
				<div class="modal-body" style="max-height: 500px;overflow-y: scroll;">
					<div id="modal-tip-edit" class="red clearfix"></div>
					<div class="widget-box">
						<div class="widget-header">
							<h4 class="widget-title">基本条款</h4>
							<div class="widget-toolbar">
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<div class="form-group">
									<div><input type="hidden" id="id_edit" /></div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="contype_edit">合同类型：</label>
									<div class="col-sm-4">
										<select class="select2" id="contype_edit" style="width: 100%" >
											<option value="">--请选择--</option>
											<option value="1">物业管理服务合同</option>
											<option value="2">协议停车合同</option>
											<option value="3">其他合同</option>
										</select>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="htsj_edit">合同时间：</label>
									<div class="col-sm-4">
										<div class="input-group">
											<span class="input-group-addon">
												<i class="fa fa-calendar bigger-110"></i>
											</span>
											<input class="form-control" type="text" name="date-range-picker" id="htsj_edit" disabled/>
										</div>
									</div>
								</div>
								<div class="form-group" >
									<label class="col-sm-2 control-label blue" style="text-align: left" for="buildid_edit">租赁地址：</label>
									<div class="col-sm-4">
										<select class="select2" id="buildid_edit" style="width: 100%" disabled></select>
									</div>
									<div class="col-sm-4" style="display: none" id="div1_edit">
										<select class="select2" id="propertyid_edit" style="width:100%" multiple disabled>
										</select>
									</div>
								</div>
								<div class="form-group" >
									<label class="col-sm-2 control-label blue" style="text-align: left" for="showAddressId_edit"></label>
									<div class="col-sm-4">
										<input type="text" placeholder="区路号" class="width-100" id="showAddressId_edit" readonly/>
									</div>
									<div class="col-sm-4" style="display: none" id="div2_edit">
										<input type="text" placeholder="室" class="width-100"  id="showPropertyIds_edit" readonly/>
									</div>
								</div>
								<div class="form-group" >
									<label class="col-sm-2 control-label blue" style="text-align: left" for="price_edit">合同金额（元）：</label>
									<div class="col-sm-4">
										<input type="text" placeholder="" class="width-100" id="price_edit" readonly/>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="widget-box">
						<div class="widget-header">
							<h4 class="widget-title">甲方信息</h4>
							<div class="widget-toolbar">
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partacode_edit">管理方：</label>
									<div class="col-sm-4">
										<select class="select2 width-100" id="partacode_edit" style="width: 100%" disabled></select>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partaaddress_edit">地址：</label>
									<div class="col-sm-4">
										<input type="text" id="partaaddress_edit" class="width-100" readonly />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partalegalperson_edit">法定代表人：</label>
									<div class="col-sm-4">
										<input type="text" id="partalegalperson_edit"_edit class="width-100" readonly />
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partancontact_edit">联系电话：</label>
									<div class="col-sm-4">
										<input type="text" id="partancontact_edit" class="width-100" readonly/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partaaccount_edit">帐户名称：</label>
									<div class="col-sm-4">
										<input type="text" id="partaaccount_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="bankname_edit">开户银行：</label>
									<div class="col-sm-4">
										<input type="text" id="bankname_edit" class="width-100" readonly/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partaaccountname_edit">帐 号：</label>
									<div class="col-sm-4">
										<input type="text" id="partaaccountname_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partataxnumber_edit">税 号：</label>
									<div class="col-sm-4">
										<input type="text" id="partataxnumber_edit" class="width-100" readonly/>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="widget-box">
						<div class="widget-header">
							<h4 class="widget-title">乙方信息</h4>
							<div class="widget-toolbar">
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partbcode_edit">承租方：</label>
									<div class="col-sm-4">
										<select class="select2" id="partbcode_edit" style="width: 100%" disabled>
										</select>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partbtype_edit">类型：</label>
									<div class="col-sm-4">
										<select class="select2" id="partbtype_edit" style="width: 100%" disabled>
											<option value="0">企业</option>
											<option value="1">个人</option>
										</select>
									</div>
								</div>
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partbaddress_edit">地址：</label>
									<div class="col-sm-4">
										<input type="text" id="partbaddress_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partblegalperson_edit">法定代表人：</label>
									<div class="col-sm-4">
										<input type="text" id="partblegalperson_edit" class="width-100" readonly/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partbncontact_edit">联系电话：</label>
									<div class="col-sm-4">
										<input type="text" id="partbncontact_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partbtaxnumber_edit">税 号：</label>
									<div class="col-sm-4">
										<input type="text" id="partbtaxnumber_edit" class="width-100" readonly/>
									</div>
								</div>
								<div class="form-group" id="div3_edit" style="display: none;">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="partbname_edit">承租方名称：</label>
									<div class="col-sm-4">
										<input type="text" id="partbname_edit" class="width-100" readonly/>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="widget-box wyxx" style="display: none">
						<div class="widget-header">
							<h4 class="widget-title">基本条款</h4>
							<div class="widget-toolbar">
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="buildarera_edit">房屋建筑面积(平方米)：</label>
									<div class="col-sm-4">
										<input type="text" id="buildarera_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="tenantarea_edit">承租建筑面积(平方米)：</label>
									<div class="col-sm-4">
										<input type="text" id="tenantarea_edit" class="width-100" readonly/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="propertyfee_edit">物业管理费(元)：</label>
									<div class="col-sm-4">
										<input type="text" id="propertyfee_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="paytype_edit">付款方式：</label>
									<div class="col-sm-4">
										<select class="select2" id="paytype_edit" style="width: 100%" disabled>
										</select>
									</div>

								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="deposit_edit">物业费押金(元)：</label>
									<div class="col-sm-4">
										<input type="text" id="deposit_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="electric_edit">电费单价(元/千瓦小时)：</label>
									<div class="col-sm-4">
										<input type="text" id="electric_edit" class="width-100" readonly/>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="widget-box cwxx" style="display: none">
						<div class="widget-header">
							<h4 class="widget-title">基本条款</h4>
							<div class="widget-toolbar">
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="manager_edit">停车场地资产产权单位：</label>
									<div class="col-sm-10">
										<input type="text" id="manager_edit" class="width-100" readonly/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="undergroundunit_edit">地下车位价格(元/月/车)：</label>
									<div class="col-sm-4">
										<input type="text" id="undergroundunit_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="surfaceunit_edit">地面车位价格(元/月/车)：</label>
									<div class="col-sm-4">
										<input type="text" id="surfaceunit_edit" class="width-100" readonly/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="undergroundnumber_edit">地下车位数量(个)：</label>
									<div class="col-sm-4">
										<input type="text" id="undergroundnumber_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="surfacenumber_edit">地面车位数量(个)：</label>
									<div class="col-sm-4">
										<input type="text" id="surfacenumber_edit" class="width-100" readonly/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="rent_edit">车位租赁费(元/月)：</label>
									<div class="col-sm-4">
										<input type="text" id="rent_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="prepay_edit">支付方式(月预付)：</label>
									<div class="col-sm-4">
										<input type="text" id="prepay_edit" class="width-100" readonly/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="cardfee_edit">停车证制作费：</label>
									<div class="col-sm-4">
										<input type="text" id="cardfee_edit" class="width-100" readonly/>
									</div>
									<label class="col-sm-2 control-label blue" style="text-align: left" for="reissuecardfee_edit">停车证补办费：</label>
									<div class="col-sm-4">
										<input type="text" id="reissuecardfee_edit" class="width-100" readonly/>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="widget-box">
						<div class="widget-header">
							<h4 class="widget-title">补充条款</h4>
							<div class="widget-toolbar">
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<div class="form-group">
									<label class="col-sm-2 control-label blue" style="text-align: left" for="supplementaryterms_edit">其他条款：</label>
									<div class="col-sm-10">
										<textarea class="width-100" id="supplementaryterms_edit" placeholder="补充合同条款使用" rows="5" readonly></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="widget-box">
						<div class="widget-header">
							<h4 class="widget-title">处理信息</h4>
							<div class="widget-toolbar">
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<div class="timeline-container timeline-style2">
									<span class="timeline-label">
										<b>处理人</b>
									</span>
									<div class="timeline-items">

										<div id="dealDiv"></div>

										<br><br>
										<div class="form-group">
											<div class="col-sm-12">
												<textarea class="width-100"  placeholder="请输入意见备注"></textarea>
											</div>
										</div>

									</div>

								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="modal-footer no-margin-top">
					<div class="modal-footer no-margin-top">
						<div class="text-center">
							<button class="btn btn-success" id="agreeButton">同意</button>
							<button class="btn btn-danger" id="backButton">退回</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

</body>
<script type="text/javascript">
	    var nowTab = "";
		var scripts = [ null, "${contextPath}/static/assets/js/jqGrid/jquery.jqGrid.js", "${contextPath}/static/assets/js/jqGrid/i18n/grid.locale-cn.js", "${contextPath}/static/assets/js/jquery-ui.custom.js",
		        		"${contextPath}/static/assets/js/jquery.ui.touch-punch.js", "${contextPath}/static/assets/js/markdown/markdown.js", "${contextPath}/static/assets/js/markdown/bootstrap-markdown.js",
		        		"${contextPath}/static/assets/js/jquery.hotkeys.js", "${contextPath}/static/assets/js/bootstrap-wysiwyg.js", "${contextPath}/static/assets/js/bootbox.js",
            			"${contextPath}/static/assets/js/jquery.ui.touch-punch.js","${contextPath}/static/assets/js/bootbox.js", "${contextPath}/static/assets/js/jquery.easypiechart.js",
						"${contextPath}/static/assets/js/jquery.gritter.js","${contextPath}/static/assets/js/jquery.gritter.js","${contextPath}/static/assets/js/date-time/moment.js",
						"${contextPath}/static/assets/js/date-time/bootstrap-datepicker.js", "${contextPath}/static/assets/js/date-time/bootstrap-timepicker.js", "${contextPath}/static/assets/js/date-time/daterangepicker.js",
						"${contextPath}/static/assets/js/date-time/bootstrap-datetimepicker.js","${contextPath}/static/assets/js/spin.js","${contextPath}/static/assets/js/jquery.media.js","${contextPath}/static/assets/js/load-min.js",
            			"${contextPath}/static/assets/js/select2.js",,null ]


        $(".page-content-area").ace_ajax("loadScripts", scripts, function() {
        	jQuery(function($) {
        		var grid_selector = "#grid-table";
        		var pager_selector = "#grid-pager";
                nowTab = "#dsh";
                $('#myTab4 a').click(function (e) {
                    e.preventDefault();
                    url = $(this).attr(("href"));
                    $(grid_selector).jqGrid('GridUnload');
                    $(pager_selector).html("");
                    if(url=='#dsh'){
                        grid_selector = "#grid-table";
                        pager_selector = "#grid-pager";
                        getjqGrid("1");
                        $(grid_selector).jqGrid("setGridWidth", parent_column.width());
                        getNavGrid();
                        nowTab = "#dsh";
                    }else if(url=='#shz'){
                        grid_selector = "#grid-table—shz";
                        pager_selector = "#grid-pager—shz";
                        getjqGrid("2");
                        $(grid_selector).jqGrid("setGridWidth", parent_column.width());
                        getNavGrid();
                        nowTab = "#shz";
					}else if(url=='#ysh'){
                        grid_selector = "#grid-table—ysh";
                        pager_selector = "#grid-pager—ysh";
                        getjqGrid("3");
                        $(grid_selector).jqGrid("setGridWidth", parent_column.width());
                        getNavGrid();
                        nowTab = "#ysh";
                    }
                });

        		$(window).on("resize.jqGrid", function() {
        			$(grid_selector).jqGrid("setGridWidth", $(".page-content").width());
        		});

        		var parent_column = $(grid_selector).closest("[class*='col-']");
        		$(document).on("settings.ace.jqGrid", function(ev, event_name, collapsed) {
        			if (event_name === "sidebar_collapsed" || event_name === "main_container_fixed") {
        				setTimeout(function() {
        					$(grid_selector).jqGrid("setGridWidth", parent_column.width());
        				}, 0);
        			}
        		});

                getjqGrid("1");

                function getjqGrid(auditState){
                    jQuery(grid_selector).jqGrid({
                        subGrid: false,
                        url: "${contextPath}/sys/flow/getContractByAudit",
                        datatype: "json",
                        height: 450,
						postData:{"selectState":auditState,"subsidiary":"323213"},
                        colNames: ["ID", "合同编号", "管理方", "承租方", "合同类型", "承租方联系电话", "租赁开始时间","租赁结束时间", "合同状态"],
                        colModel: [{
                            name: "id",
                            width: 60,
                            hidden:true
                        }, {
                            name: "sysnumber",
                            width: 120,
                            searchoptions: {sopt: ["cn","eq"]}
                        }, {
                            name: "partaname",
                            width: 150,
                            search: false
                        }, {
                            name: "partbname",
                            width: 110,
                            searchoptions: {sopt: ["cn","eq"]}
                        }, {
                            name: "contype",
                            width: 110,
                            searchoptions: {sopt: ["eq"]},
                            formatter:function(celval, options, rowdata){
                                if(celval==1){
                                    return "物业合同";
                                }else if(celval==2){
                                    return "停车合同";
                                }else if(celval==3){
                                    return "其他合同";
                                }
                            }
                        }, {
                            name: "partbcontact",
                            width: 150,
                            search: false
                        }, {
                            name: "startdate",
                            width: 150,
                            search: false
                        },{
                            name: "enddate",
                            width: 150,
                            search: false
                        }, {
                            name: "auditstate",
                            width: 100,
                            search: false,
                            formatter:function(celval, options, rowdata){
                                if(celval=='1'){
                                    return "待提交";
                                }else if(celval=='2'){
                                    return "审核中";
                                }else if(celval=='3'){
                                    return "已完成";
                                }
                            }
                        }],
                        sortname: "id",
                        sortorder: "asc",
                        viewrecords: true,
                        rowNum: 10,
                        rowList: [10, 20, 30],
                        pager: pager_selector,
                        altRows: true,
                        multiselect: true,
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
                        gridComplete:function(){
                            var ids = $(grid_selector).getDataIDs();
                            for (var i = 0; i < ids.length; i++) {
                                var rowData = $(grid_selector).getRowData(ids[i]);
                                var httime = rowData.enddate;
                                var today = new Date();
                                var date = new Date(today);
                                var today_time = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                                if (httime <= today_time) {
                                    $('#' + ids[i]).find("td").css("background-color", "#FFB6C1");
                                }
                            }
                        }
                    });
                }

        		$(window).triggerHandler("resize.jqGrid");// trigger window resize to make the grid get the correct size

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

                getNavGrid();

				function getNavGrid(){
                    jQuery(grid_selector).jqGrid("navGrid", pager_selector, {
                        edit: false,
                        editicon: "ace-icon fa fa-pencil blue",
                        add: false,
                        addicon: "ace-icon fa fa-plus-circle purple",
                        search: <shiro:hasPermission name="${ROLE_KEY}:compact:search">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:compact:search">false</shiro:lacksPermission>,
                        searchicon: "ace-icon fa fa-search orange",
                        del : false,
                        delicon : "ace-icon fa fa-trash-o red",
                        refresh: true,
                        refreshicon: "ace-icon fa fa-refresh blue",
                        view: <shiro:hasPermission name="${ROLE_KEY}:compact:view">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:compact:view">false</shiro:lacksPermission>,
                        viewicon: "ace-icon fa fa-search-plus grey"
                    }, {
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
                        }
                    }, {
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
                    }, {
                        recreateForm : true,
                        beforeShowForm : function(e) {
                            var form = $(e[0]);
                            form.closest(".ui-jqdialog").find(".ui-jqdialog-title").wrap("<div class='widget-header' />")
                        }
                    });
                    exportRefresh();
                }

                function  exportRefresh() {
                    if(true){
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
                }


                $('input[name=date-range-picker]').daterangepicker({
                    'applyClass': 'btn-sm btn-success',
                    'cancelClass': 'btn-sm btn-default',
                    locale: {
                        format: 'YYYY-MM-DD',
                        separator: '-',
                        applyLabel: '确定',
                        cancelLabel: '取消',
                        daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                        monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                            '七月', '八月', '九月', '十月', '十一月', '十二月']
                    }
                }).prev().on(ace.click_event, function () {
                    $(this).next().focus();
                });

        		function style_edit_form(form) {
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

        		function styleCheckbox(table) {
        		}

        		function updateActionIcons(table) {
        		}

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

                $("#shInformationButton").bind("click", function () {
                    var selectedId = $(grid_selector).jqGrid("getGridParam", "selrow");
                    if (null == selectedId) {
                        toastMessage('系统信息', '请选择记录！');
                    } else {
						if( nowTab!="#dsh"){
						      $('#agreeButton').css("display","none");
						      $('#backButton').css("display","none");
						}else{
                            $('#agreeButton').css("display","");
                            $('#backButton').css("display","");
						}
                        var params = new Object();
                        params.id =  selectedId;
                        $.ajax({
                            dataType : "json",
                            url : "${contextPath}/sys/contract/getContractWithInfoById",
                            type : "post",
                            contentType: 'application/json',
                            data :JSON.stringify(params),
                            beforeSend: function () {
                                $.mask_fullscreen();
                            },
                            complete: function (xmlRequest) {
                                $.mask_close_all();
                                if(xmlRequest.statusText=='success'){
                                    echoEditForm(JSON.parse(xmlRequest.responseText).rows[0]);
                                }
                            },
                            error: function () {
                                $.mask_close_all();
                            }
                        });

                        /**
						 *
						 *
						 *
						 * <div class="timeline-item clearfix">
                         <div class="timeline-info">
                         <span class="timeline-date">张三</span>
                         <i class="timeline-indicator btn btn-success no-hover"></i>
                         </div>
                         <div class="widget-box transparent">
                         <div class="widget-body">
                         <div class="widget-main no-padding">
                         <div class="clearfix">
                         <div class="pull-left">
                         <span class="orange2 bolder">同意</span>&nbsp;&nbsp;&nbsp;备注：请审核
                         </div>
                         </div>
                         </div>
                         </div>
                         </div>
                         </div>
						 *
						 *
						 *
						 *
						 * */


                        $.ajax({
                            dataType : "json",
                            url : "${contextPath}/sys/flow/getAuditRecords",
                            type : "post",
                            contentType: 'application/json',
                            data :JSON.stringify(params),
                            beforeSend: function () {
                            },
                            complete: function (xmlRequest) {
                                if(xmlRequest.statusText=='success'){
									 var rows = JSON.parse(xmlRequest.responseText).rows;
									 var html = "";
									 for(var i=0;i<rows.length;i++){
									    html += '<div class="timeline-item clearfix">'
											+'<div class="timeline-info">'
											+'<span class="timeline-date">'+rows[i].dealname+'</span>'
											+'<i class="timeline-indicator btn btn-success no-hover"></i>'
										 	+'</div>'
										 	+'<div class="widget-box transparent">'
										 	+'<div class="widget-body">'
										 	+'<div class="widget-main no-padding">'
										 	+'<div class="clearfix">'
										 	+'<div class="pull-left">';
										if(rows[i].decision==1){
											 html +='<span class="orange2 bolder">同意</span><span class="bolder">&nbsp;&nbsp;处理时间</span>'+getMyDate(rows[i].handlertime)+'&nbsp;&nbsp;&nbsp;备注：'+rows[i].opinion;
										}else if(rows[i].decision==2){
                                            html +='<span class="pink2 bolder">退回</span><span class="bolder">&nbsp;&nbsp;处理时间</span>'+getMyDate(rows[i].handlertime)+'&nbsp;&nbsp;&nbsp;备注：'+rows[i].opinion;
                                        }else if(rows[i].decision==3){
                                            html +='<span class="orange2 bolder">提交</span><span class="bolder">&nbsp;&nbsp;处理时间</span>'+getMyDate(rows[i].handlertime)+'&nbsp;&nbsp;&nbsp;备注：'+rows[i].opinion;
                                        }else if(rows[i].decision==4){
                                            html +='<span class="orange2 bolder">完成</span><span class="bolder">&nbsp;&nbsp;处理时间</span>'+getMyDate(rows[i].handlertime)+'&nbsp;&nbsp;&nbsp;备注：'+rows[i].opinion;
                                        }
                                        html +='</div></div></div></div></div></div>';
										$('#dealDiv').html(html);
                                     }
                                }
                            },
                            error: function () {

                            }
                        });
                    }
                });

        		$(document).one("ajaxloadstart.page", function(e) {
        			$(grid_selector).jqGrid("GridUnload");
        			$(".ui-jqdialog").remove();
        		});

                //预览合同
                $("#viewCompactButton").bind("click", function () {
                    var selectedId = $(grid_selector).jqGrid("getGridParam", "selrow");
                    if (null == selectedId) {
                        toastMessage("系统信息","请选择记录");
                    } else {
                        var rowData = $(grid_selector).getRowData(selectedId);
                        if(rowData.contype=='其他合同') {
                            toastMessage("系统信息", "其他合同没有pdf文件预览！");
                        }
                        //通过合同id找到pdfurl;
                        var rule = new Object();
                        rule.field = 'id';
                        rule.op = 'eq';
                        rule.data =  selectedId;
                        var rules = new Array();
                        rules.push(rule);
                        rule = new Object();
                        rule.field = 'filetype';
                        rule.op = 'eq';
                        rule.data =  "2";
                        rules.push(rule);
                        $.ajax({
                            dataType : "json",
                            url : "${contextPath}/sys/contract/getContractFileByCondition",
                            type : "get",
                            contentType: 'application/json',
                            data :generateParams(new Object(),rules),
                            complete : function(data) {
                                if(data.statusText=='success'){
                                    var pdfurl =  JSON.parse(data.responseText).rows[0].fileurl;
                                    $("#modal-table1").modal("toggle");
                                    $('#handout_wrap_inner').media({
                                        width: '100%',
                                        height: '900px',
                                        autoplay: true,
                                        src:"${contextPath}"+pdfurl.split("${contextPath}")[1],
                                    });
                                }else{
                                    toastMessage("系统信息","预览失败，请稍后重试！");
                                }
                            }
                        });
                    }
                });

        	});
        });

        function toastMessage(title, text) {
            $.gritter.add({
                title: title,
                text: text,
                position: 'top-right',
                time:2000,
                class_name: 'gritter-info'
            });
            return;
        }

        function echoEditForm(data) {
            $('#id_edit').val(data.id);
            $('#contype_edit').val(data.contype).trigger("change");
            $('#contype_edit').prop("disabled", true);
            var begin = formatDate(data.startdate);
            var end = formatDate(data.enddate);
            $("#htsj_edit").val(begin+" - "+end);
            $('#htsj_edit').data('daterangepicker').setStartDate(begin);
            $('#htsj_edit').data('daterangepicker').setEndDate(end);
            initBuildSelect2('buildid_edit');
            getBuildInfoByid(data.buildid);
            $('#buildid_edit').append(new Option(buildInfo.name,data.buildid,true,true)).trigger("change");
            $('#showAddressId_edit').val(buildInfo.address);

            if (data.contype=='1') {
                $(".wyxx").css("display", "");
                $(".cwxx").css("display", "none");
                $("#div1_edit").css("display", "");
                $("#div2_edit").css("display", "");
                initPropertySelect2_html(data.buildid,'propertyid_edit',data.propertyids);
            } else if (data.contype=='2') {
                $(".wyxx").css("display", "none");
                $(".cwxx").css("display", "");
                $("#div1_edit").css("display", "none");
                $("#div2_edit").css("display", "none");
            }
            $('#price_edit').val(data.totalamount);
            initPartaSelect2('partacode_edit');
            $('#partacode_edit').append(new Option(data.partaname,data.partacode,true,true)).trigger("change");
            $('#partaaddress_edit').val(data.partaaddress);
            $('#partalegalperson_edit').val(data.partalegalperson);
            $('#partancontact_edit').val(data.partancontact);
            $('#partaaccount_edit').val(data.partaaccount);
            $('#partaaccountname_edit').val(data.partaaccountname);
            $('#bankname_edit').val(data.bankname);
            $('#partataxnumber_edit').val(data.partataxnumber);
            initPartbSelect2('partbcode_edit');
            $('#partbcode_edit').append(new Option(data.partbname,data.partbcode,true,true)).trigger("change");
            $('#partbaddress_edit').val(data.partbaddress);
            $('#partblegalperson_edit').val(data.partblegalperson);
            $('#partbncontact_edit').val(data.partbcontact);
            $('#partbtaxnumber_edit').val(data.partbtaxnumber);
            $("#partbtype_edit").val(data.partbtype).trigger("change");
            if (data.contype=='1') {
                $('#buildarera_edit').val(data.buildarea);
                $('#tenantarea_edit').val(data.tenantarea);
                $('#propertyfee_edit').val(data.propertyfee);
                initPaytypeSelect2('paytype_edit','WYYJ');
                $('#paytype_edit').append(new Option(data.paytype,data.paytypecode,true,true)).trigger("change");
                $('#deposit_edit').val(data.deposit);
                $('#electric_edit').val(data.electric);
            }  else if (data.contype=='2') {
                $('#manager_edit').val(data.manager);
                $('#undergroundunit_edit').val(data.undergroundunit);
                $('#surfaceunit_edit').val(data.surfaceunit);
                $('#undergroundnumber_edit').val(data.undergroundnumber);
                $('#surfacenumber_edit').val(data.surfacenumber);
                $('#rent_edit').val(data.rent);
                $('#prepay_edit').val(data.prepay);
                $('#cardfee_edit').val(data.cardfee);
                $('#reissuecardfee_edit').val(data.reissuecardfee);
            }
            $('#supplementaryterms_edit').val(data.subsidiary);
            $("#modal-table-edit").modal("toggle");

        }

        function formatDate(date){
            return date.split("-")[1]+"/"+date.split("-")[2]+"/"+date.split("-")[0];
        }

        /**
         * 楼宇下拉框
         * @param id
         */
        function initBuildSelect2(id){
            $('#'+id).select2({
                ajax: {
                    type:"get",
                    url: "${contextPath}/recode/build/getBuildByCondition",
                    contentType: 'application/json',
                    dataType:"JSON",
                    delay: 550,
                    data: function (params) {
                        var rule = new Object();
                        rule.field = 'name';
                        rule.op = 'cn';
                        rule.data =  checkIsNull(params.term) ? "" : params.term;
                        var rules = new Array();
                        rules.push(rule);
                        return generateParams(params,rules);
                    },
                    processResults: function (data, params) {
                        params.page = checkIsNull(params.page) ? 1 : params.page;
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
            }).on("select2:select",function(e){
                var rule = new Object();
                rule.field = 'id';
                rule.op = 'eq';
                rule.data =  e.params.data.id;
                var rules = new Array();
                rules.push(rule);
                $.ajax({
                    dataType : "json",
                    url : "${contextPath}/recode/build/getBuildByCondition",
                    type : "get",
                    contentType: 'application/json',
                    data :generateParams(new Object(),rules),
                    complete : function(data) {
                        if(data.statusText=='success'){
                            if(id=='buildid') {
                                $('#showAddressId').val(JSON.parse(data.responseText).rows[0].address);
                                if($('#contype').val() == '1'){
                                    $('#propertyid').select2("val", " ");
                                    $('#showPropertyIds').val("");
                                    initPropertySelect2(e.params.data.id,'propertyid');
                                }
                            }else if(id=="buildid_edit"){
                                $('#showAddressId_edit').val(JSON.parse(data.responseText).rows[0].address);
                                if($('#contype_edit').val() == '1'){
                                    $('#propertyid_edit').select2("val", " ");
                                    $('#showPropertyIds_edit').val("");
                                    initPropertySelect2(e.params.data.id,'propertyid_edit');
                                }
                            }
                        }
                    }
                });
            });
        }

        function getBuildInfoByid(id){
            var rule = new Object();
            rule.field = 'id';
            rule.op = 'eq';
            rule.data =  id;
            var rules = new Array();
            rules.push(rule);
            $.ajax({
                dataType : "json",
                url : "${contextPath}/recode/build/getBuildByCondition",
                type : "get",
                contentType: 'application/json',
                async:false,
                data :generateParams(new Object(),rules),
                complete : function(data) {
                    if(data.statusText=='success'){
                        buildInfo =  JSON.parse(data.responseText).rows[0];
                    }
                }
            });
        }

        /**
         * 组织请求条件
         * @param params
         * @param rule
         * @returns {Object}
         */
        function generateParams(params,rule) {
            var query = new Object();
            query._search=true;
            query.rows = '10';
            query.page = ''+(checkIsNull(params.page) ? 1 : params.page);
            query.sidx = 'id';
            query.sord = 'asc';
            var filter = new Object();
            filter.groupOp = 'AND';
            var ruleSz = new Array();
            for(var i=0;i<rule.length;i++){
                ruleSz.push(rule[i]);
            }
            filter.rules = ruleSz;
            query.filters = JSON.stringify(filter) ;
            return query;
        }

        function checkIsNull(value){
            if(typeof(value)=="undefined" || value=='' || value==null){
                return true;
            }
            return false;
        }

        function initPropertySelect2_html(buildid,id,propertyids) {
            var rule = new Object();
            var rules = new Array();
            rule.field = 'build';
            rule.op = 'eq';
            rule.data =  buildid;
            rules.push(rule);
            $.ajax({
                type:"get",
                url: "${contextPath}/recode/property/getPropertyByCondition",
                contentType: 'application/json',
                dataType:"JSON",
                delay: 550,
                data:generateParams(new Object(),rules),
                success : function(data) {
                    var html = "";
                    $('#'+id).html(html);
                    var row = data.rows;
                    for(var i=0;i<row.length;i++){
                        html += "<option value='"+row[i].id+"'>"+row[i].name+"</option>";
                    }
                    $('#'+id).append(html);
                    $('#'+id).select2();
                    $('#'+id).val(propertyids.split(",")).trigger("change");
                    showProperty('propertyid_edit','showPropertyIds_edit');
                }
            });
        }

        function initPartaSelect2(id){
            $('#'+id).select2({
                ajax: {
                    type:"get",
                    url: "${contextPath}/recode/firstpartyContract/getfirstpartyContractByCondition",
                    contentType: 'application/json',
                    dataType:"JSON",
                    delay: 550,
                    data: function (params) {
                        var rule = new Object();
                        var rules = new Array();
                        rule.field = 'name';
                        rule.op = 'cn';
                        rule.data =  checkIsNull(params.term) ? "" : params.term;
                        rules.push(rule);
                        return generateParams(params,rules);
                    },
                    processResults: function (data, params) {
                        params.page = checkIsNull(params.page) ? 1 : params.page;
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
            }).on("select2:select",function(e){
                var rule = new Object();
                rule.field = 'id';
                rule.op = 'eq';
                rule.data =  e.params.data.id;
                var rules = new Array();
                rules.push(rule);
                $.ajax({
                    dataType : "json",
                    url : "${contextPath}/recode/firstpartyContract/getfirstpartyContractByCondition",
                    type : "get",
                    contentType: 'application/json',
                    data :generateParams(new Object(),rules),
                    complete : function(data) {
                        if(data.statusText=='success'){
                            if(id=='partacode'){
                                $('#partaaddress').val(JSON.parse(data.responseText).rows[0].address);
                                $('#partalegalperson').val(JSON.parse(data.responseText).rows[0].contactname);
                                $('#partancontact').val(JSON.parse(data.responseText).rows[0].contactnumber);
                                $('#partaaccount').val(JSON.parse(data.responseText).rows[0].account);
                                $('#partaaccountname').val(JSON.parse(data.responseText).rows[0].accountname);
                                $('#bankname').val(JSON.parse(data.responseText).rows[0].bankname);
                                $('#partataxnumber').val(JSON.parse(data.responseText).rows[0].taxnumber);
                            }else if(id=='partacode_edit'){
                                $('#partaaddress_edit').val(JSON.parse(data.responseText).rows[0].address);
                                $('#partalegalperson_edit').val(JSON.parse(data.responseText).rows[0].contactname);
                                $('#partancontact_edit').val(JSON.parse(data.responseText).rows[0].contactnumber);
                                $('#partaaccount_edit').val(JSON.parse(data.responseText).rows[0].account);
                                $('#partaaccountname_edit').val(JSON.parse(data.responseText).rows[0].accountname);
                                $('#bankname_edit').val(JSON.parse(data.responseText).rows[0].bankname);
                                $('#partataxnumber_edit').val(JSON.parse(data.responseText).rows[0].taxnumber);
                            }
                        }
                    }
                });
            })
        }

        /**
         * 室号显示
         */
        function showProperty(id,showid){
            var o=document.getElementById(id).getElementsByTagName('option');
            var all="";
            for(var i=0;i<o.length;i++){
                if(o[i].selected){
                    all+=o[i].text+",";
                }
            }
            if(all!=''){
                all = all.substring(0,all.length-1);
            }
            $('#'+showid).val(all);
        }

        function initPartbSelect2(id) {
            $('#'+id).select2({
                ajax: {
                    type:"get",
                    url: "${contextPath}/recode/customer/geCustomerByCondition",
                    contentType: 'application/json',
                    dataType:"JSON",
                    delay: 550,
                    data: function (params) {
                        var rule = new Object();
                        var rules = new Array();
                        rule.field = 'contactname';
                        rule.op = 'cn';
                        rule.data =  checkIsNull(params.term) ? "" : params.term;
                        rules.push(rule);
                        return generateParams(params,rules);
                    },
                    processResults: function (data, params) {
                        params.page = checkIsNull(params.page) ? 1 : params.page;
                        var itemList = [];
                        var row = data.rows;
                        itemList.push({id: 99999, text: '暂无信息,选此项可添加'});
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
            }).on("select2:select",function(e){
                var rule = new Object();
                rule.field = 'id';
                rule.op = 'eq';
                rule.data =  e.params.data.id;
                var rules = new Array();
                rules.push(rule);
                $.ajax({
                    dataType : "json",
                    url : "${contextPath}/recode/customer/geCustomerByCondition",
                    type : "get",
                    contentType: 'application/json',
                    data :generateParams(new Object(),rules),
                    complete : function(data) {
                        if(data.statusText=='success'){
                            if(id=='partbcode'){
                                $('#partbaddress').val(JSON.parse(data.responseText).rows[0].address);
                                $('#partblegalperson').val(JSON.parse(data.responseText).rows[0].name);
                                $('#partbncontact').val(JSON.parse(data.responseText).rows[0].contactnumber);
                                $('#partbtaxnumber').val(JSON.parse(data.responseText).rows[0].taxnumber);
                                $("#partbtype").val(JSON.parse(data.responseText).rows[0].type).trigger("change");
                            }else if(id=='partbcode_edit'){
                                $('#partbaddress_edit').val(JSON.parse(data.responseText).rows[0].address);
                                $('#partblegalperson_edit').val(JSON.parse(data.responseText).rows[0].name);
                                $('#partbncontact_edit').val(JSON.parse(data.responseText).rows[0].contactnumber);
                                $('#partbtaxnumber_edit').val(JSON.parse(data.responseText).rows[0].taxnumber);
                                $("#partbtype_edit").val(JSON.parse(data.responseText).rows[0].type).trigger("change");
                            }
                        }
                    }
                });
                if(e.params.data.id=='99999'){
                    $('#div3').css("display","");
                    $('#div3_edit').css("display","");
                }else{
                    $('#div3').css("display","none");
                    $('#div3_edit').css("display","none");
                }
            });
        }

        function initPaytypeSelect2(id,type){
            $('#'+id).select2({
                ajax: {
                    type:"get",
                    url: "${contextPath}/sys/dict/getDictByCondition",
                    contentType: 'application/json',
                    dataType:"JSON",
                    delay: 550,
                    data: function (params) {
                        var rule = new Object();
                        var rules = new Array();
                        rule.field = 'parentDictkey';
                        rule.op = 'eq';
                        rule.data = type;
                        rules.push(rule);
                        rule = new Object();
                        rule.field = 'dicValue';
                        rule.op = 'cn';
                        rule.data = checkIsNull(params.term) ? "" : params.term;
                        rules.push(rule);
                        return generateParams(params,rules);
                    },
                    processResults: function (data, params) {
                        params.page = checkIsNull(params.page) ? 1 : params.page;
                        var itemList = [];
                        var row = data.rows;
                        for(var i=0;i<row.length;i++){
                            itemList.push({id: row[i].id, text: row[i].dictValue});
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
            })
        }

        //获得年月日      得到日期oTime
        function getMyDate(str){
            var oDate = new Date(str),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth()+1,
                oDay = oDate.getDate(),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oSen = oDate.getSeconds(),
                oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
            return oTime;
        };
        //补0操作
        function getzf(num){
            if(parseInt(num) < 10){
                num = '0'+num;
            }
            return num;
        }


</script>
