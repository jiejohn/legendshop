<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
  <%@include file='/WEB-INF/pages/common/common.jsp'%>
  <%@include file='/WEB-INF/pages/common/taglib.jsp'%>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/theme/gallery/jquery.ad-gallery.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/theme/gallery/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/plugins/theme/gallery/jquery.ad-gallery.js"></script>
  <script type="text/javascript">
  var start_message = '<fmt:message key="start.message"/>';
  var stop_message = '<fmt:message key="stop.message"/>';
  $(function() {
    var galleries = $('.ad-gallery').adGallery();

  });
  </script>

  <style type="text/css">
  * {
    font-family: "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Verdana, Arial, sans-serif;
    color: #333;
    line-height: 140%;
  }
  select, input, textarea {
    font-size: 1em;
  }

  h2 {
    margin-top: 1.2em;
    margin-bottom: 0;
    padding: 0;
    border-bottom: 1px dotted #dedede;
  }
  h3 {
    margin-top: 1.2em;
    margin-bottom: 0;
    padding: 0;
  }
  .example {
    border: 1px solid #CCC;
    background: #f2f2f2;
    padding: 10px;
  }
  ul {
    list-style-image:url(list-style.gif);
  }
  pre {
    font-family: "Lucida Console", "Courier New", Verdana;
    border: 1px solid #CCC;
    background: #f2f2f2;
    padding: 10px;
  }
  code {
    font-family: "Lucida Console", "Courier New", Verdana;
    margin: 0;
    padding: 0;
  }

  #gallery {
    padding: 30px;
  }
  </style>
  <title>${prod.name}</title>
</head>
<body style="text-align:center">
  <div id="container">
    <h2>${prod.name} <a href="${pageContext.request.contextPath}/views/${prod.prodId}${applicationScope.WEB_SUFFIX}">[<fmt:message key="order"/>]</a></h2>
    <div id="gallery" class="ad-gallery">
      <div class="ad-image-wrapper">
      </div>
      <div class="ad-controls">
      </div>
      <div class="ad-nav">
        <div class="ad-thumbs">
          <ul class="ad-thumb-list">
          <c:forEach items="${requestScope.prodPics}" var="pic"  varStatus="status">
            <li><a href="${pageContext.request.contextPath}/photoserver/photo/${pic.filePath}">
                <img src="${pageContext.request.contextPath}/photoserver/images/${pic.filePath}" class="image${status.index+1}" width="108px" height="80px">
              </a></li>
          </c:forEach>
          </ul>
        </div>
      </div>
    </div>

  </div>
</body>
</html>