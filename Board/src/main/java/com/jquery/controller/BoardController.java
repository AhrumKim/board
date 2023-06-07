package com.jquery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jquery.action.Action;
import com.jquery.action.ActionForward;
import com.jquery.dao.BoardDAO;
import com.jquery.dto.Board;
import com.jquery.service.GetOneBoardService;
import com.jquery.service.InsertBoardService;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardDAO dao = new BoardDAO();

	public BoardController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlcommand = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward forward = null;

		// 게시글 목록 가져오기
		if (urlcommand.equals("/board/getBoardList")) {
			System.out.println("게시글 목록 가져오기 메서드 진입");
			List<Board> boardlist = dao.getBoardList();

			Gson gson = new Gson();
			String json = gson.toJson(boardlist);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} 
		
		if (forward != null) {
			if (forward.isRedirect()) { // true 페이지 재 요청 (location.href="페이지"
				response.sendRedirect(forward.getPath());
			} else { // 기본적으로 forward ....
						// 1. UI 전달된 경우
						// 2. UI + 로직
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlcommand = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward forward = null;

		if (urlcommand.equals("/board/getOneBoard")) {
			System.out.println("게시글 디테일 진입 ");
			int postId = Integer.parseInt(request.getParameter("postId"));
			System.out.println("**********************");
			System.out.println(postId);
			System.out.println("**********************");
			
			//해당 서비스에서 댓글도 가져오도록 처리함
			action = new GetOneBoardService();
			forward = action.execute(request, response);

		}else if (urlcommand.equals("/board/insertBoard")) {
			System.out.println("게시글 작성 진입");
			action = new InsertBoardService();
			forward = action.execute(request, response);
			
		}

		if (forward != null) {
			if (forward.isRedirect()) { // true 페이지 재 요청 (location.href="페이지"
				response.sendRedirect(forward.getPath());
			} else { // 기본적으로 forward ....
						// 1. UI 전달된 경우
						// 2. UI + 로직
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}

}
