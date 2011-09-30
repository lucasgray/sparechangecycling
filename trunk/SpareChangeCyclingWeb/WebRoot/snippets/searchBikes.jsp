<%@ include file="../includes/taglibs.jsp"%>
<script type="text/javascript">
	
	function filterAdType() {
		$("#type").children("[value=MISC]").remove();
		$("#type").children("[value=AD]").remove();
		$("#type").children("[value=UNKNOWN]").remove();
	}
	
	$(document).ready(function(event) {
		filterAdType();
	
	
		$("select[multiple]").asmSelect({animate: true});
		if ($("#asmList0").children().size() == 0) {
			$("#part-dropdown").hide();
		} else {
			$("#bike-dropdown").hide();
		}
		
		$(".bike-search").click(function(event) {
			event.preventDefault();
			
			$("#part-dropdown").hide();
			$("#bike-dropdown").fadeIn();
		});
		$(".part-search").click(function(event) {
			event.preventDefault();
			
			$("#bike-dropdown").hide();
			$("#part-dropdown").fadeIn();
		});
		
		
		$("#search-form").submit(function(event) {
			//server only wants one!		
			if ($("#bike-dropdown").css('display')=='none'){
				$("#bike-dropdown").remove();
			} else {
				$("#part-dropdown").remove();	
			}	
		});
		
		if ("${user.zip}" != "") {
			$("#zip").val('${user.zip}');
		}
		
		$("#search-form").validate();
		$.validator.messages['digits']="<img src='images/delete_16.png' style='float:right'/>";
		$.validator.messages['number']="<img src='images/delete_16.png' style='float:right'/>";
	});
</script>
<stripes:useActionBean  beanclass="com.sparechangecycling.web.actions.SearchBikesActionBean" var="_sb"/>
<stripes:form
	beanclass="com.sparechangecycling.web.actions.SearchBikesActionBean" id="search-form">
	
	<h3>Search Preferences</h3><br/>
	<%--<div style="padding:10px"><span class="button nodec bike-search">bikes</span>&nbsp;<span class="button nodec part-search">parts</span></div> --%>
	
	<span>Type of ad</span>
	<div id="dropdown">
		<stripes:select multiple="true" name="searchPreferences.type" id="type">
		<stripes:options-enumeration
			enum="com.sparechangecycling.pojos.AdType" />
		</stripes:select>
	</div>
	<%--<stripes:label for="date">Date: </stripes:label>
	<stripes:text name="preferences.strDate" id="date" size="10" />
	<br />--%>
	<stripes:label for="zip" style="float:left;clear:both">Zip code </stripes:label>
	<stripes:text class="digits text" name="searchPreferences.zip" id="zip" maxlength="5" size="4" style="float:right;"/>
	<br />
	<stripes:label for="distance" style="float:left;clear:both">Distance [mi]</stripes:label>
	<stripes:text class="digits text" name="searchPreferences.distance" id="distance" size="4" style="float:right;"/>
	<br />
	<stripes:label for="lowPrice" style="float:left;clear:both">Low end </stripes:label>
	<stripes:text class="number text" name="searchPreferences.lowPrice" id="lowPrice" size="4" style="float:right;"/>
	<br />
	<stripes:label for="highPrice" style="float:left;clear:both">High end </stripes:label>
	<stripes:text class="number text" name="searchPreferences.highPrice" id="highPrice" size="4" style="float:right;"/>
	<br />
	<stripes:label for="textMatch" style="float:left;clear:both">Text search </stripes:label><br/>
	<stripes:text class="text" name="searchPreferences.textMatch" id="textMatch" size="20"/>
	<br />
	<br />
	<br />

<!--[if lt IE 8]>

<style>

/* IE6 */

*html input.button{

overflow: visible; /* remove padding from left/right */

width:0; /*remove the remaining space in IE6*/

}

 

/* IE7 */

*:first-child+html input.button{

overflow: visible; /* remove padding from left/right */

width:auto !important;

}

</style>

<![endif]-->
	<stripes:submit class="button" name="changeStuff" id="changeStuff" value="modify search" style="float:left"/>
	<stripes:submit class="button" name="clear" id="clear" value="clear" style="float:left;margin-left:.5em"/>
	<br />
	<br />
</stripes:form>