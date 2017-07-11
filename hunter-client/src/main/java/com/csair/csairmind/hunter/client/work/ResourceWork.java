package com.csair.csairmind.hunter.client.work;

import static com.csair.csairmind.hunter.common.enums.OperateCodeHolder.RESOURCE_TASK_SUCCESS;

import com.csair.csairmind.hunter.common.config.RedisConfigVo;
import com.csair.csairmind.hunter.common.request.OperateResult;
import com.csair.csairmind.hunter.common.request.ResourceTaskRequest;
import com.csair.csairmind.hunter.common.response.ApiResponse;
import com.csair.csairmind.hunter.common.response.ResourceTaskResponse;
import com.csair.csairmind.hunter.common.vo.ResourceRule;
import com.csair.csairmind.hunter.spider.ExpandSpider;
import com.csair.csairmind.hunter.spider.factory.DistinctFactory;
import com.csair.csairmind.hunter.spider.increment.ResourceTaskIncrement;
import com.csair.csairmind.hunter.spider.processor.currency.ResourcesProcessor;
import com.csair.csairmind.hunter.spider.schedule.ResourceTaskScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * Created by zhangcheng
 * 资源解析任务工作
 */
@Slf4j
@Component
public class ResourceWork extends BaseThread {

    @Autowired
    RedisConfigVo redisConfigVo;

    public ResourceWork() {

    }

    @Override
    public void doing() {
        super.initClient();
        try {
            ResourceTaskRequest request = new ResourceTaskRequest();
            OperateResult result = defaultApiClient.execute(request);
            if (!result.getOperateCodeHolder().equals(RESOURCE_TASK_SUCCESS)) {
                takeARest(1000);
            } else {
                ApiResponse response = result.getResponse();
                ResourceTaskResponse rsp = (ResourceTaskResponse) response;
                ResourceRule task = rsp.getTask();
                if (task == null) {
                    log.info("无资源解析任务");
                } else {
                    log.info("开始执行资源解析任务");
                    ExpandSpider.
                            create(task, new JedisPool(redisConfigVo.getHostName()))
                            .run();
                }
            }
        } catch (Exception e) {
            log.error("申请任务失败，调用接口出错", e);
        }
    }
}
