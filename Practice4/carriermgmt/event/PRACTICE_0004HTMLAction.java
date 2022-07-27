/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE_0004HTMLAction.java
*@FileTitle : PRACTICE4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.12
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.12 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.carriermgmt.event;

import javax.servlet.http.HttpServletRequest;

import com.clt.framework.component.util.JSPUtil;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierListVO;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.practice4 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 Practice4SC로 실행요청<br>
 * - Practice4SC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Bao
 * @see Practice4Event 참조
 * @since J2EE 1.6
 */

public class PRACTICE_0004HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * PRACTICE_0004HTMLAction 객체를 생성
	 */
	public PRACTICE_0004HTMLAction() {}

	/**
	 * Parsing the HTML DOM object's value as a Java variable<br>
	 * Parsing the information of HttpRequest as Practice4Event and setting it in the request<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @return Event An object that implements the Event interface
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request);
		Practice0004Event event = new Practice0004Event();
		
		if(command.isCommand(FormCommand.MULTI)) {
			event.setCarrierListVOS((CarrierListVO[])getVOs(request, CarrierListVO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			CarrierListVO carrierListVO = new CarrierListVO();
			carrierListVO.setJoCrrCd(JSPUtil.getParameter(request, "s_carrier", "")); //'TRN,OSS'
			carrierListVO.setVndrSeq(JSPUtil.getParameter(request, "s_vndr_seq", ""));
			carrierListVO.setCreDtFr(JSPUtil.getParameter(request, "s_cre_dt_fr", ""));
			carrierListVO.setCreDtTo(JSPUtil.getParameter(request, "s_cre_dt_to", ""));
			
			event.setCarrierListVO(carrierListVO);
		}else if(command.isCommand(FormCommand.SEARCH01)) {
			CarrierListVO carrierListVO = new CarrierListVO();
			carrierListVO.setCustCntCd(JSPUtil.getParameter(request, "s_cust_cnt_cd",""));
			carrierListVO.setCustSeq(JSPUtil.getParameter(request, "s_cust_seq",""));
			
			event.setCarrierListVO(carrierListVO);
		}

		return  event;
	}

	/**
	 * HttpRequest의 attribute에 업무시나리오 수행결과 값 저장<br>
	 * ServiceCommand에서 View(JSP)로 실행결과를 전송하는 ResultSet을 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param eventResponse EventResponse interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, EventResponse eventResponse) {
		request.setAttribute("EventResponse", eventResponse);
	}

	/**
	 * HttpRequest의 attribute에 HttpRequest 파싱 수행결과 값 저장<br>
	 * HttpRequest 파싱 수행결과 값 request에 셋팅<br>
	 * 
	 * @param request HttpServletRequest HttpRequest
	 * @param event Event interface를 구현한 객체
	 */
	public void doEnd(HttpServletRequest request, Event event) {
		request.setAttribute("Event", event);
	}
}