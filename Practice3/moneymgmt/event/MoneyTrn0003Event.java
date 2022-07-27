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
 * @see MONEY_TRN_0003HTMLAction refer
 * @since J2EE 1.6
 */
public class MoneyTrn0003Event extends EventSupport {
	private static final long serialVersionUID = 1L;
	
	SummaryVO summaryVO = null;
	DetailVO detailVO = null;
	ConditionVO conditionVO = null;
	
	public MoneyTrn0003Event() {}
	
	public void setConditionVO(ConditionVO conditionVO) {
		this.conditionVO = conditionVO;
	}
	
	public void setSummaryVO(SummaryVO summaryVO){
		this.summaryVO = summaryVO;
	}

	public void setDetailVO(DetailVO detailVO) {
		this.detailVO = detailVO;
	}
	
	public ConditionVO getConditionVO() {
		return conditionVO;
	}
	
	public SummaryVO getSummaryVO(){
		return summaryVO;
	}

	public DetailVO getDetailVO() {
		return detailVO;
	}

}
