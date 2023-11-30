package kr.pe.eta.service.pay.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.pe.eta.service.pay.PayDao;
import kr.pe.eta.service.pay.PayService;

@Service("payService")
public class PayServiceImpl implements PayService {

	@Autowired
	@Qualifier("payDao")
	private PayDao payDao;

	public PayServiceImpl() {
		System.out.println(this.getClass());
	}

	public int getMyMoney(int userNo) throws Exception {
		System.out.println("serviceImpl getMyMoney userNo : " + userNo);

		int myMoney = payDao.getMyMoney(userNo);

		return myMoney;
	}

}
