package com.example.helloReactor.actuator;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Endpoint(id = "notes",enableByDefault = true)
public class NotesEndPoint {
    private List<Note>notes=new ArrayList<>();
    @ReadOperation
    public List<Note>notes(){
        return notes;
    }
    @WriteOperation
    public List<Note>add(String text){
        notes.add(new Note(text));
        return notes;
    }
    @DeleteOperation
    public List<Note>delete(int index){
        if(notes.size() > index)notes.remove(index);
        return notes;
    }
}

@Data
@RequiredArgsConstructor
class Note{
    private Date time=new Date();
    private final String text;
}