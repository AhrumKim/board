package kr.or.kosa.ajaxcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dto.CommentDto;
import kr.or.kosa.service.CommentService;

@WebServlet("/addComment")
public class InsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService = new CommentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String postId = request.getParameter("postId");
        String commentContents = request.getParameter("commentContents");
        CommentDto comment = CommentDto.builder()
        						.coContents(commentContents)
        						.boardNo(Integer.parseInt(postId))
        						.build();        
        response.getWriter().write(Integer.toString(commentService.insertComment(comment)));
    }
}
