package kr.pe.eta;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.Blacklist;
import kr.pe.eta.domain.Call;
import kr.pe.eta.domain.Report;
import kr.pe.eta.domain.Star;
import kr.pe.eta.service.feedback.FeedbackDao;
import kr.pe.eta.service.feedback.FeedbackService;

@SpringBootTest
//@Transactional
public class FeedbackApplicationTests {

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private FeedbackDao feedbackDao;

	// @Test
	public void testAddStar() throws Exception {

		Star star = new Star();

		star.setStar(3);
		star.setCallNo(1002);
		System.out.println("////////" + star);
		System.out.println("feedbackService.addStar(star)" + feedbackService.addStar(star));
		// feedbackService.addStar(star);
		// Assertions.assertEquals(1, feedbackDao.updateStar(star));
		Assertions.assertEquals(1, feedbackService.addStar(star));

	}

	// @Test
	public void testUpdateStar() throws Exception {

		Star star = new Star();

		star.setStar(4);
		star.setCallNo(1003);
		System.out.println("////////" + star);
		System.out.println("feedbackService.addStar(star)" + feedbackService.addStar(star));
		// feedbackService.addStar(star);
		// Assertions.assertEquals(1, feedbackDao.updateStar(star));
		Assertions.assertEquals(1, feedbackService.updateStar(star));

	}

	// @Test
	public void testGetStar() throws Exception {

		Star star = new Star();

		star.setCallNo(1002);
		star = feedbackService.getStar(star);
		System.out.println("////////" + star.getStar());
		// feedbackService.addStar(star);
		// Assertions.assertEquals(1, feedbackDao.updateStar(star));
		Assert.notNull(star);

	}

	// @Test
	public void testGetShareStar() throws Exception {

		Star star = new Star();

		star.setCallNo(1001);
		star.setPassengerNo(1001);
		star = feedbackService.getShareStar(star);
		System.out.println("////////" + star.getStar());
		// feedbackService.addStar(star);
		// Assertions.assertEquals(1, feedbackDao.updateStar(star));
		Assert.notNull(star);

	}

	// @Test
	public void testAddBlacklist() throws Exception {

		Blacklist blacklist = new Blacklist();
		blacklist.setPassengerNo(1023);
		blacklist.setDriverNo(1022);
		Assertions.assertEquals(1, feedbackService.addBlacklist(blacklist));

	}

	// @Test
	public void testDeleteBlacklist() throws Exception {

		Blacklist blacklist = new Blacklist();
		blacklist.setPassengerNo(1023);
		blacklist.setDriverNo(1022);
		Assertions.assertEquals(1, feedbackService.deleteBlacklist(blacklist));

	}

	// @Test
	public void testGetBlacklist() throws Exception {

		Blacklist blacklist = Blacklist.builder().passengerNo(1023).driverNo(1022).build();

		blacklist = feedbackService.getBlacklist(blacklist);
		assert blacklist.isBlacklistCode();
		System.out.println(blacklist);

		blacklist = Blacklist.builder().passengerNo(1023).driverNo(1000).build();

		blacklist = feedbackService.getBlacklist(blacklist);
		assert !blacklist.isBlacklistCode();

		System.out.println(blacklist);
		// Assert.notNull(blacklist);

	}

//	// @Test
//	public void testAddReport() throws Exception {
//
//		Report report = Report.builder().reportUserNo(1023).badCallNo(1000).reportCategory("요금 관련")
//				.reportDetail("passenger가 driver신고5").role("passenger").build();
////		Assertions.assertEquals(1, feedbackService.addReport(report));
//		assert 1 == feedbackService.addReport(report);
//
//	}

	@Test
	public void testGetReport() throws Exception {
		Report report = Report.builder().reportRole("driver").reportNo(1022).badCallNo(1001).build();
		// report = feedbackService.getReport(report.getReportNo());
		Map<String, Object> reportlist = feedbackService.getReport(report);
		System.out.println(reportlist);
		Assert.notNull(reportlist);

	}

	// @Test
	public void testGetReportList() throws Exception {
		Search search = new Search();
//		search.setSearchCondition("0");
//		search.setSearchKeyword("0");
//		search.setCurrentPage(1);
//		search.setPageSize(3);

//		search.setSearchCondition("1");
//		search.setSearchKeyword("요금 관련");
//		search.setCurrentPage(1);
//		search.setPageSize(3);

		search.setSearchCondition("2");
		search.setSearchKeyword("10");
		search.setCurrentPage(1);
		search.setPageSize(3);

		Map<String, Object> report = feedbackService.getReportList(search);
		Assert.notNull(report);
		System.out.println("///testGetNotice/////" + report);
	}

	// @Test
	public void testGetCall() throws Exception {
		// Report report = Report.builder().reportNo(1009).build();
		// report = feedbackService.getReport(report.getReportNo());
		Call call = feedbackService.getCall(1000);
		System.out.println(call);
		Assert.notNull(call);

	}

}
