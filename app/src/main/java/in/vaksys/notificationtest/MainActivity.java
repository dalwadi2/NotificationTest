package in.vaksys.notificationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private RealmConfiguration realmConfig;
    private static final String TAG = "harsh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Task task = realm.createObject(Task.class);
        task.setTitle("hmmmm");
        task.setId("dansdfjsad");
        task.setDescription("mjasdgs akd ajsdhgsajhg  jashgdk asjd kasjdhg kasdj askjdsagdkjsadhgas");
        realm.commitTransaction();

        task = realm.where(Task.class).findFirst();
        Log.e(TAG, "onCreate: " + String.format("ID : %s , Title : %s , Des : %s "
                , task.getId(), task.getTitle(), task.getDescription()));


        if (NotificationService.serviceRunning == 1) {
            Log.e(TAG, "onCreate: Service is on");
        } else {
            Log.e(TAG, "onCreate: service closed ");
            Util.scheduleAlarm(this);
        }
        if (NotificationService.serviceRunning == 1) {
            Log.e(TAG, "onCreate: Service is on");
        } else
            Log.e(TAG, "onCreate: service closed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }
}
