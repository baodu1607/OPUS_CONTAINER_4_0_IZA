/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : UsersMgmtDBDAOUsersManagementRSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.08.11
*@LastModifier : 
*@LastVersion : 1.0
* 2022.08.11 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.usersmgmt.integration;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Bao
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class UsersMgmtDBDAOUsersManagementRSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * Searching for users account.
	  * </pre>
	  */
	public UsersMgmtDBDAOUsersManagementRSQL(){
		setQuery();
		params = new HashMap<String,String[]>();
		String tmp = null;
		String[] arrTmp = null;
		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("username",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("userid",new String[]{arrTmp[0],arrTmp[1]});

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("active",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.usersmgmt.integration").append("\n"); 
		query.append("FileName : UsersMgmtDBDAOUsersManagementRSQL").append("\n"); 
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
		query.append("	ENDDATE" ).append("\n"); 
		query.append(",	STARTDATE" ).append("\n"); 
		query.append(",	ACTIVE" ).append("\n"); 
		query.append(",	USERNAME" ).append("\n"); 
		query.append(",	USERID" ).append("\n"); 
		query.append("FROM FEUSERMANAGEMENT" ).append("\n"); 
		query.append("WHERE 1=1" ).append("\n"); 
		query.append("#if(${userid} != '')" ).append("\n"); 
		query.append("AND	UPPER(USERID) LIKE '%'||UPPER(@[userid])||'%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if(${username} != '')" ).append("\n"); 
		query.append("AND	UPPER(USERNAME) LIKE '%'||UPPER(@[username])||'%'" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("#if(${active} != '')" ).append("\n"); 
		query.append("AND UPPER(ACTIVE) = UPPER(@[active])" ).append("\n"); 
		query.append("#end" ).append("\n"); 
		query.append("" ).append("\n"); 
		query.append("" ).append("\n"); 

	}
}