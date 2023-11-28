package kr.pe.eta.service.callres;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.Call;
import kr.pe.eta.domain.Pay;

@Mapper
public interface CallResDao {
	public Call getRecord(int callNo) throws Exception;

	public Call getReservation(int callNo) throws Exception;

	public List<Object> getRecordList(Search search, int userNo) throws Exception;

	public List<Object> getReservationList(Search search) throws Exception;

	public void addPay(Pay pay) throws Exception;

	public void updateCallStateCode(Call call) throws Exception;

	public List<Object> getCallResList(Search search) throws Exception;

	public void updateEndXY(Call call) throws Exception;

	public int getTotalCount(Search search) throws Exception;

}
