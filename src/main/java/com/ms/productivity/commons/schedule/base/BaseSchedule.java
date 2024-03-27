package com.ms.productivity.commons.schedule.base;

import com.ms.productivity.commons.schedule.execute.ExecutorReturnJobDTO;
import com.ms.productivity.enums.JobsEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.support.ScheduledMethodRunnable;

import java.io.Serializable;
import java.util.concurrent.*;

@Getter
@Slf4j
public abstract class BaseSchedule {

    protected abstract void initTemporizador();

    @Setter private boolean blocked;
    public abstract JobsEnum getJobsEnum();
    @Setter private ScheduledMethodRunnable currentTask;

    public void executeJob(BaseJobController controller){
        executeJob(controller, null);
    }

    public void executeJob(BaseJobController controller, Serializable serializable){
        controller.setJobsEnum(getJobsEnum());
        var executorService = Executors.newSingleThreadExecutor();
        try{
            if(!blocked){
                if(serializable != null){
                    controller.setReturnJobDTO(new ExecutorReturnJobDTO());
                    controller.getReturnJobDTO().setResponse(serializable);
                }
                Future<ExecutorReturnJobDTO> result = executorService.submit(controller);
                result.get(getJobsEnum().getLimitTime(), TimeUnit.MINUTES);
            }
        }catch (InterruptedException | ExecutionException | TimeoutException e){
            interromperJobPeloTimeout(controller, e);
        }
        executorService.shutdownNow();
    }
    public void interromperJobPeloTimeout(BaseJobController controller, Exception e){
        try {
            controller.destroy();
        }catch (Exception e1){
            log.error("A job {} foi interrompida por timeout", getJobsEnum().getCode(), e);
        }
    }
}
