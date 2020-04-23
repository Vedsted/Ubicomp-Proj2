package sdu.mmmi.ubi2datacollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver wifiScanReceiver;
    private WifiManager wifiManager;

    private String dirName = "A_Data";
    private String fileName = "Data.csv";
    private FileUtil fileUtil;

    private TextView txtStatus;
    private Spinner spinLocations;

    private String currentLocation;
    private String currentDirection;

    // AP's to look for
    private List<String> accessPoints = Arrays.asList(new String[]{"VedstedJorgensen_5GHz", "OnePlus2", "OnePlus 5"});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileUtil = new FileUtil(fileName, dirName);

        txtStatus = findViewById(R.id.txt_status);
        spinLocations = findViewById(R.id.spinLocations);

        spinLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentLocation = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
        // Do nothing
        txtStatus.setText("Scan failed");
    }

    private void scanSuccess() {
        txtStatus.setText("Scan successful");
        List<ScanResult> results = wifiManager.getScanResults();
        long ap1 = -1;
        long ap2 = -1;
        long ap3 = -1;

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

        long date = new Date().getTime();
        String out = String.valueOf(date) + ',' +
                currentLocation + ',' +
                currentDirection + ',' +
                ap1 + ',' +
                ap2 + ',' +
                ap3 + '\n';

        fileUtil.appendToFile(out);
    }

    public void startScan(View view) {
        txtStatus.setText("Scanning");
        switch (view.getId()) {
            case R.id.btn_StartDown: currentDirection="D"; break;
            case R.id.btn_StartUp: currentDirection="U"; break;
            case R.id.btn_StartLeft: currentDirection="L"; break;
            case R.id.btn_StartRight: currentDirection="R"; break;
            default: currentDirection="undefined"; break;
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        this.registerReceiver(wifiScanReceiver, intentFilter);

        boolean success = wifiManager.startScan();
        if (!success) {
            // scan failure handling
            txtStatus.setText("Scan failed");
        }
    }
}
