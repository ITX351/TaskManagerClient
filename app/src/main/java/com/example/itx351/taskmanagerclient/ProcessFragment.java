package com.example.itx351.taskmanagerclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProcessFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProcessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProcessFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String OPERATION_TYPE = "OperationType"; // 0 for run, 1 for kill
    private int operationType;
    private ClientGeneralThread clientGeneralThread;
    public Overall overall;

//    private OnFragmentInteractionListener mListener;

    public ProcessFragment() {
        // Required empty public constructor
    }

    public static ProcessFragment newInstance(int _operationType) {
        ProcessFragment fragment = new ProcessFragment();
        Bundle args = new Bundle();
        args.putInt(OPERATION_TYPE, _operationType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            operationType = getArguments().getInt(OPERATION_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout ll = (RelativeLayout)inflater.inflate(R.layout.fragment_process, container, false);

        TextView lblOperation = (TextView)ll.findViewById(R.id.pro_lblOperation);
        final EditText txtProcessName = (EditText)ll.findViewById(R.id.pro_txtProcessName);
        final String processName = txtProcessName.getText().toString();

        if (operationType == 0) {
            lblOperation.setText(R.string.pro_RunProcess);
        }
        else if (operationType == 1) {
            lblOperation.setText(R.string.pro_KillProcess);
        }

        Button btnOK = (Button)ll.findViewById(R.id.pro_btnOK);
        btnOK.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                // Use String: processName defined above
                if (operationType == 0) {
                    clientGeneralThread = new ClientGeneralThread(
                            DataHead.getDataHead("runProcessCommandHead"), overall.commandOutputStream, overall, processName);
                    clientGeneralThread.start();
                }
                else if (operationType == 1) {
                    clientGeneralThread = new ClientGeneralThread(
                            DataHead.getDataHead("killProcessCommandHead"), overall.commandOutputStream, overall, processName);
                    clientGeneralThread.start();
                }
            }
        });

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
