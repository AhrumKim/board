package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.PostDto;
import kr.or.kosa.utils.ConnectionHelper;

public class PostDao {
    // 전체 조회
    public List<PostDto> getPostList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PostDto> postList = new ArrayList<>();
        String sql = "SELECT no, title, contents, hit FROM board2";
        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PostDto post = PostDto.builder()
                        .no(rs.getInt("no"))
                        .title(rs.getString("title"))
                        .contents(rs.getString("contents"))
                        .hit(rs.getInt("hit"))
                        .build();
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return postList;
    }

    // 삽입
    public int insertPost(PostDto post) {
        int resultRow = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO board2 (no, title, contents) VALUES (board_seq.NEXTVAL, ?, ?)";
        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContents());
            resultRow = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultRow;
    }

    // 수정
    public int updatePost(PostDto post) {
        int resultRow = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE board2 SET title=?, contents=?, hit=? WHERE no=?";
        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContents());
            pstmt.setInt(3, post.getHit());
            pstmt.setInt(4, post.getNo());
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
    public int deletePost(int no) {
        int resultRow = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM board2 WHERE no=?";
        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            resultRow = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultRow;
    }
    
    public PostDto getPostById(int postId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PostDto post = null;
        String sql = "SELECT no, title, contents, hit FROM board2 WHERE no = ?";
        try {
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                post = PostDto.builder()
                        .no(rs.getInt("no"))
                        .title(rs.getString("title"))
                        .contents(rs.getString("contents"))
                        .hit(rs.getInt("hit"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(rs);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return post;
    }
}
