package fr.uge.jee.servlet.rectanglesession;

import java.util.ArrayDeque;
import java.util.List;

public class History {

    private final ArrayDeque<String> computations = new ArrayDeque<>();


    public void addComputation(String computation){
        computations.add(computation);
    }

    public boolean isEmpty(){
        return computations.isEmpty();
    }

    public String getLatest(){
        return computations.getLast();
    }

    public List<String> getPastComputations(){
        return List.copyOf(computations);
    }
}
