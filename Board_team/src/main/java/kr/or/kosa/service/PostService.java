package kr.or.kosa.service;

import java.util.List;

import kr.or.kosa.dao.PostDao;
import kr.or.kosa.dto.PostDto;

public class PostService {
	private PostDao postDao = null;

	public List<PostDto> getPostList() {
		postDao = new PostDao();
		return postDao.getPostList();
	}

	public PostDto getPostById(int postId) {
		postDao = new PostDao();
		return postDao.getPostById(postId);
	}

	public void increaseHit(int postId) {
		postDao = new PostDao();
		PostDto post = postDao.getPostById(postId);
		post.setHit(post.getHit() + 1);
		postDao.updatePost(post);
	}

}
