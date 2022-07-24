/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : MoneyMgmtDBDAOSearchPartnerCodeRSQL.java
*@FileTitle : MoneyManagement
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.18
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.18 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice3.moneymgmt.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Bao
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class MoneyMgmtDBDAOSearchPartnerCodeRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * searching for partner codes
	  * </pre>
	  */
	public MoneyMgmtDBDAOSearchPartnerCodeRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.practice3.moneymgmt.integration").append("\n"); 
		query.append("FileName : MoneyMgmtDBDAOSearchPartnerCodeRSQL").append("\n"); 
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
		query.append("select " ).append("\n"); 
		query.append("	distinct(jo_crr_cd) " ).append("\n"); 
		query.append("from joo_carrier" ).append("\n"); 

	}
}