package kr.pe.eta.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealReq {

	private int userNo;
	private int callNo;
	private String callCode;
	private int passengerOffer;
	private Date limitTime;
	private int driverOffer;
	private int starAvg;

}
