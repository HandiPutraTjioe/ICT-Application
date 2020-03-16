package com.english.englishproject.model;

public class EnglishBooks {

    private String name;
    private String description;
    private String category;
    private String imgUrl;
    private String rating;
    private String penerbit;

    public EnglishBooks() {
    }

    public EnglishBooks(String name, String description, String category, String imgUrl, String rating, String penerbit) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.penerbit = penerbit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
}
