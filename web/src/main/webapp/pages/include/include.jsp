<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="bundle.WebResources"/>
<%@ page language="java" %>
<%
    String username=(String) session.getAttribute("userLastName");
    int role = 0;
    if(username==null) {
        username = "guest";
    }else{
        role = 2;//Integer.parseInt((String)session.getAttribute("role"));
    }
%>