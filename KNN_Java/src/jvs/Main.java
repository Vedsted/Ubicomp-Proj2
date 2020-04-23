package jvs;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        int k = 3;
        long[] observation = new long[]{-59, -74, -48};
        List<DataObj> data = getData();

        // Get distances to observation
        List<KeyValue<String, Double>> distances = getDistances(data, observation);

        // Sort for shortest distance first
        distances.sort(Comparator.comparingDouble(KeyValue<String, Double>::getValue));

        // Get the k closest vectors
        List<KeyValue<String, Double>> kNearest = distances.subList(0, k);

        // Find dominating class
        String location = findDominating(kNearest);

        System.out.println("You are in room: " + location);
    }

    private static List<KeyValue<String, Double>> getDistances(List<DataObj> data, long[] observation){
        return data.stream().map( v -> calcDistance(v,observation)).collect(Collectors.toList());
    }

    private static String findDominating(List<KeyValue<String, Double>> kNearest) {
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

    private static KeyValue<String, Double> calcDistance(DataObj v, long[] o) {
        double a1 = Math.pow(o[0] - v.getAp1(), 2);
        double a2 = Math.pow(o[1] - v.getAp2(), 2);
        double a3 = Math.pow(o[2] - v.getAp3(), 2);
        return new KeyValue<>(v.getLocation(), Math.sqrt(a1 + a2 + a3));
    }

    private static List<DataObj> getData() {
        List<DataObj> data = new ArrayList<>();
        data.add(new DataObj("Bedroom 1",-52,-27,-72));
        data.add(new DataObj("Bedroom 1",-47,-29,-70));
        data.add(new DataObj("Bedroom 1",-46,-32,-62));
        data.add(new DataObj("Bedroom 1",-46,-36,-62));
        data.add(new DataObj("Bedroom 2",-47,-32,-63));
        data.add(new DataObj("Bedroom 2",-51,-31,-58));
        data.add(new DataObj("Bedroom 2",-49,-39,-58));
        data.add(new DataObj("Bedroom 2",-50,-40,-55));
        data.add(new DataObj("Living Room 1",-38,-47,-52));
        data.add(new DataObj("Living Room 1",-36,-40,-57));
        data.add(new DataObj("Living Room 1",-44,-36,-56));
        data.add(new DataObj("Living Room 1",-34,-42,-49));
        data.add(new DataObj("Living Room 2",-51,-47,-47));
        data.add(new DataObj("Living Room 2",-37,-48,-60));
        data.add(new DataObj("Living Room 2",-30,-38,-56));
        data.add(new DataObj("Living Room 2",-34,-44,-52));
        data.add(new DataObj("Living Room 3",-36,-54,-43));
        data.add(new DataObj("Living Room 3",-29,-57,-49));
        data.add(new DataObj("Living Room 3",-36,-46,-45));
        data.add(new DataObj("Living Room 3",-25,-61,-45));
        data.add(new DataObj("Living Room 4",-47,-46,-43));
        data.add(new DataObj("Living Room 4",-61,-49,-46));
        data.add(new DataObj("Living Room 4",-43,-42,-40));
        data.add(new DataObj("Living Room 4",-43,-53,-36));
        data.add(new DataObj("Balcony 1",-36,-42,-55));
        data.add(new DataObj("Balcony 1",-46,-41,-62));
        data.add(new DataObj("Balcony 1",-40,-58,-55));
        data.add(new DataObj("Balcony 1",-45,-44,-55));
        data.add(new DataObj("Balcony 2",-37,-50,-53));
        data.add(new DataObj("Balcony 2",-42,-41,-55));
        data.add(new DataObj("Balcony 2",-40,-52,-47));
        data.add(new DataObj("Balcony 2",-55,-53,-57));
        data.add(new DataObj("Kitchen 1",-59,-67,-53));
        data.add(new DataObj("Kitchen 1",-49,-64,-58));
        data.add(new DataObj("Kitchen 1",-53,-55,-51));
        data.add(new DataObj("Kitchen 1",-46,-56,-60));
        data.add(new DataObj("Front Door",-55,-64,-63));
        data.add(new DataObj("Front Door",-47,-68,-57));
        data.add(new DataObj("Front Door",-65,-58,-73));
        data.add(new DataObj("Front Door",-64,-60,-48));
        data.add(new DataObj("Bathroom 1",-51,-53,-41));
        data.add(new DataObj("Bathroom 1",-49,-49,-48));
        data.add(new DataObj("Bathroom 1",-44,-55,-31));
        data.add(new DataObj("Bathroom 1",-50,-54,-43));
        data.add(new DataObj("Bathroom 2",-58,-67,-55));
        data.add(new DataObj("Bathroom 2",-48,-64,-47));
        data.add(new DataObj("Bathroom 2",-48,-69,-57));
        data.add(new DataObj("Bathroom 2",-48,-59,-32));
        return data;
    }
}
