package com.lltest.viewflipperstudy.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.lltest.viewflipperstudy.R;

/**
 * Fragment contains the view flipper
 *
 */
public class ViewFlipperFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = ViewFlipperFragment.class.getSimpleName();

    public interface OnViewFlipFragmentInteractionListener {
        // TODO: Update argument type and name
        void onViewFlipInteraction(Uri uri);
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View mView;

    private Button mBtnNext, mBtnPrev, mBtnDone;

    private ViewFlipper mViewFlipper;
    private Animation mAnim_in_left2right;
    private Animation mAnim_out_left2right;
    private Animation mAnim_in_right2left;
    private Animation mAnim_out_right2left;
    private int mFlipPosition = 0;      // init position at 0

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewFlipperFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewFlipperFragment newInstance(String param1, String param2) {
        ViewFlipperFragment fragment = new ViewFlipperFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnViewFlipFragmentInteractionListener mListener;

    public ViewFlipperFragment() {
        // Required empty public constructor
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onViewFlipInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnViewFlipFragmentInteractionListener) {
            mListener = (OnViewFlipFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnViewFlipFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_view_flipper, container, false);
        initView();
        return mView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initView() {
        mBtnNext = mView.findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);
        mBtnPrev = mView.findViewById(R.id.btn_prev);
        mBtnPrev.setOnClickListener(this);
        mBtnDone = mView.findViewById(R.id.btn_done);
        mBtnDone.setOnClickListener(this);

        mFlipPosition = 0;  // reset to init position
        mViewFlipper = mView.findViewById(R.id.simpleViewFlipper);
        setAnimation();

        // TODO: enable later
        //updateUiBasedOnPosition();
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btn_next:
                Log.d(TAG, "go to next");
                goToNext();
                break;
            case R.id.btn_prev:
                Log.d(TAG, "go to prev");
                goToPrev();
                break;
            case R.id.btn_done:
                Log.d(TAG, "done");
                // todo
                break;
            default:
                // do nothing
        }
    }

    private void goToNext() {
        Log.d(TAG, "position: " + mFlipPosition);
        // TODO: cache the data entered on current position

        mViewFlipper.setInAnimation(mAnim_in_right2left);
        mViewFlipper.setOutAnimation(mAnim_out_right2left);
        mViewFlipper.showNext();

        mFlipPosition++;
        //updateUiBasedOnPosition();
    }

    private void goToPrev() {
        Log.d(TAG, "position: " + mFlipPosition);

        mViewFlipper.setInAnimation(mAnim_in_left2right);
        mViewFlipper.setOutAnimation(mAnim_out_left2right);
        mViewFlipper.showPrevious();

        mFlipPosition--;
        //updateUiBasedOnPosition();
    }

    /**
     * Update UI based on the position of the view flipper.
     *
     */
    private void updateUiBasedOnPosition() {
        switch (mFlipPosition) {
            case 0:
                disableBtn(mBtnPrev);
                enableBtn(mBtnNext);
                disableBtn(mBtnDone);
                break;
            case 1:
                enableBtn(mBtnPrev);
                enableBtn(mBtnNext);
                disableBtn(mBtnDone);
                break;
            case 2:
                enableBtn(mBtnPrev);
                disableBtn(mBtnNext);
                enableBtn(mBtnDone);
                break;
            default:
                break;
        }
    }

    private void setAnimation() {
        mAnim_in_left2right = AnimationUtils.loadAnimation(getActivity(),
                R.anim.view_flipper_left2right_in);
        mAnim_out_left2right = AnimationUtils.loadAnimation(getActivity(),
                R.anim.view_flipper_left2right_out);
        mAnim_in_right2left = AnimationUtils.loadAnimation(getActivity(),
                R.anim.view_flipper_right2left_in);
        mAnim_out_right2left = AnimationUtils.loadAnimation(getActivity(),
                R.anim.view_flipper_right2left_out);
    }

    private static void disableBtn(Button btn) {
        if (btn == null) {
            return;
        }
        btn.setAlpha(0.5f);
        btn.setFocusable(false);
        btn.setClickable(false);
    }

    private static void enableBtn(Button btn) {
        if (btn == null) {
            return;
        }
        btn.setAlpha(1.0f);
        btn.setFocusable(true);
        btn.setClickable(true);
    }
}
