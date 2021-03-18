package com.swapp.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.swapp.dao.SwboardDAO;
import com.swapp.vo.SwboardList;

public class SelectService implements SwboardService {

	@Override
	public void execute(Model model) {
		System.out.println("SelectService 클래스의 execute() 메소드 실행");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		SwboardDAO swboardDAO = ctx.getBean("swboardDAO", SwboardDAO.class);

		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) { }
		int totalCount = swboardDAO.selectCount();
		
		SwboardList swboardList = ctx.getBean("swboardList", SwboardList.class);	// 기본 생성자로 생성된 bean
		
		swboardList.initSwboardList(pageSize, totalCount, currentPage);
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", swboardList.getStartNo());
		hmap.put("endNo", swboardList.getEndNo());
		
		swboardList.setList(swboardDAO.selectList(hmap));
		
		model.addAttribute("swboardList", swboardList);
	}

}







