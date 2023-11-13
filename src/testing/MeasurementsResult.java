package testing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MeasurementsResult {
    public long arraylistTime;
    public long linkedListTime;
    public long linkedHashSetTime;
    public long hashMapTime;
    public long hashSetTime;
    public long linkedHashMapTime;
    public long treeMapTime;
    public long treeSetTime;
    public HashMap<String,Long> measurementsValues;
    public String resultName;
    public MeasurementsResult(String name) {
        resultName = name;
        measurementsValues = new HashMap<>();
    }

    private LinkedHashMap<String,Long> sortedMap(){
        List<Entry<String, Long>> entryList = new ArrayList<>(measurementsValues.entrySet());

        entryList.sort(Comparator.comparing(Map.Entry::getValue));

        LinkedHashMap<String, Long> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    @Override
    public String toString() {
        return resultName + sortedMap().toString();
    }
}
