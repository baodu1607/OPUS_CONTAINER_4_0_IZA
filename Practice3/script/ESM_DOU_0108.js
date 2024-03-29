/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : ESM_DOU_0108.js
*@FileTitle : Money Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.21
*@LastModifier : 
*@LastVersion : 1.0
* 2022.06.07 
* 1.0 Creation
=========================================================*/

var sheetObjects = new Array();
var sheetCnt = 0;

var comboObjects = new Array();
var comboCnt = 0;

var tabObjects = new Array();
var tabCnt = 0;
var beforeTab = 1;

var laneCodes = "";
var tradeCodes = "";

var searchDetail = "";
var searchSummary = "";
var searchForDbl = "";

var isDbClick = false;
var firstLoad = true;

var checkOver3M = false;

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
 * Registering IBCombo Object as list parameter : combo_obj adding process for list
 * in case of needing batch processing with other items defining list on the top of source.
 * 
 * @param combo_obj: Object - combo object.
 */
function setComboObject(combo_obj) {
    comboObjects[comboCnt++] = combo_obj;
}

/**
 * set tab object
 * @param tab_obj : tab object
 */
function setTabObject(tab_obj) {
    tabObjects[tabCnt++] = tab_obj;
}

/**
 * Defines the basic properties of the sheet on the screen<br>
 * 
 * @param sheetObj
 * @param sheetNo
 */
function initSheet(sheetObj, sheetNo) {
	switch(sheetNo) {
		case 1:
			with(sheetObj) {
				var HeadTitle1 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
				var HeadTitle2 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Curr.|Revenue|Expense|Code|Name";
				
				SetConfig({ SearchMode: 2, MergeSheet: 5, Page: 20, FrozenCol: 0, DataRowMerge: 1 });
				
				var info = { Sort: 0, ColMove: 0, HeaderCheck: 0, ColResize: 1 };
                var headers = [{ Text: HeadTitle1, Align: "Center" },
                               { Text: HeadTitle2, Align: "Center" }];
                
                InitHeaders(headers, info);
                
                // This UI only for reporting, so all colunms on Grid should be read only.
                var cols = [
                            { Type: "Status", Hidden: 1, Width: 50,  Align: "Center", ColMerge: 0, SaveName: "ibflag" },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "jo_crr_cd",        KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "rlane_cd",         KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 150, Align: "Center", ColMerge: 0, SaveName: "inv_no",           KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 200, Align: "Center", ColMerge: 0, SaveName: "csr_no",           KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "apro_flg",         KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "locl_curr_cd",     KeyField: 0 },
                            { Type: "Float",  Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "inv_rev_act_amt",  KeyField: 0 },
                            { Type: "Float",  Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "inv_exp_act_amt",  KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "prnr_ref_no",      KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "cust_vndr_eng_nm", KeyField: 0 }
                        ];
                InitColumns(cols);
                SetEditable(1);
                SetWaitImageVisible(0);
                ShowSubSum([{ StdCol: "inv_no", SumCols: "7|8", ShowCumulate: 0, CaptionText: " ", CaptionCol: 3 }]);
                resizeSheet();
			}
			break;
		case 2:
			with(sheetObj) {
				var HeadTitle1 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Rev/Exp|Item|Curr.|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
				var HeadTitle2 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Rev/Exp|Item|Curr.|Revenue|Expense|Code|Name";
				
				SetConfig({ SearchMode: 2, MergeSheet: 5, Page: 20, FrozenCol: 0, DataRowMerge: 1 });
				
				var info = { Sort: 0, ColMove: 0, HeaderCheck: 0, ColResize: 1 };
                var headers = [{ Text: HeadTitle1, Align: "Center" },
                               { Text: HeadTitle2, Align: "Center" }];
                InitHeaders(headers, info);
                
                // This UI only for reporting, so all colunms on Grid should be read only.
                var cols = [
                            { Type: "Status", Hidden: 1, Width: 50,  Align: "Center", ColMerge: 0, SaveName: "ibflag" },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "jo_crr_cd",        KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "rlane_cd",         KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 150, Align: "Center", ColMerge: 0, SaveName: "inv_no",           KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 200, Align: "Center", ColMerge: 0, SaveName: "csr_no",           KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "apro_flg",         KeyField: 0 },
                            { Type: "Combo",  Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "rev_exp",          KeyField: 0, ComboText: "Rev|Exp", ComboCode: "R|E" },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "item",             KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "locl_curr_cd",     KeyField: 0 },
                            { Type: "Float",  Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "inv_rev_act_amt",  KeyField: 0 },
                            { Type: "Float",  Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "inv_exp_act_amt",  KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "prnr_ref_no",      KeyField: 0 },
                            { Type: "Text",   Hidden: 0, Width: 100, Align: "Center", ColMerge: 0, SaveName: "cust_vndr_eng_nm", KeyField: 0 }
                        ];
                InitColumns(cols);
                SetEditable(1);
                SetWaitImageVisible(0);
                ShowSubSum([{ StdCol: "inv_no", SumCols: "9|10", ShowCumulate: 0, CaptionText: " ", CaptionCol: 3 }]);
                resizeSheet();
			}
			break;
	}
	resizeSheet(); //May lead to UI break if we do not call resizeSheet()
	
}

