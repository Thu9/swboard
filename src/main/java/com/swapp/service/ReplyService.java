package com.swapp.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.swapp.dao.SwboardDAO;
import com.swapp.vo.SwboardVO;

public class ReplyService implements SwboardService {

	@Override
	public void execute(Model model) {
		System.out.println("ReplyService 클래스의 execute() 메소드 실행");

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));
		int lev = Integer.parseInt(request.getParameter("lev"));
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		SwboardVO swboardVO = ctx.getBean("swboardVO", SwboardVO.class);
		swboardVO.setIdx(idx);
		swboardVO.setName(name);
		swboardVO.setSubject(subject);
		swboardVO.setContent(content);
		swboardVO.setRef(ref);
		swboardVO.setLev(lev + 1);
		swboardVO.setSeq(seq + 1);
		
		SwboardDAO swboardDAO = ctx.getBean("swboardDAO", SwboardDAO.class);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("ref", swboardVO.getRef());
		hmap.put("seq", swboardVO.getSeq());
		
		swboardDAO.replyIncrement(hmap);
		swboardDAO.replyInsert(swboardVO);
		
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
	}

}
