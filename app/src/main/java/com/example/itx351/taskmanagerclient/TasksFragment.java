package com.example.itx351.taskmanagerclient;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itx351.taskmanagerclient.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TasksFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SysInfo sysInfo = (SysInfo)msg.obj;

            Log.d("sysInfo", "Message received in TasksFragment");

            DummyContent.clear();

            //通过第二行的等号来获取分割点
            int times = 0, l0 = 0, l1 = 0, l2 = 0, l3 = 0, l4 = 0;

            //Log.d("strInfo", "Length of procList: " + Integer.toString(sysInfo.procList.size()));

            for (String str : sysInfo.procList) {
                //Log.d("strInfo", Integer.toString(times) + " " + str);

                if (times <= 1) { times++; continue; } //第一行为空 第二行为表头
                if (times == 2) { //一行等号 对齐下方的各类名字
                    String[] tmpEquals = str.split(" ");
                    l0 = tmpEquals[0].length(); //每一串等号的结束点
                    l1 = tmpEquals[1].length() + l0 + 1;
                    l2 = tmpEquals[2].length() + l1 + 1;
                    l3 = tmpEquals[3].length() + l2 + 1;
                    l4 = tmpEquals[4].length() + l3 + 1;
                    times++;
                    continue;
                }

                String strImageName, strCPU, strSessionName, strSession, strMemUsage;
                strImageName = str.substring(0, l0).trim();
                strCPU = str.substring(l0, l1).trim();
                strSessionName = str.substring(l1, l2).trim();
                strSession = str.substring(l2, l3).trim();
                strMemUsage = str.substring(l3, l4).trim();

                DummyContent.DummyItem nowItem = new DummyContent.DummyItem(strImageName, strCPU,
                        strSessionName, strSession, strMemUsage);
                DummyContent.addItem(nowItem);

                times++;
            }
        }
    };

    public TasksFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TasksFragment newInstance(int columnCount) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyTasksRecyclerViewAdapter(DummyContent.ITEMS, mListener));
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyContent.DummyItem item);
    }
}
