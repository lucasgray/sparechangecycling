<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../includes/taglibs.jsp" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%--facebox doesn't work on ie8, do this for now --%>
		<meta http-equiv="X-UA-Compatible" content="IE=7" /> 
		<base href="<%=basePath%>"></base>
		<link rel="SHORTCUT ICON" href="images/favicon.ico"/>
		<title>Spare Change Cycling, used bikes and bike accessories, and community</title>
		
		
		<meta name="keywords" content="used bikes, used bike, used bicycles, bicycle parts, bicycle accessories"/>
		
		<link rel="stylesheet" href="styles/template.css" type="text/css" ></link>
		<link rel="stylesheet" href="styles/jquery.ui.themeroller.css" type="text/css" media="screen"></link>
		<link rel="stylesheet" href="styles/jquery.asmselect.css" type="text/css"></link>
		<link rel="stylesheet" href="styles/jquery.facebox.css" type="text/css"></link>
		
		<script type="text/javascript" src="scripts/jquery-1.3.1.min.js"></script>
		<script type="text/javascript" src="scripts/jquery.validate.min.js"></script>
		<script type="text/javascript" src="scripts/jquery.asmselect.js"></script>
		<script type="text/javascript" src="scripts/jquery.history.js"></script>
		<script type="text/javascript" src="scripts/jquery.corners.js"></script>
		<script type="text/javascript" src="scripts/sccBuyScripts.js"></script>
		
		<script type="text/javascript">
$(document).ready(function() {
	//$(".rounded").corners();
	$("#clickable-banner").click(function(event) {
		window.location='<%=basePath%>';
	});
	$.validator.messages["required"]="Required";
    $.validator.messages["email"]="Invalid";
    
    $(".corner").corners();
    
    observeClickyAds();
    observeClickyArticles();
});
  		</script>
  		<decorator:head />
	</head>
	<body>
		<div class="wrapper">
			<div style="height:1.5em"></div>
			<div style="width:75%;margin: 0 auto;min-width: 750px">
				<div style="float:right;z-index: 2;font-size:12pt">
					<c:choose> 
						<c:when test="${empty sessionScope.user}" >
							<stripes:link beanclass="com.sparechangecycling.web.actions.LoginActionBean" event="begin">Login</stripes:link>
							| new here? <stripes:link beanclass="com.sparechangecycling.web.actions.LoginActionBean" event="begin-registration">register</stripes:link>
						</c:when>
						<c:otherwise>
							<stripes:link beanclass="com.sparechangecycling.web.actions.UserActionBean" event="viewMessages">
							<c:import url="snippets/msgIndicator.jsp"/></stripes:link>
							<stripes:link beanclass="com.sparechangecycling.web.actions.SellActionBean" event="manageAds">
							 ${sessionScope.user.username }
							 </stripes:link>
							&nbsp;|&nbsp;
							<stripes:link beanclass="com.sparechangecycling.web.actions.LoginActionBean" event="logout" >Logout</stripes:link>
							
						</c:otherwise>
					</c:choose>
				</div>
	
				
				
				<div id="banner">
				
					<div id="nav" >
						<stripes:link beanclass="com.sparechangecycling.web.actions.SearchBikesActionBean" event="search">browse ads</stripes:link>
						<stripes:link beanclass="com.sparechangecycling.web.actions.SellActionBean" event="begin">sell stuff</stripes:link>
						<stripes:link beanclass="com.sparechangecycling.web.actions.CommunityActionBean" event="begin" class="rightmost">community</stripes:link>
					</div>
				
				</div>
				</div>
				<br class="clearFloat"/>
				<div id="page-container-outer" class="corner">
				<div style="margin:0 auto;height:1.5em;width:95%"></div>
				<div id="page-container-inner" class="corner">
				<div id="clickable-banner" style="cursor: pointer;"></div>
				
				<div style="float:right;margin-right: 20px;margin-top:10px">
						<%-- addthis --%>
						<%-- okay so this is for google analytics --%>
						<script type="text/javascript">
						a2a_track_links='a2a';
						</script>
						<a class="a2a_dd" href="http://www.addtoany.com/share_save"><img src="http://static.addtoany.com/buttons/share_save_171_16.png" width="171" height="16" border="0" alt="Share/Save/Bookmark"/></a><script type="text/javascript">a2a_linkname=document.title;a2a_linkurl=location.href;a2a_prioritize=["facebook","twitter","delicious","gmail","google_bookmarks","windows_live_favorites","amazon_wish_list","stumbleupon","digg","squidoo"];</script><script type="text/javascript" src="http://static.addtoany.com/menu/page.js"></script>
				</div>
				
				<div id="body-container" style="clear:both;">
				
				<br style="clear:both;height:0px;"/>
				<decorator:body/>
			</div>
		</div>
	<div class="push"></div>
</div>
<div class="footer">
	
</div>
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try{
var pageTracker = _gat._getTracker("UA-12052408-1");
pageTracker._trackPageview();
} catch(err) {}</script>
	</body>
</html>

