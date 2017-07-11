package com.csair.csairmind.hunter.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhangcheng
 * 爬虫任务实体
 */
@Data
public class SpriderTask implements Serializable {

    private String task_id;
    //地址
    private String url;
    //分页表达式
    private String page_reg;
    //详情地址Xpath
    private String details_url_xpath;
    //详情地址正则表达式
    private String details_url_reg;
    //详情地址Jpath
    private String details_url_jpath;
    //去重类型
    private String distinct_type;
    //标题在网页上的位置
    private String title_extract_rule;
    //日期在网页上的位置
    private String date_extract_rule;
    //来源URL在网页上的位置
    private String source_extract_rule;
    //内容在网页上的位置
    private String content_extract_rule;
    //来源网站
    private String data_source;
    //任务类型
    private Integer task_type;
    //增量间隔
    private Long increment_rule;
    //搜索关键字
    private String search_wrods;
    //爬取的最大页数
    private Integer max_page_size;
    //创建时间
    private String create_time;
    //最近请求时间
    private String request_time;
    //爬虫状态
    private Integer task_status;
    //关键字编码格式
    private String wrod_code;
    //是否启用代理
    private Integer is_proxy;

}
