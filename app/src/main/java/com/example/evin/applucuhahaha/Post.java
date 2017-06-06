package com.example.evin.applucuhahaha;

/**
 * Created by Evin on 6/3/2017.
 */

public class Post {
    private int id;
    private String caption;
    private String gambar;
    private int likez;
    private int dislikez;
    private int id_kategori;
    private String id_user;

    public Post(int id, String caption, String gambar, int likez, int dislikez, int id_kategori, String id_user) {
        this.id = id;
        this.caption = caption;
        this.gambar = gambar;
        this.likez = likez;
        this.dislikez = dislikez;
        this.id_kategori = id_kategori;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getLikez() {
        return likez;
    }

    public void setLikez(int likez) {
        this.likez = likez;
    }

    public int getDislikez() {
        return dislikez;
    }

    public void setDislikez(int dislikez) {
        this.dislikez = dislikez;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
