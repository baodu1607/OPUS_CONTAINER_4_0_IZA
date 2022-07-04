/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ErrMsgMgmtBCImpl.java
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.27
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.27 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.basic;

import java.util.ArrayList;
import java.util.List;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.integration.ErrMsgMgmtDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.doutraining.errmsgmgmt.vo.ErrMsgVO;

/**
 * ALPS-DouTraining Business Logic Command Interface<br>
 * - ALPS-DouTraining에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Bao
 * @since J2EE 1.6
 */
public class ErrMsgMgmtBCImpl extends BasicCommandSupport implements ErrMsgMgmtBC {

	// Database Access Object
	private transient ErrMsgMgmtDBDAO dbDao = null;

	/**
	 * ErrMsgMgmtBCImpl 객체 생성<br>
	 * ErrMsgMgmtDBDAO를 생성한다.<br>
	 */
	public ErrMsgMgmtBCImpl() {
		dbDao = new ErrMsgMgmtDBDAO();
	}
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO errMsgVO
	 * @return List<ErrMsgVO>
	 * @exception EventException
	 */
	public List<ErrMsgVO> searchErrMsg(ErrMsgVO errMsgVO) throws EventException {
		try {
			//return List<ErrMsgVO> 
			return dbDao.searchErrMsg(errMsgVO); //return to SC
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param ErrMsgVO[] errMsgVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manageErrMsg(ErrMsgVO[] errMsgVO, SignOnUserAccount account) throws EventException{
		try {
			List<ErrMsgVO> insertVoList = new ArrayList<ErrMsgVO>();
			List<ErrMsgVO> updateVoList = new ArrayList<ErrMsgVO>();
			List<ErrMsgVO> deleteVoList = new ArrayList<ErrMsgVO>();
			
			for ( int i = 0; i < errMsgVO.length; i++ ) {
				/* Ibflag : + D : delete
				 * 			+ I : insert
				 * 			+ U : update */
				if ( errMsgVO[i].getIbflag().equals("I")){
					errMsgVO[i].setCreUsrId(account.getUsr_id());
					errMsgVO[i].setUpdUsrId(account.getUsr_id());
					if(!isDuplicateMsgCode(errMsgVO[i].getErrMsgCd())){
						insertVoList.add(errMsgVO[i]); // add ErrMsgVO to insertList
					}else {
						//System.out.println("Error Message Code Exist");
						throw new DAOException(new ErrorHandler("ERR00001").getMessage());
					}
									
				} else if ( errMsgVO[i].getIbflag().equals("U")){
					errMsgVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(errMsgVO[i]); // add ErrMsgVO to updateVoList
					
				} else if ( errMsgVO[i].getIbflag().equals("D")){
					deleteVoList.add(errMsgVO[i]); // add ErrMsgVO to deleteVoList
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addmanageErrMsgS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifymanageErrMsgS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removemanageErrMsgS(deleteVoList);
			}
			
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * Checking for duplicate Message Code
	 * 
	 * @param errMsgCode
	 * @return
	 * @exception EventException
	 */
	public boolean isDuplicateMsgCode(String errMsgCode) throws EventException {
		try {
			//Create new Error Message Object with the Code that gets inserted from client
			ErrMsgVO errMsgVO = new ErrMsgVO();
			errMsgVO.setErrMsgCd(errMsgCode); 
			List<ErrMsgVO> searchList = dbDao.searchErrMsg(errMsgVO);
			if( searchList.size() == 0) return false;
			return true;
			
		} catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(), ex);
		}
	}
	
}