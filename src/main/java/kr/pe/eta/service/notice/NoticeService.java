package kr.pe.eta.service.notice;

import java.util.Map;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.Notice;

public interface NoticeService {

	public int addNotice(Notice notice) throws Exception;

	public Notice getNotice(int noticeNo) throws Exception;

	public Map<String, Object> getNoticeList(Search search) throws Exception;

	public int updateNotice(Notice notice) throws Exception;

	public int deleteNotice(int noticeNo) throws Exception;

}
