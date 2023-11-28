package kr.pe.eta.service.callres.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.Call;
import kr.pe.eta.domain.Pay;
import kr.pe.eta.service.callres.CallResDao;
import kr.pe.eta.service.callres.CallResService;

@Service("CallResService")
public class CallResServiceImpl implements CallResService {

	@Autowired
	@Qualifier("callResDao")
	private CallResDao callResDao;

	public void setCallResDao(CallResDao callResDao) {
		this.callResDao = callResDao;
	}

	public CallResServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public Call getRecord(int callNo) throws Exception {
		// TODO Auto-generated method stub
		return callResDao.getRecord(callNo);
	}

	@Override
	public Call getReservation(int callNo) throws Exception {
		// TODO Auto-generated method stub
		return callResDao.getReservation(callNo);
	}

	@Override
	public Map<String, Object> getRecordList(Search search, int userNo) throws Exception {
		// TODO Auto-generated method stub
		List<Object> list = callResDao.getRecordList(search, userNo);
		int totalCount = callResDao.getTotalCount(search);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map<String, Object> getReservationList(Search search) throws Exception {
		// TODO Auto-generated method stub
		List<Object> list = callResDao.getReservationList(search);
		int totalCount = callResDao.getTotalCount(search);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public void addPay(Pay pay) throws Exception {
		// TODO Auto-generated method stub
		callResDao.addPay(pay);
	}

	@Override
	public void updateCallStateCode(Call call) throws Exception {
		// TODO Auto-generated method stub
		callResDao.updateCallStateCode(call);
	}

	@Override
	public Map<String, Object> getCallResList(Search search) throws Exception {
		// TODO Auto-generated method stub
		List<Object> list = callResDao.getCallResList(search);
		int totalCount = callResDao.getTotalCount(search);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public void updateEndXY(Call call) throws Exception {
		callResDao.updateEndXY(call);

	}

}
