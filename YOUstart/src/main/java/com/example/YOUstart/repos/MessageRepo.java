package com.example.YOUstart.repos;

import com.example.YOUstart.mysql_struct.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message,Long> {
    List<Message> findByTag(String tag);
}
