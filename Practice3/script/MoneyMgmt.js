/*
 * Partner code: + table name : JOO_INVOICE 
 * 				 + column name: JO_CRR_CD 
 * 				 + Data is got from JOO_CARRIER.JO_CRR_CD
 * Lane: + Only show data when user selects partner
 * 		 + Data is got from JOO_CARRIER.RLANE_CD where JO_CRR_CD = @Partner
 * Trade: + Only show data when user selects partner & Lane 
 * 		  + Data is got from JOO_CARRIER.TRD_CD where JO_CRR_CD = @Partner and RLANE_CD = @Lane
 * 
 */

/*
 * EVENT SPECIFICATION.
 * - (Screen) OnLoad : + Pre:  System retrieves all Partner data.
 * 					   + Post: System sets default month for Year Month. (@Year Month FM : previous month, @Year Month TO: current month)
 * - (Down Excel) OnClick : + If there is no data in grid, then show error message [No_data], then return. 
 *							+ Grid data download to excel file.
 * - (Year Month) OnChange : + Clear data on Grid of Summay & Details tab.
 */

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

var isDbClick = false;
var firstLoad = true;
var searchForDbl = "";

document.onclick = processButtonClick;

function setSheetObject(sheet_obj) {
    sheetObjects[sheetCnt++] = sheet_obj;
}

function setComboObject(combo_obj) {
    comboObjects[comboCnt++] = combo_obj;
}

function setTabObject(tab_obj) {
    tabObjects[tabCnt++] = tab_obj;
}

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
                SetEditable(0);
                SetWaitImageVisible(0);
                ShowSubSum([{ StdCol: "inv_no", SumCols: "7|8", ShowCumulate: 0, CaptionText: " ", CaptionCol: 3 }]);
                //showTotalSum(sheetObj);
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
                SetEditable(0);
                SetWaitImageVisible(0);
                ShowSubSum([{ StdCol: "inv_no", SumCols: "9|10", ShowCumulate: 0, CaptionText: " ", CaptionCol: 3 }]);
                //SetSumFontBold(1);
                //showTotalSum(sheetObj);
                
			}
			
			break;
	}
	resizeSheet();
	
}

function resizeSheet() {
    for (var i = 0; i < sheetObjects.length; i++) {
        ComResizeSheet(sheetObjects[i]);
    }
}

