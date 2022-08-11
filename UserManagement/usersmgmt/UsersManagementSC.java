package com.clt.apps.opus.esm.clv.usersmgmt;

import java.util.List;

import com.clt.apps.opus.esm.clv.usersmgmt.basic.UsersMgmtBC;
import com.clt.apps.opus.esm.clv.usersmgmt.basic.UsersMgmtBCImpl;
import com.clt.apps.opus.esm.clv.usersmgmt.event.UsersMgmt0007Event;
import com.clt.apps.opus.esm.clv.usersmgmt.vo.UsersManagementVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.Event;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.event.EventResponse;
import com.clt.framework.core.layer.event.GeneralEventResponse;
import com.clt.framework.support.controller.html.FormCommand;
import com.clt.framework.support.layer.service.ServiceCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class UsersManagementSC extends ServiceCommandSupport {
	
	private SignOnUserAccount account = null;

	public void doStart() {
		log.debug("Start DouTrainingSC");
		try {
			// 일단 comment --> 로그인 체크 부분
			account = getSignOnUserAccount();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}
	
	public void doEnd() {
		log.debug("Exit DouTrainingSC");
	}
	
	@Override
	public EventResponse perform(Event e) throws EventException {
		EventResponse eventResponse = null;
		
		if(e.getEventName().equalsIgnoreCase("UsersMgmt0007Event")){
			if(e.getFormCommand().isCommand(FormCommand.SEARCH)){
				eventResponse = searchUser(e);
			}else if(e.getFormCommand().isCommand(FormCommand.MULTI)){
				eventResponse = manageUser(e);
			}
		}
		
		return eventResponse;
	}

	private EventResponse manageUser(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		UsersMgmt0007Event event = (UsersMgmt0007Event)e;
		UsersMgmtBC command = new UsersMgmtBCImpl();
		
		try{
			begin();
			command.manageUser(event.getUserVOs(), account);
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

	private EventResponse searchUser(Event e) throws EventException {
		GeneralEventResponse eventResponse = new GeneralEventResponse();
		UsersMgmt0007Event event = (UsersMgmt0007Event)e;
		UsersMgmtBC command = new UsersMgmtBCImpl();
		
		try{
			List<UsersManagementVO> list = command.searchUser(event.getUserVO());
			eventResponse.setRsVoList(list);
		}catch(EventException ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch(Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
		return eventResponse;
	}

}
