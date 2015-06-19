package sokoban.LevelSelector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.mysokonabapplication.R;

/**
 * From Android API guides /GridView
 * http://developer.android.com/guide/topics/ui/layout/gridview.html
 * Edited to link to string[], changed ImageView to text view
 */
public class CustomGrid extends BaseAdapter {
    private Context mContext;
    private final String[] allKeys;

    public CustomGrid(Context c, String[] allKeys) {
        mContext = c;
        this.allKeys = allKeys;
    }

    @Override
    public int getCount() {
        return this.allKeys.length;
    }

    @Override
    public Object getItem(int position) {
        return this.allKeys[position];
    }

    @Override
    public long getItemId(int position) {
        // Auto-generated method stub
        return 0;
    }

  /*  @Override
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            String key = this.getItem(position).toString();
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(80, 85));
            textView.setPadding(8, 8, 8, 8);
            textView.setText(key);
        } else {
            textView = (TextView) convertView;
        }

        textView.setBackgroundColor(Color.RED);
        return textView;
    }
    */
    //inflating an exsisting viewdefinedin xml
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

           //grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, parent,false);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            textView.setText(this.getItem(position).toString());
        } else {
            grid =  convertView;
        }

        return grid;
    }

}
