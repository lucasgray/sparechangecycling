<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="includes/taglibs.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
  <head>
    
    <title>${singleAd.firstTitle} ${singleAd.secondTitle} for sale, <fmt:formatNumber value="${singleAd.price}" type="currency" /></title>
	<meta name="description" content="${singleAd.firstTitle} ${singleAd.secondTitle} for sale"/>
	<link rel="stylesheet" type="text/css" href="styles/jquery.facebox.css">
	
	<script type="text/javascript" src="scripts/jquery.facebox.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
	  		$('a[rel*=facebox]').facebox();
		}); 
	</script>
  </head>
  
  <body>
  <div style="margin:0em 2em 0em 2em">
  	<div class="medElement" style="float:left;" >
	    <h1><c:out value="${singleAd.firstTitle}"/> <c:out value="${singleAd.secondTitle}"/></h1>
	    <h2><fmt:formatNumber value="${singleAd.price}" type="currency" /></h2>
	    <p class="adDesc readableText"><c:out value="${singleAd.description}"/></p>
	    <c:set value="${singleAd.pics}" var="pics"/>
	</div> 
	<div class="smallElement" style="float:left;width:auto;min-height: 20em; border-left: 1px solid #cccccc">
		<h2>Images</h2><br/>
	    <c:forEach items="${singleAd.thumbs}" var="thumb" varStatus="status">
	    	<a href="${pics[status.count-1].href }" rel="facebox"/> 
	    	<img src="${thumb.href}" class="prettyThumb"/>
	   		</a>
	    </c:forEach>
	</div>
	<div style="clear:both"></div>
    <div class="btmAdStuff">
	    <h3>posted by ${singleAd.user.username} on <fmt:formatDate value="${singleAd.date}" dateStyle="LONG"/></h3>
	    <c:choose>
	    <c:when test="${not empty sessionScope.user}">
		    <stripes:link beanclass="com.sparechangecycling.web.actions.UserActionBean" rel="facebox" event="popUpMsgDialog">
		    	<stripes:param name="recipient" value="${singleAd.user.id}"/>
		    	<stripes:param name="usrName" value="${singleAd.user.username}"/>
		    	(send a message)
		    </stripes:link>
	    </c:when>
	    <c:otherwise>
	    	(<stripes:link beanclass="com.sparechangecycling.web.actions.LoginActionBean" event="begin">
	    	<stripes:param name="sourcePageForLogin" value="/used-bikes/ads/singleSccDetail/${singleAd.id}"/>
	    	log in</stripes:link> to send a message)
	    </c:otherwise>
	    </c:choose>
    </div>
	</div>
	

  </body>
</html>
