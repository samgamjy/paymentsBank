<%@ page language="java" %>
<%
    String mainPage=(String) session.getAttribute("userLastName");
//    int role = 0;
    if(username==null) {
        username = "guest";
    }else{
        role = 2;//Integer.parseInt((String)session.getAttribute("role"));
    }
%>
<a href="index.jsp">Main page</a>