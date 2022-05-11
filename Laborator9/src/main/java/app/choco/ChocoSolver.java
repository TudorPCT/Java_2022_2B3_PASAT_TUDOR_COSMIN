package app.choco;

import app.entity.City;
import app.repositories.CityRepository;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.util.*;

public class ChocoSolver {

    CityRepository cityRepository = new CityRepository();
    int[] populationList;
    Map<Integer, City> cityMap;

    public ChocoSolver(){
        init();
    }

    public void solve(Integer lowerBound, Integer upperBound) {
        Model model = new Model("Choco Solver");
        IntVar[] x = model.intVarArray("x", populationList.length,  populationList);
        model.sum(x,">=", lowerBound);
        model.sum(x,"<=", upperBound);
        int count = 1;
        while (model.getSolver().solve()) {

            StringBuilder buildSolution = new StringBuilder();
            int[] solutionList = model.getDomainUnion(x);
            char firstLetter = cityMap.get(solutionList[0]).getName().charAt(0);
            boolean isSolution = true;
            if(solutionList.length < 3)
                continue;
            for (int j : solutionList) {
                City city = cityMap.get(j);
                if (city.getName().charAt(0) != firstLetter){
                    isSolution = false;
                    break;
                }
                buildSolution.append(city.getName() + " ");
            }
            if (isSolution) {
                System.out.println(buildSolution);
                break;
            }

        }
    }

    private void init(){
        List<City> cities = cityRepository.findAll();
        cityMap = new HashMap<>();
        populationList = new int[cities.size()];
        int index = 0;
        for(City city : cities){
            populationList[index++] = city.getPopulation();
            cityMap.put(city.getPopulation(), city);
        }
    }
}
