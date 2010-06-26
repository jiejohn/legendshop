<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="bingosoft.jcf.util.EnvironmentConfig"%>
<%
   session.setAttribute("userName",null);
   session.invalidate();
   String serverUrl = EnvironmentConfig.getInstance().getPropertyValue("config/acegi-cas.properties","cas.server.url");
   String serviceUrl = EnvironmentConfig.getInstance().getPropertyValue("config/acegi-cas.properties","cas.service.url");
   String URL = serverUrl+"/logout?url="+serviceUrl+"/j_acegi_logout";
   response.sendRedirect(URL); 
%>
