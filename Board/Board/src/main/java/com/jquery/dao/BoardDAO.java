package com.jquery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jquery.dto.Board;
import com.jquery.util.ConnectionHelper;

import oracle.jdbc.proxy.annotation.Pre;

public class BoardDAO {
	//게시글 목록 가져오기
	public List<Board> getBoardList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Board> boardlist = new ArrayList<>();
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select * from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(rs.getInt("postId"),
													rs.getString("postTitle"),
													rs.getString("postContent"),
													rs.getTimestamp("createdAt"),
													rs.getString("author"));
				boardlist.add(board);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		return boardlist;
	}
	
	//게시글 디테일 가져오기
	public Board getOneBoard(int postId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Board board = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select * from board where postId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				board = new Board(rs.getInt("postId"), 
										rs.getString("postTitle"),
										rs.getString("postContent"),
										rs.getTimestamp("createdAt"),
										rs.getString("author"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return board;
	}
	
	//게시글 삭제하기
	public int deleteOneBoard(int postId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "delete from board where postId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}
	
	//게시글 수정하기
	
	public int updateOneBoard(Board board) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "update board set postTitle=? ,postContent=?,createdAt=SYSDATE where postId=?";
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, board.getPostTitle());
			pstmt.setString(2, board.getPostContent());
			pstmt.setInt(3, board.getPostId());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		
		return result;
	}
	
	//게시글 작성 후 게시글 고유번호 리턴 메서드 (게시글 작성후 게시글상세 페이지에 돌아가기 위함)
	public int insertBoard(Board board) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "INSERT INTO board (postId,postTitle, postContent, createdAt, author) "
					+ "VALUES (board_seq.NEXTVAL,?, ?, SYSDATE, ?)";
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, board.getPostTitle());
			pstmt.setString(2, board.getPostContent());
			pstmt.setString(3, board.getAuthor());
			int sqlResult = pstmt.executeUpdate();
			
			
			if(sqlResult == 1) { // insert 가 잘되었을 경우
				sql = "select postId from board where postTitle=? and postContent=? and author=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getPostTitle());
				pstmt.setString(2, board.getPostContent());
				pstmt.setString(3, board.getAuthor());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					result = rs.getInt(1);
				}
				System.out.println("가져가는 게시글 번호 : " + result);
			}else {
				System.out.println("insert 오류");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
