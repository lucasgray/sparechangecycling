<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="includes/taglibs.jsp" %>

<html>
  <head>    
    <title>Edit your profile</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div class="largeElement">
    	<h1>Editing profile for ${sessionScope.user.username}</h1>
    	<br/>
    	<stripes:errors/>
    	<p>All fields are optional.  Update the ones you'd like to change.</p><br/>
    	<stripes:form beanclass="com.sparechangecycling.web.actions.UserActionBean">
    		<stripes:label for="user.zip" class="reg">Zip Code: </stripes:label><stripes:text name="user.zip" class="text"/><br/>
    		<stripes:label for="user.email" class="reg">E-mail: </stripes:label><stripes:text name="user.email" class="text"/><br/>
    		<stripes:label for="passwordChng1" class="reg">Password: </stripes:label><stripes:password name="passwordChng1" class="text" /><br/>
    		<stripes:label for="passwordChng2" class="reg">Repeat Password: </stripes:label><stripes:password name="passwordChng2" class="text"/><br/>
    		<stripes:submit name="updateProfile" value="Update" class="button"/>
    	</stripes:form>
    </div>
  </body>
</html>
