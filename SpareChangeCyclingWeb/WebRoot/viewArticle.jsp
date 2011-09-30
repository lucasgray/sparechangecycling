<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="includes/taglibs.jsp" %>
<html>
  <head>
 
 	<script>
 	
 	$(document).ready(function(event) {
 		
 		$(".toTop").click(function(event) {
 			scroll(0,0);
 		});
 		
 	});
 	
 	</script>
 
  	<meta name="description" content="${article.summary }"/>
    <style>
    	h1,h3,h4 {
    		padding: 0px 10px 0px 10px;
    		
    	}
    	.largeElement p {
    		padding: 10px;
    	}
    </style>
    <title>${article.title }</title>

  </head>
  
  <body>
  	<div class="largeElement readableText" style="width:auto">
	<h1 >${article.title }</h1>
	<div style="margin:15px;border-top: 1px solid #cccccc"></div>
	<h4 >by ${article.author }</h4>
	
	<div style="height:1em; clear:both">&nbsp;</div>
	
	<div style="float:left;">
	${article.body }
	</div>
	
	
	<a class="toTop article">Back to top</a>
	</div> 
	<br style="clear:both;height:0px"/>
	
  </body>
</html>
