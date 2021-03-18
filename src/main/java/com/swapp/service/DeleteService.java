package com.swapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.swapp.dao.SwboardDAO;

public class DeleteService implements SwboardService {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		SwboardDAO swboardDAO = ctx.getBean("swboardDAO", SwboardDAO.class);
		swboardDAO.delete(idx);
		
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
	}

}
