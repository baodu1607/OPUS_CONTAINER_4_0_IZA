package com.clt.apps.opus.esm.clv.usersmgmt.event;

import com.clt.apps.opus.esm.clv.usersmgmt.vo.UsersManagementVO;
import com.clt.framework.support.layer.event.EventSupport;

public class UsersMgmt0007Event extends EventSupport {
	private static final long serialVersionUID = 1L;
	
	UsersManagementVO userVO = null;
	UsersManagementVO[] userVOs = null;
	
	public UsersMgmt0007Event() {}
	
	public UsersManagementVO getUserVO(){
		return userVO;
	}
	
	public UsersManagementVO[] getUserVOs(){
		return userVOs;
	}
	
	public void setUserVO(UsersManagementVO userVO){
		this.userVO = userVO;
	}
	
	public void setUserVOs(UsersManagementVO[] userVOs){
		this.userVOs = userVOs;
	}
}
