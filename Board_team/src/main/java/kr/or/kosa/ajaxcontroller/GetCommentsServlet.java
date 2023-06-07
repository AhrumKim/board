package kr.or.kosa.ajaxcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dto.CommentDto;
import kr.or.kosa.service.CommentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/getCommentList")
public class GetCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService = new CommentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String postId = request.getParameter("postId");
    	List<CommentDto> commentList = commentService.getCommentsByPostId(Integer.parseInt(postId));
    	JSONArray jsonArray = new JSONArray();
        for (CommentDto comment : commentList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("coNo", comment.getCoNo());
            jsonObject.put("coContents", comment.getCoContents());
            jsonArray.add(jsonObject);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonArray.toString());
    }
}
