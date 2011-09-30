<%@ include file="includes/taglibs.jsp"%>

<html>
  <head>
    <title>Login to Post an Ad</title>
    <meta name="description" content="Login or register to post ads and exchange messages with other users.  It's free and easy!"/>
    <script type="text/javascript">
    	$().ready(function(event) {
    		$("#register").hide();
    		
    		$("#registerPrompt").click(function(event) {
    			event.preventDefault();
    			$("#loginContainer").hide();
    			$("#registerPromptContainer").hide();
    			$("#register").fadeIn();
    		});
    		
    		
    		$("#registerform").validate();
    		$("#loginform").validate();
    	});
    </script>
    
  </head>
  
  <body>
    <stripes:errors/>
    
    <div class="largeElement rounded" style="float:left" id="register">
	     <stripes:form id="registerform" beanclass="com.sparechangecycling.web.actions.LoginActionBean">
	    	<h1>Register</h1><br/>
	    	<stripes:hidden name="stopBot"/>
	    	<label for="username" class="reg">Username</label><stripes:text name="username" class="text required"/><br/>
	    	<label for="email" class="reg">Email</label><stripes:text name="email" class="text required email"/><br/>
			<label for="pass" class="reg">Pass</label><stripes:password name="pass" class="text required"/><br/>
			<label for="zip" class="reg">Zip Code</label><stripes:text name="zip" class="text"/><br/><br/>
			<stripes:submit name="register" value="register" class="button"/>
	    </stripes:form>
    </div>
    
    <div class="smallElement rounded" style="float:left;" id="loginContainer">
	    <h3>Log in to control your account and post ads!</h3><br/>
	    <stripes:form id="loginform" beanclass="com.sparechangecycling.web.actions.LoginActionBean">
	    	<label for="username" class="reg">Username</label><stripes:text name="username" class="text required"/><br/>
			<label for="pass" class="reg">Pass</label><stripes:password name="pass" class="text required"/><br/>
			<stripes:hidden name="stopBot"/><br/>
			<stripes:submit name="login" value="login" class="button"/>
	    </stripes:form>
	    <div>
			<stripes:link beanclass="com.sparechangecycling.web.actions.LoginActionBean" event="forgot-password">forgot password?</stripes:link>    
    	</div>
    </div>
    
	<div class="smallElement rounded" style="float:left;margin-left:2em;" id="registerPromptContainer">
	    Registration is free, and only takes a moment.<br/><br/>
	    <a href="" class="button nodec" id="registerPrompt">register</a>
    </div>
    <div style="clear:both;"></div>    
 
    
  </body>
</html>
