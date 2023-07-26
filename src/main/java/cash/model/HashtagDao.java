package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cash.vo.Hashtag;

public class HashtagDao {
	// 해시태그 전체 행 구하기
	public int CountHashtagList(Connection conn,String word, String memberId) {
		int row = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM hashtag h INNER JOIN cashbook c ON  h.cashbook_no = c.cashbook_no WHERE h.word = ? AND c.member_id = ? ";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, word);
			stmt.setString(2, memberId);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				row = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	//해시태그별 전체 구하는 쿼리
	public List<Map<String,Object>> AllHashtagList(Connection conn,String word, String memberId, int beginRow, int rowPerPage){
		List<Map<String,Object>> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.cashbook_no cashbookNo, c.category category, c.cashbook_date cashbookDate, c.price price, c.memo memo, c.createdate createdate, c.updatedate updatedate, h.word word FROM hashtag h INNER JOIN cashbook c ON  h.cashbook_no = c.cashbook_no WHERE h.word = ? AND c.member_id = ? order by c.cashbook_no desc LIMIT ?, ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, word);
			stmt.setString(2, memberId);
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("cashbookNo", rs.getInt("cashbookNo"));
				m.put("category", rs.getString("category"));
				m.put("cashbookDate", rs.getString("cashbookDate"));
				m.put("price", rs.getInt("price"));
				m.put("memo", rs.getString("memo"));
				m.put("createdate", rs.getString("createdate"));
				m.put("updatedate", rs.getString("updatedate"));
				m.put("word", rs.getString("word"));
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	// word와 카운트 
	public List<Map<String,Object>> selectWordCountByMonth(Connection conn, int targetYear, int targetMonth, String memberId){
		List<Map<String,Object>> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select word, count(*) cnt FROM hashtag h INNER JOIN cashbook c ON h.cashbook_no = c.cashbook_no WHERE c.member_id = ? AND year(c.cashbook_date) = ? AND MONTH(c.cashbook_date) = ? group by word order by count(*) DESC";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> m = new HashMap<String,Object>();
				m.put("word", rs.getString("word"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	//해시태그 추가하는 메서드
	public int insertHashtag(Connection conn, Hashtag hashtag) {
		int row = 0;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO hashtag(cashbook_no, word, updatedate, createdate) VALUES(?,?,NOW(),NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, hashtag.getCashbookNo());
			stmt.setString(2, hashtag.getWord());
			row = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				stmt.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
}
