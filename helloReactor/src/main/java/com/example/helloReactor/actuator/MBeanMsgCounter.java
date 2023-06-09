package com.example.helloReactor.actuator;

import com.example.helloReactor.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
@ManagedResource
public class MBeanMsgCounter{
    @Autowired
    MessageRepo mr;
    int count=0;

    @ManagedAttribute
    public long getMsgCount(){
        long count=0;
        try {count=mr.count().block();}catch (Exception f){}
        return count;
    }

    @ManagedOperation
    public long increment(int i){
        count=count+i;
        return count;
    }

    @ManagedAttribute
    public long getCounter(){
        return count;
    }
}
