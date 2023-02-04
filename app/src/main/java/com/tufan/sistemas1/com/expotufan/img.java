package com.tufan.sistemas1.com.expotufan;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImageView;

public class img extends AppCompatActivity {
SmartImageView smartImageView;
TextView nombreT;
TextView medidaT;

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        smartImageView= findViewById(R.id.imgver);
        nombreT= findViewById(R.id.txtnombreT);
        medidaT= findViewById(R.id.txtmedidaT);

        String urlfinal=appRutaservidor.IPIMG+ listaConta.imgver1;
        Rect rect=new Rect(smartImageView.getLeft(),smartImageView.getTop(),smartImageView.getRight(),smartImageView.getBottom());
        nombreT.setText(listaConta.nombresT);
        medidaT.setText(listaConta.medidasT);
        smartImageView.setImageUrl(urlfinal,rect);

        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());


    }
    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            smartImageView.setScaleX(mScaleFactor);
            smartImageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}
