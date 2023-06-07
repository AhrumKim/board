package kr.or.kosa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dto.CommentDto;
import kr.or.kosa.dto.PostDto;
import kr.or.kosa.service.CommentService;
import kr.or.kosa.service.PostService;

@WebServlet("/board")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService;
    private CommentService commentService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        postService = new PostService();
        commentService = new CommentService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String postId = request.getParameter("id");  
        if (postId != null) {
        	int postId2 = Integer.parseInt(postId);
            postService.increaseHit(postId2);            
            PostDto clickedPost = postService.getPostById(postId2);
            List<CommentDto> commentList = commentService.getCommentsByPostId(postId2);
            request.setAttribute("clickedPost", clickedPost);
            request.setAttribute("commentList", commentList);
            request.getRequestDispatcher("/WEB-INF/views/post.jsp").forward(request, response);
        } else {
            List<PostDto> postList = postService.getPostList();
            request.setAttribute("postList", postList);            
            request.getRequestDispatcher("/WEB-INF/views/board.jsp").forward(request, response);
        }
    }
}
