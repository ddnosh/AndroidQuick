package la.xiong.androidquick.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import la.xiong.androidquick.R;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class CommonToolBar extends RelativeLayout implements View.OnClickListener {
    private TextView mTvTitle;
    private TextView mTvBack;
    private ImageView mImgBack;
    private ImageView mImgLeft;
    private ImageView mImgRight;
    private TextView mTvRight;
    private ImageView mImgTitle;
    private View mLeftView, mRightView;
    private OnToolBarClickListener mOnToolbarClickListener;
    private OnBackClickListener mOnBackClickListener;
    private Context mContext;

    public CommonToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CommonToolBar);
        String titleText = typedArray.getString(R.styleable.CommonToolBar_title_text);
        Drawable leftImgDrawable = typedArray.getDrawable(R.styleable.CommonToolBar_left_img_src);
        Drawable rightImgDrawable = typedArray.getDrawable(R.styleable.CommonToolBar_right_img_src);
        String rightText = typedArray.getString(R.styleable.CommonToolBar_right_text);
        setBackgroundColor(typedArray.getColor(R.styleable.CommonToolBar_background, getContext().getResources().getColor(R.color.blue_sky)));
        typedArray.recycle();
        LayoutInflater.from(getContext()).inflate(R.layout.common_toolbar, this);
        //左边图片
        mImgLeft = (ImageView) findViewById(R.id.img_left);
        //左边返回箭头
        mImgBack = (ImageView) findViewById(R.id.img_back);
        //左边文字
        mTvBack = (TextView) findViewById(R.id.tv_back);
        //中间文字
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        //中间图片
        mImgTitle = (ImageView) findViewById(R.id.img_title);
        //右边文字
        mTvRight = (TextView) findViewById(R.id.tv_right);
        //右边图片
        mImgRight = (ImageView) findViewById(R.id.img_right);
        mLeftView = findViewById(R.id.rl_left);
        mRightView = findViewById(R.id.rl_right);
        mImgLeft.setOnClickListener(this);
        mImgRight.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
        mTvBack.setOnClickListener(this);
        mTvRight.setOnClickListener(this);

        if (!TextUtils.isEmpty(titleText)) {
            setTitle(titleText);
        } else {
            mTvTitle.setVisibility(GONE);
            mImgTitle.setVisibility(VISIBLE);
        }

        if (!TextUtils.isEmpty(rightText)) {
            mTvRight.setVisibility(VISIBLE);
            mTvRight.setText(rightText);
        } else {
            mTvRight.setVisibility(GONE);
        }

        if (leftImgDrawable != null) {
            mTvBack.setVisibility(GONE);
            mImgBack.setVisibility(GONE);
            mImgLeft.setVisibility(VISIBLE);
            mImgLeft.setImageDrawable(leftImgDrawable);
        }

        if (rightImgDrawable != null) {
            mImgRight.setImageDrawable(rightImgDrawable);
        } else {
            mImgRight.setVisibility(GONE);
        }

//        if (mImgRight.getVisibility() == GONE && mTvRight.getVisibility() == GONE) {
//            mRightView.setVisibility(GONE);
//        }else {
        mLeftView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //                boolean isFirst = true;
            @Override
            public void onGlobalLayout() {
                int width = mLeftView.getWidth();
                int width2 = mRightView.getWidth();
                if (width > width2) {
                    ViewGroup.LayoutParams param = mRightView.getLayoutParams();
                    param.width = width;
                    mRightView.setLayoutParams(param);
                } else {
                    ViewGroup.LayoutParams param = mLeftView.getLayoutParams();
                    param.width = width2;
                    mLeftView.setLayoutParams(param);
                }
            }
        });
//        }


    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
        mTvTitle.setVisibility(VISIBLE);
        mImgTitle.setVisibility(GONE);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.img_back || i == R.id.tv_back) {
            if (mOnBackClickListener != null) {
                mOnBackClickListener.onBackClick();
            } else {
                if (mContext instanceof Activity) {
                    ((Activity) mContext).finish();
                }
            }

        } else if (i == R.id.img_left) {
            if (mOnToolbarClickListener != null) {
                mOnToolbarClickListener.onLeftClick();
            }

        } else if (i == R.id.img_right) {
            if (mOnToolbarClickListener != null) {
                mOnToolbarClickListener.onRightClick();
            }

        } else if (i == R.id.tv_right) {
            if (mOnToolbarClickListener != null) {
                mOnToolbarClickListener.onRightClick();
            }

        }
    }

    public void setOnTopBarClickListener(OnToolBarClickListener onToolBarClickListener) {
        this.mOnToolbarClickListener = onToolBarClickListener;
    }

    public void setOnBackClickListener(OnBackClickListener onBackClickListener) {
        this.mOnBackClickListener = onBackClickListener;
    }

    public interface OnToolBarClickListener {
        void onLeftClick();

        void onRightClick();
    }

    public interface OnBackClickListener {
        void onBackClick();
    }


}
