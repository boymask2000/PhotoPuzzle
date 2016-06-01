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
    private Chunk chunk;

    private int finx;
    private int finy;

    private int curx;
    private int cury;

    public Moving(Chunk c, int startx, int starty, int finalx, int finaly) {
        chunk=c;
        curx = startx;
        cury = starty;
        finx = finalx;
        finy = finaly;
    }

    public void nextStep() {
        int delta = 5;
        int dx=0; int dy=0;

        int min = dist(finx, finy, curx - delta, cury);
        int d1 = dist(finx, finy, curx - delta, cury - delta);
        if( d1<min){dx=-delta;dy=-delta; min=d1;}
        d1 = dist(finx, finy, curx - delta, cury + delta);
        if( d1<min){dx=-delta;dy=delta; min=d1;}

        d1 = dist(finx, finy, curx + delta, cury);
        if( d1<min){dx=delta;dy=0; min=d1;}
        d1 = dist(finx, finy, curx + delta, cury - delta);
        if( d1<min){dx=delta;dy=-delta; min=d1;}
        d1 = dist(finx, finy, curx + delta, cury + delta);
        if( d1<min){dx=delta;dy=delta; min=d1;}
        d1 = dist(finx, finy, curx, cury - delta);
        if( d1<min){dx=0;dy=-delta; min=d1;}
        d1 = dist(finx, finy, curx, cury + delta);
        if( d1<min){dx=0;dy=delta; min=d1;}

        curx+=dx;
        cury+=dy;

        chunk.setX(curx);
        chunk.setX(cury);

    }

    private int dist(int ax, int ay, int bx, int by) {
        return (ax - bx) * (ax - bx) + (ay - by) * (ay - by);
    }
}
