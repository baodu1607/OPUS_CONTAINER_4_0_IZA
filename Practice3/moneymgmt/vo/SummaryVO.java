/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : SummaryVO.java
*@FileTitle : SummaryVO
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.20  
* 1.0 Creation
=========================================================*/

package com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo;

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

public class SummaryVO extends AbstractValueObject {

	private static final long serialVersionUID = 1L;
	
	private Collection<SummaryVO> models = new ArrayList<SummaryVO>();
	
	/* Column Info */
	private String csrNo = null;
	/* Column Info */
	private String invRevActAmt = null;
	/* Column Info */
	private String dateTo = null;
	/* Column Info */
	private String loclCurrCd = null;
	/* Column Info */
	private String custVndrSeq = null;
	/* Column Info */
	private String trdCd = null;
	/* Column Info */
	private String joCrrCd = null;
	/* Column Info */
	private String rlaneCd = null;
	/* Column Info */
	private String custVndrCntCd = null;
	/* Column Info */
	private String dateFr = null;
	/* Page Number */
	private String pagerows = null;
	/* Column Info */
	private String invNo = null;
	/* Column Info */
	private String custVndrEngNm = null;
	/* Column Info */
	private String invExpActAmt = null;
	/* VO Data Value( C:Creation, U:Update, D:Delete ) */
	private String ibflag = null;
	/* Column Info */
	private String prnrRefNo = null;
	/* Column Info */
	private String aproFlg = null;

	/*	테이블 컬럼의 값을 저장하는 Hashtable */
	private HashMap<String, String> hashColumns = new LinkedHashMap<String, String>();

	/*	테이블 컬럼에 대응되는 멤버변수를 저장하는 Hashtable */
	private HashMap<String, String> hashFields = new LinkedHashMap<String, String>();
	
	public SummaryVO() {}

	public SummaryVO(String ibflag, String pagerows, String joCrrCd, String rlaneCd, String loclCurrCd, String invNo, String csrNo, String aproFlg, String custVndrCntCd, String custVndrSeq, String prnrRefNo, String custVndrEngNm, String invRevActAmt, String invExpActAmt, String trdCd, String dateFr, String dateTo) {
		this.csrNo = csrNo;
		this.invRevActAmt = invRevActAmt;
		this.dateTo = dateTo;
		this.loclCurrCd = loclCurrCd;
		this.custVndrSeq = custVndrSeq;
		this.trdCd = trdCd;
		this.joCrrCd = joCrrCd;
		this.rlaneCd = rlaneCd;
		this.custVndrCntCd = custVndrCntCd;
		this.dateFr = dateFr;
		this.pagerows = pagerows;
		this.invNo = invNo;
		this.custVndrEngNm = custVndrEngNm;
		this.invExpActAmt = invExpActAmt;
		this.ibflag = ibflag;
		this.prnrRefNo = prnrRefNo;
		this.aproFlg = aproFlg;
	}
	
	/**
	 * 테이블 컬럼에 저장할 값을 Hashtable<"column_name", "value"> 로 반환
	 * @return HashMap
	 */
	public HashMap<String, String> getColumnValues(){
		this.hashColumns.put("csr_no", getCsrNo());
		this.hashColumns.put("inv_rev_act_amt", getInvRevActAmt());
		this.hashColumns.put("date_to", getDateTo());
		this.hashColumns.put("locl_curr_cd", getLoclCurrCd());
		this.hashColumns.put("cust_vndr_seq", getCustVndrSeq());
		this.hashColumns.put("trd_cd", getTrdCd());
		this.hashColumns.put("jo_crr_cd", getJoCrrCd());
		this.hashColumns.put("rlane_cd", getRlaneCd());
		this.hashColumns.put("cust_vndr_cnt_cd", getCustVndrCntCd());
		this.hashColumns.put("date_fr", getDateFr());
		this.hashColumns.put("pagerows", getPagerows());
		this.hashColumns.put("inv_no", getInvNo());
		this.hashColumns.put("cust_vndr_eng_nm", getCustVndrEngNm());
		this.hashColumns.put("inv_exp_act_amt", getInvExpActAmt());
		this.hashColumns.put("ibflag", getIbflag());
		this.hashColumns.put("prnr_ref_no", getPrnrRefNo());
		this.hashColumns.put("apro_flg", getAproFlg());
		return this.hashColumns;
	}
	
