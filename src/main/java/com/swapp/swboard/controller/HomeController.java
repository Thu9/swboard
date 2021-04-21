package com.swapp.swboard.controller;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swapp.dao.MybatisDAO;
import com.swapp.vo.SwboardList;
import com.swapp.vo.SwboardVO;

@Controller
public class HomeController {
	
	@Autowired
	public SqlSession sqlSession;
	
	// 첫 페이지
	@RequestMapping(value = { "/", "/index"} )
	public String home(Locale locale, Model model) {
		return "index";
	}
	
	// 게시글 입력 페이지
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, Model model) {
		return "insert";
	}
	
	// 게시글 입력 메소드
	@RequestMapping("/insertOK")
	public String insertOK(HttpServletRequest request, Model model) {
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		SwboardVO swboardVO = ctx.getBean("swboardVO", SwboardVO.class);
		
		swboardVO.setName(request.getParameter("name"));
		swboardVO.setSubject(request.getParameter("subject"));
		swboardVO.setContent(request.getParameter("content"));
		
		mapper.insert(swboardVO);
		return "redirect:list";
	}
	// 게시판 페이지
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) { }	
		int totalCount = mapper.selectCount();
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		SwboardList swboardList = ctx.getBean("swboardList", SwboardList.class);
		swboardList.initSwboardList(pageSize, totalCount, currentPage);
		
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", swboardList.getStartNo());
		hmap.put("endNo", swboardList.getEndNo());
		
		swboardList.setList(mapper.selectList(hmap));
		model.addAttribute("swboardList", swboardList);
		
		return "list";
	}
	
	// 조회수 증가 메소드
	@RequestMapping("/increment")
	public String increment(HttpServletRequest request, Model model) {
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		int idx = Integer.parseInt(request.getParameter("idx"));
		mapper.increment(idx);
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		return "redirect:contentView";
	}
	
	// 게시글 페이지
	@RequestMapping("/contentView")
	public String contentView(HttpServletRequest request, Model model) {
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		SwboardVO swboardVO = ctx.getBean("swboardVO", SwboardVO.class);
		swboardVO = mapper.selectByIdx(idx);
		
		model.addAttribute("vo", swboardVO);
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		model.addAttribute("enter", "\r\n");
		
		return "contentView";
	}
	
	// 게시글 삭제 메소드
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		mapper.delete(idx);
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		
		return "redirect:list";
	}
	
	// 게시글 수정 메소드
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model, SwboardVO swboardVO) {
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		mapper.update(swboardVO);

		return "redirect:list";
	}
	
	// 게시글 댓글 메소드
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		SwboardVO swboardVO = ctx.getBean("swboardVO", SwboardVO.class);
		swboardVO = mapper.selectByIdx(idx);
		
		model.addAttribute("vo", swboardVO);
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		model.addAttribute("enter", "\r\n");
		
		return "reply";
	}
	
	// 게시글 댓글 입력 메소드
	@RequestMapping("/replyInsert")
	public String replyInsert(HttpServletRequest request, Model model, SwboardVO swboardVO) {
		MybatisDAO mapper = sqlSession.getMapper(MybatisDAO.class);
		
		swboardVO.setLev(swboardVO.getLev() + 1);
		swboardVO.setSeq(swboardVO.getSeq() + 1);

		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("ref", swboardVO.getRef());
		hmap.put("seq", swboardVO.getSeq());
		mapper.replyIncrement(hmap);
		mapper.replyInsert(swboardVO);
		
		model.addAttribute("currentPage", Integer.parseInt(request.getParameter("currentPage")));
		
		return "redirect:list";
	}
}
