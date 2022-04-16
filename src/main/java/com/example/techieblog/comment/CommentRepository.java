package com.example.techieblog.comment;

import com.example.techieblog.message.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findCommentsByMessageOrderByPostedAtAsc(long messageID);
    List<Comment> findAllByMessage_Id(long messageID);


}
