package com.ms.productivity.commons.schedule;

import com.ms.productivity.commons.schedule.base.BaseSchedule;
import com.ms.productivity.enums.JobsEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ScheduleFactory {
    private static final boolean BLOCKED_ALL_SCHEDULE = true;

    @Getter private List<Task> executorsJobs;

    public void processarJobs(ScheduledTaskRegistrar taskRegistrar){
        executorsJobs = new ArrayList<>();
        executorsJobs.addAll(taskRegistrar.getCronTaskList());
        executorsJobs.addAll(taskRegistrar.getFixedDelayTaskList());
        executorsJobs.addAll(taskRegistrar.getFixedRateTaskList());
        executorsJobs.addAll(taskRegistrar.getTriggerTaskList());
        executorsJobs.forEach(task -> {
            var scheduleTask = (ScheduledMethodRunnable)task.getRunnable();
            var job = (BaseSchedule)scheduleTask.getTarget();
            if (!JobsEnum.getExceptions().contains(job.getJobsEnum())){
                job.setBlocked(BLOCKED_ALL_SCHEDULE);
            }
        });
    }
}
