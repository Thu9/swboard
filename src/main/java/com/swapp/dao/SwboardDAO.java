package com.swapp.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.swapp.swboard.Constant;
import com.swapp.vo.SwboardVO;

public class SwboardDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public SwboardDAO() {
		
		jdbcTemplate = Constant.jdbcTemplate;

	}
	
	public void insert(final SwboardVO swboardVO) {
		String sql = "insert into swboard (idx, name, subject, content, ref, lev, seq)" +  
				"values (swboard_idx_seq.nextval, ?, ?, ?, swboard_idx_seq.currval, 0, 0)";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, swboardVO.getName());
				ps.setString(2, swboardVO.getSubject());
				ps.setString(3, swboardVO.getContent());
			}
		});
	}

	public int selectCount() {
		System.out.println("selectcount 실행");
		
		String sql = "select count(*) from swboard";
		return jdbcTemplate.queryForInt(sql);
	}

	public ArrayList<SwboardVO> selectList(HashMap<String, Integer> hmap) {
		System.out.println("selectList 실행");
		
		String sql = "select * from ("
				+    "    select rownum rnum, AA.* from ("
				+	 "        select * from swboard order by ref desc, seq asc"
				+	 "    ) AA where rownum <= " + hmap.get("endNo")
				+	 ") where rnum >= " + hmap.get("startNo");
		
		return (ArrayList<SwboardVO>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(SwboardVO.class));
	}

	public void increment(final int idx) {
		String sql = "update mvcboard set hit = hit + 1 where idx = " + idx;
		jdbcTemplate.update(sql);
	}

	public SwboardVO selectByIdx(int idx) {
		
		String sql ="select * from swboard where idx = " + idx;
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(SwboardVO.class));
	}

	public void delete(final int idx) {
		
		String sql = "delete from mvcboard where idx = " + idx;
		jdbcTemplate.update(sql);
	}

	public void update(final SwboardVO swboardVO) {
		String sql = "update swboard set subject = '" + swboardVO.getSubject() + "', content = '" + swboardVO.getContent() + 
				"' where idx = '" + swboardVO.getIdx() + "'";
		jdbcTemplate.update(sql);
	}

	public void update(final int idx, final String subject, final String content) {
		String sql = "update swboard set subject = '" + subject + "', content = '" + content + "' where idx = " + idx;
		jdbcTemplate.update(sql);
	}

	public void replyIncrement(final HashMap<String, Integer> hmap) {
		String sql = "update swboard set seq = seq + 1 where ref = '" + hmap.get("ref") + "' and seq >= '" + hmap.get("seq") + "'";
		jdbcTemplate.update(sql);
	}

	public void replyInsert(final SwboardVO swboardVO) {
		System.out.println("MvcboardDAO 클래스의 replyInsert() 메소드 실행");

		String sql = "insert into swboard (idx, name, subject, content, ref, lev, seq) values (swboard_idx_seq.nextval, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, swboardVO.getName());
				ps.setString(2, swboardVO.getSubject());
				ps.setString(3, swboardVO.getContent());
				ps.setInt(4, swboardVO.getRef());
				ps.setInt(5, swboardVO.getLev());
				ps.setInt(6, swboardVO.getSeq());
			}
		});
	}
}
