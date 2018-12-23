<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<link rel="stylesheet" href="${contextPath}/static/assets/css/load.css" />

<div class="row">
	<div class="col-xs-12">
		<div class="well well-sm">
			<shiro:hasPermission name="${ROLE_KEY}:customer:add">
				<a id="addInformationButton" role="button" class="btn btn-info btn-sm" data-toggle="modal">
					添加记录
				</a>
			</shiro:hasPermission>
			<shiro:lacksPermission name="${ROLE_KEY}:customer:add">
				<a id="addInformationButton" disabled="disabled" role="button" class="btn btn-info btn-sm" data-toggle="modal">
					添加记录
				</a>
	        </shiro:lacksPermission>
	        <shiro:hasPermission name="${ROLE_KEY}:customer:edit">
				<a id="editInformationButton" role="button" class="btn btn-purple btn-sm" data-toggle="modal">
					编辑记录
				</a>
			</shiro:hasPermission>
			<shiro:lacksPermission name="${ROLE_KEY}:customer:edit">
				<a id="editInformationButton" role="button" disabled="disabled" class="btn btn-purple btn-sm" data-toggle="modal">
					编辑记录
				</a>			
			</shiro:lacksPermission>
		</div>
		
		<table id="lyxx-table"></table>
			
		<div id="grid-pager"></div>

		<script type="text/javascript">
			var $path_base = "${contextPath}/static";//in Ace demo this will be used for editurl parameter
		</script>

		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
</div><!-- /.row -->

<div id="modal-table" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="min-width: 820px;">
		<form id="informationForm">
			<div class="modal-content">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						信息新增
					</div>
				</div>
				<div class="modal-body" style="max-height: 500px;overflow-y: scroll;">
					<div class="row">
						<div class="col-xs-12 col-sm-12">
							<div class="widget-box">
								<div class="widget-header">
									<h4 class="widget-title">客户信息新增</h4>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<input type="hidden" id="id">
										<div style="display: none">
											<label for="form-field-8">证件种类</label>
											<input id="idtype" class="form-control" type="text"> 
											<label for="form-field-8">证件号码</label>
											<input id="idnumber" class="form-control" type="text">
											<label for="form-field-8">证件有效期</label>
											<input id="idexpiresend" class="form-control" type="text">
										</div>
										<hr />
										<div>
											<label for="form-field-8">名称</label>
											<input id="name" class="form-control" type="text"> 
											<label for="form-field-8">地址</label>
											<input id="address" class="form-control" type="text">
										</div>
										<hr />
										<div>
											<label for="form-field-8">联系人</label>
											<input id="contactname" class="form-control" type="text"> 
											<label for="form-field-8">联系电话</label>
											<input id="contactnumber" class="form-control" type="text"> 
											<label for="form-field-8">税号</label>
											<input id="taxnumber" class="form-control" type="text">
										</div>
										<hr />
										<div>
											<label for="form-field-8">银行账号</label>
											<input id="account" class="form-control" type="text"> 
											<label for="form-field-8">户名</label>
											<input id="accountname" class="form-control" type="text"> 
											<label for="form-field-8">开户行</label>
											<input id="bankname" class="form-control" type="text">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer no-margin-top">
					<div class="text-center">
						<button id="submitButton" type="submit" class="btn btn-app btn-success btn-xs">
							<i class="ace-icon fa fa-floppy-o bigger-160"></i>
							保存
						</button>
						<button class="btn btn-app btn-pink btn-xs" data-dismiss="modal">
							<i class="ace-icon fa fa-share bigger-160"></i>
							取消
						</button>
					</div>
				</div>
			</div><!-- /.modal-content -->
		</form>
	</div><!-- /.modal-dialog -->
</div>

