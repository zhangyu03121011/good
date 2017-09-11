package com.ivplay.modules.job.mapper;

import com.ivplay.common.base.mapper.BaseMapper;
import com.ivplay.modules.job.model.ScheduleJob;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/5/4.
 */
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {
    /**
     * 注:通用mapper 不支持@PostConstruct
     * 查询所有任务
     * @return
     */
    List<ScheduleJob> findList();
}
