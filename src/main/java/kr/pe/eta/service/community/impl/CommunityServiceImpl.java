package kr.pe.eta.service.community.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.Call;
import kr.pe.eta.domain.DealReq;
import kr.pe.eta.service.community.CommunityDao;
import kr.pe.eta.service.community.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private CommunityDao communityDao;

	public CommunityServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addReservation(Call call) throws Exception {
		communityDao.addReservation(call);
	}

	@Override
	public int getCallNo(int userNo) throws Exception {
		int callNo = communityDao.getCallNo(userNo);
		return callNo;
	}

	@Override
	public void addDealReq(DealReq dealReq) throws Exception {
		communityDao.addDealReq(dealReq);
	}

	@Override
	public void addDealReqDriver(DealReq dealReq) throws Exception {
		communityDao.addDealReq(dealReq);
	}

	@Override
	public void deleteDealReq(int userNo) throws Exception {
		communityDao.deleteDealReq(userNo);
	}

	@Override
	public void deleteDealReqDriver(int userNo) throws Exception {
		communityDao.deleteDealReq(userNo);
	}

	@Override
	public DealReq getDeal(int userNo) throws Exception {
		DealReq dealReq = communityDao.getDeal(userNo);
		return dealReq;
	}

	@Override
	public Map<String, Object> getDealDriverList(Search search, int callNo) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		param.put("searchKeyword", search.getSearchKeyword());
		param.put("endRowNum", search.getEndRowNum());
		param.put("startRowNum", search.getStartRowNum());
		param.put("callNo", callNo);

		List<DealReq> list = communityDao.getDealDriverList(param);

		int totalCount = communityDao.getTotalCountDriver(callNo);
		map.put("list", list);
		map.put("totalCount", totalCount);
		return map;
	}

	@Override
	public Map<String, Object> getDealList(Search search) throws Exception {
		List<DealReq> list = communityDao.getDealList(search);

		int totalCount = communityDao.getTotalCountPass(search);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		return map;
	}

}
