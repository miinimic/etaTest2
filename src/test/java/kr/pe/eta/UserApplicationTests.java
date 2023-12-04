package kr.pe.eta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.pe.eta.service.user.UserService;

@SpringBootTest
class UserApplicationTests {

	@Autowired
	UserService service;

//	// @Test
//	void contextLoads() throws Exception {
//
//		User user = new User();
//
//		user.setEmail("als9045@naver.com");
//		user.setPwd("qweasd1513");
//		user.setName("김민수");
//		user.setNickName("김민수");
//		user.setBirth("19940930");
//		user.setGender("1");
//		user.setPhone("01066775412");
//		user.setRole("passenger");
//		user.setCarOpt("0");
//		user.setCarNum("12나1234");
//		user.setBank("신한");
//		user.setAccount("1103803154");
//
//		service.addUser(user);
//	}
//
//	@Test
//	void getuser() throws Exception {
//
//		User user = service.getUser(1011);
//
//		System.out.println("user" + user);
//		assertEquals("김민수", user.getName());
//		assertEquals("김민수", user.getNickName());
//		assertEquals("qweasd1513", user.getPwd());
//		assertEquals("als9045@naver.com", user.getEmail());
//		assertEquals(0, user.isBlockCode());
//		System.out.println("isCode" + user.isBlockCode());
//		System.out.println("isCode" + user.isDealCode());
//		System.out.println("isCode" + user.isPetOpt());
//		System.out.println("isCode" + user.isShareCode());
//
//	}
//
//	// @Test
//	void deleteUser() throws Exception {
//
//		User user = service.getUser(1023);
//
//		// assertEquals(1, service.deleteUser(user.getPwd()));
//
//	}
//
//	// @Test
//	void updateUser() throws Exception {
//
//		User user = service.getUser(1024);
//
//		user.setName("이건뭐야");
//		user.setNickName("뭐가임마");
//
//		service.updateUser(user);
//
//	}
//
//	// @Test
//	void updatePwd() throws Exception {
//
//		User user = service.getUser(1024);
//
//		user.setPwd("qwer");
//		service.updateUser(user);
//
//	}

//	@Test
//	void userList() throws Exception {
//		Search search = new Search();
//
//		search.setSearchKeyword("");
//		search.setSearchCondition("0");
//		search.setEndRowNum(5);
//		search.setStartRowNum(1);
//
//		TestUser user = new TestUser();
//
//		System.out.println(service.getUserList(search));
//	}

}
