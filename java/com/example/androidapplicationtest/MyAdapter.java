package com.example.androidapplicationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private ViewHolder mViewHolder;
    private LayoutInflater mLayoutInflater;
    private List<String> mList;

    public MyAdapter(Context context, List<String> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mList = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.activity_messageboard_layout, null);
            mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.text_view);
            mViewHolder.mEditText = (EditText) convertView.findViewById(R.id.edit_text);
            mViewHolder.mImageView = (ImageView)convertView.findViewById(R.id.image_view) ;
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        if (position <= 9) {
            mViewHolder.mTextView.setText("leave your message"+" 0" + (position));
        } else {
            mViewHolder.mTextView.setText("leave your message"+" " + (position));
        }
        int []imageTest={
                R.drawable.test1,
                R.drawable.test2,
                R.drawable.test3,
                R.drawable.test4,
                R.drawable.test5,
                R.drawable.test6,
                R.drawable.test7,
                R.drawable.test8,
                R.drawable.test9,
                R.drawable.test10,
                R.drawable.test11,
                R.drawable.test12,
                R.drawable.test13,
        };
        mViewHolder.mImageView.setImageResource(imageTest[position%13]);
        return convertView;
    }

    static final class ViewHolder {
        TextView mTextView;
        EditText mEditText;
        ImageView mImageView;
    }
}