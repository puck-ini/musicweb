package org.zchzh.music.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zchzh.music.entity.newentity.LogEntity;
import org.zchzh.music.event.LogEvent;
import org.zchzh.music.repository.LogEntityRepo;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

@Component
public class LogListener {

    @Autowired
    private LogEntityRepo repo;

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveDemoLog(LogEvent event) {
        LogEntity logEntity = (LogEntity) event.getSource();
        repo.save(logEntity);
    }
}
