package kr.pe.eta.service.pay;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayDao {

	public int getMyMoney(int userNo) throws Exception;

}
