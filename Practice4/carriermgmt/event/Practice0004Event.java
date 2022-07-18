/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : Practice0004Event.java
*@FileTitle : PRACTICE4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.07.12
*@LastModifier : 
*@LastVersion : 1.0
* 2022.07.12 
* 1.0 Creation
=========================================================*/
package com.clt.apps.opus.esm.clv.practice4.carriermgmt.event;

import com.clt.framework.support.layer.event.EventSupport;
import com.clt.apps.opus.esm.clv.practice4.carriermgmt.vo.CarrierListVO;


/**
 * PRACTICE_0004 에 대한 PDTO(Data Transfer Object including Parameters)<br>
 * -  PRACTICE_0004HTMLAction에서 작성<br>
 * - ServiceCommand Layer로 전달하는 PDTO로 사용<br>
 *
 * @author Bao
 * @see PRACTICE_0004HTMLAction 참조
 * @since J2EE 1.6
 */

public class Practice0004Event extends EventSupport {

	private static final long serialVersionUID = 1L;
	
	/** Table object inquiry condition and single case processing */
	CarrierListVO carrierListVO = null;
	
	/** Table Value Object Multi Data processing */
	CarrierListVO[] carrierListVOs = null;

	public Practice0004Event(){}
	
	public void setCarrierListVO(CarrierListVO carrierListVO){
		this. carrierListVO = carrierListVO;
	}

	public void setCarrierListVOS(CarrierListVO[] carrierListVOs){
		this. carrierListVOs = carrierListVOs;
	}

	public CarrierListVO getCarrierListVO(){
		return carrierListVO;
	}

	public CarrierListVO[] getCarrierListVOS(){
		return carrierListVOs;
	}

}