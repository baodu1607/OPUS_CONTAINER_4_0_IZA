package com.clt.apps.opus.esm.clv.usersmgmt.basic;

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.usersmgmt.integration.UsersMgmtDBDAO;
import com.clt.apps.opus.esm.clv.usersmgmt.vo.UsersManagementVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;

public class UsersMgmtBCImpl extends BasicCommandSupport implements UsersMgmtBC {

	private transient UsersMgmtDBDAO dbDao = null;
	
	public UsersMgmtBCImpl() {
		dbDao = new UsersMgmtDBDAO();
	}
	
	@Override
	public List<UsersManagementVO> searchUser(UsersManagementVO userVO) throws EventException {
		try{
			return dbDao.searchUser(userVO);
		}catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	@Override
	public void manageUser(UsersManagementVO[] userVO, SignOnUserAccount account) throws EventException {
		try{
			List<UsersManagementVO> insertVoList = new ArrayList<UsersManagementVO>();
			List<UsersManagementVO> updateVoList = new ArrayList<UsersManagementVO>();
			
			for(int i=0; i < userVO.length; i++){
				if(userVO[i].getIbflag().equals("I")){
					insertVoList.add(userVO[i]);
				}else if(userVO[i].getIbflag().equals("U")){
					updateVoList.add(userVO[i]);
				}
			}
			
			if(insertVoList.size() > 0){
				dbDao.addUsers(insertVoList);
			}
			
			if(updateVoList.size() > 0){
				dbDao.modifyUsers(updateVoList);
			}
			
		}catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}		
	}
	
}
