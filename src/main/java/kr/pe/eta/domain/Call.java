package kr.pe.eta.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Call {

	private int callNo;
	private String callCode;
	private int userNo;
	private int realPay;
	private String startAddr;
	private String startKeyword;
	private double startX;
	private double startY;
	private String endAddr;
	private String endKeyword;
	private double endX;
	private double endY;
	private String callStateCode;
	private String routeOpt;
	private String carOpt;
	private boolean petOpt;

}
