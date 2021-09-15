package com.example.calculator.bean;

public class RollItem {
    private String content;
    private int Rid;

    public RollItem(String content, int Rid){
        this.content = content;
        this.Rid = Rid;
    }

    public int getRid() {
        return Rid;
    }

    public void setRid(int rid) {
        Rid = rid;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString(){
        return content;
    }

}
