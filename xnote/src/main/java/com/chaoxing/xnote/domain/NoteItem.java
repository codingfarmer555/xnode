package com.chaoxing.xnote.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 笔记条目实体
 */
public class NoteItem  implements Serializable {
       private  String username;
       private  String publishTime;
       private  int readCount;
       private  String title;
       private  String content;
       private  String[] picUrls;
       private  int commentCount;
       private  int favourCount;
       private  int transmitCount;

    public NoteItem() {
    }

    public NoteItem(String username, String publishTime, int readCount, String title, String content, String[] picUrls, int commentCount, int favourCount, int transmitCount) {
        this.username = username;
        this.publishTime = publishTime;
        this.readCount = readCount;
        this.title = title;
        this.content = content;
        this.picUrls = picUrls;
        this.commentCount = commentCount;
        this.favourCount = favourCount;
        this.transmitCount = transmitCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public  String[] getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String[] picUrls) {
        this.picUrls = picUrls;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFavourCount() {
        return favourCount;
    }

    public void setFavourCount(int favourCount) {
        this.favourCount = favourCount;
    }

    public int getTransmitCount() {
        return transmitCount;
    }

    public void setTransmitCount(int transmitCount) {
        this.transmitCount = transmitCount;
    }

    @Override
    public String toString() {
        return "NoteItem{" +
                "username='" + username + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", readCount=" + readCount +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picUrls=" + Arrays.toString(picUrls) +
                ", commentCount=" + commentCount +
                ", favourCount=" + favourCount +
                ", transmitCount=" + transmitCount +
                '}';
    }
}
