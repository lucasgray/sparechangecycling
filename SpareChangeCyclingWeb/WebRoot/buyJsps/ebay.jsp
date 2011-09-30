<%@ include file="../includes/taglibs.jsp"%>
<script type="text/javascript" src="scripts/jquery.facebox.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
  		$('a[rel*=facebox]').facebox();
	}); 
</script>
<script type="text/javascript">
$(document).ready(function(event) {
	observeClickyAdsEbay();
});
</script>
<stripes:useActionBean beanclass="com.sparechangecycling.web.actions.SearchBikesActionBean" var="ads" />
	<p>Viewing ebay ads for ${ads.context.searchPreferences.type[0].string}</p>
	<a href="snippets/aboutEbay.html" style="font-size: 12px" rel="facebox"><img src="images/info_16.png" class="no_u"/>&nbsp;learn more</a><br/><br/>
	
	<c:set var="bikes" value="${ads.ebayBikes }"/>
	
	<c:if test="${fn:length(bikes) eq 0}">
		<br/><br/>
		<p style="text-align: center;">No results to display.  Try broadening your search.</p>
		<br/><br/>
	</c:if>
	
	<c:forEach items="${bikes}" var="ad">

		<div class="ad" >
		
		<img src="${ad.picLink }" style="float: left;margin-right:10px;"/>
		<span class="ebay-title">
			<a href="${ad.link}" title="${ad.title}" style="height:85px;" class="href">
				<c:choose>
					<c:when test="${fn:length(ad.title) > 75}">
							${fn:substring(ad.title,0,75) }... 
					</c:when>
					<c:otherwise>
							${ad.title} 
					</c:otherwise>
				</c:choose>
			</a>
		</span>
		<p class="price"><fmt:formatNumber value="${ad.currentPrice}" type="currency" /></p>
		<p class="date">Auction end date: <fmt:formatDate value="${ad.endDateTime}" pattern="hh:mm a MM/dd/yyyy" /></p>
		</div>
			
		<div style="height: .5em;">&nbsp;</div>
	</c:forEach>
