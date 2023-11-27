package kr.pe.eta.service.community.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.eta.service.community.CommunityDao;
import kr.pe.eta.service.community.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private CommunityDao communityDao;

	public CommunityServiceImpl() {
		System.out.println(this.getClass());
	}

}
