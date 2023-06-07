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
import kr.or.kosa.service.MemoAddService;
import kr.or.kosa.service.MemoIdCheckService;
import kr.or.kosa.service.MemoListService;



@WebServlet("*.memo")
public class FrontMemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public FrontMemoController() {
        super();
        
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	//request.setCharacterEncoding("UTF-8");
    	//필터 한글 처리 고민 하지 마세요 ....
    	
       	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlcommand = requestURI.substring(contextPath.length());
    	

    	Action action=null;
    	ActionForward forward=null;
    	
    	if(urlcommand.equals("/MemoAdd.memo")) {  //글쓰기 처리
    		//UI + 로직
    		action = new MemoAddService();
    		forward = action.execute(request, response);
    		
    	}else if(urlcommand.equals("/MemoList.memo")) { //목록보기
    		//UI + 로직
    		action = new MemoListService();
    		forward = action.execute(request, response);
    		
    	}else if(urlcommand.equals("/MemoId.memo")) { //비동기 사용유뮤(별도의 servlet)
    		//UI + 로직
    		//비동기도 동일한 로직으로 처리할 것이냐 고민 ^^
    		action = new MemoIdCheckService();
    		forward = action.execute(request, response);
    	}else if(urlcommand.equals("/MemoView.memo")) {  //만약 상세보기 있다면
    		//UI 제공
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/memoview.jsp");
    	}
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 페이지 재 요청 (location.href="페이지"
    			response.sendRedirect(forward.getPath());
    		}else { //기본적으로 forward ....
    			    //1. UI 전달된 경우
    			    //2. UI + 로직
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

}
