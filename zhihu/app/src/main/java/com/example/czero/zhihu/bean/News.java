package com.example.czero.zhihu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 15-2-3.
 */
public class News implements Serializable {
    private int id;
    private String title;
    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public News() {
    }

    public News(int id, String title, String image) {
        this.id = id;
        this.title = title;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public int getId() {

        return id;
    }


}
