package sokoban.game.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import sokoban.game.GetMazeInfoCallback;
import sokoban.game.BlockTypes;

public class MazeView extends View {
    private Paint outlinePaint;
    private Paint fillPaint;
    private int across;
    private int down;
    private int blockLength;
    private int blockOutline;
    private int minMargin;
    private int mazeTop;
    private int mazeLeft;
    private int manAlong;
    private int manDown;
    private GetMazeInfoCallback callback;

    public MazeView(Context context) {
        super(context);
        this.across = 5;
        this.down = 5;
        this.minMargin = 10;
        this.blockOutline = 3;
        this.init();
    }



    public MazeView(Context context, GetMazeInfoCallback callback) {
        super(context);

        this.minMargin = 50;
        this.blockOutline = 10;
        this.register(callback);
        this.setAcrossAndDown(callback);
        this.init();
    }

    private  void init(){
        outlinePaint = new Paint();
        outlinePaint.setColor(Color.BLACK);
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeWidth(this.blockOutline);

        fillPaint = new Paint();
        fillPaint.setColor(Color.BLACK);
        fillPaint.setStyle(Paint.Style.FILL);
    }

    //won't need this to be public  when I have it going with the maze
    public void setManPos(int along, int down) {
        this.manAlong = along;
        this.manDown = down;
        invalidate();
    }

    private void setAcrossAndDown(GetMazeInfoCallback callback) {
        this.across = callback.getMazeWidth();
        this.down = callback.getMazeHeight();
    }

    public void register(GetMazeInfoCallback callback){
        this.callback = callback;
    }

    @Override
    public void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        this.setBlockLength(width, height);
        this.setMazeBounds(width, height);
        drawMaze(canvas);
    }

