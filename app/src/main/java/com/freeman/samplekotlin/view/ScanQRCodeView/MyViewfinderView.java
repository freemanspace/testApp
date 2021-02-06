package com.freeman.samplekotlin.view.ScanQRCodeView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.freeman.samplekotlin.R;
import com.journeyapps.barcodescanner.Size;
import com.journeyapps.barcodescanner.ViewfinderView;


public final class MyViewfinderView extends ViewfinderView {

    public MyViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        refreshSizes();
        if (framingRect == null || previewSize == null) {
            return;
        }

        final Rect frame = framingRect;
        final Size previewSize = this.previewSize;

        final int width = canvas.getWidth();
        final int height = canvas.getHeight();

        // Draw the exterior (i.e. outside the framing rect) darkened
        paint.setColor(resultBitmap != null ? resultColor : maskColor);
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);

        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            paint.setAlpha(CURRENT_POINT_OPACITY);
            canvas.drawBitmap(resultBitmap, null, frame, paint);
        } else {
            // // 周圍灰色區
            paint.setColor(resultBitmap != null ? resultColor : maskColor);
            canvas.drawRect(0, 0, width, frame.top, paint);
            canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
            canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
            canvas.drawRect(0, frame.bottom + 1, width, height, paint);

            // 四個直角
            paint.setColor(getResources().getColor(R.color.blue));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(20);
            //左上
            canvas.drawLine(frame.left, frame.top - 10, frame.left + 100, frame.top - 10, paint);
            canvas.drawLine(frame.left - 10, frame.top - 20, frame.left - 10, frame.top + 100, paint);
            //右上
            canvas.drawLine(frame.right, frame.top - 10, frame.right - 100, frame.top - 10, paint);
            canvas.drawLine(frame.right + 10, frame.top - 20, frame.right + 10, frame.top + 100, paint);
            //左下
            canvas.drawLine(frame.left, frame.bottom + 10, frame.left + 100, frame.bottom + 10, paint);
            canvas.drawLine(frame.left - 10, frame.bottom + 20, frame.left - 10, frame.bottom - 100, paint);
            //右下
            canvas.drawLine(frame.right, frame.bottom + 10, frame.right - 100, frame.bottom + 10, paint);
            canvas.drawLine(frame.right + 10, frame.bottom + 20, frame.right + 10, frame.bottom - 100, paint);
        }
    }
}
