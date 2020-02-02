package com.board.dto;

public class BbsDTO {
    private int bbsID = 1, bbsHit = 0;
    private String bbsTitle, bbsContent, userID, bbsDate, bbsFile;

    public String getBbsFile() { return bbsFile; }

    public void setBbsFile(String bbsFile) { this.bbsFile = bbsFile; }

    public String getBbsDate() {
        return bbsDate;
    }

    public void setBbsDate(String bbsDate) {
        this.bbsDate = bbsDate;
    }

    public int getBbsID() {
        return bbsID;
    }

    public void setBbsID(int bbsID) {
        this.bbsID = bbsID;
    }

    public int getBbsHit() {
        return bbsHit;
    }

    public void setBbsHit(int bbsHit) {
        this.bbsHit = bbsHit;
    }

    public String getBbsTitle() {
        return bbsTitle;
    }

    public void setBbsTitle(String bbsTitle) {
        this.bbsTitle = bbsTitle;
    }

    public String getBbsContent() {
        return bbsContent;
    }

    public void setBbsContent(String bbsContent) {
        this.bbsContent = bbsContent;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BbsDto [bbsID=").append(bbsID).append(", bbsHit=").append(bbsHit).append(", bbsTitle=")
                .append(bbsTitle).append(", bbsContent=").append(bbsContent).append(", bbsDate=").append(bbsDate).append(", userID=").append(userID).append("]");
        return builder.toString();
    }
}
