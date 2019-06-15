package com.echomu.seeklevelbardemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.List;

public class SeekBarView extends FrameLayout {

    private OnRangeSeekBarListener mListener;
    private Context mContext;
    private TextView tvLevel;
    private View lineT;
    private List<LevelBean> levelBeanList;
    private int seekBarWidth, levelWidth;
    private int firstLeft, lastX, level;
    private boolean init;
    private int leftMargin, rightMargin;

    public SeekBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeekBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_indicator, this, true);
        tvLevel = findViewById(R.id.tv_level);
        lineT = findViewById(R.id.lineT);
    }

    public void setData(List<LevelBean> levelBeanList, int seekBarWidth, int levelWidth, int leftMargin, int rightMargin) {
        this.levelBeanList = levelBeanList;
        this.seekBarWidth = seekBarWidth;
        this.levelWidth = levelWidth;
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
        firstLeft = getLeft() + leftMargin;

        init = true;
        level = 2;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (init) {
            LevelBean levelBean = levelBeanList.get(level - 1);
            tvLevel.setText(levelBean.getName() + levelBean.getValue());
            lineT.setBackgroundColor(levelBean.getColor());
            layout(firstLeft + level * levelWidth - levelWidth / 2 - getWidth() / 2, getTop(), firstLeft + level * levelWidth - levelWidth / 2 + getWidth() / 2, getBottom());
            init = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();//获取触摸事件触摸位置的原始X坐标
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int l = getLeft() + dx;
                int r = getRight() + dx;

                //下面判断移动是否超出屏幕
                if (l < 0) {
                    l = 0;
                    r = l + getWidth();
                }
                if (r > seekBarWidth) {
                    r = seekBarWidth;
                    l = r - getWidth();
                }

                //重新布局
                layout(l, getTop(), r, getBottom());
                invalidate();
                lastX = (int) event.getRawX();

                //获取等级
                level = (getLeft() - firstLeft + getWidth() / 2) / levelWidth + 1;
                //刷新指示器
                LevelBean levelBean = levelBeanList.get(level - 1);
                tvLevel.setText(levelBean.getName() + levelBean.getValue());
                lineT.setBackgroundColor(levelBean.getColor());

                break;
            case MotionEvent.ACTION_UP:
                //回到该等级中间位置
                layout(firstLeft + level * levelWidth - levelWidth / 2 - getWidth() / 2, getTop(), firstLeft + level * levelWidth - levelWidth / 2 + getWidth() / 2, getBottom());
                invalidate();
                levelBean = levelBeanList.get(level - 1);
                onSeekStop(level,levelBean.getName());
                break;
        }
        return true;
    }

    public void addOnRangeSeekBarListener(OnRangeSeekBarListener listener) {
        this.mListener = listener;
    }

    private void onSeekStop(int level,String name) {
        if (mListener == null) {
            return;
        }
        mListener.onSeekStop(level,name);
    }

    /**
     * dip to px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
