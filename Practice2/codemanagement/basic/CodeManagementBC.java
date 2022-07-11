/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeManagementBC.java
*@FileTitle : Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.06
*@LastModifier : Bao Du
*@LastVersion : 1.0
* 2022.07.04 Bao Du
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.doutraining.codemanagement.basic;

import java.util.List;

import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtDetailVO;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.support.view.signon.SignOnUserAccount;

/**
 * Code Management Business Command Interface<br>
 * 
 * @author Bao Du
 * @see
 * @since 
 */
public interface CodeManagementBC {
	/**
	 * Searching for master vo<br>
	 * 
	 * @param masterVO
	 * @return CodeMgmtMasterVO
	 * @exception EventException
	 */
	public List<CodeMgmtMasterVO> searchMasterVO(CodeMgmtMasterVO masterVO) throws EventException;
	
	/**
	 * Searching for detail vo<br>
	 * 
	 * @param detailVO
	 * @return CodeMgmtDetailVO 
	 * @exception EventException
	 */
	public List<CodeMgmtDetailVO> searchDetailVO(CodeMgmtDetailVO detailVO) throws EventException;
	
	/**
	 * Manage data on master table
	 * 
	 * @param codeMgmtMaster
	 * @param account
	 * @exception EventException
	 */
	public void manageCodeMgmtMaster(CodeMgmtMasterVO[] codeMgmtMasters, SignOnUserAccount account) throws EventException;
	
	/**
	 * Manage data on detail table
	 * 
	 * @param codeMgmtDetail
	 * @param account
	 * @exception EventException
	 */
	public void manageCodeMgmtDetail(CodeMgmtDetailVO[] codeMgmtDetails, SignOnUserAccount account) throws EventException;
	
	
}
