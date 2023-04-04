package hill_climb_algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//Problema do caixeiro viajante
//TSP - traveling slaesman problem
public class HillClimbingTSP {
    
    private static Random random = new Random();

    //coordenadas das cidades a serem visitadas
    private static int[][] cities  = new int[][]{
        {0, 0}, {1, 2}, {3, 1}, {5, 4}, {7, 1}, 
        {9, 3}, {8, 5}, {6, 6}, {4, 5}, {2, 7}
    };

    //funcao para definir a distancia total percorrida
    //calcula a distancia total percorrida por todas as cidades 
    //tour representa a ordem das cidades visistadas
    public static double totalDistance(List<Integer> tour){
        
        double distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            int city1 = tour.get(i);
            int city2 = tour.get(i + 1);
            int x1 = cities[city1][0];
            int y1 = cities[city1][1];
            int x2 = cities[city2][0];
            int y2 = cities[city2][1];

            //distance = double que representa a distancia total percorrida
            //calculo da distancia usando formula da distancia euclidiana 
            //raiz quadrada da soma das diferencas das coordenadas x e y das 2 cidades
            distance += Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            
    }

    //calculo da distancia entre a primeira e aultima cidade tambem e adicionada 
    int firstCity = tour.get(0);
    int lastCity = tour.get(tour.size()-1);
    int x1 = cities[firstCity][0];
    int y1 = cities[firstCity][1];
    int x2 = cities[lastCity][0];
    int y2 = cities[lastCity][1];
    distance += Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    return distance;

    }

    public static List<Integer> hillClimb(List<Integer> tour){
        double distance = totalDistance(tour);

        while (true) {
            List<Integer> tour2 = new ArrayList<>(tour);
            Collections.swap(tour2, random.nextInt(tour2.size()), random.nextInt(tour2.size()));
            double distance2 = totalDistance(tour2);
            if (distance2 < distance) {
                return tour;
            } else {
                tour = tour2;
                distance = distance2;
            }
        }
    }

    public static int[][] getCities() {
        return cities;
    }

    public static void setCities(int[][] cities) {
        HillClimbingTSP.cities = cities;
    }

    
}