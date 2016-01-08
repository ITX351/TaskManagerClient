package com.example.itx351.taskmanagerclient;

//public class ScreenshotFragment extends AppCompatActivity {
//    private Overall overall;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_screenshot);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        overall = (Overall)getApplication();
//        overall.screenshot.setImageBitmap(BitmapFactory.decodeStream(overall.commandInputStream));
//    }
//
//}

import android.media.Image;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.os.Handler;


public class ScreenshotFragment extends Fragment {
//    private OnFragmentInteractionListener mListener;

    Overall overall;
    private ImageView img;
    RelativeLayout ll;

    public final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ImageView screenshot = (ImageView)msg.obj;
            if (screenshot == null) {
                Log.e("screenshot", "screenshot NULL in Screenshot Fragment!! ");
                return;
            }
        }
    };



    public ScreenshotFragment() {
        // Required empty public constructor
    }

    public static ScreenshotFragment newInstance() {
        return new ScreenshotFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overall = (Overall)getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ll = (RelativeLayout)inflater.inflate(R.layout.fragment_screenshot, container, false);
        img = (ImageView)ll.findViewById(R.id.screenshot);
        return ll;
    }

}
