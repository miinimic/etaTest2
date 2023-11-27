package kr.pe.eta.service.user.impl;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pe.eta.service.user.UserDao;
import kr.pe.eta.service.user.UserService;

@Mapper
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public UserServiceImpl() {
		System.out.println(this.getClass());
	}

}
