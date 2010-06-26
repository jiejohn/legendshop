<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/jsp/common.jsp"%>
<%@ taglib uri="/WEB-INF/tld/auth.tld" prefix="auth" %>
<%@page import="bingosoft.jcf.util.EnvironmentConfig"%>
<%@page import="bingosoft.jcf.util.FileConfig"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.done.util.FileTimeWrapper"%>

<%@page import="java.util.Arrays"%>
<%@page import="java.text.DecimalFormat"%><html>
<head>
<title>文件下载</title>

</head>
<body>
<div align="center"><span class="title1">上传的文件列表</span><br>
</div>
<center>
                 <table width="567" class="tableBorder">
                 <tr>
                    <td width="180" >文件名</td>
                    <td width="180" >时间</td>
                    <td width="180" >大小</td>
                    <td width="30" >下载</td>
                   </tr>
                <%
                 	        //String filePath = "/v0/done/logs/";
       	      				String filePath = EnvironmentConfig.getInstance().getPropertyValue(FileConfig.ConfigFile, "DownloadPath");
       	   					File dir = new File(filePath);
			                 File[] files=null;
			                 if(dir.isDirectory()){
			                     files=dir.listFiles();
			                 }
			                 if(files!=null){
				              FileTimeWrapper [] fileWrappers = new FileTimeWrapper[files.length]; 
				                  for (int j=0; j<files.length; j++) {
				                	              fileWrappers[j] = new FileTimeWrapper(files[j]);  
				                	          }  
				                  Arrays.sort(fileWrappers); 
			                 for(int i=0;i<fileWrappers.length;i++){
			                	 DecimalFormat format = new DecimalFormat("#.#");
			                	 String str = format.format(fileWrappers[i].getFile().length()/1024.0);
                  %>
                  <tr >
                    <td ><%=fileWrappers[i].getFile().getName() %></td>
                    <td ><%=new Timestamp(fileWrappers[i].getFile().lastModified())%></td>
                    <td ><%=str %></td>
                    <td >
                        <a href="<%=request.getContextPath()%>/servlet/downloadfileservlet/<%=java.net.URLEncoder.encode(fileWrappers[i].getFile().getName(),"UTF-8") %>">下载</a>
                    </td>
                   </tr>
 		      <%} }%>
                </table>
  
</center>
</body>
</html>
