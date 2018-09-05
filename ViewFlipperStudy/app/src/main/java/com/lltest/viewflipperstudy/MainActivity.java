package com.lltest.viewflipperstudy;

import android.app.Fragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lltest.viewflipperstudy.ui.ViewFlipperFragment;

import static com.lltest.viewflipperstudy.common.Constants.FRAGMENT_VIEW_FLIP_TAG;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        ViewFlipperFragment.OnViewFlipFragmentInteractionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mBtnShowViewFlipperPage = null;



    @Override
    public void onViewFlipInteraction(Uri uri) {
        // TODO: interaction with fragment
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        mBtnShowViewFlipperPage = findViewById(R.id.btn_show_view_flipper_page);
        mBtnShowViewFlipperPage.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btn_show_view_flipper_page:
                Log.d(TAG, "try to load login fragment");
                loadFragmentViewFlip();
                break;
            // TODO: other buttons
            default:
                // do nothing
        }
    }

    private void loadFragmentViewFlip() {
        Log.d(TAG, "loadFragmentViewFlip()");
        Fragment fragment = getFragmentManager().
                findFragmentByTag(FRAGMENT_VIEW_FLIP_TAG);

        if (fragment == null) {
            fragment = ViewFlipperFragment.newInstance("arg1", "arg2");
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, fragment, FRAGMENT_VIEW_FLIP_TAG)
                    .addToBackStack(FRAGMENT_VIEW_FLIP_TAG)
                    .commitAllowingStateLoss();
        }
    }
}
