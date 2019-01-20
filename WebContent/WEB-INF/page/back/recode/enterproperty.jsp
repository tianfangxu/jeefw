<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery-ui.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery.gritter.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/select2.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/chosen.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/datepicker.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/daterangepicker.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/colorpicker.css" />
<style>
	.ui-jqgrid-sortable {
		padding-left: 4px;
		font-size: 13px;
		color: #777;
		font-weight: bold;
		text-align: center;
	}
	.ui-jqgrid .ui-jqgrid-labels th {
		border-right: 1px solid #E1E1E1 !important;
		text-align: center !important;
	}
	.selectyear{
		float: none;
	    box-sizing: border-box;
	    background-color: #fff;
	    border: 1px solid #aaa;
	    border-radius: 4px;
	    padding-top: 1px;
	    height: 28px;
	}
</style>

<div class="row">
	<div class="col-xs-12">
		<div class="well well-sm" >
			<select class="select2 col-xs-2 " id="lyxx">
			</select>
			
			<select class="col-xs-2 selectyear" id="year" data-placeholder="选择年份">
				<option>2018</option>
				<option>2019</option>
				<option>2020</option>
				<option>2021</option>
				<option>2022</option>
				<option>2023</option>
				<option>2024</option>
				<option>2025</option>
			</select>
			
			<div role="button" class="btn btn-info btn-sm" onclick="getdatamsh()">
				查询
			</div>
			<div role="button" class="btn btn-info btn-sm" onclick="exportexcel()">
				导出
			</div>
			
			<div style="display: none">
				<shiro:hasPermission name="${ROLE_KEY}:enterproperty:add">
					<a id="addInformationButton" role="button" class="btn btn-info btn-sm" data-toggle="modal">
						添加记录
					</a>
				</shiro:hasPermission>
				<shiro:lacksPermission name="${ROLE_KEY}:enterproperty:add">
					<a id="addInformationButton1" disabled="disabled" role="button" class="btn btn-info btn-sm" data-toggle="modal">
						添加记录
					</a>
				</shiro:lacksPermission>
				<shiro:hasPermission name="${ROLE_KEY}:enterproperty:edit">
					<a id="editInformationButton" role="button" class="btn btn-purple btn-sm" data-toggle="modal">
						编辑记录
					</a>
				</shiro:hasPermission>
				<shiro:lacksPermission name="${ROLE_KEY}:enterproperty:edit">
					<a id="editInformationButton1" role="button" disabled="disabled" class="btn btn-purple btn-sm" data-toggle="modal">
						编辑记录
					</a>
				</shiro:lacksPermission>
			</div>
		</div>
		
		<table id="enterproperty-table"></table>
			
		<div id="grid-pager"></div>
		
		<table id="exportexceltable" style="display: none"></table>

		<script type="text/javascript">
			var $path_base = "${contextPath}/static";//in Ace demo this will be used for editurl parameter
		</script>

		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
