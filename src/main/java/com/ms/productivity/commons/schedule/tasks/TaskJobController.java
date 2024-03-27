package com.ms.productivity.commons.schedule.tasks;

import com.ms.productivity.commons.schedule.base.BaseJobController;
import com.ms.productivity.commons.schedule.base.BaseSchedule;
import com.ms.productivity.commons.schedule.execute.ExecutorReturnJobDTO;
import com.ms.productivity.commons.schedule.ScheduleFactory;
import com.ms.productivity.enums.JobsEnum;
import com.ms.productivity.services.impl.SystemConfigurationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@AllArgsConstructor
public class TaskJobController extends BaseJobController {

    private final ScheduleFactory scheduleFactory;

    private final SystemConfigurationServiceImpl systemConfigurationService;

    @Override
    public void settup() {
        manipulingTasksScheduled();
    }

    @Override
    public void initJob(ExecutorReturnJobDTO returnJobDTO) throws SchedulingException {
        manipulingTasksScheduled();
        returnJobDTO.setResponse("Success loading the tasks");
    }

    private void manipulingTasksScheduled(){
        var listTasks = scheduleFactory.getExecutorsJobs();
        if(listTasks != null){
            listTasks.forEach(this::processarTasks);
        }
    }
    private void processarTasks(Task task){
        var scheduleTask = (ScheduledMethodRunnable)task.getRunnable();
        var job = (BaseSchedule)scheduleTask.getTarget();
        if(!JobsEnum.TASKS.equals(job.getJobsEnum())){
            var blocked = systemConfigurationService.findValueBlockTaskJobsEnum(job.getJobsEnum());
            if (blocked == null){
                systemConfigurationService.createBlockTaskConfiguration(job.getJobsEnum());
                job.setBlocked(Boolean.TRUE);
            }else if (blocked.booleanValue() != job.isBlocked()) {
                log.info("Changing the job {} for {}", job.getJobsEnum().getCode(), job.isBlocked() ? "Active" : "");
                job.setBlocked(blocked.booleanValue());
            }
        }
    }
}
