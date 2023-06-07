package com.jquery.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.action.Action;
import com.jquery.action.ActionForward;
import com.jquery.dao.BoardDAO;
import com.jquery.dao.BoardCommentDAO;
import com.jquery.dto.Board;

public class GetOneBoardService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int postId = Integer.parseInt(request.getParameter("postId"));
		//System.out.println(postId);
		
		BoardDAO dao = new BoardDAO();
		BoardCommentDAO commentdao = new BoardCommentDAO();
		
		Board board = dao.getOneBoard(postId);
		Map<Integer, Object> map = commentdao.getCommentListByPostId(postId);
		
		request.setAttribute("oneBoard",board);
		request.setAttribute("oneBoardComment",map);
		
		// 데이터 확인을 위한 console.log() 구문 추가 System.out.println("commentCount: " +
		/*
		 * System.out.println("commentList: " + map.get(1));
		 * System.out.println("commentList: " + map.get(2));
		 */
		 
		ActionForward forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/boardDetail.jsp");
	  	  
	  	return forward;
	}

}
