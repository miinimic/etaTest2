package kr.pe.eta.service.pay;

import java.util.List;

import kr.pe.eta.domain.Pay;

public interface PayService {

	public int getMyMoney(int userNo) throws Exception;

	public void addCharge(int userNo, int Tpay) throws Exception;

	public void updateMyMoney(int userNo, int updateMyMoney) throws Exception;

	public List<Pay> getTpayList(int userNo) throws Exception;

	public void addPay(Pay pay) throws Exception;

}
