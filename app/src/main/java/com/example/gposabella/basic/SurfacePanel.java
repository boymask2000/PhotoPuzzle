package com.example.gposabella.basic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gposabella on 30/05/2016.
 */
public class SurfacePanel extends SurfaceView implements SurfaceHolder.Callback {
    private final Context context;
    private final Bitmap mbitmap;
    private final int imgWidth;
    private final int imgHeight;
    private final Bitmap resizedPhotoBitMap;
    private final List<Chunk> lista;
    private final int chunkHeight;
    private final int chunkWidth;
    private Bitmap photoBitMap;
    private MyThread mythread;
    private int screenWidth;
    private int screenHeight;

    private Board board;

    /**
     * parameterizedBitmap constructor for surface panel class
     **/

    public SurfacePanel(Context ctx, AttributeSet attrSet) {
        super(ctx, attrSet);
        context = ctx;
        board = new Board(context);

        getDims();
        init();
//the bintitmap Bitmapwe wish to draw
        photoBitMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.gatto);

        mbitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.skull);
        imgWidth = mbitmap.getWidth();
        imgHeight = mbitmap.getHeight();
        SurfaceHolder holder = getHolder();
        resizedPhotoBitMap = getResizedBitmap(photoBitMap);
        holder.addCallback(this);
        lista = randomize(board.prepareImage(resizedPhotoBitMap));
        chunkHeight = board.getChunkHeight();
        chunkWidth = board.getChunkWidth();
    }

    private List<Chunk> randomize(List<Chunk> ll) {

        Random rand = new Random(100);
        List<Chunk> out = new ArrayList<Chunk>();
        List<Integer> indexes = new ArrayList<Integer>();
        //List<Integer> values = new ArrayList<Integer>();
        for (int i = 0; i < ll.size(); i++) {
            indexes.add(i);
        }
        int count = 0;
        while (!indexes.isEmpty()) {
            int ii = (int) (Math.random() * indexes.size());

            int index = indexes.get(ii);
            System.out.println("ii= " + index);
            indexes.remove(ii);
            out.add(ll.get(index));
            count++;
            if (count % 2 == 0)
                scambia(out.get(count - 1), out.get(count - 2), false);
        }
        return out;
    }

    private void init() {

    }


    void doDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(255, 153, 51));
        paint.setStrokeWidth(10);
        //  Bitmap     q=getResizedBitmap(photoBitMap);
        //     canvas.drawBitmap(resizedPhotoBitMap, 0, 0, null);
        for (Chunk c : lista) {

            canvas.drawBitmap(c.getBitmap(), c.getX(), c.getY(), null);
            canvas.drawLine(c.getX(), c.getY(), c.getX() + c.getWidth(), c.getY(), paint);
            canvas.drawLine(c.getX(), c.getY(), c.getX(), c.getY() + c.getHeight(), paint);
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) screenWidth) / width;
        float scaleHeight = ((float) screenHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    private void getDims() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Display display = wm.getDefaultDisplay();
        Point size = new Point();

        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mythread.setRunning(false);
        boolean retry = true;
        while (retry) {
            try {
                mythread.join();

                retry = false;
            } catch (Exception e) {

                Log.v("Exception Occured", e.getMessage());
            }
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    private Chunk first = null;

    private void scambia(Chunk c1, Chunk c2, boolean slow) {

      /*  if (slow) {

            int d = c1.getX()<c2.getX()?1:-1;
            for (int i = c1.getX(); i < c2.getX(); i=i+d) {
                c1.setX(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
        int xf = c1.getX();
        int yf = c1.getY();
        int posf = c1.getPosAttuale();
        c1.setX(c2.getX());
        c1.setY(c2.getY());
        c1.setPosAttuale(c2.getPosAttuale());
        c2.setX(xf);
        c2.setY(yf);
        c2.setPosAttuale(posf);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Chunk p = getChunk(x, y);
                if (first == null) first = p;
                else {
//                    int xf=first.getX();
//                    int yf=first.getY();
//                    first.setX(p.getX());
//                    first.setY(p.getY());
//                    p.setX(xf);
//                    p.setY(yf);
                    scambia(first, p, true);
                    checkFine();
                    first = null;
                }

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:


                break;
            default:
                break;
        }


        return true;
    }

    private void checkFine() {
        for (Chunk c : lista) {
            if (c.getPosAttuale() != c.getPosCorretta()) return;
        }
        System.out.println("PPPPPPPPPPPP");
    }

    public Chunk getChunk(float x, float y) {
        for (Chunk c : lista) {
            if (c.isInside(x, y))
                return c;
        }
        return null;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mythread = new MyThread(holder, context, this);

        mythread.setRunning(true);

        mythread.start();
    }

}