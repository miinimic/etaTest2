package kr.pe.eta.service.callres.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.eta.service.callres.CallResDao;
import kr.pe.eta.service.callres.CallResService;

@Service
public class CallResServiceImpl implements CallResService {

	@Autowired
	private CallResDao callResDao;

	public CallResServiceImpl() {
		System.out.println(this.getClass());
	}

}
