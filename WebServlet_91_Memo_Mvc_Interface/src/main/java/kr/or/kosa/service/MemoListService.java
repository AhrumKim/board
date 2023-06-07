package kr.or.kosa.service;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.MemoDao;
import kr.or.kosa.dto.Memo;

public class MemoListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward=null;
    	try {
    		 MemoDao dao = new MemoDao();
			 List<Memo> memolist = dao.getMemoList();
			 request.setAttribute("memolist", memolist);
			 
			 forward = new ActionForward();
			 forward.setRedirect(false);
			 forward.setPath("/WEB-INF/views/memolist.jsp");
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
