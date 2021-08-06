package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class FollowView extends View {

    public float bitmapX;
    public float bitmapY;

    public FollowView(Context context) {
        super(context);
        bitmapX=300;
        bitmapY=200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.xiaozhi);

        canvas.drawBitmap(bitmap,bitmapX,bitmapY,paint);

        if(bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
