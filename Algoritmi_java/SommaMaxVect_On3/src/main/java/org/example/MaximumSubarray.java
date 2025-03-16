package org.example;

public class MaximumSubarray {

    public static int maxSubarraySum(int[] array) {
        int n = array.length;
        int maxSum = Integer.MIN_VALUE;

        // Ciclo esterno: fissa l'indice di inizio del sottovettore
        for (int i = 0; i < n; i++) {
            // Ciclo medio: fissa l'indice di fine del sottovettore
            for (int j = i; j < n; j++) {
                int currentSum = 0;

                // Ciclo interno: calcola la somma del sottovettore da i a j
                for (int k = i; k <= j; k++) {
                    currentSum += array[k];
                }

                // Aggiorna la somma massima se necessario
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = maxSubarraySum(array);
        System.out.println("La somma massima del sottovettore è: " + maxSum);
    }
}