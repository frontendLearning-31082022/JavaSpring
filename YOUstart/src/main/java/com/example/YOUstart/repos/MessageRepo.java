package com.example.YOUstart.repos;

import com.example.YOUstart.mysql_struct.Message;
import com.example.YOUstart.mysql_struct.User;
import com.example.YOUstart.mysql_struct.dto.MessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message,Long> {

    @Query("SELECT new com.example.YOUstart.mysql_struct.dto." +
            "MessageDTO(m, COUNT(ml), SUM(CASE WHEN ml= :user THEN 1 ELSE 0 END)>0) " +
            "FROM Message m LEFT JOIN m.likes ml " +
            "GROUP BY m")
    Page<MessageDTO> findAll(Pageable pageable, @Param("user") User curUser);

    @Query("SELECT new com.example.YOUstart.mysql_struct.dto." +
            "MessageDTO(m, COUNT(ml), SUM(CASE WHEN ml= :user THEN 1 ELSE 0 END)>0) " +
            "FROM Message m LEFT JOIN m.likes ml " +
            "WHERE m.tag=:tag " +
            "GROUP BY m")
    Page<MessageDTO> findByTag( @Param("tag")String tag, @Param("user") User user, Pageable pageable);



    @Query("SELECT new com.example.YOUstart.mysql_struct.dto." +
            "MessageDTO(m, COUNT(ml), SUM(CASE WHEN ml= :user THEN 1 ELSE 0 END)>0) " +
            "FROM Message m LEFT JOIN m.likes ml " +
            "WHERE m.author = :author " +
            "GROUP BY m")
    Page<MessageDTO>findByUser(Pageable pageable, @Param("author") User author, @Param("user") User curUser);
}
