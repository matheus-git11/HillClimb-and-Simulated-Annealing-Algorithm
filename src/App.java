import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hill_climb_algorithm.HillClimbingTSP;
import hill_climb_algorithm.SimulatedAnnealingTSP;

public class App {
    public static void main(String[] args) throws Exception {

        // Gera uma ordem inicial aleatória de cidades
         List<Integer> tour = new ArrayList<>();
         for (int i = 0; i < HillClimbingTSP.getCities().length; i++) {
             tour.add(i);
         }
         Collections.shuffle(tour);
         
         // Executa o algoritmo Hill Climb
         tour = HillClimbingTSP.hillClimb(tour);
         
         // Imprime a solução encontrada
         System.out.println("\n\n\n");
        System.out.println("HillClimbing");
         System.out.println("Melhor solução encontrada:");
         for (int city : tour) {
             System.out.print(city + " ");
         }
         System.out.println("\nDistância total percorrida: " + HillClimbingTSP.totalDistance(tour));


         //////////////////////////////////


        System.out.println("\n\n");
        System.out.println("SIMULATED ANNEALING");
        List<Integer> initialTour = SimulatedAnnealingTSP.generateInitialTour();
        List<Integer> bestTour = SimulatedAnnealingTSP.simulatedAnnealing(initialTour);
        int bestDistance = SimulatedAnnealingTSP.objectiveFunction(bestTour);
        System.out.println("Melhor solucao encontrada: " + bestTour);
        System.out.println("Distancia total percorrida: " + bestDistance);
                
     }
}
