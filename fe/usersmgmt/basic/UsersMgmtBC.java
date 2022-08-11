package com.clt.apps.opus.esm.clv.usersmgmt.basic;

import java.util.List;

import com.clt.apps.opus.esm.clv.usersmgmt.vo.UsersManagementVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public interface UsersMgmtBC {
	
	public List<UsersManagementVO> searchUser(UsersManagementVO userVO) throws EventException;
	
	public void manageUser(UsersManagementVO[] userVO, SignOnUserAccount account) throws EventException;

}
