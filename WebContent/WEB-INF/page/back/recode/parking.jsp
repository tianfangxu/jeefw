<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery-ui.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery.gritter.css" />
<link rel="stylesheet"
	href="${contextPath}/static/assets/css/select2.css" />

<div style="margin-bottom: 10px">
	<select class="col-xs-2" id="buildSelect" style="float: none;height: 35px;" onchange="getdatareflush()">
		
	</select>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="well well-sm">
			<shiro:hasPermission name="${ROLE_KEY}:parking:add">
				<a id="addInformationButton" role="button" class="btn btn-info btn-sm" data-toggle="modal">
					添加记录
				</a>
			</shiro:hasPermission>
			<shiro:lacksPermission name="${ROLE_KEY}:parking:add">
				<a id="addInformationButton1" disabled="disabled" role="button" class="btn btn-info btn-sm" data-toggle="modal">
					添加记录
				</a>
	        </shiro:lacksPermission>
	        <shiro:hasPermission name="${ROLE_KEY}:parking:edit">
				<a id="editInformationButton" role="button" class="btn btn-purple btn-sm" data-toggle="modal">
					编辑记录
				</a>
			</shiro:hasPermission>
			<shiro:lacksPermission name="${ROLE_KEY}:parking:edit">
				<a id="editInformationButton1" role="button" disabled="disabled" class="btn btn-purple btn-sm" data-toggle="modal">
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
									<h4 class="widget-title">停车位信息新增</h4>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div>
											<input type="hidden" id="id">
											<label for="form-field-8">楼宇</label>
											<select class="form-control" id="buil">
												
											</select>
											<label for="form-field-8">名称</label>
											<input id="name" class="form-control" type="text">
										</div>
										<hr />
										<div>
											<label for="form-field-8">类型</label>
											<!-- <input id="type" class="form-control" type="text">  -->
											<div class="radio" style="border: 1px solid #d5d5d5;padding: 5px 4px 6px;">
												<label>
													<input name="type" type="radio" class="ace" value="0" />
													<span class="lbl">固定</span>
												</label>
												<label>
													<input name="type" type="radio" class="ace" value="1"  />
													<span class="lbl">临时</span>
												</label>
											</div>
											<label for="form-field-8" style="display: none">面积</label>
											<input id="area" class="form-control" type="hidden" value="0">
										</div>
										<hr />
										<div>
											<label for="form-field-8">价格</label>
											<input id="price" class="form-control" type="text"> 
											<label for="form-field-8">租赁状态</label>
											<!-- <input id="used" class="form-control" type="text"> -->
											<div class="radio" style="border: 1px solid #d5d5d5;padding: 5px 4px 6px;">
												<label>
													<input name=used type="radio" class="ace" value="0" />
													<span class="lbl">未出租</span>
												</label>
												<label>
													<input name="used" type="radio" class="ace" value="1"  />
													<span class="lbl">已出租</span>
												</label>
											</div>
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

<!-- page specific plugin scripts -->
<script type="text/javascript">
		var scripts = [ null, "${contextPath}/static/assets/js/jqGrid/jquery.jqGrid.js", "${contextPath}/static/assets/js/jqGrid/i18n/grid.locale-cn.js", "${contextPath}/static/assets/js/jquery-ui.custom.js",
		        		"${contextPath}/static/assets/js/jquery.ui.touch-punch.js", "${contextPath}/static/assets/js/markdown/markdown.js", "${contextPath}/static/assets/js/markdown/bootstrap-markdown.js",
		        		"${contextPath}/static/assets/js/select2.js","${contextPath}/static/assets/js/jquery.hotkeys.js", "${contextPath}/static/assets/js/bootstrap-wysiwyg.js", "${contextPath}/static/assets/js/bootbox.js", "${contextPath}/static/assets/js/jquery.gritter.js", null ]
      
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
        		
        		//楼宇信息的下拉栏
        		getbuildSelect();
        		
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
        			url : "${contextPath}/recode/parking/getParkingByCondition",
        			datatype : "json",
        			height : 450,
        			width : 770,
        			colNames : ["编号","楼宇编号","楼宇","名称", "类型", "面积","价格", "租赁状态"],
        			colModel : [ 
						{
							 name:'id',
							 width : 150,
							 hidden:true,
						},
						{
							 name:'build',
							 width : 150,
							 hidden:true,
						},
						{
							name : "buildname",
							width : 150,
							search:false,
						},{
							name : "name",
							width : 200,
							searchoptions : {sopt : ["cn","eq"]},
						},{
							name : "type",
							width : 200,
							searchoptions : {sopt : ["cn","eq"]},
							formatter:function(val){
								if(val == '1'){
									return '临时';
								}else{
									return '固定';
								}
							}
						},{
							name : "area",
							width : 100,
							search:false,
							hidden:true,
						}, {
							name : "price",
							width : 150,
							search:false,
						}, {
							name : "used",
							width : 100,
							searchoptions : {sopt : ["cn","eq"]},
							formatter:function(val){
								if(val == '1'){
									return '已出租';
								}else{
									return '未出租';
								}
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
        			editurl :  "${contextPath}/recode/parking/delParking",
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
        				$("#id").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).id);
        				$("#name").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).name);
        				$("#buil").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).build);
        				$("#area").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).area);
        				//$("#type").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).type);
        				$("#price").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).price);
        				//$("#used").val(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).used);
        				var used_ = '0';
        				if(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).used == '已出租'){
        					used_ =  '1';
						}else{
							used_ = '0';
						}
        				var type_ = '0';
        				if(jQuery(grid_selector).jqGrid("getRowData",jQuery(grid_selector).jqGrid("getGridParam", "selrow")).type == '临时'){
        					type_ =  '1';
						}else{
							type_ = '0';
						}
        				$("input[name='used'][value='"+used_+"']").attr('checked',true);
        				$("input[name='type'][value='"+type_+"']").attr('checked',true);
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
        			del : <shiro:hasPermission name="${ROLE_KEY}:parking:delete">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:parking:delete">false</shiro:lacksPermission>,
        			delicon : "ace-icon fa fa-trash-o red",
        			search : <shiro:hasPermission name="${ROLE_KEY}:parking:search">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:parking:search">false</shiro:lacksPermission>,
        			searchicon : "ace-icon fa fa-search orange",
        			refresh : true,
        			refreshicon : "ace-icon fa fa-refresh blue",
        			view : <shiro:hasPermission name="${ROLE_KEY}:parking:view">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:parking:view">false</shiro:lacksPermission>,
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
        		if(<shiro:hasPermission name="${ROLE_KEY}:parking:export">true</shiro:hasPermission><shiro:lacksPermission name="${ROLE_KEY}:parking:export">false</shiro:lacksPermission>){
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
		
		function getbuildSelect(){
			$.ajax({
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
   	   					//$('#buildSelect').html(html);
   	   					$('#buil').html(html);
   	   					
   					}
   					
   				}
   			});
			
			$('#buildSelect').select2({
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
		function getdatareflush(){
			jQuery("#lyxx-table").jqGrid('setGridParam',{
                datatype:'json',
                postData:{'build':$("#buildSelect").val()}, //发送数据
                page:1
            }).trigger("reloadGrid"); //重新载入
		}
		function checknull(val){
			if(val == null || val == undefined || val == "undefined" || val == 'null' ){
				return '';
			}
			return val;
		}
</script>
