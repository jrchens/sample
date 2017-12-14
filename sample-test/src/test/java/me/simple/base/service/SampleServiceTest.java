package me.simple.base.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.sample.test.service.SampleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:applicationContext.xml"})
public class SampleServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(SampleServiceTest.class);
    @Autowired
    private SampleService sampleService;
    
    @Test
    public void testGetNow(){
	String now = sampleService.getNow();
	logger.info("now: {}",now);
    }
}
