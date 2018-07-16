package com.example.aman.fusion;
/**
 * Created by Lakshya on 27-03-2018.
 */

public class ListDataItem {
    private String titleText;
    private String contentText;
    private String imageUrl;
    private String extraLink;

    public ListDataItem(String titleText, String contentText){
        this.titleText = titleText;
        this.contentText = contentText;
        this.imageUrl = null;
        this.extraLink = null;
    }

    public ListDataItem(String titleText, String contentText, String imageUrl){
        this.titleText = titleText;
        this.contentText = contentText;
        this.imageUrl = imageUrl;
        this.extraLink = null;
    }


    public ListDataItem(String titleText, String contentText, String imageUrl, String extraLink){
        this.titleText = titleText;
        this.contentText = contentText;
        this.imageUrl = imageUrl;
        this.extraLink = extraLink;
    }

    public String getTitleText(){ return titleText;}

    public String getContentText(){ return contentText;}

    public String getImageUrl(){ return imageUrl;}

    public boolean hasImage(){
        if (imageUrl == null)
            return false;
        return true;
    }

    public boolean hasExtraLink(){
        if (extraLink == null)
            return false;
        return true;
    }

    public boolean hasContentText(){
        if (contentText == null)
            return false;
        return true;
    }

    public String getExtraLink(){ return extraLink;}
}
