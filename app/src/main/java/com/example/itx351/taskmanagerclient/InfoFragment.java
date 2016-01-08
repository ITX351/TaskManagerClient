package com.example.itx351.taskmanagerclient;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.os.Handler;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {
//    private OnFragmentInteractionListener mListener;

    Overall overall;
    RelativeLayout ll;
    private TextView lblCPUContent, lblMemoryContent, lblDiskContent,
            lblNetworkContent, lblDomainNameContent; //, lblKeywordContent;

    private void setInfo(SysInfo sysInfo) {

    }

    public final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SysInfo sysInfo = overall.sysInfo;

            if (sysInfo == null) {
                Log.e("sysInfo", "sysInfo NULL in InfoFragment!! ");
                return;
            }
            String strCPU, strMemory, strDisk, strNetwork;
            strCPU = Double.toString(sysInfo.cpuSys);
            strMemory = Double.toString(sysInfo.memUsed) + " / " + Double.toString(sysInfo.memTotal);
            strDisk = "To show disk";
            strNetwork = "To show network";

            lblCPUContent.setText(strCPU);
            lblMemoryContent.setText(strMemory);
            lblDiskContent.setText(strDisk);
            lblNetworkContent.setText(strNetwork);
        }
    };

    private final Thread thread = new Thread(new Runnable(){
        @Override
        public void run() {
            while (true) {
                try {
                    handler.sendMessage(new Message());
                    Thread.sleep(R.integer.AutoUpdateSleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    });

    public InfoFragment() {
        // Required empty public constructor
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
        ll = (RelativeLayout)inflater.inflate(R.layout.fragment_info, container, false);

        lblCPUContent = (TextView)ll.findViewById(R.id.inf_lblCPUContent);
        lblMemoryContent = (TextView)ll.findViewById(R.id.inf_lblMemoryContent);
        lblDiskContent = (TextView)ll.findViewById(R.id.inf_lblDiskContent);
        lblNetworkContent = (TextView)ll.findViewById(R.id.inf_lblNetworkContent);
        lblDomainNameContent = (TextView)ll.findViewById(R.id.inf_lblDomainNameContent);
//        lblKeywordContent = (TextView)ll.findViewById(R.id.inf_lblKeywordContent);

        lblDomainNameContent.setText(overall.DomainName);
//        lblKeywordContent.setText(overall.Keyword);

        thread.start();
        return ll;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
