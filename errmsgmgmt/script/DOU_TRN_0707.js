/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : DOU_TRN_0707.js
*@FileTitle : Error Message Management
*Open Issues :
*Change history :
*@LastModifyDate : 2022.06.27
*@LastModifier : Bao Du
*@LastVersion : 1.0
* 2022.06.27 
* 1.0 Creation
=========================================================*/
/****************************************************************************************
  이벤트 구분 코드: [초기화]INIT=0; [입력]ADD=1; [조회]SEARCH=2; [리스트조회]SEARCHLIST=3;
					[수정]MODIFY=4; [삭제]REMOVE=5; [리스트삭제]REMOVELIST=6 [다중처리]MULTI=7
					기타 여분의 문자상수  COMMAND01=11; ~ COMMAND20=30;
 ***************************************************************************************/

/*------------------다음 코드는 JSDoc을 잘 만들기 위해서 추가된 코드임 ------------------*/
   /**
     * @fileoverview 업무에서 공통으로 사용하는 자바스크립트파일로 달력 관련 함수가 정의되어 있다.
     * @author 한진해운
     */

    /**
     * @extends 
     * @class DOU_TRN_0707 : DOU_TRN_0707 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */
    /*function DOU_TRN_0707() {
    	this.processButtonClick		= tprocessButtonClick;
    	this.setSheetObject 		= setSheetObject;
    	this.loadPage 				= loadPage;
    	this.initSheet 				= initSheet;
    	this.initControl            = initControl;
    	this.doActionIBSheet 		= doActionIBSheet;
    	this.setTabObject 			= setTabObject;
    	this.validateForm 			= validateForm;
    }*/
    
   	/* necessary */
    var sheetObjects=new Array();
	var sheetCnt=0;

	document.onclick = processButtonClick;
	
	function processButtonClick(){
		//store sheetObjects[0] in sheetObject1 for shorten
        var sheetObject1 = sheetObjects[0];
        //for shorten
        var formObject = document.form;
        
        try {
        	//get name of the element that fired the event
        	var srcName = ComGetEvent("name");
        	//console.log(srcName);
        	switch(srcName) {
           		case "btn_Add":
           			doActionIBSheet(sheetObject1, formObject, IBINSERT);
           			break;
           		case "btn_Retrieve":
           			doActionIBSheet(sheetObject1, formObject, IBSEARCH);
           			break;
           		case "btn_Save":
           			doActionIBSheet(sheetObject1, formObject, IBSAVE);
           			break;
           		case "btn_DownExcel":
           			doActionIBSheet(sheetObject1, formObject, IBDOWNEXCEL);
           			break;
           		default:
           			//console.log("Found no button");
           			break;
        	}
        } catch(e) {
        	if( e == "[object Error]") {
        		ComShowMessage(OBJECT_ERROR);
        	} else {
        		ComShowMessage(e);
        	}
   	  	}
    }
	
	function setSheetObject(sheet_obj){
		//set sheetObjects equal sheet_obj after that increase.
	       sheetObjects[sheetCnt++]=sheet_obj;
	}
	
	function loadPage() {
		for(i = 0; i < sheetObjects.length; i++){
			/* (inside ComConfigSheet) ShowButtonImage method check or configure 
			 * the button image styles of popup and combo
			 * 0: Display combo, calendar and pop-up image only when is focused.
			 */
			/* (inside ComConfigSheet) SetDown2ExcelConfig : configure some basic settings for 
			 * excel download
			 * SheetDesign : whether to apply IBSheet design concept to download file default = 0.
			 */
			/* (inside ComConfigSheet) DataRowHeight: check or configure row height of all data rows.
			 * This property can be set at pixels. Default value is 21 pixels.
			 */
			ComConfigSheet(sheetObjects[i]);
			initSheet(sheetObjects[i],i+1);		
			ComEndConfigSheet(sheetObjects[i]);
			
			doActionIBSheet(sheetObjects[i], document.form, IBSEARCH);
		}
	}
	
	function initSheet(sheetObj, sheetNo) {
        var cnt=0;
		var sheetID=sheetObj.id;
        switch(sheetID) {
            case "sheet1":
              with(sheetObj){ //for shorten the syntax 
                var HeadTitle="|Del|Msg Cd|Msg Type|Msg level|Message|Description" ;
                var headCount=ComCountHeadTitle(HeadTitle);
                /*SearchMode: 2 is search all
                MergeSheet: 5 Allow merge in the header rows only
                DataRowMerge: whether to allow horizontal merge of the entire row (Default=0)*/
                //SetConfig( { SearchMode:2, MergeSheet:5, Page:20, DataRowMerge:1 } );
                SetConfig({SearchMode: 2});
                /*Sort : allow sorting by clicking on the header
                HeaderCheck : Whether the CheckAll in the header is checked (default = 1)*/
                var info    = { Sort:1, ColMove:0, HeaderCheck:0, ColResize:1 };
                //config headers using JSON 
                var headers = [ { Text:HeadTitle, Align:"Center"} ];
                
                InitHeaders(headers, info);
                /* SaveName (String) : a parameter name used to save or search data
                 * CalcLogic can be used to configure calculation formula for the data. Calculation equation by column.
                 * Format (String) : data mask application format. Can be used to configure whether to apply Mask to data.
                 * PointCount can be used to configure the number of decimal places to display for column type Float.
                 * UpdateEdit can be used to configure edit ability of data the transaction status of which is Search.
                 * 			+ Whether to allow data editing when transaction is in "Search" state (default value is 1)
                 * InsertEdit (boolean) can be used to configure edit ability of data the transaction status of which is Insert.
                 * 			+ Whether to allow data editing when transaction is in "Insert" state (default value is 1)
                 * MultiLineText (boolean) whether to use multiple lines.
                 * ColMerge (boolean) whether to allow column merging.
                 */
                var cols = [ 
                         {Type:"Status",    Hidden:1, Width:30,   Align:"Center",  ColMerge:0,   SaveName:"ibflag" },
	                     {Type:"DelCheck",  Hidden:0, Width:45,   Align:"Center",  ColMerge:1,   SaveName:"DEL",         KeyField:0, 	UpdateEdit:1,   InsertEdit:1 },
	                     {Type:"Text",      Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_msg_cd",  KeyField:1, 	UpdateEdit:0,   InsertEdit:1 },
	                     {Type:"Combo",     Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_tp_cd",   KeyField:1, 	UpdateEdit:1,   InsertEdit:1, ComboText:"Server|UI|Both", ComboCode:"S|U|B" },
	                     {Type:"Combo",     Hidden:0, Width:80,   Align:"Center",  ColMerge:0,   SaveName:"err_lvl_cd",  KeyField:1, 	UpdateEdit:1,   InsertEdit:1, ComboText:"ERR|WARNING|INFO", ComboCode:"E|W|I" },
	                     {Type:"Text",      Hidden:0, Width:400,  Align:"Left",    ColMerge:0,   SaveName:"err_msg",     KeyField:1, 	UpdateEdit:1,   InsertEdit:1, MultiLineText:1 },
	                     {Type:"Text",      Hidden:0, Width:250,  Align:"Left",    ColMerge:0,   SaveName:"err_desc",    KeyField:0, 	UpdateEdit:1,   InsertEdit:1 } 
	                   ];
                  
                InitColumns(cols);
                /* WaitImageVisible : Check or configure whether to display waiting image during processing. 
                 * Depending on the property setting, waiting images may or may not display for different processing.
                 * 
                 */
                //Check whether to display waiting image for processing
                if(GetWaitImage() === 1){
                	SetWaitImageVisible(1);
                }else{
                	SetWaitImageVisible(0);
                }
                 
                resizeSheet();
              }
            break;
        }
    }
	
	function resizeSheet(){ //responsive
	   	         ComResizeSheet(sheetObjects[0]);
	}
	
	function doActionIBSheet(sheetObj,formObj,sAction) {
        switch(sAction) {
			case IBSEARCH:     			            
				ComOpenWait(true); //loading page
				
				/* sheetObj.DoSearch(PageUrl, [Param], [Opt]) 
				 * PageUrl : search XML page file name
				 * Param   : search condition Query String, default = "" */
				formObj.f_cmd.value = SEARCH; //set parameter = SEARCH
 				sheetObj.DoSearch("DOU_TRN_0707GS.do", FormQueryString(formObj)); //call to HTML
				break;
				// May use GetSearchData() instead if do not understand well with Opt of DoSeach()
				
			case IBSAVE:
				ComOpenWait(true);
            	formObj.f_cmd.value = MULTI;
                sheetObj.DoSave("DOU_TRN_0707GS.do", FormQueryString(formObj));              
				break;
				
			case IBINSERT:
				// DataInsert(0)  : create as the first row
				// DataInsert(-1) : create as the last row
				// DataInsert()   : create immediately below the currently selected row
				var rowIndex = sheetObj.DataInsert();
				//console.log("this is row index " + rowIndex);
				break;
				
			case IBDOWNEXCEL:
				//if there is no data, show message
				if(sheetObj.RowCount() < 1){ //check the total data row
					//console.log("There is no data in cell");
					ComShowCodeMessage("COM132501");
				}else{
					sheetObj.Down2Excel({DownCols: makeHiddenSkipCol(sheetObj), SheetDesign:1, Merge:1});
				}
				break;
        }
    }
	
	/**
	 * After Search. When search done : close the loading popup - Reference : IBSheet Events
	 * 
	 * @param Code, Msg, StCode, StMsg
	 * @return @returns
	 * @exception EventException
	 */
	function sheet1_OnSearchEnd(Code, Msg, StCode, StMsg) {
		//ObjectID_OnSearchEnd(Code, Msg, StCode, StMsg)
		//Code (type Long): Processing result code (0 is success, other is error)
		//Msg (String): Processing result message
		//StCode (Integer): HTTP response code
		//StMsg (String): HTTP response message 
     	ComOpenWait(false);
    }
	
	function sheet1_OnSaveEnd(Code, Msg, StCode, StMsg){
		//Code similar with OnSearchEnd() but O or higher is success
		//The rest is the same.
		//This event can fire when DoSave or DoAllSave function is called.
		ComOpenWait(false);
	}
	
	function sheet1_OnClick(Row, Col, Value, CellX, CellY, CellW, CellH){
		//All parameter are Long in type except Value type String
		//Row : row index of the cell
		//Column : Column index of the cell
		//Value : Cell value where the event fired.
		//CellX and CellY : coordinate of the cell
		//CellW : Width , CellH : Height
		//OnDblClick() is as similar as OnClick()
	}
	
	function sheet1_OnChange(Row, Col, Value, OldValue, RaiseFlag){
		//RaiseFlag (Integer) Event fire option (0: manual editing, 1: method, 2: paste)
	}
	
	
	/**
	 * Catch edit event and fire check message code.
	 * 
	 * @param sheetObject
	 * @returns
	 * @exception 
	 */
	function sheet1_OnAfterEdit(sheetObject, Row, Col){
		//alert("Exit editing.");
		var colName = sheetObject.ColSaveName(Col); //get Name of currently edit column
		console.log(sheetObject.ColSaveName(Col));
		if (colName === "err_msg_cd"){
			var msgCode = sheetObject.GetCellValue(Row, Col);
			//console.log("Message Code " + msgCode);
			var checkResult = checkMessageCodeFormat(msgCode);
			//console.log("checkResult "+ checkResult);
			if(checkResult) {
				sheetObject.SetCellValue(Row, Col, msgCode);
			}else {
				ComShowCodeMessage("COM132201"); //COM130103
				sheetObject.SetCellValue(Row, Col, "");			
			}
		}
	}
	
	/**
	 * validate message code
	 * 
	 * @param messageCode
	 * @returns Boolean
	 */
	function checkMessageCodeFormat(messageCode){
		//using regular expression literal
		/* ^  : match the beginning of input. ex: /^A/ does not match "an A", but does match the first "A" in "An A".
		 * $  : match the end of input. ex /t$/ does not match the "t" in "eater", but does match it in "eat".
		 * \d : digit
		 * [] : range
		 * {} : quantifiers : matches exactly "n" occurrences of the preceding item.
		 * */
		var regexPattern = /^([A-Z]{3})(\d{5})$/;
		
		//var found = messageCode.match(regexPattern);
		var test = regexPattern.test(messageCode);
		console.log("Test " + test);
		if(test){
			return true;
		}else {
			return false;
		}
	}
