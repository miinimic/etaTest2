package kr.pe.eta.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice {

	private int noticeNo;
	private String noticeTitle;
	private Date noticeDate;
	private String noticeDetail;

//	public Notice(String noticeTitle, String noticeDetail) {
//
//		this.noticeTitle = noticeTitle;
//		this.noticeDetail = noticeDetail;
//	}

	public Notice(int noticeNo, String noticeTitle, String noticeDetail) {
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeDetail = noticeDetail;
	}

	public String toString() {
		return "Notice : [noticeNo] " + noticeNo + " [noticeDate] " + noticeDate + " [noticeTitle] " + noticeTitle
				+ " [noticeDetail] " + noticeDetail;
	}
}
