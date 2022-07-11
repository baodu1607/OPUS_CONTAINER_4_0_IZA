/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DouTrn0002Event.java
*@FileTitle : Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.06
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.codemanagement.event;

import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtDetailVO;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtMasterVO;
import com.clt.framework.support.layer.event.EventSupport;

/**
 * PDTO(Data Transfer Object including Parameters) for DOU_TRN_0002<br>
 * - Created by DOU_TRN_0002HTMLAction<br>
 * - Distributed to the SC<br>
 *
 * @author Bao Du
 * @see DOU_TRN_0002HTMLAction 
 * @since J2EE 1.6
 */
public class DouTrn0002Event extends EventSupport {
	private static final long serialVersionUID = 1L;
	int sheetNo = 0;
	CodeMgmtMasterVO codeMgmtMasterVO = null;
	CodeMgmtMasterVO[] codeMgmtMasterVOs = null;
	CodeMgmtDetailVO codeMgmtDetailVO = null;
	CodeMgmtDetailVO[] codeMgmtDetailVOs = null;
	
	public DouTrn0002Event() {}
	
	/*public int getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(int sheetNo) {
		this.sheetNo = sheetNo;
	}*/

	public CodeMgmtDetailVO getCodeMgmtDetailVo() {
		return codeMgmtDetailVO;
	}
	public void setCodeMgmtDetailVo(CodeMgmtDetailVO codeMgmtDetailVo) {
		this.codeMgmtDetailVO = codeMgmtDetailVo;
	}
	public CodeMgmtDetailVO[] getCodeMgmtDetailVos() {
		return codeMgmtDetailVOs;
	}
	public void setCodeMgmtDetailVos(CodeMgmtDetailVO[] codeMgmtDetailVos) {
		this.codeMgmtDetailVOs = codeMgmtDetailVos;
	}
	public CodeMgmtMasterVO getCodeMgmtMasterVo() {
		return codeMgmtMasterVO;
	}
	public void setCodeMgmtMasterVo(CodeMgmtMasterVO codeMgmtMasterVo) {
		this.codeMgmtMasterVO = codeMgmtMasterVo;
	}
	public CodeMgmtMasterVO[] getCodeMgmtMasterVos() {
		return codeMgmtMasterVOs;
	}
	public void setCodeMgmtMasterVos(CodeMgmtMasterVO[] codeMgmtMasterVos) {
		this.codeMgmtMasterVOs = codeMgmtMasterVos;
	}
	
}
