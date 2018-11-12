<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>Java企业通用开发平台框架</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <meta http-equiv="refresh" content="1800" />
        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap.css" />
        <link rel="stylesheet" href="${contextPath}/static/assets/css/font-awesome.css" />
        <!-- text fonts -->
        <link rel="stylesheet" href="${contextPath}/static/assets/css/ace-fonts.css" />
        <link rel="stylesheet" href="${contextPath}/static/assets/css/activities-serverload.css" />
        <!-- ace styles -->
        <link rel="stylesheet" href="${contextPath}/static/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
        <!--[if lte IE 9]>
			<link rel="stylesheet" href="${contextPath}/static/assets/css/ace-part2.css" class="ace-main-stylesheet" />
		<![endif]-->
        <!--[if lte IE 9]>
		  <link rel="stylesheet" href="${contextPath}/static/assets/css/ace-ie.css" />
		<![endif]-->
        <!-- ace settings handler -->
        <script src="${contextPath}/static/assets/js/ace-extra.js"></script>
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lte IE 8]>
		<script src="${contextPath}/static/assets/js/html5shiv.js"></script>
		<script src="${contextPath}/static/assets/js/respond.js"></script>
		<![endif]-->
    </head>
    <body class="no-skin">
        <!-- #section:basics/navbar.layout -->
        
            <script type="text/javascript">
				try {
					ace.settings.check('navbar', 'fixed')
				} catch (e) {
				}
			</script>
            
            <!-- /.navbar-container -->
        
        <!-- /section:basics/navbar.layout -->
        <div class="main-container" id="main-container">
            <script type="text/javascript">
				try {
					ace.settings.check('main-container', 'fixed')
				} catch (e) {
				}
			</script>
            <!-- #section:basics/sidebar -->
            <div id="sidebar" class="sidebar responsive">
                <script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>
				<!-- new -->
                
               <!-- new -->
                <!-- /.sidebar-shortcuts -->
                <ul class="nav nav-list">
                	<c:forEach var="authority" items="${authorityList}">
	                	<c:if test="${authority.subAuthorityList.size() > 0}">
		                    <li class="">
		                        <a href="<c:out value='${authority.dataUrl}'/>" class="dropdown-toggle">
		                            <i class="<c:out value='${authority.menuClass}'/>"></i>
		                            <span class="menu-text"><c:out value="${authority.menuName}"/></span>
		                            <b class="arrow fa fa-angle-down"></b>
		                        </a>
		                        <b class="arrow"></b>
		                        <ul class="submenu">
		                        	<c:forEach var="subAuthorityList" items="${authority.subAuthorityList}">
			                        <li class="">
		                                <a <c:if test="${subAuthorityList.subAuthorityList.size() > 0}">class="dropdown-toggle"</c:if> data-url="<c:out value='${subAuthorityList.dataUrl}'/>" href="home#<c:out value='${subAuthorityList.dataUrl}'/>">
		                                    <i class="<c:out value='${subAuthorityList.menuClass}'/>"></i><c:out value="${subAuthorityList.menuName}"/>
		                                	<c:if test="${subAuthorityList.subAuthorityList.size() > 0}">
		                                		<b class="arrow fa fa-angle-down"></b>
		                                	</c:if>
		                                </a>
		                                <b class="arrow"></b>
		                                <ul class="submenu">
				                        	<c:forEach var="subSubAuthorityList" items="${subAuthorityList.subAuthorityList}">
				                        	<c:if test="${authority.subAuthorityList.size() > 0}">
						                        <li class="">
					                                <a data-url="<c:out value='${subSubAuthorityList.dataUrl}'/>" href="home#<c:out value='${subSubAuthorityList.dataUrl}'/>">
					                                    <i class="<c:out value='${subSubAuthorityList.menuClass}'/>"></i><c:out value="${subSubAuthorityList.menuName}"/>
					                                </a>
					                                <b class="arrow"></b>
					                            </li>
				                            </c:if>
				                            </c:forEach>
				                        </ul>
		                            </li>
		                            </c:forEach>
		         				</ul>
		                    </li>
		                </c:if>
                    </c:forEach>
                </ul>
                <!-- /.nav-list -->
                <!-- #section:basics/sidebar.layout.minimize -->
                <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                    <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
                </div>
                <!-- /section:basics/sidebar.layout.minimize -->
                <script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
            </div>
            <!-- /section:basics/sidebar -->
            <div class="main-content">
                <!-- #section:basics/content.breadcrumbs -->
                <div class="breadcrumbs" id="breadcrumbs">
                    <script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>
                    <ul class="breadcrumb">
                        <li>
                            <i class="ace-icon fa fa-home home-icon"></i>
                            <a href="${contextPath}/sys/sysuser/home">
								 首页
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- /section:basics/content.breadcrumbs -->
                <div class="page-content">                  
                    <div class="page-content-area" data-ajax-content="true">
                    </div>
                </div>
            </div>
            <!-- /.main-content -->
            <div class="footer">
                <div class="footer-inner">
                    <!-- #section:basics/footer -->
                    <div class="footer-content">
                        <span class="bigger-120">
                            <span class="blue bolder">
                                                                                             憬承信息
                            </span>
                            &copy; 
                            2015-2018
                        </span>
                    </div>
                    <!-- /section:basics/footer -->
                </div>
            </div>
            <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
            </a>
        </div>
        <!-- /.main-container -->
        <!-- basic scripts -->
        <!--[if !IE]> -->
			<script type="text/javascript">
				window.jQuery || document.write("<script src='${contextPath}/static/assets/js/jquery.js'>" + "<"+"/script>");
			</script>
		<!-- <![endif]-->
        <!--[if IE]>
			<script type="text/javascript">
			 	window.jQuery || document.write("<script src='${contextPath}/static/assets/js/jquery1x.js'>"+"<"+"/script>");
			</script>
		<![endif]-->
        <script type="text/javascript">
			if ('ontouchstart' in document.documentElement)
				document.write("<script src='${contextPath}/static/assets/js/jquery.mobile.custom.js'>" + "<"+"/script>");
		</script>
        <script src="${contextPath}/static/assets/js/bootstrap.js"></script>
        <!-- ace scripts -->
        <script src="${contextPath}/static/assets/js/ace/elements.scroller.js"></script>
        <script src="${contextPath}/static/assets/js/ace/elements.colorpicker.js"></script>
        <script src="${contextPath}/static/assets/js/ace/elements.fileinput.js"></script>
        <script src="${contextPath}/static/assets/js/ace/elements.typeahead.js"></script>
        <script src="${contextPath}/static/assets/js/ace/elements.wysiwyg.js"></script>
        <script src="${contextPath}/static/assets/js/ace/elements.spinner.js"></script>
        <script src="${contextPath}/static/assets/js/ace/elements.treeview.js"></script>
        <script src="${contextPath}/static/assets/js/ace/elements.wizard.js"></script>
        <script src="${contextPath}/static/assets/js/ace/elements.aside.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.ajax-content.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.touch-drag.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.sidebar.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.sidebar-scroll-1.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.submenu-hover.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.widget-box.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.settings.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.settings-rtl.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.settings-skin.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.widget-on-reload.js"></script>
        <script src="${contextPath}/static/assets/js/ace/ace.searchbox-autocomplete.js"></script>
    </body>
</html>
