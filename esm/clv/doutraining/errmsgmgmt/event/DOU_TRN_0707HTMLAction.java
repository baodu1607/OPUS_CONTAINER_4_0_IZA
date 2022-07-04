/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0707HTMLAction.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.27
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.27 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.event;

import javax.servlet.http.HttpServletRequest;
import com.clt.framework.core.controller.html.HTMLActionException;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.support.controller.HTMLActionSupport;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.component.util.JSPUtil;

import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * HTTP Parser<br>
 * - com.clt.apps.opus.esm.clv.doutraining 화면을 통해 서버로 전송되는 HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
 * - Parsing 한 정보를 Event로 변환, request에 담아 DouTrainingSC로 실행요청<br>
 * - DouTrainingSC에서 View(JSP)로 실행결과를 전송하는 EventResponse를 request에 셋팅<br>
 * @author Bao
 * @see DouTrainingEvent 참조
 * @since J2EE 1.6
 */

/**
 * HTTP Parser<br>
 * - Phân tích giá trị của đối tượng HTML DOM được gửi đến máy chủ 
 * thông qua com.clt.apps.opus.esm.clv.doutraining dưới dạng một biến Java (một Object Java).
 * Có thể coi là cast value.<br>
 * - Chuyển đổi thông tin được phân tích cú pháp thành một sự kiện, đưa nó vào request và 
 * yêu cầu thực thi với DouTrainingSC<br>
 * - EventResponse gửi kết quả thực thi từ DouTrainingSC tới View (JSP) được đặt trong request.<br>
 * @author Bao Du
 * @see DounTrainingEvent
 * @since J2EE 1.6
 */

public class DOU_TRN_0707HTMLAction extends HTMLActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * DOU_TRN_0707HTMLAction 객체를 생성
	 */
	public DOU_TRN_0707HTMLAction() {}

	/**
	 * HTML DOM 객체의 Value를 자바 변수로 Parsing<br>
	 * HttpRequst의 정보를 DouTrainingEvent로 파싱하여 request에 셋팅<br>
	 * @param request HttpServletRequest HttpRequest
	 * @return Event Event interface를 구현한 객체
	 * @exception HTMLActionException
	 */
	public Event perform(HttpServletRequest request) throws HTMLActionException {
		
    	FormCommand command = FormCommand.fromRequest(request); //framework support : receive req from client
		/* Declare for mapping
		 * <event-class>com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.event.DouTrn0707Event</event-class>
		 */
    	DouTrn0707Event event = new DouTrn0707Event(); 
		
		if(command.isCommand(FormCommand.MULTI)) { //like req.isMULTI()
			//based on Action we create Event equivalent.
			//return array of class ErrMsgVO. Why array of class ErrMsgVO? Because MULTI include update, remove, insert.
			event.setErrMsgVOS((ErrMsgVO[])getVOs(request, ErrMsgVO .class,""));
		}
		else if(command.isCommand(FormCommand.SEARCH)) {
			//get data input from clients to retrieve data
			ErrMsgVO errMsgVO = new ErrMsgVO();
			errMsgVO.setErrMsgCd(JSPUtil.getParameter(request, "s_err_msg_cd", ""));
			errMsgVO.setErrMsg(JSPUtil.getParameter(request, "s_err_msg", ""));
			event.setErrMsgVO(errMsgVO);
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