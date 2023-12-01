package kr.pe.eta.service.feedback.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.Blacklist;
import kr.pe.eta.domain.Call;
import kr.pe.eta.domain.Report;
import kr.pe.eta.domain.Star;
import kr.pe.eta.service.feedback.FeedbackDao;
import kr.pe.eta.service.feedback.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;

	public FeedbackServiceImpl() {
		System.out.println(this.getClass());
	}

	public int addStar(Star star) throws Exception {
		return feedbackDao.updateStar(star);
	}

	public int updateStar(Star star) throws Exception {
		return feedbackDao.updateStar(star);
	}

	public Star getStar(Star star) throws Exception {
		return feedbackDao.getStar(star);
	}

	public Star getShareStar(Star star) throws Exception {
		return feedbackDao.getShareStar(star);
	}

	public int addBlacklist(Blacklist blacklist) throws Exception {
		return feedbackDao.addBlacklist(blacklist);
	}

	public int deleteBlacklist(Blacklist blacklist) throws Exception {
		return feedbackDao.deleteBlacklist(blacklist);
	}

	public Blacklist getBlacklist(Blacklist blacklist) throws Exception {
		Blacklist blacklist1 = feedbackDao.getBlacklist(blacklist);
		if (blacklist1 == null) {
			// blacklist1 = new Blacklist();
			blacklist1 = Blacklist.builder().blacklistCode(false).build();
		} else {
			blacklist1.setBlacklistCode(true);
		}
		return blacklist1;
	}

	public int addReport(Report report) throws Exception {
		return feedbackDao.addReport(report);
	}

	public Map<String, Object> getReport(Report report) throws Exception {

		List<Report> reportlist = feedbackDao.getReport(report);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportlist", reportlist);
		return map;
	}

//	public Map<String, Object> getShareReport(Report report) throws Exception {
//		List<Report> reportlist = feedbackDao.getShareReport(report);
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("reportlist", reportlist);
//		return map;
//	}

	public Map<String, Object> getReportList(Search search) throws Exception {

		List<Report> reportlist = feedbackDao.getReportList(search);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportlist", reportlist);
		return map;

	}

	public Call getCall(int callNo) throws Exception {
		return feedbackDao.getCall(callNo);
	}

}
