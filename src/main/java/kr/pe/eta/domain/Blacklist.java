package kr.pe.eta.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Blacklist {

	private int driverNo;
	private int passengerNo;
	private boolean blacklistCode;

	@Override
	public String toString() {
		return "Blacklist [driverNo=" + driverNo + ", passengerNo=" + passengerNo + ", blacklistCode=" + blacklistCode
				+ "]";
	}

}
