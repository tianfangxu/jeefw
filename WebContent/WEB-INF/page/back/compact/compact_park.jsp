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
<link rel="stylesheet" href="${contextPath}/static/assets/css/load.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/fileinput.min.css" />


<body>
    <div style="margin-bottom: 10px">
        合同期限 :
        <select class="col-xs-2 select2" id="htqx" style="float: none;height: 35px;" onchange="getdatareflush()">
            <option value="">所有</option>
            <option value="1">将到期</option>
            <option value="2">已过期</option>
        </select>
    </div>

    <!--操作按钮-->
    <div class="row">
        <div class="col-xs-12">
            <div class="well well-sm">
                <shiro:hasPermission name="${ROLE_KEY}:compact_park:add">
                    <a id="addCompactButton" role="button" class="btn btn-info btn-sm" data-toggle="modal">
                        添加合同
                    </a>
                </shiro:hasPermission>
                <shiro:lacksPermission name="${ROLE_KEY}:compact_park:add">
                    <a id="addCompactButton" role="button" class="btn btn-info btn-sm" data-toggle="modal">
                        添加合同
                    </a>
                </shiro:lacksPermission>
                <shiro:hasPermission name="${ROLE_KEY}:compact_park:edit">
                    <a id="editCompactButton" role="button" class="btn btn-purple btn-sm" data-toggle="modal">
                        编辑合同
                    </a>
                </shiro:hasPermission>
                <shiro:lacksPermission name="${ROLE_KEY}:compact_park:edit">
                    <a id="editCompactButton" role="button" class="btn btn-purple btn-sm"
                       data-toggle="modal">
                        编辑合同
                    </a>
                </shiro:lacksPermission>
                <shiro:hasPermission name="${ROLE_KEY}:compact_park:delete">
                    <a id="delCompactButton" role="button" class="btn btn-danger btn-sm" data-toggle="modal">
                        删除合同
                    </a>
                </shiro:hasPermission>
                <shiro:lacksPermission name="${ROLE_KEY}:compact_park:delete">
                    <a id="delCompactButton" role="button" class="btn btn-danger btn-sm"
                       data-toggle="modal">
                        删除合同
                    </a>
                </shiro:lacksPermission>
                <a id="viewCompactButton" role="button" class="btn btn-inverse btn-sm" data-toggle="modal">
                    合同预览
                </a>
                <a id="examineCompactButton" role="button" class="btn btn-pink btn-sm" data-toggle="modal">
                    提交审核
                </a>

            </div>

            <table id="grid-table"></table>

            <div id="grid-pager"></div>

            <script type="text/javascript">
                var $path_base = "${contextPath}/static";
            </script>

        </div>
    </div>

    <!--合同预览-->
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

    <!--附件上传-->
    <div id="modal-table3" class="modal fade" data-backdrop="static">
        <div class="modal-dialog" style="width: 60%;">
            <div class="modal-content">
                <div class="table-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <span class="white">&times;</span>
                    </button>
                    附件上传
                </div>
                <div style="height: 350px;">
                    <input name="uploadFile"  type="file" id="uploadFile" multiple class="file-loading" />
                </div>
            </div>
        </div>
    </div>

    <!--合同新增-->
    <div id="modal-table" class="modal fade" data-backdrop="static">
    <div class="modal-dialog" style="min-width: 820px;">
        <form id="compactForm" class="form-horizontal">
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
                    <div id="modal-tip" class="red clearfix"></div>
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
                                    <div><input type="hidden" id="id" /></div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="htsj">合同期限：</label>
                                    <div class="col-sm-4">
                                        <div class="input-group">
											<span class="input-group-addon">
												<i class="fa fa-calendar bigger-110"></i>
											</span>
                                            <input class="form-control" type="text" name="date-range-picker" id="htsj"/>
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left;display:none" for="contype">合同类型：</label>
                                    <div class="col-sm-4" style="display: none;">
                                        <select class="select2" id="contype" style="width: 100%">
                                            <option value="2" selected>协议停车合同</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="buildid">租赁地址：</label>
                                    <div class="col-sm-4">
                                        <select class="select2" id="buildid" style="width: 100%"></select>
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="showAddressId"></label>
                                    <div class="col-sm-4">
                                        <input type="text" placeholder="区路号" class="width-100" id="showAddressId" readonly/>
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="price">合同金额（元/年）：</label>
                                    <div class="col-sm-4">
                                        <input type="text" placeholder="" class="width-100" id="price" />
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partacode">管理方：</label>
                                    <div class="col-sm-4">
                                        <select class="select2 width-100" id="partacode" style="width: 100%"></select>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partaaddress">地址：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partaaddress" class="width-100" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partalegalperson">法定代表人：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partalegalperson" class="width-100"  />
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partancontact">联系电话：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partancontact" class="width-100" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partaaccount">帐户名称：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partaaccount" class="width-100" />
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="bankname">开户银行：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="bankname" class="width-100" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partaaccountname">帐 号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partaaccountname" class="width-100" />
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partataxnumber">税 号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partataxnumber" class="width-100" />
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbcode">承租方：</label>
                                    <div class="col-sm-4">
                                        <select class="select2" id="partbcode" style="width: 100%">
                                        </select>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbtype">类型：</label>
                                    <div class="col-sm-4">
                                        <select class="select2" id="partbtype" style="width: 100%" onchange="changePartbShow()">
                                            <option value="0">企业</option>
                                            <option value="1">个人</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbaddress">地址：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbaddress" class="width-100"/>
                                    </div>
                                    <div id="div4">
                                        <label class="col-sm-2 control-label blue" style="text-align: left" for="partblegalperson">法定代表人：</label>
                                        <div class="col-sm-4">
                                            <input type="text" id="partblegalperson" class="width-100"/>
                                        </div>
                                    </div>
                                    <div id="div5" style="display: none">
                                        <label class="col-sm-2 control-label blue" style="text-align: left" for="partbzjzl">证件种类：</label>
                                        <div class="col-sm-4">
                                            <select id="partbzjzl" class="form-control">
                                                <option value="身份证">身份证</option>
                                                <option value="护照">护照</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbncontact">联系电话：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbncontact" class="width-100"/>
                                    </div>
                                    <div id="div6">
                                        <label class="col-sm-2 control-label blue" style="text-align: left" for="partbtaxnumber">税 号：</label>
                                        <div class="col-sm-4">
                                            <input type="text" id="partbtaxnumber" class="width-100" />
                                        </div>
                                    </div>
                                    <div id="div7" style="display: none">
                                        <label class="col-sm-2 control-label blue" style="text-align: left" for="partbzjhm">证件号码：</label>
                                        <div class="col-sm-4">
                                            <input type="text" id="partbzjhm" class="width-100" />
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbncontact">开户行：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbbankname" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbtaxnumber">银行账号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbaccount" class="width-100" />
                                    </div>
                                </div>
                                <div class="form-group" >
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbtaxnumber">户名：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbaccountname" class="width-100" />
                                    </div>
                                    <div id="div3" style="display: none;">
                                        <label class="col-sm-2 control-label blue" style="text-align: left" for="partbname">承租方名称：</label>
                                        <div class="col-sm-4">
                                            <input type="text" id="partbname" class="width-100"/>
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="buildarera">房屋建筑面积(平方米)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="buildarera" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="tenantarea">承租建筑面积(平方米)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="tenantarea" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="propertyfee">物业管理费(元)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="propertyfee" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="paytype">付款方式：</label>
                                    <div class="col-sm-4">
                                        <select class="select2" id="paytype" style="width: 100%">
                                        </select>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="deposit">物业费押金(元)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="deposit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="electric">电费单价(元/千瓦小时)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="electric" class="width-100"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="widget-box cwxx">
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="manager">停车场地资产管理单位：</label>
                                    <div class="col-sm-10">
                                        <input type="text" id="manager" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="undergroundunit">地下车位价格(元/月/车)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="undergroundunit" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="surfaceunit">地面车位价格(元/月/车)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="surfaceunit" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="undergroundnumber">地下车位数量(个)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="undergroundnumber" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="surfacenumber">地面车位数量(个)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="surfacenumber" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="rent">车位租赁费(元/月)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="rent" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="prepay">支付方式(月预付)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="prepay" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="cardfee">停车证制作费：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="cardfee" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="reissuecardfee">停车证补办费：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="reissuecardfee" class="width-100"/>
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="supplementaryterms">其他条款：</label>
                                    <div class="col-sm-10">
                                        <textarea class="width-100" id="supplementaryterms" placeholder="补充合同条款使用" rows="5"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer no-margin-top">
                    <div class="text-center">
                        <button id="submitButton" type="button" class="btn btn-app btn-success btn-xs">
                            <i class="ace-icon fa fa-floppy-o bigger-160"></i>
                            保存
                        </button>
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

    <!--合同编辑-->
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="htsj_edit">合同期限：</label>
                                    <div class="col-sm-4">
                                        <div class="input-group">
											<span class="input-group-addon">
												<i class="fa fa-calendar bigger-110"></i>
											</span>
                                            <input class="form-control" type="text" name="date-range-picker" id="htsj_edit"/>
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left;display: none;" for="contype_edit">合同类型：</label>
                                    <div class="col-sm-4" style="display: none;">
                                        <select class="select2" id="contype_edit" style="width: 100%" >
                                            <option value="">--请选择--</option>
                                            <option value="1">物业管理服务合同</option>
                                            <option value="2">协议停车合同</option>
                                            <option value="3">其他合同</option>
                                        </select>
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="price_edit">合同金额（元/年）：</label>
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
                                        <select class="select2" id="partbtype_edit" style="width: 100%" onchange="changePartbShow_edit()">
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
                                    <div id="div4_edit" style="display:none">
                                        <label class="col-sm-2 control-label blue" style="text-align: left" for="partblegalperson_edit">法定代表人：</label>
                                        <div class="col-sm-4">
                                            <input type="text" id="partblegalperson_edit" class="width-100"/>
                                        </div>
                                    </div>
                                    <div id="div5_edit" style="display:none">
                                        <label class="col-sm-2 control-label blue" style="text-align: left" for="partbzjzl_edit">证件种类：</label>
                                        <div class="col-sm-4">
                                            <select id="partbzjzl_edit" class="form-control">
                                                <option value="身份证">身份证</option>
                                                <option value="护照">护照</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="partbncontact_edit">联系电话：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="partbncontact_edit" class="width-100"/>
                                    </div>
                                    <div id="div6_edit" style="display:none;">
                                        <label class="col-sm-2 control-label blue" style="text-align: left" for="partbtaxnumber_edit">税 号：</label>
                                        <div class="col-sm-4">
                                            <input type="text" id="partbtaxnumber_edit" class="width-100" />
                                        </div>
                                    </div>
                                    <div id="div7_edit" style="display:none;">
                                        <label class="col-sm-2 control-label blue" style="text-align: left" for="partbzjhm_edit">证件号码：</label>
                                        <div class="col-sm-4">
                                            <input type="text" id="partbzjhm_edit" class="width-100" />
                                        </div>
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="manager_edit">停车场地资产管理单位：</label>
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
                        <button id="submitButton_edit" type="button" class="btn btn-app btn-success btn-xs">
                            <i class="ace-icon fa fa-floppy-o bigger-160"></i>
                            保存
                        </button>
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
    var scripts = [null, "${contextPath}/static/assets/js/jqGrid/jquery.jqGrid.js", "${contextPath}/static/assets/js/jqGrid/i18n/grid.locale-cn.js", "${contextPath}/static/assets/js/jquery-ui.custom.js",
        "${contextPath}/static/assets/js/jquery.ui.touch-punch.js", "${contextPath}/static/assets/js/markdown/markdown.js","${contextPath}/static/assets/js/markdown/bootstrap-markdown.js",
        "${contextPath}/static/assets/js/jquery.hotkeys.js", "${contextPath}/static/assets/js/bootstrap-wysiwyg.js", "${contextPath}/static/assets/js/bootbox.js",
        "${contextPath}/static/assets/js/jquery.gritter.js","${contextPath}/static/assets/js/date-time/moment.js", "${contextPath}/static/assets/js/date-time/bootstrap-datepicker.js",
        "${contextPath}/static/assets/js/date-time/bootstrap-timepicker.js", "${contextPath}/static/assets/js/date-time/daterangepicker.js", "${contextPath}/static/assets/js/date-time/bootstrap-datetimepicker.js",
        "${contextPath}/static/assets/js/select2.js","${contextPath}/static/assets/js/jquery.media.js","${contextPath}/static/assets/js/load-min.js" ,"${contextPath}/static/assets/js/fileinput.min.js" ,
        "${contextPath}/static/assets/js/zh.js",null]
    $(".page-content-area").ace_ajax("loadScripts", scripts, function () {
        jQuery(function ($) {
            $('.select2').select2({allowClear:true})
            getInitBuildValue();
            var grid_selector = "#grid-table";
            var pager_selector = "#grid-pager";

            var  roles = '<%=session.getAttribute("ROLE_KEY")%>' ;
            var  showBuild = true;
            if(roles.indexOf("ROLE_ADMIN")>-1){
                showBuild = false;
            }

            $(window).on("resize.jqGrid", function () {
                $(window).unbind("onresize");
                $(grid_selector).jqGrid("setGridWidth", $(".page-content").width());
                $(window).bind("onresize", this);
            })

            var parent_column = $(grid_selector).closest("[class*='col-']");
            $(document).on("settings.ace.jqGrid", function (ev, event_name, collapsed) {
                if (event_name === "sidebar_collapsed" || event_name === "main_container_fixed") {
                    setTimeout(function () {
                        $(grid_selector).jqGrid("setGridWidth", parent_column.width());
                    }, 0);
                }
            });

            initBuildSelect2('buildid');
            initPartaSelect2('partacode');
            initPaytypeSelect2('paytype','WYYJ');
            initPartbSelect2('partbcode');

            $(document).keydown(function (event) {
                var key = window.event ? event.keyCode : event.which;
                if (key == 13) {
                    if ($("#search-input").val() == "") {
                        toastMessage('系统信息', '请输入检索词！');
                    } else {
                        $.ajax({
                            dataType: "json",
                            url: "${contextPath}/sys/compact/getCompactHibernateSearch",
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

           /* var rule = new Object();
            rule.field = 'contype';
            rule.op = 'eq';
            rule.data = '2';
            var rules = new Array();
            rules.push(rule);*/

            jQuery(grid_selector).jqGrid({
                subGrid: false,
               // postData:generateParams(new Object(),rules),
                url: "${contextPath}/sys/contract/getContractByCondition?contype=2",
                datatype: "json",
                height: 450,
                width:window.screen.availWidth-20,
                colNames: ["ID", "合同编号","楼宇", "管理方", "承租方", "合同类型", "承租方联系电话", "租赁开始时间","租赁结束时间", "合同状态","合同金额","操作"],
                colModel: [{
                    name: "id",
                    width: 60,
                    hidden:true
                }, {
                    name: "sysnumber",
                    width: 120,
                    searchoptions: {sopt: ["cn","eq"]}
                },  {
                    name: "buildname",
                    width: 120,
                    search:false,
                    hidden:showBuild
                },{
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
                    search: false,
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
                },{
                    name: "totalamount",
                    width: 100,
                    search: false
                }, {
                    name: "operation",
                    width: 100,
                    search: false,
                    formatter:function(celval, options, rowdata){
                        return  '<a role="button" class="btn btn-sm" onclick="openUploadModel(\''+rowdata.id+'\')">上传附件</a>&nbsp;&nbsp;&nbsp;<a role="button" class="btn btn-sm" onclick="loadFile(\''+rowdata.id+'\')">下载附件</a>';
                    }
                }],
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
                beforeSelectRow: beforeSelectRow,
                editurl: "${contextPath}/sys/compact/operateCompact",
                gridComplete:function(){
                    var ids = $(grid_selector).getDataIDs();
                    for (var i = 0; i < ids.length; i++) {
                        var rowData = $(grid_selector).getRowData(ids[i]);
                        var httime = rowData.enddate;
                        var today = new Date();
                        var date = new Date(today);
                        var today_time = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                        if (compareDate(httime,today_time)) {
                            $('#' + ids[i]).find("td").css("background-color", "#FFB6C1");
                        }
                        date.setMonth(date.getMonth() + 4);
                        var today_time4 = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                        if (compareDate(today_time,httime)&&compareDate(httime,today_time4)) {
                            $('#' + ids[i]).find("td").css("background-color", "#FFE4CA");
                        }
                    }
                }
            });

            $('#PropertyIds').on('input propertychange',function(){
                var result = $(this).val();
                $('#showPropertyIds').val(result);
            });

            $('#PropertyIds_edit').on('input propertychange',function(){
                var result = $(this).val();
                $('#showPropertyIds_edit').val(result);
            });

            $(window).triggerHandler("resize.jqGrid");

            function beforeSelectRow() {
                $(grid_selector).jqGrid('resetSelection');
                return true;
            }

            function aceSwitch(cellvalue, options, cell) {
                setTimeout(function () {
                    $(cell).find("input[type=checkbox]").addClass("ace ace-switch ace-switch-5").after("<span class='lbl'></span>");
                }, 0);
            }

            $("#survey").ace_wysiwyg({
                toolbar:
                    [
                        "font", null,
                        "fontSize", null,
                        {name: "bold", className: "btn-info"},
                        {name: "italic", className: "btn-info"},
                        {name: "strikethrough", className: "btn-info"},
                        {name: "underline", className: "btn-info"}, null,
                        {name: "insertunorderedlist", className: "btn-success"},
                        {name: "insertorderedlist", className: "btn-success"},
                        {name: "outdent", className: "btn-purple"},
                        {name: "indent", className: "btn-purple"}, null,
                        {name: "justifyleft", className: "btn-primary"},
                        {name: "justifycenter", className: "btn-primary"},
                        {name: "justifyright", className: "btn-primary"},
                        {name: "justifyfull", className: "btn-inverse"}, null,
                        {name: "createLink", className: "btn-pink"},
                        {name: "unlink", className: "btn-pink"}, null,
                        {name: "insertImage", className: "btn-success"}, null,
                        "foreColor", null,
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
                }
                $("<div class='alert'> <button type='button' class='close' data-dismiss='alert'>&times;</button>" + "<strong>File upload error</strong> " + msg + " </div>").prependTo("#alerts");
            }

            $("#addCompactButton").bind("click", function () {
                $("#modal-table").modal("toggle");
                $("#compactForm")[0].reset();
                $("#editor").html("");
                $("#modal-tip").html("");
                initBuildSelect2('buildid');
                if(initBuildValue.length>0){
                    $('#buildid').append(new Option(initBuildValue[1],initBuildValue[0],true,true)).trigger("change");
                    $('#showAddressId').val(initBuildValue[2]);
                }
                $("#partacode").append(new Option("上海交投物业管理有限公司","1",true,true)).trigger("change");
                $('#partaaddress').val("上海市徐汇区建国东路525号");
                $('#partalegalperson').val("凌军");
                $('#partancontact').val("021-54355520");
                $('#partaaccount').val("98410155260000642");
                $('#partaaccountname').val("上海交投物业管理有限公司");
                $('#bankname').val("浦发银行大众大厦支行");
                $('#partataxnumber').val("913101011345400534");
                initPartaSelect2('partacode');
                initPaytypeSelect2('paytype','WYYJ');
                initPartbSelect2('partbcode');
                $('#manager').val("上海交投物业管理有限公司");

            });

            $("#editCompactButton").bind("click", function () {
                var selectedId = $(grid_selector).jqGrid("getGridParam", "selrow");
                if (null == selectedId) {
                    toastMessage('系统信息', '请选择记录！');
                } else {
                    if(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).auditstate == '已完成'){
                        $('#submitButton_edit').css('display','none');
                    }else{
                        $('#submitButton_edit').css('display','');
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
                            }else{
                                toastMessage('系统信息', xmlRequest.responseJSON.message);
                            }
                        },
                        error: function () {
                            $.mask_close_all();
                        }
                    });
                }
            });

            $("#examineCompactButton").bind("click", function () {
                var selectedId = $(grid_selector).jqGrid("getGridParam", "selrow");
                if (null == selectedId) {
                    toastMessage('系统信息', '请选择记录！');
                } else {
                    if (jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).auditstate != '待提交') {
                        toastMessage('系统信息', '该合同已在审核中！');
                    } else {
                        bootbox.confirm({
                            message: "确定提交审核？",
                            buttons: {
                                confirm: {
                                    label: '确定',
                                    className: 'btn-success',
                                },
                                cancel: {
                                    label: '取消',
                                    className: 'btn-danger'
                                }
                            },
                            callback: function (result) {
                                if(result){
                                    var params = new Object();
                                    params.id =  selectedId;
                                    params.opinion = "";
                                    $.ajax({
                                        dataType : "json",
                                        url : "${contextPath}/sys/flow/submitAudit",
                                        type : "post",
                                        contentType: 'application/json',
                                        data :JSON.stringify(params),
                                        beforeSend: function () {
                                            $.mask_fullscreen();
                                        },
                                        complete: function (data) {
                                            jQuery(grid_selector).trigger("reloadGrid");
                                            $.mask_close_all();
                                            toastMessage("系统提示",data.responseJSON.message);
                                        },
                                        error: function () {
                                            $.mask_close_all();
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });

            $("#delCompactButton").bind("click", function () {
                var selectedId = $(grid_selector).jqGrid("getGridParam", "selrow");
                if (null == selectedId) {
                    toastMessage('系统信息', '请选择记录！');
                } else {
                    if (jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).auditstate == '审核中') {
                        toastMessage('系统信息', '该合同已在审核中！');
                    } else if(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).auditstate == '已完成'){
                        toastMessage('系统信息', '该合同已经审核完毕！');
                    } else {
                        bootbox.confirm({
                            message: "确定删除？",
                            buttons: {
                                confirm: {
                                    label: '确定',
                                    className: 'btn-success'
                                },
                                cancel: {
                                    label: '取消',
                                    className: 'btn-danger'
                                }
                            },
                            callback: function (result) {
                                if (result) {
                                    var params = new Object();
                                    params.id =  selectedId;
                                    $.ajax({
                                        dataType : "json",
                                        url : "${contextPath}/sys/contract/deleteContract",
                                        type : "post",
                                        contentType: 'application/json',
                                        data :JSON.stringify(params),
                                        beforeSend: function () {
                                            $.mask_fullscreen();
                                        },
                                        complete: function (data) {
                                            jQuery(grid_selector).trigger("reloadGrid");
                                            $.mask_close_all();
                                            toastMessage("系统提示",data.responseJSON.message);
                                        },
                                        error: function () {
                                            $.mask_close_all();
                                        }
                                    });
                                }
                            }

                        });
                    }
                }
            });

            $("#submitButton").on("click", function (e) {
                var re = /^[0-9]+.?[0-9]*$/;

                //基础信息校验
                if ($.trim($("#contype").val()) == "") {
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请选择合同类型");
                    return;
                }
                if($.trim($('#htsj').val())== ""){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请选择合同期限");
                    return;
                }
                if($('#buildid').val() == null){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请选择楼宇");
                    return;
                }
                if($("#contype").val() == '1'){
                    if($.trim($('#PropertyIds').val()) == ""){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请输入楼宇具体的管理单元");
                        return;
                    }
                }
                if($("#contype").val() == '3'){
                    if($.trim($('#price').val())==''||!re.test($.trim($('#price').val()))) {
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请填写合同金额，只能为数字");
                        return;
                    }
                }

                //甲方信息校验
                if($('#partacode').val()==null){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请选择甲方基本资料");
                    return;
                }
                if($.trim($('#partaaddress').val())==''){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请填写甲方地址");
                    return;
                }
                if($.trim($('#partalegalperson').val())==''){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请填写甲方法定代表人");
                    return;
                }
                if($.trim($('#partancontact').val())==''){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请填写甲方联系电话");
                    return;
                }
                if($.trim($('#partaaccount').val())==''){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请填写甲方账号");
                    return;
                }
                if($.trim($('#bankname').val())==''){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请填写甲方开户银行");
                    return;
                }
                if($.trim($('#partaaccountname').val())==''){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请填写甲方帐号");
                    return;
                }
                if($.trim($('#partataxnumber').val())==''){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请填写甲方税号");
                    return;
                }

                //乙方信息校验
                if($.trim($('#partbaccountname').val())==''){
                    location.href = "#modal-tip";
                    $("#modal-tip").html("请填写乙方户名");
                    return;
                }
                if($('#partbcode').val()=='99999'){
                    if($.trim($('#partbname').val())==''){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请填写承租方名称");
                        return;
                    }
                }
                if($("#contype").val() == '1'){
                    //校验房屋
                    if($.trim($('#buildarera').val())==''){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请填写房屋建筑面积");
                        return;
                    }
                    if($.trim($('#tenantarea').val())==''){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请填写承租建筑面积");
                        return;
                    }
                    if($.trim($('#propertyfee').val())==''){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请填写物业管理费");
                        return;
                    }
                    if($('#paytype').val()==null){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请选择付款方式");
                        return;
                    }
                    if($.trim($('#deposit').val())==''){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请填写物业费押金");
                        return;
                    }
                    if($.trim($('#electric').val())==''){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请填写电费单价");
                        return;
                    }
                }else if($("#contype").val() == '2'){
                    //校验停车
                    if($.trim($('#manager').val())==''){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请填写停车场地资产产权单位");
                        return;
                    }
                    if($.trim($('#rent').val())==''||!re.test($.trim($('#rent').val()))){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请填写车位租赁费,只能为数字");
                        return;
                    }
                    if($.trim($('#prepay').val())==''||!re.test($.trim($('#prepay').val()))){
                        location.href = "#modal-tip";
                        $("#modal-tip").html("请填写支付方式(月预付),只能为数字");
                        return;
                    }
                }
                var params = generateAddData();
                $.ajax({
                    dataType: "json",
                    url: "${contextPath}/sys/contract/saveContract",
                    type: "post",
                    contentType: 'application/json',
                    data: JSON.stringify(params),
                    beforeSend: function () {
                        $("#modal-table").modal("toggle");
                        $.mask_fullscreen();
                    },
                    complete: function (xmlRequest) {
                        console.log(xmlRequest);
                        jQuery(grid_selector).trigger("reloadGrid");
                        $.mask_close_all();
                        toastMessage("系统提示",xmlRequest.responseJSON.message);
                        clearForm("compactForm");
                    },
                    error: function () {
                        $.mask_close_all();
                    }
                });
            });

            $("#submitButton_edit").on("click", function (e) {
                var re = /^[0-9]+.?[0-9]*$/;

                //基础信息校验
                if ($.trim($("#contype_edit").val()) == "") {
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请选择合同类型");
                    return;
                }
                if($.trim($('#htsj_edit').val())== ""){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请选择合同期限");
                    return;
                }
                if($('#buildid_edit').val() == null){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请选择楼宇");
                    return;
                }
                if($("#contype_edit").val() == '1'){
                    if($('#PropertyIds_edit').val() == ""){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请选择楼宇具体的管理单元");
                        return;
                    }
                }
                if($("#contype_edit").val() == '3'){
                    if($.trim($('#price_edit').val())==''||!re.test($.trim($('#price_edit').val()))) {
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请填写合同金额，只能为数字");
                        return;
                    }
                }

                //甲方信息校验
                if($('#partacode_edit').val()==null){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请选择甲方基本资料");
                    return;
                }
                if($.trim($('#partaaddress_edit').val())==''){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请填写甲方地址");
                    return;
                }
                if($.trim($('#partalegalperson_edit').val())==''){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请填写甲方法定代表人");
                    return;
                }
                if($.trim($('#partancontact_edit').val())==''){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请填写甲方联系电话");
                    return;
                }
                if($.trim($('#partaaccount_edit').val())==''){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请填写甲方账号");
                    return;
                }
                if($.trim($('#bankname_edit').val())==''){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请填写甲方开户银行");
                    return;
                }
                if($.trim($('#partaaccountname_edit').val())==''){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请填写甲方帐号");
                    return;
                }
                if($.trim($('#partataxnumber_edit').val())==''){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip-edit").html("请填写甲方税号");
                    return;
                }

                //乙方信息校验

                if($.trim($('#partbaccountname_edit').val())==''){
                    location.href = "#modal-tip-edit";
                    $("#modal-tip").html("请填写乙方户名");
                    return;
                }

                if($('#partbcode_edit').val()=='99999'){
                    if($.trim($('#partbname_edit').val())==''){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请填写承租方名称");
                        return;
                    }
                }
                if($("#contype_edit").val() == '1'){
                    //校验房屋
                    if($.trim($('#buildarera_edit').val())==''){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请填写房屋建筑面积");
                        return;
                    }
                    if($.trim($('#tenantarea_edit').val())==''){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请填写承租建筑面积");
                        return;
                    }
                    if($.trim($('#propertyfee_edit').val())==''){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请填写物业管理费");
                        return;
                    }
                    if($('#paytype_edit').val()==null){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请选择付款方式");
                        return;
                    }
                    if($.trim($('#deposit_edit').val())==''){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请填写物业费押金");
                        return;
                    }
                    if($.trim($('#electric_edit').val())==''){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请填写电费单价");
                        return;
                    }
                }else if($("#contype_edit").val() == '2'){
                    //校验停车
                    if($.trim($('#manager_edit').val())==''){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请填写停车场地资产产权单位");
                        return;
                    }
                    if($.trim($('#rent_edit').val())==''||!re.test($.trim($('#rent_edit').val()))){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请填写车位租赁费,只能为数字");
                        return;
                    }
                    if($.trim($('#prepay_edit').val())==''||!re.test($.trim($('#prepay_edit').val()))){
                        location.href = "#modal-tip-edit";
                        $("#modal-tip-edit").html("请填写支付方式(月预付),只能为数字");
                        return;
                    }
                }
                var params = generateEditData();
                $.ajax({
                    dataType: "json",
                    url: "${contextPath}/sys/contract/updateContract",
                    type: "post",
                    contentType: 'application/json',
                    data: JSON.stringify(params),
                    beforeSend: function () {
                        $("#modal-table-edit").modal("toggle");
                        $.mask_fullscreen();
                    },
                    complete: function (xmlRequest) {
                        jQuery(grid_selector).trigger("reloadGrid");
                        $.mask_close_all();
                        toastMessage("系统提示",xmlRequest.responseJSON.message);
                    },
                    error: function () {
                        $.mask_close_all();
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

            jQuery(grid_selector).jqGrid("navGrid", pager_selector, {
                edit: false,
                editicon: "ace-icon fa fa-pencil blue",
                del :false,
                delicon : "ace-icon fa fa-trash-o red",
                add: false,
                addicon: "ace-icon fa fa-plus-circle purple",
                search: <shiro:hasPermission name="${ROLE_KEY}:compact_park:search">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:compact_park:search">false</shiro:lacksPermission>,
                searchicon: "ace-icon fa fa-search orange",
                refresh: true,
                refreshicon: "ace-icon fa fa-refresh blue",
                view: <shiro:hasPermission name="${ROLE_KEY}:compact_park:view">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:compact_park:view">false</shiro:lacksPermission>,
                viewicon: "ace-icon fa fa-search-plus grey"
            }, {
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
                }
            }, {
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

            }, {
                recreateForm: true,
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    form.closest(".ui-jqdialog").find(".ui-jqdialog-title").wrap("<div class='widget-header' />")
                }
            })

            if (<shiro:hasPermission name="${ROLE_KEY}:compact_park:export">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:compact_park:export">false</shiro:lacksPermission>) {
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
                        var form = "<form name='csvexportform' action='${contextPath}/sys/contract/exportContract' method='post'>";
                        form = form + "<input type='hidden' name='csvBuffer' value='" + encodeURIComponent(rows) + "'>";
                        form = form + "</form><script>document.csvexportform.submit();</sc" + "ript>";
                        OpenWindow = window.open("", "");
                        OpenWindow.document.write(form);
                        OpenWindow.document.close();
                    }
                });
            }

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
                /**
                 * $(table).find("input:checkbox").addClass("ace") .wrap("<label />") .after("<span class="lbl align-top" />") $(".ui-jqgrid-labels th[id*="_cb"]:first-child")
                 * .find("input.cbox[type=checkbox]").addClass("ace") .wrap("<label />").after("<span class="lbl align-top" />");
                 */
            }

            function updateActionIcons(table) {
                /**
                 * var replacement = { "ui-ace-icon fa fa-pencil" : "ace-icon fa fa-pencil blue", "ui-ace-icon fa fa-trash-o" : "ace-icon fa fa-trash-o red", "ui-icon-disk" : "ace-icon fa fa-check green", "ui-icon-cancel" :
                 * "ace-icon fa fa-times red" }; $(table).find(".ui-pg-div span.ui-icon").each(function(){ var icon = $(this); var $class = $.trim(icon.attr("class").replace("ui-icon", "")); if($class in replacement)
                 * icon.attr("class", "ui-icon "+replacement[$class]); })
                 */
            }

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

            $(document).one("ajaxloadstart.page", function (e) {
                $(grid_selector).jqGrid("GridUnload");
                $(".ui-jqdialog").remove();
            });

            $('#contype').change(function () {
                if ($('#contype').val() == '1') {
                    $(".wyxx").css("display", "");
                    $(".cwxx").css("display", "none");
                    $("#div1").css("display", "");
                    $("#div2").css("display", "");

                } else if ($('#contype').val() == '2') {
                    $(".wyxx").css("display", "none");
                    $(".cwxx").css("display", "");
                    $("#div1").css("display", "none");
                    $("#div2").css("display", "none");
                } else if($('#contype').val() == '3'){
                    $(".wyxx").css("display", "none");
                    $(".cwxx").css("display", "none");
                    $("#div1").css("display", "none");
                    $("#div2").css("display", "none");
                }
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

            $('#partbcode').change(function () {
                if($('#partbcode').val()!='99999'){
                    $('#partbtype').attr('disabled',true);
                    $('#partbaddress').attr('readonly',true);
                    $('#partblegalperson').attr('readonly',true);
                    $('#partbncontact').attr('readonly',true);
                    $('#partbtaxnumber').attr('readonly',true);
                    $('#partbbankname').attr('readonly',true);
                    $('#partbaccount').attr('readonly',true);
                    $('#partbaccountname').attr('readonly',true);
                    $('#partbzjzl').attr('disabled',true);
                    $('#partbzjhm').attr('readonly',true);
                }else{
                    $('#partbtype').attr('disabled',false);
                    $('#partbaddress').attr('readonly',false);
                    $('#partblegalperson').attr('readonly',false);
                    $('#partbncontact').attr('readonly',false);
                    $('#partbtaxnumber').attr('readonly',false);
                    $('#partbbankname').attr('readonly',false);
                    $('#partbaccount').attr('readonly',false);
                    $('#partbaccountname').attr('readonly',false);
                    $('#partbzjzl').attr('disabled',false);
                    $('#partbzjhm').attr('readonly',false);
                }
            });

            $('#partbcode_edit').change(function () {
                if($('#partbcode_edit').val()!='99999'){
                    $('#partbtype_edit').attr('disabled',true);
                    $('#partbaddress_edit').attr('readonly',true);
                    $('#partblegalperson_edit').attr('readonly',true);
                    $('#partbncontact_edit').attr('readonly',true);
                    $('#partbtaxnumber_edit').attr('readonly',true);
                    $('#partbbankname_edit').attr('readonly',true);
                    $('#partbaccount_edit').attr('readonly',true);
                    $('#partbaccountname_edit').attr('readonly',true);
                    $('#partbzjzl_edit').attr('disabled',true);
                    $('#partbzjhm_edit').attr('readonly',true);
                }else{
                    $('#partbtype_edit').attr('disabled',false);
                    $('#partbaddress_edit').attr('readonly',false);
                    $('#partblegalperson_edit').attr('readonly',false);
                    $('#partbncontact_edit').attr('readonly',false);
                    $('#partbtaxnumber_edit').attr('readonly',false);
                    $('#partbbankname_edit').attr('readonly',false);
                    $('#partbaccount_edit').attr('readonly',false);
                    $('#partbaccountname_edit').attr('readonly',false);
                    $('#partbzjzl_edit').attr('disabled',false);
                    $('#partbzjhm_edit').attr('readonly',false);
                }
            });

        });
    });

    var initBuildValue = [];
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

    /**
     * 楼宇物业下拉框
     * @param buildid
     * @param propertyid
     */
    function initPropertySelect2(buildid,propertyid){
        $('#'+propertyid).select2({
            ajax: {
                type:"get",
                url: "${contextPath}/recode/property/getPropertyByCondition",
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
                    rule = new Object();
                    rule.field = 'build';
                    rule.op = 'eq';
                    rule.data =  buildid;
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
            multiBoolean:true,
            language: "zh-CN",
            placeholder:'--请选择--',//默认文字提示
            allowClear: true,//允许清空
            escapeMarkup: function (markup) { return markup; }, // 自定义格式化防止xss注入
            templateResult: function formatRepo(repo){return repo.text;}, // 函数用来渲染结果
            templateSelection: function formatRepoSelection(repo){return repo.text;} // 函数用于呈现当前的选择
        }).on("select2:select",function(e){
            if(propertyid=='propertyid'){
                showProperty('propertyid','showPropertyIds');
            }else{
                showProperty('propertyid_edit','showPropertyIds_edit');
            }
        }).on("select2:unselect",function(e){
            if(propertyid=='propertyid'){
                showProperty('propertyid','showPropertyIds');
            }else{
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
                    }else{
                        toastMessage("系统提示",xmlRequest.responseJSON.message);
                    }
                }
            });
        })
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
                    if(params.page==1){
                        itemList.push({id: 99999, text: '暂无信息,选此项可添加'});
                    }
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
                            if($('#partbtype').val()==1){
                                $('#partbzjhm').val(JSON.parse(data.responseText).rows[0].idnumber);
                                $("#partbzjzl").val(JSON.parse(data.responseText).rows[0].idtype).trigger("change");
                            }
                        }else if(id=='partbcode_edit'){
                            $('#partbaddress_edit').val(JSON.parse(data.responseText).rows[0].address);
                            $('#partblegalperson_edit').val(JSON.parse(data.responseText).rows[0].name);
                            $('#partbncontact_edit').val(JSON.parse(data.responseText).rows[0].contactnumber);
                            $('#partbtaxnumber_edit').val(JSON.parse(data.responseText).rows[0].taxnumber);
                            $('#partbaccount_edit').val(JSON.parse(data.responseText).rows[0].account);
                            $('#partbaccountname_edit').val(JSON.parse(data.responseText).rows[0].accountname);
                            $('#partbbankname_edit').val(JSON.parse(data.responseText).rows[0].bankname);
                            $("#partbtype_edit").val(JSON.parse(data.responseText).rows[0].type).trigger("change");
                            if($('#partbtype_edit').val()==1){
                                $('#partbzjhm_edit').val(JSON.parse(data.responseText).rows[0].idnumber);
                                $("#partbzjzl_edit").val(JSON.parse(data.responseText).rows[0].idtype).trigger("change");
                            }
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

    function checkIsNull(value){
        if(typeof(value)=="undefined" || value=='' || value==null){
            return true;
        }
        return false;
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

    function generateAddData(){
        var data = new Object();
        /*var propertyids = $('#propertyid').val();
        if(propertyids!=null&&propertyids.indexOf(",")){
            var ps = propertyids.toString().split(",");
            var propertys = "";
            for(var i=0;i<ps.length;i++){
                propertys +=   ps[i]+",";
            }
            data.propertyid = propertys.substring(0,propertys.length-1);
        }*/
        data.propertyText = $.trim($("#PropertyIds").val());
        data.id =  $("#id").val();
        data.contype = $('#contype').val();
        data.htsj = $('#htsj').val();
        data.totalamount = $.trim($('#price').val());
        data.buildid = $('#buildid').val();
        data.partacode = $('#partacode').val();
        data.partaname = $("#partacode option:checked").text();
        data.partaaddress = $.trim($('#partaaddress').val());
        data.partalegalperson = $.trim($('#partalegalperson').val());
        data.partancontact = $.trim($('#partancontact').val());
        data.partaaccount = $.trim($('#partaaccount').val());
        data.bankname = $.trim($('#bankname').val());
        data.partaaccountname = $.trim($('#partaaccountname').val());
        data.partataxnumber = $.trim($('#partataxnumber').val());
        var  partbcode =  $('#partbcode').val();
        if(partbcode=='99999'){
            data.partbcode = '';
            data.partbname = $.trim($("#partbname").val());
        }else{
            data.partbcode = partbcode;
            data.partbname = $("#partbcode option:checked").text();
        }
        data.partbaddress = $.trim($('#partbaddress').val());
        data.partblegalperson = $.trim($('#partblegalperson').val());
        data.partbcontact = $.trim($('#partbncontact').val());
        data.partbtaxnumber = $.trim($('#partbtaxnumber').val());
        data.partbaccount = $.trim($('#partbaccount').val());
        data.partbaccountname = $.trim($('#partbaccountname').val());
        data.partbbankname = $.trim($('#partbbankname').val());
        data.partbtype = $('#partbtype').val();
        data.subsidiary = $.trim($('#supplementaryterms').val());
        data.buildarea = $.trim($('#buildarera').val());
        data.tenantarea = $.trim($('#tenantarea').val());
        data.propertyfee =  $.trim($('#propertyfee').val());
        data.paytype = $('#paytype').val();
        data.deposit = $.trim($('#deposit').val());
        data.electric = $.trim($('#electric').val());
        data.manager = $.trim($('#manager').val());
        data.undergroundunit = $.trim($('#undergroundunit').val());
        data.surfaceunit = $.trim($('#surfaceunit').val());
        data.undergroundnumber = $.trim($('#undergroundnumber').val());
        data.surfacenumber = $.trim($('#surfacenumber').val());
        data.rent = $.trim($('#rent').val());
        data.prepay = $.trim($('#prepay').val());
        data.cardfee = $.trim($('#cardfee').val());
        data.reissuecardfee = $.trim($('#reissuecardfee').val());
        data.auditstate = 1;
        //证件种类和证件号码
        data.partbzjzl = $('#partbzjzl').val();
        data.partbzjhm = $.trim($('#partbzjhm').val());
        return data;
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

        if(data.partbtype==0){
            $('#div4_edit').css("display","");
            $('#div6_edit').css("display","");
        }else if(data.partbtype==1){
            $('#div5_edit').css("display","");
            $('#div7_edit').css("display","");
        }
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
        $("#partbzjzl_edit_edit").val(data.partbzjzl).trigger("change");
        $("#partbzjhm_edit").val(data.partbzjhm);
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
                } else{
                    toastMessage("系统提示",xmlRequest.responseJSON.message);
                }
            }
        });
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

    function generateEditData(){
        var data = new Object();
        /*var propertyids = $('#propertyid_edit').val();
        if(propertyids!=null&&propertyids.indexOf(",")){
            var ps = propertyids.toString().split(",");
            var propertys = "";
            for(var i=0;i<ps.length;i++){
                propertys +=   ps[i]+",";
            }
            data.propertyid = propertys.substring(0,propertys.length-1);
        }*/
        data.propertyText = $.trim($("#PropertyIds_edit").val());
        data.id =  $("#id_edit").val();
        data.contype = $('#contype_edit').val();
        data.htsj = $('#htsj_edit').val();
        data.totalamount = $.trim($('#price_edit').val());
        data.buildid = $('#buildid_edit').val();
        data.partacode = $('#partacode_edit').val();
        data.partaname = $("#partacode_edit option:checked").text();
        data.partaaddress = $.trim($('#partaaddress_edit').val());
        data.partalegalperson = $.trim($('#partalegalperson_edit').val());
        data.partancontact = $.trim($('#partancontact_edit').val());
        data.partaaccount = $.trim($('#partaaccount_edit').val());
        data.bankname = $.trim($('#bankname_edit').val());
        data.partaaccountname = $.trim($('#partaaccountname_edit').val());
        data.partataxnumber = $.trim($('#partataxnumber_edit').val());
        var  partbcode =  $('#partbcode_edit').val();
        if(partbcode=='99999'){
            data.partbcode = '';
            data.partbname = $.trim($("#partbname_edit").val());
        }else{
            data.partbcode = partbcode;
            data.partbname = $("#partbcode_edit option:checked").text();
        }
        data.partbaddress = $.trim($('#partbaddress_edit').val());
        data.partblegalperson = $.trim($('#partblegalperson_edit').val());
        data.partbcontact = $.trim($('#partbncontact_edit').val());
        data.partbtaxnumber = $.trim($('#partbtaxnumber_edit').val());
        data.partbaccountname = $.trim($('#partbaccountname_edit').val());
        data.partbaccount = $.trim($('#partbaccount_edit').val());
        data.partbbankname = $.trim($('#partbbankname_edit').val());
        data.partbtype = $('#partbtype_edit').val();
        data.subsidiary = $.trim($('#supplementaryterms_edit').val());
        data.buildarea = $.trim($('#buildarera_edit').val());
        data.tenantarea = $.trim($('#tenantarea_edit').val());
        data.propertyfee =  $.trim($('#propertyfee_edit').val());
        data.paytype = $('#paytype_edit').val();
        data.deposit = $.trim($('#deposit_edit').val());
        data.electric = $.trim($('#electric_edit').val());
        data.manager = $.trim($('#manager_edit').val());
        data.undergroundunit = $.trim($('#undergroundunit_edit').val());
        data.surfaceunit = $.trim($('#surfaceunit_edit').val());
        data.undergroundnumber = $.trim($('#undergroundnumber_edit').val());
        data.surfacenumber = $.trim($('#surfacenumber_edit').val());
        data.rent = $.trim($('#rent_edit').val());
        data.prepay = $.trim($('#prepay_edit').val());
        data.cardfee = $.trim($('#cardfee_edit').val());
        data.reissuecardfee = $.trim($('#reissuecardfee_edit').val());
        data.auditstate = 1;
        data.partbzjzl = $('#partbzjzl_edit').val();
        data.partbzjhm = $.trim($('#partbzjhm_edit').val());
        return data;
    }

    function clearForm(form) {
        var formlist3 = document.getElementById(form).getElementsByTagName("input");
        for (var i = 0; i < formlist3.length; i++) {
            if ((formlist3[i].type == "text" || formlist3[i].type == "hidden")) {
                $(formlist3[i]).val("");
            }
        }
        $("#" + form + " .select2").each(function () {
            $(this).val("").trigger("change");
        });
    }

    function openUploadModel(contract_id){
        var fileInput = new FileInput();
        fileInput.Init("uploadFile", "${contextPath}/sys/contract/uploadContractFile",contract_id);
        $("#modal-table3").modal("toggle");
    }

    var FileInput = function() {
        var oFile = new Object();
        //初始化fileinput控件（第一次初始化）
        oFile.Init = function(ctrlName, uploadUrl,param) {
            var control = $('#' + ctrlName);
            //初始化上传控件的样式
            control.fileinput({
                language: 'zh', //设置语言
                uploadUrl: uploadUrl,
                uploadAsync: true, //默认异步上传
                showUpload: false, //是否显示上传按钮
                showRemove: true, //显示移除按钮
                showCaption: true, //是否显示标题
                dropZoneEnabled: true, //是否显示拖拽区域
                //minImageWidth: 50, //图片的最小宽度
                //minImageHeight: 50,//图片的最小高度
                //maxImageWidth: 1000,//图片的最大宽度
                //maxImageHeight: 1000,//图片的最大高度
                //maxFileSize:0,//单位为kb，如果为0表示不限制文件大小
                //minFileCount: 0,
                maxFileCount: 10, //表示允许同时上传的最大文件个数
                enctype: 'multipart/form-data',
                browseClass: "btn btn-primary", //按钮样式: btn-default、btn-primary、btn-danger、btn-info、btn-warning
                previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
                uploadExtraData:function (previewId, index) {           //传参
                    var data = {
                        "id": param,      //此处自定义传参
                    };
                    return data;
                }
            });

            //文件上传完成之后发生的事件
            $("#uploadFile").on("fileuploaded", function(event, data, previewId, index) {

            });
        }
        return oFile;   //这里必须返回oFile对象，否则FileInput组件初始化不成功
    };


    function loadFile(id){
        var params = new Object();
        params.id =  id;
        $.ajax({
            dataType : "json",
            url : "${contextPath}/sys/contract/downloadFile",
            type : "post",
            contentType: 'application/json',
            data :JSON.stringify(params),
            beforeSend: function () {
                $.mask_fullscreen();
            },
            complete: function (xmlRequest) {
                $.mask_close_all();
                console.log(xmlRequest) ;
                if(xmlRequest.statusText=='success'){
                    if(xmlRequest.responseJSON.success=='success'){
                        var url = xmlRequest.responseJSON.message;
                        window.open("${contextPath}"+url.split("${contextPath}")[1]);
                    } else{
                        toastMessage('系统信息', xmlRequest.responseJSON.message);

                    }
                }else{
                    toastMessage('系统信息', xmlRequest.responseJSON.message);
                }
            },
            error: function () {
                $.mask_close_all();
            }
        });
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

    function getdatareflush(){
        jQuery("#grid-table").jqGrid('setGridParam',{
            datatype:'json',
            postData:{'htqx':$("#htqx").val()}, //发送数据
            page:1
        }).trigger("reloadGrid"); //重新载入
    }

    function compareDate(startDate, endDate) {
        var arrStart = startDate.split("-");
        var startTime = new Date(arrStart[0], arrStart[1], arrStart[2]);
        var startTimes = startTime.getTime();
        var arrEnd = endDate.split("-");
        var endTime = new Date(arrEnd[0], arrEnd[1], arrEnd[2]);
        var endTimes = endTime.getTime();
        if (endTimes<startTimes) {
            return false;
        }
        return true;
    }

    function getInitBuildValue(){
        var rule = new Object();
        rule.field = 'name';
        rule.op = 'cn';
        rule.data =  "";
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
                    initBuildValue.push(JSON.parse(data.responseText).rows[0].id);
                    initBuildValue.push(JSON.parse(data.responseText).rows[0].name);
                    initBuildValue.push(JSON.parse(data.responseText).rows[0].address);
                } else{
                    toastMessage("系统提示",data.responseJSON.message);
                }
            }
        });
    }

    function changePartbShow(){
        if($('#partbtype').val()==0){
            $('#div4').css("display","");
            $('#div6').css("display","");
            $('#div5').css("display","none");
            $('#div7').css("display","none");
        }else if($('#partbtype').val()==1){
            $('#div4').css("display","none");
            $('#div6').css("display","none");
            $('#div5').css("display","");
            $('#div7').css("display","");
        }
    }

    function changePartbShow_edit(){
        if($('#partbtype_edit').val()==0){
            $('#div4_edit').css("display","");
            $('#div6_edit').css("display","");
            $('#div5_edit').css("display","none");
            $('#div7_edit').css("display","none");
        }else if($('#partbtype_edit').val()==1){
            $('#div4_edit').css("display","none");
            $('#div6_edit').css("display","none");
            $('#div5_edit').css("display","");
            $('#div7_edit').css("display","");
        }
    }

</script>
