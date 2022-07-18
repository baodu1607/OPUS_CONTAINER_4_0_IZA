/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : CarrierMgmtDBDAOSearchLnCdRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.18
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.18 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.carriermgmt.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Bao
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class CarrierMgmtDBDAOSearchLnCdRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Searching LnCd
	  * </pre>
	  */
	public CarrierMgmtDBDAOSearchLnCdRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.practice4.carriermgmt.integration").append("\n"); 
		query.append("FileName : CarrierMgmtDBDAOSearchLnCdRSQL").append("\n"); 
		query.append("*/").append("\n"); 
	}
	
	public String getSQL(){
		return query.toString();
	}
	
	public HashMap<String,String[]> getParams() {
		return params;
	}

	/**
	 * Query 생성
	 */
	public void setQuery(){
		query.append("SELECT " ).append("\n"); 
		query.append("	VSL_SLAN_CD AS RLANE_CD" ).append("\n"); 
		query.append("FROM MDM_REV_LANE" ).append("\n"); 
		query.append("WHERE	DELT_FLG = 'N'" ).append("\n"); 

	}
}