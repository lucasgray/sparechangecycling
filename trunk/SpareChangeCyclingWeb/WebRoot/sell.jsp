<%@ include file="includes/taglibs.jsp" %>
<html>
  <head>
    <title>Post a Used Bike or Bike Part Ad</title>
    <meta name="description" content="Sell your used bikes and accessories here.  It's easy and free!"/>
    <script type="text/javascript">
    	$().ready(function(event){
    		$(".partsAd").hide();
    		$(".bikeAd").hide();
    		
    		$("#sellBike").click(function(event) {
    			event.preventDefault();
    			$(".partsAd").hide();
    			$(".bikeAd").fadeIn();
    		});
    		$("#sellParts").click(function(event) {
    			event.preventDefault();
    			$(".bikeAd").hide();
    			$(".partsAd").fadeIn();
    		});
    		
    		$("#sellBikeForm").validate();
    		$("#sellPartForm").validate();
    	});
    </script>
    
  </head>
  
  <body>
    <div class="largeElement" style="min-height: 30em;">
    	<h1>Create an ad</h1><br/>
    	<p>Are you selling a full bike or a part/accessory?</p>
    	<div class="buttons"><a href="" id="sellBike" >Full bike</a><a href="" id="sellParts" class="rightmost">Part/accessory</a></div><br/><br/>
    	<stripes:useActionBean beanclass="com.sparechangecycling.web.actions.SellActionBean" var="_sb"/>
     	<stripes:form beanclass="com.sparechangecycling.web.actions.SellActionBean" id="sellBikeForm">
    		<stripes:hidden name="stopBot"/>
    		<div class="bikeAd"><br/><h3>New Bike Ad</h3><br/>
	    		<stripes:label for="intermediateAdType" class="reg">Bike category</stripes:label>
				<stripes:select name="intermediateAdType">
					<stripes:options-collection
						collection='${_sb.context.bikeTypes}' />
				</stripes:select><br/>
				<stripes:label for="sccBikeAd.year" class="reg">Year</stripes:label>
	    		<stripes:text name="sccBikeAd.year" class="text required"/><br/>
	    		<stripes:label for="sccBikeAd.firstTitle" class="reg">Make</stripes:label>
	    		<stripes:text name="sccBikeAd.firstTitle" class="text required"/><br/>
	    		<stripes:label for="sccBikeAd.secondTitle" class="reg">Model</stripes:label>
	    		<stripes:text name="sccBikeAd.secondTitle" class="text required"/><br/>
	    		<stripes:label for="sccBikeAd.price" class="reg">Price</stripes:label>
	    		<stripes:text name="sccBikeAd.price" class="text required number"/><br/><br/><br/>
	    		<stripes:label for="sccBikeAd.description" class="reg">Description</stripes:label><br/>
	    		<stripes:textarea name="sccBikeAd.description" cols="75" rows="5" class="required"/><br/>
	    		
	    		<stripes:label for="pic">Picture</stripes:label>
	    		<stripes:file name="pic" class="text"/><br/>
	    		
	    		<stripes:submit name="createBike" value="create" class="button"/>
    		</div>
    		</stripes:form>
    		
    		<stripes:form beanclass="com.sparechangecycling.web.actions.SellActionBean" id="sellPartForm">
    			<stripes:hidden name="stopBot"/>
	    		<div class="partsAd"><br/><h3>New Parts Ad</h3><br/>
	    		<stripes:label for="intermediateAdType" class="reg">Part category</stripes:label>
				<stripes:select name="intermediateAdType" >
					<stripes:options-collection
						collection='${_sb.context.partTypes}' />
				</stripes:select><br/>
				<stripes:label for="sccBikeAd.firstTitle" class="reg">Brand</stripes:label>
	    		<stripes:text name="sccBikeAd.firstTitle" class="text required"/><br/>
	    		<stripes:label for="sccBikeAd.price" class="reg">Price</stripes:label>
	    		<stripes:text name="sccBikeAd.price" class="text required"/><br/><br/><br/>
	    		<stripes:label for="sccBikeAd.description" class="reg">Description</stripes:label><br/>
	    		<stripes:textarea name="sccBikeAd.description" cols="75" rows="5" class="required"/><br/>
	    		
	    		<stripes:label for="pic">Picture</stripes:label>
	    		<stripes:file name="pic" class="text"/><br/>
	    		
	    		<stripes:submit name="createPartAd" value="create" class="button"/>
    			</div>
    		</stripes:form>
    		
    </div>
  </body>
</html>
