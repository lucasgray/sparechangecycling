package com.sparechangecycling.web.actions;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.SimpleError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sparechangecycling.web.util.SccActionBeanContext;

public class BaseActionBean implements ActionBean{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private SccActionBeanContext context;
	
	private String stopBot;
	
	@Before(stages = LifecycleStage.BindingAndValidation)
	public void setHasMsgs() {
		
		if (getContext().getUserCookie()!= null) {
			logger.info("going to set msgs");
			getContext().setHasUnreadMessages();
		}
		
	}
	
	public String getSourcePage() {
		return (String)getContext().getRequest().getSession().getAttribute("source_for_login");
	}

	public void setSourcePage(String sourcePage) {
		getContext().getRequest().getSession().setAttribute("source_for_login",sourcePage);
	}
	
    /** Interface method from ActionBean. */
    public void setContext(ActionBeanContext context) {
        this.context = (SccActionBeanContext) context;
    }

    /** Interface method from ActionBean, using a co-variant return type! */
    public SccActionBeanContext getContext() {
        return this.context;
    }
    
	public String getStopBot() {
		return stopBot;
	}

	public void setStopBot(String stopBot) {
		this.stopBot = stopBot;
	}
	
	public void checkForBot() {
		if (this.stopBot != null) {
			context.getValidationErrors().addGlobalError(new SimpleError("no thanks, you're a machine."));
		}
	}
}
