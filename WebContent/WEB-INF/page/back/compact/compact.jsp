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



<div class="row">
    <div class="col-xs-12">
        <div class="well well-sm">
            <shiro:hasPermission name="${ROLE_KEY}:buliding:add">
                <a id="addInformationButton" role="button" class="btn btn-info btn-sm" data-toggle="modal">
                    添加合同
                </a>
            </shiro:hasPermission>
            <shiro:lacksPermission name="${ROLE_KEY}:buliding:add">
                <a id="addInformationButton" disabled="disabled" role="button" class="btn btn-info btn-sm"
                   data-toggle="modal">
                    添加合同
                </a>
            </shiro:lacksPermission>
            <shiro:hasPermission name="${ROLE_KEY}:buliding:edit">
                <a id="editInformationButton" role="button" class="btn btn-purple btn-sm" data-toggle="modal">
                    编辑合同
                </a>
            </shiro:hasPermission>
            <a id="downloadInformationButton" role="button" class="btn btn-inverse btn-sm" data-toggle="modal">
                合同预览
            </a>
            <a id="examineInformationButton" role="button" class="btn btn-pink btn-sm" data-toggle="modal">
                提交审核
            </a>
            <shiro:lacksPermission name="${ROLE_KEY}:buliding:edit">
                <a id="editInformationButton" role="button" disabled="disabled" class="btn btn-purple btn-sm"
                   data-toggle="modal">
                    编辑合同
                </a>
            </shiro:lacksPermission>
            <form id="bulidingHibernateSearchForm" class="nav-search form-search">
                <span class="input-icon" style="position: relative;top: -30px;">
                    <input type="text" placeholder="全文检索 ..." class="nav-search-input" id="search-input"
                           autocomplete="off"/>
                    <i class="ace-icon fa fa-search nav-search-icon"></i>
                </span>
            </form>
        </div>

        <table id="grid-table"></table>

        <div id="grid-pager"></div>

        <script type="text/javascript">
            var $path_base = "${contextPath}/static";//in Ace demo this will be used for editurl parameter
        </script>

        <!-- PAGE CONTENT ENDS -->
    </div><!-- /.col -->
</div>
<!-- /.row -->


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


