package kr.or.kosa.ajaxcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dto.CommentDto;
import kr.or.kosa.service.CommentService;

@WebServlet("/updateComment")
public class UpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService commentService = new CommentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String commentId = request.getParameter("commentId");
        String commentContents = request.getParameter("commentContents");
        String postId = request.getParameter("postId");
        CommentDto comment = CommentDto.builder()
        						.coNo(Integer.parseInt(commentId))
        						.coContents(commentContents)
        						.boardNo(Integer.parseInt(postId))
        						.build();
        response.getWriter().write(Integer.toString(commentService.updateComment(comment)));
    }
}