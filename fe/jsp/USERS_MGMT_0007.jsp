<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : USERS_MGMT_0007.jsp
*@FileTitle : 
*Open Issues :
*Change history :
*@LastModifyDate : 2022.08.10
*@LastModifier :
*@LastVersion : 1.0
* 2022.08.10
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
<%@ page import="com.clt.apps.opus.esm.clv.usersmgmt.event.UsersMgmt0007Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	UsersMgmt0007Event  event = null;			//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			//서버에서 발생한 에러
	String strErrMsg = "";						//에러메세지
	int rowCount	 = 0;						//DB ResultSet 리스트의 건수
	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";
	String strUsr_id		= "";
	String strUsr_nm		= "";
	
	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();


		event = (UsersMgmt0007Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);
		
		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		// Added logic to extract data from server when loading initial screen ..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		
	}catch(Exception e) {
		out.println(e.toString());
	}
%>

<script language="javascript">
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
	<input type="hidden" name="pagerows">
	
	<!-- page_title_area(S) -->
	<div class="page_title_area clear">
		<!-- page_title(S) -->
		<h2 class="page_title"><button type="button"><span id="title">USERS MANAGEMENT</span></button></h2>
		<!-- page_title(E) -->
		
		<!-- opus_design_btn(S) -->
		<div class="opus_design_btn"><!-- 
		--><button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!-- 
		--><button type="button" class="btn_accent" name="btn_New" id="btn_New">New</button><!--  
		--><button type="button" class="btn_accent" name="btn_Save" id="btn_Save">Save</button><!-- 
		--><button type="button" class="btn_normal" name="btn_DownExcel" id="btn_DownExcel">Excel</button>
		</div>
		<!-- opus_design_btn(E) -->
		
		<!-- page_location(S) -->
		<div class="location">
			<span id="navigation"></span>
		</div>
		<!-- page_location(E) -->
		
	</div>
	<!-- page_title_area(E) -->

	<!-- wrap_search(S) -->
	<div class="wrap_search">
		<!-- opus_design_inquiry(S) -->
		<div class="opus_design_inquiry wFit">
			<table>
				<tbody>
					<colgroup>
						<col width="20px">	
						<col width="100px">	
						<col width="100px">
						<col width="100px">
						<col width="75px">									
						<col width="*" />						
					</colgroup>
					<tr>
						<th>User ID</th>
						<td><input type="text" style="width: 100px;"  value="" name="s_user_id" id="s_user_id"></td>
						<th>User Name</th>
						<td><input type="text" style="width: 100px;"  value="" name="s_user_name" id="s_user_name"></td>
						<th>Active</th>
						<td><input type="text" style="width: 100px;"  value="" name="s_active" id="s_active"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- opus_design_inquiry(E) -->
	</div>
	<!-- wrap_search(E) -->

	<!-- wrap_result(S) -->
	<div class="wrap_result">
	    <!-- opus_design_grid(S) -->
	    <div class="opus_design_grid clear">
	        <script language="javascript">ComSheetObject('sheet1');</script>
	    </div>
	    <!-- opus_design_grid(E) -->
	</div>
    <!-- wrap_result(E) -->
</form>