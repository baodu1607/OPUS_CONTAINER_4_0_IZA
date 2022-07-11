/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DouTrainingSC.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.27
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.27 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining;

import java.util.List;

import com.clt.apps.opus.esm.clv.doutraining.codemanagement.basic.CodeManagementBC;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.basic.CodeManagementBCImpl;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.event.DouTrn0002Event;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtDetailVO;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.basic.ErrMsgMgmtBC;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.basic.ErrMsgMgmtBCImpl;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.event.DouTrn0707Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.ErrMsgVO;


/**
 * ALPS-DouTraining Business Logic ServiceCommand - ALPS-DouTraining 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Bao
 * @see ErrMsgMgmtDBDAO
 * @since J2EE 1.6
 */

public class DouTrainingSC extends ServiceCommandSupport {
	// Remember SC is not return event to BC but calling BC to process data in db.
	private SignOnUserAccount account = null;

	/**
	 * DouTraining system preceding work scenario<br>
	 * Creating related internal objects when calling a business scenario<br>
	 */
	public void doStart() {
		log.debug("DouTrainingSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * DouTraining system 업무 시나리오 마감작업<br>
	 * Release related internal objects at the end of the business scenario<br>
	 */
	public void doEnd() {
		log.debug("DouTrainingSC 종료");
	}

	/**
	 * Carry out business scenarios corresponding to each event<br>
	 * ALPS-DouTraining system 업무에서 발생하는 모든 이벤트의 분기처리
	 * (In English) Branch processing of all events occurring in ALPS-DouTraining system work<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO (Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// Which part to use if SC handles multiple events
		// The events are in DouTrn0707Event
		if (e.getEventName().equalsIgnoreCase("DouTrn0707Event")) { 
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchErrMsg(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageErrMsg(e);
			}
		}
		
		// The events are in DouTrn0002Event
		if(e.getEventName().equalsIgnoreCase("DouTrn0002Event")){
			if(e.getFormCommand().isCommand(FormCommand.SEARCH)){
				eventResponse = searchCodeMgmtMaster(e);
			}
			else if(e.getFormCommand().isCommand(FormCommand.SEARCH01)){
				eventResponse = searchCodeMgmtDetail(e);
			}
			else if(e.getFormCommand().isCommand(FormCommand.MULTI)){
				eventResponse = manageCodeMgmtMaster(e);
			}
			else if(e.getFormCommand().isCommand(FormCommand.MULTI01)){
				eventResponse = manageCodeMgmtDetail(e);
			}
		}
		
		return eventResponse;
	}
	
	/**
	 * DOU_TRN_0002 : [Event]<br>
	 * Searching for master<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchCodeMgmtMaster(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0002Event event = (DouTrn0002Event)e;
		CodeManagementBC command = new CodeManagementBCImpl();
		
		try {
			List<CodeMgmtMasterVO> list = command.searchMasterVO(event.getCodeMgmtMasterVo());
			eventResponse.setRsVoList(list);
		}catch (EventException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch (Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
	
	/**
	 * DOU_TRN_0002 : [Event]<br>
	 * Searching for detail<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchCodeMgmtDetail(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0002Event event = (DouTrn0002Event)e;
		CodeManagementBC command = new CodeManagementBCImpl();
		
		try {
			List<CodeMgmtDetailVO> list = command.searchDetailVO(event.getCodeMgmtDetailVo());
			eventResponse.setRsVoList(list);		
		}catch (EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch (Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}
	
	/**
	 * DOU_TRN_0002: [Event]<br>
	 * Code Management Master VO<br>
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	private EventResponse manageCodeMgmtMaster(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0002Event event = (DouTrn0002Event)e;
		CodeManagementBC command = new CodeManagementBCImpl();
		
		try {
			begin();
			command.manageCodeMgmtMaster(event.getCodeMgmtMasterVos(), account);
			eventResponse.setUserMessage(new ErrorHandler("DOU00001").getUserMessage());
			commit();
		}catch(EventException ex){
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}
	
	/**
	 * DOU_TRN_0002: [Event]<br>
	 * Code Management Detail VO<br>
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	private EventResponse manageCodeMgmtDetail(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0002Event event = (DouTrn0002Event)e;
		CodeManagementBC command = new CodeManagementBCImpl();
		
		try {
			begin();
			command.manageCodeMgmtDetail(event.getCodeMgmtDetailVos(), account);
			eventResponse.setUserMessage(new ErrorHandler("DOU00001").getUserMessage());
			commit();			
		}catch(EventException ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
	
	/**
	 * DOU_TRN_0707 : [Event]<br>
	 * [Act]for [Business Target].<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchErrMsg(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0707Event event = (DouTrn0707Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();

		try{
			List<ErrMsgVO> list = command.searchErrMsg(event.getErrMsgVO()); //call to BCImpl and return a list for ViewAdapter
			eventResponse.setRsVoList(list); // R is search
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * DOU_TRN_0707 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manageErrMsg(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		DouTrn0707Event event = (DouTrn0707Event)e;
		ErrMsgMgmtBC command = new ErrMsgMgmtBCImpl();
		try{
			begin();
			command.manageErrMsg(event.getErrMsgVOS(), account);
			//when insert, update, delete success return a message for client.
			eventResponse.setUserMessage(new ErrorHandler("DOU00001").getUserMessage());
			commit();
		} catch(EventException ex) {
			rollback(); 
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			rollback();
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
}