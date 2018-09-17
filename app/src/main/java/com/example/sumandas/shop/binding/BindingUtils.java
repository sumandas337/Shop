package com.example.sumandas.shop.binding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;

import java.util.List;

@SuppressWarnings("unused")
public class BindingUtils {

    @Nullable
    private static ViewModelBinder defaultBinder = null;


    @Nullable
    public static ViewModelBinder getDefaultBinder() {
        if (defaultBinder == null) {
            defaultBinder = (viewDataBinding, viewModel) -> viewDataBinding.setVariable(BR.vm, viewModel);
        }
        return defaultBinder;
    }


    public static void setDefaultBinder(@NonNull ViewModelBinder viewModelBinder) {
        defaultBinder = viewModelBinder;
    }

    @BindingAdapter("adapter")
    public static void bindAdapter(@NonNull RecyclerView recyclerView, @Nullable RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingConversion
    @NonNull
    public static IViewProvider getViewProviderForLayout(@LayoutRes final int layoutId) {
        return vm -> layoutId;
    }

    @BindingAdapter(value = {"items", "view_provider","numColumns","isHorizontal"}, requireAll = false)
    public static void bindAdapterWithDefaultBinder(@NonNull RecyclerView recyclerView, @Nullable List<IViewModel> items,
                                                    IViewProvider viewProvider, int numColumns, boolean isHorizontal) {
        BindingRecyclerAdapter adapter;
        if (items != null && viewProvider != null) {
            if (numColumns > 0) {
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), numColumns));
            } else {
                if (isHorizontal) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
                            LinearLayoutManager.HORIZONTAL, false));
                } else {
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                }
            }

            adapter = new BindingRecyclerAdapter(items, viewProvider, defaultBinder);
            bindAdapter(recyclerView, adapter);
        }
    }


    @BindingAdapter({"items", "view_provider"})
    public static void bindVMWithLayout(@NonNull LinearLayout linearLayout, @Nullable List<IViewModel> items, IViewProvider viewProvider) {
        LayoutInflater layoutInflater = LayoutInflater.from(linearLayout.getContext());
        linearLayout.removeAllViews();
        if(items!=null){
            for (IViewModel iViewModel : items) {
                int resId = viewProvider.getView(iViewModel);
                View view = layoutInflater.inflate(resId, linearLayout, false);
                ViewDataBinding binding = DataBindingUtil.bind(view);
                defaultBinder.bind(binding, iViewModel);
                binding.executePendingBindings();
                linearLayout.addView(view);
            }
        }


    }

    @BindingAdapter("textChangedListener")
    public static void bindTextWatcher(EditText editText, TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }



    @BindingAdapter(value = {"addVectorDrawableLeft", "addVectorDrawableTop", "addVectorDrawableRight", "addVectorDrawableBottom"}, requireAll = false)
    public static void addCompoundDrawable(TextView textView, int drawableLeft, int drawableTop, int drawableRight, int drawableBottom) {
        Drawable drawableL = null;
        Drawable drawableT = null;
        Drawable drawableR = null;
        Drawable drawableB = null;
        if (drawableLeft > 0) {
            drawableL = AppCompatResources.getDrawable(textView.getContext(), drawableLeft);
        }
        if (drawableTop > 0) {
            drawableT = AppCompatResources.getDrawable(textView.getContext(), drawableTop);
        }
        if (drawableRight > 0) {
            drawableR = AppCompatResources.getDrawable(textView.getContext(), drawableRight);
        }
        if (drawableBottom > 0) {
            drawableB = AppCompatResources.getDrawable(textView.getContext(), drawableBottom);
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(drawableL, drawableT, drawableR, drawableB);
    }


    @BindingAdapter("setError")
    public static void setError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
    }

    @BindingAdapter("setHint")
    public static void setHint(TextInputLayout textInputLayout, String hint) {
        textInputLayout.setHint(hint);
    }




    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView view, String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(view.getContext()).load(imgUrl).into(view);
        }
    }

}