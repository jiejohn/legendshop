<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<link href="${pageContext.request.contextPath}/common/css/css.css" rel="stylesheet" type="text/css" />
     <style type="text/css" media="all">
       @import url("${pageContext.request.contextPath}/common/default/css/screen.css");
     </style>
<jsp:scriptlet> String lClass = "its";
   if( request.getParameter( "class" ) != null ) {
      lClass = request.getParameter( "class" );
      if (!("isis".equals(lClass) || "its".equals(lClass) || "mars".equals(lClass) || "simple".equals(lClass) || "report".equals(lClass) || "mark".equals(lClass)))
      {
        lClass="";
      }
   }
   pageContext.setAttribute("tableclass", lClass);
</jsp:scriptlet>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />