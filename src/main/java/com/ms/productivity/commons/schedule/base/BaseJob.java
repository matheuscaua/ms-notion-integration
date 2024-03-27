package com.ms.productivity.commons.schedule.base;

import com.ms.productivity.enums.JobsEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.support.ScheduledMethodRunnable;

@Getter
public abstract class BaseJob {
    protected abstract void initTime();

    @Setter private boolean blocked;

    public abstract JobsEnum getJobsEnum();

    private ScheduledMethodRunnable currentTask;

    public void executeJob(BaseJobController controller){

    }
}
