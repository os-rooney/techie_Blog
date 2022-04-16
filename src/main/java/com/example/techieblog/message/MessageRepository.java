package com.example.techieblog.message;

import com.example.techieblog.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAllByOrderByPostedAtDesc();
    List<Message> findMessagesByUserOrderByPostedAtDesc(User user);
    List<Message> findMessageById(long messageID);

}
