package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import cash.model.CashbookDao;
import cash.model.MemberDao;
import cash.vo.Cashbook;

public class CashbookService {
	private CashbookDao cashbookDao;
	public List<Cashbook> todayCashbook(String memberId, int targetYear, int targetMonth) {
		this.cashbookDao = new CashbookDao();
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			list = cashbookDao.todayCashbook(conn, memberId, targetYear, targetMonth);
			System.out.println(list + "CashbookService todayCashbook");
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
	//현재달 최고 수입
	public int selectMaxMoney(String memberId, int targetYear, int targetMonth) {
		this.cashbookDao = new CashbookDao();
		int row = 0;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			row = cashbookDao.selectMaxMoney(conn, memberId, targetYear, targetMonth);
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
	// 총 지출
	public int totalUnMoney(String memberId) {
		this.cashbookDao = new CashbookDao();
		int row = 0;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			row = cashbookDao.totalUnMoney(conn, memberId);
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
	// 총 수입
	public int totalMoney(String memberId) {
		this.cashbookDao = new CashbookDao();
		int row = 0;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			row = cashbookDao.totalMoney(conn, memberId);
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
	//현재달 최고 지출
	public int selectMaxUnMoney(String memberId, int targetYear, int targetMonth) {
		this.cashbookDao = new CashbookDao();
		int row = 0;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			row = cashbookDao.selectMaxUnMoney(conn, memberId, targetYear, targetMonth);
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
	//이번달 마지막 지출
	public Cashbook selectLastMoney(String memberId, int targetYear, int targetMonth) {
		this.cashbookDao = new CashbookDao();
		Cashbook list = new Cashbook();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			list = cashbookDao.selectLastMoney(conn, memberId, targetYear, targetMonth);
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
	
	public int addCashbook(Cashbook cashbook) {
		this.cashbookDao = new CashbookDao();
		int row = 0;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			row = cashbookDao.insertCashbook(conn, cashbook);
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
	public List<Cashbook> selectCashbookListByMonth(String memberId, int targetYear, int targetMonth){
		this.cashbookDao = new CashbookDao();
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			list = cashbookDao.selectCashbookListByMonth(conn, memberId, targetYear, targetMonth);
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
	public List<Cashbook> selectCashbookOne(String memberId, int targetYear, int targetMonth, int targetDay){
		this.cashbookDao = new CashbookDao();
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			list = cashbookDao.selectCashbookOne(conn, memberId, targetYear, targetMonth, targetDay);
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
}
