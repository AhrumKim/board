package com.jquery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jquery.dto.Member;
import com.jquery.util.ConnectionHelper;

public class MemberDAO {
	//회원가입
	public int joinMember(Member dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "insert into member(user_id, user_pwd) values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getUser_pwd());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		return result;
	}
	
	public int login(Member dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 3;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select user_id, user_pwd from member where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			rs = pstmt.executeQuery();
			
			String id = "";
			String pwd = "";
			while(rs.next()) {
				id = rs.getString("user_id");
				pwd = rs.getString("user_pwd");
			}
			
			if(id.equals(dto.getUser_id())) {
				if(pwd.equals(dto.getUser_pwd())) {
					System.out.println("아이디, 비밀번호 모두 일치");
					result = 1;
				} else {
					System.out.println("아이디만 일치");
					result = 2;
				}
			} else {
				System.out.println("아이디, 비밀번호 모두 불일치");
				result = 3;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}
}
