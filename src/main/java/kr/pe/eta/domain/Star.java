package kr.pe.eta.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Star {

	private int driverNo;
	private int passengerNo;
	private Date starDate;
	private int callNo;
	private Date callDate;
	private int star;

	@Override
	public String toString() {
		return "Star [driverNo=" + driverNo + ", passengerNo=" + passengerNo + ", starDate=" + starDate + ", callNo="
				+ callNo + ", callDate=" + callDate + ", star=" + star + "]";
	}

}
