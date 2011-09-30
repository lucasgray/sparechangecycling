<%@ include file="../includes/taglibs.jsp"%>
<stripes:useActionBean
	beanclass="com.sparechangecycling.web.actions.SearchBikesActionBean"
	var="ads" />
<script type="text/javascript">
$(document).ready(function(event) {
	observeClickyAds();
});
</script>
<p>Viewing spare change cycling ads</p><br/>

<c:set var="bikes" value="${ads.sccBikes }"/>

<c:if test="${fn:length(bikes) eq 0}">
	<br/><br/>
	<p style="text-align: center;">No results to display.  Try broadening your search.</p>
	<br/><br/>
</c:if>

<c:forEach items="${bikes}" var="ad">


	<div class="ad">
	
		<img src="${ad.thumbs[0].href}" class="prettyThumb" style="float: left;margin-right:10px;" /> 
		<span class="title">
			<stripes:link beanclass="com.sparechangecycling.web.actions.AdDetailActionBean" event="singleSccDetail" style="height:110px;" class="href">
			<stripes:param name="id" value="${ad.id}" />
						<c:out value="${ad.firstTitle}"/>
						<c:out value="${ad.secondTitle}"/>
			</stripes:link> 
		</span> 
		<p class="price">
			<fmt:formatNumber value="${ad.price}" type="currency" /> 
		</p> 
		<div class="detail">
			<c:out value="${ad.description}"/>
		</div> 
		<div style="float:left;clear:left;margin-top:.5em;">
			-posted by ${ad.user.username} <fmt:formatDate value="${ad.date}" pattern=" hh:mm aa MM/dd/yyyy"/>
		</div>
	</div>
	<div style="height: 1em;margin-top:1em;border-top:1px solid #cccccc;margin:1em 100px 0px 100px"></div>
</c:forEach>