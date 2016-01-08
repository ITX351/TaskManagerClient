package com.example.itx351.taskmanagerclient;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.itx351.taskmanagerclient.TasksFragment.OnListFragmentInteractionListener;
import com.example.itx351.taskmanagerclient.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTasksRecyclerViewAdapter extends RecyclerView.Adapter<MyTasksRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTasksRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tasks, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mImageNameView.setText(mValues.get(position).ImageName);
        holder.mPIdView.setText(mValues.get(position).PID);
//        holder.mSessionNameView.setText(mValues.get(position).SessionName);
//        holder.mSessionView.setText(mValues.get(position).Session);
//        holder.mMemUsageView.setText(mValues.get(position).MemUsage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mImageNameView;
        public final TextView mPIdView;
//        public final TextView mSessionNameView;
//        public final TextView mSessionView;
//        public final TextView mMemUsageView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageNameView = (TextView) view.findViewById(R.id.ImageName);
            mPIdView = (TextView) view.findViewById(R.id.PID);
//            mSessionNameView = (TextView) view.findViewById(R.id.SessionName);
//            mSessionView = (TextView) view.findViewById(R.id.Session);
//            mMemUsageView = (TextView) view.findViewById(R.id.MemUsage);
        }
    }
}
