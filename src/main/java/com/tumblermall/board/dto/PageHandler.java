package com.tumblermall.board.dto;

//페이징용 DTO

public class PageHandler {
    private int totalCnt;   //총 게시물 개수
    private int pasgeSize;  //한 페이지의 크기               //한번에 보여줄 게시물의 개수(크기)
    private int naviSize = 10;   //페이지 네비게이션의 크기    //네비게이션:몇개의 쪽수를 보여주는지
    private int totlaPage;  //전체 페이지 개수
    private int page;       //현재 페이지
    private int beginPage;  //네이베이션의 첫번째 페이지
    private int endpage;    //네비게이션의마지막 페이지
    private boolean showPrev;   //이전 페이지로 이동하는 링크
    private boolean showNext;   //다음 페이지로 이동하는 링크



    public int getOffset() {
        return (page - 1) * pasgeSize;
    }


    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getPasgeSize() {
        return pasgeSize;
    }

    public void setPasgeSize(int pasgeSize) {
        this.pasgeSize = pasgeSize;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotlaPage() {
        return totlaPage;
    }

    public void setTotlaPage(int totlaPage) {
        this.totlaPage = totlaPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndpage() {
        return endpage;
    }

    public void setEndpage(int endpage) {
        this.endpage = endpage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", pasgeSize=" + pasgeSize +
                ", naviSize=" + naviSize +
                ", totlaPage=" + totlaPage +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endpage=" + endpage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
