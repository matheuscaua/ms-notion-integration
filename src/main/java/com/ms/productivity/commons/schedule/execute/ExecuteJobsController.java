package com.ms.productivity.commons.schedule.execute;

import com.ms.productivity.commons.schedule.base.BaseJobController;
import com.ms.productivity.enums.JobsEnum;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/executeJobs")
@AllArgsConstructor
public class ExecuteJobsController {

    private static final String JOB_SUFIXE = "JobController";

    private final ApplicationContext context;

    @PostMapping(value = "/{name}")
    public ExecutorReturnJobDTO executeJob(@PathVariable String name){
        try{
            var job = (BaseJobController)context.getBean(name + JOB_SUFIXE);
            job.setJobsEnum(JobsEnum.EXECUTOR_JOBS);
            return job.executeJob();
        }catch (BeansException e){
            var errorResponse = new ExecutorReturnJobDTO();
            var jobs = findNameJobs();
            errorResponse.setResponse("Jobs disponíveis: " + jobs);
            return errorResponse;
        }
    }

    private List<String> findNameJobs(){
        var beans = Arrays.stream(context.getBeanDefinitionNames());
        beans = beans.filter(b -> b.contains(JOB_SUFIXE));
        beans = beans.map(this::formatNameJob);
        return beans.collect(Collectors.toList());
    }

    private String formatNameJob(String nameBean){ return nameBean.replace(JOB_SUFIXE, "");}
}
