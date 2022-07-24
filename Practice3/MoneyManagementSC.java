package com.clt.apps.opus.esm.clv.practice3;

import java.util.List;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.basic.MoneyMgmtBC;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.basic.MoneyMgmtBCImpl;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.event.MoneyTrn0003Event;
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

public class MoneyManagementSC extends ServiceCommandSupport{
	
	private SignOnUserAccount account = null;
	
	public void doStart() {
		log.debug("MoneymgmtSC 시작");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}
	
	public void doEnd() {
		log.debug("MoneymgmtSC 종료");
	}
	
	public EventResponse perform(Event e) throws EventException {
		EventResponse eventResponse = null;
		
		if (e.getEventName().equalsIgnoreCase("MoneyTrn0003Event")) {
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
	
	private EventResponse searchDetailVO(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		MoneyTrn0003Event event = (MoneyTrn0003Event)e;
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

	private EventResponse searchSummaryVO(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		MoneyTrn0003Event event = (MoneyTrn0003Event)e;
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
		
	private EventResponse searchTradeCodes(Event e) throws EventException{
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		MoneyTrn0003Event event = (MoneyTrn0003Event)e;
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

	private EventResponse searchLaneCodes(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		MoneyTrn0003Event event = (MoneyTrn0003Event) e;
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
