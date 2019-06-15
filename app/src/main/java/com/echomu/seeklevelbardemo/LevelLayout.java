package com.echomu.seeklevelbardemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author : echoMu
 * date   : 2019/6/5
 * desc   :
 */
public class LevelLayout extends FrameLayout {

    private SeekBarView mSeekBarView;
    private RecyclerView rv;
    private Context mContext;
    private List<LevelBean> levelBeanList = new ArrayList<>();
    private int leftMargin, rightMargin;

    public LevelLayout(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public LevelLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LevelLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.lauout_seeklevel, this, true);
        mSeekBarView = findViewById(R.id.rsb_view);
        rv = findViewById(R.id.rv_level);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LevelLayout);
            leftMargin = ta.getDimensionPixelSize(R.styleable.LevelLayout_left_margin, SeekBarView.dip2px(context, 24));
            rightMargin = ta.getDimensionPixelSize(R.styleable.LevelLayout_right_margin, SeekBarView.dip2px(context, 24));
            ta.recycle();
        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(manager);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rv.getLayoutParams();
        params.leftMargin = leftMargin;
        params.rightMargin = rightMargin;
    }

    public void setData(@NonNull List<LevelBean> levelBeanList, OnRangeSeekBarListener mListener) {
        this.levelBeanList = levelBeanList;
        mSeekBarView.addOnRangeSeekBarListener(mListener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int minW = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int width = resolveSizeAndState(minW, widthMeasureSpec, 1);
        int LevelLayoutWidth = width - leftMargin - rightMargin;
        int levelWidth = LevelLayoutWidth / levelBeanList.size();

        mSeekBarView.setData(levelBeanList, width, levelWidth,leftMargin,rightMargin);
        SeekLevelAdapter adapter = new SeekLevelAdapter(mContext, levelBeanList, levelWidth);
        rv.setAdapter(adapter);
    }

}