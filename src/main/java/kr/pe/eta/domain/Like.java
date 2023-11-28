package kr.pe.eta.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Like {

	private int userNo;
	private String likeName;
	private String likeAddr;

	@Override
	public String toString() {
		return "Like [userNo=" + userNo + ", likeName=" + likeName + ", likeAddr=" + likeAddr + "]";
	}

}
