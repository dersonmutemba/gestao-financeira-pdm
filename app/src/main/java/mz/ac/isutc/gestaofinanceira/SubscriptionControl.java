package mz.ac.isutc.gestaofinanceira;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class SubscriptionControl extends Service {
    public SubscriptionControl() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}