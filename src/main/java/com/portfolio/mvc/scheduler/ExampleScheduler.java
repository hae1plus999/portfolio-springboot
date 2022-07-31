package com.portfolio.mvc.scheduler;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class ExampleScheduler {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//크론에 5초에 한번씩 작동
	@Scheduled(cron = "#{@schedulerCronExample1}")
	public void schedule1() {
		logger.info("schedul1동작하고 있음 : {}", Calendar.getInstance().getTime());
	}
}
