package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    //


    private CakeModel cakeModel = new CakeModel();

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint textPaint = new Paint();

    Paint balloonPaint = new Paint();
    Paint balloonStringPaint = new Paint();



    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 60.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;

    public static final float balloonWidth = 70.0f;

    public static final float balloonHeight = 80.0f;

    // method to retrieve the CakeModel object in CakeView
    public CakeModel getCakeModel(){
        return cakeModel;
    }


    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {

        super(context, attrs);
        cakeModel = new CakeModel();

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        //cakePaint.setColor(0xFFC755B5);  //violet-red
        cakePaint.setColor(0xFF4985FF); // NEW BLUE COLOR
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.RED);
        balloonPaint.setColor(Color.BLUE);
        balloonStringPaint.setColor(Color.BLACK);

        setBackgroundColor(Color.WHITE);  //better than black default

    }

    public void drawBalloon(Canvas canvas, float x, float y){
        RectF r = new RectF(x-balloonWidth/2 ,y-balloonHeight/2, x+balloonWidth, y+balloonHeight);
        canvas.drawRect(x, y+balloonHeight/2, x+10.0f, y+300.0f, balloonStringPaint);
        canvas.drawOval(r, balloonPaint);

    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);
        if(cakeModel.hasCandles) {
            //draw the outer flame
            float flameCenterX = left + candleWidth / 2;
            float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
            if (cakeModel.isLit) {
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);
            }

            //draw the inner flame
            flameCenterY += outerFlameRadius / 3;
            if (cakeModel.isLit) {
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }

            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
        }
    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);




        //Draw two candles equidistant from each other
        drawCandle(canvas, cakeLeft + cakeWidth/3 - candleWidth/2, cakeTop);
        drawCandle(canvas, cakeLeft + 2*cakeWidth/3 - candleWidth/2, cakeTop);

        textPaint.setTextSize(100);
        canvas.drawText("Coordinates" + cakeModel.x + " ," + cakeModel.y + " ", 100,1100,textPaint );




        //
        if(cakeModel.balloon_X!=0 && cakeModel.balloon_Y!=0) {
            drawBalloon(canvas, cakeModel.balloon_X, cakeModel.balloon_Y);
        }



    }//onDraw

}//class CakeView

