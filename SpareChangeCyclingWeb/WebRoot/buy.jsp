<%@ include file="includes/taglibs.jsp"%>
<stripes:useActionBean beanclass="com.sparechangecycling.web.actions.SearchBikesActionBean" var="ads" />
<html>
	<head>
		<title>Search Used Bike Ads, Parts, and Accessories</title>
		<meta name="description" content="Browse user-generated ads for bike and bike parts.  Craigslist and ebay ads included."/>
		<script type="text/javascript" src="scripts/sccBuyScripts.js"></script>
		<script type="text/javascript">
		$.ajaxSetup ({
		    // Disable caching of AJAX responses */
		    cache: false
		});
				
		
		var _scc = "buyJsps/scc.jsp";
		var _craigs = "buyJsps/craigs.jsp";
		var _ebay = "buyJsps/ebay.jsp";
		
		function pageload(hash) {
			//show loading img regardless
			$("#ads-container").html("<div style='line-height:4em;'>&nbsp;</div><div style='text-align:center;'><img src='images/ajax-loader.gif' style='border:0px;background-color:white;'/></div><div style='line-height:4em;'>&nbsp;</div>");
		
			// hash doesn't contain the first # character.
			if(hash) {
				$(".tab").addClass("inactive");
				
				$("#tab-"+hash).addClass("active");
				$("#tab-"+hash).removeClass("inactive");
			
				// restore ajax loaded state
				if (hash=="craigs") {
					$("#ads-container").load(_craigs);
				} else if (hash=="scc") {
					$("#ads-container").load(_scc);
				} else if (hash=="ebay") {
					$("#ads-container").load(_ebay);
				}
			} else {
				// start page
				$("#tab-scc").toggleClass("active");
				$("#tab-scc").toggleClass("inactive");
				$("#ads-container").load(_scc);
			}
		}
			
		
  		$(document).ready(function() {
  			$.historyInit(pageload);
  			
  			$(".tab").click(function(event) {
  				$(this).children("a").click();
  			});
  			
  			//$("#date").datepicker({ clearText: 'Erase' });

			$("a[rel='history']").click(function(){
				var hash = this.href;
				hash = hash.replace(/^.*#/, '');
				// moves to a new page.
				// pageload is called at once.
				$.historyLoad(hash);
				
				//$(".tab").addClass("inactive");
				
				//$("#"+$(this).parent().attr("id")).addClass("active");
				//$("#"+$(this).parent().attr("id")).removeClass("inactive");				
				
				return false;			
			});

		});
		
		
  		</script>
	</head>
	<body>
		<table>
			<tr>
				<td style="vertical-align: top;padding-top:2.75em">
					<div id="left-search-container" class="rounded">
						<c:import url="/snippets/searchBikes.jsp"/>
					</div>
					<br />
				</td>
				<td style="width: 80%; vertical-align: top;">
					<div id="ads-outer-container">
						
							<div id="tab-container">
							<span id="tab-scc" class="inactive tab"><a class="nodec" rel="history" href="used-bikes/buy-bikes/begin.action#scc"><img src="images/scc_ico.png"/>SCC bikes</a></span>
							<span id="tab-ebay" class="inactive tab"><a class="nodec" rel="history" href="used-bikes/buy-bikes/begin.action#ebay"><img src="images/ebay.png"/>Ebay bikes</a></span>
							<span id="tab-craigs" class="inactive tab"><a class="nodec" rel="history" href="used-bikes/buy-bikes/begin.action#craigs"><img src="images/craigs.png" />Craigs bikes</a></span>
							</div>
						<div style="clear: both;" id="ads-container" class="rounded Element">

							<%-- ads will go here, thanks javascript --%>		
									
						</div>
					</div>
				</td>
			</tr>

		</table>
		
	</body>
</html>

