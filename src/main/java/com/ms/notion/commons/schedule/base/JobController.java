package com.ms.notion.commons.schedule.base;

import com.ms.notion.commons.schedule.execute.ExecutorReturnJobDTO;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.SchedulingException;

import java.net.URISyntaxException;
import java.util.concurrent.Callable;

public interface JobController extends InitializingBean, DisposableBean, Callable<ExecutorReturnJobDTO> {

    void settup() throws URISyntaxException;

    void initJob(ExecutorReturnJobDTO returnJobDTO) throws SchedulingException;

    @Override
    default void afterPropertiesSet() throws Exception{
        settup();
    }
    @Override
    default void destroy() throws Exception {Thread.currentThread().interrupt();}

    default SchedulingException prosecuteError(String message, Exception e){
        return new SchedulingException(message, e);
    }

}
