//array of sheetObject
var sheetObjects = new Array();

//number of sheets
var sheetCnt = 0;

document.onclick = processButtonClick;

function processButtonClick() {
	var sheetObject1 = sheetObjects[0];
	
	var formObj = document.form;
	var srcName = ComGetEvent("name");
	
	if(srcName === null) {
		return;
	}
	
	switch(srcName) {
		case "btn_Retrieve":
			doActionIBSheet(sheetObject1, formObj, IBSEARCH);
			break;
		case "btn_OK":
			comPopupOK();
			break;
		default:
			break;
	}
}

function loadPage() {
	for(var i=0; i < sheetObjects.length; i++){
		ComConfigSheet(sheetObjects[i]);
        initSheet(sheetObjects[i], i + 1);
        ComEndConfigSheet(sheetObjects[i]);
	}
	
	//auto search data after loading finish.
    doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}

function initSheet(sheetObj, sheetNo) {
	var sheetID = sheetObj.id;
	
	switch(sheetID) {
		case "sheet1":
			with(sheetObj) {
				var HeadTitle = "STS|Select|Country|Sequence|Legacy Customer Name|Short Name";
				
				SetConfig( { SearchMode:2, MergeSheet:5, Page:20, FrozenCol:0, DataRowMerge:1 } );
				
				var info    = { Sort:1, ColMove:1, HeaderCheck:0, ColResize:1 };
				var headers = [ { Text:HeadTitle, Align:"Center"} ];
				InitHeaders(headers, info);
				
				var cols = [
							{Type : "Radio",  Hidden:0, Width:50,  Align:"Center", SaveName:"radio",           KeyField:0, UpdateEdit:1, InsertEdit:1}, 
							{Type:"CheckBox", Hidden:0, Width:20,  Align:"Center", SaveName:"checkbox",        KeyField:0, UpdateEdit:1, InsertEdit:1},
							{Type : "Text",   Hidden:0, Width:100, Align:"Center", SaveName:"cust_cnt_cd",     KeyField:1, UpdateEdit:0, InsertEdit:1}, 
							{Type : "Text",   Hidden:0, Width:100, Align:"Center", SaveName:"cust_seq",        KeyField:1, UpdateEdit:0, InsertEdit:1}, 
							{Type : "Text",   Hidden:0, Width:500, Align:"Left",   SaveName:"cust_lgl_eng_nm", KeyField:1, UpdateEdit:0, InsertEdit:1}, 
							{Type : "Text",   Hidden:0, Width:200, Align:"Left",   SaveName:"cust_abbr_nm",    KeyField:0, UpdateEdit:0, InsertEdit:1}
						];
				InitColumns(cols)
		        SetWaitImageVisible(0);
                ComResizeSheet(sheetObjects[0]);
			}
			break;
	}
}


/**
 * Function that add sheet object to array
 */
function setSheetObject(sheet_obj) {
    sheetObjects[sheetCnt++] = sheet_obj;
}

function doActionIBSheet(sheetObj, formObj, sAction) {
	switch(sAction) {
		case IBSEARCH:
			formObj.f_cmd.value = SEARCH01;
			sheetObj.DoSearch("CUS_POPUPGS.do", FormQueryString(formObj));
			break;
		default:
			break;
	}
}








