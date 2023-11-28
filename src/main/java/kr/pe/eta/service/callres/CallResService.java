package kr.pe.eta.service.callres;

import java.util.Map;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.Call;
import kr.pe.eta.domain.Pay;

public interface CallResService {
	public Call getRecord(int callNo) throws Exception;

	public Call getReservation(int callNo) throws Exception;

	public Map<String, Object> getRecordList(Search search, int userNo) throws Exception;

	public Map<String, Object> getReservationList(Search search) throws Exception;

	public void addPay(Pay pay) throws Exception;

	public void updateCallStateCode(Call call) throws Exception;

	public Map<String, Object> getCallResList(Search search) throws Exception;

	public void updateEndXY(Call call) throws Exception;

}
