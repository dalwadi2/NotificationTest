package in.vaksys.notificationtest;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;

import br.com.goncalves.pugnotification.notification.PugNotification;
import io.realm.Realm;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class NotificationService extends IntentService {
    public static int serviceRunning = 0;
    private Realm realm;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        realm = Realm.getDefaultInstance();

        try {
            realm = Realm.getDefaultInstance();
            Task results1 = realm.where(Task.class).findFirst();
            fireNotification(results1);

        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        serviceRunning = 1;
        return serviceRunning;
    }

    @Override
    public void onDestroy() {
        serviceRunning = 0;
    }

    private void fireNotification(Task drop) {
        String message = getString(R.string.notif_message) + "\"" + drop.getDescription() + "\"";
        PugNotification.with(this)
                .load()
                .title(R.string.notif_title)
                .message("asjdhajsgdags")
                .bigTextStyle(message)
                .smallIcon(R.mipmap.ic_launcher_aa)
                .largeIcon(R.mipmap.ic_launcher_aa)
                .flags(Notification.DEFAULT_ALL)
                .autoCancel(true)
                .click(MainActivity.class)
                .simple()
                .build();
    }
}
