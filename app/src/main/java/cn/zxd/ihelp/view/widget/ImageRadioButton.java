package cn.zxd.ihelp.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zxd.ihelp.R;

/**
 * Created by danielzhang on 16/7/25.
 */

public class ImageRadioButton extends RelativeLayout {

    @BindView(R.id.iv_image_radio_button_image)
    ImageView iv_image_radio_button_image;

    @BindView(R.id.tv_image_radio_button_text)
    TextView tv_image_radio_button_text;

    public ImageRadioButton(Context context) {
        super(context);
        initViews(context, null, 0);
    }

    public ImageRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs, 0);
    }

    public ImageRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs, defStyleAttr);
    }

    protected void initViews(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.widget_image_radio_button, this, true);
        ButterKnife.bind(this);
        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageRadioButton);
            CharSequence text = typedArray.getText(R.styleable.ImageRadioButton_imageRadioButtonText);
            if (null != text) tv_image_radio_button_text.setText(text);
            Drawable drawable = typedArray.getDrawable(R.styleable.ImageRadioButton_imageRadioButtonImage);
            if (null != drawable) iv_image_radio_button_image.setImageDrawable(drawable);
            boolean checked = typedArray.getBoolean(R.styleable.ImageRadioButton_checked, false);
            setChecked(checked);
            typedArray.recycle();
        }
    }

    public void setChecked(boolean checked) {
        if (checked) {
            iv_image_radio_button_image.setSelected(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tv_image_radio_button_text.setTextColor(getResources().getColor(R.color.colorAccent, null));
            } else {
                tv_image_radio_button_text.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        } else {
            iv_image_radio_button_image.setSelected(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tv_image_radio_button_text.setTextColor(getResources().getColor(R.color.colorDisable, null));
            } else {
                tv_image_radio_button_text.setTextColor(getResources().getColor(R.color.colorDisable));
            }
        }
    }

}
