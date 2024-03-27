package com.ms.productivity.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum JobsEnum {

    NOTION_DATABASE("NOTION_DATABASE_JOB", "GET Notion Databases", 2),
    PRODUCTIVITY("NOTION_PRODUCTIVITY", "GET Notion Productivity", 5),
    EXECUTOR_JOBS("EXECUTOR JOBS", "Execute Jobs", 360),
    TASKS("HABILITA TASKS", "Execute Jobs", 360),
    DESABILITAR("DESABILITA TASKS", "Execute Jobs", 360);

    private final String code;
    private final String jobName;
    private final int limitTime;

    JobsEnum(String code, String jobName, int limitTime) {
        this.code = code;
        this.jobName = jobName;
        this.limitTime = limitTime;
    }

    public static List<JobsEnum> getExceptions(){
        var listExceptions = new ArrayList<JobsEnum>();
        listExceptions.add(JobsEnum.TASKS);
        //listExceptions.add(JobsEnum.DESABILITAR);
        return listExceptions;
    }
}
