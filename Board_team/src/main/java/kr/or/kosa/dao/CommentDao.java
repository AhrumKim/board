package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.CommentDto;
import kr.or.kosa.utils.ConnectionHelper;

public class CommentDao {
    // 전체 조회
    public List<CommentDto> getCommentList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CommentDto> commentList = new ArrayList<>();
        String sql = "SELECT co_no, co_contents, board_no FROM COMMENT2";
        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CommentDto comment = CommentDto.builder()
                        .coNo(rs.getInt("co_no"))
                        .coContents(rs.getString("co_contents"))
                        .boardNo(rs.getInt("board_no"))
                        .build();
                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return commentList;
    }

    // 삽입
    public int insertComment(CommentDto comment) {
        int commentId = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "INSERT INTO comment2 (co_no, co_contents, board_no) VALUES (co_seq.NEXTVAL, ?, ?)";
        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, comment.getCoContents());
            pstmt.setInt(2, comment.getBoardNo());
            pstmt.executeUpdate();
            
            // 시퀀스로 생성된 commentId 가져오기
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT co_seq.CURRVAL FROM DUAL");
            if (rs.next()) {
                commentId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return commentId;
    }


    // 수정
    public int updateComment(CommentDto comment) {
        int resultRow = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE comment2 SET co_contents=? WHERE co_no=?";
        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, comment.getCoContents());
            pstmt.setInt(2, comment.getCoNo());
            resultRow = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultRow;
    }

    // 삭제
    public int deleteComment(int coNo) {
        int resultRow = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM comment2 WHERE co_no=?";
        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, coNo);
            resultRow = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultRow;
    }
    
    public List<CommentDto> getCommentsByPostId(int postId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CommentDto> commentList = new ArrayList<>();
        String sql = "SELECT co_no, co_contents, board_no FROM comment2 WHERE board_no = ?";
        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CommentDto comment = CommentDto.builder()
                        .coNo(rs.getInt("co_no"))
                        .coContents(rs.getString("co_contents"))
                        .boardNo(rs.getInt("board_no"))
                        .build();
                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return commentList;
    }
}