</div><!-- /.row -->
<!-- page specific plugin scripts -->
<script type="text/javascript">
var scripts = [ null, "${contextPath}/static/assets/js/jqGrid/jquery.jqGrid.js", "${contextPath}/static/assets/js/jqGrid/i18n/grid.locale-cn.js", "${contextPath}/static/assets/js/jquery-ui.custom.js",
        		"${contextPath}/static/assets/js/jquery.ui.touch-punch.js", "${contextPath}/static/assets/js/markdown/markdown.js", "${contextPath}/static/assets/js/markdown/bootstrap-markdown.js",
        		,"${contextPath}/static/assets/js/echarts.js","${contextPath}/static/assets/js/select2.js","${contextPath}/static/assets/js/jquery.hotkeys.js", 
        		"${contextPath}/static/assets/js/bootstrap-wysiwyg.js", "${contextPath}/static/assets/js/bootbox.js", 
        		"${contextPath}/static/assets/js/ExportExcel.js","${contextPath}/static/assets/js/jquery.gritter.js", null ]

		$(".page-content-area").ace_ajax("loadScripts", scripts, function() {
        	// inline scripts related to this page
        	jQuery(function($) {
        		
        		var grid_selector = "#enterproperty-table";
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
        		
        		getbuildMsg();
        		
        		jQuery(grid_selector).jqGrid({
        			subGrid : false,
        			url : "${contextPath}/recode/export/getExportInfo",
        			datatype : "json",
        			colNames : ["编号","室号", "合同面积m²", "公司名称","合同到期日", "付费方式","应收","实收","应收","实收","应收","实收","应收","实收","应收","实收"
                        ,"应收","实收","应收","实收","应收","实收","应收","实收","应收","实收","应收","实收","应收","实收","应收","实收","备注"],
        			colModel : [ 
						{name : 'id', width : 150,align:'center', hidden:true,label : "编号",},
						{name : "roomnum",width : 150,align:'center',search:false,},
						{name : "area",width : 150,align:'center', search:false,},
						{name : "name", width : 150,align:'center', search:false,},
						{name : "deadline",width : 150,align:'center',search:false,},
						{name : "type", width : 150,align:'center',search:false,},
						{name : "receipt1",width : 150,align:'center',search:false},
						{name : "receivable1",width : 150,align:'center',search:false},
                        {name : "receipt2",width : 150,align:'center',search:false},
                        {name : "receivable2",width : 150,align:'center',search:false},
                        {name : "receipt3",width : 150,align:'center',search:false},
                        {name : "receivable3",width : 150,align:'center',search:false},
                        {name : "receipt4",width : 150,align:'center',search:false},
                        {name : "receivable4",width : 150,align:'center',search:false},
                        {name : "receipt5",width : 150,align:'center',search:false},
                        {name : "receivable5",width : 150,align:'center',search:false},
                        {name : "receipt6",width : 150,align:'center',search:false},
                        {name : "receivable6",width : 150,align:'center',search:false},
                        {name : "receipt7",width : 150,align:'center',search:false},
                        {name : "receivable7",width : 150,align:'center',search:false},
                        {name : "receipt8",width : 150,align:'center',search:false},
                        {name : "receivable8",width : 150,align:'center',search:false},
                        {name : "receipt9",width : 150,align:'center',search:false},
                        {name : "receivable9",width : 150,align:'center',search:false},
                        {name : "receipt10",width : 150,align:'center',search:false},
                        {name : "receivable10",width : 150,align:'center',search:false},
                        {name : "receipt11",width : 150,align:'center',search:false},
                        {name : "receivable11",width : 150,align:'center',search:false},
                        {name : "receipt12",width : 150,align:'center',search:false},
                        {name : "receivable12",width : 150,align:'center',search:false},
                        {name : "receipt0",width : 150,align:'center',search:false},
                        {name : "receivable0",width : 150,align:'center',search:false},
                        { name : "remarks", width : 150,align:'center',search:false, }
                    ],
                    autowidth:true,
                    shrinkToFit:false,
                    autoScroll: false,
        			sortname : "id",
        			sortorder : "asc",
        			viewrecords : true,
        			rowNum : 10,
        			rowList : [ 10, 20, 30 ],
        			pager : pager_selector,
        			altRows : true,
        			//toppager : true,
        			multiselect : false,
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
        			editurl : null
        		});

                $(grid_selector).jqGrid('setGroupHeaders', {
                    useColSpanStyle: true,
                    groupHeaders:[
                        {startColumnName:'receipt1', numberOfColumns:2, titleText: '1月'},
                        {startColumnName:'receipt2', numberOfColumns:2, titleText: '2月'},
                        {startColumnName:'receipt3', numberOfColumns:2, titleText: '3月'},
                        {startColumnName:'receipt4', numberOfColumns:2, titleText: '4月'},
                        {startColumnName:'receipt5', numberOfColumns:2, titleText: '5月'},
                        {startColumnName:'receipt6', numberOfColumns:2, titleText: '6月'},
                        {startColumnName:'receipt7', numberOfColumns:2, titleText: '7月'},
                        {startColumnName:'receipt8', numberOfColumns:2, titleText: '8月'},
                        {startColumnName:'receipt9', numberOfColumns:2, titleText: '9月'},
                        {startColumnName:'receipt10', numberOfColumns:2, titleText: '10月'},
                        {startColumnName:'receipt11', numberOfColumns:2, titleText: '11月'},
                        {startColumnName:'receipt12', numberOfColumns:2, titleText: '12月'},
                        {startColumnName:'receipt0', numberOfColumns:2, titleText: '总计'},

                    ]
                });

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
        				$("#name").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).name);
        				$("#address").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).address);
        				$("#contact").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).contact);
        				$("#manager").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).managerid);
        				$("#comment").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).comment);
        				$("#propertyfee").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).propertyfee);
        			}
        		});
        		
        		$("#informationForm").on("submit", function(e) {
        			var data = new Object();
        			data.name=$("#name").val();
        			data.address=$("#address").val();
        			data.contact=$("#contact").val();
        			data.managerid=$("#manager").val();
                    data.manager=$("#manager option:checked").html();
        			data.comment=$("#comment").val();
        			data.propertyfee=$("#propertyfee").val();
        			if($("#id").val() == '' || $("#id").val() == null || $("#id").val() == undefined){
        			}else{
        				data.id = $("#id").val();
        			}
   			    	$.ajax({
           				dataType : "json",
           				url : "${contextPath}/recode/enterproperty/saveOrupdatenterproperty",
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
        			del : <shiro:hasPermission name="${ROLE_KEY}:enterproperty:delete">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:enterproperty:delete">false</shiro:lacksPermission>,
        			delicon : "ace-icon fa fa-trash-o red",
        			search : <shiro:hasPermission name="${ROLE_KEY}:enterproperty:search">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:enterproperty:search">false</shiro:lacksPermission>,
        			searchicon : "ace-icon fa fa-search orange",
        			refresh : true,
        			refreshicon : "ace-icon fa fa-refresh blue",
        			view : <shiro:hasPermission name="${ROLE_KEY}:enterproperty:view">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:enterproperty:view">false</shiro:lacksPermission>,
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
        		if(<shiro:hasPermission name="${ROLE_KEY}:enterproperty:export">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:enterproperty:export">false</shiro:lacksPermission>){
    				jQuery(grid_selector).jqGrid("navButtonAdd", pager_selector,{
   					   caption : "",
   				       title : "导出Excel",
   				       buttonicon : "ace-icon fa fa-file-excel-o green", 
   				       onClickButton : exportexcel(),
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
        		}

        		function updateActionIcons(table) {
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
		
		function getdatamsh(){
	        jQuery("#enterproperty-table").jqGrid('setGridParam',{
	            datatype:'json',
	            postData:{'buildcode':$("#lyxx").val(),'year':$("#year").val()}, //发送数据
	            page:1
	        }).trigger("reloadGrid"); //重新载入
	    }
		
		function getbuildMsg(){
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
		function checknull(val){
			if(val == null || val == undefined || val == "undefined" || val == 'null' ){
				return '';
			}
			return val;
		}
		
		function exportexcel5555() {
    	   var keys = [], ii = 0, rows = "";
    	   var ids = $("#enterproperty-table").getDataIDs(); // Get All IDs
    	   var row = $("#enterproperty-table").getRowData(ids[0]); // Get First row to get the labels
    	   //var label = $(grid_selector).jqGrid("getGridParam","colNames");
	    	   for (var k in row) {
    	   	   keys[ii++] = k; // capture col names
    	   	   rows = rows + k + "\t"; // output each Column as tab delimited
    	   }
	    	   rows = "编号\t室号\t合同面积m²\t公司名称\t合同到期日\t付费方式\t应收（1月）\t实收（1月）\t应收（2月）\t实收（2月）\t应收（3月）\t实收（3月）\t应收（4月）\t实收（4月）\t应收（5月）\t实收（5月）\t应收（6月）\t实收（6月）\t应收（7月）\t实收（7月）\t应收（8月）\t实收（8月）\t应收（9月）\t实收（9月）\t应收（10月）\t实收（10月）\t应收（11月）\t实收（11月）\t应收（12月）\t实收（12月）\t应收（全年）\t实收（全年）\t备注";   
    	   rows = rows + "\n"; // Output header with end of line
    	   for (i = 0; i < ids.length; i++) {
    	   	   row = $("#enterproperty-table").getRowData(ids[i]); // get each row
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
		
		function exportexcel666666(){
			$("#exportexceltable").html('<tr><td colspan="34">'+$("#year").val()+$("#lyxx option:selected").html()+'物业费收入明细</td></tr>');
			$("#exportexceltable").append('<tr>'+$('.jqg-second-row-header').html()+"</tr>");
			$("#exportexceltable").append('<tr>'+$('.jqg-third-row-header').html()+"</tr>");
			$("#exportexceltable").append($('#enterproperty-table').html());
			var tb = new TableToExcel('exportexceltable');  
	        tb.setFontStyle("Courier New");  
	        tb.setFontSize(10);  
	        tb.setTableBorder(2);  
	        tb.setColumnWidth(7);  
	        tb.isLineWrap(true);  
	        tb.getExcelFile();
			return ;
		}
		
		function exportexcel(){
			$("#exportexceltable").html('<tr><td colspan="34" style="text-align: center;font-size:28px">'+$("#year").val()+$("#lyxx option:selected").html()+'物业费收入明细</td></tr>');
			$("#exportexceltable").append('<tr>'+$('.jqg-second-row-header').html()+"</tr>");
			$("#exportexceltable").append('<tr>'+$('.jqg-third-row-header').html()+"</tr>");
			$('#enterproperty-table').find(".jqgfirstrow").remove();
			$("#exportexceltable").append($('#enterproperty-table').find("tbody").html());
			methodToExport('exportexceltable');
		}
</script>
