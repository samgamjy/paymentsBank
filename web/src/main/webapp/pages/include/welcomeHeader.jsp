<p>Welcome, <%=username%></p>
<%@ page language="java" %>
<%
    String profile="";

//    int role = Integer.parseInt((String)session.getAttribute("role"));
    if (role == 2){
        profile = "<a href=\"/PaymentControl?page=PROFILE_CLIENT\">profile</a>";
    }else{
        profile = "<a href=\"/pages/createClient.jsp\">registration</a>";
    }
%>
<p>Profile detail, <%=profile%></p>
