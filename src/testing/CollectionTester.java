package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionTester {
    ArrayList<Employee> arrayList;
    LinkedList<Employee> linkedList;
    HashSet<Employee> hashSet;
    TreeSet<Employee> treeSet;
    LinkedHashSet<Employee> linkedHashSet;
    HashMap<Integer,Employee> hashMap;
    LinkedHashMap<Integer,Employee> linkedHashMap;
    TreeMap<Integer,Employee> treeMap;
    public MeasurementsResult addNElementsTime;
    public MeasurementsResult getRandomElementTime;
    public MeasurementsResult containsTime;
    public MeasurementsResult filterTime;
    public MeasurementsResult allMatchTime;
    public MeasurementsResult parallelFilterTime;
    public MeasurementsResult parallelAllMatchTime;
    public MeasurementsResult removeTime;


    public CollectionTester() {
        this.arrayList = new ArrayList<>();
        this.linkedList = new LinkedList<>();
        this.hashSet = new HashSet<>();
        this.treeSet = new TreeSet<>();
        this.linkedHashSet = new LinkedHashSet<>();
        this.hashMap = new HashMap<>();
        this.linkedHashMap = new LinkedHashMap<>();
        this.treeMap = new TreeMap<>();


        getRandomElementTime = new MeasurementsResult("Get random element   ");
        containsTime = new MeasurementsResult        ("Contains             ");
        filterTime = new MeasurementsResult          ("Filter               ");
        parallelFilterTime = new MeasurementsResult  ("ParallelFilter       ");
        allMatchTime = new MeasurementsResult        ("All Match            ");
        parallelAllMatchTime = new MeasurementsResult("Parallel All Match   ");
        removeTime = new MeasurementsResult          ("Remove               ");

    }

    public void fillCollection(int n) {
        addNElementsTime = new MeasurementsResult("Add " + n + " Elements  ");
        for (int i = 0; i < n; i++) {
            Employee employee = new Employee(i, "John", 30000);
            int finalI = i;

            // ArrayList
            addNElementsTime.measurementsValues.put("ArrayList", measureExecutionTime(() -> arrayList.add(employee)));

            // LinkedHashMap
            addNElementsTime.measurementsValues.put("LinkedHashMap", measureExecutionTime(() -> linkedHashMap.put(finalI, employee)));

            // HashMap
            addNElementsTime.measurementsValues.put("HashMap", measureExecutionTime(() -> hashMap.put(finalI, employee)));

            // LinkedList
            addNElementsTime.measurementsValues.put("LinkedList", measureExecutionTime(() -> linkedList.add(employee)));

            // TreeSet
            addNElementsTime.measurementsValues.put("TreeSet", measureExecutionTime(() -> treeSet.add(employee)));

            // LinkedHashSet
            addNElementsTime.measurementsValues.put("LinkedHashSet", measureExecutionTime(() -> linkedHashSet.add(employee)));

            // HashSet
            addNElementsTime.measurementsValues.put("HashSet", measureExecutionTime(() -> hashSet.add(employee)));

            // TreeMap
            addNElementsTime.measurementsValues.put("TreeMap", measureExecutionTime(() -> treeMap.put(finalI, employee)));
        }
    }

    public void getRandomElement() {
        int n = new Random().nextInt(1, arrayList.size());

        // ArrayList
        getRandomElementTime.measurementsValues.put("ArrayList", measureExecutionTime(() -> arrayList.get(n)));

        // LinkedList
        getRandomElementTime.measurementsValues.put("LinkedList", measureExecutionTime(() -> {
            Employee e = linkedList.get(n);
        }));

        // HashSet
        getRandomElementTime.measurementsValues.put("HashSet", measureExecutionTime(() -> hashSet.iterator().next()));

        // LinkedHashMap
        getRandomElementTime.measurementsValues.put("LinkedHashMap", measureExecutionTime(() -> linkedHashMap.get(n)));

        // TreeSet
        getRandomElementTime.measurementsValues.put("TreeSet", measureExecutionTime(() -> treeSet.iterator().next()));

        // LinkedHashSet
        getRandomElementTime.measurementsValues.put("LinkedHashSet", measureExecutionTime(() -> linkedHashSet.iterator().next()));

        // TreeMap
        getRandomElementTime.measurementsValues.put("TreeMap", measureExecutionTime(() -> treeMap.get(n)));

        // HashMap
        getRandomElementTime.measurementsValues.put("HashMap", measureExecutionTime(() -> hashMap.get(n)));
    }

    public void removeElement() {
        int n = new Random().nextInt(1, arrayList.size());
        Employee employee = new Employee(n, "John", 30000);

        // ArrayList
        removeTime.measurementsValues.put("ArrayList", measureExecutionTime(() -> arrayList.remove(employee)));

        // LinkedList
        removeTime.measurementsValues.put("LinkedList", measureExecutionTime(() -> linkedList.remove(employee)));

        // HashSet
        removeTime.measurementsValues.put("HashSet", measureExecutionTime(() -> hashSet.remove(employee)));

        // LinkedHashMap
        removeTime.measurementsValues.put("LinkedHashMap", measureExecutionTime(() -> linkedHashMap.remove(n)));

        // TreeSet
        removeTime.measurementsValues.put("TreeSet", measureExecutionTime(() -> treeSet.remove(employee)));

        // LinkedHashSet
        removeTime.measurementsValues.put("LinkedHashSet", measureExecutionTime(() -> linkedHashSet.remove(employee)));

        // TreeMap
        removeTime.measurementsValues.put("TreeMap", measureExecutionTime(() -> treeMap.remove(n)));

        // HashMap
        removeTime.measurementsValues.put("HashMap", measureExecutionTime(() -> hashMap.remove(n)));
    }

    public void containsElement() {
        int n = new Random().nextInt(1, arrayList.size());
        Employee employee = new Employee(n, "John", 30000);

        // ArrayList
        containsTime.measurementsValues.put("ArrayList", measureExecutionTime(() -> arrayList.contains(employee)));

        // LinkedList
        containsTime.measurementsValues.put("LinkedList", measureExecutionTime(() -> linkedList.contains(employee)));

        // HashSet
        containsTime.measurementsValues.put("HashSet", measureExecutionTime(() -> hashSet.contains(employee)));

        // LinkedHashMap
        containsTime.measurementsValues.put("LinkedHashMap", measureExecutionTime(() -> linkedHashMap.containsValue(employee)));

        // TreeSet
        containsTime.measurementsValues.put("TreeSet", measureExecutionTime(() -> treeSet.contains(employee)));

        // LinkedHashSet
        containsTime.measurementsValues.put("LinkedHashSet", measureExecutionTime(() -> linkedHashSet.contains(employee)));

        // TreeMap
        containsTime.measurementsValues.put("TreeMap", measureExecutionTime(() -> treeMap.containsValue(employee)));

        // HashMap
        containsTime.measurementsValues.put("HashMap", measureExecutionTime(() -> hashMap.containsValue(employee)));
    }

    public void filterCollection() {
        // ArrayList
        filterTime.measurementsValues.put("ArrayList", measureExecutionTime(() ->
            arrayList.stream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toList())));

        // LinkedList
        filterTime.measurementsValues.put("LinkedList", measureExecutionTime(() ->
            linkedList.stream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toList())));

        // HashSet
        filterTime.measurementsValues.put("HashSet", measureExecutionTime(() ->
            hashSet.stream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toSet())));

        // LinkedHashMap
        filterTime.measurementsValues.put("LinkedHashMap", measureExecutionTime(() ->
            linkedHashMap.values().stream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toList())));

        // TreeSet
        filterTime.measurementsValues.put("TreeSet", measureExecutionTime(() ->
            treeSet.stream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toCollection(TreeSet::new))));

        // LinkedHashSet
        filterTime.measurementsValues.put("LinkedHashSet", measureExecutionTime(() ->
            linkedHashSet.stream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toCollection(LinkedHashSet::new))));

        // TreeMap
        filterTime.measurementsValues.put("TreeMap", measureExecutionTime(() ->
            treeMap.values().stream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toMap(Employee::getId, Function.identity()))));

        // HashMap
        filterTime.measurementsValues.put("HashMap", measureExecutionTime(() ->
            hashMap.values().stream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toMap(Employee::getId, Function.identity()))));


        //Parallel
        parallelFilterTime.measurementsValues.put("ArrayList", measureExecutionTime(() ->
            arrayList.parallelStream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toList())));

        // LinkedList
        parallelFilterTime.measurementsValues.put("LinkedList", measureExecutionTime(() ->
            linkedList.parallelStream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toList())));

        // HashSet
        parallelFilterTime.measurementsValues.put("HashSet", measureExecutionTime(() ->
            hashSet.parallelStream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toSet())));

        // LinkedHashMap
        parallelFilterTime.measurementsValues.put("LinkedHashMap", measureExecutionTime(() ->
            linkedHashMap.values().parallelStream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toList())));

        // TreeSet
        parallelFilterTime.measurementsValues.put("TreeSet", measureExecutionTime(() ->
            treeSet.parallelStream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toCollection(TreeSet::new))));

        // LinkedHashSet
        parallelFilterTime.measurementsValues.put("LinkedHashSet", measureExecutionTime(() ->
            linkedHashSet.parallelStream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toCollection(LinkedHashSet::new))));

        // TreeMap
        parallelFilterTime.measurementsValues.put("TreeMap", measureExecutionTime(() ->
            treeMap.values().parallelStream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toMap(Employee::getId, Function.identity()))));

        // HashMap
        parallelFilterTime.measurementsValues.put("HashMap", measureExecutionTime(() ->
            hashMap.values().parallelStream().filter(e -> e.getId() % 2 == 0).collect(Collectors.toMap(Employee::getId, Function.identity()))));
    }

    public void allMatch() {
        // ArrayList
        allMatchTime.measurementsValues.put("ArrayList", measureExecutionTime(() ->
            arrayList.stream().allMatch(e -> e.getName().equals("John"))));

        // LinkedList
        allMatchTime.measurementsValues.put("LinkedList", measureExecutionTime(() ->
            linkedList.stream().allMatch(e -> e.getName().equals("John"))));

        // HashSet
        allMatchTime.measurementsValues.put("HashSet", measureExecutionTime(() ->
            hashSet.stream().allMatch(e -> e.getName().equals("John"))));

        // LinkedHashMap
        allMatchTime.measurementsValues.put("LinkedHashMap", measureExecutionTime(() ->
            linkedHashMap.values().stream().allMatch(e -> e.getName().equals("John"))));

        // TreeSet
        allMatchTime.measurementsValues.put("TreeSet", measureExecutionTime(() ->
            treeSet.stream().allMatch(e -> e.getName().equals("John"))));

        // LinkedHashSet
        allMatchTime.measurementsValues.put("LinkedHashSet", measureExecutionTime(() ->
            linkedHashSet.stream().allMatch(e -> e.getName().equals("John"))));

        // TreeMap
        allMatchTime.measurementsValues.put("TreeMap", measureExecutionTime(() ->
            treeMap.values().stream().allMatch(e -> e.getName().equals("John"))));

        // HashMap
        allMatchTime.measurementsValues.put("HashMap", measureExecutionTime(() ->
            hashMap.values().stream().allMatch(e -> e.getName().equals("John"))));


        //Parallel
        // ArrayList
        parallelAllMatchTime.measurementsValues.put("ArrayList", measureExecutionTime(() ->
            arrayList.parallelStream().allMatch(e -> e.getName().equals("John"))));

        // LinkedList
        parallelAllMatchTime.measurementsValues.put("LinkedList", measureExecutionTime(() ->
            linkedList.parallelStream().allMatch(e -> e.getName().equals("John"))));

        // HashSet
        parallelAllMatchTime.measurementsValues.put("HashSet", measureExecutionTime(() ->
            hashSet.parallelStream().allMatch(e -> e.getName().equals("John"))));

        // LinkedHashMap
        parallelAllMatchTime.measurementsValues.put("LinkedHashMap", measureExecutionTime(() ->
            linkedHashMap.values().parallelStream().allMatch(e -> e.getName().equals("John"))));

        // TreeSet
        parallelAllMatchTime.measurementsValues.put("TreeSet", measureExecutionTime(() ->
            treeSet.parallelStream().allMatch(e -> e.getName().equals("John"))));

        // LinkedHashSet
        parallelAllMatchTime.measurementsValues.put("LinkedHashSet", measureExecutionTime(() ->
            linkedHashSet.parallelStream().allMatch(e -> e.getName().equals("John"))));

        // TreeMap
        parallelAllMatchTime.measurementsValues.put("TreeMap", measureExecutionTime(() ->
            treeMap.values().parallelStream().allMatch(e -> e.getName().equals("John"))));

        // HashMap
        parallelAllMatchTime.measurementsValues.put("HashMap", measureExecutionTime(() ->
            hashMap.values().parallelStream().allMatch(e -> e.getName().equals("John"))));
    }
    public long measureExecutionTime(Runnable methodToMeasure) {
        long startTime = System.nanoTime();
        methodToMeasure.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public static long nanosToMillis(final long nanos) {
        return Math.max(0L, Math.round(nanos / 1_000_000.0d));
    }

    public void runTest(int n){
        fillCollection(n);
        removeElement();
        getRandomElement();
        containsElement();
        filterCollection();
        allMatch();

        System.out.println(addNElementsTime.toString());
        System.out.println(removeTime.toString());
        System.out.println(getRandomElementTime.toString());
        System.out.println(containsTime.toString());
        System.out.println(filterTime.toString());
        System.out.println(parallelFilterTime.toString());
        System.out.println(allMatchTime.toString());
        System.out.println(parallelAllMatchTime.toString());
        System.out.println("\n");
    }


}
