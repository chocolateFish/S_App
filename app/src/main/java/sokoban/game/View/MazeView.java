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
    private Paint paint;
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
        //this.across = 5;
        //this.down = 5;
        this.minMargin = 50;
        this.blockOutline = 10;
        this.paint = new Paint();
    }


    public MazeView(Context context, GetMazeInfoCallback callback) {
        super(context);
        //this.across = 5;
        //this.down = 5;
        this.minMargin = 50;
        this.blockOutline = 10;
        this.paint = new Paint();
        this.register(callback);
        this.setAcrossAndDown(callback);

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

    private void setBlockLength(int width, int height){
        int sideLength;
        sideLength = height - (2*this. minMargin);
        if (height > width){
            sideLength = width - (2*this.minMargin);
        }
        //this.blockLength = sideLength/12;
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
        int right;
        int top = this.mazeTop;
        int bottom;
        // draw a column
        for (int downNum = 0; downNum < this.down; downNum ++) {
            left = this.mazeLeft;
            bottom = top + this.blockLength;
            //draw a row -
            for (int acrossNum = 0; acrossNum < this.across; acrossNum++) {
                right = left + this.blockLength;
                /*
                if (acrossNum == this.across-1 && downNum == this.down-1) {
                    this.drawTile(canvas, left, top, right, bottom, FixedTypes.EMPTY);
                }else if (acrossNum == 0 || acrossNum == (this.across-1)||downNum ==0|| (downNum == this.down-1)){
                    this.drawTile(canvas, left, top, right, bottom, FixedTypes.WALL);

                }else if(acrossNum %2 == 1  && downNum % 3 == 0 ){
                    this.drawTile(canvas, left, top, right, bottom, FixedTypes.TARGET);
                }else  {
                    this.drawTile(canvas, left, top, right, bottom, FixedTypes.FlOOR);
                }
                */
                //this.drawTileBackground(canvas, left, top, right, bottom, Color.TRANSPARENT);
                //if (acrossNum == this.manAlong && downNum == this.manDown) {
                //    this.drawMan(canvas, left, top, right);
                //}
                BlockTypes theBlock = this.callback.getBlockType(acrossNum, downNum);
                this.drawBlock(canvas, left, top, right, bottom, theBlock);
                left = right;
            }
            top = bottom;
        }
    }

    private void drawBlock(Canvas canvas, int left, int top, int right, int bottom, BlockTypes theBlock){
        switch(theBlock){
            case FlOOR:
                this.drawBlockBackground(canvas, left, top, right, bottom, Color.LTGRAY);
                this.drawFloor(canvas, left, top, right);
                break;
            case TARGET:
                this.drawBlockBackground(canvas, left, top, right, bottom, Color.LTGRAY);
                this.drawTarget(canvas, left, top, right, bottom);
                break;
            case WALL:
                this.drawBlockBackground(canvas, left, top, right, bottom, Color.TRANSPARENT);
                this.drawWall(canvas, left, top, right, bottom);
                break;
            case FLOORMAN:
                this.drawBlockBackground(canvas, left, top, right, bottom, Color.LTGRAY);
                this.drawFloor(canvas, left, top, right);
                this.drawMan(canvas, left, top, right);
                break;
            case TARGETMAN:
                this.drawBlockBackground(canvas, left, top, right, bottom, Color.LTGRAY);
                this.drawTarget(canvas, left, top, right, bottom);
                this.drawMan(canvas, left, top, right);
            case FLOORBOX:
                this.drawBlockBackground(canvas, left, top, right, bottom, Color.LTGRAY);
                this.drawFloor(canvas, left, top, right);
                this.drawBox(canvas, left, top, right);
                break;
            case TARGETBOX:
                this.drawBlockBackground(canvas, left, top, right, bottom, Color.LTGRAY);
                this.drawTarget(canvas, left, top, right, bottom);
                this.drawBox(canvas, left, top, right);
                break;
            case EMPTY:
                break;
        }
    }

    private void drawBlockBackground(Canvas canvas, int left, int top, int right, int bottom, int aColor){
        paint.setColor(aColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(left, top, right, bottom, paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.blockOutline);
        canvas.drawRect(left, top, right, bottom, paint);
    }

    private void drawTarget(Canvas canvas, int left, int top, int right, int bottom){
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.blockOutline);
        paint.setColor(Color.BLUE);
        Path path = new Path();
        path.moveTo(left, top);
        path.lineTo(right, bottom);
        path.moveTo(right, top);
        path.lineTo(left, bottom);
        path.moveTo(left, top);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawFloor(Canvas canvas, int left, int top, int right){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        int margin = 50;
        float radius = ((right - left)/2) - margin;
        float circlex = left + margin + radius;
        float circleY = top + margin + radius;
        canvas.drawCircle(circlex, circleY, radius, paint);
    }

    //draw triangle
    private void drawWall(Canvas canvas, int left, int top, int right, int bottom){
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(this.blockOutline);
        paint.setColor(Color.RED);
        int margin = 50;

        float firstVertexX = left + ((right - left)/2);
        float firstVertexY = top + margin;

        float secondVertexX = left + margin;
        float secondVertexY = bottom - margin;

        float thirdVertexX = right - margin;
        float thirdVertexY = bottom - margin;

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(firstVertexX, firstVertexY);
        path.lineTo(secondVertexX, secondVertexY);
        path.lineTo(thirdVertexX, thirdVertexY);
        path.lineTo(firstVertexX, firstVertexY);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);

    }

    private void drawMan(Canvas canvas, int left, int top, int right){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        int margin = 10;
        float radius = ((right - left)/2) - margin;
        float circlex = left + margin + radius;
        float circleY = top + margin + radius;
        canvas.drawCircle(circlex, circleY, radius, paint);
    }

    private void drawBox(Canvas canvas, int left, int top, int right){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        int margin = 10;
        float radius = ((right - left)/2) - margin;
        float circlex = left + margin + radius;
        float circleY = top + margin + radius;
        canvas.drawCircle(circlex, circleY, radius, paint);
        /*
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(aColor);
        int margin = 10;
        int rectTop = top + margin;
        int rectLeft = left + margin;
        int rectRight = rectTop + this.blockLength - margin;
        int rectBottom = rectLeft - this.blockLength - margin;
        canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, paint);
        */
    }

}
