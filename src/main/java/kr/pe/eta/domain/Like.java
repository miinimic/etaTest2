package kr.pe.eta.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Like {

	private int likeNo;
	private int userNo;
	private String likeName;
	private String likeAddr;

	@Override
	public String toString() {
		return "Like [userNo=" + userNo + ", likeNo=" + likeNo + ", likeName=" + likeName + ", likeAddr=" + likeAddr
				+ "]";
	}

}
