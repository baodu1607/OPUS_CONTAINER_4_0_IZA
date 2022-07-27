/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierMgmtDBDAO.java
*@FileTitle : PRACTICE4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.12
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.12 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.carriermgmt.integration;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clt.apps.opus.esm.clv.practice4.carriermgmt.basic.CarrierMgmtBCImpl;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.component.rowset.DBRowSet;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.db.ISQLTemplate;
import com.clt.framework.support.db.RowSetUtil;
import com.clt.framework.support.db.SQLExecuter;
import com.clt.framework.support.layer.integration.DBDAOSupport;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierListVO;


/**
 * ALPS CarrierMgmtDBDAO <br>
 * - ALPS-Practice4 system Business Logic을 처리하기 위한 JDBC 작업수행.<br>
 * 
 * @author Bao
 * @see CarrierMgmtBCImpl 참조
 * @since J2EE 1.6
 */
public class CarrierMgmtDBDAO extends DBDAOSupport {

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param CarrierListVO carrierListVO
	 * @return List<CarrierListVO>
	 * @exception DAOException
	 */
	 @SuppressWarnings("unchecked")
	public List<CarrierListVO> searchCarrierListVO(CarrierListVO carrierListVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierListVO> list = null;
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();		
		//carrier codes that receive from combo box 		
		List<String> crrCds = new ArrayList<String>();
		
		try{
			
			if(carrierListVO != null){
				Map<String, String> mapVO = carrierListVO .getColumnValues();
				if(!(carrierListVO.getJoCrrCd().isEmpty() && "All".equalsIgnoreCase(carrierListVO.getJoCrrCd()))){
					crrCds=Arrays.asList(carrierListVO.getJoCrrCd().split(","));
				}
				
				param.put("crrCds",crrCds);
				param.putAll(mapVO);
				
				velParam.put("crrCds",crrCds);
				velParam.putAll(mapVO);
			}
			
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierMgmtDBDAOCarrierListVORSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierListVO .class);
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param CarrierListVO carrierListVO
	 * @exception DAOException
	 * @exception Exception
	 */
	public void addCarrierListVO(CarrierListVO carrierListVO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			Map<String, String> mapVO = carrierListVO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			int result = sqlExe.executeUpdate((ISQLTemplate)new CarrierMgmtDBDAOCarrierListVOCSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param CarrierListVO carrierListVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int modifyCarrierListVO(CarrierListVO carrierListVO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		int result = 0;
		try {
			Map<String, String> mapVO = carrierListVO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate((ISQLTemplate)new CarrierMgmtDBDAOCarrierListVOUSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param CarrierListVO carrierListVO
	 * @return int
	 * @exception DAOException
	 * @exception Exception
	 */
	public int removeCarrierListVO(CarrierListVO carrierListVO) throws DAOException,Exception {
		//query parameter
		Map<String, Object> param = new HashMap<String, Object>();
		//velocity parameter
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		int result = 0;
		try {
			Map<String, String> mapVO = carrierListVO .getColumnValues();
			
			param.putAll(mapVO);
			velParam.putAll(mapVO);
			
			SQLExecuter sqlExe = new SQLExecuter("");
			result = sqlExe.executeUpdate((ISQLTemplate)new CarrierMgmtDBDAOCarrierListVODSQL(), param, velParam);
			if(result == Statement.EXECUTE_FAILED)
					throw new DAOException("Fail to insert SQL");
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return result;
	}

	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<CarrierListVO> carrierListVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] addCarrierListVOS(List<CarrierListVO> carrierListVO) throws DAOException,Exception {
		int insCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierListVO .size() > 0){
				insCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOCarrierListVOCSQL(), carrierListVO,null);
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
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<CarrierListVO> carrierListVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] modifyCarrierListVOS(List<CarrierListVO> carrierListVO) throws DAOException,Exception {
		int updCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierListVO .size() > 0){
				updCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOCarrierListVOUSQL(), carrierListVO,null);
				for(int i = 0; i < updCnt.length; i++){
					if(updCnt[i]== Statement.EXECUTE_FAILED)
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
		return updCnt;
	}
	
	/**
	 * [처리대상] 정보를 [행위] 합니다.<br>
	 * 
	 * @param List<CarrierListVO> carrierListVO
	 * @return int[]
	 * @exception DAOException
	 * @exception Exception
	 */
	public int[] removeCarrierListVOS(List<CarrierListVO> carrierListVO) throws DAOException,Exception {
		int delCnt[] = null;
		try {
			SQLExecuter sqlExe = new SQLExecuter("");
			if(carrierListVO .size() > 0){
				delCnt = sqlExe.executeBatch((ISQLTemplate)new CarrierMgmtDBDAOCarrierListVODSQL(), carrierListVO,null);
				for(int i = 0; i < delCnt.length; i++){
					if(delCnt[i]== Statement.EXECUTE_FAILED)
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
		return delCnt;
	}
	
	/**
	 * Getting carrier codes<br>
	 * 
	 * @return List<CarrierListVO>
	 * @throws DAOException
	 */
	public List<CarrierListVO> getCrrCds() throws DAOException {
		//Store value that return from database
		DBRowSet dbRowset = null;
		List<CarrierListVO> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		try {
			//do query here
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierMgmtDBDAOSearchCrrCdRSQL(), param, velParam);
			//Convert dbRowset to list
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierListVO .class);
		} catch(SQLException se) {
			//Logging exception
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		return list;
	}
	
	/**
	 * Getting lane codes<br>
	 * 
	 * @return List<CarrierListVO>
	 * @throws DAOException
	 */
	public List<CarrierListVO> getLnCds() throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierListVO> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();
		try {
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierMgmtDBDAOSearchLnCdRSQL(), param, velParam);
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierListVO .class);
		} catch(SQLException se) {
			log.error(se.getMessage(),se);
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
				
		return list;
	}
	
	public List<CarrierListVO> searchCust(CarrierListVO carrierListVO) throws DAOException {
		DBRowSet dbRowset = null;
		List<CarrierListVO> list = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> velParam = new HashMap<String, Object>();
		
		try{
			if(carrierListVO != null){
				Map<String, String> mapVO = carrierListVO .getColumnValues();
				
				param.putAll(mapVO);
				velParam.putAll(mapVO);
			}
			//Query
			dbRowset = new SQLExecuter("").executeQuery((ISQLTemplate)new CarrierMgmtDBDAOSearchCustRSQL(), param, velParam);
			//Convert dbRowset to list
			list = (List)RowSetUtil.rowSetToVOs(dbRowset, CarrierListVO.class);
		} catch(SQLException se) {
			//Logging exception
			log.error(se.getMessage(),se);
			//throw new Exception
			throw new DAOException(new ErrorHandler(se).getMessage());
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			throw new DAOException(new ErrorHandler(ex).getMessage());
		}
		
		return list;
	}
	
}