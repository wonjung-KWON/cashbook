package cash.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CounterDao {
	// 오늘 처음인지 확인 체크 select로
	
	// 오늘 날짜 첫번째 접속 -> insert
	
	public void insertCounter(Connection conn) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO counter values(CURDATE(), 1)";
			stmt = conn.prepareStatement(sql);
			int row = stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			// 예외를 던져야된다
			throw new Exception(); // 강제로 예외를 발생
		}finally {
			try {
				stmt.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	// 오늘 날짜 첫번째가 아니면 -> update
	public void UpdateCounter(Connection conn) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE counter SET count_num = count_num+1 WHERE count_date = CURDATE()";
			stmt = conn.prepareStatement(sql);
			int row = stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			// 예외를 던져야된다
			throw new Exception(); // 강제로 예외를 발생
		}finally {
			try {
				stmt.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// 오늘 날짜 카운터
	public int selectCounterCurdate(Connection conn) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int counter = 0;
		try {
			String sql = "SELECT count_num counterNum FROM counter WHERE count_date = CURDATE()";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				counter = rs.getInt("counterNum");
			}
		}catch(Exception e){
			e.printStackTrace();
			// 예외를 던져야된다
			throw new Exception(); // 강제로 예외를 발생
		}finally {
			try {
				rs.close();
				stmt.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return counter;
	}
	// 누적 카운터
	public int selectCounterAll(Connection conn) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		try {
			String sql = "SELECT SUM(count_num) totalCount FROM counter";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt("totalCount");
			}
		}catch(Exception e){
			e.printStackTrace();
			// 예외를 던져야된다
			throw new Exception(); // 강제로 예외를 발생
		}finally {
			try {
				rs.close();
				stmt.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return totalCount;
	}
}
