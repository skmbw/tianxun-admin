<%@page import="com.vteba.tianxun.admin.Employee"%>
<%@page import="com.vteba.common.constant.CommonConst"%>
<%@page import="com.vteba.tianxun.admin.user.model.Authorities"%>
<%@page import="com.vteba.tianxun.admin.user.model.ModuleMenu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/taglib.inc" %>

<%
	Employee user = (Employee)session.getAttribute(CommonConst.CONTEXT_USER);
	if (user == null) {
		response.sendRedirect("employee/login");
		return;
	}
	List<ModuleMenu> moduleMenus = user.getModuleMenuList();
	String currentActionPath = (String)request.getAttribute("currentActionPath");
	int panel = -1;//打开哪个菜单面板
	if (moduleMenus != null && moduleMenus.size() > 0) {
		boolean exit = false;
		for (int i = 0; i < moduleMenus.size(); i++) {
			if (exit) break;
			ModuleMenu menu = moduleMenus.get(i);
			if(menu==null || menu.getAuthorityList()==null || menu.getAuthorityList().size()==0){
				continue;
			}
			label:
			for(Authorities auth : menu.getAuthorityList()){
				if(auth == null || auth.getResUrls() == null || auth.getResUrls().size()==0){
					continue;
				}
				for (String urls : auth.getResUrls()) {
					if (currentActionPath.equals(urls) || currentActionPath.startsWith(urls + "/")) {
						request.setAttribute("currentPath", auth.getAction());
						panel = i;
						exit = true;
						break label;
					}
				}
			}
		}
	}
	request.setAttribute("panel", panel);
%>

<%@page import="java.util.List"%>
		<nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav metismenu" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="img/profile_small.jpg" />
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${sessionScope.security_context_user.name}</strong>
                             </span> <span class="text-muted text-xs block">${sessionScope.security_context_user.position} <b class="caret"></b></span> </span> </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <!-- <li><a href="profile.html">Profile</a></li> -->
                                <li><a href="javascript:;" onclick="updataPassword()">修改密码</a></li>
                                <li class="divider"></li>
                                <li><a href="logout">退出登录</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            IN+
                        </div>
                    </li>
                    <c:if test="${has_index_stats == false}">
                    <li <c:if test="${panel == -1}">class="active"</c:if>>
                        <a href="<c:url value="/common/platform"/>"><i class="fa fa-th-large"></i> <span class="nav-label">首页</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li <c:if test="${panel == -1}">class="active"</c:if>><a href="<c:url value="/common/platform"/>">首页</a></li>
                        </ul>
                    </li>
                    </c:if>
                    <c:if test="${security_context_user.moduleMenuList ne null }">
                    	<c:forEach items="${security_context_user.moduleMenuList}" var="menu" varStatus="st">
                    		<li <c:if test="${panel != -1 && (st.count-1) == panel}">class="active"</c:if>>
                    			<a style="cursor:pointer;"><i class="${menu.icon}"></i> <span class="nav-label">${menu.moduleName}</span><span class="fa arrow"></span></a>
		                        <ul class="nav nav-second-level collapse">
		                            <c:forEach items="${menu.authorityList}" var="auth">
			  							<li <c:if test='${currentPath eq auth.action}'>class="active"</c:if>>
											<a href="<c:url value="${auth.action}"/>">
												<i class=""></i>
												${auth.authDesc}
											</a>
										</li>
			  						</c:forEach>
		                        </ul>
                    		</li>
                    	</c:forEach>
                    </c:if>
                </ul>

            </div>
        </nav>
        <!-- iCheck -->
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
<div class="modal inmodal fade" id="updateUserPasswd" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-md">
        <div class="modal-content  ibox-content">
        
        <form method="post" id="updateUserPasswdForm" class="form-horizontal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
			<div class="form-group">
				<label class="col-sm-3 control-label"><font color="red">* </font>密码类型</label>
	        	<div class="col-sm-8">
	            	<div class="i-checks"><label> <input type="radio" id="userLoginPwd" value="1" checked name="userState" class="iradio_square-green" > <i></i> 登录密码 </label></div>
	            	<div class="i-checks"><label> <input type="radio" id="userWorkPwd" value="2" name="userState" class="iradio_square-green"> <i></i> 工作密码 </label></div>
	        	</div>
	    	</div>	
			<div class="form-group">
	            <label class="col-sm-3 control-label"><font color="red">* </font>旧密码</label>
	                <div class="col-sm-8"><input type="password" id="userOldpasswd" name="userOldpasswd" placeholder="旧密码" class="form-control"></div>
	        </div>									
			<div class="form-group">
	            <label class="col-sm-3 control-label"><font color="red">* </font>新密码</label>
	                <div class="col-sm-8"><input type="password" id="userNewpasswd" name="userNewpasswd" placeholder="新密码" class="form-control"></div>
	        </div>
			<div class="form-group">
	            <label class="col-sm-3 control-label"><font color="red">* </font>重复新密码</label>
	                <div class="col-sm-8"><input type="password" id="userRepasswd" name="userRepasswd" placeholder="再次输入新密码" class="form-control"></div>
	        </div>
    
            </div>
            <div class="modal-footer">
            	<div class="col-sm-4 col-sm-offset-3">
            		<button type="button" class="btn btn-white" data-dismiss="modal">取 消</button>
                	<!-- <button type="submit" id="saveUserPasswd" class="btn btn-primary">确 定</button> -->
                	<button type="button" id="saveUserPasswd" onclick="doUpdata()" class="btn btn-primary">确 定</button>
                </div>
            </div>
		</form>
        </div>

    </div>
</div>
<script type="text/javascript">
	function updataPassword() {
		document.getElementById('userOldpasswd').value="";
		document.getElementById('userNewpasswd').value="";
		document.getElementById('userRepasswd').value="";
		$('#updateUserPasswd').modal('show');
	}
	function doUpdata() {
		var userOldpasswd = $("#userOldpasswd").val();
		var userNewpasswd = $("#userNewpasswd").val();
		var userRepasswd = $("#userRepasswd").val();
		if (userOldpasswd.length < 6 || userOldpasswd.length > 20) {
			bootbox.alert("旧密码长度为6-20！");
			return ;
		}
		if (userNewpasswd.length < 6 || userNewpasswd.length > 20) {
			bootbox.alert("新密码长度为6-20！");
			return ;
		}
		if (userRepasswd !== userNewpasswd) {
			bootbox.alert("两次输入的密码不相同！");
			return ;
		}
		var json = $('form#updateUserPasswdForm').serializeJSON();
		$.ajax({
			type : "post",
			contentType : "application/json; charset=UTF-8",
			url : ctx + "/employee/updatePassword",
			data : json,
			dataType : "json",
			success : function(msg) {
				if (msg.code == 1) {
					$('#updateUserPasswd').modal('hide');
					bootbox.alert("修改密码成功！");
				} else if(msg.code == 2){
					$('#updateUserPasswd').modal('hide');
					bootbox.dialog({
                        message: "修改密码成功，请重新登录！",
                        title: "温馨提示：",
                        buttons: {
                            main: {
                                label: "确定",
                                className: "btn btn-primary",
                                callback: function() {
                                    window.location.reload();
                                }
                            }
                        }
                    });
				}
				else{
					bootbox.alert(msg.message);
				}
			},
			error : function(msg) {
				bootbox.alert(msg.message);
			}
		});
	};
</script>