package com.jquery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dao.BoardCommentDAO;
import com.jquery.dao.BoardDAO;
import com.jquery.dao.MemberDAO;
import com.jquery.dto.Board;
import com.jquery.dto.BoardComment;
import com.jquery.dto.Member;

@WebServlet("/api")
public class ApiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO dao = new MemberDAO();
	BoardDAO boardDAO = new BoardDAO();
	BoardCommentDAO boardCommentDAO = new BoardCommentDAO();
       
    public ApiController() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlcommand = requestURI.substring(contextPath.length());
		
		if (urlcommand.equals("/api/join")) {	 //회원가입
			System.out.println("회원가입 진입");
			Member dto = new Member(request.getParameter("user_id"),request.getParameter("user_pwd"));
			int result = dao.joinMember(dto);
		} else if (urlcommand.equals("/api/login")) { //로그인
			System.out.println("로그인 진입");
			Member dto = new Member(request.getParameter("user_id"),request.getParameter("user_pwd"));
			int result = dao.login(dto);
			System.out.println("result : " + result);
			
			//데이터 보내기
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(String.valueOf(result));
		} else if (urlcommand.equals("/api/deleteOneBoard")) { // 게시판 게시글 삭제
			int postId = Integer.parseInt(request.getParameter("postId"));
			System.out.println(postId);
			int result = boardDAO.deleteOneBoard(postId);
			
			if(result>0) {
				System.out.println("게시글 삭제 성공");
			} else {
				System.out.println("게시글 삭제 실패");
			}
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(String.valueOf(result));
			
		}else if (urlcommand.equals("/api/updateOneBoard")) { // 게시판 게시글 수정
			System.out.println("게시글 수정 진입");
			Board board = new Board();
			
			board.setPostId(Integer.parseInt(request.getParameter("postId")));
			board.setPostTitle(request.getParameter("postTitle"));
			board.setPostContent(request.getParameter("postContent"));
			System.out.println(board);
			boardDAO.updateOneBoard(board);
			
			
		}else if (urlcommand.equals("/api/insertBoard")) { // 게시글 작성
			Board board = new Board();
			board.setPostTitle(request.getParameter("postTitle"));
			board.setAuthor(request.getParameter("author"));
			board.setPostContent(request.getParameter("postContent"));
			
			int result = boardDAO.insertBoard(board);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(String.valueOf(result));
			
		}else if (urlcommand.equals("/api/insertComment")) { // 댓글 작성
			BoardComment comment = new BoardComment();
			comment.setCommentContent(request.getParameter("commentContent"));
			comment.setPostId(Integer.parseInt(request.getParameter("postId")));
			comment.setAuthor(request.getParameter("author"));
			
			int result = boardCommentDAO.insertComment(comment);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(String.valueOf(result));
				
			
		}else if (urlcommand.equals("/api/updateOneComment")) { // 댓글 수정
			BoardComment comment = new BoardComment();
			comment.setCommentId(Integer.parseInt(request.getParameter("commentId")));
			comment.setCommentContent(request.getParameter("commentContent"));
			
			int result = boardCommentDAO.updateComment(comment);
			if(result>0) {
				System.out.println("댓글 update 성공");
			}
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(String.valueOf(result));
		
		}else if (urlcommand.equals("/api/deleteOneComment")) { // 댓글 삭제
			int commentId = Integer.parseInt(request.getParameter("commentId"));
			int result = boardCommentDAO.deleteComment(commentId);
			if(result>0) {
				System.out.println("댓글 삭제 성공");
			}
			
		} else if (urlcommand.equals("/api/idChk")) { //아이디 중복 체크
			System.out.println("로그인 진입");
			String id = request.getParameter("user_id");
			System.out.println("넘어온 유저 아이디 : " + id);
			
			int result = dao.idChk(id);
			
			//데이터 보내기
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(String.valueOf(result));
		} 
		
	}

}
