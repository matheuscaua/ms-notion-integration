package com.ms.productivity.configuration;

import com.ms.productivity.commons.schedule.ScheduleFactory;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
@EnableScheduling
@AllArgsConstructor
@Configuration
public class ScheduleConfiguration implements SchedulingConfigurer {

    private static final Integer MAXIMUM_NUMBER_TASKS = 12;

    private final ScheduleFactory scheduleFactory;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(initTaskScheduler());
        scheduleFactory.processarJobs(taskRegistrar);
    }
    private TaskScheduler initTaskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(MAXIMUM_NUMBER_TASKS);
        taskScheduler.setRemoveOnCancelPolicy(true);
        taskScheduler.initialize();
        return taskScheduler;
    }
}
