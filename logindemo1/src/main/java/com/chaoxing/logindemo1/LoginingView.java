package com.chaoxing.logindemo1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class LoginingView extends View {

    public float bitmapX;
    public float bitmapY;

    public LoginingView(Context context) {
        super(context);
        bitmapX=200;
        bitmapY=400;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.logining);

        canvas.drawBitmap(bitmap,bitmapX,bitmapY,paint);

        if(bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
