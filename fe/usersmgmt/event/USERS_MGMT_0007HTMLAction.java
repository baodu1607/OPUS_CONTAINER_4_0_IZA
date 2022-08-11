package com.clt.apps.opus.esm.clv.usersmgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clv.usersmgmt.vo.UsersManagementVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;

public class USERS_MGMT_0007HTMLAction extends HTMLActionSupport {
	private static final long serialVersionUID = 1L;
	
	public USERS_MGMT_0007HTMLAction() {}

	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		UsersMgmt0007Event event = new UsersMgmt0007Event();
		
		if(command.isCommand(FormCommand.MULTI)){
			event.setUserVOs((UsersManagementVO[])getVOs(request, UsersManagementVO .class, ""));
			
		} else if (command.isCommand(FormCommand.SEARCH)){
			UsersManagementVO userVO = new UsersManagementVO();
			userVO.setUserid(JSPUtil.getParameter(request, "s_user_id", ""));
			userVO.setUsername(JSPUtil.getParameter(request, "s_user_name", ""));
			userVO.setActive(JSPUtil.getParameter(request, "s_active", ""));
			event.setUserVO(userVO);
		}
		
		return event;
	}
	
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}
	
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
	
}
