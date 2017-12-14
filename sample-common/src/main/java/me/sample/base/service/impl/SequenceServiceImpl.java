package me.sample.base.service.impl;

import java.util.Map;
import java.util.Queue;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;

import me.sample.base.service.SequenceService;

@Service
@Transactional
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;
    private static final int capacity = 5;
    private static final Map<String,Queue<Long>> queueMap = Maps.newConcurrentMap();
    // private static final Queue<Long> queue = Queues.newArrayBlockingQueue(capacity);

    @Override
    public Long next() {
        return next("sys") ;
    }
    
    @Override
    public Long next(String code) {
        Queue<Long> queue = queueMap.get(code);
        if(queue == null){
            queue = Queues.newArrayBlockingQueue(capacity);
            queueMap.put(code, queue);
        }
        Long id = queue.poll();
        if (id == null) {
            long _id = jdbcTemplate.queryForObject("select id from sequence where code = ?", Long.class, code).longValue();
            for (int i = 0; i < capacity; i++) {
                queue.offer(++_id);
            }
            jdbcTemplate.update("update sequence set id = ?", _id);
            id = queue.poll();
        }
        return id;
    }
}
