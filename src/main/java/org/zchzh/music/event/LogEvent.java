package org.zchzh.music.event;

import org.springframework.context.ApplicationEvent;
import org.zchzh.music.entity.LogEntity;

/**
 * @author zengchzh
 * @date 2021/6/8
 */
public class LogEvent extends ApplicationEvent {
    public LogEvent(LogEntity source) {
        super(source);
    }
}
