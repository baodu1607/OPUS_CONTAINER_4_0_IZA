<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0002.jsp
*@FileTitle : Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.11
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.04 
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
<%@ page import="com.clt.apps.opus.esm.clv.doutraining.codemanagement.event.DouTrn0002Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	DouTrn0002Event event = null;
	Exception serverException = null;
	String strErrMsg = "";
	int rowCount	 = 0;
	
	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";
	
	String strUsr_id		= "";
	String strUsr_nm		= "";
	Logger log = Logger.getLogger("com.clt.apps.DouTraining.CodeManagement");
	
	try {
		SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();
		
		event = (DouTrn0002Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);
		
		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}
		
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
	}catch (Exception e) {
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

<form name='form'>
	<input type="hidden" name="f_cmd">
	<input type="hidden" name="pagerows">
	
	<div class="page_title_area clear">
		<h2 class="page_title"><button type="button"><span id="title"></span></button></h2>
		
		<div class="opus_design_btn">
			<button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve" >Retrieve</button><!--
			--><button type="button" class="btn_normal" name="btn_Save" id="btn_Save">Save</button>
		</div>
		
		<div class="location">
			<span id="navigation"></span>
		</div>
	</div>

	<div class="wrap_search" >
		<div class="opus_design_inquiry wFit">
			<table>
		    	<tbody>
					<tr>
						<th width="40">Subsystem</th>
						<td width="120"><input type="text" style="width:100px;" class="input" value="" name="s_ownr_sub_sys_cd" id="s_ownr_sub_sys_cd"></td>
						<th width="40">Cd ID</th>
						<td><input type="text" style="width:100px;" class="input" value="" name="s_intg_cd_id" id="s_intg_cd_id"></td>
					</tr> 
				</tbody>
			</table>
		</div>
	</div>

	<div class="wrap_result" >
		<div class="opus_design_grid">
			<h3 class="title_design">Master</h3>
			<div class="opus_design_btn">
            	<button type="button" class="btn_accent" name="btn_Add" id="btn_Add">Row Add</button>
        	</div>			
		</div>
		<script language="javascript">ComSheetObject('sheet1');</script>
		
		<div class="opus_design_inquiry"><table class="line_bluedot"><tr><td></td></tr></table></div>
		
		<div class="opus_design_grid">
			<h3 class="title_design">Detail</h3>
			<div class="opus_design_btn">
            	<button type="button" class="btn_accent" name="btn_dtl_Add" id="btn_dtl_Add">Row Add</button>
        	</div>		
		</div>
		<script language="javascript">ComSheetObject('sheet2');</script>
	</div>
</form>