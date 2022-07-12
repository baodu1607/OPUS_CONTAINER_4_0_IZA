/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0002.js
*@FileTitle : Practice 2
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.11
*@LastModifier : Bao Du
*@LastVersion : 1.0
* 2022.07.04 
* 1.0 Creation
=========================================================*/

/* necessary */
var sheetObjects = new Array();
var sheetCnt = 0;

document.onclick = processButtonClick;
/**
 * Branching to the corresponding login when a button on the screen is pressed.
 * 
 */
function processButtonClick() {
	try {
		var formObj = document.form;
		var sheetObject1 = sheetObjects[0];
		var sheetObject2 = sheetObjects[1];
		var srcName = ComGetEvent("name");
		//console.log("Event name: " + srcName);
		switch (srcName) {
			case "btn_Retrieve":
				doActionIBSheet(sheetObjects[0], formObj, IBSEARCH);
				break;
			case "btn_Save":
				// Need to find out where the data is input that is sheetObject1 or sheetObject2
				// Find the the changes in row that means there are new data
				//doActionIBSheet(sheetObject1, formObj, IBSAVE);
				//doActionIBSheet(sheetObject2, formObj, IBSAVE);
				/* If we do not use the if condition here, there will always a popup after click save button
					because at that time to function will be called at the same time
					but only one sheet contained data, other may or may not contain data.
				*/
				/* IsDataModified() check transaction status of data rows.
				 * If any data is in transaction status other than "Search", 1 is returned; otherwise, 0 is returned*/
				
				var sheet1RowStatus = sheetObject1.IsDataModified();
				var sheet2RowStatus = sheetObject2.IsDataModified();
				if(sheet1RowStatus === 0 && sheet2RowStatus === 0){
					ComShowCodeMessage("COM130503");
					break; 
				}
				
				/*if((sheet1RowStatus&&sheet2RowStatus)||sheet1RowStatus){
					//both sheet1 and sheet2 contain transaction status other than search
					//sheet1 contain transaction status other than search
					doActionIBSheet(sheetObject1, formObj, IBSAVE);
				}else {
//					console.log(sheetObject2.GetSaveString());
					doActionIBSheet(sheetObject2, formObj, IBSAVE);
				}*/
				
				if(sheet1RowStatus){
					doActionIBSheet(sheetObject1, formObj, IBSAVE);
				}else if(sheet2RowStatus){
					doActionIBSheet(sheetObject2, formObj, IBSAVE);
				}				
				break;
				
			case "btn_Add":
				sheetObject1.DataInsert();
				break;
			
			case "btn_dtl_Add":
				var selectRow=sheetObject1.GetSelectRow(); //index of current select row
				var intg_cd_id=sheetObject1.GetCellValue(selectRow,"intg_cd_id");
				sheetObject2.DataInsert(-1); //last row
				var row=sheetObject2.GetSelectRow();
				//console.log(row);
				sheetObject2.SetCellValue(row,"intg_cd_id",intg_cd_id);
			break;

			default:
				break;
				
		}	
	} catch (e) {
		if (e == "[object Error]") {
			ComShowCodeMessage('JOO00001');
		} else {
			ComShowMessage(e.message);
		}
	}
}

/**
 * Put sheet objects in global variable "sheetObjects"<br>
 * 
 * @param sheet_obj
 */
function setSheetObject(sheet_obj){
	sheetObjects[sheetCnt++]=sheet_obj;
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
	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
}

/**
 * Define the basic properties of the sheet on the screen,
 * for example Column information, sheet basic attributes, etc.
 * 
 * @param sheetObj
 * @param sheetNo
 */
