package com.swapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.swapp.dao.MemberDAO;
import com.swapp.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	//회원가입
	@Override
	public void MemberJoin(MemberVO vo) {
		System.out.println("회원가입 서비스 시작");
		memberDAO.MemberJoin(vo);
		System.out.println("회원가입 서비스 끝");
	}
	
}