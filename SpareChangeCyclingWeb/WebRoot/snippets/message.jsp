<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../includes/taglibs.jsp" %>

<html>
  <head>
	<link rel="stylesheet" type="text/css" href="styles/jquery.facebox.css">
  </head>
  
  <body>
  	<div id="popMsg">
  	<stripes:form beanclass="com.sparechangecycling.web.actions.UserActionBean">
  		<stripes:hidden name="recipient" value="${recipient}"/>
  		<h3>Composing message to ${usrName}</h3><br/>
  		<label for="message.title" class="msg">Title</label><stripes:text name="message.title" class="text" style="width:30em;"/><br/>
  		<label for="message.body" class="msg">Message</label><stripes:textarea name="message.body" style="width: 30em;"/><br/><br/>
  		<stripes:submit name="sendMessage" value="send" class="button" style="margin: 0 auto;display:block;"/>
  	</stripes:form>
  	</div>
  </body>
</html>