function initSheet(sheetObj, sheetNo) {
    var cnt=0;
	var sheetID=sheetObj.id;
    switch(sheetID) {
        case "sheet1":
          with(sheetObj){
            var HeadTitle=
            	"|Del|Subsystem|Cd ID|Cd Name|Length|Cd Type|Table Name|Description Remark|Flag|Create User|Create Date|Update User|Update Date";
            var headCount = ComCountHeadTitle(HeadTitle);           
            SetConfig({SearchMode: 2});            
            var info    = { Sort:1, ColMove:0, ColResize:1 };            
            var headers = [ { Text:HeadTitle, Align:"Center"} ];
            
            InitHeaders(headers, info);
            
            /* Status : data that display and contain transaction status, and define by ibflag prop.
             * 
             */
            var cols = [ 
                     {Type:"Status", Hidden:1, SaveName:"ibflag", KeyField:0 },
                     {Type:"DelCheck", Hidden:0, Width:20,   Align:"Center",    SaveName:"DEL",         KeyField:0, 	UpdateEdit:1,   InsertEdit:1 },
                     {Type:"Text",   Hidden:0, Width:90,   Align:"Center",  SaveName:"ownr_sub_sys_cd", KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"" },
                     {Type:"Text",   Hidden:0, Width:80,   Align:"Center",  SaveName:"intg_cd_id",  	KeyField:1, 	UpdateEdit:0,   InsertEdit:1, Format:"" },
                     {Type:"Text",   Hidden:0, Width:200,  Align:"Center",  SaveName:"intg_cd_nm",  	KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"" },
                     {Type:"Text",   Hidden:0, Width:50,   Align:"Center",  SaveName:"intg_cd_len",  	KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"" },
                     {Type:"Combo",  Hidden:0, Width:100,  Align:"Left",    SaveName:"intg_cd_tp_cd",  	KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"", ComboCode:"G|T", ComboText:"General|TY" },
                     {Type:"Text",   Hidden:0, Width:150,  Align:"Left",    SaveName:"mng_tbl_nm",  	KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"" },
                     {Type:"Text",   Hidden:0, Width:300,  Align:"Center",  SaveName:"intg_cd_desc",  	KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"" },
                     {Type:"Combo",  Hidden:0, Width:40,   Align:"Center",  SaveName:"intg_cd_use_flg", KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"", ComboCode:"N|Y", ComboText:"N|Y" },
                     {Type:"Text",   Hidden:0, Width:80,   Align:"Center",  SaveName:"cre_usr_id",  	KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"" },
                     {Type:"Date",   Hidden:0, Width:80,   Align:"Left",    SaveName:"cre_dt",  		KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"Ymd" },
                     {Type:"Text",   Hidden:0, Width:80,   Align:"Left",    SaveName:"upd_usr_id",  	KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"" },
                     {Type:"Date",   Hidden:0, Width:80,   Align:"Left",    SaveName:"upd_d",  			KeyField:0, 	UpdateEdit:1,   InsertEdit:1, Format:"Ymd" }
                   ];
              
            InitColumns(cols);                        
            resizeSheet(sheetNo);
            SetSheetHeight(240);
            /*Because waiting image displays as default but this initSheet() do not a search
             * or saving method is called where user waiting time is required --> set this property
             * as 0 to remove waiting image*/
            SetWaitImageVisible(0);
          }
          break;
          
        case "sheet2":
        	with(sheetObj){
        		var HeadTitle = "|Del|Cd ID|Cd Val|Display Name|Description Remark|Order";
        		SetConfig({SearchMode: 2});
        		var info = {Sort: 0, ColMove: 1, HeaderCheck: 0, ColResize: 1};
        		var headers = [{Text: HeadTitle, Align: "Center"}];
        		InitHeaders(headers, info);
        		
        		var cols = [
        		        {Type:"Status",    Hidden:1, Width:10,   Align:"Center",  SaveName:"ibflag" },
        		        {Type:"DelCheck",  Hidden:0, Width:20,   Align:"Center",  SaveName:"DEL",         		  KeyField:0, 	UpdateEdit:1,   InsertEdit:1 },
        		        {Type:"Text",      Hidden:0, Width:60,   Align:"Center",  SaveName:"intg_cd_id",    	  KeyField:0,   UpdateEdit:0,   InsertEdit:1 },
      			     	{Type:"Text",      Hidden:0, Width:60,   Align:"Center",  SaveName:"intg_cd_val_ctnt",    KeyField:1,   UpdateEdit:0,   InsertEdit:1 },
      			     	{Type:"Text",      Hidden:0, Width:200,  Align:"Center",  SaveName:"intg_cd_val_dp_desc", KeyField:0,   UpdateEdit:1,   InsertEdit:1 },
      			     	{Type:"Text",      Hidden:0, Width:600,  Align:"Center",  SaveName:"intg_cd_val_desc",    KeyField:0,   UpdateEdit:1,   InsertEdit:1 },
      			     	{Type:"Text",      Hidden:0, Width:50,   Align:"Center",  SaveName:"intg_cd_val_dp_seq",  KeyField:0,   UpdateEdit:1,   InsertEdit:1 }        		               		            
      			     ];
        		       	
        		InitColumns(cols);       		        		
        		resizeSheet(sheetNo);
        		SetSheetHeight(240);
        		SetWaitImageVisible(0);
        	}
        	break;
        default:
        	break;
    }
}

/**
 * 
 * 
 * @param sheetNo
 */

function resizeSheet(sheetNo){
  	ComResizeSheet(sheetObjects[sheetNo]);
}

/**
 * Functions that define transaction logic between UI and serve
 * 
 * @param sheetObj
 * @param formObj
 * @param sAction
 */
