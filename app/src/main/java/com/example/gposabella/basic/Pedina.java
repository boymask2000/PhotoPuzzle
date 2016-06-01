package com.example.gposabella.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by gposabella on 30/05/2016.
 */
public class Pedina {
    private static int screenWidth = 0;
    private static int screenHeight = 0;
    private final Context context;
    private final Bitmap mbitmap;
    private final int imgWidth;
    private final int imgHeight;
    private final Board board;

    private int x;
    private int y;
    float y1;
    float x1;


    public Pedina(Context context, Board board, int x, int y) {
        this.context = context;
        this.board = board;
        getDims();
        mbitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.skull);
        imgWidth = mbitmap.getWidth();
        imgHeight = mbitmap.getHeight();
        this.x = x;
        this.y = y;

        x1 = board.translateX(x);
        y1 = board.translateY(y);
    }



    public void makeDrow(Canvas canvas) {

        canvas.drawBitmap(mbitmap, x1, y1, null);
    }

    public void makeDrow(float x1, float y1) {
        this.x1 = x1;
        this.y1 = y1;


    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
        x1 = board.translateX(x);
    }

    public void setY(int y) {
        this.y = y;
        y1 = board.translateY(y);
    }

    private void getDims() {
        if (screenWidth != 0) return;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Display display = wm.getDefaultDisplay();
        Point size = new Point();

        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }
}
