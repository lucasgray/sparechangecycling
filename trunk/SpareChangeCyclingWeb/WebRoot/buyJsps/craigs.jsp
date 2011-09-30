<%@ include file="../includes/taglibs.jsp"%>
<script type="text/javascript" src="scripts/jquery.facebox.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
  		$('a[rel*=facebox]').facebox();
	}); 
</script>
<script type="text/javascript">
$(document).ready(function(event) {
	observeClickyAds();
});
</script>
<stripes:useActionBean beanclass="com.sparechangecycling.web.actions.SearchBikesActionBean" var="ads" />
	<p>Viewing craigslist ads</p>
	<a href="snippets/aboutCraigs.html" style="font-size: 12px" rel="facebox"><img src="images/info_16.png" class="no_u"/>&nbsp;learn more</a><br/><br/>
	
	<c:set var="bikes" value="${ads.craigsBikes }"/>
	
	<c:if test="${fn:length(bikes) eq 0}">
		<br/><br/>
		<p style="text-align: center;">No results to display.  Try broadening your search.</p>
		<br/><br/>
	</c:if>
	
	<c:forEach items="${bikes}" var="ad">
	<div class="ad">
		
			<span class="title">
				<a href="${ad.link}" class="href"> 
					<c:choose>
						<c:when test="${fn:length(ad.title) > 60}">
							${fn:substring(ad.title,0,60) }... 
						</c:when>
						<c:otherwise>
							${ad.title}
						</c:otherwise>
					</c:choose>
				</a>
			</span>
			<div style="float:left; padding-right: 5px;width: 150px;">
			<c:if test="${ad.price gt 0.0}">
			<p class="price"><fmt:formatNumber value="${ad.price}" type="currency" /></p>
			</c:if>
			<p class="city">${ad.location}</p>
			<p class="subdomain">${ad.clSubdomain}.craigslist.org</p>
			<p class="city"><fmt:formatDate value="${ad.date}" pattern=" hh:mm aa MM/dd/yyyy"/></p>
			</div>
			
			<p class="detail" >${ad.detail}</p>
					
	</div>
	<div style="height: 1em;margin-top:1em;border-top:1px solid #cccccc;margin:1em 100px 0px 100px"></div>
	</c:forEach>