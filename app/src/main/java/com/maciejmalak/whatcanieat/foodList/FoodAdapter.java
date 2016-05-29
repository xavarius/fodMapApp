package com.maciejmalak.whatcanieat.foodList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.maciejmalak.whatcanieat.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.ButterKnife;

public class FoodAdapter extends BaseAdapter implements Filterable {

    @BindDrawable(R.drawable.circle_allowed)
    Drawable mAllowedIndicator;
    @BindDrawable(R.drawable.circle_disallowed)
    Drawable mDisallowedIndicator;
    @BindDrawable(R.drawable.circle_restricted)
    Drawable mRestrictedIndicator;

    private ArrayList<FoodPojo> mList;
    private FoodPojoFilter foodPojoFilter;

    public FoodAdapter() {
        mList = DataSetHelper.returnData();
    }

    @Override
    public Filter getFilter() {
        if (foodPojoFilter == null) {
            foodPojoFilter = new FoodPojoFilter();
        }
        return foodPojoFilter;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = getItemView(parent.getContext(), parent, convertView);
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        final FoodPojo foodItem = (FoodPojo) getItem(position);

        viewHolder.mTextView.setText(foodItem.getName());

        if (foodItem.isAllowed() == 1) {
            viewHolder.mImageView.setBackground(mAllowedIndicator);
        } else if (foodItem.isAllowed() == -1) {
            viewHolder.mImageView.setBackground(mDisallowedIndicator);
        } else {
            viewHolder.mImageView.setBackground(mRestrictedIndicator);
        }

        return convertView;
    }

    private View getItemView(final Context context, final ViewGroup parent, View convertView) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false);
            final ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

            /*Bind only once. */
            if(mAllowedIndicator == null || mDisallowedIndicator == null
                    || mRestrictedIndicator == null) {
                ButterKnife.bind(this, convertView);
            }
        }
        return convertView;
    }

    static public class ViewHolder {
        @Bind(R.id.product_text) TextView mTextView;
        @Bind(R.id.product_status) ImageView mImageView;

        ViewHolder(final View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }

    private class FoodPojoFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults results = new FilterResults();

            mList = DataSetHelper.returnData();

            if (constraint == null || constraint.length() == 0) {
                results.values = mList;
                results.count = getCount();
            } else {
                final ArrayList<FoodPojo> filteredList = new ArrayList<>();

                for (FoodPojo f : mList) {
                    final String name = f.getName().toLowerCase();
                    if(name.contains(constraint.toString().toLowerCase())) {
                        filteredList.add(f);
                    }
                }

                results.values = filteredList;
                results.count = 0;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList = (ArrayList<FoodPojo>) results.values;
            notifyDataSetChanged();
        }
    }
}