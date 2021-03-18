package com.swapp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.swapp.vo.SwboardVO;

public interface MybatisDAO {
	
	int selectCount();
	ArrayList<SwboardVO> selectList(HashMap<String, Integer> hmap);
	
	void insert(SwboardVO swboardVO);
	void increment(int idx);
	SwboardVO selectByIdx(int idx);
	void delete(int idx);
	void update(SwboardVO swboardVO);
	void replyIncrement(HashMap<String, Integer> hmap);
	void replyInsert(SwboardVO swboardVO);
}
