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

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver wifiScanReceiver;
    private WifiManager wifiManager;

    // AP's to look for
    private List<String> accessPoints = Arrays.asList("CableBox-9655-2_4Ghz", "bedroom23", "bathroom23");

    private TextView txtResult;
    private EditText kVal;

    private int[][] samples = new int[3][4];
    private int sampleCounter = 0;

    private List<SignalVector> dataLocations;
    private List<SignalVector> dataRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         kVal = findViewById(R.id.in_k);
         txtResult = findViewById(R.id.txtResult);

        try {
            dataLocations = KnnData.getData(this);
            dataRooms = KnnData.getDataRooms(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        sampleCounter = 0;
        samples = new int[3][4];
        txtResult.setText("Scan failed - Resetting");
    }

    private void scanSuccess() {
        txtResult.setText("Scan " + sampleCounter);

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

        samples[0][sampleCounter] = ap1;
        samples[1][sampleCounter] = ap2;
        samples[2][sampleCounter] = ap3;

        if (sampleCounter == 3) {
            int a1 = getAvg(samples[0]);
            int a2 = getAvg(samples[1]);
            int a3 = getAvg(samples[2]);

            Knn knnLocations = new Knn(Integer.parseInt(this.kVal.getText().toString()), dataLocations);
            Knn knnRooms = new Knn(Integer.parseInt(this.kVal.getText().toString()), dataRooms);
            String toPrint = "Locations:\n" + getSamples(samples)+knnLocations.getLocation(a1, a2, a3) + "\n\n";
            toPrint += "Rooms:\n" + getSamples(samples)+knnRooms.getLocation(a1, a2, a3) + "\n";
            txtResult.setText(toPrint);
            samples = new int[3][4];
            sampleCounter = 0;
        } else {
            sampleCounter++;
            startScan();
        }

    }

    private String getSamples(int[][] samples){
        return  "Scan 0: "+samples[0][0]+","+samples[1][0]+","+samples[2][0]+"]\n"+
                "Scan 1: "+samples[0][1]+","+samples[1][1]+","+samples[2][1]+"]\n"+
                "Scan 2: "+samples[0][2]+","+samples[1][2]+","+samples[2][2]+"]\n"+
                "Scan 3: "+samples[0][3]+","+samples[1][3]+","+samples[2][3]+"]\n";
    }

    private int getAvg(int[] arr){
        int res = 0;
        for(int i : arr){
            res+=i;
        }
        return res/arr.length;
    }

    public void startScan(View view) {
        startScan();
    }

    private void startScan() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        this.registerReceiver(wifiScanReceiver, intentFilter);

        boolean success = wifiManager.startScan();
        if (!success) {
            // scan failure handling
            scanFailure();
        }
    }
}
