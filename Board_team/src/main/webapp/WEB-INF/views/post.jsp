<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Post Details</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="row justify-content-center">
		<div class="col-8">
			<div class="fs-4 mt-4 mb-4">상세 보기</div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col">제목</th>
						<th scope="col">내용</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${clickedPost.title}</td>
						<td>${clickedPost.contents}</td>
						<td>${clickedPost.hit}</td>
					</tr>
				</tbody>
			</table>
		
			<div class="fs-4 mt-4 mb-4">댓글 보기</div>
			<table class="table table-bordered" id="comment">
				<thead>
					<tr>
						<th scope="col" class="col-1">ID</th>
						<th scope="col" class="col-9">내용</th>
						<th scope="col" class="col-2">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="comment" items="${commentList}">
						<tr>
							<td>${comment.coNo}</td>
							<td class="cocontents">${comment.coContents}</td>
							<td>
								<button type="button" class="edit btn btn-primary">수정</button>
								<button type="button" class="delete btn btn-danger">삭제</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
			<form id="commentForm">
				<input type="hidden" id="postId" value="${clickedPost.no}">
				<input type="text" id="commentContents" placeholder="댓글을 입력하세요"	required>
				<button type="submit" id="addComment" class="btn btn-primary">댓글 입력</button>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script>
    $(document).ready(function() {
        $('#commentForm').submit(function(event) {
        	event.preventDefault();
            let postId = $('#postId').val();
            let commentContents = $('#commentContents').val();
            addComment(postId, commentContents);
            $('#commentContents').val('');
        });
        
        $('#addComment').click(function(event) {
        	event.preventDefault();
            let postId = $('#postId').val();
            let commentContents = $('#commentContents').val();
            addComment(postId, commentContents);
            $('#commentContents').val('');
        });
        
        $(document).on('click', 'button.delete', function() {
            let row = $(this).parent().parent();
            let commentId = row.find('td:first-child').text();

            $.ajax({
                url: 'deleteComment',
                type: 'POST',
                data: {
                    commentId: commentId
                },
                success: function(response) {
                	getUpdatedCommentList()
                },
                error: function(xhr, status, error) {
                    console.log(error);
                }
            });
        });
        
        $(document).on('click', 'button.edit', function() {
       	  let row = $(this).closest('tr');
       	  let commentContents = row.find('.cocontents').text();
       	  row.find('.cocontents').empty().append('<input type="text" value="' + commentContents + '">');
       	  $(this).text('확인').removeClass('edit').addClass('confirm');
       	});



       	$(document).on('click', 'button.confirm', function() {
       	  let row = $(this).closest('tr');
          let postId = $('#postId').val();
       	  let commentId = row.find('td:first-child').text();
       	  let commentContents = row.find('input[type="text"]').val();
       	  console.log(commentId);
       	  console.log(commentContents);
       	  $.ajax({
       	    url: 'updateComment',
       	    type: 'POST',
       	    data: {
    	      postId: postId,
       	      commentId: commentId,
       	      commentContents: commentContents
       	    },
       	    success: function(response) {
       	    	getUpdatedCommentList();
       	    },
       	    error: function(xhr, status, error) {
       	      console.log(error);
       	    }
       	  });
       	});

        
        function addComment(postId, commentContents) {        	
            $.ajax({
                url: 'addComment',
                type: 'POST',
                data: {
                    postId: postId,
                    commentContents: commentContents
                },
                success: function(response) {
                    $('#commentContents').val('');
                    getUpdatedCommentList();
                },
                error: function(xhr, status, error) {
                    console.log(error);
                }
            });
        }
        
        function getUpdatedCommentList() {
            let postId = $('#postId').val();
            $.ajax({
                url: 'getCommentList',
                type: 'POST',
                dataType: 'json',
                data: {
                    postId: postId
                },
                success: function(response) {
                	let commentTable = $('#comment tbody');
                    commentTable.empty();
                    
                    for (let i = 0; i < response.length; i++) {
                        let comment = response[i];
                        let newRow = '<tr>' +
                            '<td>' + comment.coNo + '</td>' +
                            '<td class="cocontents">' + comment.coContents + '</td>' +
                            '<td>' +
                            '<button type="button" class="edit btn btn-primary me-3 ms-3">수정</button>' +
                            '<button type="button" class="delete btn btn-danger">삭제</button>' +
                            '</td>' +
                            '</tr>';
                        commentTable.append(newRow);
                    }
                },
                error: function(xhr, status, error) {
                    console.log(error);
                }
            });
        }
        getUpdatedCommentList();
    });
</script>
		
</body>
</html>
