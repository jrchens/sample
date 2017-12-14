package me.sample.test.service.impl;

import java.util.Random;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.sample.test.service.SampleService;

@Service
@Transactional(readOnly = true)
public class SampleServiceImpl implements SampleService {

    private static final Logger logger = LoggerFactory.getLogger(SampleService.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String getNow() {
	logger.info("me.sample.base.service.SampleService.getNow");
	return jdbcTemplate.queryForObject("select now()", String.class);
    }

    @Override
    public String getVersion() {
	logger.info("me.sample.base.service.SampleService.getVersion");
	return jdbcTemplate.queryForObject("select version()", String.class);
    }
    
    @Override
    @Cacheable(cacheNames={"default"})
    public String getRandom(long seed) {
	logger.info("me.sample.base.service.SampleService.getRandom");
	Random random = new Random();
	random.setSeed(seed);
	return String.valueOf(random.nextInt());
    }
}
