package kr.pe.eta.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShareReq {

	private int shareReqNo;
	private String callCode;
	private int callNo;
	private int firstSharePassengerNo;
	private int firstShareCount;
	private int startShareCount;
	private int maxShareCount;
	private Date shareDate;

}
