<%@ include file="includes/taglibs.jsp" %>
<html>
  <head>
    <title>Edit a single ad</title>
    <script type="text/javascript">
        $().ready(function(event){
    		$("#editSingleAd").validate();
    		
    	});
    </script>
  </head>
  <stripes:useActionBean beanclass="com.sparechangecycling.web.actions.BaseActionBean" var="_bab"/>
  <body>
    <div class="largeElement">
    
    	<h1>Editing ad: 
    	<c:if test="${isBike}">
    		${singleAd.year}
    	</c:if>	
    	 ${singleAd.firstTitle} ${singleAd.secondTitle } 
    	</h1>
     	<stripes:form beanclass="com.sparechangecycling.web.actions.AdDetailActionBean" id="editSingleAd">
     	<stripes:useActionBean beanclass="com.sparechangecycling.web.actions.AdDetailActionBean" var="_ad"/>
    		<stripes:hidden name="stopBot"/>
    		<stripes:hidden name="sccBikeAd.id"/>
    		<c:choose>
	    		<c:when test="${isBike}">
					<stripes:label for="intermediateAdType" class="reg">Bike category</stripes:label>
					<stripes:select name="intermediateAdType" >
					<stripes:options-collection
						collection='${_bab.context.bikeTypes}' />
					</stripes:select><br/>
					<stripes:label for="sccBikeAd.year" class="reg">Year</stripes:label>
		    		<stripes:text name="sccBikeAd.year" class="text required" value="${singleAd.year}"/><br/>
		    		<stripes:label for="sccBikeAd.firstTitle" class="reg">Make</stripes:label>
		    		<stripes:text name="sccBikeAd.firstTitle" class="text required" value="${singleAd.firstTitle}"/><br/>
		    		<stripes:label for="sccBikeAd.secondTitle" class="reg">Model</stripes:label>
		    		<stripes:text name="sccBikeAd.secondTitle" class="text required" value="${singleAd.secondTitle}"/><br/>
	    		</c:when>
	    		<c:otherwise>
					<stripes:label for="intermediateAdType" class="reg">Part category</stripes:label>
					<stripes:select name="intermediateAdType" class="required">
						<stripes:options-collection
							collection='${_bab.context.partTypes}' />
					</stripes:select><br/>
					<stripes:label for="sccBikeAd.firstTitle" class="reg">Brand</stripes:label>
		    		<stripes:text name="sccBikeAd.firstTitle" class="text required" value="${singleAd.firstTitle}"/><br/>	
	    		</c:otherwise>
    		</c:choose>
    		<stripes:label for="sccBikeAd.price" class="reg">Price</stripes:label>
    		<stripes:text name="sccBikeAd.price" class="text number" value="${singleAd.price}"/><br/>
    		<stripes:label for="sccBikeAd.description" class="reg">Description</stripes:label><br/>
    		<stripes:textarea name="sccBikeAd.description" class="text required" cols="75" rows="5" value="${singleAd.description}"/><br/><br/>
    		<br/><br/>
    		<p>Current picture:</p>
    		<img src="${singleAd.thumbs[0].href}"/><br/>
    		
    		<stripes:label for="pic">Upload a new picture if you'd like</stripes:label><br/>
    		<stripes:file name="pic" class="text"/><br/>
    		
    		<stripes:submit name="mergeAd" value="save changes"/>
    	</stripes:form>		
    </div>
  </body>
</html>
