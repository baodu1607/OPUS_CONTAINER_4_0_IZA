/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE_0004.js
*@FileTitle : PRACTICE4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.12
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.12 
* 1.0 Creation
=========================================================*/

var sheetObjects = new Array();
var sheetCnt = 0;

var comboObjects = new Array();
var comboCnt = 0;

document.onclick = processButtonClick;

/**
 * Put sheet objects in global variable "sheetObjects"<br>
 * 
 * @param sheet_obj
 */
function setSheetObject(sheet_obj){
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
 * Functions that calls a common function that sets the default settings of the sheet.<br>
 * It is the first called area when fire jsp onload event.<br>
 *  
 */
function loadPage() {
	for(i = 0; i < sheetObjects.length; i++){
		ComConfigSheet(sheetObjects[i]);
		initSheet(sheetObjects[i], i+1);		
		ComEndConfigSheet(sheetObjects[i]);			
	}
	
	for(i = 0; i < comboObjects.length; i++){
		initCombo(comboObjects[i], i+1);
	}
	
	// Searching data after loading finish.
	doActionIBSheet(sheetObjects[0], comboObjects[0], document.form, IBSEARCH);
}

/**
 * Configure sheet<br>
 * 
 * @param sheetObj
 * @param sheetNo
 */
function initSheet(sheetObj, sheetNo) {
	var sheetID = sheetObj.id;
	switch(sheetID){
		case "sheet1":
			with(sheetObj) {
				var HeadTitle = 
					"|Chk|Carrier|Rev. Lane|Vendor Code|Customer Code|Customer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";
				SetConfig( { SearchMode:2 } );
				
				var info    = { Sort:1, ColMove:0, ColResize:1 };            
	            var headers = [ { Text:HeadTitle, Align:"Center"} ];		
	            InitHeaders(headers, info);
				
				/*var cols = [
				            	{Type:"Status",   Hidden:1, Width:50, Align:"Center", SaveName:"ibflag"},
				            	{Type:"DelCheck", Hidden:0, Width:50, Align:"Center", SaveName:"del_chk"},
				            	{Type:"Text",     Hidden:0, Width:100, Align:"Center",  SaveName:"jo_crr_cd",     KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"E", InputCaseSensitive:1, EditLen:3},
						        {Type:"Combo",    Hidden:0, Width:100, Align:"Center",  SaveName:"rlane_cd",      KeyField:1, UpdateEdit:1, InsertEdit:1, ComboCode:lnCds, ComboText: lnCds},
						        {Type:"Float",    Hidden:0, Width:100, Align:"Center",  SaveName:"vndr_seq",      KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"N", EditLen:6},
						        {Type:"Text",     Hidden:0, Width:50,  Align:"Center",  SaveName:"cust_cnt_cd",   KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"E", InputCaseSensitive:1, EditLen:2}, 
							    {Type:"Float",    Hidden:0, Width:100, Align:"Center",  SaveName:"cust_seq",      KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"N", EditLen: 6}, 
							    {Type:"Text", 	  Hidden:0, Width:100, Align:"Center",  SaveName:"trd_cd",        KeyField:0, UpdateEdit:1, InsertEdit:1, AcceptKeys:"E", InputCaseSensitive:1, EditLen:3},
				            	{Type:"Combo",    Hidden:0, Width:70,  Align:"Center",  SaveName:"delt_flg",   	  KeyField:0, UpdateEdit:1, InsertEdit:1, ComboCode:"N|Y",  ComboText:"N|Y"},
				            	{Type:"Text", 	  Hidden:0, Width:200, Align:"Center",  SaveName:"cre_dt",     	  KeyField:0, UpdateEdit:0, InsertEdit:0}, 
							    {Type:"Text", 	  Hidden:0, Width:200, Align:"Left",    SaveName:"cre_usr_id", 	  KeyField:0, UpdateEdit:0, InsertEdit:0}, 
							    {Type:"Text", 	  Hidden:0, Width:200, Align:"Center",  SaveName:"upd_dt",     	  KeyField:0, UpdateEdit:0, InsertEdit:0}, 
							    {Type:"Text", 	  Hidden:0, Width:200, Align:"Left",    SaveName:"upd_usr_id", 	  KeyField:0, UpdateEdit:0, InsertEdit:0}				            					       			            	
				            ];*/
	            
	            var cols = [ 
				            {Type:"Status",    Hidden:1, Width:50,  Align:"Center",  SaveName:"ibflag"}, 
				            {Type:"DelCheck",  Hidden:0, Width:50,  Align:"Center",  SaveName:"del_chk"},
					        {Type:"Popup", Hidden:0, Width:100, Align:"Center",  SaveName:"jo_crr_cd",     KeyField:1, UpdateEdit:0, InsertEdit:1, AcceptKeys:"E", InputCaseSensitive:1, EditLen:3},
					        {Type:"Combo",     Hidden:0, Width:100, Align:"Center",  SaveName:"rlane_cd",      KeyField:1, UpdateEdit:0, InsertEdit:1, ComboCode:lnCds, ComboText: lnCds},
					        {Type:"Popup", Hidden:0, Width:100, Align:"Center",  SaveName:"vndr_seq",      KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"N", EditLen:6},
					        {Type:"Popup",     Hidden:0, Width:50,  Align:"Center",  SaveName:"cust_cnt_cd",   KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"E", InputCaseSensitive:1, EditLen:2}, 
						    {Type:"Popup",     Hidden:0, Width:100, Align:"Center",  SaveName:"cust_seq",      KeyField:1, UpdateEdit:1, InsertEdit:1, AcceptKeys:"N", EditLen: 6}, 
						    {Type:"Popup", Hidden:0, Width:100, Align:"Center",  SaveName:"trd_cd",        KeyField:0, UpdateEdit:1, InsertEdit:1, AcceptKeys:"E", InputCaseSensitive:1, EditLen:3},
						    {Type:"Combo",     Hidden:0, Width:70,  Align:"Center",  SaveName:"delt_flg",      KeyField:0, UpdateEdit:1, InsertEdit:1, ComboCode:"N|Y",  ComboText:"N|Y"}, 
						    {Type:"Text",      Hidden:0, Width:200, Align:"Center",  SaveName:"cre_dt",        KeyField:0, UpdateEdit:0, InsertEdit:0}, 
						    {Type:"Text",      Hidden:0, Width:200, Align:"Left",    SaveName:"cre_usr_id",    KeyField:0, UpdateEdit:0, InsertEdit:0}, 
						    {Type:"Text",      Hidden:0, Width:200, Align:"Center",  SaveName:"upd_dt",        KeyField:0, UpdateEdit:0, InsertEdit:0}, 
						    {Type:"Text",      Hidden:0, Width:200, Align:"Left",    SaveName:"upd_usr_id",    KeyField:0, UpdateEdit:0, InsertEdit:0}
					    ];
	            
				InitColumns(cols);
				SetWaitImageVisible(0);
                ComResizeSheet(sheetObjects[0]);
			}
			break;
		default:
			break;
	}
}

/**
 * Catch click event<br>
 */
function processButtonClick(){
	var sheetObject1 = sheetObjects[0];
	var comboObject1 = comboObjects[0];
	var formObj = document.form;
	var srcName = ComGetEvent("name");
	
	switch(srcName) {
		case "btn_Retrieve":
			doActionIBSheet(sheetObject1, comboObject1, formObj, IBSEARCH);
			break;
		case "btn_calendar_dt_fr":
		case "btn_calendar_dt_to":
			var calendar = new ComCalendar();
			if(srcName === "btn_calendar_dt_fr"){
				calendar.select(formObj.s_cre_dt_fr, "yyyy-MM-dd");
			}else {
				calendar.select(formObj.s_cre_dt_to, "yyyy-MM-dd");
			}	
			break;
		case "btn_Add":
			sheetObject1.DataInsert();
			break;
		case "btn_New":
			doActionIBSheet(sheetObject1, comboObject1, formObj, IBRESET);
			break;
		case "btn_Delete":
			doActionIBSheet(sheetObject1, comboObject1, formObj, IBDELETE);
			break;
		case "btn_Save":
			doActionIBSheet(sheetObject1, comboObject1, formObj, IBSAVE);
			break;
		case "btn_DownExcel":
			doActionIBSheet(sheetObject1, comboObject1, formObj, IBDOWNEXCEL);
			break;
		default:
			break;
	}		
}

/**
 * Define transaction logic between UI and server<br>
 * 
 * @param sheetObj
 * @param formObj
 * @param sAction
 */
function doActionIBSheet(sheetObj, comboObj, formObj, sAction) {
	switch(sAction) {
		case IBSEARCH:
			formObj.f_cmd.value = SEARCH;
			sheetObj.DoSearch("PRACTICE_0004GS.do", FormQueryString(formObj));
			break;
		case IBSAVE:
			formObj.f_cmd.value = MULTI;
			sheetObj.DoSave("PRACTICE_0004GS.do", FormQueryString(formObj));
			break;
		case IBDELETE:
			formObj.f_cmd.value = MULTI;
			sheetObj.SetCellValue(sheetObj.GetSelectRow(), "ibflag", "D"); //SetCellValue(row, col, value)
			sheetObj.DoSave("PRACTICE_0004GS.do", FormQueryString(formObj));
			break;
		case IBRESET:
			formObj.reset();
			sheetObj.RemoveAll();
			checkAllItem(comboObj);
			doActionIBSheet(sheetObj, comboObj, formObj, IBSEARCH);
			break;
		case IBDOWNEXCEL:
			if(sheetObj.RowCount() < 1) {
				ComShowCodeMessage("COM132501");
			}else {
				sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj)});
			}
			break;
		default:
			break;
	}
}

