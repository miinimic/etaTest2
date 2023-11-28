package kr.pe.eta.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Block {

	private int userNo;
	private boolean blockCode;
	private Date blockDate;
	private Date unblockDate;
	private int blockOpt;

	@Override
	public String toString() {
		return "Block [userNo=" + userNo + ", blockCode=" + blockCode + ", blockDate=" + blockDate + ", unblockDate="
				+ unblockDate + ", blockOpt=" + blockOpt + "]";
	}

}
