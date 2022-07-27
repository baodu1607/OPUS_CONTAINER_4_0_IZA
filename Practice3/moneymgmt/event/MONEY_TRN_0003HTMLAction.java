/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : MONEY_TRN_0003HTMLAction.java
*@FileTitle : Money Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.24
*@LastModifier : BaoDu
*@LastVersion : 1.0
* 2022.07.19 
* 1.0 Creation
=========================================================*/

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

/**
 * HTTP Parser<br>
 * - Parsing the value of the HTML DOM object sent to the server through the com.clt.apps.opus.esm.clv.doutraining.moneymgmt screen as a Java variable<br>
 * - Parsing information is converted into an event, put in a request, and requested to be executed by MoneyMgmtSC<br>
 * - EventResponse that transmits execution result from MoneyMgmtSC to View (JSP) is set in the request<br>
 * 
 * @author BaoDu
 * @see MoneyTrn0003Event 참조
 * @since J2EE 1.6
 */
public class MONEY_TRN_0003HTMLAction extends HTMLActionSupport{
	private static final long serialVersionUID = 1L;
	
	public MONEY_TRN_0003HTMLAction() {}
	
	/**
	 * Parsing the HTLM DOM object's value as a Java variable<br>
	 * Parsing teh information of HttpRequest as MoneyTrn0003Event and setting it in the request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event object that implements the interface
	 * @exception HTMLActionException
	 */
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
