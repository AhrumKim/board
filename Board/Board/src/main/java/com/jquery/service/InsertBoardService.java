package com.jquery.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.action.Action;
import com.jquery.action.ActionForward;
import com.jquery.dao.BoardDAO;
import com.jquery.dto.Board;

public class InsertBoardService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Board board = new Board();
		board.setPostTitle(request.getParameter("postTitle"));
		board.setAuthor(request.getParameter("author"));
		board.setPostContent(request.getParameter("postContent"));
		
		BoardDAO dao = new BoardDAO();
		int result = dao.insertBoard(board);
		
		ActionForward forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/Board/board/getOneBoard?" +result);
	  	  
	  	return forward;
	}

}
