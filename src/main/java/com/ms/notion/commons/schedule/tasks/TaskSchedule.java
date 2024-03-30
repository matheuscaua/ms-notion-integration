package com.ms.notion.commons.schedule.tasks;

import com.ms.notion.commons.schedule.base.BaseSchedule;
import com.ms.notion.enums.JobsEnum;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskSchedule extends BaseSchedule {
    private static final String TRINTA_EM_TRINTA_SEGUNDOS = "*/30 * * * * *";

    private final TaskJobController controller;

    @Override
    @Scheduled(cron = TRINTA_EM_TRINTA_SEGUNDOS)
    protected void initTemporizador() {
        executeJob(controller);
    }

    @Override
    public JobsEnum getJobsEnum() {
        return JobsEnum.TASKS;
    }
}
