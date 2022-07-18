/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierMgmtBC.java
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

import java.util.List;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierListVO;

/**
 * ALPS-Practice4 Business Logic Command Interface<br>
 * - ALPS-Practice4에 대한 비지니스 로직에 대한 인터페이스<br>
 *
 * @author Bao
 * @since J2EE 1.6
 */

public interface CarrierMgmtBC {

	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierListVO	carrierListVO
	 * @return List<CarrierListVO>
	 * @exception EventException
	 */
	public List<CarrierListVO> searchCarrierListVO(CarrierListVO carrierListVO) throws EventException;
	
	/**
	 * [비즈니스대상]을 [행위] 합니다.<br>
	 * 
	 * @param CarrierListVO[] carrierListVO
	 * @param account SignOnUserAccount
	 * @exception EventException
	 */
	public void manageCarrierListVO(CarrierListVO[] carrierListVO,SignOnUserAccount account) throws EventException;
	
	/**
	 * Retrieve carrier codes from db for initial combo-box object<br>
	 * 
	 * @return List<CarrierListVO>
	 * @throws EventException
	 */
	public List<CarrierListVO> getCrrCds() throws EventException;
	
	/**
	 * Retrieve lane codes from db for initial combo-box object<br>
	 * 
	 * @return List<CarrierListVO>
	 * @throws EventException
	 */
	public List<CarrierListVO> getLnCds() throws EventException;
	
	
	
	
	
}