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
    private TextView lblCPUContent, lblMemoryContent, lblDiskContent,
            lblNetworkContent;

    private final Handler handler = new Handler();

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            SysInfo sysInfo = overall.sysInfo;

            if (sysInfo == null) {
                Log.e("sysInfo", "sysInfo NULL in InfoFragment!");
            }
            else {
                String strCPU, strMemory, strDisk, strNetwork;
                strCPU = Double.toString(sysInfo.cpuSys);
                strMemory = Double.toString(sysInfo.memUsed) + " / " + Double.toString(sysInfo.memTotal);
                strDisk = "To show disk";
                strNetwork = "To show network";

                lblCPUContent.setText(strCPU); //更新界面信息
                lblMemoryContent.setText(strMemory);
                lblDiskContent.setText(strDisk);
                lblNetworkContent.setText(strNetwork);
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

        lblCPUContent = (TextView)ll.findViewById(R.id.inf_lblCPUContent); //存储控件
        lblMemoryContent = (TextView)ll.findViewById(R.id.inf_lblMemoryContent);
        lblDiskContent = (TextView)ll.findViewById(R.id.inf_lblDiskContent);
        lblNetworkContent = (TextView)ll.findViewById(R.id.inf_lblNetworkContent);
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
