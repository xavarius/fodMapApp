package com.maciejmalak.whatcanieat.foodList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maciejmalak.whatcanieat.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.ButterKnife;

public class FoodAdapter extends ArrayAdapter {
    @BindDrawable(R.drawable.circle_allowed)
    Drawable mAllowedIndicator;
    @BindDrawable(R.drawable.circle_disallowed)
    Drawable mDisallowedIndicator;
    @BindDrawable(R.drawable.circle_restricted)
    Drawable mRestrictedIndicator;

    public FoodAdapter(final Context context, final ArrayList<FoodPojo> products) {
        super(context, R.layout.product_row, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = getItemView(parent.getContext(), parent, convertView);
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        final FoodPojo foodItem = (FoodPojo) getItem(position);

        viewHolder.mTextView.setText(foodItem.getName());

        if(foodItem.isAllowed() == 1) {
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
}
