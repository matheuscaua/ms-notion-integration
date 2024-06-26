package com.ms.notion.controllers.notion.schedules;

import com.ms.notion.commons.schedule.base.BaseSchedule;
import com.ms.notion.enums.JobsEnum;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotionProductivitySchedule extends BaseSchedule {

    private final NotionProductivityJobController controller;
    private static final String THIRTY_IN_THIRTY_SECONDS = "*/30 * * * * *";

    @Override
    @Scheduled(cron = THIRTY_IN_THIRTY_SECONDS)
    protected void initTemporizador() {
        executeJob(controller);
    }

    @Override
    public JobsEnum getJobsEnum() {
        return JobsEnum.PRODUCTIVITY;
    }
}
