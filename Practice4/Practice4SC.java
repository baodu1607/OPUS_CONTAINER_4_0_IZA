/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice4SC.java
*@FileTitle : PRACTICE4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.12
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.12 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4;

import java.util.List;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.basic.CarrierMgmtBC;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.basic.CarrierMgmtBCImpl;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.event.Practice0004Event;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierListVO;


/**
 * ALPS-Practice4 Business Logic ServiceCommand - ALPS-Practice4 대한 비지니스 트랜잭션을 처리한다.
 * 
 * @author Bao
 * @see CarrierMgmtDBDAO
 * @since J2EE 1.6
 */

public class Practice4SC extends ServiceCommandSupport {
	// Login User Information
	private SignOnUserAccount account = null;

	/**
	 * Practice4 system 업무 시나리오 선행작업<br>
	 * 업무 시나리오 호출시 관련 내부객체 생성<br>
	 */
	public void doStart() {
		log.debug("Practice4SC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}

	/**
	 * Practice4 system 업무 시나리오 마감작업<br>
	 * 업무 시나리오 종료시 관련 내부객체 해제<br>
	 */
	public void doEnd() {
		log.debug("Practice4SC 종료");
	}

	/**
	 * 각 이벤트에 해당하는 업무 시나리오 수행<br>
	 * ALPS-Practice4 system 업무에서 발생하는 모든 이벤트의 분기처리<br>
	 * 
	 * @param e Event
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		// RDTO(Data Transfer Object including Parameters)
		EventResponse eventResponse = null;

		// SC가 여러 이벤트를 처리하는 경우 사용해야 할 부분
		if (e.getEventName().equalsIgnoreCase("Practice0004Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchCarrierListVO(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.MULTI)) {
				eventResponse = manageCarrierListVO(e);
			}
			else if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = initCombo();
			}
		}
		return eventResponse;
	}
	/**
	 * PRACTICE_0004 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchCarrierListVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice0004Event event = (Practice0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();

		try{
			List<CarrierListVO> list = command.searchCarrierListVO(event.getCarrierListVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}	
		return eventResponse;
	}
	
	/**
	 * PRACTICE_0004 : [이벤트]<br>
	 * [비즈니스대상]을 [행위]합니다.<br>
	 *
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse manageCarrierListVO(Event e) throws EventException {
		// PDTO(Data Transfer Object including Parameters)
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		Practice0004Event event = (Practice0004Event)e;
		CarrierMgmtBC command = new CarrierMgmtBCImpl();
		try{
			begin();
			command.manageCarrierListVO(event.getCarrierListVOS(),account);
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
	
	/**
	 * Initial combo box object<br>
	 * 
	 * @return eventResponse EventResponse
	 * @throws EventException
	 */
	private EventResponse initCombo() throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		CarrierMgmtBC command = new CarrierMgmtBCImpl();
		
		try {
			List<CarrierListVO> crrCdList = command.getCrrCds();
			List<CarrierListVO> lnCdList = command.getLnCds();
			StringBuilder crrCds = new StringBuilder();
			StringBuilder lnCds = new StringBuilder();
			
			if(crrCdList.size() != 0){
				for(int i=0; i<crrCdList.size(); i++){
					crrCds.append(crrCdList.get(i).getJoCrrCd() + "|");
				}
				crrCds.deleteCharAt(crrCds.length()-1); // delete ","
			}
			
			if(lnCdList.size() != 0){
				for(int i=0; i<lnCdList.size(); i++){
					lnCds.append(lnCdList.get(i).getRlaneCd() + "|");
				}
				lnCds.deleteCharAt(lnCds.length()-1);
			}
			
			eventResponse.setETCData("crrCds", crrCds.toString()); //return to client side through GeneralEventResponse
			eventResponse.setETCData("lnCds", lnCds.toString());	
		} catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		return eventResponse;
	}
}