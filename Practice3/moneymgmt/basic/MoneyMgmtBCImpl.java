package com.clt.apps.opus.esm.clv.practice3.moneymgmt.basic;

import java.util.List;

import com.clt.apps.opus.esm.clv.practice3.moneymgmt.integration.MoneyMgmtDBDAO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.ConditionVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.DetailVO;
import com.clt.apps.opus.esm.clv.practice3.moneymgmt.vo.SummaryVO;
import com.clt.framework.component.message.ErrorHandler;
import com.clt.framework.core.layer.event.EventException;
import com.clt.framework.core.layer.integration.DAOException;
import com.clt.framework.support.layer.basic.BasicCommandSupport;

public class MoneyMgmtBCImpl extends BasicCommandSupport implements MoneyMgmtBC {
	
	private transient MoneyMgmtDBDAO dbDao = null;
	
	public MoneyMgmtBCImpl() {
		dbDao = new MoneyMgmtDBDAO();
	}

	/**
	 * Getting data for Partner combo box<br>
	 * 
	 * @return List<SummaryVO>
	 * @exception EventException
	 */
	@Override
	public List<SummaryVO> getPartnerCodes() throws EventException {
		try {
			return dbDao.getPartnerCodes();
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	/**
	 * Getting data for Lane combo box<br>
	 * 
	 * @param summaryVO
	 * @return List<SummaryVO>
	 * @throws EventException
	 */
	@Override
	public List<SummaryVO> getLaneCodes(SummaryVO summaryVO)
			throws EventException {
		try {
			return dbDao.getLaneCodes(summaryVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	@Override
	public List<SummaryVO> getTradeCodes(SummaryVO summaryVO)
			throws EventException {
		try {
			return dbDao.getTradeCodes(summaryVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	
	public List<SummaryVO> searchSummaryVO(ConditionVO conditionVO) throws EventException {
		try {
			return dbDao.searchSummaryVO(conditionVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}

	@Override
	public List<DetailVO> searchDetailVO(ConditionVO conditionVO)
			throws EventException {
		try {
			return dbDao.searchDetailVO(conditionVO);
		} catch(DAOException ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		} catch (Exception ex) {
			throw new EventException(new ErrorHandler(ex).getMessage(),ex);
		}
	}
	

	
}
