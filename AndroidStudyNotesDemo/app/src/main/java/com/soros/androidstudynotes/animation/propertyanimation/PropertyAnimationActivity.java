package com.soros.androidstudynotes.animation.propertyanimation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.soros.androidstudynotes.R;

import java.util.ArrayList;

public class PropertyAnimationActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<Fragment> fragments;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.d("PropertyAnimation", "==============onNavigationItemSelected==============");
            switch (item.getItemId()) {
                case R.id.property_value_animator:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.property_object_animator:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.property_animator_set:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.d("PropertyAnimation", "==============onPageSelected==============");
            switch (position) {
                case 0:
                    navigation.setSelectedItemId(R.id.property_value_animator);
                    break;
                case 1:
                    navigation.setSelectedItemId(R.id.property_object_animator);
                    break;
                case 2:
                    navigation.setSelectedItemId(R.id.property_animator_set);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        initFragments();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new ValueAnimatorFragment());
        fragments.add(new ObjectAniatorFragment());
        fragments.add(new AnimatorSetFragment());
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {


        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }
}
