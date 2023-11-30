package kr.pe.eta.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealReqDriver {

	private int userNo;
	private int callNo;
	private String callCode;
	private int driverOffer;
	private int starAvg;

	@Override
	public String toString() {
		return "DealReqDriver [userNo=" + userNo + ", callNo=" + callNo + ", callCode=" + callCode + ", driverOffer="
				+ driverOffer + ", starAvg=" + starAvg + "]";
	}

}
