<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="includes/taglibs.jsp" %>


<html>
	<head>
		<meta name="decorator" content="home"/>
		<meta name="description" content="Spare Change Cycling is a place to buy and sell used bikes, bike parts, and bike acccessories."/>
		<meta name="google-site-verification" content="Ll1bwNSMYDDjBiw0C-IPbu8lC_GJbAPllvnKvjSwYsc" />
		<link rel="stylesheet" type="text/css" href="styles/jquery.facebox.css">
	
		<script type="text/javascript" src="scripts/jquery.facebox.js"></script>
		
		<script>
		jQuery(document).ready(function($) {
	  		$('a[rel*=facebox]').facebox();
		});
		</script>
	</head>
	<body>

				
			<div style="margin: 0 auto;width:90%;border:3px solid #444444;background: url(images/helmet.png) 95% 90% no-repeat" >
			<div class="smallFloatingElement" style="color:#444444;">
				<h3>Spare Change Cycling is...</h3>
			</div>
			<div class="smallFloatingElement" style="color:#444444;background-color:transparent">
			<p>...a free, community-oriented site where biking enthusiasts can buy and sell their wares</p><br/>
			<p>...a friendly place to share stories, tips and tricks, and learn</p><br/>
			<p>...an opportunity for small bike shops to reach a broader audience</p><br/>
			<p><a href="">learn more (coming soon)</a></p>
			</div>
			</div>	

					
			<div style="height: 1.5em;clear:both;">&nbsp;</div>
			<div style="clear:both;width:90%;min-width: 700px;margin: 0 auto">
			
			
			
				<div style="color:white;background-color:#444444;margin: 0 auto; padding: 10px" class="corner">

				
				
					<div style="float:left;margin-right:2.5%;width:47%">
					<div class="smallFloatingElement corner" style="margin-bottom:2em;color:#444444;">
						<h3>Latest Articles</h3>
					</div>
					<div class="smallFloatingElement corner" style="color:#444444;padding:10px">
						<stripes:useActionBean beanclass="com.sparechangecycling.web.actions.CommunityActionBean" event="populateArticles" var="ab" />
				    	<c:forEach items="${ab.articles}" var="article">
					    	<div class="article">
						    	<h2>${article.title}</h2><br/>
						    	<h3>written by ${article.author}</h3><br/>
				    			<p>${article.summary}</p><br/>
			    				<stripes:link beanclass="com.sparechangecycling.web.actions.CommunityActionBean" event="viewSingleArticle" class="href">
			    				<stripes:param name="id" value="${article.id}"/>
			    				[read more]
			    				</stripes:link>
	    						<div style="margin:15px;border-top: 1px solid #cccccc"></div>
	    					</div>
	    				</c:forEach>
					</div>
					</div>
					
					<div style="float:left;width:47%">
					<div class="smallFloatingElement corner" style="margin-bottom:2em;color:#444444">
						<h3>Latest Ads</h3>
					</div>
					<div class="smallFloatingElement corner" style="color:#444444;padding:10px">
						<stripes:useActionBean beanclass="com.sparechangecycling.web.actions.SearchBikesActionBean" var="sbab" />
						<c:set var="bikes" value="${sbab.latestAds}"/>
				    	<c:forEach items="${bikes}" var="ad">
					    	<div class="ad">
	
								<img src="${ad.thumbs[0].href}" class="prettyThumb" style="float: left;margin-right:4px;" />
								<br style="clear:both"/> 
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
							</div>
							<div style="float:left;clear:left;">
								posted by 
								<c:choose>
								<c:when test="${not empty sessionScope.user}">
								    <stripes:link beanclass="com.sparechangecycling.web.actions.UserActionBean" rel="facebox" event="popUpMsgDialog">
								    	<stripes:param name="recipient" value="${ad.user.id}"/>
								    	<stripes:param name="usrName" value="${ad.user.username}"/>
								    	${ad.user.username}
								    </stripes:link>
							    </c:when>
							    <c:otherwise>
							    	${ad.user.username}
							    </c:otherwise>
							    </c:choose>
								 on <fmt:formatDate value="${ad.date}" dateStyle="LONG"/>
							</div>
							<div style="height: 2em; clear:both;">&nbsp;</div>
	    				</c:forEach>
					</div>
					</div>
										
					<div style="clear:both"></div>
				</div>
				
	  				
		</div>
	</body>
</html>


  

    
