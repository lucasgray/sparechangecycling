<%@ include file="includes/taglibs.jsp"%>

<html>
  <head>
    <title>Forgotten Password</title>
    <meta name="description" content="Reset your password."/>    
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
	    	<h1>Reset Password</h1><br/>
	    	<stripes:hidden name="stopBot"/>
	    	<label for="email" class="reg">Email</label><stripes:text name="email" class="text required"/><br/>
			<stripes:submit name="reset-password" value="reset" class="button"/>
	    </stripes:form>
    </div>
    <div style="clear:both;"></div>        
        
  </body>
</html>
