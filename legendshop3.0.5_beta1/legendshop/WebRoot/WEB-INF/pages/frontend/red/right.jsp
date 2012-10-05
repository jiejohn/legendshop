<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file='/WEB-INF/pages/common/taglib.jsp'%>
<link rel="stylesheet" type="text/css" media='screen' href="${pageContext.request.contextPath}/common/default/css/overlay.css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<script type="text/javascript">
		function pager(curPageNO){
			document.getElementById("curPageNO").value=curPageNO;
			document.getElementById("form1").submit();
		}
	</script>
    <!-----------right_con-------------->
     <div class="right_con">
          <div class="i-right" ><div class="hotsale">
            <div  class="mc list-h">
            <dl>
            <dt><a href="#" target="_blank"><img width="100" height="100" alt=" " src="http://img14.360buyimg.com/n4/1445/93addefa-8c64-4819-b52b-eed494165837.jpg"/></a></dt>
            <dd>
             <div class="p-name"><a href="#" target="_blank">周末放价，强悍小Y仅4599，抢完关仓！精工之作，唯美首选！强劲小Y，性能无可比拟！</a></div>
             <div class="p-price">特价：<span>￥499.00</span></div>
             <div class="btns"><a href="#" target="_blank">立即抢购</a></div>
            </dd>
            </dl>
             <dl>
            <dt><a href="#" target="_blank"><img width="100" height="100" alt=" " src="http://img14.360buyimg.com/n4/1445/93addefa-8c64-4819-b52b-eed494165837.jpg"/></a></dt>
            <dd>
             <div class="p-name"><a href="#" target="_blank">周末放价，强悍小Y仅4599，抢完关仓！精工之作，唯美首选！强劲小Y，性能无可比拟！</a></div>
             <div class="p-price">特价：<span>￥499.00</span></div>
             <div class="btns"><a href="#" target="_blank">立即抢购</a></div>
            </dd>
            </dl>
             <dl>
            <dt><a href="#" target="_blank"><img width="100" height="100" alt=" " src="http://img14.360buyimg.com/n4/1445/93addefa-8c64-4819-b52b-eed494165837.jpg"></a></dt>
            <dd>
             <div class="p-name"><a href="#" target="_blank">周末放价，强悍小Y仅4599，抢完关仓！精工之作，唯美首选！强劲小Y，性能无可比拟！</a></div>
             <div class="p-price">特价：<span>￥499.00</span></div>
             <div class="btns"><a href="#" target="_blank">立即抢购</a></div>
            </dd>
            </dl>
            </div>
          </div></div>
          
          <!--------------筛选---------------->
          <div class="m10 select">
                <div class="mt">
                <h1>平板电视</h1><div class="extra"><a href="http://www.360buy.com/products/737-794-798.html">重置筛选条件</a></div></div>
                <dl  class="fore">
                   <dt>品牌：</dt>
                   <dd><div class="content"><div rel="0"><a class="curr" href="#" id="0">不限</a></div><div rel="8"><a href="#" id="3789">夏普</a></div><div rel="6"><a href="#" id="4085">索尼</a></div><div rel="6"><a href="#" id="1279">三星</a></div><div rel="3"><a href="#" id="3999">海信</a></div><div rel="6"><a href="#" id="5674">TCL</a></div><div rel="1"><a href="#" id="3781">创维</a></div><div rel="6"><a href="#" id="4678">松下</a></div><div rel="2"><a href="#" id="4012">飞利浦</a></div><div rel="3"><a href="#" id="3889">康佳</a></div><div rel="1"><a href="#" id="3888">长虹</a></div><div rel="5"><a href="#" id="54846">清华同方</a></div><div rel="1"><a href="#">东芝</a></div><div rel="8"><a href="#" id="70352">熊猫</a></div><div rel="4"><a href="#" id="9675">乐华</a></div><div rel="4"><a href="#" id="1280">LG</a></div><div rel="4"><a href="#" id="113066">联想</a></div><div rel="1"><a href="#" id="64110">BOE/京东方</a></div><div rel="5"><a href="#" id="62990">Pangoo/盘古</a></div></div></dd></dl>
                <dl><dt>品类：</dt><dd><div ><a class="curr" href="#" >不限</a></div><div  ><a href="#" >LED背光电视</a></div><div ><a href="#" >LCD背光电视</a></div><div  ><a href="#" >等离子电视</a></div></dd></dl>
                <dl><dt>功能：</dt><dd><div ><a class="curr" href="#" >不限</a></div><div ><a href="#" >3D电视</a></div><div ><a href="#" >非3D电视</a></div><div ><a href="#" >智能电视</a></div><div ><a href="#" >非智能电视</a></div><div ><a href="#" >网络电视</a></div><div ><a href="#" >非网络电视</a></div></dd></dl>
                
			</div>
          <!---------------筛选end------------------->
        
        
        <div class="pagetab m10">
         <h3>排序</h3>
         <ul>           
           <li class="on upa"><span>销量</span></li>
           <li><span>价格</span></li>
           <!-- <li class="on downb"><span>价格</span></li> -->
           <li><span>评论数</span></li>  
           <li><span>上架时间</span></li>           
         </ul>       
        </div>
        
        
        <!--------------产品列表--------------------->
        <div class="plist" class="m">
				<ul class="list-h">       
				
                <c:forEach items="${requestScope.list}" var="prodDetail" varStatus="status">            
                  <li>
                    <div class="p-img"><a href="#" target="_blank"><img width="180" height="160"  src="<ls:images item='${prodDetail.pic}'/>"></a><div class="pi3"></div></div>
                    <div class="p-name"><a href="#" target="_blank">${prodDetail.name}</a></div>
                    <div class="p-price" > <c:if test="${not empty prodDetail.cash}">
                                                                <fmt:message key="prod_cash" /> 
                                                                <fmt:formatNumber
                                                                        type="currency" value="${prodDetail.cash}" pattern="${CURRENCY_PATTERN}"/>
                                                                
                                                              </c:if></div>
                    <!-- <div class="extra"><span class="evaluate"><a href="#" target="_blank">已有68人评价</a></span><span class="reputation">(91%好评)</span><span id="p533471"></span></div> -->
                     <div class="btns"><input type="button"  value="购买" ><input type="button"  value="关注"  ><input type="button" value="对比" ></div>
                  </li>
                 </c:forEach>

				</ul>              
                
			</div>
            
            <!--------产品列表end---------->
            
         <div class="clear"></div>
         <div class="fanye">
		<c:if test="${toolBar!=null}">
			<p align="right">
				<c:out value="${toolBar}" escapeXml="${toolBar}"></c:out>
			</p>
			</c:if>
         
                                    <form action="${pageContext.request.contextPath}/sort" id="form1" method="post">
                                        <input type="hidden" id="curPageNO" name="curPageNO" value="${prod.curPageNO}"/>
                                        <input type="hidden" name="sortId" value="${sort.sortId}"/>
                                    </form>
         </div> 
       
       
     </div>
    <!-----------right_con end-------------->
    





