package cn.yellowgg.entity;

import lombok.Data;

/**
 * @Author:黄广
 * @Description:分页的bean
 * @Date: Created in 19-3-18 下午4:04
 */
@Data
public class PageBean {
    /**
     * 页数
     */
    private Integer page;

    /**
     * 每页记录数
     */
    private Integer rows;

    /**
     * 起始页
     */
    private Integer start;

    public PageBean(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }

    public Integer getStart() {
        //起始页算法
        return (page - 1) * rows;
    }
}
