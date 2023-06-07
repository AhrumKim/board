package kr.or.kosa.service;

import java.util.List;

import kr.or.kosa.dao.CommentDao;
import kr.or.kosa.dto.CommentDto;

public class CommentService {
	private CommentDao commentDao = new CommentDao();

	public List<CommentDto> getCommentsByPostId(int postId) {
		return commentDao.getCommentsByPostId(postId);
	}
	
	public int deleteComment(int coNo) {
		return commentDao.deleteComment(coNo);	
	}
	
	public int insertComment(CommentDto comment) {
		return commentDao.insertComment(comment);
	}
	
	public int updateComment(CommentDto comment) {
		return commentDao.updateComment(comment);
	}

}
