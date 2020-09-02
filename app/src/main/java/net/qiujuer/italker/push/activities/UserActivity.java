package net.qiujuer.italker.push.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

import net.qiujuer.genius.ui.compat.UiCompat;
import net.qiujuer.italker.common.app.Activity;
import net.qiujuer.italker.common.app.Fragment;
import net.qiujuer.italker.push.R;
import net.qiujuer.italker.push.frags.user.UpdateInfoFragment;

import butterknife.BindView;

/**
 * 用户信息界面
 * 可以提供用户信息修改
 */
public class UserActivity extends Activity {
    private Fragment mCurFragment;
    @BindView(R.id.im_bg)
    ImageView mBg;

    /**
     * 显示界面的入口方法
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, UserActivity.class));
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_user;
    }


    @Override
    protected void initWidget() {
        super.initWidget();

        mCurFragment = new UpdateInfoFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, mCurFragment)
                .commit();

        // 初始化背景
        Glide.with(this)
                .load(R.drawable.bg_src_tianjin)
                .centerCrop() //居中剪切
                .into(new DrawableImageViewTarget(mBg) {
                    @Override
                    protected void setResource(@Nullable Drawable resource) {
                        if (resource == null) {
                            super.setResource(resource);
                            return;
                        }
                        // 使用适配类进行包装
                        Drawable drawable = DrawableCompat.wrap(resource);
                        drawable.setColorFilter(UiCompat.getColor(getResources(), R.color.colorAccent),
                                PorterDuff.Mode.SCREEN); // 设置着色的效果和颜色，蒙板模式
                        // 设置给ImageView
                        super.setResource(drawable);
                    }
                });
    }

    // Activity中收到剪切图片成功的回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCurFragment.onActivityResult(requestCode, resultCode, data);
    }
}
