<%@ include file="includes/taglibs.jsp"%>

<html>
  <head>
    <title>Upgrade for additional active ads</title>    
  </head>
  
  <body>
    <stripes:errors/>
    
    <div class="largeElement rounded" style="float:left" id="register">
	    <p>Upgrade in order to list additional ads!  It's easy.  Simply click the button and you're halfway there.  
	    Otherwise, head back to your accounts to delete a few active ads.</p>
	    <br/><br/>
	    <h1 style="">Why upgrade?</h1>
	    <br/>
		<div style="margin-left: 2em;">
		 	<div style="float:left">
			    <h2>Small bike shop?</h2>
			    <ul >
			    	<li>Save an ad template for easy listing of all your bikes!<a href="">See sample</a></li>
			    	<li>Make your ads stand out with color templates!</li>
			    	<li>Get a free profile page to link directly to you! <a href="">See sample</a></li>
			    </ul>
		    </div>
		    <div style="float:left;margin-left: 2em;">
			    <h2>Hobbyist?</h2>
			    <ul style="float:left">
			    	<li>Blah</li>
			    	<li>Blah</li>
			    	<li>Blah</li>
			    </ul>
		    </div>
	    </div>
	    <br style="clear:both;"/><br/> 
	    <ul >
		    	<li class="horiz-list">
		    		<div class="upgrade-list-items corner">
		    		1 month: $10
		    		<form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
					<input type="hidden" name="cmd" value="_xclick-subscriptions">
					<input type="hidden" name="business" value="seller_1251767536_biz@gmail.com">
					<input type="hidden" name="lc" value="US">
					<input type="hidden" name="item_name" value="Subscription to Spare Change Cycling">
					<input type="hidden" name="no_note" value="1">
					<input type="hidden" name="a3" value="10.00">
					<input type="hidden" name="currency_code" value="USD">
					<input type="hidden" name="src" value="1">
					<input type="hidden" name="p3" value="1">
					<input type="hidden" name="t3" value="M">
					<input type="hidden" name="sra" value="1">
					
					<input type="hidden" name="custom" value="${sessionScope.user.username }"/>
					 
					<input type="hidden" name="bn" value="PP-SubscriptionsBF:btn_subscribeCC_LG.gif:NonHostedGuest">
					<input type="image" src="https://www.paypal.com/en_US/i/btn/btn_subscribeCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
					<img alt="" border="0" src="https://www.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">
					</form>
							    		
		    		
		    		</div>
		    	</li>
		    	<li class="horiz-list"><div class="upgrade-list-items corner">
		    		3 month: $25
		    		<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
					<input type="hidden" name="cmd" value="_s-xclick">
					<input type="hidden" name="hosted_button_id" value="7705866">
					<input type="image" src="https://www.paypal.com/en_US/i/btn/btn_subscribeCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
					<img alt="" border="0" src="https://www.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">
					</form>
		    		
		    		</div>
		    	</li>
		    	<li class="horiz-list"><div class="upgrade-list-items corner">
		    		1 year: $60 Great value!
		    		
		    		<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
					<input type="hidden" name="cmd" value="_xclick-subscriptions">
					<input type="hidden" name="business" value="lucas.e.gray@gmail.com">
					<input type="hidden" name="lc" value="US">
					<input type="hidden" name="item_name" value="Subscription to Spare Change Cycling">
					<input type="hidden" name="no_note" value="1">
					<input type="hidden" name="a3" value="60.00">
					<input type="hidden" name="currency_code" value="USD">
					<input type="hidden" name="src" value="1">
					<input type="hidden" name="p3" value="1">
					<input type="hidden" name="t3" value="Y">
					<input type="hidden" name="sra" value="1">
					<input type="hidden" name="bn" value="PP-SubscriptionsBF:btn_subscribeCC_LG.gif:NonHostedGuest">
					<input type="image" src="https://www.paypal.com/en_US/i/btn/btn_subscribeCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
					<img alt="" border="0" src="https://www.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">
					</form>
		    		
		    		</div>
		    	</li>
	    </ul>
    </div>
    <div style="clear:both;"></div>        
        
  </body>
</html>
