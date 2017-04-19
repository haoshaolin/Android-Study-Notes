package com.soros.androidstudynotes.animation.propertyanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.soros.androidstudynotes.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ValueAnimatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ValueAnimatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ValueAnimatorFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG = "ValueAnimator";
    private ImageView imageView;
    private View imageContainer;
    private ImageView pointImage;

    private OnFragmentInteractionListener mListener;

    public ValueAnimatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ValueAnimator.
     */
    // TODO: Rename and change types and number of parameters
    public static ValueAnimatorFragment newInstance(String param1, String param2) {
        ValueAnimatorFragment fragment = new ValueAnimatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_value_animator, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = (ImageView) view.findViewById(R.id.image1);
        pointImage = (ImageView) view.findViewById(R.id.pointImage);
        imageContainer = view.findViewById(R.id.container);

        view.findViewById(R.id.startAlpha).setOnClickListener(this);
        view.findViewById(R.id.startTranslate).setOnClickListener(this);
        view.findViewById(R.id.startRotate).setOnClickListener(this);
        view.findViewById(R.id.startScale).setOnClickListener(this);
        view.findViewById(R.id.startBezierCurve).setOnClickListener(this);

    }

    public void translate() {

        Log.d(TAG, String.format("======0:imageView:left=%s,right=%s,top=%s,bottom=%s,x=%s,y=%s,translateX=%s,translateY=%s",imageView.getLeft(),imageView.getRight(),imageView.getTop(),imageView.getBottom(),imageView.getX(),imageView.getY(),imageView.getTranslationX(),imageView.getTranslationY()));

        ValueAnimator valueAnimator = ValueAnimator.ofInt(imageView.getLeft(), imageContainer.getWidth() - imageView.getWidth());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatorValue = (int) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                marginLayoutParams.leftMargin = animatorValue;
                imageView.setLayoutParams(marginLayoutParams);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                translate2();
                Log.d(TAG, String.format("=====1:imageView:left=%s,right=%s,top=%s,bottom=%s,x=%s,y=%s,translateX=%s,translateY=%s",imageView.getLeft(),imageView.getRight(),imageView.getTop(),imageView.getBottom(),imageView.getX(),imageView.getY(),imageView.getTranslationX(),imageView.getTranslationY()));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setTarget(imageView);
        valueAnimator.start();
    }

    public void translate2() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(imageView.getTop(), imageContainer.getHeight() - imageView.getHeight());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatorValue = (int) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                marginLayoutParams.topMargin = animatorValue;
                imageView.setLayoutParams(marginLayoutParams);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                translate3();
                Log.d(TAG, String.format("=====2:imageView:left=%s,right=%s,top=%s,bottom=%s,x=%s,y=%s,translateX=%s,translateY=%s",imageView.getLeft(),imageView.getRight(),imageView.getTop(),imageView.getBottom(),imageView.getX(),imageView.getY(),imageView.getTranslationX(),imageView.getTranslationY()));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.setInterpolator(new OvershootInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setTarget(imageView);
        valueAnimator.start();
    }

    public void translate3() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(imageView.getLeft(), 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatorValue = (int) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                marginLayoutParams.leftMargin = animatorValue;
                imageView.setLayoutParams(marginLayoutParams);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                translate4();
                Log.d(TAG, String.format("=====3:imageView:left=%s,right=%s,top=%s,bottom=%s,x=%s,y=%s,translateX=%s,translateY=%s",imageView.getLeft(),imageView.getRight(),imageView.getTop(),imageView.getBottom(),imageView.getX(),imageView.getY(),imageView.getTranslationX(),imageView.getTranslationY()));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setTarget(imageView);
        valueAnimator.start();
    }

    public void translate4() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(imageView.getTop(), 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatorValue = (int) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                marginLayoutParams.topMargin = animatorValue;
                imageView.setLayoutParams(marginLayoutParams);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, String.format("=====4:imageView:left=%s,right=%s,top=%s,bottom=%s,x=%s,y=%s,translateX=%s,translateY=%s",imageView.getLeft(),imageView.getRight(),imageView.getTop(),imageView.getBottom(),imageView.getX(),imageView.getY(),imageView.getTranslationX(),imageView.getTranslationY()));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.setInterpolator(new AnticipateInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setTarget(imageView);
        valueAnimator.start();
    }

    public void scale() {
        PropertyValuesHolder valuesHolderX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f);
        PropertyValuesHolder valuesHolderY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f);
        ValueAnimator valueAnimator = ValueAnimator.ofPropertyValuesHolder(valuesHolderX, valuesHolderY);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageView.setScaleX((Float) animation.getAnimatedValue("scaleX"));
                imageView.setScaleY((Float) animation.getAnimatedValue("scaleY"));
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setTarget(imageView);
        valueAnimator.setInterpolator(new ScaleInterpolator());
        valueAnimator.start();
    }

    public static class ScaleInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float input) {
//            input = (float) Math.sin(input);
            input *= 0.8f;
            return input * input;
        }
    }

    public void alpha() {
        ValueAnimator valueAnimator = ValueAnimator.ofArgb(0x00ddddee, 0xff00ff00);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatorValue = (int) animation.getAnimatedValue();
                imageView.setBackgroundColor(animatorValue);
            }
        });

        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setTarget(imageView);
        valueAnimator.start();
    }

    public void rotate() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(imageView.getRotation(), imageView.getRotation() + 360.0f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatorValue = (float) animation.getAnimatedValue();
                imageView.setRotation(animatorValue);
            }
        });

        valueAnimator.setDuration(4000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setTarget(imageView);
        valueAnimator.start();
    }

    class RPoint {
        float mX;
        float mY;
    }

    float x = 0f;
    float y = 0f;
    private void translateBezierCurve(final View view){
        x = 0f;
        y = 0f;
        final RPoint p0 = new RPoint();//原点坐标就是view的所在
        p0.mX = view.getX();
        p0.mY = view.getY();

        float r = (imageContainer.getWidth() - pointImage.getWidth())/2;
        final RPoint p1 = new RPoint();//点p1
        p1.mX = view.getX() + r;
        p1.mY = view.getY() - r;

        final RPoint p2 = new RPoint();//点p2
        p2.mX = view.getX() + 2*r;
        p2.mY = view.getY();

        Log.i("tag","anim:" + view.getX() + ",,," + view.getY());

        ValueAnimator goInAnim = new ValueAnimator();
        goInAnim.setFloatValues(0f,1f);
        goInAnim.setDuration(1000);
        goInAnim.setRepeatMode(ValueAnimator.REVERSE);
        goInAnim.setRepeatCount(1);
        goInAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float t = (float)valueAnimator.getAnimatedValue();
                float oneMinusT = 1.0f - t;

                x = oneMinusT * oneMinusT * (p0.mX)
                        + 2 * t * oneMinusT * (p1.mX)
                        + t * t  * (p2.mX);

                y = oneMinusT * oneMinusT * (p0.mY)
                        + 2 * t * oneMinusT * (p1.mY)
                        + t * t * (p2.mY);
                view.setX(x);
                view.setY(y);
            }
        });
        goInAnim.setTarget(view);
        goInAnim.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startBezierCurve:
                translateBezierCurve(pointImage);
                break;
            case R.id.startTranslate:
                translate();
                break;
            case R.id.startAlpha:
                alpha();
                break;
            case R.id.startRotate:
                rotate();
                break;
            case R.id.startScale:
                scale();
                break;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
