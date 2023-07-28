package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cash.vo.Cashbook;
import cash.vo.Member;

public class CashbookDao {
	
	// 최근 기록 7개
			public List<Cashbook> todayCashbook(Connection conn, String memberId, int targetYear, int targetMonth) {
				List<Cashbook> list = new ArrayList<Cashbook>();
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String sql = "select category ,price, cashbook_date cashbookDate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? order by updatedate desc limit 0,7";
					try {
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, memberId);
						stmt.setInt(2, targetYear);
						stmt.setInt(3, targetMonth);
						System.out.println(stmt +"<--stmt");
						rs = stmt.executeQuery();
						
						while(rs.next()) {
							Cashbook c = new Cashbook();
							c.setCategory(rs.getString("category"));
							c.setPrice(rs.getInt("price"));
							c.setCashbookDate(rs.getString("cashbookDate"));
							list.add(c);
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
	// 이번달 마지막 지출
		public Cashbook selectLastMoney(Connection conn, String memberId, int targetYear, int targetMonth) {
				Cashbook list = new Cashbook();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				String sql = "select price, updatedate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND category = '수입' order by updatedate desc limit 0,1";
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, memberId);
					stmt.setInt(2, targetYear);
					stmt.setInt(3, targetMonth);
					System.out.println(stmt +"<--stmt");
					rs = stmt.executeQuery();
					
					while(rs.next()) {
						list.setPrice(rs.getInt("price"));
						list.setUpdatedate(rs.getString("updatedate"));
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
	// 총 지출
		public int totalUnMoney(Connection conn, String memberId) {
			int row = 0;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "select sum(price) price FROM cashbook WHERE member_id = ? AND category = '지출'";
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, memberId);
				System.out.println(stmt +"<--stmt");
				rs = stmt.executeQuery();
				if(rs.next()) {
					row = rs.getInt("price");
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
	// 총 수입
		public int totalMoney(Connection conn, String memberId) {
		int row = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select sum(price) price FROM cashbook WHERE member_id = ? AND category = '수입'";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			System.out.println(stmt +"<--stmt");
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("price");
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
	// 선택년도 월 최고 지출
		public int selectMaxUnMoney(Connection conn, String memberId, int targetYear, int targetMonth) {
			int row = 0;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "select max(price) price FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND category = '지출'";
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, memberId);
				stmt.setInt(2, targetYear);
				stmt.setInt(3, targetMonth);
				System.out.println(stmt +"<--stmt");
				rs = stmt.executeQuery();
				if(rs.next()) {
					row = rs.getInt("price");
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
	// 최고 수입
	public int selectMaxMoney(Connection conn, String memberId, int targetYear, int targetMonth) {
		int row = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select max(price) price FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND category = '수입'";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			System.out.println(stmt +"<--stmt");
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("price");
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
	// 반환값 : cashbook_no 키값
	public int insertCashbook(Connection conn, Cashbook cashbook) {
		int cashbookNo = 0;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO cashbook(member_id, category, cashbook_date, price, memo, updatedate, createdate) VALUES(?,?,?,?,?,NOW(),NOW())";
		try {
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashbook.getMemberId());
			stmt.setString(2, cashbook.getCategory());
			stmt.setString(3, cashbook.getCashbookDate());
			stmt.setInt(4, cashbook.getPrice());
			stmt.setString(5, cashbook.getMemo());
			System.out.println(stmt +"<--stmt");
			int row = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
			
			while(rs.next()) {
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
		return cashbookNo;
	}	
	public List<Cashbook> selectCashbookListByMonth(Connection conn, String memberId, int targetYear, int targetMonth){
		List<Cashbook> list = new ArrayList<Cashbook>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo,category, price, cashbook_date cashbookDate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? ORDER BY cashbook_date ASC";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			System.out.println(stmt +"<--stmt");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				list.add(c);
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
	public List<Cashbook> selectCashbookOne(Connection conn, String memberId, int targetYear, int targetMonth, int targetDay){
		List<Cashbook> list = new ArrayList<Cashbook>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, price, memo, updatedate, createdate , cashbook_date cashbookDate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ? ORDER BY cashbook_date ASC";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setInt(4, targetDay);
			System.out.println(stmt +"<--stmt");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				c.setUpdatedate(rs.getString("updatedate"));
				c.setCreatedate(rs.getString("createdate"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				list.add(c);
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
}
