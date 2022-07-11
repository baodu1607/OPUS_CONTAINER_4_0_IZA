/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CodeManagementBCImpl.java
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

import java.util.ArrayList;
import java.util.List;

import com.clt.apps.opus.esm.clv.doutraining.codemanagement.integration.CodeManagementDBDAO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;
import com.clt.framework.support.view.signon.SignOnUserAccount;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtDetailVO;

/**
 * Code Management Business Command Implement
 * 
 * @author BaoDu
 * @see CodeManagementBC
 * @since 
 */
public class CodeManagementBCImpl extends BasicCommandSupport implements CodeManagementBC {
	private transient CodeManagementDBDAO dbDao = null;
	public CodeManagementBCImpl() {
		dbDao = new CodeManagementDBDAO();
	}
	
	/**
	 * Searching for master vo<br>
	 * 
	 * @param masterVO CodeMgmtMasterVO
	 * @return List<CodeMgmtMasterVO>
	 * @exception EventException
	 */
	public List<CodeMgmtMasterVO> searchMasterVO(CodeMgmtMasterVO masterVO) throws EventException {
		try {
			return dbDao.searchMasterVO(masterVO);
		}catch (DAOException ex) { //why catch DAOException here instead of EventException
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch (Exception ex){
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * Searching for detail vo<br>
	 * 
	 * @param detailVO CodeMgmtDetailVO
	 * @return List<CodeMgmtDetailVO>
	 * @exception EventException
	 */
	public List<CodeMgmtDetailVO> searchDetailVO(CodeMgmtDetailVO detailVO) throws EventException {
		try {
			return dbDao.searchDetailVO(detailVO);
		}catch (DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * Manage data on master table
	 * 
	 * @param codeMgmtMaster
	 * @param account
	 * @exception EventException
	 */
	public void manageCodeMgmtMaster(CodeMgmtMasterVO[] codeMgmtMasters, SignOnUserAccount account) throws EventException {
		try {
			List<CodeMgmtMasterVO> insertList = new ArrayList<CodeMgmtMasterVO>();
			List<CodeMgmtMasterVO> updateList = new ArrayList<CodeMgmtMasterVO>();
			List<CodeMgmtMasterVO> deleteList = new ArrayList<CodeMgmtMasterVO>();
			List<CodeMgmtDetailVO> deleteDetailList = new ArrayList<CodeMgmtDetailVO>();
			
			for(int i=0; i < codeMgmtMasters.length; i++){
				if(codeMgmtMasters[i].getIbflag().equals("I")){
					//Check duplicated
					CodeMgmtMasterVO masterVO = new CodeMgmtMasterVO();
					masterVO.setIntgCdId(codeMgmtMasters[i].getIntgCdId());
					if(searchMasterVO(masterVO).size() == 0){
						codeMgmtMasters[i].setCreUsrId(account.getUsr_id());
						codeMgmtMasters[i].setUpdUsrId(account.getUsr_id());
						insertList.add(codeMgmtMasters[i]);
					}
					/*codeMgmtMasters[i].setCreUsrId(account.getUsr_id());
					codeMgmtMasters[i].setUpdUsrId(account.getUsr_id());
					insertList.add(codeMgmtMasters[i]);*/
				}else if(codeMgmtMasters[i].getIbflag().equals("U")){
					codeMgmtMasters[i].setUpdUsrId(account.getUsr_id());
					updateList.add(codeMgmtMasters[i]);
				}else if(codeMgmtMasters[i].getIbflag().equals("D")){
					CodeMgmtDetailVO detailVO = new CodeMgmtDetailVO();
					detailVO.setIntgCdId(codeMgmtMasters[i].getIntgCdId());
					deleteDetailList.addAll(dbDao.searchDetailVO(detailVO));
					deleteList.add(codeMgmtMasters[i]);
				}	
			}
			
			if(insertList.size() > 0){
				dbDao.addCodeMgmtMasterVOs(insertList);
			}
			if(updateList.size() > 0){
				dbDao.modifyCodeMgmtMasterVOs(updateList);
			}
			if(deleteList.size() > 0){
				if(deleteDetailList.size() > 0){
					dbDao.removeCodeMgmtDetailVOs(deleteDetailList);
				}
				dbDao.removeCodeMgmtMasterVOs(deleteList);
			}
												
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch(Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	/**
	 * Manage data on detail table
	 * 
	 * @param codeMgmtDetail
	 * @param account
	 * @exception EventException
	 */
	public void manageCodeMgmtDetail(CodeMgmtDetailVO[] codeMgmtDetails, SignOnUserAccount account) throws EventException {
		try {
			List<CodeMgmtDetailVO> insertList = new ArrayList<CodeMgmtDetailVO>();
			List<CodeMgmtDetailVO> updateList = new ArrayList<CodeMgmtDetailVO>();
			List<CodeMgmtDetailVO> deleteList = new ArrayList<CodeMgmtDetailVO>();
			for(int i=0; i < codeMgmtDetails.length; i++) {
				if(codeMgmtDetails[i].getIbflag().equals("I")){															
					//duplicate checking					
					if(searchDetailVO(codeMgmtDetails[i]).size() == 0){
						codeMgmtDetails[i].setCreUsrId(account.getUsr_id());
						codeMgmtDetails[i].setUpdUsrId(account.getUsr_id());
						insertList.add(codeMgmtDetails[i]);
					}
				}else if(codeMgmtDetails[i].getIbflag().equals("U")){
					codeMgmtDetails[i].setUpdUsrId(account.getUsr_id());
					updateList.add(codeMgmtDetails[i]);
				}else if(codeMgmtDetails[i].getIbflag().equals("D")){
					deleteList.add(codeMgmtDetails[i]);
				}												
			}
			
			if( insertList.size() > 0){
				dbDao.addCodeMgmtDetailVOs(insertList);
			}
			if( updateList.size() > 0){
				dbDao.modifyCodeMgmtDetailVOs(updateList);
			}
			if( deleteList.size() > 0){
				dbDao.removeCodeMgmtDetailVOs(deleteList);
			}
		} catch(DAOException ex) {
			// throw new EventException if we have DAOException
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			//other exception
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}			
	}
}
