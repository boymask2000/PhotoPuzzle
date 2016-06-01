package com.example.gposabella.basic;

import android.graphics.Bitmap;

/**
 * Created by gposabella on 01/06/2016.
 */
public class Chunk {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int width;
    private int height;


    private int posCorretta;
    private int posAttuale;

    public Chunk(Bitmap bitmap, int x, int y, int w, int h) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public boolean isInside(float x1, float y1) {



        if (x1 >= x && x1 <= x + width && y1 >= y && y1 <= y + height) return true;


        return false;
    }
    public int getPosCorretta() {
        return posCorretta;
    }

    public void setPosCorretta(int posCorretta) {
        this.posCorretta = posCorretta;
    }

    public int getPosAttuale() {
        return posAttuale;
    }

    public void setPosAttuale(int posAttuale) {
        this.posAttuale = posAttuale;
    }

}
