package kr.pe.eta.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShareReqPassenger {

	private int shareReqNo;
	private int otherSharePassengerNo;

	@Override
	public String toString() {
		return "ShareReqPassenger [shareReqNo=" + shareReqNo + ", otherSharePassengerNo=" + otherSharePassengerNo + "]";
	}

}
