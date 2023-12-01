package kr.pe.eta.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.eta.common.Search;
import kr.pe.eta.domain.User;
import kr.pe.eta.service.user.UserDao;
import kr.pe.eta.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public UserServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addUser(User user) throws Exception {
		userDao.addUser(user);

	}

	@Override
	public User getUser(String email) throws Exception {

		return userDao.getUser(email);
	}

	@Override
	public Map<String, Object> getUserList(Search search) throws Exception {
		List<User> list = userDao.getUserList(search);
		Map<String, Object> map = new HashMap<String, Object>();
		int drivertotalCount = userDao.getDriverCount(search);
		int passengertotalCount = userDao.getPassengerCount(search);
		map.put("list", list);
		map.put("drivertotalCount", new Integer(drivertotalCount));
		map.put("passengertotalCount", new Integer(passengertotalCount));

		System.out.println("passengertotalCount " + passengertotalCount);
		System.out.println("drivertotalCount " + drivertotalCount);
		map.put("list", list);
		return map;
	}

	@Override
	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);

	}

	@Override
	public void updatePwd(User user) throws Exception {
		userDao.updatePwd(user);

	}

	@Override
	public void deleteUser(String eamil) throws Exception {
		userDao.deleteUser(eamil);
	}

	@Override
	public boolean dupEmail(String eamil) throws Exception {
		boolean result = true;
		User user = userDao.getEmail(eamil);

		// 중복이 false
		if (user != null) {
			result = false;
		}
		return result;
	}

	@Override
	public boolean dupNickname(String nickName) throws Exception {
		boolean result = true;
		User user = userDao.getNickName(nickName);

		// 중복이 false
		if (user != null) {
			result = false;
		}
		return result;
	}

	@Override
	public Map<String, Object> autoUserList(Search search) throws Exception {
		List<User> list = userDao.getUserList(search);
		int drivertotalCount = userDao.getDriverCount(search);
		int passengertotalCount = userDao.getPassengerCount(search);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("drivertotalCount", new Integer(drivertotalCount));
		map.put("passengertotalCount", new Integer(passengertotalCount));

		System.out.println("passengertotalCount " + passengertotalCount);
		System.out.println("drivertotalCount " + drivertotalCount);

		return map;
	}

	public int getPassengerCount(Search search) throws Exception {
		return userDao.getPassengerCount(search);
	}

	public int getDriverCount(Search search) throws Exception {
		return userDao.getDriverCount(search);

	}

}
