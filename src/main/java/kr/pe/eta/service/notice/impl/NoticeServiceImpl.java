package kr.pe.eta.service.notice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.eta.service.notice.NoticeDao;
import kr.pe.eta.service.notice.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;

	public NoticeServiceImpl() {
		System.out.println(this.getClass());
	}

}
