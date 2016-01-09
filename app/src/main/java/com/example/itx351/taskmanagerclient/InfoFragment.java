package com.example.itx351.taskmanagerclient;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InfoFragment extends Fragment {
    Overall overall;
    private TextView lblCPUSysContent, lblCPUUserContent, lblCPUCombinedContent, lblMemoryContent;

    private final Handler handler = new Handler();

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            SysInfo sysInfo = overall.sysInfo;

            if (sysInfo == null) {
                Log.e("sysInfo", "sysInfo NULL in InfoFragment!");
            }
            else {
                String strCPUSys, strCPUUser, strCPUCombined, strMemory;
                strCPUSys = Double.toString(sysInfo.cpuSys);
                strCPUUser = Double.toString(sysInfo.cpuUser);
                strCPUCombined = Double.toString(sysInfo.cpuCombined);
                strMemory = Double.toString(sysInfo.memUsed) + " / " + Double.toString(sysInfo.memTotal);

                lblCPUSysContent.setText(strCPUSys); //更新界面信息
                lblCPUUserContent.setText(strCPUUser);
                lblCPUCombinedContent.setText(strCPUCombined);
                lblMemoryContent.setText(strMemory);
            }
            handler.postDelayed(this, Overall.InfoFragmentAutoUpdateSleepTime); //持续更新
        }
    };

    public InfoFragment() {
    }

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overall = (Overall)getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout ll = (RelativeLayout)inflater.inflate(R.layout.fragment_info, container, false);

        lblCPUSysContent = (TextView)ll.findViewById(R.id.inf_lblCPUSysContent); //存储控件
        lblCPUUserContent = (TextView)ll.findViewById(R.id.inf_lblCPUUserContent);
        lblCPUCombinedContent = (TextView)ll.findViewById(R.id.inf_lblCPUCombinedContent);
        lblMemoryContent = (TextView)ll.findViewById(R.id.inf_lblMemoryContent);
        ((TextView)ll.findViewById(R.id.inf_lblDomainNameContent)).setText(overall.DomainName); //显示域名

        return ll;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        handler.post(task);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        handler.removeCallbacks(task);
    }
}
