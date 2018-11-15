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
            <select class="select2 col-xs-2 " id="lyxx" >
                <option value="">--请选择楼宇--</option>
                <option value="wy">巴士大厦</option>
                <option value="cw">金宏大厦</option>
                <option value="cw">天源大厦</option>
                <option value="cw">天山</option>
                <option value="cw">芦恒路</option>
                <option value="cw">逸仙路</option>
            </select>
            <div class="col-xs-2">
                <div class="input-group">
                <span class="input-group-addon">
                    <i class="fa fa-calendar bigger-110"></i>
                </span>
                    <input type="text" name="date-range-picker" id="htsj" placeholder="选择统计时间范围"/>
                </div>
            </div>
            <a id="searchReportButton" role="button" disabled="disabled" class="btn btn-purple btn-sm" data-toggle="modal">
                查询
            </a>
            <a id="downloadReportButton" role="button" disabled="disabled" class="btn btn-purple btn-sm" data-toggle="modal" >
                导出报表
            </a>
        </div>

        <table id="grid-table"></table>

        <div id="grid-pager"></div>

        <script type="text/javascript">
            var $path_base = "${contextPath}/static";
        </script>

    </div>
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
                url: "${contextPath}/static/json/report.json",
                datatype: "json",
                height: 450,
                colNames: ["ID", "承租单位", "租赁期限", "月租金", "房间", "一月", "二月", "三月",
                            "四月", "五月", "六月", "七月", "八月", "九月", "十月",
                            "十一月", "十二月"],
                colModel: [{
                    name: "id",
                    index: "id",
                    label: "ID",
                    width: 60,
                    sorttype: "long",
                    search: false
                }, {
                    name: "czf",
                    index: "czf",
                    label: "承租单位",
                    width: 180,
                    editable: true,
                    editoptions: {size: "20", maxlength: "50"},
                    editrules: {required: true},
                    searchoptions: {sopt: ["cn"]}
                }, {
                    name: "htsj",
                    index: "htsj",
                    label: "租赁期限",
                    width: 120,
                    editable: true,
                    editoptions: {size: "20", maxlength: "100"},
                    searchoptions: {sopt: ["cn"]}
                }, {
                    name: "yzj",
                    index: "yzj",
                    label: "月租金",
                    width: 110,
                    editable: true,
                    editoptions: {size: "20", maxlength: "15"},
                    search: false,
                }, {
                    name: "dz",
                    index: "dz",
                    label: "房间",
                    width: 110,
                    editable: true,
                    editoptions: {size: "20", maxlength: "15"},
                    search: false,
                },
                 {name: "zj1", index: "zj1", width: 70, label: "一月", search: false},
                 {name: "zj2", index: "zj2", width: 70, label: "二月", search: false},
                 {name: "zj3", index: "zj3", width: 70, label: "三月", search: false},
                 {name: "zj4", index: "zj4", width: 70, label: "四月", search: false},
                 {name: "zj5", index: "zj5", width: 70, label: "五月", search: false},
                 {name: "zj6", index: "zj6", width: 70, label: "六月", search: false},
                 {name: "zj7", index: "zj7", width: 70, label: "七月", search: false},
                 {name: "zj8", index: "zj8", width: 70, label: "八月", search: false},
                 {name: "zj9", index: "zj9", width: 70, label: "九月", search: false},
                 {name: "zj10", index:"zj10", width: 70, label: "十月", search: false},
                 {name: "zj11", index:"zj11", width: 70, label: "十一月", search: false},
                 {name: "zj12", index:"zj12", width: 70, label: "十二月", search: false}

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
                editurl: "${contextPath}/sys/buliding/operateInformation"
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

        });
    });


</script>
