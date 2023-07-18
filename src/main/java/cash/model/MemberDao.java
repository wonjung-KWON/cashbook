package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import cash.vo.Member;

public class MemberDao {
	//비밀번호 확인
	public int checkPw(Member member) {
		int row = 0;
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) from member WHERE member_id = ? AND member_pw = password(?)";
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
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
				conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	//회원정보수정
	public int modifyMember(String memberId,String memberPw) {
		int row = 0;
		Connection conn =null;
		PreparedStatement stmt = null;
		System.out.println(memberId);
		System.out.println(memberPw);
		String sql = "update member set member_pw = password(?), updatedate = now() WHERE member_id = ?";
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberPw);
			stmt.setString(2, memberId);
			row = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				stmt.close();
				conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(row);
		return row;
	}
	
	// 회원탈퇴
	public int removeMember(Member member) {
		int row = 0;
		Connection conn =null;
		PreparedStatement stmt = null;
		
		String sql = "DELETE FROM member WHERE member_id = ? AND member_pw = password(?)";
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			row = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				stmt.close();
				conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	//회원 상세정보
	public Member selectMemberOne(String memberId) {
		Member returnMember = null;
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId, member_pw memberPw, member_name memberName, member_phone memberPhone, createdate, updatedate FROM member WHERE member_id = ?";
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
				returnMember.setMemberPw(rs.getString("memberPw"));
				returnMember.setMemberName(rs.getString("memberName"));
				returnMember.setMemberPhone(rs.getString("memberPhone"));
				returnMember.setCreatedate(rs.getString("createdate"));
				returnMember.setUpdatedate(rs.getString("updatedate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return returnMember;
	}
	
	//회원가입 메서드
	public int insertMember(Member member) {
		
		int row = 0;
		Connection conn =null;
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO member(member_id, member_pw, member_name, member_phone, createdate, updatedate) values(?, PASSWORD(?), ?, ?,now(), now())";
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.setString(3, member.getMemberName());
			stmt.setString(4, member.getMemberPhone());
			row = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				stmt.close();
				conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return row;
	}
	
	
	// 로그인메서드
	public Member selectMemberById(Member paramMember) {
			Member returnMember = null;
			
			Connection conn =null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT member_id memberId FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
			try {
				conn = DriverManager.getConnection("jdbc:mariadb://43.202.104.49:3306/cash","root","java1234");
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, paramMember.getMemberId());
				stmt.setString(2, paramMember.getMemberPw());
				rs = stmt.executeQuery();
				if(rs.next()) {
					returnMember = new Member();
					returnMember.setMemberId(rs.getString("memberId"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			
			return returnMember;
	}
}
