package service;

import java.sql.Connection;
import java.sql.DriverManager;

import cash.model.MemberDao;
import cash.vo.Member;

public class MemberService {
	private MemberDao memberDao;
	// 비밀번호 확인 서비스
	public int checkPw(Member member) {
		this.memberDao = new MemberDao();
		int row = 0;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			row = memberDao.checkPw(conn, member);
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
	//회원정보수정
		public int modifyMember(String memberId,String memberPw) {
			this.memberDao = new MemberDao();
			int row = 0;
			Connection conn = null;
			try {
				// conn.setAutoCommit(false);
				conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
				row = memberDao.modifyMember(conn, memberId, memberPw);
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
	// 회원탈퇴
	public int removeMember( Member member) {
		this.memberDao = new MemberDao();
		int row = 0;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			row = memberDao.removeMember(conn, member);
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
	//회원 상세정보
	public Member selectMemberOne(String memberId) {
		this.memberDao = new MemberDao();
		Member returnMember = null;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			returnMember = memberDao.selectMemberOne(conn, memberId);
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
		return returnMember;
	}
	//회원가입 메서드
	public int insertMember(Member member) {
		this.memberDao = new MemberDao();
		int row = 0;	
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			row = memberDao.insertMember(conn, member);
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
	// 로그인메서드
	public Member selectMemberById(Member paramMember) {
		this.memberDao = new MemberDao();
		Member returnMember = null;
		Connection conn = null;
		try {
			// conn.setAutoCommit(false);
			conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
			returnMember = memberDao.selectMemberById(conn, paramMember);
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
		return returnMember;
	}
}