<div id="modal-htxx" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="min-width: 66%;">
		<form id="getlistContract">
			<div class="modal-content">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						合同信息
					</div>
				</div>
				<div class="modal-body" style="max-height: 500px;overflow-y: scroll;">
						<div class="row">
							<div class="col-xs-12">
								<div class="table-header">
									历史合同信息
								</div>
				
								<table id="contract_table"></table>

                                <div id="grid-pager_contract"></div>
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
			</div><!-- /.modal-content -->
		</form>
	</div><!-- /.modal-dialog -->
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
                                            <input class="form-control" type="text" name="date-range-picker" id="htsj_edit"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="buildid_edit">租赁地址：</label>
                                    <div class="col-sm-4">
                                        <select class="select2" id="buildid_edit" style="width: 100%"></select>
                                    </div>
                                    <div class="col-sm-4" style="display: none" id="div1_edit">
                                        <%--<select class="select2" id="propertyid_edit" style="width:100%" multiple></select>--%>
                                        <input type="text"  class="width-100"  id="PropertyIds_edit"   />
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
                                        <input type="text" placeholder="" class="width-100" id="price_edit" />
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
                                        <select class="select2 width-100" id="partacode_edit" style="width: 100%"></select>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partaaddress_edit">地址：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partaaddress_edit" class="width-100" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partalegalperson_edit">法定代表人：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partalegalperson_edit"_edit class="width-100"  />
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partancontact_edit">联系电话：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partancontact_edit" class="width-100" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partaaccount_edit">帐户名称：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partaaccount_edit" class="width-100" />
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="bankname_edit">开户银行：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="bankname_edit" class="width-100" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partaaccountname_edit">帐 号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partaaccountname_edit" class="width-100" />
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partataxnumber_edit">税 号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partataxnumber_edit" class="width-100" />
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
                                        <select class="select2" id="partbcode_edit" style="width: 100%">
                                        </select>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbtype_edit">类型：</label>
                                    <div class="col-sm-4">
                                        <select class="select2" id="partbtype_edit" style="width: 100%">
                                            <option value="0">企业</option>
                                            <option value="1">个人</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbaddress_edit">地址：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbaddress_edit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partblegalperson_edit">法定代表人：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partblegalperson_edit" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbncontact_edit">联系电话：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbncontact_edit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbtaxnumber_edit">税 号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbtaxnumber_edit" class="width-100" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbncontact_edit">开户行：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbbankname_edit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbtaxnumber_edit">银行账号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbaccount_edit" class="width-100" />
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbtaxnumber_edit">户名：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbaccountname_edit" class="width-100" />
                                    </div>
                                    <div id="div3_edit" style="display: none;">
                                        <label class="col-sm-2 control-label blue" style="text-align: left" for="partbname_edit">承租方名称：</label>
                                        <div class="col-sm-4">
                                            <input type="text" id="partbname_edit" class="width-100"/>
                                        </div>
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
                                        <input type="text" id="buildarera_edit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="tenantarea_edit">承租建筑面积(平方米)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="tenantarea_edit" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="propertyfee_edit">物业管理费(元)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="propertyfee_edit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="paytype_edit">付款方式：</label>
                                    <div class="col-sm-4">
                                        <select class="select2" id="paytype_edit" style="width: 100%">
                                        </select>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="deposit_edit">物业费押金(元)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="deposit_edit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="electric_edit">电费单价(元/千瓦小时)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="electric_edit" class="width-100"/>
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
                                        <input type="text" id="manager_edit" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="undergroundunit_edit">地下车位价格(元/月/车)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="undergroundunit_edit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="surfaceunit_edit">地面车位价格(元/月/车)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="surfaceunit_edit" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="undergroundnumber_edit">地下车位数量(个)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="undergroundnumber_edit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="surfacenumber_edit">地面车位数量(个)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="surfacenumber_edit" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="rent_edit">车位租赁费(元/月)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="rent_edit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="prepay_edit">支付方式(月预付)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="prepay_edit" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="cardfee_edit">停车证制作费：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="cardfee_edit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="reissuecardfee_edit">停车证补办费：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="reissuecardfee_edit" class="width-100"/>
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
                                        <textarea class="width-100" id="supplementaryterms_edit" placeholder="补充合同条款使用" rows="5"></textarea>
                                    </div>
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


