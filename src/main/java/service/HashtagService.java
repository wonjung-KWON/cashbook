package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Hashtag;

public class HashtagService {
	private HashtagDao hasgtagDao;
	
	// 해시태그 전체 행 구하기
	public int CountHashtagList(String word, String memberId) {
		this.hasgtagDao = new HashtagDao();
		int row = 0;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			row = hasgtagDao.CountHashtagList(conn, word, memberId);
		}catch (Exception e) {
			// conn.rollback();
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	//해시태그별 전체 구하는 쿼리
	public List<Map<String,Object>> AllHashtagList(String word, String memberId, int beginRow, int rowPerPage){
	this.hasgtagDao = new HashtagDao();
	List<Map<String,Object>> list = new ArrayList<>();
	Connection conn = null;
	try {
		// conn.setAutoCommit(false);
		conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
		list = hasgtagDao.AllHashtagList(conn, word, memberId, beginRow, rowPerPage);
	}catch (Exception e) {
		// conn.rollback();
		e.printStackTrace();
	}finally {
		try {
			conn.close();
		}catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return list;
	}
	// word와 카운트 
	public List<Map<String,Object>> selectWordCountByMonth(int targetYear, int targetMonth, String memberId){
		this.hasgtagDao = new HashtagDao();
		List<Map<String,Object>> list = new ArrayList<>();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			list = hasgtagDao.selectWordCountByMonth(conn, targetYear, targetMonth, memberId);
		}catch (Exception e) {
			// conn.rollback();
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	//해시태그 추가하는 메서드
	public int insertHashtag(Hashtag hashtag) {
		this.hasgtagDao = new HashtagDao();
		int row = 0;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			row = hasgtagDao.insertHashtag(conn, hashtag);
		}catch (Exception e) {
			// conn.rollback();
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
}
