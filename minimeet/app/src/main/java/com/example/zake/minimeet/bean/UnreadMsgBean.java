package com.example.zake.minimeet.bean;

import java.util.List;

/**
 * Created by Steve on 2016/7/5.
 */
public class UnreadMsgBean {

    //进行中

    private int orderstate_in_progress;

    private List<Integer> orderstate_in_progress_list;

    //待评价

    private int orderstate_pending_comment;

    private List<Integer> orderstate_pending_comment_list;

    //已结束

    private int orderstate_ended;

    private List<Integer> orderstate_ended_list;

    public int getOrderstate_in_progress() {
        return orderstate_in_progress;
    }

    public void setOrderstate_in_progress(int orderstate_in_progress) {
        this.orderstate_in_progress = orderstate_in_progress;
    }

    public List<Integer> getOrderstate_in_progress_list() {
        return orderstate_in_progress_list;
    }

    public void setOrderstate_in_progress_list(List<Integer> orderstate_in_progress_list) {
        this.orderstate_in_progress_list = orderstate_in_progress_list;
    }

    public int getOrderstate_pending_comment() {
        return orderstate_pending_comment;
    }

    public void setOrderstate_pending_comment(int orderstate_pending_comment) {
        this.orderstate_pending_comment = orderstate_pending_comment;
    }

    public List<Integer> getOrderstate_pending_comment_list() {
        return orderstate_pending_comment_list;
    }

    public void setOrderstate_pending_comment_list(List<Integer> orderstate_pending_comment_list) {
        this.orderstate_pending_comment_list = orderstate_pending_comment_list;
    }

    public int getOrderstate_ended() {
        return orderstate_ended;
    }

    public void setOrderstate_ended(int orderstate_ended) {
        this.orderstate_ended = orderstate_ended;
    }

    public List<Integer> getOrderstate_ended_list() {
        return orderstate_ended_list;
    }

    public void setOrderstate_ended_list(List<Integer> orderstate_ended_list) {
        this.orderstate_ended_list = orderstate_ended_list;
    }
}
