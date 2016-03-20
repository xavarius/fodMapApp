package list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maciejmalak.whatcanieat.R;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter {

    public FoodAdapter(final Context context, final ArrayList<FoodPojo> products) {
        super(context, R.layout.product_row, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = getItemView(parent.getContext(), parent, convertView);
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        final FoodPojo item = (FoodPojo) getItem(position);

        viewHolder.mTextView.setText(item.getName());

        return convertView;
    }

    private View getItemView(final Context context, final ViewGroup parent, View convertView) {
        if(convertView == null) {
            final ViewHolder viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.product_text);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.product_status);
            convertView.setTag(viewHolder);
        }
        return convertView;
    }

    static public class ViewHolder {
        TextView mTextView;
        ImageView mImageView;
    }
}
