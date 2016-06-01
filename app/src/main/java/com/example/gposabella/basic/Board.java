package com.example.gposabella.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gposabella on 31/05/2016.
 */
public class Board {
    private static final int NROW = 5;
    private static final int NCOL = 5;
    private int cellSize = 220;
    private final Context context;
    private Pedina tab[][] = new Pedina[NROW][NCOL];
    private int chunkHeight;
    private int chunkWidth;

    private void init() {
        for (int i = 0; i < NROW; i++)
            for (int j = 0; j < NCOL; j++) {
                if (i == 0 && j == 0) continue;
                tab[i][j] = new Pedina(context, this, i, j);
            }
    }

    public Board(Context context) {
        this.context = context;
        init();
    }







    public void makeDraw(Canvas canvas) {
        for (int i = 0; i < NROW; i++)
            for (int j = 0; j < NCOL; j++) {
                if (tab[i][j] != null)
                    tab[i][j].makeDrow(canvas);
            }
    }


    public float translateX(int x) {
        return x * cellSize;
    }

    public float translateY(int y) {
        return y * cellSize;
    }

    public void moveToFree(Pedina p) {
        int pi = p.getX();
        int pj = p.getY();
        if (pi > 0) if (move(p, pi, pj, pi - 1, pj)) return;
        if (pi < NCOL - 1) if (move(p, pi, pj, pi + 1, pj)) return;
        if (pj > 0) if (move(p, pi, pj, pi, pj - 1)) return;
        if (pj < NROW - 1) if (move(p, pi, pj, pi, pj + 1)) return;
    }

    private boolean move(Pedina p, int oldi, int oldj, int i, int j) {
        if (tab[i][j] != null) return false;
        tab[i][j] = p;
        p.setX(i);
        p.setY(j);
        tab[oldi][oldj] = null;
        return true;
    }

    public  List<Chunk> prepareImage(Bitmap bit) {
        List<Chunk> lista = new ArrayList<Chunk>();
        int chunkNumbers = NROW * NCOL;

         chunkHeight = bit.getHeight() / NROW;
         chunkWidth = bit.getWidth() / NCOL;
    /*chunkHeight = 300/rows;
    chunkWidth = 300/cols;*/
int pos=0;
        //xCoord and yCoord are the pixel positions of the image chunks
        int yCoord = 0;
        for (int x = 0; x < NROW; x++) {
            int xCoord = 0;
            for (int y = 0; y < NCOL; y++) {
                Bitmap b = Bitmap.createBitmap(bit, xCoord, yCoord, chunkWidth, chunkHeight);
                Chunk c = new Chunk(b, xCoord, yCoord, chunkWidth, chunkHeight);
                c.setPosCorretta(pos);
                c.setPosAttuale(pos++);
              //  lista.add(Bitmap.createBitmap(bit, xCoord, yCoord, chunkWidth, chunkHeight));
                lista.add(c);
                xCoord += chunkWidth;
            }
            yCoord += chunkHeight;
        }
        return lista;
    }
    public int getChunkHeight() {
        return chunkHeight;
    }

    public int getChunkWidth() {
        return chunkWidth;
    }
}
