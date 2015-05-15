<p>Welcome, <%=username%></p>
<%@ page language="java" %>
<%
    String profile="";

//    int role = Integer.parseInt((String)session.getAttribute("role"));
    String root = "Guest";
    if (role == 2){
        profile = "<a href=\"/PaymentControl?page=PROFILE_CLIENT\">profile</a>";
        root = "Client";
    }else{
        if (role == 0) {
            profile = "<a href=\"/pages/createClient.jsp\">registration</a>";
            root = "Guest";
        } else{
            profile = "<a href=\"/pages/createClient.jsp\">registration</a>";
            root = "Guest";
        }
    }

%>
<p>Profile detail, <%=profile%></p>
<p>Role, <%=root%></p>