/**
 * Turn on waiting processing effect<br>
 */
function sheet1_OnBeforeSearch() { 
	ComOpenWait(true);
}

/**
 * Turn off waiting processing effect<br>
 */
function sheet1_OnSearchEnd() { 
	ComOpenWait(false);
}

/**
 * Getting value of a cell<br>
 * 
 * @param sheetObj
 * @param colSaveName
 * @returns
 */
function getCellValue(sheetObj, colSaveName){
	return sheetObj.GetCellValue(sheetObj.GetSelectRow(), colSaveName);
}

/**
 * Initial combo box object<br>
 * 
 * @param comboObj
 * @param comboNo
 */
function initCombo(comboObj, comboNo) {
	comboObj.SetTitle("All"); //SetTitle : set the text value that will be displayed on the title row.
	//Set whether the title row will be displayed or not.
	//If set to true, the title row will be displayed on the first row of the drop down list.
	comboObj.SetTitleVisible(true);
	comboObj.SetEnableAllCheckBtn(true); //Enable the button that selects all items of the drop down list
	comboObj.SetMultiSelect(true); //Obj.SetMultiSelect(Value) return boolean, setting value (for get method)
	//console.log("CrrCds " + crrCds);
	//console.log("lnCds " + lnCds);
	addComboItem(comboObj, crrCds);
	checkAllItem(comboObj); //check all for the initial call (loading page)
}

