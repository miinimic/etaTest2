package kr.pe.eta.service.feedback;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.Blacklist;
import kr.pe.eta.domain.Call;
import kr.pe.eta.domain.Report;
import kr.pe.eta.domain.Star;

@Mapper
public interface FeedbackDao {

	public int updateStar(Star star) throws Exception;

	public Star getStar(Star star) throws Exception;

	public Star getShareStar(Star star) throws Exception;

	public int addBlacklist(Blacklist blacklist) throws Exception;

	public int deleteBlacklist(Blacklist blacklist) throws Exception;

	public Blacklist getBlacklist(Blacklist blacklist) throws Exception;

	public int addReport(Report report) throws Exception;

	public Call getCall(int callNo) throws Exception;

	public List<Report> getReport(Report report) throws Exception;

	public List<Report> getReportList(Search search) throws Exception;

}
