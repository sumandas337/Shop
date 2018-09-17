package com.example.sumandas.shop.binding;

import android.support.annotation.LayoutRes;

public interface IViewProvider {
    @LayoutRes
    int getView(IViewModel vm);
}
