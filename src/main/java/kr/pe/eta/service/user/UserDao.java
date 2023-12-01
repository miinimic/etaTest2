package kr.pe.eta.service.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.User;

@Mapper
public interface UserDao {

	public User addUser(User user) throws Exception;

	public List<User> getUserList(Search search) throws Exception;

	public User updateUser(User user) throws Exception;

	public User getNickName(String nickName);

	public User getEmail(String email) throws Exception;

	public User updatePwd(String pwd) throws Exception;

	public User getUser(String email) throws Exception;

	public User deleteUser(int userNo) throws Exception;

	public int getPassengerCount(Search search) throws Exception;

	public int getDriverCount(Search search) throws Exception;

}
