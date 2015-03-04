package com.example.anthonygrisaffi.roomez;

/**
 * Created by KD on 3/4/15.
 */

import android.os.AsyncTask;

import com.layer.sdk.LayerClient;
import com.layer.sdk.exceptions.LayerException;
import com.layer.sdk.listeners.LayerAuthenticationListener;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class MyAuthenticationListener implements LayerAuthenticationListener {


    @Override
    public void onAuthenticated(LayerClient client, String arg1) {
        System.out.println("Authentication successful");
    }

    @Override
    public void onAuthenticationChallenge(final LayerClient layerClient, final String nonce) {
        final String mUserId = "USER_ID_HERE";

          /*
   * 2. Acquire an identity token from the Layer Identity Service
   */
        (new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    HttpPost post = new HttpPost("https://layer-identity-provider.herokuapp.com/identity_tokens");
                    post.setHeader("Content-Type", "application/json");
                    post.setHeader("Accept", "application/json");

                    JSONObject json = new JSONObject()
                            .put("app_id", layerClient.getAppId())
                            .put("user_id", mUserId)
                            .put("nonce", nonce);
                    post.setEntity(new StringEntity(json.toString()));

                    HttpResponse response = (new DefaultHttpClient()).execute(post);
                    String eit = (new JSONObject(EntityUtils.toString(response.getEntity())))
                            .optString("identity_token");

            /*
             * 3. Submit identity token to Layer for validation
             */
                    layerClient.answerAuthenticationChallenge(eit);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }).execute();
    }



    @Override
    public void onAuthenticationError(LayerClient layerClient, LayerException e) {
        // TODO Auto-generated method stub
        System.out.println("There was an error authenticating");
    }

    @Override
    public void onDeauthenticated(LayerClient client) {
        // TODO Auto-generated method stub
    }
}