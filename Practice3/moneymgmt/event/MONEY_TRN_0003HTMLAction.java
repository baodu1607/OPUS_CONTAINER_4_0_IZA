package com.clt.apps.opus.esm.clv.practice3.moneymgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.ConditionVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;


public class MONEY_TRN_0003HTMLAction extends HTMLActionSupport{
	private static final long serialVersionUID = 1L;
	
	public MONEY_TRN_0003HTMLAction() {}
	
	
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		MoneyTrn0003Event event = new MoneyTrn0003Event();
		
		if(command.isCommand(FormCommand.SEARCH02)) {
			SummaryVO summaryVO = new SummaryVO();
			summaryVO.setJoCrrCd(JSPUtil.getParameter(request, "s_partner_code",""));
			event.setSummaryVO(summaryVO);
		}else if(command.isCommand(FormCommand.SEARCH03)) {
			SummaryVO summaryVO = new SummaryVO();
			summaryVO.setJoCrrCd(JSPUtil.getParameter(request, "s_partner_code",""));
			summaryVO.setRlaneCd(JSPUtil.getParameter(request, "s_lane_code",""));
			event.setSummaryVO(summaryVO);
		}else if(command.isCommand(FormCommand.SEARCH)) {
			event.setConditionVO((ConditionVO)getVO(request, ConditionVO.class));
		}else if(command.isCommand(FormCommand.SEARCH01)) {
			event.setConditionVO((ConditionVO)getVO(request,ConditionVO.class));
		}
		
		return event;
	}
	
	/**
	 * Storing the business scenario execution result value in the attribute of HttpRequest<br>
	 * Setting the ResultSet that transmits the execution result from ServiceCommand to View (JSP) in the request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse An object that implements the EventResponse interface.
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * Saving the HttpRequest parsing result value in the HttpRequest attribute<br>
	 * HttpRequest parsing result value and set in request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param Event An object that implements the Event interface.
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}

}
