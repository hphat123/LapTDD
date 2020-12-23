package com.example.test.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.test.Model.Categories;
import com.example.test.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewholder> {

    private Context mContext;

    private List<Categories> mList;

    private OnItemClick mOnItemClick;

    public void setOnItemClick(OnItemClick OnItemClick) {
        this.mOnItemClick = OnItemClick;
    }

    public CategoriesAdapter(Context mContext, List<Categories> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override  //tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu
    public CategoriesViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_categories, viewGroup, false);
        return new CategoriesViewholder(view);
    }

    @Override  //chuyển dữ liệu phần tử vào ViewHolder
    public void onBindViewHolder(@NonNull final CategoriesViewholder categoriesViewholder, final int i) {
        Categories categories = mList.get(i);
        categoriesViewholder.mBtn.setText(categories.getName() + "");
        if (categories.isSelect()) {
            categoriesViewholder.mBtn.setBackgroundResource(R.drawable.shape_button_sl);
        } else {
            categoriesViewholder.mBtn.setBackgroundResource(R.drawable.shape_button_usl);
        }
        categoriesViewholder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick != null) {
                    mOnItemClick.onClickListListener(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }     //Cho biết số phần tử dữ liệu

    class CategoriesViewholder extends RecyclerView.ViewHolder {

        private Button mBtn;

        public CategoriesViewholder(@NonNull View itemView) {
            super(itemView);
            mBtn = itemView.findViewById(R.id.btn_item_categories);
        }
    }
}
