package com.clt.apps.opus.esm.clv.doutraining.codemanagement.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtMasterVO;
import com.clt.apps.opus.esm.clv.doutraining.codemanagement.vo.CodeMgmtDetailVO;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.component.message.ErrorHandler;

public class CodeManagementDBDAO extends DBDAOSupport {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Searching data for master table<br>
	 * 
	 * @param masterVO CodeMgmtMasterVO
	 * @return List<CodeMgmtMasterVO>
	 * @exception DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CodeMgmtMasterVO> searchMasterVO(CodeMgmtMasterVO masterVO) throws DAOException {
		DBRowSet dbRowset = null;
		//List of MasterVO
		List<CodeMgmtMasterVO> list = null;
		//parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		try {
			if(masterVO != null){
				Map<String, String> mapVO = masterVO.getColumnValues();
				//Add all values to param and velParam
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			//do query here
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CodeManagementDBDAOCodeMgmtMasterVORSQL(), param, velParam);
			//cast DBRowSet to List
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CodeMgmtMasterVO .class);
		}catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new DAOException(new ErrorHandler(e).getMessage());
		}catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
	/**
	 * Searching data for detail table<br>
	 * 
	 * @param detailVO CodeMgmtDetailVO
	 * @return List<CodeMgmtMasterVO>
	 * @exception DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<CodeMgmtDetailVO> searchDetailVO(CodeMgmtDetailVO detailVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CodeMgmtDetailVO> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		try {
			if(detailVO != null){
				Map<String, String> mapVO = detailVO.getColumnValues();
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CodeManagementDBDAOCodeMgmtDetailVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CodeMgmtDetailVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param detailVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addCodeMgmtDetailVOs(List<CodeMgmtDetailVO> detailVO) throws DAOException, Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			
			if(detailVO.size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CodeManagementDBDAOCodeMgmtDetailVOCSQL(), detailVO, null);
				for(int i=0; i<insCnt.length; i++){
					if(insCnt[i] == Statement.EXECUTE_FAILED){
						throw new DAOException("Fail to insert No"+ i + " SQL");
					}
				}
			}					
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}
	
	/**
	 * Add data in master table<br>
	 * 
	 * @param masterVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addCodeMgmtMasterVOs(List<CodeMgmtMasterVO> masterVO) throws DAOException, Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(masterVO.size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CodeManagementDBDAOCodeMgmtMasterVOCSQL(), masterVO, null);
				for(int i = 0; i < insCnt.length; i++){
					if(insCnt[i]== Statement.EXECUTE_FAILED)
						throw new DAOException("Fail to insert No"+ i + " SQL");
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}
	
	/**
	 * Update data on master table<br>
	 * 
	 * @param masterVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifyCodeMgmtMasterVOs(List<CodeMgmtMasterVO> masterVO) throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(masterVO.size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new CodeManagementDBDAOCodeMgmtMasterVOUSQL(), masterVO, null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i] == Statement.EXECUTE_FAILED){
						throw new DAOException("Fail to update No"+ i + " SQL");
					}
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	/**
	 * Update data on detail table<br>
	 * 
	 * @param detailVO
	 * @return int[]
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] modifyCodeMgmtDetailVOs(List<CodeMgmtDetailVO> detailVO) throws DAOException, Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(detailVO.size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new CodeManagementDBDAOCodeMgmtDetailVOUSQL(), detailVO, null);
				for(int i=0; i< updCnt.length; i++){
					if(updCnt[i] == Statement.EXECUTE_FAILED){
						throw new DAOException("Fail to update No"+ i + " SQL");
					}
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
	/**
	 * Remove data on master table<br>
	 * 
	 * @param masterVO
	 * @return int[]
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] removeCodeMgmtMasterVOs(List<CodeMgmtMasterVO> masterVO) throws DAOException, Exception {
		int delCnt[] = null;
		try{
			SQLExecuter sqlExe = new SQLExecuter("");
			if(masterVO.size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CodeManagementDBDAOCodeMgmtMasterVODSQL(), masterVO ,null);
				for(int i=0; i<delCnt.length; i++){
					if(delCnt[i] == Statement.EXECUTE_FAILED){
						throw new DAOException("Fail to delete No"+ i + " SQL");
					}
				}
			}
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
	
	/**
	 * Remove data on detail table<br>
	 * 
	 * @param detailVO
	 * @return int[]
	 * @throws DAOException
	 * @throws Exception
	 */
	public int[] removeCodeMgmtDetailVOs(List<CodeMgmtDetailVO> detailVO) throws DAOException, Exception {
		int delCnt[] = null;
		try{
			SQLExecuter sqlExe = new SQLExecuter("");
			if(detailVO.size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CodeManagementDBDAOCodeMgmtDetailVODSQL(), detailVO, null);
				for(int i=0; i<delCnt.length; i++){
					if(delCnt[i] == Statement.EXECUTE_FAILED){
						throw new DAOException("Fail to delete No"+ i + " SQL");
					}
				}
			}			
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return delCnt;
	}
}
