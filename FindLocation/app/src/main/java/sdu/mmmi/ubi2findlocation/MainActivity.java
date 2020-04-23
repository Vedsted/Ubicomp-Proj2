package sdu.mmmi.ubi2findlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver wifiScanReceiver;
    private WifiManager wifiManager;

    // AP's to look for
    private List<String> accessPoints = Arrays.asList("CableBox-9655-2_4Ghz", "bedroom23", "bathroom23");

    private TextView txtResult;
    private EditText kVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         kVal = findViewById(R.id.in_k);
         txtResult = findViewById(R.id.txtResult);

        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
                if (success) {
                    scanSuccess();
                } else {
                    scanFailure();
                }
            }
        };
    }

    private void scanFailure() {
        txtResult.setText("Scan failed");
    }

    private void scanSuccess() {
        List<ScanResult> results = wifiManager.getScanResults();
        int ap1 = Integer.MIN_VALUE;
        int ap2 = Integer.MIN_VALUE;
        int ap3 = Integer.MIN_VALUE;

        for(ScanResult result : results){
            if (accessPoints.contains(result.SSID)) {
                String ssid = result.SSID;
                int level = result.level; // dBm
                switch (accessPoints.indexOf(ssid)){
                    case 0: ap1=level; break;
                    case 1: ap2=level; break;
                    case 2: ap3=level; break;
                }
            }
        }

        Knn knn = new Knn(Integer.parseInt(this.kVal.getText().toString()));
        txtResult.setText(knn.getLocation(ap1, ap2, ap3));
    }

    public void startScan(View view) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        this.registerReceiver(wifiScanReceiver, intentFilter);

        boolean success = wifiManager.startScan();
        if (!success) {
            // scan failure handling
            txtResult.setText("Scan failed");
        }
    }
}
