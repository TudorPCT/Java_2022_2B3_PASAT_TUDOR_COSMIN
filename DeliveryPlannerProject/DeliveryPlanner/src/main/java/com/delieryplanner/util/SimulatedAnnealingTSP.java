package com.delieryplanner.util;

import com.delieryplanner.model.Command;
import com.delieryplanner.model.Warehouse;


import java.util.*;

public class SimulatedAnnealingTSP {

    private final List<Command> nodeList;
    private final Warehouse warehouse;
    private final Random rand = new Random();

    public SimulatedAnnealingTSP(List<Command> nodeList, Warehouse warehouse) {
        this.nodeList = nodeList;
        this.warehouse = warehouse;
    }

    public Map<Double, List<Command>> execute(int maxIterations){
        int t = 0;
        double temperature = 100;
        var candidate = generateRandomVector();
        double candidateCost = evaluate(candidate);
        int maxLocal = 200;
        double bestCost = candidateCost;

        do {
            int count = 0;
            do {
                var newCandidate = createNeighbour(candidate);
                double newCandidateCost = evaluate(newCandidate);
                if (newCandidateCost < candidateCost) {
                    candidate = newCandidate;
                    candidateCost = newCandidateCost;
                    if (newCandidateCost < bestCost)
                        bestCost = newCandidateCost;
                } else if (rand.nextDouble() < Math.exp(-(Math.abs(newCandidateCost - candidateCost) / temperature))) {
                    candidate = newCandidate;
                    candidateCost = newCandidateCost;
                }
                count++;
            } while (count < maxLocal);

            temperature *= .9;
            t++;
        } while (t < maxIterations && temperature > 0.00005);

        Map<Double, List<Command>> result = new HashMap<>();
        result.put(candidateCost, candidate);
        return result;
    }

    private List<Command> generateRandomVector(){
        List<Command> canditate = new ArrayList<>(nodeList);
        Collections.shuffle(canditate);
        return canditate;
    }

    public double evaluate(List<Command> candidate)
    {
        double cromCost = Math.sqrt(Math.pow(candidate.get(0).getLatitude() - warehouse.getLatitude(), 2) + Math.pow(candidate.get(0).getLongitude() - warehouse.getLongitude(), 2));

        for (int i = 1; i < candidate.size(); i++) {
            cromCost += Math.sqrt(Math.pow(candidate.get(i).getLatitude() - candidate.get(i - 1).getLatitude(), 2) + Math.pow(candidate.get(i).getLongitude() - candidate.get(i - 1).getLongitude(), 2));
        }
        cromCost += Math.sqrt(Math.pow(candidate.get(candidate.size()-1).getLatitude() - warehouse.getLatitude(), 2) + Math.pow(candidate.get(candidate.size()-1).getLongitude() - warehouse.getLongitude(), 2));
        return cromCost;
    }

    private List<Command> createNeighbour(List<Command> candidate) {
        int a = (rand.nextInt(candidate.size()) + 1) % candidate.size();
        int b = rand.nextInt(candidate.size());

        List<Command> inverseNeighbour = inverseOperator(a, b, candidate);
        List<Command> swapNeighbour = swapOperator(a, b, candidate);
        List<Command> insertNeighbour = insertOperator(a, b, candidate);

        double inverseNeighbourCost = evaluate(inverseNeighbour);
        double swapNeighbourCost = evaluate(swapNeighbour);
        double insertNeighbourCost = evaluate(insertNeighbour);
        if (inverseNeighbourCost <= swapNeighbourCost && inverseNeighbourCost <= insertNeighbourCost) {
            return inverseNeighbour;
        }
        else if (swapNeighbourCost <= inverseNeighbourCost && swapNeighbourCost <= insertNeighbourCost)
            return swapNeighbour;
        else return insertNeighbour;
    }

    private List<Command> inverseOperator(Integer a, Integer b, List<Command> candidate)
    {
        List<Command> candidateTemp = new ArrayList<>();

        if (a > b) {
            Integer aux;
            aux = a;
            a = b;
            b = aux;
        }

        for (int i = 0; i < a; i++) candidateTemp.add(candidate.get(i));
        for (int i = b; i >= a; i--) candidateTemp.add(candidate.get(i));
        for (int i = b + 1; i < candidate.size(); i++) candidateTemp.add(candidate.get(i));

        return candidateTemp;
    }

    private List<Command> swapOperator(Integer a, Integer b, List<Command> candidate){
        List<Command> candidateTemp = new ArrayList<>(candidate);
        Command point = candidateTemp.get(a);
        candidateTemp.set(a, candidateTemp.get(b));
        candidateTemp.set(b, point);

        return candidateTemp;
    }

    private List<Command> insertOperator(Integer a, Integer b, List<Command> candidate){
        List<Command> candidateTemp = new ArrayList<>();

        if (a < b)
        {
            for (int i = 0; i < a; i++) candidateTemp.add(candidate.get(i));

            candidateTemp.add(candidate.get(b));
            candidateTemp.add(candidate.get(a));

            for (int i = a + 1; i < candidate.size(); i++)
                if (i != b) candidateTemp.add(candidate.get(i));
        }
        else if (a > b)
        {
            for (int i = 0; i < b; i++) candidateTemp.add(candidate.get(i));
            for (int i = b + 1; i < a; i++) candidateTemp.add(candidate.get(i));
            candidateTemp.add(candidate.get(a));
            candidateTemp.add(candidate.get(b));
            for (int i = a + 1; i < candidate.size(); i++) candidateTemp.add(candidate.get(i));
        }
        else candidateTemp.addAll(candidate);

        return candidateTemp;
    }
}
