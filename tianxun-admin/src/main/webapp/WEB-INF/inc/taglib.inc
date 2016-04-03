<%@page import="com.vteba.utils.common.PropUtils"%>
<%@page import="com.vteba.utils.date.DateUtils"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="/META-INF/tld/" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="base" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<% String imagePrefix = PropUtils.get("images.prefix");
   request.setAttribute("imagePrefix", imagePrefix);
   String version = PropUtils.get("js.version", DateUtils.toDateString("yyyyMMdd"));
   request.setAttribute("jsVersion", version);
 %>
 <% String filePath = PropUtils.get("file.path");
   request.setAttribute("filePath", filePath);
 %>