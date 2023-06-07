package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemoDao;

public class MemoIdCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward=null;
		String idcheck=null;
		
		try {
			
			String id = request.getParameter("id");
	    	MemoDao dao = new MemoDao();
	    	idcheck= dao.isCheckById(id);
	    	System.out.println("idcheck : " + idcheck);
	    	request.setAttribute("idcheck", idcheck);
	    	
	    	forward = new ActionForward();
		    forward.setRedirect(false); //True 클라이언트가 새로운 페이지를 요청하게 할 거예요 
			forward.setPath("/WEB-INF/views/uservalid.jsp");
	    	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;
    
		
		
		
		
		
		
	}

}
