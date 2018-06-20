package com.xunyi.cloud.wisdom.model;

import java.io.Serializable;

/**
 *
 * @ClassName: ReportOverdueConfig
 * @Description: 报告过期配置
 * @author lwf
 * @date 2016年10月19日 下午4:00:18
 *
 */
public class ReportOverdueConfig implements Serializable{

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -8239050202409012443L;

    public static final String OVERDUE_COLLECTION_NAME = "config_report_overdue";

    private Integer channel_type;
    private String channel_name;
    private Integer overdue_date;
    private String overdue_unit;

    public Integer getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(Integer channel_type) {
        this.channel_type = channel_type;
    }

    public Integer getOverdue_date() {
        return overdue_date;
    }

    public void setOverdue_date(Integer overdue_date) {
        this.overdue_date = overdue_date;
    }

    public String getOverdue_unit() {
        return overdue_unit;
    }

    public void setOverdue_unit(String overdue_unit) {
        this.overdue_unit = overdue_unit;
    }

    public String getChannel_name()
    {
        return channel_name;
    }

    public void setChannel_name(String channel_name)
    {
        this.channel_name = channel_name;
    }


}