<div id="modal-table" class="modal fade" data-backdrop="static">
    <div class="modal-dialog" style="min-width: 820px;">
        <form id="bulidingForm" class="form-horizontal">
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
                    <div>
                        <input type="hidden" id="id"/>
                    </div>

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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="glf">合同类型：</label>
                                    <div class="col-sm-4">
                                        <select class="select2 width-100" id="htlx">
                                            <option value="">--请选择--</option>
                                            <option value="wy">物业管理服务合同</option>
                                            <option value="cw">协议停车合同</option>
                                        </select>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="htsj">合同时间：</label>
                                    <div class="col-sm-4">
                                        <div class="input-group">
											<span class="input-group-addon">
												<i class="fa fa-calendar bigger-110"></i>
											</span>
                                            <input class="form-control" type="text" name="date-range-picker" id="htsj"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="zldz">租赁地址：</label>
                                    <div class="col-sm-2">
                                        <select class="select2 width-100" id="zldz">
                                            <option value="">--请选择--</option>
                                            <option value="lya">楼宇A</option>
                                            <option value="lyb">楼宇B</option>
                                            <option value="lyc">楼宇C</option>
                                            <option value="lyd">楼宇D</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="text" id="road" placeholder="区路号" class="width-100"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" id="shi" placeholder="室" class="width-100"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="text" id="dy" placeholder="管理单元" class="width-100"/>
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="glf">管理方：</label>
                                    <div class="col-sm-4">
                                        <select class="select2 width-100" id="glf">
                                            <option value="WY">上海交投物业管理有限公司</option>
                                            <option value="XYTC">上海交投物业管理有限公司</option>
                                        </select>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="glfdz">地址：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="glfdz" class="width-100" readonly
                                               value="上海市徐汇区吴中东路555号8楼"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="glfname">法定代表人：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="glfname" class="width-100" readonly value="邵重业"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="glflxdh">联系电话：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="glflxdh" class="width-100" readonly value="021-34770192"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="glfzhmc">帐户名称：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="glfzhmc" class="width-100" readonly value="上海交投物业管理有限公司"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="glfkhyh">开户银行：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="glfkhyh" class="width-100" readonly value="上海浦东发展银行卢湾支行"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="glfzh">帐 号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="glfzh" class="width-100" readonly value="98990158000000493"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="glfsh">税 号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="glfsh" class="width-100" readonly
                                               value="913123299958782109N"/>
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="czf">承租方：</label>
                                    <div class="col-sm-4">
                                        <select class="select2 width-100" id="czf">
                                            <option value="WY">无匹配用户</option>
                                            <option value="WY">张三</option>
                                            <option value="XYTC">李四</option>
                                        </select>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="czfdz">地址：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="czfdz" class="width-100"/>
                                    </div>
                                </div>
                                <div class="space-4"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="czfname">法定代表人：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="czfname" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="czflxdh">联系电话：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="czflxdh" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="czfsh">税 号：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="czfsh" class="width-100" />
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="fwjzmj">房屋建筑面积(平方米)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="fwjzmj" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="czjzmj">承租建筑面积(平方米)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="czjzmj" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="fkfs">付款方式：</label>
                                    <div class="col-sm-4">
                                        <select class="select2 width-100" id="fkfs">
                                            <option value="WY">押一付一</option>
                                            <option value="XYTC">押一付三</option>
                                            <option value="XYTC">押二付二</option>
                                        </select>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="wyyj">物业费押金(元)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="wyyj" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="dfdj">电费单价(元/千瓦小时)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="dfdj" class="width-100"/>
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="cqdw">停车场地资产产权单位：</label>
                                    <div class="col-sm-10">
                                        <input type="text" id="cqdw" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="dxcwjg">地下车位价格(元/月/车)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="dxcwjg" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="dscwjg">地面车位价格(元/月/车)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="dscwjg" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="dxcwsl">地下车位数量(个)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="dxcwsl" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="dscwsl">地面车位数量(个)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="dscwsl" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="cwzlf">车位租赁费(元/月)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="cwzlf" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="zffs">支付方式(月预付)：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="zffs" class="width-100"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="zzf">停车证制作费：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="zzf" class="width-100"/>
                                    </div>
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="bbf">停车证补办费：</label>
                                    <div class="col-sm-4">
                                        <input type="text" id="bbf" class="width-100"/>
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
                                    <label class="col-sm-2 control-label blue" style="text-align: left" for="qttk">其他条款：</label>
                                    <div class="col-sm-10">
                                        <textarea class="width-100" id="qttk" placeholder="补充合同条款使用" rows="5"></textarea>
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

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, "${contextPath}/static/assets/js/jqGrid/jquery.jqGrid.js", "${contextPath}/static/assets/js/jqGrid/i18n/grid.locale-cn.js", "${contextPath}/static/assets/js/jquery-ui.custom.js",
        "${contextPath}/static/assets/js/jquery.ui.touch-punch.js", "${contextPath}/static/assets/js/markdown/markdown.js", "${contextPath}/static/assets/js/markdown/bootstrap-markdown.js",
        "${contextPath}/static/assets/js/jquery.hotkeys.js", "${contextPath}/static/assets/js/bootstrap-wysiwyg.js", "${contextPath}/static/assets/js/bootbox.js",
        "${contextPath}/static/assets/js/jquery.gritter.js", "${contextPath}/static/assets/js/date-time/bootstrap-datepicker.js", "${contextPath}/static/assets/js/date-time/bootstrap-timepicker.js",
        "${contextPath}/static/assets/js/date-time/moment.js", "${contextPath}/static/assets/js/date-time/daterangepicker.js", "${contextPath}/static/assets/js/date-time/bootstrap-datetimepicker.js",
        "${contextPath}/static/assets/js/select2.js","${contextPath}/static/assets/js/jquery.media.js",null]
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
                url: "${contextPath}/static/json/compact.json",
                datatype: "json",
                height: 450,
                colNames: ["ID", "合同编号", "管理方", "承租方", "合同类型", "承租方联系电话", "租赁时间", "合同状态",
                            "租赁地址", "其他条款", "范围建筑面积", "承租建筑面积", "付款方式", "物业费押金(元)", "电费单价(元/千瓦小时)", "停车场地资产产权单位",
                            "地下车位价格(元/月/车)", "地面车位价格(元/月/车)", "地下车位数量(个)", "地面车位数量(个)", "车位租赁费(元/月)", "支付方式(月预付)",
                            "停车证制作费", "停车证补办费", "管理方地址", "管理方法定代表人", "管理方联系电话", "管理方帐户名称", "管理方开户银行", "管理方账号",
                            "管理方税号", "承租方地址", "承租方法定代表人", "承租方税号"],
                colModel: [{
                    name: "id",
                    index: "id",
                    label: "ID",
                    width: 60,
                    sorttype: "long",
                    search: false
                }, {
                    name: "htbh",
                    index: "htbh",
                    label: "合同编号",
                    width: 150,
                    editable: true,
                    editoptions: {size: "20", maxlength: "50"},
                    editrules: {required: true},
                    searchoptions: {sopt: ["cn"]}
                }, {
                    name: "glf",
                    index: "glf",
                    label: "管理方",
                    width: 150,
                    editable: true,
                    editoptions: {size: "20", maxlength: "100"},
                    searchoptions: {sopt: ["cn"]}
                }, {
                    name: "czf",
                    index: "czf",
                    label: "承租方",
                    width: 110,
                    editable: true,
                    editoptions: {size: "20", maxlength: "15"},
                    search: false,
                }, {
                    name: "htlx",
                    index: "htlx",
                    label: "合同类型",
                    width: 110,
                    editable: true,
                    editoptions: {size: "20", maxlength: "15"},
                    search: false,
                }, {
                    name: "czflxdh",
                    index: "czflxdh",
                    label: "承租方联系电话",
                    width: 150,
                    sorttype: "date",
                    searchoptions: {sopt: ["cn"]}
                }, {
                    name: "htsj",
                    index: "htsj",
                    label: "租赁时间",
                    width: 200,
                    editable: true,
                    search: false,
                    edittype: "textarea",
                    editoptions: {rows: "2", cols: "18", maxlength: "200"}
                }, {
                    name: "survey",
                    index: "survey",
                    width: 100,
                    label: "合同状态",
                    search: false
                },
                    {name: "zldz", index: "zldz",hidden: true, width: 100,label: "租赁地址"},
                    {name: "qttk", index: "qttk", hidden: true, label: "其他条款"},
                    {name: "fwjzmj", index: "fwjzmj", hidden: true, label: "范围建筑面积"},
                    {name: "czjzmj", index: "czjzmj", hidden: true, label: "承租建筑面积"},
                    {name: "fkfs", index: "fkfs", hidden: true, label: "付款方式"},
                    {name: "wyyj", index: "wyyj", hidden: true, label: "物业费押金(元)"},
                    {name: "dfdj", index: "dfdj", hidden: true, label: "电费单价(元/千瓦小时)"},
                    {name: "cqdw", index: "cqdw", hidden: true, label: "停车场地资产产权单位"},
                    {name: "dxcwjg", index: "dxcwjg", hidden: true, label: "地下车位价格(元/月/车)"},
                    {name: "dscwjg", index: "dscwjg", hidden: true, label: "地面车位价格(元/月/车)"},
                    {name: "dxcwsl", index: "dxcwsl", hidden: true, label: "地下车位数量(个)"},
                    {name: "dscwsl", index: "dscwsl", hidden: true, label: "地面车位数量(个)"},
                    {name: "cwzlf", index: "cwzlf", hidden: true, label: "车位租赁费(元/月)"},
                    {name: "zffs", index: "zffs", hidden: true, label: "支付方式(月预付)"},
                    {name: "zzf", index: "zzf", hidden: true, label: "停车证制作费"},
                    {name: "bbf", index: "bbf", hidden: true, label: "停车证补办费"},
                    {name: "glfdz", index: "glfdz", hidden: true, label: "管理方地址"},
                    {name: "glfname", index: "glfname", hidden: true, label: "管理方法定代表人"},
                    {name: "glflxdh", index: "glflxdh", hidden: true, label: "管理方联系电话"},
                    {name: "glfzhmc", index: "glfzhmc", hidden: true, label: "管理方帐户名称"},
                    {name: "glfkhyh", index: "glfkhyh", hidden: true, label: "管理方开户银行"},
                    {name: "glfzh", index: "glfzh", hidden: true, label: "管理方账号"},
                    {name: "glfsh", index: "glfsh", hidden: true, label: "管理方税号"},
                    {name: "czfdz", index: "czfdz", hidden: true, label: "承租方地址"},
                    {name: "czfname", index: "czfname", hidden: true, label: "承租方法定代表人"},
                    {name: "czfsh", index: "czfsh", hidden: true, label: "承租方税号"}
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
                gridComplete:function(){
                    var ids = $(grid_selector).getDataIDs();
                    for(var i=0;i<ids.length;i++){
                        var rowData = $(grid_selector).getRowData(ids[i]);
                        if(rowData.htsj=='2016-11-06 2018-11-06'){//如果天数等于0，则背景色置灰显示
                            $('#'+ids[i]).find("td").css("background-color","#FFB6C1");
                        }
                    }
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

            $("#editInformationButton").bind("click", function () {
                var selectedId = $(grid_selector).jqGrid("getGridParam", "selrow");
                if (null == selectedId) {
                    $.gritter.add({
                        title: "系统信息",
                        text: "请选择记录",
                        class_name: "gritter-info gritter-center"
                    });
                } else {
                    $("#modal-table").modal("toggle");
                    /**
                     * "id": 1,
                     "htbh": "WY201811090001",
                     "htlx": "物业管理服务合同",
                     "htsj": "2018-11-06 2019-11-06",
                     "zldz": "上海市徐汇区吴中东路555号8楼",
                     "qttk":"9.1未尽事宜，双方经协商一致可以补充协议形式予以约定；<br>9.2《租户守则》为本合同之附件，为本合同有效组成部分;9.3本合同一式四份，双方各执二份；本合同自签署之日起生效；9.4合同期满，本合同自然终止。",
                     "fwjzmj":"300",
                     "czjzmj":"300",
                     "fkfs":"押一付一",
                     "wyyj":"20000",
                     "dfdj":"1",
                     "cqdw":"上海交投物业管理有限公司",
                     "dxcwjg":"1000",
                     "dscwjg":"800",
                     "dxcwsl":"3",
                     "dscwsl":"1",
                     "cwzlf":"3800",
                     "zffs":"3",
                     "zzf":"200",
                     "bbf":"50",
                     "glf":"上海交投物业管理有限公司",
                     "glfdz":"上海市徐汇区吴中东路555号8楼",
                     "glfname":"王思聪",
                     "glflxdh":"021-34770192",
                     "glfzhmc":"上海交投物业管理有限公司",
                     "glfkhyh":"上海浦东发展银行卢湾支行",
                     "glfzh":"98990158000000493",
                     "glfsh":"913123299958782109N",
                     "czf":"张三",
                     "czfdz":"上海市徐汇区吴中东路555号8楼",
                     "czfname":"张三",
                     "czflxdh": "021-34770192",
                     "czfsh":"913123299958782109N",
                     "survey": "已审核"
                     */
                    $("#id").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).id);
                    $("#htbh").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).htbh);
                    $("#htlx").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).htlx);
                    $("#htsj").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).htsj);
                    $("#qttk").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).qttk);
                    $("#fwjzmj").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).fwjzmj);
                    $("#czjzmj").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).czjzmj);
                    $("#fkfs").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).fkfs);
                    $("#wyyj").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).wyyj);
                    $("#dfdj").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).dfdj);
                    $("#cqdw").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).cqdw);
                    $("#dxcwjg").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).dxcwjg);
                    $("#dscwjg").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).dscwjg);
                    $("#dxcwsl").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).dxcwsl);
                    $("#dscwsl").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).dscwsl);
                    $("#cwzlf").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).cwzlf);
                    $("#zffs").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).zffs);
                    $("#zzf").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).zzf);
                    $("#bbf").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).bbf);
                    $("#glf").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).glf);
                    $("#glfdz").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).glfdz);
                    $("#glfname").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).glfname);
                    $("#glflxdh").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).glflxdh);
                    $("#glfzhmc").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).glfzhmc);
                    $("#glfkhyh").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).glfkhyh);
                    $("#glfzh").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).glfzh);
                    $("#glfsh").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).glfsh);
                    $("#czf").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).czf);
                    $("#czfdz").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).czfdz);
                    $("#czfname").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).czfname);
                    $("#czflxdh").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).czflxdh);
                    $("#czfsh").html(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).czfsh);
                    $("#survey").val(jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).survey);

                }
            });

            $("#examineInformationButton").bind("click", function () {
                var selectedId = $(grid_selector).jqGrid("getGridParam", "selrow");
                if (null == selectedId) {
                    $.gritter.add({
                        title: "系统信息",
                        text: "请选择记录",
                        class_name: "gritter-info gritter-center"
                    });
                } else {
                    if (jQuery(grid_selector).jqGrid("getRowData", jQuery(grid_selector).jqGrid("getGridParam", "selrow")).survey != '待提交') {
                        toastMessage('系统信息', '该合同已在审核中！');
                    } else {
                        /* bootbox.confirm("确定提交审核？", function(result) {
                             if(result) {
                                 bootbox.alert("提交成功！");
                             }
                         });*/

                        bootbox.confirm({
                            message: "确定提交审核？",
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
                                    bootbox.alert("提交成功！");
                                }
                            }
                        });

                    }
                }
            });

            $("#bulidingForm").on("submit", function (e) {
                if ($.trim($("#number").val()) == "") {
                    $("#modal-tip").html("请填写编号");
                    return;
                } else {
                    $.ajax({
                        dataType: "json",
                        url: "${contextPath}/sys/buliding/operateInformation?oper=save",
                        type: "post",
                        data: {
                            id: $("#id").val(),
                            number: $("#number").val(),
                            name: $("#name").val(),
                            phone: $("#phone").val(),
                            address: $("#address").val(),
                            survey: $("#survey").html()
                        },
                        complete: function (xmlRequest) {
                            $("#modal-table").modal("toggle");
                            jQuery(grid_selector).trigger("reloadGrid");
                        }
                    });
                }
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

            $('#htlx').change(function () {
                if ($('#htlx').val() == 'wy') {
                    $(".wyxx").css("display", "");
                    $(".cwxx").css("display", "none");
                } else if ($('#htlx').val() == 'cw') {
                    $(".wyxx").css("display", "none");
                    $(".cwxx").css("display", "");
                }
            });

            function toastMessage(title, text) {
                $.gritter.add({
                    title: title,
                    text: text,
                    class_name: 'gritter-info gritter-center'
                });
                return;
            }


            $("#downloadInformationButton").bind("click", function () {
                var selectedId = $(grid_selector).jqGrid("getGridParam", "selrow");
                if (null == selectedId) {
                    $.gritter.add({
                        title: "系统信息",
                        text: "请选择记录",
                        class_name: "gritter-info gritter-center"
                    });
                } else {
                    $("#modal-table1").modal("toggle");
                    $('#handout_wrap_inner').media({
                        width: '100%',
                        height: '900px',
                        autoplay: true,
                        src:'${contextPath}/static/word/wyglht.pdf',
                    });
                }
            });

        });
    });


</script>
