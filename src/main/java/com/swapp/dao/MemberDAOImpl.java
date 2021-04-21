package com.swapp.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.swapp.vo.MemberVO;

public class MemberDAOImpl {

	@Autowired
	private MemberDAO memberDAO;
	
	//회원가입 쿼리 테스트 메서드
	
	public void memberJoin() throws Exception{
		MemberVO vo = new MemberVO();
		
		vo.setMemberId("test");			//회원 id
		vo.setMemberPw("test");			//회원 비밀번호
		vo.setMemberName("test");		//회원 이름
		vo.setMemberMail("test");		//회원 메일
		vo.setMemberAddr1("test");		//회원 우편번호
		vo.setMemberAddr2("test");		//회원 주소
		vo.setMemberAddr3("test");		//회원 상세주소
		
		memberDAO.MemberJoin(vo);			//쿼리 메서드 실행
		
	}
	
	
	
}