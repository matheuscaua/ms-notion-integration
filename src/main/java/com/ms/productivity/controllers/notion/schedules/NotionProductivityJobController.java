package com.ms.productivity.controllers.notion.schedules;
import com.ms.productivity.commons.schedule.base.BaseJobController;
import com.ms.productivity.commons.schedule.execute.ExecutorReturnJobDTO;
import com.ms.productivity.services.impl.notion.NotionProductivityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingException;
import org.springframework.stereotype.Controller;

import java.net.URISyntaxException;

@Controller
@Slf4j
public class NotionProductivityJobController extends BaseJobController {

    @Autowired
    private NotionProductivityServiceImpl notionProductivityService;
    @Override
    public void settup() throws URISyntaxException {

    }

    @Override
    public void initJob(ExecutorReturnJobDTO returnJobDTO) throws SchedulingException {
        try{
            notionProductivityService.calculate();
        }catch (Exception e){
            log.error("ERROR JOB - {}", e.getMessage());
        }
    }
}
