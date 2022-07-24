package com.clt.apps.opus.esm.clv.practice3.moneymgmt.event;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.ConditionVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.support.layer.event.EventSupport;

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
