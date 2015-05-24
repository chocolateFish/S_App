package sokoban.game.View;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by fab66 on 18/05/2015.
 */
public class SwipeDetector implements View.OnTouchListener{
    static final String logTag = "ActivitySwipeDetector";
    private SwipeInterface controller;
    static final int MIN_DISTANCE = 100;
    private float downX, downY, upX, upY;

    public SwipeDetector(SwipeInterface controller){
        this.controller = controller;
    }

    public void onRightToLeftSwipe(){
        Log.i(logTag, "RightToLeftSwipe!");
        controller.rightToLeft();
    }

    public void onLeftToRightSwipe(){
        Log.i(logTag, "LeftToRightSwipe!");
        controller.leftToRight();
    }

    public void onTopToBottomSwipe(){
        Log.i(logTag, "onTopToBottomSwipe!");
        controller.topToBottom();
    }

    public void onBottomToTopSwipe(){
        Log.i(logTag, "onBottomToTopSwipe!");
        controller.bottomToTop();
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                // swipe horizontal?
                if(Math.abs(deltaX) > MIN_DISTANCE){
                    // left or right
                    if(deltaX < 0) { this.onLeftToRightSwipe(); return true; }
                    if(deltaX > 0) { this.onRightToLeftSwipe(); return true; }
                }
                else {
                    Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE);
                }

                // swipe vertical?
                if(Math.abs(deltaY) > MIN_DISTANCE){
                    // top or down
                    if(deltaY < 0) { this.onTopToBottomSwipe(); return true; }
                    if(deltaY > 0) { this.onBottomToTopSwipe(); return true; }
                }
                else {
                    Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE);
                    v.performClick();
                }
            }
        }
        return false;
    }
}

