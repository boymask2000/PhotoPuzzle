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
public class Moving {
    private static int screenWidth = 0;
    private static int screenHeight = 0;
    private final Context context;
    private final Bitmap mbitmap;
    private final int imgWidth;
    private final int imgHeight;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int step = 10;


    public Moving(Context context, int x, int y, int dx, int dy) {
        this.context = context;
        getDims();
        mbitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.skull);
        imgWidth = mbitmap.getWidth();
        imgHeight = mbitmap.getHeight();
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void moveNext() {
        int vx = dx * step;
        int vy = dy * step;


        if (x + vx < 0) {
            invertDx();
            return;
        }
        if (y + vy < 0) {
            invertDy();
            return;
        }
        if (x + vx > screenWidth - imgWidth) {
            invertDx();
            return;
        }
        if (y + vy > screenHeight - imgHeight) {
            invertDy();
            return;
        }
        x += vx;
        y += vy;
    }

    public void makeDrow(Canvas canvas) {
        canvas.drawBitmap(mbitmap, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void invertDx() {
        dx = -dx;
    }

    public void invertDy() {
        dy = -dy;
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
