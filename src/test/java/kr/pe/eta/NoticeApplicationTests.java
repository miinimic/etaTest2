package kr.pe.eta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import kr.pe.eta.common.Search2;
import kr.pe.eta.domain.Notice;
import kr.pe.eta.service.notice.NoticeDao;
import kr.pe.eta.service.notice.NoticeService;

@SpringBootTest
public class NoticeApplicationTests {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private NoticeDao noticeDao;

	// @Test
	public void testAddNotice() throws Exception {

		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = localDate.atStartOfDay();
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		Notice notice = new Notice("공지사항88", "공지사항88");

		System.out.println("////////" + notice);

		Assertions.assertEquals(1, noticeService.addNotice(notice));
		// Assertions.assertEquals(1, noticeDao.addNotice(notice));

	}

	// @Test
	public void testGetNotice() throws Exception {

		int noticeNo = 1020;

		// Notice notice = noticeDao.getNotice(noticeNo);
		Notice notice = noticeService.getNotice(noticeNo);
		Assert.notNull(notice);
		System.out.println("///testGetNotice/////" + notice);

	}

	// @Test
	public void testGetNoticeList() throws Exception {

		Search2 search = new Search2("test", 3, 1);

		// Notice notice = noticeDao.getNotice(noticeNo);
		Map<String, Object> notice = noticeService.getNoticeList(search);
		Assert.notNull(notice);
		System.out.println("///testGetNotice/////" + notice);

	}

	// @Test
	public void testUpdateNotice() throws Exception {
		Notice notice = new Notice(1021, "test수정3", "test수정3 입니다.");
		System.out.println(notice);
		noticeService.updateNotice(notice);
		// Assertions.assertEquals(0, noticeDao.updateNotice(notice));
	}

	// @Test
	public void testDeleteNotice() throws Exception {

		int noticeNo = 1040;

		Assertions.assertEquals(1, noticeService.deleteNotice(noticeNo));

	}

}
