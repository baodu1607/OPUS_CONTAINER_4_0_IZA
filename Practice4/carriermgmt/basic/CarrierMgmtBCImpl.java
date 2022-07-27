/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierMgmtBCImpl.java
*@FileTitle : PRACTICE4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.12
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.12 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.carriermgmt.basic;

import java.util.ArrayList;
import java.util.List;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.integration.CarrierMgmtDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierListVO;

/**
 * ALPS-Practice4 Business Logic Command Interface<br>
 * - ALPS-Practice4에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Bao
 * @since J2EE 1.6
 */
public class CarrierMgmtBCImpl extends BasicCommandSupport implements CarrierMgmtBC {

	// Database Access Object
	private transient CarrierMgmtDBDAO dbDao = null;

	/**
	 * CarrierMgmtBCImpl 객체 생성<br>
	 * CarrierMgmtDBDAO를 생성한다.<br>
	 */
	public CarrierMgmtBCImpl() {
		dbDao = new CarrierMgmtDBDAO();
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierListVO carrierListVO
	 * @return List<CarrierListVO>
	 * @exception EventException
	 */
	public List<CarrierListVO> searchCarrierListVO(CarrierListVO carrierListVO) throws EventException {
		try {
			return dbDao.searchCarrierListVO(carrierListVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
		
	}
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierListVO[] carrierListVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manageCarrierListVO(CarrierListVO[] carrierListVO, SignOnUserAccount account) throws EventException{
		try {
			List<CarrierListVO> insertVoList = new ArrayList<CarrierListVO>();
			List<CarrierListVO> updateVoList = new ArrayList<CarrierListVO>();
			List<CarrierListVO> deleteVoList = new ArrayList<CarrierListVO>();
			
			for ( int i=0; i<carrierListVO .length; i++ ) {
				if ( carrierListVO[i].getIbflag().equals("I")){
					//Do not check the duplicate
					CarrierListVO carrier = carrierListVO[i];
					carrier.setCreUsrId(account.getUsr_id());
					carrier.setUpdUsrId(account.getUsr_id());
					insertVoList.add(carrierListVO[i]);
					
				} else if ( carrierListVO[i].getIbflag().equals("U")){
					carrierListVO[i].setUpdUsrId(account.getUsr_id());
					updateVoList.add(carrierListVO[i]);
					
				} else if ( carrierListVO[i].getIbflag().equals("D")){
					deleteVoList.add(carrierListVO[i]);
				}
			}
			
			if ( insertVoList.size() > 0 ) {
				dbDao.addCarrierListVOS(insertVoList);
			}
			
			if ( updateVoList.size() > 0 ) {
				dbDao.modifyCarrierListVOS(updateVoList);
			}
			
			if ( deleteVoList.size() > 0 ) {
				dbDao.removeCarrierListVOS(deleteVoList);
			}
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * Retrieve carrier codes from db for initial combo-box object<br>
	 * 
	 * @return List<CarrierListVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierListVO> getCrrCds() throws EventException {
		try {
			return dbDao.getCrrCds();
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * Retrieve lane codes from db for initial combo-box object<br>
	 * 
	 * @return List<CarrierListVO>
	 * @exception EventException
	 */
	@Override
	public List<CarrierListVO> getLnCds() throws EventException {
		try {
			return dbDao.getLnCds();
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	@Override
	public List<CarrierListVO> searchCust(CarrierListVO carrierListVO)
			throws EventException {
		try {
			return dbDao.searchCust(carrierListVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
}