package kr.pe.eta.service.callreq.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.eta.service.callreq.CallReqDao;
import kr.pe.eta.service.callreq.CallReqService;

@Service
public class CallReqServiceImpl implements CallReqService {

	@Autowired
	private CallReqDao callReqDao;

	public CallReqServiceImpl() {
		System.out.println(this.getClass());
	}

}