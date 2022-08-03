/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : MoneyTrn0003Event.java
*@FileTitle : Money Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.25
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.19 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.moneymgmt.event;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.ConditionVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.support.layer.event.EventSupport;

/**
 * MONEY_TRN_0003 for PDTO(Data Transfer Object including Parameters)<br>
 * - Created from MONEY_TRN_0003HTMLAction<br>
 * - Used as PDTO delivered to ServiceCommand Layer<br>
 *
 * @author BaoDu
 * @see ESM_DOU_0108HTMLAction refer
 * @since J2EE 1.6
 */
public class EsmDou0108Event extends EventSupport {
	private static final long serialVersionUID = 1L;
	
	SummaryVO summaryVO = null;
	DetailVO detailVO = null;
	ConditionVO conditionVO = null;
	
	/**
	 * EsmDou0108Event generation
	 */
	public EsmDou0108Event() {}
	
	/**
	 * Set condition VO
	 * 
	 * @param conditionVO
	 */
	public void setConditionVO(ConditionVO conditionVO) {
		this.conditionVO = conditionVO;
	}
	
	/**
	 * Set summary
	 * 
	 * @param summaryVO
	 */
	public void setSummaryVO(SummaryVO summaryVO){
		this.summaryVO = summaryVO;
	}
	
	/**
	 * Set detail 
	 * 
	 * @param detailVO
	 */
	public void setDetailVO(DetailVO detailVO) {
		this.detailVO = detailVO;
	}
	
	/**
	 * Get condition VO
	 * @return ConditionVO
	 */
	public ConditionVO getConditionVO() {
		return conditionVO;
	}
	
	/**
	 * Get summary VO
	 * @return SummaryVO
	 */
	public SummaryVO getSummaryVO(){
		return summaryVO;
	}

	/**
	 * Get detail VO
	 * @return DetailVO
	 */
	public DetailVO getDetailVO() {
		return detailVO;
	}

}
