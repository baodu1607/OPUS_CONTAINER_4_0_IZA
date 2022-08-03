/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : MoneyMgmtSC.java
*@FileTitle : Money Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.25
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.19
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3;

import java.util.List;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.basic.MoneyMgmtBC;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.basic.MoneyMgmtBCImpl;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.event.EsmDou0108Event;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * ALPS-MoneyMgmt Business Logic ServiceCommand - Process business transaction for ALPS-MoneyMgmt.
 * 
 * @author BaoDu
 * @see MoneyMgmtDBDAO
 * @since J2EE 1.6
 */
public class MoneyManagementSC extends ServiceCommandSupport{
	
	private SignOnUserAccount account = null;
	
	/**
	 * MoneyMgmt system task scenario precedent work<br>
	 * Creating related internal objects when calling a business scenario<br>
	 */
	public void doStart() {
		log.debug("MoneymgmtSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}
	
	/**
	 * MoneyMgmt system work scenario finishing work<br>
	 * Release related internal objects when the work scenario is finished<br>
	 */
	public void doEnd() {
		log.debug("MoneymgmtSC 종료");
	}
	
	/**
	 * Carry out business scenarios for each event<br>
	 * Branch processing of all events occurring in ALPS-MoneyMgmt system work<br>
	 * 
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	public EventResponse perform(Event e) throws EventException {
		EventResponse eventResponse = null;
		
		if (e.getEventName().equalsIgnoreCase("EsmDou0108Event")) {
			if (e.getFormCommand().isCommand(FormCommand.SEARCH)) {
				eventResponse = searchSummaryVO(e);
			}else if (e.getFormCommand().isCommand(FormCommand.DEFAULT)) {
				eventResponse = initCombo();
			}else if(e.getFormCommand().isCommand(FormCommand.SEARCH02)){
				eventResponse = searchLaneCodes(e);
			}else if(e.getFormCommand().isCommand(FormCommand.SEARCH03)){
				eventResponse = searchTradeCodes(e);
			}else if (e.getFormCommand().isCommand(FormCommand.SEARCH01)) {
				eventResponse = searchDetailVO(e);
			}
		}
			
		return eventResponse;
	}
	
	/**
	 * ESM_DOU_0108 : [Event]<br>
	 * [Act] for [Business Target].<br>
	 * This method is used for searching Detail data  
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchDetailVO(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		EsmDou0108Event event = (EsmDou0108Event)e;
		MoneyMgmtBC command = new MoneyMgmtBCImpl();
		
		try {
			List<DetailVO> list = command.searchDetailVO(event.getConditionVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} 
		
		return eventResponse;
	}

	/**
	 * ESM_DOU_0108 : [Event]<br>
	 * [Act] for [Business Target].<br>
	 * This method is used for searching Summary data  
	 * @param Event e
	 * @return EventResponse
	 * @exception EventException
	 */
	private EventResponse searchSummaryVO(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		EsmDou0108Event event = (EsmDou0108Event)e;
		MoneyMgmtBC command = new MoneyMgmtBCImpl();
		
		try {
			List<SummaryVO> list = command.searchSummaryVO(event.getConditionVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}
	
	/**
	 * Searching data for trade combo box<br>
	 * @param e
	 * @return eventReponse
	 * @throws EventException
	 */
	private EventResponse searchTradeCodes(Event e) throws EventException{
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		EsmDou0108Event event = (EsmDou0108Event)e;
		MoneyMgmtBC command = new MoneyMgmtBCImpl();
		
		try {
			List<SummaryVO> tradeCodeList = command.getTradeCodes(event.getSummaryVO());
			StringBuilder tradeCodes = new StringBuilder();
			
			if(tradeCodeList.size() != 0){
				for(int i = 0; i < tradeCodeList.size(); i++){
					tradeCodes.append(tradeCodeList.get(i).getTrdCd() + "|");
				}
				tradeCodes.deleteCharAt(tradeCodes.length()-1);
			}
			eventResponse.setETCData("tradeCodes", tradeCodes.toString());
			
		} catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}

	/**
	 * Searching data for lane combo box<br>
	 * 
	 * @param e
	 * @return eventResponse
	 * @throws EventException
	 */
	private EventResponse searchLaneCodes(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		EsmDou0108Event event = (EsmDou0108Event) e;
		MoneyMgmtBC command = new MoneyMgmtBCImpl();
		
		try {
			List<SummaryVO> laneCodeList = command.getLaneCodes(event.getSummaryVO());
			StringBuilder laneCodes = new StringBuilder();
			
			if(laneCodeList.size() != 0){
				for(int i=0; i< laneCodeList.size(); i++){
					laneCodes.append(laneCodeList.get(i).getRlaneCd() + "|");
				}
				
				laneCodes.deleteCharAt(laneCodes.length()-1); //delete "|"
			}
			eventResponse.setETCData("laneCodes",laneCodes.toString());
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}

	/**
	 * Initializing data for partner combo box <br>
	 * 
	 * @return eventResponse
	 * @throws EventException
	 */
	private EventResponse initCombo() throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		MoneyMgmtBC command = new MoneyMgmtBCImpl();
		
		try {
			List<SummaryVO> partnerCodeList = command.getPartnerCodes();
			StringBuilder partnerCodes = new StringBuilder();
			
			if(partnerCodeList.size() != 0){
				for(int i=0; i<partnerCodeList.size(); i++){
					partnerCodes.append(partnerCodeList.get(i).getJoCrrCd()+"|");
				}
				partnerCodes.deleteCharAt(partnerCodes.length()-1); //delete "|"
			}
			
			eventResponse.setETCData("partnerCodes", partnerCodes.length()==0 ? "" : partnerCodes.toString());
		} catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}
	
}
