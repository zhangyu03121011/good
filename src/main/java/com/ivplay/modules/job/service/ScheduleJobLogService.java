package com.ivplay.modules.job.service;

import com.github.pagehelper.PageInfo;
import com.ivplay.common.base.service.BaseService;
import com.ivplay.modules.job.model.ScheduleJobLog;

/**
 *
 * Created by cuip on 2017/5/20.
 */
public interface ScheduleJobLogService extends BaseService<ScheduleJobLog> {
    /**
     * 根据ID分页查询调度任务历史
     * @param pageNum
     * @param pageSize
     * @param jobName
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<ScheduleJobLog> findPage(Integer pageNum, Integer pageSize, Long jobId, String jobName, String startTime, String endTime);
}
