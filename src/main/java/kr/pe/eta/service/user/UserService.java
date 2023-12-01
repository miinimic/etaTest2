package kr.pe.eta.service.user;

import java.util.Map;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.User;

public interface UserService {

	// 회원가입
	public User addUser(User user) throws Exception;

	// 내정보확인
	public User getUser(int userNo) throws Exception;

	// 회원리스트
	public Map<String, Object> getUserList(Search search) throws Exception;

	// 정보수정
	public User updateUser(User user) throws Exception;

	// pwd수정
	public User updatePwd(String pwd) throws Exception;

	// 이메일 중복체크
	public boolean dupEmail(String eamil) throws Exception;

	// 닉네임 중복체크
	public boolean dupNickname(String nickName) throws Exception;

	// 회원탈퇴
	public User deleteUser(int userNo) throws Exception;

	// aoto
	public Map<String, Object> autoUserList(Search search) throws Exception;

	public int getPassengerCount(Search search) throws Exception;

	public int getDriverCount(Search search) throws Exception;

}
