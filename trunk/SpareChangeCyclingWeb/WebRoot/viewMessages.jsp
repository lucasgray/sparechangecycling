<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="includes/taglibs.jsp" %>

<html>
  <head>
    <title>Message inbox</title>
    
    <script type="text/javascript" src="scripts/jquery.facebox.js"></script>
    <script type="text/javascript">
    	$(document).ready(function($) {
	  		$('a[rel*=facebox]').facebox();
	  		
	  		
	  		var curMsg;
	  		$("[id^=link-]").click(function(event) {
	  			curMsg=$(this).attr("id").substring(5);
	  			//alert(curMsg);
	  		});
	  		$(document).bind('close.facebox', function() {
				$("#msg-"+curMsg).css("font-weight","normal");
			});
		});
	</script>
  </head>
  
  <body>
  	<div class="medElement">
  		<h1>Message Inbox</h1><br/>

		<c:if test="${fn:length(sessionScope.user.receivedMessages) eq 0}">
			<p>You have no messages.</p>
		</c:if>
  	
  		<c:forEach items="${sessionScope.user.receivedMessages}" var="message">
  			<div class="msg">
		  		<div id="msg-${message.id}" style="<c:if test="${!message.isRead}">font-weight:bold;</c:if>">
		  		<stripes:link id="link-${message.id}" beanclass="com.sparechangecycling.web.actions.UserActionBean" rel="facebox" event="viewSingleMessage">
		    		<stripes:param name="message.id" value="${message.id}"/>
		  			${message.title }
		  		</stripes:link>
				</div>
				<p>Sent <fmt:formatDate value="${message.timestamp}" pattern="h:mm a EEE, MMM d, ''yy"/> from ${message.sender.username}</p>
  			</div>
  		</c:forEach>
  	
  	<stripes:link beanclass="com.sparechangecycling.web.actions.SellActionBean" event="manageAds">
	Back to control panel
	</stripes:link>
	</div>
	
	
	
  </body>
</html>
