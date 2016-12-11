package com.soros.androidstudynotes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.soros.androidstudynotes.activity.LifeCycleActivity;
import com.soros.androidstudynotes.service.ServiceStudyActivity;
import com.soros.androidstudynotes.utils.ShortcutUtil;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BaseViewHolder.OnRecyclerItemClick {

    private final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<MenuEntity> mItems;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addShortcut();

        initDatas();
        initRecyclerView();
    }

    private void addShortcut() {

        if(ShortcutUtil.hasShortcut(MainActivity.this, getString(R.string.app_name))) {
            Log.d(TAG, "===========hasInstallShortcut===========");
            ShortcutUtil.deleteShortCut(MainActivity.this, R.string.app_name);
        } else {
            Log.d(TAG, "===========hasNotInstallShortcut===========");
            sendBroadcast(ShortcutUtil.getShortcutToDesktopIntent(MainActivity.this, R.string.app_name, R.drawable.common_google_signin_btn_icon_light_disabled));
        }
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new MenuAdapter(getApplicationContext(), this));
    }

    private void initDatas() {
        mItems = new ArrayList<>();
        mItems.add(new MenuEntity(R.string.activity, LifeCycleActivity.class));
        mItems.add(new MenuEntity(R.string.service, ServiceStudyActivity.class));
        mItems.add(new MenuEntity(R.string.activity, LifeCycleActivity.class));
        mItems.add(new MenuEntity(R.string.activity, LifeCycleActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent();
        intent.setClass(this, mItems.get(position).cls);
        startActivity(intent);
    }

    public static class MenuEntity {
        @StringRes
        int menuStrId;
        Class cls;

        public MenuEntity(int menuStrId, Class<?> cls) {
            this.menuStrId = menuStrId;
            this.cls = cls;
        }
    }

    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

        LayoutInflater layoutInflater;
        Context mContext;
        BaseViewHolder.OnRecyclerItemClick onRecyclerItemClick;

        public MenuAdapter(Context context, BaseViewHolder.OnRecyclerItemClick itemClick) {
            layoutInflater = LayoutInflater.from(context);
            onRecyclerItemClick = itemClick;
        }

        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MenuViewHolder(mContext, layoutInflater, R.layout.menu_item, parent, onRecyclerItemClick);
        }

        @Override
        public void onBindViewHolder(MenuViewHolder holder, int position) {
            holder.bindData(mItems.get(position), position);
        }

        @Override
        public int getItemCount() {
            return mItems == null ? 0 : mItems.size();
        }

        public class MenuViewHolder extends BaseViewHolder<MenuEntity> {

            TextView mTextView;

            public MenuViewHolder(@NonNull Context context, LayoutInflater layoutInflater, @LayoutRes int resId, ViewGroup viewGroup, OnRecyclerItemClick itemClick) {
                super(context, layoutInflater, resId, viewGroup, itemClick);
                mTextView = (TextView) itemView.findViewById(R.id.textView);
            }

            @Override
            public void bindData(MenuEntity menuItem, int position) {
                mTextView.setText(menuItem.menuStrId);
            }
        }
    }
}
