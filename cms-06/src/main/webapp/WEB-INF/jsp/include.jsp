<%@page language="java" pageEncoding="utf-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--自定标签 --%>
<%@ taglib prefix="u" uri="/WEB-INF/UserTag.tld" %>
<% request.setAttribute("webPath", request.getContextPath());%>
<%--引入公共CSS --%>
<link rel="stylesheet" type="text/css" href="${webPath}/resources/css/common.css">
<%--引入Jquery --%>
<script type="text/javascript" src="${webPath}/resources/plugin/jquery-1.7.2.js"></script>
<%--引入通用JS包括(弹窗)--%>
<script src="${webPath}/resources/js/common.js"></script>
<%--引入选择页--%>
<script src="${webPath}/resources/js/common_ref.js"></script>
<%--常用验证JS--%>
<script src="${webPath}/resources/js/validate.js"></script>
<%--日期类控件--%>
<script type="text/javascript" src="${webPath}/resources/plugin/My97DatePicker/WdatePicker.js"></script>