function doActionIBSheet(sheetObj, formObj, sAction) {
	with(sheetObj) {
		switch(sAction){
			case IBSEARCH:
				ComOpenWait(true);
				//sheetId = sheetObj.id;
				if(sheetObj.id === "sheet1"){
					formObj.f_cmd.value = SEARCH;
					//console.log("SEARCH " + FormQueryString(formObj));
					DoSearch("DOU_TRN_0002GS.do", FormQueryString(formObj));					
				}else if(sheetObj.id === "sheet2"){
					formObj.f_cmd.value = SEARCH01;
					//console.log("SEARCH01 " + FormQueryString(formObj));
					DoSearch("DOU_TRN_0002GS.do", FormQueryString(formObj));
				}				
				//ComOpenWait(false);
				break;
			case IBSAVE:				
				ComOpenWait(true);
				if(sheetObj.id === "sheet1"){
					formObj.f_cmd.value = MULTI;
					DoSave("DOU_TRN_0002GS.do", FormQueryString(formObj));
				} else if(sheetObj.id === "sheet2"){
					formObj.f_cmd.value = MULTI01;
					DoSave("DOU_TRN_0002GS.do", FormQueryString(formObj));
				}
				//ComOpenWait(false);
				break;
			default:
				break;
		}
	}
}

/**
 * Catch the double click event to show detail for a data in master table
 * 
 * @param sheetObj
 * @param Row
 */
function sheet1_OnDblClick(sheetObj, Row) {
	//Can not use the Value parameter because it's depended on the Cell that user's clicked.
	//So to get a Cd ID -> just need index of the Row, after move the Column that we need value.
	//NOTED : We can use the SaveName instead of the index of Row or Col
	//console.log(sheetObj);
	//console.log("Value " + Value);
	//console.log("Row " + Row);
	//console.log("Double Click Fired.");
	//console.log("Cd ID value: " + sheetObj.GetCellValue(Row, "intg_cd_id"));
	if(sheetObj.GetCellValue(Row, "ibflag") === "I"){
		return;
	}
	/* Assign the 'intg_cd_id' (Cd ID) for the search parameter 's_intg_cd_id'
	 * Searching by id to find the tag that include the s_intg_cd_id parameter
	 */
	document.form.s_intg_cd_id.value = sheetObj.GetCellValue(Row, "intg_cd_id");
	doActionIBSheet(sheetObjects[1], document.form, IBSEARCH);
	document.form.s_intg_cd_id.value = "";
}

/**
 * Turn off processing effect at the end of searching. sheet1
 * 
 */
function sheet1_OnSearchEnd(){
	ComOpenWait(false);
}

/**
 * Turn off processing effect at the end of searching. sheet2
 */
function sheet2_OnSearchEnd(){
	ComOpenWait(false);
}

/**
 * Checking for duplicate
 * @param sheetObj
 * @param colName
 * @return Boolean 
 */

function checkDuplicate(sheetObj, selectedRow) {
	//Using FindText() inside IBSheet to find specific text within a column and return row numbers.
	//First get the specific text that we want to check 
	var id = sheetObj.GetCellValue(selectedRow, "intg_cd_id");
	//console.log("Selected Row "+ selectedRow);
	//console.log("Client's input " + id);
	if(id === ""){
		ComShowCodeMessage("COM12114","intg_cd_id");
		return true;
	}
	var isDuplicate = sheetObj.FindText("intg_cd_id", id);
	//console.log("isDuplicate " + isDuplicate);
	//According to the IBSheet, if no identical string is found, -1 is returned.
	if(isDuplicate !== -1 && isDuplicate !== selectedRow){
		 /*We need isDuplicate !== selectedRow because find text will find in all row but not selectedRow
		  *Without isDuplicate !== selectedRow the FindText always return the index of  the selected row
		  *That means isDuplicate is always not equal to -1.*/
		ComShowCodeMessage("COM131302", id);
		return true; // There is a string that match with the id in Cd ID column(intg_cd_id)
	}
	return false;
}

/**
 * Catching the changes on sheet1 and check duplicate
 * 
 * @param sheetObj
 * @param Row
 * @param Col
 */
function sheet1_OnChange(sheetObj, Row, Col) {
	if(sheetObjects[0].ColSaveName(Col) === "intg_cd_id"){
		checkDuplicate(sheetObj, Row);
	}
}

/**
 * Catching the changes on sheet2 and check duplicate
 * 
 * @param sheetObj
 * @param Row
 * @param Col
 */
/*function sheet2_OnChagnge(sheetObj, Row, Col){
	if(sheetObjects[1].ColSaveName(Col) == "intg_cd_val_ctnt"){
		checkDuplicate(sheetObj, Row);
	}
}*/

/**
 * Turn off processing effect at the end of saving. sheet1
 */
function sheet1_OnSaveEnd(){
	ComOpenWait(false);
}

/**
 * Turn off processing effect at the end of saving. sheet1
 */
function sheet2_OnSaveEnd(){
	ComOpenWait(false);
}





















