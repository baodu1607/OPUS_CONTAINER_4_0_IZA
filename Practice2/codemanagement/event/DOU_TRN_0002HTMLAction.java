/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0002HTMLAction.java
*@FileTitle : Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.codemanagement.event;

import javax.servlet.http.HttpServletRequest;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.component.util.JSPUtil;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtDetailVO;

/**
 * HTTP Parser<br>
 * - Parse the value of the HTML DOM that sent to the server through
 * com.clt.apps.opus.esm.clv.doutraining as a Java variable (Java Object).
 * Can be considered as cast value.<br>
 * - Convert the "parsed" information into an event, put it in the request and
 * send to DouTrainingSC<br>
 * - EventResponse send execution results from DouTrainingSC to View (JSP) that placed in request.<br>
 * @author Bao Du
 * @see DounTrainingEvent
 * @since J2EE 1.6
 */
public class DOU_TRN_0002HTMLAction extends HTMLActionSupport {
	private static final long serialVersionUID = 1L;
	public DOU_TRN_0002HTMLAction() {}
	
	/**
	 * Parse the request in Java readable, convert it to event, put it in the request and
	 * send to SC<br>
	 * 
	 * @param request HttpServletRequest An request that receive from client side
	 * @return event DouTrn0002Event
	 * @exception HTMLActionException 
	 */
	@Override
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		FormCommand command = FormCommand.fromRequest(request);
		DouTrn0002Event event = new DouTrn0002Event();
				
		if(command.isCommand(FormCommand.SEARCH)){//Searching for the master table
			CodeMgmtMasterVO masterVO = new CodeMgmtMasterVO();
			masterVO.setIntgCdId(JSPUtil.getParameter(request, "s_intg_cd_id",""));
			masterVO.setOwnrSubSysCd(JSPUtil.getParameter(request, "s_ownr_sub_sys_cd",""));			
			event.setCodeMgmtMasterVo(masterVO);			
		}else if(command.isCommand(FormCommand.SEARCH01)){ //Searching for the detail table
			CodeMgmtDetailVO detailVO = new CodeMgmtDetailVO();
			detailVO.setIntgCdId(JSPUtil.getParameter(request, "s_intg_cd_id",""));			
			event.setCodeMgmtDetailVo(detailVO);			
		}else if(command.isCommand(FormCommand.MULTI)){
			event.setCodeMgmtMasterVos((CodeMgmtMasterVO[])getVOs(request, CodeMgmtMasterVO.class,""));		
		}else if(command.isCommand(FormCommand.MULTI01)){
			event.setCodeMgmtDetailVos((CodeMgmtDetailVO[])getVOs(request, CodeMgmtDetailVO.class,""));
		
		}
		
		return event;
	}
	
	/**
	 * Saving the business scenario execution result value in the attribute of HttpRequest<br>
	 * Setting the ResultSet that transmits the execution result from ServiceCommand to View(JSP)
	 * in the request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}
	
	/**
	 * Save the HttpRequest parsing result in HttpRequest attribute<br>
	 * HttpRequest parsing result value set in request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event An object that implements the Event interface
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
	
}
