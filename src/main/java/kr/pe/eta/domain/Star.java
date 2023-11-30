package kr.pe.eta.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Star {

	private int driverNo;
	private int passengerNo;
	private Date starDate;
	private int callNo;
	private Date callDate;
	private int star;

}