/**
 * Adding items for combo object.<br>
 * 
 * @param comboObj
 * @param comboItems
 */
function addComboItem(comboObj, comboItems) {
	// split() splits a String into an array of substrings, and returns the array
	// Obj.InsertItem(Index, Text, Code): add an item. Set the text value using a "|" separator when
																		// using multi-column.
	/*Index (Integer - Required): row index of the item to add.
	 *Text  (String  - Required): text of the item to add.
	 *Code  (String  - Optional): code of the item to add, if not entered, the Index value is converted
	 *to a string and used as the code value.*/
	
	//console.log("comboItems "+ comboItems);
	comboItems = comboItems.split("|");
	//console.log("comboItems after split " + comboItems);
	for(var i=0; i < comboItems.length; i++){
		comboObj.InsertItem(i, comboItems[i], comboItems[i]);
	}
}

/**
 * Check all combo box items when initial loading page
 * 
 * @param comboObj
 */
function checkAllItem(comboObj) {
	/*Obj.SetItemCheck(Index_Code, Flag, Event) : select a specific item in the check box as an index
	or code value, when MultiSelect is selected.
	
	-Index_Code (String - Required): value of a specific index or code
	-Flag (Boolean - Required): Selection / Clear status
	-Event (Boolean - Optional): Event handler calling status (default: true)*/
	var size = comboObj.GetItemCount();
	for(var i=0; i<size; i++){
		comboObj.SetItemCheck(i, true); //Select the checkbox of the item corresponding to the "i" code		
	}
	// Display "All" keyword for client to notice that all items of combo box is checked
	document.form.s_carrier_text.value = "All";
	document.form.s_carrier.value = "All"; // for checking in DBDAO, improve performing
}

