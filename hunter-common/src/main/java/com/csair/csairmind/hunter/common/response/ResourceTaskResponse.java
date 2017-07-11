package com.csair.csairmind.hunter.common.response;

import com.csair.csairmind.hunter.common.vo.ResourceRule;
import lombok.Data;

/**
 * Created by zhangcheng
 * 申请资源解析任务响应类
 */
@Data
public class ResourceTaskResponse extends ApiResponse {
    private ResourceRule task;
}