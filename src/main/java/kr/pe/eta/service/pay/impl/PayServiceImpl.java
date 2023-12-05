package kr.pe.eta.service.pay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.pe.eta.domain.Pay;
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

	public void addCharge(int userNo, int Tpay) throws Exception {
		System.out.println("serviceImpl addCharge userNo : " + userNo + ", " + Tpay);

		payDao.addCharge(userNo, Tpay);

	}

	public void updateMyMoney(int userNo, int updateMyMoney) throws Exception {
		System.out.println("serviceImpl updateMyMoney userNo : " + userNo + ", " + updateMyMoney);

		payDao.updateMyMoney(userNo, updateMyMoney);

	}

	public List<Pay> getTpayList(int userNo) throws Exception {
		System.out.println("serviceImpl getTpayList userNo : " + userNo);

		List<Pay> TpayList = payDao.getTpayList(userNo);

		return TpayList;

	}

	public void addPay(Pay pay) throws Exception {
		System.out.println("serviceImpl addPay : " + pay);

		payDao.addPay(pay);

	}

}