	/**
	 * 컬럼명에 대응되는 멤버변수명을 저장하여 Hashtable<"column_name", "variable"> 로 반환   
	 * @return
	 */
	public HashMap<String, String> getFieldNames(){
		this.hashFields.put("csr_no", "csrNo");
		this.hashFields.put("inv_rev_act_amt", "invRevActAmt");
		this.hashFields.put("date_to", "dateTo");
		this.hashFields.put("locl_curr_cd", "loclCurrCd");
		this.hashFields.put("cust_vndr_seq", "custVndrSeq");
		this.hashFields.put("trd_cd", "trdCd");
		this.hashFields.put("jo_crr_cd", "joCrrCd");
		this.hashFields.put("rlane_cd", "rlaneCd");
		this.hashFields.put("cust_vndr_cnt_cd", "custVndrCntCd");
		this.hashFields.put("date_fr", "dateFr");
		this.hashFields.put("pagerows", "pagerows");
		this.hashFields.put("inv_no", "invNo");
		this.hashFields.put("cust_vndr_eng_nm", "custVndrEngNm");
		this.hashFields.put("inv_exp_act_amt", "invExpActAmt");
		this.hashFields.put("ibflag", "ibflag");
		this.hashFields.put("prnr_ref_no", "prnrRefNo");
		this.hashFields.put("apro_flg", "aproFlg");
		return this.hashFields;
	}
	
	/**
	 * Column Info
	 * @return csrNo
	 */
	public String getCsrNo() {
		return this.csrNo;
	}
	
	/**
	 * Column Info
	 * @return invRevActAmt
	 */
	public String getInvRevActAmt() {
		return this.invRevActAmt;
	}
	
	/**
	 * Column Info
	 * @return dateTo
	 */
	public String getDateTo() {
		return this.dateTo;
	}
	
	/**
	 * Column Info
	 * @return loclCurrCd
	 */
	public String getLoclCurrCd() {
		return this.loclCurrCd;
	}
	
	/**
	 * Column Info
	 * @return custVndrSeq
	 */
	public String getCustVndrSeq() {
		return this.custVndrSeq;
	}
	
	/**
	 * Column Info
	 * @return trdCd
	 */
	public String getTrdCd() {
		return this.trdCd;
	}
	
	/**
	 * Column Info
	 * @return joCrrCd
	 */
	public String getJoCrrCd() {
		return this.joCrrCd;
	}
	
	/**
	 * Column Info
	 * @return rlaneCd
	 */
	public String getRlaneCd() {
		return this.rlaneCd;
	}
	
	/**
	 * Column Info
	 * @return custVndrCntCd
	 */
	public String getCustVndrCntCd() {
		return this.custVndrCntCd;
	}
	