/**
 * Resize the sheet<br>
 */
function resizeSheet() {
    for (var i = 0; i < sheetObjects.length; i++) {
        ComResizeSheet(sheetObjects[i]);
    }
}

/**
 * Catch client's click event<br>
 */
function processButtonClick() {
	var sheetObject1 = sheetObjects[0];
	var sheetObject2 = sheetObjects[1];
	var formObject = document.form;
	
	try {
		var srcName = ComGetEvent("name");
		switch(srcName) {
			case "btn_Retrieve":
				if(!checkOver3M && checkOverThreeMonth()){
				
					if(ComShowCodeConfirm('ESM0002')) {
						// If user clicks okay, then system keep going next step and 
						// will not show this message any more even user clicks retrieve again.
						checkOver3M = true;
					}else {
						break;
					}
				}
				doActionIBSheet(getCurrentSheet(), formObject, IBSEARCH);
				break;
				
			case "btn_date_fr_up":
				if(!isValidDate()){
					ComShowCodeConfirm('ESM0001');
					break;
				} 
				addMonth(formObject.s_date_fr, 1);
				break;
				
			case "btn_date_fr_down":
				addMonth(formObject.s_date_fr, -1);
				break;
				
			case "btn_date_to_up":
				addMonth(formObject.s_date_to, 1);
				break;
				
			case "btn_date_to_down":
				if(!isValidDate()) {
					ComShowCodeConfirm('ESM0001');
					break;
				} 
				addMonth(formObject.s_date_to, -1);
				break;	
				
			case "btn_New":
                doActionIBSheet(sheetObject1, formObject, IBRESET);
                break;
                
			case "btn_DownExcel":
                doActionIBSheet(sheetObject1, formObject, IBDOWNEXCEL);
                break;
                
            case "btn_DownExcel2":
            	ComShowCodeConfirm('ESM0003');
                break;
		}
		
	} catch (e) {
        if (e == "[object Error]") {
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
			if(!isDbClick) {
				if(sheetObj.id === "sheet1"){
					formObj.f_cmd.value = SEARCH;
					searchSummary = getCurrentSearchOption();
					searchForDbl = FormQueryString(formObj);
				}else if(sheetObj.id == "sheet2"){
					formObj.f_cmd.value = SEARCH01;
					searchDetail = getCurrentSearchOption();
				}
				
				var xml = sheetObj.GetSearchData("ESM_DOU_0108GS.do", FormQueryString(formObj));
				sheetObj.LoadSearchData(xml, {Sync: 1});
			} else {
				searchDetail = searchSummary;
				console.log("searchForDbl "+ searchForDbl);
				var xml = sheetObj.GetSearchData("ESM_DOU_0108GS.do", searchForDbl);
				sheetObj.LoadSearchData(xml, {Sync: 1});
			}
			break;
			
		case IBRESET:
			resetForm(formObj);
			break;
			
		case IBDOWNEXCEL:
			 if (sheetObj.RowCount() < 1) {
	                ComShowCodeMessage("COM132501");
	         } else {
	        	 sheetObj.Down2ExcelBuffer(true);
	        	 sheetObj.Down2Excel({ FileName: 'Report', SheetName: ' sheet1', DownCols: makeHiddenSkipCol(sheetObj), SheetDesign: 1, Merge: 1 });
	        	 sheetObjects[1].Down2Excel({SheetName: ' sheet2', DownCols: makeHiddenSkipCol(sheetObjects[1]), Merge: 1});
	        	 sheetObj.Down2ExcelBuffer(false);
	         }
			break;
		
	}
}

/**
 * Collect data for searching<br>
 * 
 * @return currentSearchOption
 */
function getCurrentSearchOption() {
	var currentSearchOption = "";
	
	with (document.form) {
        currentSearchOption += s_date_fr.value;
        currentSearchOption += s_date_to.value;
        currentSearchOption += s_partner_code.value;
        currentSearchOption += s_lane_code.value;
        currentSearchOption += s_trade_code.value;
    }
    return currentSearchOption;
	
}

/**
 * Clear all the client's input condition and records on the grid<br>
 * 
 * @param formObj
 */
function resetForm(formObj) {
	ComOpenWait(true);
	sheetObjects[0].RemoveAll();
	sheetObjects[1].RemoveAll();
	formObj.reset();
	// Clear partner codes ==> clear lane, trade codes
	s_partner_code.SetSelectIndex(0); // "All"
    s_lane_code.SetEnable(false);
    s_trade_code.SetEnable(false);
	initCalendar();
	ComOpenWait(false);
}

/**
 * This function calls a common function that sets the default settings of the sheet,
 * It is the first called area when file *jsp onload event.<br>
 */
function loadPage() {
	initCalendar();
	for(var j = 0; j < comboObjects.length; j++){
		initCombo(comboObjects[j], j);
	}
	for(var i = 0; i < sheetObjects.length; i++){
		ComConfigSheet(sheetObjects[i]);
        initSheet(sheetObjects[i], i + 1);
        ComEndConfigSheet(sheetObjects[i]);
	}
	for(var k = 0; k < tabObjects.length; k++) {
		initTab(tabObjects[k], k+1);
		tabObjects[k].SetSelectedIndex(0);
	}
	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}

/**
 * Define the date<br>
 */
function initCalendar() {
	var formObj = document.form;
	
	var ymTo = ComGetNowInfo("ym", "-"); // careful with the order of ymTo and ymFrom
	formObj.s_date_to.value = ymTo;
     
    var ymFrom = ComGetDateAdd(ymTo, "M", -1);
    ymFrom = ComReplaceStr(ymFrom, "-", "").substring(0, 6);
    formObj.s_date_fr.value = ComGetMaskedValue(ymFrom, "ym");
}

/**
 * Function that define the calendar for client's searching condition<br>
 * 
 * @param obj
 * @param month
 */
function addMonth(obj, month) {
	if(obj.value != ""){
		obj.value = ComGetDateAdd(obj.value + "-01", "M", month).substr(0, 7);
	}
	
	//Requirement: Year Month (OnChange) : clear data on Grid of Summary & Details tab.
	for(var i=0; i<sheetObjects.length; i++){
		sheetObjects[i].RemoveAll();
	}
}

/**
 * Check if the number of months between from_date and to_date is over three months or not<br>
 * 
 * @returns Boolean
 */
function checkOverThreeMonth() {
	var formObj = document.form;
	var fromDate = formObj.s_date_fr.value.replaceStr("-", "") + "01";
	var toDate = formObj.s_date_to.value.replaceStr("-", "") + "01";
	
	return ComGetDaysBetween(fromDate, toDate) > 88 ? true : false;
}

/**
 * check valid date<br>
 * 
 * @returns {Boolean}
 */
function isValidDate() {
	var formObj = document.form;
	var fromDate = formObj.s_date_fr.value.replaceStr("-", "") + "01";
	var toDate = formObj.s_date_to.value.replaceStr("-", "") + "01";
	return ComGetDaysBetween(fromDate, toDate) > 0;
}

/**
 * Initializing the tab object<br>
 * 
 * @param tabObj
 * @param tabNo
 */
function initTab(tabObj, tabNo) {
	switch(tabNo) {
		case 1:
			with(tabObj) {
				InsertItem("Summary", "");
				InsertItem("Detail", "");
			}
			break;
	}
}

/**
 * Initializing the combo-box objects<br>
 * 
 * @param comboObj
 * @param comboNo
 */
function initCombo(comboObj, comboNo) {
	switch(comboNo){
		case 0:
			comboObj.SetDropHeight(250);
			comboObj.SetMultiSelect(1);
			addComboItem(comboObj, partnerCodes);
			comboObj.SetItemCheck(0, true, false);
			comboObjects[1].SetEnable(false);
            comboObjects[2].SetEnable(false);
			break;
		case 1:
			addComboItem(comboObj, laneCodes);
			break;
		case 2:
			addComboItem(comboObj, tradeCodes);
			break;
	}
}

/**
 * Add items to combo-box<br>
 * 
 * @param comboObj
 * @param comboItems
 */
function addComboItem(comboObj, comboItems) {
	comboItems = comboItems ? comboItems.split("|") : [];
	
	for(var i=0; i<comboItems.length; i++){
		comboObj.InsertItem(i, comboItems[i], comboItems[i]);
	}
}

/**
 * Checking all the items of combo-box<br>
 * 
 * @param comboObj
 * @param status
 */
function checkAllItem(comboObj, status) {
	var size = comboObj.GetItemCount();
	
	for(var i=1; i<size; i++) { //start from i=1 because the first item is "All"
		comboObj.SetItemCheck(i, status, false);
	}
}

/**
 * Checking whether all the items of combo-box is checked<br>
 * 
 * @param comboObj
 * @returns {Boolean}
 */
function isCheckAllItem(comboObj) {
    var size = comboObj.GetItemCount(); // the total of items of combo-box.
    var count = 1;
    for (var i = 1; i < size; i++) {    // first item is "All" so we skip it.
        if (comboObj.GetItemCheck(i)) {
            count++; 					// the items that gets checked.
        }
    }
    return count == size;
}

/**
 * Event fired when the user checks the items of Partner Combo-box<br>
 * 
 * @param comboObj
 * @param index
 * @param value
 * @param checked
 */
function s_partner_code_OnCheckClick(comboObj, index, value, checked){
	if(checked){
		if(value === "All"){
			checkAllItem(comboObj, false);
			
		}else if(comboObj.GetItemCheck(0)){
			comboObj.SetItemCheck(0, false, false);
			
		}else if(isCheckAllItem(comboObj)){
			checkAllItem(comboObj, false);
			comboObj.SetItemCheck(0, true, false);
		}
	}else if(getPartnerValue() === ""){
		comboObj.SetItemCheck(0, true, false);
	}
	
	enableLaneCombo();
    enableTradeCombo();
}

/**
 * Event fired when the user checks the items of the Partner combo-box and click outside the combo-box,
 * it will automatically enable Lane combo-box<br>
 */
function s_partner_code_OnBlur() {
    enableLaneCombo(true);
}

/**
 * Event fired when there are any changes on Trade combo-box,
 * it will automatically enable the Trade combo-box<br>
 */
function s_lane_code_OnChange() {
    enableTradeCombo(true);
}

/**
 * Enable Lane combo-box<br>
 * 
 * @param generate
 */
function enableLaneCombo(generate){
	setLaneValue("");
	var partnerValue = getPartnerValue();
	
	if(partnerValue === "All"){
		comboObjects[1].SetEnable(false);
	}else {
		comboObjects[1].SetEnable(true);
		if(generate){
			generateComboData(1);
		}
	}
	
}

/**
 * Enable Trade combo-box<br>
 * 
 * @param generate
 */
function enableTradeCombo(generate){
	setTradeValue("");
	var laneValue = getLaneValue();
	
	if(laneValue === ""){
		comboObjects[2].SetEnable(false);
		setTradeValue("");
	}else {
		comboObjects[2].SetEnable(true);
		if(generate){
			generateComboData(2);
		}
	}
	
}

/**
 * Generating data for combo-box<br>
 * 
 * @param comboNo
 */
function generateComboData(comboNo) {
	ComOpenWait(true);
	comboObj = comboObjects[comboNo];
	comboObj.RemoveAll(); 
	formObj = document.form;
	
	if(comboNo === 1){ // lane combo-box
		formObj.f_cmd.value = SEARCH02;
		var xml = sheetObjects[0].GetSearchData("ESM_DOU_0108GS.do", FormQueryString(formObj));
		laneCodes = ComGetEtcData(xml, "laneCodes");
		
	}else { //trade combo-box
		formObj.f_cmd.value = SEARCH03;
		var xml = sheetObjects[0].GetSearchData("ESM_DOU_0108GS.do", FormQueryString(formObj));
		tradeCodes = ComGetEtcData(xml, "tradeCodes");
	}
	
	initCombo(comboObj, comboNo);
	ComOpenWait(false);
}

/**
 * Get value of partner combo
 */
function getPartnerValue(){
	return document.form.s_partner_code_text.value;
}

/**
 * Set value for partner combo
 * @param value
 */
function setPartnerValue(value) {
    document.form.s_partner_code_text.value = value;
    document.form.s_partner_code.value = value;
}

/**
 * Get value of lane combo
 */
function getLaneValue() {
    return document.form.s_lane_code_text.value;
}

/**
 * Set value for lane combo
 * @param value
 */
function setLaneValue(value) {
    document.form.s_lane_code_text.value = value;
    document.form.s_lane_code.value = value;
}

/**
 * Get value of trade combo
 */
function getTradeValue() {
    return document.form.s_trade_code_text.value;
}

/**
 * Set value for lane combo
 * @param value
 */
function setTradeValue(value) {
    document.form.s_trade_code_text.value = value;
    document.form.s_trade_code.value = value;
}

/**
 * Event fires when user double click on sheet 1
 * 
 * @param sheetObj
 * @param Row
 * @param Col
 */
function sheet1_OnDblClick(sheetObj, Row, Col) {
	formObj = document.form;
	isDbClick = true;
	
	if(sheetObj.GetCellValue(Row, "joo_crr_cd") != ""){
		if(searchDetail != searchSummary || sheetObjects[1].RowCount() === 0){
			doActionIBSheet(sheetObjects[1], formObj, IBSEARCH);
		}
		
		//Because we only chooses some of these rows but they are discontinuous ==> using "SaveName"
		var columnNames = ["jo_crr_cd", "rlane_cd", "inv_no", "csr_no", "locl_curr_cd", "prnr_ref_no"];
		var summaryData = getDataRow(sheetObjects[0], Row, columnNames); //only one row
		var detailRowCount = sheetObjects[1].RowCount(); 
		
		for(var i=2; i<detailRowCount; i++){
			 /*Find the record on the Detail that matched with the Summary <br>
			  *Requirement : if user double click on 1 invoice, then system automatically change to 
			  *detail tab and select to the right row for user checking.*/
			if(summaryData === getDataRow(sheetObjects[1], i, columnNames)) {
				tabObjects[0].SetSelectedIndex(1);
				sheetObjects[1].SetSelectRow(i);
				return;
			}
		}
		console.log("Continue");
		ComShowCodeMessage('COM132701'); //There is no data to search
	}
}

/**
 * Collect data of a specific row<br>
 * 
 * @param sheetObj
 * @param row
 * @param saveNames : names of column
 * @returns result
 */
function getDataRow(sheetObj, row, saveNames) {
	var result = "";
	for (var i = 0; i < saveNames.length; i++) {
		result += sheetObj.GetCellValue(row, saveNames[i]);
	}
	console.log("result " + result);
	return result;
}

/**
 * Event fired after searching<br>
 * 
 * @param sheetObj
 */
function sheet1_OnSearchEnd(sheetObj) {
	ComOpenWait(false);
	if(sheetObj.RowCount() > 0){
		showTotalSum(sheetObj);
	}
	highLightSum(sheetObj);
}

/**
 * Event fired after searching<br>
 * @param sheetObj
 */
function sheet2_OnSearchEnd(sheetObj) {
	ComOpenWait(false);
	if(sheetObj.RowCount() > 0){
		showTotalSum(sheetObj);
	}
	highLightSum(sheetObj);
}

/**
 * Show total sum<br>
 * 
 * @param sheetObj
 */
function showTotalSum(sheetObj) {
	var revTotalVND = 0; // Revenue in VND
	var expTotalVND = 0; // Expense in VND
	var revTotalUSD = 0; // in USD
	var expTotalUSD = 0; // in USD
	
	var subSum = sheetObj.FindSubSumRow();
	var arrSubsum = subSum.split("|");
	
	for(var i=0; i<arrSubsum.length; i++) {
		var locl_curr_cd = sheetObj.GetCellValue(arrSubsum[i]-1, "locl_curr_cd");
		sheetObj.SetCellValue(arrSubsum[i], "locl_curr_cd", locl_curr_cd);
		sheetObj.SetCellFont("FontBold", arrSubsum[i], "locl_curr_cd", arrSubsum[i], "inv_exp_act_amt", 1);
		if(locl_curr_cd === "VND") {
			revTotalVND += sheetObj.GetCellValue(arrSubsum[i], "inv_rev_act_amt");
			expTotalVND += sheetObj.GetCellValue(arrSubsum[i], "inv_exp_act_amt");
		} else {
			revTotalUSD += sheetObj.GetCellValue(arrSubsum[i], "inv_rev_act_amt");
            expTotalUSD += sheetObj.GetCellValue(arrSubsum[i], "inv_exp_act_amt");
		}
	}
	
	sheetObj.DataInsert(-1);
    sheetObj.SetCellValue(sheetObj.LastRow(), "locl_curr_cd", "VND");
    sheetObj.SetCellValue(sheetObj.LastRow(), "inv_rev_act_amt", revTotalVND);
    sheetObj.SetCellValue(sheetObj.LastRow(), "inv_exp_act_amt", expTotalVND);
    sheetObj.SetCellValue(sheetObj.LastRow(), "rev_exp", "");

    sheetObj.DataInsert(-1);
    sheetObj.SetCellValue(sheetObj.LastRow(), "locl_curr_cd", "USD");
    sheetObj.SetCellValue(sheetObj.LastRow(), "inv_rev_act_amt", revTotalUSD);
    sheetObj.SetCellValue(sheetObj.LastRow(), "inv_exp_act_amt", expTotalUSD);
    sheetObj.SetCellValue(sheetObj.LastRow(), "rev_exp", "");
    
    sheetObj.SetSelectRow(-1);
}

/**
 * Handle change tab<br>
 */
function handleOnChangeTab() {
	if(firstLoad) {
		console.log("firstLoad OnChangeTab");
		firstLoad = false;
		return;
	}
	
	if(isDbClick){
		console.log("isDbClick OnChangeTab");
		isDbClick = false;
		return;
	}
	
	var currentSheet = getCurrentSheet();
	var formQuery = getCurrentSearchOption(); //partner, lane, trade
	console.log("formQuery " + formQuery);
	console.log("searchSummary " + searchSummary);
	console.log("searchDetail " + searchDetail);
	console.log("searchForDbl " + searchForDbl);
	
	if(searchSummary != formQuery && formQuery != searchDetail) {
		if(ComShowCodeConfirm('ESM0004')) { // search data was changed. do you want to retrieve?
			doActionIBSheet(currentSheet, document.form, IBSEARCH);
		}else {
			return;
		}
	}
	
	if(currentSheet.id === "sheet1") { //summary sheet
		console.log("searching for summary.")
		if(searchSummary != formQuery) {
			console.log("searchSummary !== formQuery");
			doActionIBSheet(currentSheet, document.form, IBSEARCH);
		}
	}else { //detail sheet
		console.log("searching for detail.")
		if(searchDetail != formQuery) {
			console.log("searchDetail !== formQuery");
			doActionIBSheet(currentSheet, document.form, IBSEARCH);
		}
	}
	
}

/**
 * Event when clicking tab activating selected tab items<br>
 * 
 * @param tabObj
 * @param nItem
 */
function tab1_OnChange(tabObj, nItem) {
	console.log("Change tab");
	console.log("nItem "+ nItem);
	var tabObjects = document.all.item("tabLayer");
	ComShowObject(tabObjects[nItem], true);
	
	for(var i=0; i<tabObjects.length; i++){
		if(i != nItem) {
			ComShowObject(tabObjects[i], false);
		}
	}
	
	beforeTab = nItem;
	handleOnChangeTab();
	resizeSheet();
}

/**
 * Get current sheet<br>
 * 
 * @returns
 */
function getCurrentSheet() {
	return sheetObjects[beforeTab];
}

/**
 * Highlight the sum row<br>
 * @param sheetObj
 */
function highLightSum(sheetObj) {
    sheetObj.SetRangeBackColor(2, 0, sheetObj.LastRow() - 2, 12, COLOR_WHITE);
    if (sheetObj.RowCount() > 0) {
        var lastRowIndex = sheetObj.LastRow();
        sheetObj.SetRowBackColor(lastRowIndex, COLOR_SUB_SUM);
        sheetObj.SetRowBackColor(lastRowIndex - 1, COLOR_SUB_SUM);
        
        if (sheetObj.id == "sheet1") {
            for (var i = 6; i <= 8; i++) {
                sheetObj.SetCellFontBold(lastRowIndex, i, 1);
                sheetObj.SetCellFontBold(lastRowIndex - 1, i, 1);
            }
        } else {
            for (var i = 8; i <= 10; i++) {
                sheetObj.SetCellFontBold(lastRowIndex, i, 1);
                sheetObj.SetCellFontBold(lastRowIndex - 1, i, 1);
            }
        }
    }
}