/**
 * Validate date<br>
 */
function isValidDate() {
	var from = new Date(document.form.s_cre_dt_fr.value);
	var to = new Date(document.form.s_cre_dt_to.value);
	return from < to;
}

/**
 * Handle event click on popup item<br>
 * Notice: maybe sDisplay parameter of ComOpenPopup is not as important as we think<br>
 */
function sheet1_OnPopupClick(sheetObj, Row, Col){
	var colName = sheetObj.ColSaveName(Col);
	switch(colName){
		case "jo_crr_cd":
			ComOpenPopup('/opuscntr/COM_ENS_0N1.do', 800, 500, 'setCrrCd', '1,0,0,1,1,1,1,1', true);
			break;
		case "cust_cnt_cd":
		case "cust_seq":
			ComOpenPopup('/opuscntr/CUS_POPUP.do', 800, 500, 'setCustCd', '1,0,1,1,1,1', true);
			break;
		case "vndr_seq":
			ComOpenPopup('/opuscntr/COM_COM_0007.do', 900, 520, 'setVndrCd', '1,0,1', true, false);
			break;
		case "trd_cd":
			ComOpenPopup('/opuscntr/COM_COM_0012.do', 800, 500, 'setTrdCd', '1,0,0,1,1,1,1,1', true);
			break;
	}
}

/**
 * Retrieve customer code from pop-up window<br>
 * 
 * @param aryPopupData
 */
function setCustCd(aryPopupData){
	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "cust_cnt_cd", aryPopupData[0][2]);
	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "cust_seq",    aryPopupData[0][3]);
}

/**
 * Retrieve carrier code from pop-up window<br>
 * When user click OK on pop-up window, this function is fired<br>
 * 
 * @param aryPopupData
 */
function setCrrCd(aryPopupData, row, col, sheetId){
	/*console.log("type "+ typeof(aryPopupData));
	console.log("aryPopupData Carrier code "+ aryPopupData);
	console.log("row "+ row);
	console.log("col "+ col);
	console.log("sheetId "+ sheetId);
	console.log(" data " + aryPopupData[0][3]);*/
	
	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "jo_crr_cd", aryPopupData[0][3], 0);
}

/**
 * Retrieve vendor code from pop-up window<br>
 * 
 * @param aryPopupData
 */
function setVndrCd(aryPopupData){
	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "vndr_seq", aryPopupData[0][2],0);
}

/**
 * Retrieve trade code from pop-up window<br>
 * 
 * @param aryPopupData
 */
function setTrdCd(aryPopupData){
	sheetObjects[0].SetCellValue(sheetObjects[0].GetSelectRow(), "trd_cd", aryPopupData[0][3]);
}



 	