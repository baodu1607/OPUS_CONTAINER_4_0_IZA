/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : UsersManagementVO.java
*@FileTitle : UsersManagementVO
*Open Issues :
*Change history :
*@LastModifyDate : 2022.08.11
*@LastModifier : 
*@LastVersion : 1.0
* 2022.08.11  
* 1.0 Creation
=========================================================*/

package com.clt.apps.opus.esm.clv.usersmgmt.vo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.clt.framework.component.common.AbstractValueObject;
import com.clt.framework.component.util.JSPUtil;

/**
 * Table Value Ojbect<br>
 * 관련 Event 에서 생성, 서버실행요청시 Data 전달역할을 수행하는 Value Object
 *
 * @author 
 * @since J2EE 1.6
 * @see AbstractValueObject
 */

public class UsersManagementVO extends AbstractValueObject {

	private static final long serialVersionUID = 1L;
	
	private Collection<UsersManagementVO> models = new ArrayList<UsersManagementVO>();
	
	/* Column Info */
	private String username = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String startdate = null;
	/* Column Info */
	private String userid = null;
	/* Column Info */
	private String active = null;
	/* Column Info */
	private String enddate = null;
	/* Page Number */
	private String pagerows = null;

	/*	테이블 컬럼의 값을 저장하는 Hashtable */
	private HashMap<String, String> hashColumns = new LinkedHashMap<String, String>();

	/*	테이블 컬럼에 대응되는 멤버변수를 저장하는 Hashtable */
	private HashMap<String, String> hashFields = new LinkedHashMap<String, String>();
	
	public UsersManagementVO() {}

	public UsersManagementVO(String ibflag, String pagerows, String userid, String username, String active, String startdate, String enddate) {
		this.username = username;
		this.ibflag = ibflag;
		this.startdate = startdate;
		this.userid = userid;
		this.active = active;
		this.enddate = enddate;
		this.pagerows = pagerows;
	}
	
	/**
	 * 테이블 컬럼에 저장할 값을 Hashtable<"column_name", "value"> 로 반환
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues(){
		this.hashColumns.put("username", getUsername());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("startdate", getStartdate());
		this.hashColumns.put("userid", getUserid());
		this.hashColumns.put("active", getActive());
		this.hashColumns.put("enddate", getEnddate());
		this.hashColumns.put("pagerows", getPagerows());
		return this.hashColumns;
	}
	
	/**
	 * 컬럼명에 대응되는 멤버변수명을 저장하여 Hashtable<"column_name", "variable"> 로 반환   
	 * @return
	 */
	public HashMap<String, String> getFieldNames(){
		this.hashFields.put("username", "username");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("startdate", "startdate");
		this.hashFields.put("userid", "userid");
		this.hashFields.put("active", "active");
		this.hashFields.put("enddate", "enddate");
		this.hashFields.put("pagerows", "pagerows");
		return this.hashFields;
	}
	
	/**
	 * Column Info
	 * @return username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @return ibflag
	 */
	public String getIbflag() {
		return this.ibflag;
	}
	
	/**
	 * Column Info
	 * @return startdate
	 */
	public String getStartdate() {
		return this.startdate;
	}
	
	/**
	 * Column Info
	 * @return userid
	 */
	public String getUserid() {
		return this.userid;
	}
	
	/**
	 * Column Info
	 * @return active
	 */
	public String getActive() {
		return this.active;
	}
	
	/**
	 * Column Info
	 * @return enddate
	 */
	public String getEnddate() {
		return this.enddate;
	}
	
	/**
	 * Page Number
	 * @return pagerows
	 */
	public String getPagerows() {
		return this.pagerows;
	}
	

	/**
	 * Column Info
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * VO Data Value( C:Creation, U:Update, D:Delete )
	 * @param ibflag
	 */
	public void setIbflag(String ibflag) {
		this.ibflag = ibflag;
	}
	
	/**
	 * Column Info
	 * @param startdate
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	
	/**
	 * Column Info
	 * @param userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/**
	 * Column Info
	 * @param active
	 */
	public void setActive(String active) {
		this.active = active;
	}
	
	/**
	 * Column Info
	 * @param enddate
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	/**
	 * Page Number
	 * @param pagerows
	 */
	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}
	
/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request) {
		fromRequest(request,"");
	}

	/**
	 * Request 의 데이터를 추출하여 VO 의 멤버변수에 설정.
	 * @param request
	 */
	public void fromRequest(HttpServletRequest request, String prefix) {
		setUsername(JSPUtil.getParameter(request, prefix + "username", ""));
		setIbflag(JSPUtil.getParameter(request, prefix + "ibflag", ""));
		setStartdate(JSPUtil.getParameter(request, prefix + "startdate", ""));
		setUserid(JSPUtil.getParameter(request, prefix + "userid", ""));
		setActive(JSPUtil.getParameter(request, prefix + "active", ""));
		setEnddate(JSPUtil.getParameter(request, prefix + "enddate", ""));
		setPagerows(JSPUtil.getParameter(request, prefix + "pagerows", ""));
	}

	/**
	 * Request 의 데이터를 VO 배열로 변환하여 반환.
	 * @param request
	 * @return UsersManagementVO[]
	 */
	public UsersManagementVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Request 넘어온 여러 건 DATA를 VO Class 에 담는다. 
	 * @param request
	 * @param prefix
	 * @return UsersManagementVO[]
	 */
	public UsersManagementVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		UsersManagementVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] username = (JSPUtil.getParameter(request, prefix	+ "username", length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag", length));
			String[] startdate = (JSPUtil.getParameter(request, prefix	+ "startdate", length));
			String[] userid = (JSPUtil.getParameter(request, prefix	+ "userid", length));
			String[] active = (JSPUtil.getParameter(request, prefix	+ "active", length));
			String[] enddate = (JSPUtil.getParameter(request, prefix	+ "enddate", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows", length));
			
			for (int i = 0; i < length; i++) {
				model = new UsersManagementVO();
				if (username[i] != null)
					model.setUsername(username[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (startdate[i] != null)
					model.setStartdate(startdate[i]);
				if (userid[i] != null)
					model.setUserid(userid[i]);
				if (active[i] != null)
					model.setActive(active[i]);
				if (enddate[i] != null)
					model.setEnddate(enddate[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getUsersManagementVOs();
	}

	/**
	 * VO 배열을 반환
	 * @return UsersManagementVO[]
	 */
	public UsersManagementVO[] getUsersManagementVOs(){
		UsersManagementVO[] vos = (UsersManagementVO[])models.toArray(new UsersManagementVO[models.size()]);
		return vos;
	}
	
	/**
	 * VO Class의 내용을 String으로 변환
	 */
	public String toString() {
		   return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE );
	   }

	/**
	* 포맷팅된 문자열에서 특수문자 제거("-","/",",",":")
	*/
	public void unDataFormat(){
		this.username = this.username .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.startdate = this.startdate .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.userid = this.userid .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.active = this.active .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.enddate = this.enddate .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}
}
