<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="includes/taglibs.jsp" %>

<html>
  <head>
    <title>Buy and sell bikes, parts, and accessories</title>
  </head>
  <body>
  	
  	<stripes:form beanclass="com.sparechangecycling.web.actions.ArticleActionBean">
  		<stripes:hidden name="article.id" value="article.id"/>
	  	Title: <stripes:text name="article.title" size="100"/><br/>
	  	Body: <stripes:textarea name="article.body" cols="100" rows="20"/><br/>
	  	Author: <stripes:text name="article.author" size="100"/><br/>
  		Summary: <stripes:text name="article.summary" size="100"/><br/>
  		<stripes:submit name="saveArticle" value="Save"/>
  	</stripes:form>
  	
  </body>
</html>
