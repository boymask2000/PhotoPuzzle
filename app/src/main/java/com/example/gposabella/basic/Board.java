package com.example.gposabella.basic;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gposabella on 31/05/2016.
 */
public class Board {
    private static final int NROW = 5;
    private static final int NCOL = 5;

    private final Context context;
    private int chunkHeight;
    private int chunkWidth;

    private void init() {

    }

    public Board(Context context) {
        this.context = context;
        init();
    }


    public List<Chunk> prepareImage(Bitmap bit) {
        List<Chunk> lista = new ArrayList<Chunk>();
        int chunkNumbers = NROW * NCOL;

        chunkHeight = bit.getHeight() / NROW;
        chunkWidth = bit.getWidth() / NCOL;

        int pos = 0;
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
}
