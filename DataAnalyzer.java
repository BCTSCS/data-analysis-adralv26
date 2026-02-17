import java.util.ArrayList;

public class DataAnalyzer {

    static int linearSearch(ArrayList<Integer> numbers, int target){
        int index = 0;
        while (index < numbers.size()) {
            if(numbers.get(index)==target) {
                return index;
            } else {
                index++;
            }
        }
        return -1;
    }

    static int binarySearch(ArrayList<Integer> numbers, int target){
        int minIndex = 0;
        int maxIndex = numbers.size() - 1;
        while (minIndex < maxIndex ) {
            int middle =(int) Math.floor((maxIndex+minIndex) / 2);
            if (numbers.get(middle).equals(target)){
                return middle;
            } else {
                if (target > numbers.get(middle)){
                    minIndex = middle + 1;
                } else {
                    maxIndex = middle - 1;
                }

            }

        }



        return -1;
    }

    public static void main(String[] args) {
        // ArrayList<String> arenaList = FileOperator.getStringList("userStory/arenas.txt");
        // for (String arena : arenaList) {
        //     System.out.println(arena);
        // }
        ArrayList<Integer> a = FileOperator.getIntList("numbers.txt");
        // Calculate time
        long startTime = System.nanoTime(); 
        int result = binarySearch(a, 10); 
        long endTime = System.nanoTime(); 



        // Display time in nanoseconds and milliseconds 
        long duration = endTime - startTime; 
        System.out.println("Time taken: " + duration + " nanoseconds"); 
        System.out.println("Time taken: " + (duration / 1000000.0) + " milliseconds");
    }
}
