package sdu.mmmi.ubi2findlocation;

import android.content.Context;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Knn {

    private int k;
    private List<SignalVector> data;

    public Knn(int k, List<SignalVector> data) {
        this.k = k;
        this.data = data;
    }

    public String getLocation(int ap1, int ap2, int ap3) {
        int[] observation = new int[]{ap1, ap2, ap3};

        List<KeyValue<String, Double>> distances = data.stream().map( v -> calcDistance(v,observation)).collect(Collectors.toList());
        distances.sort(Comparator.comparingDouble(KeyValue<String, Double>::getValue));

        List<KeyValue<String, Double>> kNearest = distances.subList(0, k);
        String location = findDominating(kNearest);
       return "You are in room: "+  location;
    }

    private String findDominating(List<KeyValue<String, Double>> kNearest) {
        // If k == 1 return first
        if (kNearest.size() == 1) return kNearest.get(0).getKey();

        Map<String, Integer> occurrences = new HashMap<>();
        for (KeyValue<String, Double> r : kNearest){
            if (!occurrences.containsKey(r.getKey())){
                occurrences.put(r.getKey(), 0);
            }
            occurrences.put(r.getKey(), occurrences.get(r.getKey()) +1 );
        }

        List<KeyValue<String, Integer>> o = occurrences.keySet().stream().map(k -> new KeyValue<String, Integer>(k, occurrences.get(k))).collect(Collectors.toList());
        o.sort(Comparator.comparingInt(KeyValue<String, Integer>::getValue));
        return o.get(0).getKey();
    }

    private KeyValue<String, Double> calcDistance(SignalVector v, int[] o) {
        double a1 = Math.pow(o[0] - v.getAp1(), 2);
        double a2 = Math.pow(o[1] - v.getAp2(), 2);
        double a3 = Math.pow(o[2] - v.getAp3(), 2);
        return new KeyValue<>(v.getLocation(), Math.sqrt(a1 + a2 + a3));
    }
}
