<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="includes/taglibs.jsp" %>

<html>
  <head>    
    <title>Manage your ads</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>
$().ready(function() {
	$(".prettyAcctBtn").click(function(event) {
		window.location = $(this).children(".nodec").attr("href");
	});
	$(".adslist").click(function(event) {
		window.location = $(this).children(".viewAd").attr("href");
	});
});
</script>
  </head>
  
  <body>
    <div class="fullWidthPrettyContainer">
    	<stripes:messages/>
    
    	<div style="text-align: center;">
		<h1>Hey there, ${sessionScope.user.username}!</h1>
    	<br/>
    	</div>
    	
		<div style="float:left;width:50%">
	    <h2>Manage ads</h2><br/>
	    <c:if test="${fn:length(ads) eq 0}">
	    	<p>You have no active ads.  
	    	<stripes:link beanclass="com.sparechangecycling.web.actions.SellActionBean" event="begin">Post one!</stripes:link></p>
	    </c:if>
	    	    
	    <c:forEach items="${ads}" var="ad">
	    
	    
	    	<p class="adslist">
	    	<stripes:link beanclass="com.sparechangecycling.web.actions.AdDetailActionBean" event="singleSccDetail" class="viewAd" style="display:none">
		    	<stripes:param name="id" value="${ad.id}"/>
		   	</stripes:link>
	    	${ad.year} ${ad.firstTitle} ${ad.secondTitle}
	    	
	    	<stripes:link beanclass="com.sparechangecycling.web.actions.AdDetailActionBean" event="deleteAd">
	    		<stripes:param name="curUserId" value="${sessionScope.user.id}"/>
	    		<stripes:param name="id" value="${ad.id}"/>
	    		<img src="images/delete_16.png" title="delete" alt="delete" style="float:right;"/>
	    	</stripes:link> 
	    	<stripes:link beanclass="com.sparechangecycling.web.actions.AdDetailActionBean" event="editAd">
	    		<stripes:param name="curUserId" value="${sessionScope.user.id}"/>
	    		<stripes:param name="id" value="${ad.id}"/>
	    		<img src="images/pencil_16.png" title="edit" alt="edit" style="float:right;margin-right:5px"/>
	    	</stripes:link> 
	    	
	    	</p>
	    </c:forEach><br/>
	    </div>
	    
	    <div style="float:left;width:50%">
	    <h2>Account Settings</h2><br/>
	    <c:if test="${not sessionScope.user.subscription.active}">
	    	<div class="prettyAcctBtn" ><stripes:link beanclass="com.sparechangecycling.web.actions.SellActionBean" event="upgrade" class="nodec"><img src="images/up_32.png"/>
	    	<span style="vertical-align: middle;">Upgrade your account</span>
	    	</stripes:link></div><br/>
	    </c:if>
	    
	    <c:if test="${sessionScope.user.subscription.active}">
	    	<div class="prettyAcctBtn" ><stripes:link beanclass="com.sparechangecycling.web.actions.ProfileActionBean" event="createOrModify" class="nodec"><img src="images/user_32.png"/>Create/modify your profile page</stripes:link></div><br/>
	    </c:if>
	    
	    <div class="prettyAcctBtn" ><stripes:link beanclass="com.sparechangecycling.web.actions.UserActionBean" event="viewMessages" class="nodec"><img src="images/letter_32.png"/>&nbsp;View your message inbox</stripes:link></div><br/>
	    <div class="prettyAcctBtn" ><stripes:link beanclass="com.sparechangecycling.web.actions.UserActionBean" event="editProfile" class="nodec"><img src="images/key_32.png"/>Edit profile/account info</stripes:link></div>
    	
    	<div style="height:.25;clear:both;"></div>
    	</div>
    	<br style="clear:both"/>
    </div>
  </body>
</html>
