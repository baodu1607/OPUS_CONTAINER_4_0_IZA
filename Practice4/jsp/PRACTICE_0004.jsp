<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE_0004.jsp
*@FileTitle : PRACTICE4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.12
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.12 
* 1.0 Creation
=========================================================*/
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="com.clt.apps.opus.esm.clv.practice4.carriermgmt.event.Practice0004Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	Practice0004Event  event = null;			//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			//서버에서 발생한 에러
	String strErrMsg = "";						//에러메세지
	int rowCount	 = 0;						//DB ResultSet 리스트의 건수

	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";

	String strUsr_id		= "";
	String strUsr_nm		= "";
	Logger log = Logger.getLogger("com.clt.apps.Practice4.CarrierMgmt");
	
	String crrCds = "";
	String lnCds  = "";

	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();


		event = (Practice0004Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		// 초기화면 로딩시 서버로부터 가져온 데이터 추출하는 로직추가 ..
		// English : Additional logic to extract data from the server when loading the initial screen
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		crrCds = eventResponse.getETCData("crrCds");
		lnCds  = eventResponse.getETCData("lnCds");
		
		
	}catch(Exception e) {
		out.println(e.toString());
	}
%>

<script language="javascript">
	var crrCds = "<%=crrCds%>";
	var lnCds = "<%=lnCds%>";
	function setupPage(){
		var errMessage = "<%=strErrMsg%>";
		if (errMessage.length >= 1) {
			ComShowMessage(errMessage);
		}
		loadPage();
	}
</script>

<form name="form">
	<input type="hidden" name="f_cmd">
		
	<div class="page_title_area clear">
		<div class="opus_design_btn">
			<button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!--
			--><button type="button" class="btn_accent" name="btn_New" id="btn_New">New</button><!--
			--><button type="button" class="btn_accent" name="btn_Save" id="btn_Save">Save</button><!--
			--><button type="button" class="btn_accent" name="btn_DownExcel" id="btn_DownExcel">Down Excel</button>
		</div>
	</div>

	<div class="wrap_search">
		<div class="opus_design_inquiry wfit">
			<table>
				<tbody>
					<tr>
						<th width="50">Carrier</th>
						<td width="120">
							<script type="text/javascript">ComComboObject('s_carrier');</script>
						</td>

						<th width="50">Vendor</th>
						<td width="120">
							<input type="text" style="width: 120px;" class="input" value="" maxlength="6" name="s_vndr_seq" id="s_vndr_seq">
						</td>

						<th width="100">Create Date</th>
						<td>
							<input type="text" style="width:120px; text-align:center;" placeholder="YYYY-MM-DD" name="s_cre_dt_fr" id="s_cre_dt_fr" ><!--  
							--><button type="button" class="calendar ir" name="btn_calendar_dt_fr" id="btn_calendar_dt_fr"></button> ~ 
							<input type="text" style="width:120px; text-align:center;" placeholder="YYYY-MM-DD" name="s_cre_dt_to" id="s_cre_dt_to" ><!-- 
							--><button type="button" class="calendar ir" name="btn_calendar_dt_to" id="btn_calendar_dt_to"></button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="wrap_result">
		<div class="opus_design_grid">
			<div class="opus_design_btn">
				<button type="button" class="btn_normal" name="btn_Delete" id="btn_Delete">Row Delete</button><!-- 
				--><button type="button" class="btn_normal" name="btn_Add" id="btn_Add">Row Add</button>
			</div>
			
			<script language="javascript">ComSheetObject('sheet1');</script>
		</div>
	</div>
</form>
