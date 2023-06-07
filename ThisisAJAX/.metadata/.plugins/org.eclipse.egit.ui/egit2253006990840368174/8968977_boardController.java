package kr.or.kosa.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.BoardDetailService;
import kr.or.kosa.service.BoardListService;
import kr.or.kosa.service.MemberAddService;
import kr.or.kosa.service.MemberDeleteService;
import kr.or.kosa.service.MemberListService;
import kr.or.kosa.service.MemberRegisterFormService;
import kr.or.kosa.service.MemberUpdateService;
import kr.or.kosa.service.MvcLoginService;
import kr.or.kosa.service.MvcLoginViewService;
import kr.or.kosa.service.MvcLogoutViewService;

@WebServlet("*.board")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public boardController() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");

		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlcommand = requestUri.substring(contextPath.length());

		System.out.println("requestUri : " + requestUri);
		System.out.println("contextPath : " + contextPath);
		System.out.println("urlcommand : " + urlcommand);

		Action action = null;
		ActionForward forward = null;

		if (urlcommand.equals("/boardlist.board")) {
			// 게시판 리스트
			action = new BoardListService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/boardRegister.board")) {
			// 게시판 상세보기
			action = new BoardDetailService();
			forward = action.execute(request, response);
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
