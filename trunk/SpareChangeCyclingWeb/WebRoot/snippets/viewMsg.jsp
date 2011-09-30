<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp" %>

<html>
  <head>
	<link rel="stylesheet" type="text/css" href="styles/jquery.facebox.css">
	<script type="text/javascript">
		jQuery(document).ready(function($) {
	  		$('a[rel*=facebox1]').facebox();
		}); 
	</script>
  </head>
  
  <body>
  	<div id="popMsg" style="padding: 2em;">
	   
	  <h3>${message.title }</h3> 
	  <p>${message.body}</p>
	  <p>	-from 
		<stripes:link beanclass="com.sparechangecycling.web.actions.UserActionBean" rel="facebox1" event="popUpMsgDialog">
	    	<stripes:param name="recipient" value="${message.sender.id}"/>
	    	<stripes:param name="usrName" value="${message.sender.username}"/>
	    	${message.sender.username }</stripes:link>
		, <fmt:formatDate value="${message.timestamp}" pattern=" hh:mm aa MM/dd/yyyy"/>
	</p> 	
  	</div>
  </body>
</html>
