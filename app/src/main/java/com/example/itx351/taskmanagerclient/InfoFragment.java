package com.example.itx351.taskmanagerclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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
            lblNetworkContent, lblDomainNameContent, lblKeywordContent;

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
        lblKeywordContent = (TextView)ll.findViewById(R.id.inf_lblKeywordContent);

        lblDomainNameContent.setText(overall.DomainName);
        lblKeywordContent.setText(overall.Keyword);

        return ll;
    }

    public class modifier implements Runnable {
        String CPU, Memory, Disk, Network;
        public modifier(String _CPU, String _Memory, String _Disk, String _Network) {
            CPU = _CPU; Memory = _Memory; Disk = _Disk; Network = _Network;
        }

        @Override
        public void run() {
            lblCPUContent.setText(CPU);
            lblMemoryContent.setText(Memory);
            lblDiskContent.setText(Disk);
            lblNetworkContent.setText(Network);
        }
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
