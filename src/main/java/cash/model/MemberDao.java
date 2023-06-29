package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import cash.vo.Member;

public class MemberDao {
	public Member selectMemberById(Member paramMember) {
			Member returnMember = null;
			
			Connection conn =null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT member_id memberId FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
				
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
