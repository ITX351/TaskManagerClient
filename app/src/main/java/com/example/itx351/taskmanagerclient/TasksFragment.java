package com.example.itx351.taskmanagerclient;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.itx351.taskmanagerclient.dummy.DummyContent;
import com.example.itx351.taskmanagerclient.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {
    Overall overall;
    RecyclerView recyclerView;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private final Handler handler = new Handler();

    private final Runnable task = new Runnable(){
        @Override
        public void run() {
            SysInfo sysInfo = overall.sysInfo;

            if (sysInfo == null) {
                Log.e("sysInfo", "sysInfo NULL in TasksFragment!");
            }
            else {
                List<DummyItem> items = new ArrayList<>();
                items.clear();

                //通过第二行的等号来获取分割点
                int times = -1, l0 = 0, l1 = 0, l2 = 0, l3 = 0, l4 = 0;

                for (String str : sysInfo.procList) {
                    times++;

                    if (times <= 1) continue; //第一行为空 第二行为表头
                    if (times == 2) { //一行等号 对齐下方的各类名字
                        String[] tmpEquals = str.split(" ");
                        l0 = tmpEquals[0].length(); //每一串等号的结束点
                        l1 = tmpEquals[1].length() + l0 + 1;
                        l2 = tmpEquals[2].length() + l1 + 1;
                        l3 = tmpEquals[3].length() + l2 + 1;
                        l4 = tmpEquals[4].length() + l3 + 1;
                        continue;
                    }

                    String strImageName, strCPU, strSessionName, strSession, strMemUsage;
                    strImageName = str.substring(0, l0).trim(); //截取字符串
                    strCPU = str.substring(l0, l1).trim();
                    strSessionName = str.substring(l1, l2).trim();
                    strSession = str.substring(l2, l3).trim();
                    strMemUsage = str.substring(l3, l4).trim();

                    DummyContent.DummyItem nowItem = new DummyContent.DummyItem(strImageName, strCPU,
                            strSessionName, strSession, strMemUsage);
                    items.add(nowItem); //添加元素
                }
                recyclerView.setAdapter(new MyTasksRecyclerViewAdapter(items, mListener)); //更新视图

                Toast.makeText(getActivity(), "List updated.", Toast.LENGTH_SHORT).show();
            }
            handler.postDelayed(this, Overall.TasksFragmentAutoUpdateSleepTime);
        }
    };

    public TasksFragment() {
    }

    public static TasksFragment newInstance(int columnCount) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount); //每行显示数目
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overall = (Overall)getActivity().getApplication();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        handler.post(task);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        handler.removeCallbacks(task);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
    }
}
