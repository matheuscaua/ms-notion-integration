package com.ms.productivity.commons.schedule.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.productivity.commons.schedule.execute.ExecutorReturnJobDTO;
import com.ms.productivity.enums.JobsEnum;
import com.ms.productivity.enums.StatusJobEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;

@Data
@NoArgsConstructor
public abstract class BaseJobController implements JobController {

    private static final String JOB = "JOB";

    private ExecutorReturnJobDTO returnJobDTO;

    private JobsEnum jobsEnum;

    private ObjectMapper objectMapper;



    @Override
    public ExecutorReturnJobDTO call() throws Exception {
        return executeJob();
    }
    public ExecutorReturnJobDTO executeJob(){
        var returnJob = this.returnJobDTO != null ? this.returnJobDTO : new ExecutorReturnJobDTO();
        var log = LoggerFactory.getLogger(getClass());

        try {
            log.info("Init - {}", jobsEnum.getJobName());
            initJob(returnJob);
            returnJob.finsihed(StatusJobEnum.SUCCESS);
            log.info("Finished execution - {}",jobsEnum.getJobName());
        }catch (Exception e){
            returnJob.setMessage(e.getMessage());
            returnJob.finsihed(StatusJobEnum.ERROR);
        }
        return returnJobDTO;
    }
}
