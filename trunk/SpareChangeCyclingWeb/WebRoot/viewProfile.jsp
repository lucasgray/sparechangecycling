<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="includes/taglibs.jsp" %>
<html>
  <head>
    
    <title>${user.profile.shopName }</title>
    
  <link rel="stylesheet" href="styles/jquery.jcarousel.css" type="text/css"></link>
  <link rel="stylesheet" href="scripts/jcarousel_skin/skin.css" type="text/css"></link>
  <script type="text/javascript" src="scripts/jquery.jcarousel.pack.js"></script>  
    <script type="text/javascript" src="scripts/sccBuyScripts.js"></script>
    
  	<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=ABQIAAAA7njFJpmdJjJUrvULx7JvERQ-xbmVcJ3zPf06YSAS1a70p4JcVRSHbVXtcBji-FNSVmhaT6_BwvPQog" type="text/javascript"></script>

	<script type="text/javascript">
		$(document).ready(function(event) {
			$("#carousel").jcarousel();
			observeClickyAds();
			
			initialize();
			
		});
		
		$(window).unload(GUnload());

	    

var map = null;
var geocoder = null;
function initialize() {
	if (GBrowserIsCompatible()) {
		map = new GMap2(document.getElementById("map_canvas"));
		map.setUIToDefault();
		geocoder = new GClientGeocoder();
		address= '${user.profile.addrOne} ${user.profile.addrTwo } ${user.profile.city }, ${user.profile.state } ${user.profile.zip }';
		geocoder.getLatLng(address,
			function(point) {
				if (!point) {
					alert(address + " not found");
				} else {
					map.setCenter(point, 12);
					var marker = new GMarker(point);
					map.addOverlay(marker);
					marker.openInfoWindowHtml('${user.profile.shopName}');
				}
			}
		);
	}
}
		
	</script>
  
  </head>
  
  <body>
  	<div class="fullWidthPrettyContainer">
  	<div style="float:left; width: 60%">
	<h1>${user.profile.shopName}</h1>
	
	<p style="padding: 10px 0px 10px 0px">${user.profile.blurb }</p>
	
	<h3>Address</h3>
	
	<p>${user.profile.addrOne }</p>
	<p>${user.profile.addrTwo }</p>
	<p>${user.profile.city }, ${user.profile.state } ${user.profile.zip }</p>

	</div>
	
	<div id="map_canvas" style="width: 40%; height: 300px;float:right;"></div>
	
	<br style="clear:both"/>
	
	<h2>Current bikes and parts for sale: </h2>
	<br style="border-bottom: 1px solid #cccccc;"/>
    <ul id="carousel" class="jcarousel-skin-tango">
		<c:forEach items="${user.ads}" var="ad" varStatus="x">	
	
			<li>
				<div class="inside ad">
					<div class="carousel_img_container">
						<img src="${ad.thumbs[0].href }" class="carousel_img"/>
					</div>
					<br/>
					<span class="title">
					<stripes:link beanclass="com.sparechangecycling.web.actions.AdDetailActionBean" event="singleSccDetail" style="height:110px;" class="href">
					<stripes:param name="id" value="${ad.id}" />
					<h3>${ad.year} ${ad.firstTitle } ${ad.secondTitle }</h3>
					</stripes:link>
					</span>
					<p style="text-align: center"><fmt:formatNumber value="${ad.price}" type="currency" /></p>
					</div>
			</li>
			
		</c:forEach>
	</ul>                				
	</div>
  </body>
</html>
