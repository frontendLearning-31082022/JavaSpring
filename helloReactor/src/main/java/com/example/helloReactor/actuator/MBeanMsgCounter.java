package com.example.helloReactor.actuator;

import com.example.helloReactor.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Service;
import javax.management.Notification;

@Service
@ManagedResource
public class MBeanMsgCounter implements NotificationPublisherAware {
    @Autowired
    MessageRepo mr;
    private NotificationPublisher np;

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

        if (count%2==0)np.sendNotification(
                new Notification("msg.cnt",this,count,"pushed new"));

        return count;
    }

    @ManagedAttribute
    public long getCounter(){
        return count;
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher np) {
        this.np=np;
    }
}
