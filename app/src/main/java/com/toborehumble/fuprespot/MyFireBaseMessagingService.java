package com.toborehumble.fuprespot;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

import static com.google.firebase.inappmessaging.internal.Logging.TAG;

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    public MyFireBaseMessagingService() {
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        /*If you want to send messages to this application instance or
        manage this apps subscriptions on the server side, send the
        Instance ID token to your app server.*/

        //sendRegistrationToServer(token);
    }
}
