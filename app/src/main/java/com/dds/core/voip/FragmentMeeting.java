package com.dds.core.voip;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dds.skywebrtc.CallSession;
import com.dds.skywebrtc.EnumType;
import com.dds.skywebrtc.SkyEngineKit;
import com.dds.webrtc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dds on 2020/5/24.
 * ddssingsong@163.com
 */
public class FragmentMeeting extends Fragment implements CallSession.CallSessionCallback, View.OnClickListener {
    private SkyEngineKit gEngineKit;
    private CallMultiActivity activity;
    private RelativeLayout meeting_item_container;
    private NineGridView grid_view;
    private BaseAdapter adapter;
    private List<View> views = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (CallMultiActivity) getActivity();
        if (activity != null) {

        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting, container, false);
        initView(view);
        init();
        return view;
    }

    private void initView(View view) {
        meeting_item_container = view.findViewById(R.id.meeting_item_container);
        grid_view = view.findViewById(R.id.grid_view);
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public Object getItem(int position) {
                return views.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }
        };
    }


    private void init() {
        gEngineKit = activity.getEngineKit();
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void didCallEndWithReason(EnumType.CallEndReason var1) {

    }

    @Override
    public void didChangeState(EnumType.CallState var1) {

    }

    @Override
    public void didChangeMode(boolean isAudioOnly) {

    }

    @Override
    public void didCreateLocalVideoTrack() {
        View surfaceView = gEngineKit.getCurrentSession().setupLocalVideo(true);
        if (surfaceView != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(200, 280);
            surfaceView.setLayoutParams(layoutParams);
            meeting_item_container.addView(surfaceView);
        }


    }

    @Override
    public void didReceiveRemoteVideoTrack(String userId) {
        View surfaceView = gEngineKit.getCurrentSession().setupRemoteVideo(userId, false);
        if (surfaceView != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(200, 280);
            layoutParams.leftMargin = 200;
            surfaceView.setLayoutParams(layoutParams);
            meeting_item_container.addView(surfaceView);
        }
    }

    @Override
    public void didError(String error) {

    }
}