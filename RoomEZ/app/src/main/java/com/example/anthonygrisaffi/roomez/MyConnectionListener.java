package com.example.anthonygrisaffi.roomez;

import com.layer.sdk.LayerClient;
import com.layer.sdk.exceptions.LayerException;
import com.layer.sdk.listeners.LayerConnectionListener;

import java.security.acl.Group;

/**
 * Created by KD on 3/4/15.
 */
public class MyConnectionListener implements LayerConnectionListener {




    @Override
    public void onConnectionConnected(LayerClient layerClient) {
        layerClient.authenticate();
    }

    @Override
    public void onConnectionDisconnected(LayerClient arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onConnectionError(LayerClient arg0, LayerException e) {
        // TODO Auto-generated method stub
    }

}