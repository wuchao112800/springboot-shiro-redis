package com.example.demo.Config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class SessionDaoConfig extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    //添加redis key 前缀
    private static final String keyPrefix = "session:";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        SimpleSession session1 = (SimpleSession) session;
        session1.setId(sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("doReadSession======"+sessionId);
        Session o = (Session) redisTemplate.opsForValue().get(keyPrefix+sessionId);
        if (o!=null)
            System.out.println("doReadSession:  "+o.getId());
        return o;
    }

    @Override
    protected void doUpdate(Session session) {
        if (session instanceof ValidatingSession) {
            System.out.println(session.getTimeout());
            System.out.println(session.getId());
            if (((ValidatingSession) session).isValid()) {
                redisTemplate.opsForValue().set(keyPrefix+session.getId(), session);
            } else {
                redisTemplate.delete(keyPrefix+session.getId());
            }
        } else {
            redisTemplate.opsForValue().set(session.getId(), session);
        }
    }

    @Override
    protected void doDelete(Session session) {
        redisTemplate.delete(keyPrefix+session.getId());
    }
}
