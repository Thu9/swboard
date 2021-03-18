package com.swapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.swapp.dao.SwboardDAO;
import com.swapp.vo.SwboardVO;

public class ContentViewService implements SwboardService {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		SwboardDAO swboardDAO = ctx.getBean("swboardDAO", SwboardDAO.class);
		
		SwboardVO swboardVO = ctx.getBean("swboardVO", SwboardVO.class);
		swboardVO = swboardDAO.selectByIdx(idx);
		
		model.addAttribute("vo", swboardVO);
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		model.addAttribute("enter", "\r\n");
	}

}
