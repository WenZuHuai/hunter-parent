package com.csair.csairmind.hunter.common.response;

import com.csair.csairmind.hunter.common.vo.DetailsRule;
import lombok.Data;

/**
 * Created by zhangcheng
 * 申请详情解析任务响应类
 */
@Data
public class DetalisTaskResponse extends ApiResponse {
    private DetailsRule task;
}