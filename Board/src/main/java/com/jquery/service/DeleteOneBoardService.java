package com.jquery.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.action.Action;
import com.jquery.action.ActionForward;
import com.jquery.dao.BoardDAO;

public class DeleteOneBoardService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int postId = Integer.parseInt(request.getParameter("postId"));
		System.out.println(postId);
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteOneBoard(postId);
		
		if(result>0) {
			System.out.println("게시글 삭제 성공");
		} else {
			System.out.println("게시글 삭제 실패");
		}
		
		ActionForward forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/main.jsp");
		
		return forward;
	}

}
