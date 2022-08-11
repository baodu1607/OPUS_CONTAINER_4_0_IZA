/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : USERS_MGMT_0007.js
*@FileTitle : Users Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.08.10
*@LastModifier : BaoDu
*@LastVersion : 1.0
* 2022.08.10 
* 1.0 Creation
=========================================================*/

var sheetObjects = new Array();
var sheetCnt = 0;

var comboObjects = new Array();
var comboCnt = 0;

document.onclick = processButtonClick;

/**
 * Registering IBSheet Object as list adding process for list in case of needing
 * batch processing with other items defining list on the top of source.
 * 
 * @param sheet_obj: Object - sheet object.
 */
function setSheetObject(sheet_obj) {
    sheetObjects[sheetCnt++] = sheet_obj;
}

/**
 * Put combo objects in global variable "comboObjects"<br>
 * 
 * @param combo_obj
 */
function setComboObject(combo_obj) {
	comboObjects[comboCnt++] = combo_obj;
}

/**
 *  This function calls a common function that sets the default settings of the sheet,
 *  It is the first called area when file *jsp onload event.
 */
function loadPage() {  
    for (var i = 0; i < sheetObjects.length; i++) {
        ComConfigSheet(sheetObjects[i]);
        initSheet(sheetObjects[i], i + 1);
        ComEndConfigSheet(sheetObjects[i]);
    }
    
    for(i = 0; i < comboObjects.length; i++){
		initCombo(comboObjects[i]);
	}
    
    doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}

/**
 * Defines the basic properties of the sheet on the screen.
 * 
 * @param sheetObj
 * @param sheetNo
 */
function initSheet(sheetObj, sheetNo) {
	switch(sheetNo) {
		case 1:
			with(sheetObj){
				var HeadTitle = "|User ID|User Name|Active|Start Date|End Date";
				
				SetConfig({SearchMode: 2});
				
				var info = { Sort:0, ColMove:0, HeaderCheck:0, ColResize:1 };
				var headers = [ { Text:HeadTitle, Align:"Center"} ];
				
				InitHeaders(headers, info);
				
				var cols = [
				             {Type:"Status", Hidden:1, Width:30,   Align:"Center",  ColMerge:0,   SaveName:"ibflag" },
				             {Type:"Text",   Hidden:0, Width:100,  Align:"Center",  ColMerge:0,   SaveName:"userid",    KeyField:1, 	UpdateEdit:0,   InsertEdit:1 },
			                 {Type:"Text",   Hidden:0, Width:100,  Align:"Center",  ColMerge:0,   SaveName:"username",  KeyField:1, 	UpdateEdit:1,   InsertEdit:1 },
			                 //{Type:"Combo",  Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"active",    KeyField:0, 	UpdateEdit:1,   InsertEdit:1, ComboText:"Yes|No", ComboCode:"Y|N"},
			                 {Type:"CheckBox",   Hidden:0, Width:50,   Align:"Center",    ColMerge:0,   SaveName:"active",    KeyField:0, 	UpdateEdit:1,   InsertEdit:1, FalseValue: "0"},
			                 {Type:"Date",   Hidden:0, Width:250,  Align:"Left",    ColMerge:0,   SaveName:"startdate", KeyField:1, 	UpdateEdit:1,   InsertEdit:1 },
			                 {Type:"Date",   Hidden:0, Width:250,  Align:"Left",    ColMerge:0,   SaveName:"enddate",   KeyField:0, 	UpdateEdit:1,   InsertEdit:1 }
				            ];
				
				InitColumns(cols);
				SetWaitImageVisible(0);
			}
			break;
			
		default:
			break;
	}
	resizeSheet();
}

/**
 * Resize the sheet.
 */
function resizeSheet() {
    for (var i = 0; i < sheetObjects.length; i++) {
        ComResizeSheet(sheetObjects[i]);
    }
}

/**
 * Initial combo box object<br>
 * 
 * @param comboObj
 * @param comboNo
 */
function initCombo(comboObj){
	with(comboObj){
		SetMultiSelect(true);
		InsertItem(0, "Yes", "Y");	
		InsertItem(1, "No", "N");
	}
}

/**
 * Handle client's click event.
 */
function processButtonClick(){
	var sheetObject1 = sheetObjects[0];
	var formObj = document.form;
	
	try{
		var srcName = ComGetEvent("Name");
		switch(srcName) {
			case "btn_Retrieve":
				doActionIBSheet(sheetObject1, formObj, IBSEARCH);
				break;
				
			case "btn_New":
				sheetObject1.DataInsert();
				break;
				
			case "btn_Save":		
				doActionIBSheet(sheetObject1, formObj, IBSAVE);
				break;
				
			case "btn_DownExcel":
				doActionIBSheet(sheetObject1, formObj, IBDOWNEXCEL);
				break;
				
			default:
				break;
		}
	}catch (e) {
        if (e === "[object Error]") {
            ComShowMessage(OBJECT_ERROR);
        }
        else {
            ComShowMessage(e);
        }
    }
}

/**
 *  Defines the transaction logic between the UI and the server of IBSheet<br>
 *  
 * @param sheetObj
 * @param formObj
 * @param sAction
 */
function doActionIBSheet(sheetObj, formObj, sAction) {
	switch(sAction) {
		case IBSEARCH:
			formObj.f_cmd.value = SEARCH;
			sheetObj.DoSearch("USERS_MGMT_0007GS.do", FormQueryString(formObj));
			var xml = sheetObj.GetSearchData("USERS_MGMT_0007GS.do", FormQueryString(formObj));
			sheetObj.LoadSearchData(xml);
			break;
			
		case IBSAVE:
			formObj.f_cmd.value = MULTI;
			sheetObj.DoSave("USERS_MGMT_0007GS.do", FormQueryString(formObj));
			break;
			
		case IBDOWNEXCEL:
			if(sheetObj.RowCount() < 1){ 
				ComShowCodeMessage("COM132501");
			}else{
				sheetObj.Down2Excel({DownCols: "userid|username|active|startdate|enddate", SheetDesign:1, Merge:1});
			}
			break;
			
		default:
			break;
		
	}
}















