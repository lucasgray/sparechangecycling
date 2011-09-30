<%@ include file="includes/taglibs.jsp"%>

<html>
  <head>
    <title>Register to Sell and Interact</title>
    <meta name="description" content="Register to post ads and exchange messages with other users.  It's free and easy!"/>    
    <script type='text/javascript'>
    
    	$().ready(function(event) { 
    		$("#registerForm").validate();
    		$(".largeElement :text, .largeElement :password").focus(function() {
    			$(this).css('backgroundColor','#F3F3F3');
    		});
    		$(".largeElement :text, .largeElement :password").blur(function() {
    			$(this).css('backgroundColor','#FFFFFF');
    		});
    	});
    	
    	
    </script>
  </head>
  
  <body>
    <stripes:errors/>
    
    <div class="largeElement rounded" style="float:left" id="register">
	    <stripes:form beanclass="com.sparechangecycling.web.actions.LoginActionBean" id="registerForm">
	    	<h1>Register</h1><br/>
	    	<stripes:hidden name="stopBot"/>
	    	<label for="username" class="reg">Username</label><stripes:text name="username" class="text required"/><br/>
	    	<label for="email" class="reg">Email</label><stripes:text name="email" class="text required"/><br/>
			<label for="pass" class="reg">Pass</label><stripes:password name="pass" class="text required"/><br/>
			<label for="zip" class="reg">Zip Code</label><stripes:text name="zip" class="text"/><br/><br/>
			<stripes:submit name="register" value="register" class="button"/>
	    </stripes:form>
    </div>
    <div style="clear:both;"></div>        
        
  </body>
</html>
