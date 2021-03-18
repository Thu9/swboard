package com.swapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.swapp.dao.SwboardDAO;
import com.swapp.vo.SwboardVO;

public class UpdateService implements SwboardService {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
//		request 객체로 넘어온 수정할 글번호와 데이터를 받는다.
		int idx = Integer.parseInt(request.getParameter("idx"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		SwboardDAO swboardDAO = ctx.getBean("swboardDAO", SwboardDAO.class);
		swboardDAO.update(idx, subject, content);
		
		SwboardVO swboardVO = ctx.getBean("swboardVO", SwboardVO.class);
		swboardVO.setIdx(idx);
		swboardVO.setSubject(subject);
		swboardVO.setContent(content);
		swboardDAO.update(swboardVO);
		
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));

	}

}
