package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


//tecnica de otimizacao que utiliza  um processo de começar com uma solução inicial 
//e permitir que o algoritmo se mova aleatoriamente para soluções piores ou melhores
// com uma probabilidade decrescente ao longo do tempo. 
//Essa abordagem permite que o algoritmo escape de mínimos locais e encontre a melhor solução global.
public class SimulatedAnnealingTSP {

    //temperatura inicial
    private static final double INITIAL_TEMPERATURE = 100;
    //fator de resfriamento
    private static final double COOLING_FACTOR = 0.99;
     //numero maximo de iteracoes sem sua melhorar
    private static final int MAX_ITERATIONS_WITHOUT_IMPROVEMENT = 1000;
    //distancia de troca
    private static final int SWAP_DISTANCE = 2;

    
    public static int[][] getCities() {
        return cities;
    }

    private static int[][] cities = {
            {0, 0}, {1, 2},{3, 1},{5, 4},{7, 1},
            {9, 3}, {8, 5},{6, 6},{4, 5},{2, 7}
    };

    //gera uma solucao inicial aleatoria e em seguida embaralha a lista
    public static List<Integer> generateInitialTour() {
        List<Integer> tour = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            tour.add(i);
        }
        Collections.shuffle(tour);
        return tour;
    }

    //calcular a distancia entre as cidades
    private static int calculateDistance(int city1, int city2) {
        int x1 = cities[city1][0];
        int y1 = cities[city1][1];
        int x2 = cities[city2][0];
        int y2 = cities[city2][1];
        return (int) Math.round(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }

    //recebe uma lista de inteiros que representa uma ordem de visitacao
    //e calcula a distancia total percorrida
    public static int objectiveFunction(List<Integer> tour) {
        int distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            int city1 = tour.get(i);
            int city2 = tour.get(i + 1);
            distance += calculateDistance(city1, city2);
        }
        distance += calculateDistance(tour.get(tour.size() - 1), tour.get(0));
        return distance;
    }

    //retorna uma nova solucao aleatoria a partir da solucao atual
    private static List<Integer> getRandomNeighbour(List<Integer> tour) {
        List<Integer> newTour = new ArrayList<>(tour);
        Random random = new Random();
        int i = random.nextInt(newTour.size() - SWAP_DISTANCE * 2) + SWAP_DISTANCE;
        int j = random.nextInt(SWAP_DISTANCE * 2 + 1) - SWAP_DISTANCE;
        Collections.swap(newTour, i, i + j);
        return newTour;
    }

    //implementacao do algoritimo simulated Annealing
    //recebe como entrada a solucao inicial e retorna a solucao final 
    public static List<Integer> simulatedAnnealing(List<Integer> initialTour) {
        List<Integer> currentTour = initialTour;
        List<Integer> bestTour = currentTour;
        double temperature = INITIAL_TEMPERATURE;
        int iterationsWithoutImprovement = 0;
        //a cade iteracao o algoritimo gera uma nova solucao aleatoria a partidr da atual
        //e calcula a diferenca de distancia delta entre a nova solucao e a solucao atual
        // o loop continua ate que o numero de iteracoes sem melhora seja alcancado
        // ou ate que a temperatura atinga o limite
        while (temperature > 1e-10 && iterationsWithoutImprovement < MAX_ITERATIONS_WITHOUT_IMPROVEMENT) {
            List<Integer> newTour = getRandomNeighbour(currentTour);
            int delta = objectiveFunction(newTour) - objectiveFunction(currentTour);
            if (delta < 0 || Math.exp(-delta / temperature) > Math.random()) {
                currentTour = newTour;
                if (objectiveFunction(currentTour) < objectiveFunction(bestTour)) {
                    bestTour = currentTour;
                    iterationsWithoutImprovement = 0;
                } else {
                    iterationsWithoutImprovement++;
                }
            } else {
                iterationsWithoutImprovement++;
            }
            temperature *= COOLING_FACTOR;
        }
        return bestTour;
    }
}
