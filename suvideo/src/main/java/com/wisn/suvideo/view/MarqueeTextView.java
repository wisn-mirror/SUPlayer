package com.wisn.suvideo.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Wisn on 2019-07-24 15:14.
 */
public class MarqueeTextView  extends AppCompatTextView {
    private boolean mNeedFocus;
    public MarqueeTextView(Context context) {
        super(context);
    }
    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //返回textview是否处在选中的状态
    //而只有选中的textview才能够实现跑马灯效果
    @Override
    public boolean isFocused() {
        if (mNeedFocus) {
            return false;
        }
        return super.isFocused();
    }

    public void setNeedFocus(boolean needFocus) {
        mNeedFocus = needFocus;
    }
}
