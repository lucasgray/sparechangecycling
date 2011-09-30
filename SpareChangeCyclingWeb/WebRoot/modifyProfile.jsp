<%@ include file="includes/taglibs.jsp"%>

<html>
  <head>
    <title>Create or modify your profile info!</title>    
  </head>
  
  <body>
    <stripes:errors/>
    
    <div class="largeElement rounded" style="float:left" id="register">
	    <stripes:form beanclass="com.sparechangecycling.web.actions.ProfileActionBean">
	    	<h1>Profile</h1><br/>
	    	<label for="profile.shopName" class="reg">Business name</label><stripes:text name="profile.shopName" class="text"/><br/>
	    	<label for="profile.addrOne" class="reg">Address 1</label><stripes:text name="profile.addrOne" class="text"/><br/>
			<label for="profile.addrTwo" class="reg">Address 2</label><stripes:password name="profile.addrTwo" class="text"/><br/>
			<label for="profile.city" class="reg">City</label><stripes:text name="profile.city" class="text"/><br/><br/>
			<label for="profile.blurb" class="reg">Blurb</label><stripes:text name="profile.blurb" class="text"/><br/><br/>
			<stripes:submit name="modify" value="modify" class="button"/>
	    </stripes:form>
    </div>
    <div style="clear:both;"></div>        
        
  </body>
</html>
