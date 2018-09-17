package com.example.sumandas.shop.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BindingRecyclerAdapter extends RecyclerView.Adapter<BindingRecyclerAdapter.DataBindingViewHolder>{

    private final @NonNull
    ViewModelBinder mBinder;

    private List<IViewModel> mViewModels;

    private IViewProvider mViewProvider;

    public BindingRecyclerAdapter(@NonNull List<IViewModel> viewModels,
                                  @NonNull IViewProvider viewProvider,
                                  @NonNull ViewModelBinder viewModelBinder) {
        mBinder = viewModelBinder;
        mViewModels = viewModels;
        mViewProvider = viewProvider;
    }

    public BindingRecyclerAdapter(@NonNull IViewProvider viewProvider,
                                  @NonNull ViewModelBinder viewModelBinder) {
        mBinder = viewModelBinder;
        mViewModels = new ArrayList<>();
        mViewProvider = viewProvider;
    }

    @Override
    public int getItemViewType(int position) {
        return mViewProvider.getView(mViewModels.get(position));
    }

    public void setmViewModels(List<IViewModel> viewModels) {
        mViewModels = viewModels;
        notifyDataSetChanged();
    }

    public void addViewModel(IViewModel model){
        mViewModels.add(model);
        notifyDataSetChanged();
    }

    public void removeViewModel(IViewModel model){
        mViewModels.remove(model);
        notifyDataSetChanged();
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                viewType, parent, false);
        return new DataBindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position) {
        mBinder.bind(holder.viewBinding, mViewModels.get(position));
        holder.viewBinding.executePendingBindings();
    }

    @Override
    public void onViewRecycled(DataBindingViewHolder holder) {
        mBinder.bind(holder.viewBinding, null);
        holder.viewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mViewModels.size();
    }


    public static class DataBindingViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        final ViewDataBinding viewBinding;

        public DataBindingViewHolder(@NonNull ViewDataBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }
    }
}



