/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : UsersMgmtDBDAOUsersManagementVOUSQL.java
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.08.11
*@LastModifier : 
*@LastVersion : 1.0
* 2022.08.11 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.usersmgmt.integration ;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.clt.framework.support.db.ISQLTemplate;

/**
 *
 * @author Bao
 * @see DAO 참조
 * @since J2EE 1.6
 */

public class UsersMgmtDBDAOUsersManagementVOUSQL implements ISQLTemplate{

	private StringBuffer query = new StringBuffer();
	
	Logger log =Logger.getLogger(this.getClass());
	
	/** Parameters definition in params/param elements */
	private HashMap<String,String[]> params = null;
	
	/**
	  * <pre>
	  * updating users account.
	  * </pre>
	  */
	public UsersMgmtDBDAOUsersManagementVOUSQL(){
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
		params.put("startdate",new String[]{arrTmp[0],arrTmp[1]});

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

		tmp = java.sql.Types.VARCHAR + ",N";
		arrTmp = tmp.split(",");
		if(arrTmp.length !=2){
			throw new IllegalArgumentException();
		}
		params.put("enddate",new String[]{arrTmp[0],arrTmp[1]});

		query.append("/*").append("\n"); 
		query.append("Path : com.clt.apps.opus.esm.clv.usersmgmt.integration ").append("\n"); 
		query.append("FileName : UsersMgmtDBDAOUsersManagementVOUSQL").append("\n"); 
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
		query.append("UPDATE FEUSERMANAGEMENT SET " ).append("\n"); 
		query.append("	ENDDATE= TO_DATE(@[enddate],'YYYY-MM-DD')" ).append("\n"); 
		query.append(",	STARTDATE = TO_DATE(@[startdate],'YYYY-MM-DD')" ).append("\n"); 
		query.append(",	ACTIVE = @[active]" ).append("\n"); 
		query.append(",	USERNAME = @[username]" ).append("\n"); 
		query.append("WHERE	USERID = @[userid]" ).append("\n"); 

	}
}