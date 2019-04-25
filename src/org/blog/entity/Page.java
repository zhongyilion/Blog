package org.blog.entity;

public class Page {
    private int nowpage;   //现页
    private int firstpage;   //首页
    private int lastpage;   //尾页
    private int nextpage;   //下一页
    private int previouspage;  //上一页
    private int allcount;   //总信息条数
    private int everyPageCount;  //每页的信息条数

    public int getNowpage() {
        return nowpage;
    }

    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }

    public int getFirstpage() {
        return firstpage;
    }

    public void setFirstpage(int firstpage) {
        this.firstpage = firstpage;
    }

    public int getLastpage() {
        return lastpage;
    }

    public void setLastpage(int lastpage) {
        this.lastpage = lastpage;
    }

    public int getNextpage() {
        return nextpage;
    }

    public void setNextpage(int nextpage) {
        this.nextpage = nextpage;
    }

    public int getPreviouspage() {
        return previouspage;
    }

    public void setPreviouspage(int previouspage) {
        this.previouspage = previouspage;
    }

    public int getAllcount() {
        return allcount;
    }

    public void setAllcount(int allcount) {
        this.allcount = allcount;
    }

    public int getEveryPageCount() {
        return everyPageCount;
    }

    public void setEveryPageCount(int everyPageCount) {
        this.everyPageCount = everyPageCount;
    }

    @Override
    public String toString() {
        return "page{" +
                "nowpage=" + nowpage +
                ", firstpage=" + firstpage +
                ", lastpage=" + lastpage +
                ", nextpage=" + nextpage +
                ", previouspage=" + previouspage +
                ", allcount=" + allcount +
                ", everyPageCount=" + everyPageCount +
                '}';
    }
}