<!-- page specific plugin scripts -->
<script type="text/javascript">
				var scripts = [null, "${contextPath}/static/assets/js/jqGrid/jquery.jqGrid.js", "${contextPath}/static/assets/js/jqGrid/i18n/grid.locale-cn.js", "${contextPath}/static/assets/js/jquery-ui.custom.js",
				               "${contextPath}/static/assets/js/jquery.ui.touch-punch.js", "${contextPath}/static/assets/js/markdown/markdown.js","${contextPath}/static/assets/js/markdown/bootstrap-markdown.js",
				               "${contextPath}/static/assets/js/jquery.hotkeys.js", "${contextPath}/static/assets/js/bootstrap-wysiwyg.js", "${contextPath}/static/assets/js/bootbox.js",
				               "${contextPath}/static/assets/js/jquery.gritter.js","${contextPath}/static/assets/js/date-time/moment.js", "${contextPath}/static/assets/js/date-time/bootstrap-datepicker.js",
				               "${contextPath}/static/assets/js/date-time/bootstrap-timepicker.js", "${contextPath}/static/assets/js/date-time/daterangepicker.js", "${contextPath}/static/assets/js/date-time/bootstrap-datetimepicker.js",
				               "${contextPath}/static/assets/js/select2.js","${contextPath}/static/assets/js/jquery.media.js","${contextPath}/static/assets/js/load-min.js" ,null]
		$(".page-content-area").ace_ajax("loadScripts", scripts, function() {
        	// inline scripts related to this page
        	jQuery(function($) {
        		
        		var grid_selector = "#lyxx-table";
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
        		
        		 jQuery("#contract_table").jqGrid({
                     subGrid: false,
                    // postData:{"filters":"{\"groupOp\":\"AND\",\"rules\":[{\"field\":\"partbcode\",\"op\":\"cn\",\"data\":\""+id+"\"}]}"},
                     url: "${contextPath}/sys/contract/getContractByCondition",
                     datatype: "json",
                     height: 450,
                     colNames: ["ID", "合同编号", "管理方", "承租方", "合同类型", "承租方联系电话", "租赁开始时间","租赁结束时间", "合同状态",""],
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
                         formatter:function(celval, options, rowdata){
                             if(celval=='1'){
                                 return "物业合同";
                             }else if(celval=='2'){
                                 return "停车合同";
                             }else if(celval=='3'){
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
                     } ,
                     {
                         label : "",
                         width : 200,
                         editable : true,
                         search : false,
                         align:'center',
                         formatter:function(id,op,row){
                             return '<a class="btn btn-info btn-sm" onclick="getmsgcontractInfo(\''+row.id+'\')">详情</a>';
                         }

                     }],
                     sortname: "id",
                     sortorder: "asc",
                     viewrecords: true,
                     rowNum: 10,
                     rowList: [10, 20, 30],
                     pager: "#grid-pager_contract",
                     altRows: true,
                     multiselect: true,
                     multiboxonly: true,

                     editurl: "${contextPath}/sys/compact/operateCompact",

                 });
        		
        		jQuery(grid_selector).jqGrid({
        			subGrid : false,
        			url : "${contextPath}/recode/customer/geCustomerByCondition?type=0",
        			datatype : "json",
        			height : 450,
        			align:'center',
        			colNames : ["编号",'证件种类','证件号码','证件有效期','名称','地址','联系人','联系电话','税号','银行账号','户名','开户行','' ],
        			colModel : [ 
			       {
						 name:'id',
						 width : 150,
						 hidden:true,
					},
					{
						name : "idtype",
						width : 150,
						 hidden:true,
					},{
						name : "idnumber",
						width : 200,
						 hidden:true,
					},{
						name : "idexpiresend",
						width : 100,
						 hidden:true,
					}, {
						name : "name",
						width : 150,
						searchoptions : {sopt : ["cn","eq"]},
					}, {
						name : "address",
						width : 200,
						searchoptions : {sopt : ["cn","eq"]},
					}, {
						name : "contactname",
						width : 100,
						searchoptions : {sopt : ["cn","eq"]},
					}, {
						name : "contactnumber",
						width : 100,
						search:false,
					}, {
						name : "taxnumber",
						width : 100,
						search:false,
					}, {
						name : "account",
						width : 100,
						search:false,
					}, {
						name : "accountname",
						width : 100,
						search:false,
					}, {
						name : "bankname",
						width : 100,
						search:false,
					}
					 ,
        			{
        				label : "",
        				width : 200,
        				editable : true,
        				search : false,
        				align:'center',
        				formatter:function(id,op,row){
        					return '<a class="btn btn-info btn-sm" onclick="getmsg(\''+row.id+'\')">合同信息</a>';
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
        			editurl :"${contextPath}/recode/customer/delCustomer",
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
        			$("#id").val('');
    				$("#modal-table").modal("toggle");
    				$("#informationForm")[0].reset();
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
        				$("#modal-table").modal("toggle");
        				$("#id").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).id);
        				$("#idtype").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).idtype);
        				$("#idnumber").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).idnumber);
        				$("#idexpiresend").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).idexpiresend);
        				$("#name").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).name);
        				$("#address").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).address);
        				$("#contactname").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).contactname);
        				$("#contactnumber").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).contactnumber);
        				$("#taxnumber").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).taxnumber);
        				$("#account").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).account);
        				$("#accountname").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).accountname);
        				$("#bankname").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).bankname);
        			}
        		});
        		
        		$("#informationForm").on("submit", function(e) {
        			var data = new Object();
        			data.type = '0';
        			data.idtype  = $("#idtype").val();
    				data.idnumber  = $("#idnumber").val();
    				data.idexpiresend  = $("#idexpiresend").val();
    				data.name  = $("#name").val();
    				data.address  = $("#address").val();
    				data.contactname  = $("#contactname").val();
    				data.contactnumber  = $("#contactnumber").val();
    				data.taxnumber  = $("#taxnumber").val();
    				data.account  = $("#account").val();
    				data.accountname  = $("#accountname").val();
    				data.bankname  = $("#bankname").val();
        			if($("#id").val() == '' || $("#id").val() == null || $("#id").val() == undefined){
        			}else{
        				data.id = $("#id").val();
        			}
   			    	$.ajax({
           				dataType : "json",
           				url : "${contextPath}/recode/customer/saveOrupdCustomer",
           				type : "post",
           				contentType: 'application/json',
              			data :JSON.stringify(data),
           				complete : function(xmlRequest) {
           					$("#modal-table").modal("toggle");
           					jQuery(grid_selector).trigger("reloadGrid");
           				}
           			});
        		});
        		
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
        		
        		// navButtons
        		jQuery(grid_selector).jqGrid("navGrid", pager_selector, { // navbar options
        			edit : false,
        			editicon : "ace-icon fa fa-pencil blue",
        			add : false,
        			addicon : "ace-icon fa fa-plus-circle purple",
        			del : <shiro:hasPermission name="${ROLE_KEY}:customer:delete">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:customer:delete">false</shiro:lacksPermission>,
        			delicon : "ace-icon fa fa-trash-o red",
        			search : <shiro:hasPermission name="${ROLE_KEY}:customer:search">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:customer:search">false</shiro:lacksPermission>,
        			searchicon : "ace-icon fa fa-search orange",
        			refresh : true,
        			refreshicon : "ace-icon fa fa-refresh blue",
        			view : <shiro:hasPermission name="${ROLE_KEY}:customer:view">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:customer:view">false</shiro:lacksPermission>,
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
        		if(<shiro:hasPermission name="${ROLE_KEY}:customer:export">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:customer:export">false</shiro:lacksPermission>){
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
		function getmsg(id){
		   
			$("#modal-htxx").modal('toggle');
            /**
             * 合同列表
             */
            getcontractlist(id);
		}


		function getcontractlist(id){
			jQuery("#contract_table").jqGrid('setGridParam',{
                datatype:'json',
                postData:{"filters":"{\"groupOp\":\"AND\",\"rules\":[{\"field\":\"partbcode\",\"op\":\"eq\",\"data\":\""+id+"\"}]}"}, //发送数据
                page:1
            }).trigger("reloadGrid"); //重新载入
        }

        function getmsgcontractInfo(id){
            var params = new Object();
            params.id =  id;
            $.ajax({
                dataType : "json",
                url : "${contextPath}/sys/contract/getContractWithInfoById",
                type : "post",
                contentType: 'application/json',
                data :JSON.stringify(params),
                complete: function (xmlRequest) {
                   console.log(xmlRequest);
                    if(xmlRequest.status=='200'){
                        contractInfo(JSON.parse(xmlRequest.responseText).rows[0]);
                    }else{
                        toastMessage('系统信息', xmlRequest.responseJSON.message);
                    }
                },
                error: function () {
                 
                }
            });
        }

        function contractInfo(data){
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
                    $('#PropertyIds_edit').val(data.propertyids);
                    $('#showPropertyIds_edit').val(data.propertyids);
                    //initPropertySelect2_html(data.buildid,'propertyid_edit',data.propertyids);
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
                $('#partbaccount_edit').val(data.partbaccount);
                $('#partbaccountname_edit').val(data.partbaccountname);
                $('#partbbankname_edit').val(data.partbbankname);
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
        
        function formatDate(date){
            return date.split("-")[1]+"/"+date.split("-")[2]+"/"+date.split("-")[0];
        }
        
        var buildInfo;
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
                                /*if($('#contype').val() == '1'){
                                    $('#propertyid').select2("val", " ");
                                    $('#showPropertyIds').val("");
                                    initPropertySelect2(e.params.data.id,'propertyid');
                                }*/
                            }else if(id=="buildid_edit"){
                                $('#showAddressId_edit').val(JSON.parse(data.responseText).rows[0].address);
                                /*if($('#contype_edit').val() == '1'){
                                    $('#propertyid_edit').select2("val", " ");
                                    $('#showPropertyIds_edit').val("");
                                    initPropertySelect2(e.params.data.id,'propertyid_edit');
                                }*/
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
                    if(data.status=='200'){
                        buildInfo =  JSON.parse(data.responseText).rows[0];
                    } else{
                        toastMessage("系统提示",xmlRequest.responseJSON.message);
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
                         }else{
                             toastMessage("系统提示",xmlRequest.responseJSON.message);
                         }
                     }
                 });
             })
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
                                 $('#partbaccount').val(JSON.parse(data.responseText).rows[0].account);
                                 $('#partbaccountname').val(JSON.parse(data.responseText).rows[0].accountname);
                                 $('#partbbankname').val(JSON.parse(data.responseText).rows[0].bankname);
                                 $("#partbtype").val(JSON.parse(data.responseText).rows[0].type).trigger("change");
                             }else if(id=='partbcode_edit'){
                                 $('#partbaddress_edit').val(JSON.parse(data.responseText).rows[0].address);
                                 $('#partblegalperson_edit').val(JSON.parse(data.responseText).rows[0].name);
                                 $('#partbncontact_edit').val(JSON.parse(data.responseText).rows[0].contactnumber);
                                 $('#partbtaxnumber_edit').val(JSON.parse(data.responseText).rows[0].taxnumber);
                                 $('#partbaccount_edit').val(JSON.parse(data.responseText).rows[0].account);
                                 $('#partbaccountname_edit').val(JSON.parse(data.responseText).rows[0].accountname);
                                 $('#partbbankname_edit').val(JSON.parse(data.responseText).rows[0].bankname);
                                 $("#partbtype_edit").val(JSON.parse(data.responseText).rows[0].type).trigger("change");
                             }
                         }else{
                             toastMessage("系统提示",xmlRequest.responseJSON.message);
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
</script>
