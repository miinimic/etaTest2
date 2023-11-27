package kr.pe.eta.web.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.eta.service.notice.NoticeService;

@Controller
@RequestMapping("/notice/json/*")
public class NoticeRestController {

	@Autowired
	private NoticeService noticeService;

	public NoticeRestController() {
		System.out.println(this.getClass());
	}

}
