package com.example.demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
public class JobStarterTests {

	@Autowired
    private ScheduledTasks tasks;

    @Test
    public void contextLoads() {
        
    }
}