function processButtonClick() {
	var sheetObject1 = sheetObjects[0];
	var sheetObject2 = sheetObjects[1];
	var formObject = document.form;
	
	try {
		var srcName = ComGetEvent("name");
		switch(srcName) {
			case "btn_Retrieve":
				//to-do
				doActionIBSheet(getCurrentSheet(), formObject, IBSEARCH);
				break;
			case "btn_date_fr_up":
				//to-do
				addMonth(formObject.s_date_fr, 1);
				break;
			case "btn_date_fr_down":
				//to-do
				addMonth(formObject.s_date_fr, -1);
				break;
			case "btn_date_to_up":
				//to-do
				addMonth(formObject.s_date_to, 1);
				break;
			case "btn_date_to_down":
				//to-do
				addMonth(formObject.s_date_to, -1);
				break;
			case "btn_New":
                doActionIBSheet(sheetObject1, formObject, IBRESET);
                break;
			case "btn_DownExcel":
                doActionIBSheet(sheetObject1, formObject, IBDOWNEXCEL);
                break;
            case "btn_DownExcel2":
                doActionIBSheet(sheetObject1, formObject, IBDOWNEXCEL2);
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
				
				var xml = sheetObj.GetSearchData("MoneyMgmtGS.do", FormQueryString(formObj));
				sheetObj.LoadSearchData(xml, {abc: 1});
			} else {
				searchDetail = searchSummary;
				var xml = sheetObj.GetSearchData("MoneyMgmtGS.do", searchForDbl);
				sheetObj.LoadSearchData(xml, {abc: 1});
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

function resetForm(formObj) {
	ComOpenWait(true);
	sheetObjects[0].RemoveAll();
	sheetObjects[1].RemoveAll();
	formObj.reset();
	// Need to clear Partner Codes ==> clear Lane, Trade 
	s_partner_code.SetSelectIndex(0);
    s_lane_code.SetEnable(false);
    s_trade_code.SetEnable(false);
	initCalendar();
	ComOpenWait(false);
}

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

function initCalendar() {
	var formObj = document.form;
	var ymTo = ComGetNowInfo("ym", "-");				// careful with the order of ymTo and ymFrom
	var ymFrom = ComGetDateAdd(ymTo + "-01", "M", -1);
	
	formObj.s_date_to.value = ymTo;
    formObj.s_date_fr.value = ymFrom;
}

function addMonth(obj, month) {
	if(obj.value != ""){
		obj.value = ComGetDateAdd(obj.value + "-01", "M", month).substr(0, 7);
	}
	for(var i=0; i<sheetObjects.length; i++){
		sheetObjects[i].RemoveAll();
	}
}

function GetDateFormat(obj, sFormat) {
	//TODO
}

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

function initCombo(comboObj, comboNo) {
	switch(comboNo){
		case 0:
			comboObj.SetDropHeight(250);
			comboObj.SetMultiSelect(1);
			//console.log("partnerCodes " + partnerCodes);
			addComboItem(comboObj, partnerCodes);
			comboObj.SetItemCheck(0, true, false);
			comboObjects[1].SetEnable(false);
            comboObjects[2].SetEnable(false);
			break;
		case 1:
			//console.log("laneCodes " + laneCodes);
			addComboItem(comboObj, laneCodes);
			break;
		case 2:
			//console.log("tradeCodes " + tradeCodes);
			addComboItem(comboObj, tradeCodes);
			break;
	}
}

function addComboItem(comboObj, comboItems) {
	comboItems = comboItems ? comboItems.split("|") : [];
	
	for(var i=0; i<comboItems.length; i++){
		comboObj.InsertItem(i, comboItems[i], comboItems[i]);
	}
}

function checkAllItem(comboObj, status) {
	var size = comboObj.GetItemCount();
	for(var i=1; i<size; i++) { //start from i=1 because the first item is All
		comboObj.SetItemCheck(i, status, false);
	}
}

function isCheckAllItem(comboObj) {
    var size = comboObj.GetItemCount();
    var count = 1;
    for (var i = 1; i < size; i++) { //first item is "All"
        if (comboObj.GetItemCheck(i)) {
            count++;
        }
    }
    return count == size;
}

function s_partner_code_OnCheckClick(comboObj, index, value, checked){
	/*
	 * An event occur when the check box is clicked, if multiple selection is used.
	 * Syntax: function object ID_OnCheckClick(Index, Code)
	 * + Index 	 (Integer) index value of the clicked item.
	 * + Code    (String)  code value of the clicked item.
	 * + Checked (Boolean) the value that specifies whether checked or not.
	 */
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

function s_partner_code_OnBlur() {
    enableLaneCombo(true);
    //enableTradeCombo(true);
}

function s_lane_code_OnChange() {
    enableTradeCombo(true);
}

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

function generateComboData(comboNo) {
	comboObj = comboObjects[comboNo];
	comboObj.RemoveAll();
	formObj = document.form;
	
	
	ComOpenWait(true);
	if(comboNo === 1){ // lane combo-box
		formObj.f_cmd.value = SEARCH02;
		var xml = sheetObjects[0].GetSearchData("MoneyMgmtGS.do", FormQueryString(formObj));
		laneCodes = ComGetEtcData(xml, "laneCodes");
		
		//console.log("generate laneCodes " + laneCodes);
	}else { //trade combo-box
		formObj.f_cmd.value = SEARCH03;
		var xml = sheetObjects[0].GetSearchData("MoneyMgmtGS.do", FormQueryString(formObj));
		tradeCodes = ComGetEtcData(xml, "tradeCodes");
		
		//console.log("generate tradeCodes " + tradeCodes);
	}
	ComOpenWait(false);
	
	initCombo(comboObj, comboNo);
}

function getPartnerValue(){
	return document.form.s_partner_code_text.value;
}

function setPartnerValue(value) {
    document.form.s_partner_code_text.value = value;
    document.form.s_partner_code.value = value;
}

function getLaneValue() {
    return document.form.s_lane_code_text.value;
}

function setLaneValue(value) {
    document.form.s_lane_code_text.value = value;
    document.form.s_lane_code.value = value;
}

function getTradeValue() {
    return document.form.s_trade_code_text.value;
}

function setTradeValue(value) {
    document.form.s_trade_code_text.value = value;
    document.form.s_trade_code.value = value;
}

function sheet1_OnDblClick(sheetObj, Row, Col) {
	formObj = document.form;
	
	if(sheetObj.GetCellValue(Row, "joo_crr_cd") != ""){
		if(searchDetail != searchSummary || sheetObjects[1].RowCount() === 0){
			doActionIBSheet(sheetObjects[1], formObj, IBSEARCH);
		}
		
		//Because these rows are discontinuous ==> using "SaveName"
		var saveNames = ["jo_crr_cd", "rlane_cd", "inv_no", "csr_no", "locl_curr_cd", "prnr_ref_no"];
		var summaryData = getDataRow(sheetObjects[0], Row, saveNames);
		var size = sheetObjects[1].RowCount();
		
		for(var i=2; i<size; i++){
			if(summaryData === getDataRow(sheetObjects[1], i, saveNames)) {
				tabObjects[0].SetSelectedIndex(1);
				sheetObjects[1].SetSelectRow(i);
				return;
			}
		}
		ComShowCodeMessage('COM132701');
	}
}

function getDataRow(sheetObj, row, saveNames) {
	var result = "";
	for (var i = 0; i < saveNames.length; i++) {
		result += sheetObj.GetCellValue(row, saveNames[i]);
	}
	return result;
}

function sheet1_OnSearchEnd(sheetObj) {
	ComOpenWait(false);
	if(sheetObj.RowCount() > 0){
		showTotalSum(sheetObj);
	}
	
	highLightSum(sheetObj);
}

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

function handleOnChangeTab() {
	//TODO
	if(firstLoad) {
		firstLoad = false;
		return;
	}
	
	if(isDbClick){
		isDbClick = false;
		return;
	}
	
	var currentSheet = getCurrentSheet();
	var formQuery = getCurrentSearchOption();
	
	if(searchSummary != formQuery && formQuery != searchDetail) {
		if(confirm(msgs['ESM0004'])) { // search data was changed. do you want to retrieve?
			doActionIBSheet(currentSheet, document.form, IBSEARCH);
		}else {
			return;
		}
	}
	
	if(currentSheet.id === "sheet1") { //summary sheet
		if(searchSummary != formQuery) {
			doActionIBSheet(currentSheet, document.form, IBSEARCH);
		}
	}else {
		if(searchDetail != formQuery) {
			doActionIBSheet(currentSheet, document.form, IBSEARCH);
		}
	}
	
}

function tab1_OnChange(tabObj, nItem) {
	//TODO
	var tabObjects = document.all.item("tabLayer");
	//console.log(tabObjects);
	tabObjects[nItem].style.display = "Inline";
	
	for(var i=0; i<tabObjects.length; i++){
		if(i != nItem) {
			tabObjects[i].style.display = "none";
			tabObjects[beforeTab].style.zIndex = tabObjects[nItem].style.zIndex-1;
		}
	}
	
	beforeTab = nItem;
	handleOnChangeTab();
	resizeSheet();
}

function getCurrentSheet() {
	return sheetObjects[beforeTab];
}

function highLightSum(sheetObj) {
    sheetObj.SetRangeBackColor(2, 0, sheetObj.LastRow() - 2, 12, "white")
    if (sheetObj.RowCount() > 0) {
        var lastRowIndex = sheetObj.LastRow();
        sheetObj.SetRowBackColor(lastRowIndex, "#f68f8c");
        sheetObj.SetRowBackColor(lastRowIndex - 1, "#f68f8c");
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









