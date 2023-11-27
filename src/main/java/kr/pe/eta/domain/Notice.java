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

}