/*
    // This example shows an Activity, but you would use the same approach if
// you were subclassing a View.
    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getActionMasked();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text, duration);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                CharSequence downText = "Action was DOWN";
                toast.setText(downText);
                toast.show();
                return true;
            case (MotionEvent.ACTION_MOVE) :
                CharSequence moveText = "Action was MOVE";
                toast.setText(moveText);
                return true;
            case (MotionEvent.ACTION_UP) :
                CharSequence upText = "Action was UP";
                toast.setText(upText);
                toast.show();
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                // Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                // Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
                //        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
*/
    //TODO check stroke width - if the stroke is on the exact edge,itmight onle be hald in the block length itself
    private void setBlockLength(int width, int height){
        int sideLength;
        sideLength = height - (2*this. minMargin);
        if (height > width){
            sideLength = width - (2*this.minMargin);
        }
        this.blockLength = (sideLength /this.down) - (2 * this.blockOutline);
        if (this.across > this.down){
            this.blockLength = (sideLength /this.across)- (2 * this.blockOutline);
        }
    }

    private void setMazeBounds(int width,int height){
        int mazeWidth = this.blockLength * this.across;
        this.mazeLeft = (width - mazeWidth)/2;
        int mazeHeight = this.blockLength * this.down;
        this.mazeTop = (height - mazeHeight)/2;
    }

    protected void drawMaze(Canvas canvas){
        int left;
        int top = this.mazeTop;
        // draw a column
        for (int downNum = 0; downNum < this.down; downNum ++) {
            left = this.mazeLeft;
            //draw a row -
            for (int acrossNum = 0; acrossNum < this.across; acrossNum++) {
                BlockTypes theBlock = this.callback.getBlockType(acrossNum, downNum);
                this.drawBlock(canvas, left, top, theBlock);
                left = left + this.blockLength;
            }
            top = top + this.blockLength;
        }
    }

    private void drawBlock(Canvas canvas, int left, int top, BlockTypes theBlock){
        switch(theBlock){
            case FlOOR:
                this.drawBlockBackground(canvas, left, top, Color.LTGRAY);
                this.drawFloor(canvas, left, top);
                break;
            case TARGET:
                this.drawBlockBackground(canvas, left, top,  Color.LTGRAY);
                this.drawTarget(canvas, left, top);
                break;
            case WALL:
                this.drawBlockBackground(canvas, left, top, Color.TRANSPARENT);
                this.drawWall(canvas, left, top);
                break;
            case FLOORMAN:
                this.drawBlockBackground(canvas, left, top, Color.LTGRAY);
                this.drawFloor(canvas, left, top);
                this.drawMan(canvas, left, top, Color.YELLOW);
                break;
            case TARGETMAN:
                this.drawBlockBackground(canvas, left, top, Color.LTGRAY);
                this.drawTarget(canvas, left, top);
                this.drawMan(canvas, left, top, Color.GREEN);
                break;
            case FLOORBOX:
                this.drawBlockBackground(canvas, left, top, Color.LTGRAY);
                this.drawFloor(canvas, left, top);
                this.drawBox(canvas, left, top, Color.BLUE);
                break;
            case TARGETBOX:
                this.drawBlockBackground(canvas, left, top, Color.LTGRAY);
                this.drawTarget(canvas, left, top);
                this.drawBox(canvas, left, top, Color.MAGENTA);
                break;
            case EMPTY:
                break;
        }
    }

    private void drawBlockBackground(Canvas canvas, int left, int top, int aColor){
        int right = left + this.blockLength;
        int bottom = top + this.blockLength;
        fillPaint.setColor(aColor);
        canvas.drawRect(left, top, right, bottom, fillPaint);
        canvas.drawRect(left, top, right, bottom, outlinePaint);
    }

    private void drawTarget(Canvas canvas, int left, int top){
        int margin = 20;
        int xLeft = left + margin;
        int xTop = top + margin;
        int xRight = left + this.blockLength - margin;
        int xBottom = top + this.blockLength- margin;
        Path path = new Path();
        path.moveTo(xLeft, xTop);
        path.lineTo(xRight, xBottom);
        path.moveTo(xRight, xTop);
        path.lineTo(xLeft, xBottom);
        path.moveTo(xLeft, xTop);
        path.close();
        canvas.drawPath(path, outlinePaint);
    }

    private void drawFloor(Canvas canvas, int left, int top){
        fillPaint.setColor(Color.GRAY);
        //TODO calculate margin as percentage
        int margin = 20;
        float radius = (this.blockLength/2) - margin;
        float circleX = left + margin + radius;
        float circleY = top + margin + radius;
        canvas.drawCircle(circleX, circleY, radius, fillPaint);
    }

    //draw triangle
    private void drawWall(Canvas canvas, int left, int top){
        fillPaint.setColor(Color.RED);

        //TODO - calculate margin as percentage
        int margin = 10;

        float firstVertexX = left + (this.blockLength/2);
        float firstVertexY = top + margin;

        float secondVertexX = left + margin;
        float secondVertexY = top + this.blockLength - margin;

        float thirdVertexX = left + this.blockLength - margin;
        float thirdVertexY = top + this.blockLength - margin;

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(firstVertexX, firstVertexY);
        path.lineTo(secondVertexX, secondVertexY);
        path.lineTo(thirdVertexX, thirdVertexY);
        path.lineTo(firstVertexX, firstVertexY);
        path.close();
        canvas.drawPath(path, fillPaint);
        canvas.drawPath(path, outlinePaint);

    }

    private void drawMan(Canvas canvas, int left, int top, int aColor){
        fillPaint.setColor(aColor);
        //TODO - calculate margin as percentage
        int margin = 10;
        float radius = (this.blockLength/2) - margin;
        float circleX = left + margin + radius;
        float circleY = top + margin + radius;
        canvas.drawCircle(circleX, circleY, radius, fillPaint);
    }

    private void drawBox(Canvas canvas, int left, int top, int aColor){
        fillPaint.setColor(aColor);
        //TODO - calculate margin as percentage
        int margin = 10;
        int rectTop = top + margin;
        int rectLeft = left + margin;
        int rectBottom = top + this.blockLength - margin;
        int rectRight = left + this.blockLength - margin;
        canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, fillPaint);
    }

}
