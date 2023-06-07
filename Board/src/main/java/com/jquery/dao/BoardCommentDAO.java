package com.jquery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jquery.dto.BoardComment;
import com.jquery.util.ConnectionHelper;

public class BoardCommentDAO {
	//해당 게시글의 모든 댓글과 댓글의 수 가져오기
	public Map<Integer,Object> getCommentListByPostId(int postId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Map<Integer,Object> map = new HashMap<>();
		List<BoardComment> commentList = new ArrayList<>();
		int commentCount = 0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select commentId, postId, commentContent, createdAt, author from boardComment where postId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				/* System.out.println("가져온 데이터가 있어요"); */
				BoardComment comment = new BoardComment(
												rs.getInt("commentId"),
												rs.getInt("postId"),
												rs.getString("commentContent"),
												rs.getTimestamp("createdAt"),
												rs.getString("author")
											);
				commentList.add(comment);
				commentCount++;
			}
			
			/*
			 * System.out.println("**************************");
			 * System.out.println("commentCount : " + commentCount);
			 * System.out.println("commentList : " + commentList);
			 * System.out.println("**************************");
			 */
			
			map.put(1, commentCount);
			map.put(2, commentList);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		/* System.out.println("게시글 댓글 리턴"); */
		return map;
	}
	
	// 댓글 작성
	public int insertComment(BoardComment comment) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			
			String sql = "INSERT INTO BoardComment (commentId, postId, commentContent, createdAt, author)"
					+ "VALUES (boardComment_seq.NEXTVAL, ?,  ? , SYSDATE, ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getPostId());
			pstmt.setString(2, comment.getCommentContent());
			pstmt.setString(3, comment.getAuthor());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
			
		}
		
		return result;
	}
	
	//댓글 수정
	public int updateComment(BoardComment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "update boardComment set commentContent=?, createdAt=SYSDATE where commentId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setInt(2, comment.getCommentId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		return result;
	}
	
	//댓글 삭제
	public int deleteComment(int commentId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "delete from boardComment where commentID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}
	
}
