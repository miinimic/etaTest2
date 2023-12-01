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
	public User addUser(User user) throws Exception {
		return userDao.addUser(user);

	}

	@Override
	public User getUser(int userNo) throws Exception {

		return userDao.getUser(userNo);
	}

	@Override
	public Map<String, Object> getUserList(Search search) throws Exception {
		List<User> list = userDao.getUserList(search);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

	@Override
	public User updateUser(User user) throws Exception {
		return userDao.updateUser(user);

	}

	@Override
	public User updatePwd(String pwd) throws Exception {
		return userDao.updatePwd(pwd);

	}

	@Override
	public User deleteUser(int userNo) throws Exception {
		return userDao.deleteUser(userNo);
	}

	@Override
	public boolean dupEmail(String eamil) throws Exception {
		boolean result = true;
		User user = userDao.getEmail(eamil);
		// 중복이면 false
		if (user != null) {
			result = false;
		}

		return result;
	}

	@Override
	public boolean dupNickname(String nickName) throws Exception {
		boolean result = true;
		User user = userDao.getNickName(nickName);
		// 중복이면 false
		if (user != null) {
			result = false;
		}

		return result;
	}

	@Override
	public Map<String, Object> autoUserList(Search search) throws Exception {
		List<User> list = userDao.getUserList(search);
		int totalPassenger = userDao.getPassengerCount(search);
		int totalDriver = userDao.getDriverCount(search);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalPassenger", totalPassenger);
		map.put("totalDriver", totalDriver);

		return map;
	}
}
