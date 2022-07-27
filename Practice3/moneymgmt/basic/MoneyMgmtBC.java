/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : MoneyMgmtBC.java
*@FileTitle : Money Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.25
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.19
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.moneymgmt.basic;

import java.util.List;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.ConditionVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.core.layer.event.EventException;

/**
 * ALPS-Moneymgmt Business Logic Command Interface<br>
 * - Interface to business logic for ALPS-Moneymgmt<br>
 *
 * @author BaoDu
 * @since J2EE 1.6
 */
public interface MoneyMgmtBC {
	/**
	 * Getting data for Partner combo box<br>
	 * 
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	public List<SummaryVO> getPartnerCodes() throws EventException;

	/**
	 * Getting data for Lane combo box<br>
	 * 
	 * @param summaryVO
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	public List<SummaryVO> getLaneCodes(SummaryVO summaryVO) throws EventException;
	
	/**
	 * Getting data for Trade combo box<br>
	 * 
	 * @param summaryVO
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	public List<SummaryVO> getTradeCodes(SummaryVO summaryVO) throws EventException;
	
	/**
	 * Searching Summary data <br>
	 * 
	 * @param ConditionVO conditionVO
	 * @return List<SummaryVO>
	 * @exception EventException
	 */
	public List<SummaryVO> searchSummaryVO(ConditionVO conditionVO) throws EventException;

	/**
	 * Searching Detail data<br>
	 * @param ConditionVO conditionVO
	 * @return List<DetailVO>
	 * @throws EventException
	 */
	public List<DetailVO> searchDetailVO(ConditionVO conditionVO) throws EventException;
}
