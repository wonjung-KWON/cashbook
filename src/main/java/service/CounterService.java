package service;

import java.sql.Connection;
import java.sql.DriverManager;

import cash.model.CounterDao;

public class CounterService {
	private CounterDao counterDao;
	public void addCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			counterDao.insertCounter(conn);
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
		// conn.commit();
		return;
	}
	
	public void modifyCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			counterDao.UpdateCounter(conn);
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
		return;
		// conn.commit();
	}
	
	public int getCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		int counter = 0;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			counter = counterDao.selectCounterCurdate(conn);
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
		return counter;
	}
	public int getCounterAll() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		int totalCounter = 0;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			totalCounter = counterDao.selectCounterAll(conn);
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
		return totalCounter;
	}
}