	/**
	 * Column Info
	 * @return dateFr
	 */
	public String getDateFr() {
		return this.dateFr;
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
	 * @return invNo
	 */
	public String getInvNo() {
		return this.invNo;
	}
	
	/**
	 * Column Info
	 * @return custVndrEngNm
	 */
	public String getCustVndrEngNm() {
		return this.custVndrEngNm;
	}
	
	/**
	 * Column Info
	 * @return invExpActAmt
	 */
	public String getInvExpActAmt() {
		return this.invExpActAmt;
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
	 * @return prnrRefNo
	 */
	public String getPrnrRefNo() {
		return this.prnrRefNo;
	}
	
	/**
	 * Column Info
	 * @return aproFlg
	 */
	public String getAproFlg() {
		return this.aproFlg;
	}
	

	/**
	 * Column Info
	 * @param csrNo
	 */
	public void setCsrNo(String csrNo) {
		this.csrNo = csrNo;
	}
	
	/**
	 * Column Info
	 * @param invRevActAmt
	 */
	public void setInvRevActAmt(String invRevActAmt) {
		this.invRevActAmt = invRevActAmt;
	}
	
	/**
	 * Column Info
	 * @param dateTo
	 */
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	/**
	 * Column Info
	 * @param loclCurrCd
	 */
	public void setLoclCurrCd(String loclCurrCd) {
		this.loclCurrCd = loclCurrCd;
	}
	
	/**
	 * Column Info
	 * @param custVndrSeq
	 */
	public void setCustVndrSeq(String custVndrSeq) {
		this.custVndrSeq = custVndrSeq;
	}
	
	/**
	 * Column Info
	 * @param trdCd
	 */
	public void setTrdCd(String trdCd) {
		this.trdCd = trdCd;
	}
	
	/**
	 * Column Info
	 * @param joCrrCd
	 */
	public void setJoCrrCd(String joCrrCd) {
		this.joCrrCd = joCrrCd;
	}
	
	/**
	 * Column Info
	 * @param rlaneCd
	 */
	public void setRlaneCd(String rlaneCd) {
		this.rlaneCd = rlaneCd;
	}
	
	/**
	 * Column Info
	 * @param custVndrCntCd
	 */
	public void setCustVndrCntCd(String custVndrCntCd) {
		this.custVndrCntCd = custVndrCntCd;
	}
	
	/**
	 * Column Info
	 * @param dateFr
	 */
	public void setDateFr(String dateFr) {
		this.dateFr = dateFr;
	}
	
	/**
	 * Page Number
	 * @param pagerows
	 */
	public void setPagerows(String pagerows) {
		this.pagerows = pagerows;
	}
	
	/**
	 * Column Info
	 * @param invNo
	 */
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	
	/**
	 * Column Info
	 * @param custVndrEngNm
	 */
	public void setCustVndrEngNm(String custVndrEngNm) {
		this.custVndrEngNm = custVndrEngNm;
	}
	
	/**
	 * Column Info
	 * @param invExpActAmt
	 */
	public void setInvExpActAmt(String invExpActAmt) {
		this.invExpActAmt = invExpActAmt;
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
	 * @param prnrRefNo
	 */
	public void setPrnrRefNo(String prnrRefNo) {
		this.prnrRefNo = prnrRefNo;
	}
	
	/**
	 * Column Info
	 * @param aproFlg
	 */
	public void setAproFlg(String aproFlg) {
		this.aproFlg = aproFlg;
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
		setCsrNo(JSPUtil.getParameter(request, prefix + "csr_no", ""));
		setInvRevActAmt(JSPUtil.getParameter(request, prefix + "inv_rev_act_amt", ""));
		setDateTo(JSPUtil.getParameter(request, prefix + "date_to", ""));
		setLoclCurrCd(JSPUtil.getParameter(request, prefix + "locl_curr_cd", ""));
		setCustVndrSeq(JSPUtil.getParameter(request, prefix + "cust_vndr_seq", ""));
		setTrdCd(JSPUtil.getParameter(request, prefix + "trd_cd", ""));
		setJoCrrCd(JSPUtil.getParameter(request, prefix + "jo_crr_cd", ""));
		setRlaneCd(JSPUtil.getParameter(request, prefix + "rlane_cd", ""));
		setCustVndrCntCd(JSPUtil.getParameter(request, prefix + "cust_vndr_cnt_cd", ""));
		setDateFr(JSPUtil.getParameter(request, prefix + "date_fr", ""));
		setPagerows(JSPUtil.getParameter(request, prefix + "pagerows", ""));
		setInvNo(JSPUtil.getParameter(request, prefix + "inv_no", ""));
		setCustVndrEngNm(JSPUtil.getParameter(request, prefix + "cust_vndr_eng_nm", ""));
		setInvExpActAmt(JSPUtil.getParameter(request, prefix + "inv_exp_act_amt", ""));
		setIbflag(JSPUtil.getParameter(request, prefix + "ibflag", ""));
		setPrnrRefNo(JSPUtil.getParameter(request, prefix + "prnr_ref_no", ""));
		setAproFlg(JSPUtil.getParameter(request, prefix + "apro_flg", ""));
	}

	/**
	 * Request 의 데이터를 VO 배열로 변환하여 반환.
	 * @param request
	 * @return SummaryVO[]
	 */
	public SummaryVO[] fromRequestGrid(HttpServletRequest request) {
		return fromRequestGrid(request, "");
	}

	/**
	 * Request 넘어온 여러 건 DATA를 VO Class 에 담는다. 
	 * @param request
	 * @param prefix
	 * @return SummaryVO[]
	 */
	public SummaryVO[] fromRequestGrid(HttpServletRequest request, String prefix) {
		SummaryVO model = null;
		
		String[] tmp = request.getParameterValues(prefix + "ibflag");
  		if(tmp == null)
   			return null;

  		int length = request.getParameterValues(prefix + "ibflag").length;
  
		try {
			String[] csrNo = (JSPUtil.getParameter(request, prefix	+ "csr_no", length));
			String[] invRevActAmt = (JSPUtil.getParameter(request, prefix	+ "inv_rev_act_amt", length));
			String[] dateTo = (JSPUtil.getParameter(request, prefix	+ "date_to", length));
			String[] loclCurrCd = (JSPUtil.getParameter(request, prefix	+ "locl_curr_cd", length));
			String[] custVndrSeq = (JSPUtil.getParameter(request, prefix	+ "cust_vndr_seq", length));
			String[] trdCd = (JSPUtil.getParameter(request, prefix	+ "trd_cd", length));
			String[] joCrrCd = (JSPUtil.getParameter(request, prefix	+ "jo_crr_cd", length));
			String[] rlaneCd = (JSPUtil.getParameter(request, prefix	+ "rlane_cd", length));
			String[] custVndrCntCd = (JSPUtil.getParameter(request, prefix	+ "cust_vndr_cnt_cd", length));
			String[] dateFr = (JSPUtil.getParameter(request, prefix	+ "date_fr", length));
			String[] pagerows = (JSPUtil.getParameter(request, prefix	+ "pagerows", length));
			String[] invNo = (JSPUtil.getParameter(request, prefix	+ "inv_no", length));
			String[] custVndrEngNm = (JSPUtil.getParameter(request, prefix	+ "cust_vndr_eng_nm", length));
			String[] invExpActAmt = (JSPUtil.getParameter(request, prefix	+ "inv_exp_act_amt", length));
			String[] ibflag = (JSPUtil.getParameter(request, prefix	+ "ibflag", length));
			String[] prnrRefNo = (JSPUtil.getParameter(request, prefix	+ "prnr_ref_no", length));
			String[] aproFlg = (JSPUtil.getParameter(request, prefix	+ "apro_flg", length));
			
			for (int i = 0; i < length; i++) {
				model = new SummaryVO();
				if (csrNo[i] != null)
					model.setCsrNo(csrNo[i]);
				if (invRevActAmt[i] != null)
					model.setInvRevActAmt(invRevActAmt[i]);
				if (dateTo[i] != null)
					model.setDateTo(dateTo[i]);
				if (loclCurrCd[i] != null)
					model.setLoclCurrCd(loclCurrCd[i]);
				if (custVndrSeq[i] != null)
					model.setCustVndrSeq(custVndrSeq[i]);
				if (trdCd[i] != null)
					model.setTrdCd(trdCd[i]);
				if (joCrrCd[i] != null)
					model.setJoCrrCd(joCrrCd[i]);
				if (rlaneCd[i] != null)
					model.setRlaneCd(rlaneCd[i]);
				if (custVndrCntCd[i] != null)
					model.setCustVndrCntCd(custVndrCntCd[i]);
				if (dateFr[i] != null)
					model.setDateFr(dateFr[i]);
				if (pagerows[i] != null)
					model.setPagerows(pagerows[i]);
				if (invNo[i] != null)
					model.setInvNo(invNo[i]);
				if (custVndrEngNm[i] != null)
					model.setCustVndrEngNm(custVndrEngNm[i]);
				if (invExpActAmt[i] != null)
					model.setInvExpActAmt(invExpActAmt[i]);
				if (ibflag[i] != null)
					model.setIbflag(ibflag[i]);
				if (prnrRefNo[i] != null)
					model.setPrnrRefNo(prnrRefNo[i]);
				if (aproFlg[i] != null)
					model.setAproFlg(aproFlg[i]);
				models.add(model);
			}

		} catch (Exception e) {
			return null;
		}
		return getSummaryVOs();
	}

	/**
	 * VO 배열을 반환
	 * @return SummaryVO[]
	 */
	public SummaryVO[] getSummaryVOs(){
		SummaryVO[] vos = (SummaryVO[])models.toArray(new SummaryVO[models.size()]);
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
		this.csrNo = this.csrNo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.invRevActAmt = this.invRevActAmt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.dateTo = this.dateTo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.loclCurrCd = this.loclCurrCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.custVndrSeq = this.custVndrSeq .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.trdCd = this.trdCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.joCrrCd = this.joCrrCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.rlaneCd = this.rlaneCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.custVndrCntCd = this.custVndrCntCd .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.dateFr = this.dateFr .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.pagerows = this.pagerows .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.invNo = this.invNo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.custVndrEngNm = this.custVndrEngNm .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.invExpActAmt = this.invExpActAmt .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.ibflag = this.ibflag .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.prnrRefNo = this.prnrRefNo .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
		this.aproFlg = this.aproFlg .replaceAll(",", "").replaceAll("-", "").replaceAll("/", "").replaceAll(":", "");
	}
}