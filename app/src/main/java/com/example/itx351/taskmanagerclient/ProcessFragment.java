package com.example.itx351.taskmanagerclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProcessFragment extends Fragment {
    private static final String OPERATION_TYPE = "OperationType"; // 0 Run Process, 1 Kill Process
    private int operationType;
    private ClientGeneralThread clientGeneralThread;
    public Overall overall;

    public ProcessFragment() {
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
        overall = (Overall)getActivity().getApplication();
        if (getArguments() != null) {
            operationType = getArguments().getInt(OPERATION_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout ll = (RelativeLayout)inflater.inflate(R.layout.fragment_process, container, false);

        TextView lblOperation = (TextView)ll.findViewById(R.id.pro_lblOperation);
        TextView lblProcessNameOrId = (TextView)ll.findViewById(R.id.pro_lblProcessNameOrID);
        final EditText txtProcessName = (EditText)ll.findViewById(R.id.pro_txtProcessName);

        if (operationType == 0) {
            lblOperation.setText(R.string.pro_RunProcess);
            lblProcessNameOrId.setText(R.string.pro_ProcessName);
        }
        else if (operationType == 1) {
            lblOperation.setText(R.string.pro_KillProcess);
            lblProcessNameOrId.setText(R.string.pro_ProcessID);
        }

        Button btnOK = (Button)ll.findViewById(R.id.pro_btnOK);
        btnOK.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
            String processName = txtProcessName.getText().toString();
            System.out.println("Name " + processName);

            if (operationType == 0) { //Run 进程
                clientGeneralThread = new ClientGeneralThread(
                        DataHead.getDataHead("runProcessCommandHead"), overall.commandOutputStream, overall, processName);
                clientGeneralThread.start();
                Log.i("Tag", "running process");
            }
            else if (operationType == 1) { //Kill 进程
                clientGeneralThread = new ClientGeneralThread(
                        DataHead.getDataHead("killProcessCommandHead"), overall.commandOutputStream, overall, processName);
                clientGeneralThread.start();
                Log.i("Tag", "killing process");
            }
            }
        });
        return ll;
    }
}
