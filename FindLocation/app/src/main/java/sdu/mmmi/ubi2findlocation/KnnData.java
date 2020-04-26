package sdu.mmmi.ubi2findlocation;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KnnData {

    private final static String pathToCsv = "";


    public static List<SignalVector> getData(Context context) throws IOException {
        List<SignalVector> data = new ArrayList<>();

        InputStream is = context.getResources().openRawResource(R.raw.data);
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = csvReader.readLine()) != null) {
            String[] row = line.split(",");
            // 1587720906833,Bedroom 1,U,-45,-44,-59
            data.add(new SignalVector(row[1], Integer.parseInt(row[3]), Integer.parseInt(row[4]), Integer.parseInt(row[5])));
            System.out.println(row[1] +","+Integer.parseInt(row[3])+","+ Integer.parseInt(row[4])+","+ Integer.parseInt(row[5]));
            // do something with the data
        }
        csvReader.close();

        return data;
    }

    public static List<SignalVector> getDataRooms(Context context) throws IOException {
        List<SignalVector> data = getData(context);

        List<SignalVector> RoomData = data.stream().map(r -> {
            String[] s = r.getLocation().split(" ");
            String newLoc = "";
            for (int i = 0; i < s.length - 1; i++) {
                newLoc += s[i];
            }
            r.setLocation(newLoc);
            System.out.println(newLoc);
            return r;
        }).collect(Collectors.toList());

        return RoomData;
    }




    public static List<SignalVector> getTestData() {
        List<SignalVector> data = new ArrayList<>();
        data.add(new SignalVector("Bedroom 1",-52,-27,-72));
        data.add(new SignalVector("Bedroom 1",-47,-29,-70));
        data.add(new SignalVector("Bedroom 1",-46,-32,-62));
        data.add(new SignalVector("Bedroom 1",-46,-36,-62));
        data.add(new SignalVector("Bedroom 2",-47,-32,-63));
        data.add(new SignalVector("Bedroom 2",-51,-31,-58));
        data.add(new SignalVector("Bedroom 2",-49,-39,-58));
        data.add(new SignalVector("Bedroom 2",-50,-40,-55));
        data.add(new SignalVector("Living Room 1",-38,-47,-52));
        data.add(new SignalVector("Living Room 1",-36,-40,-57));
        data.add(new SignalVector("Living Room 1",-44,-36,-56));
        data.add(new SignalVector("Living Room 1",-34,-42,-49));
        data.add(new SignalVector("Living Room 2",-51,-47,-47));
        data.add(new SignalVector("Living Room 2",-37,-48,-60));
        data.add(new SignalVector("Living Room 2",-30,-38,-56));
        data.add(new SignalVector("Living Room 2",-34,-44,-52));
        data.add(new SignalVector("Living Room 3",-36,-54,-43));
        data.add(new SignalVector("Living Room 3",-29,-57,-49));
        data.add(new SignalVector("Living Room 3",-36,-46,-45));
        data.add(new SignalVector("Living Room 3",-25,-61,-45));
        data.add(new SignalVector("Living Room 4",-47,-46,-43));
        data.add(new SignalVector("Living Room 4",-61,-49,-46));
        data.add(new SignalVector("Living Room 4",-43,-42,-40));
        data.add(new SignalVector("Living Room 4",-43,-53,-36));
        data.add(new SignalVector("Balcony 1",-36,-42,-55));
        data.add(new SignalVector("Balcony 1",-46,-41,-62));
        data.add(new SignalVector("Balcony 1",-40,-58,-55));
        data.add(new SignalVector("Balcony 1",-45,-44,-55));
        data.add(new SignalVector("Balcony 2",-37,-50,-53));
        data.add(new SignalVector("Balcony 2",-42,-41,-55));
        data.add(new SignalVector("Balcony 2",-40,-52,-47));
        data.add(new SignalVector("Balcony 2",-55,-53,-57));
        data.add(new SignalVector("Kitchen 1",-59,-67,-53));
        data.add(new SignalVector("Kitchen 1",-49,-64,-58));
        data.add(new SignalVector("Kitchen 1",-53,-55,-51));
        data.add(new SignalVector("Kitchen 1",-46,-56,-60));
        data.add(new SignalVector("Front Door",-55,-64,-63));
        data.add(new SignalVector("Front Door",-47,-68,-57));
        data.add(new SignalVector("Front Door",-65,-58,-73));
        data.add(new SignalVector("Front Door",-64,-60,-48));
        data.add(new SignalVector("Bathroom 1",-51,-53,-41));
        data.add(new SignalVector("Bathroom 1",-49,-49,-48));
        data.add(new SignalVector("Bathroom 1",-44,-55,-31));
        data.add(new SignalVector("Bathroom 1",-50,-54,-43));
        data.add(new SignalVector("Bathroom 2",-58,-67,-55));
        data.add(new SignalVector("Bathroom 2",-48,-64,-47));
        data.add(new SignalVector("Bathroom 2",-48,-69,-57));
        data.add(new SignalVector("Bathroom 2",-48,-59,-32));
        return data;
    }
}
