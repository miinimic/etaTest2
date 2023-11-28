package kr.pe.eta.service.community;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.Call;
import kr.pe.eta.domain.DealReq;

@Mapper
public interface CommunityDao {

	public void addReservation(Call call) throws Exception;

	public int getCallNo(int userNo) throws Exception;

	public void addDealReq(DealReq dealReq) throws Exception;

	public void deleteDealReq(int userNo) throws Exception;

	public DealReq getDeal(int dealNo) throws Exception;

	public List<DealReq> getDealDriverList(Map param) throws Exception;

	public List<DealReq> getDealList(Search search) throws Exception;

	public int getTotalCountPass(Search search) throws Exception;

	public int getTotalCountDriver(int call_no) throws Exception;

}
