/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : UsersMgmtDBDAO.java
*@FileTitle : Users Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.08.10
*@LastModifier : BaoDu
*@LastVersion : 1.0
* 2022.08.10
* 1.0 Creation
=========================================================*/

package com.clt.apps.opus.esm.clv.usersmgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.usersmgmt.vo.UsersManagementVO;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;

/**
 * 
 * 
 * @author BaoDu
 * @see UsersMgmtBCImpl
 */
public class UsersMgmtDBDAO extends DBDAOSupport {
	
	@SuppressWarnings("unchecked")
	public List<UsersManagementVO> searchUser(UsersManagementVO userVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<UsersManagementVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		try{
			if(userVO != null){
				Map<String, String> mapVO = userVO.getColumnValues();
				System.out.println("mapVO " + mapVO);
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new UsersMgmtDBDAOUsersManagementRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, UsersManagementVO .class);
			
		}catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}

	public int[] addUsers(List<UsersManagementVO> userVO) throws DAOException, Exception {
		int insCnt[] = null;
		try{
			SQLExecuter sqlExe = new SQLExecuter("");
			
			if(userVO.size() >0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new UsersMgmtDBDAOUsersManagementVOCSQL(), userVO, null);
				for(int i=0; i<insCnt.length; i++){
					if(insCnt[i] == Statement.EXECUTE_FAILED){
						throw new DAOException("Fail to insert No"+ i + " SQL");
					}
				}
			}
		}catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return insCnt;
	}

	public int[] modifyUsers(List<UsersManagementVO> userVO) throws DAOException, Exception {
		int updCnt[] = null;
		try{
			SQLExecuter sqlExe = new SQLExecuter("");
			if(userVO.size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new UsersMgmtDBDAOUsersManagementVOUSQL(), userVO, null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i] == Statement.EXECUTE_FAILED){
						throw new DAOException("Fail to update No"+ i + " SQL");
					}
				}
			}
		}catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return updCnt;
	}
	
}
