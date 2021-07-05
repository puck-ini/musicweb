package org.zchzh.music.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import org.zchzh.music.model.entity.LogEntity;

/**
 * @author zengchzh
 * @date 2021/6/8
 */

public class LogEvent extends ApplicationEvent {

    private LogEntity logEntity;

    public LogEvent(LogEntity source) {
        super(source);
        this.logEntity = new LogEntity();
    }

    public LogEvent(Object source, LogEntity logEntity) {
        super(source);
        this.logEntity = logEntity;
    }

    public LogEntity getLogEntity() {
        return logEntity;
    }

    public void setLogEntity(LogEntity logEntity) {
        this.logEntity = logEntity;
    }
}
