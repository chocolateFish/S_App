package sokoban.filer.view;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FilerView extends RelativeLayout {
    TextView inputVw;
    TextView outputVw;
    Button submitDataBtn;
    Button retrieveDataBtn;
    int minMargin;

    FilerView(Context context) {
        super(context);

        this.inputVw = new TextView(context);
        this.outputVw = new TextView(context);
        this.submitDataBtn = new Button(context);
        this.retrieveDataBtn = new Button(context);
    }

    //Calculations based on View size
    private int calcPercentageOfValue(int percentage, int value) {
        float ans = (percentage * value) / 100;
        return Math.round(ans);
    }

    private void setMinMargin(int shorterSide){
        this.minMargin =   this.minMargin = calcPercentageOfValue(8, shorterSide);

    }

    private void drawInputVw(int width, int height){
        int inputWidth = width - 2*this.minMargin;
        int inputHeight = 70;
        int left = this.minMargin;
        int top = this.minMargin;



    }

    public void onDraw(){


    }


   @Override
   protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
       super.onMeasure( widthMeasureSpec,heightMeasureSpec);
       int width = this.getMeasuredWidth();
       int height = this.getMeasuredHeight();
       int shorterSide = height;
       if (shorterSide < width){
           shorterSide = height;
       }
       this.setMinMargin(shorterSide);

   }

}

