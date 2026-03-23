import java.util.Random;

/** 
 * MIT License
 *
 * Copyright(c) 2024-255 João Caram <caram@pucminas.br>
 *                       Eveline Alonso Veloso
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random(42);
    static long operacoes;
    static double nanoToMilli = 1.0/1_000_000;

    /**
     * Código de teste 1. Este método percorre o vetor de 2 em 2 posições,
     * somando o resultado de vetor[i] % 2.
     * Complexidade: O(n) - linear.
     * Operação relevante: vetor[i] % 2 (operação de módulo).
     * @param vetor Vetor com dados para teste.
     * @return Soma dos restos da divisão por 2 dos elementos em posições pares.
     */
    static int codigo1(int[] vetor) {
        int resposta = 0;
        for (int i = 0; i < vetor.length; i += 2) {
            resposta += vetor[i]%2;
            operacoes++;
        }
        return resposta;
    }

    /**
     * Código de teste 2. Este método possui um loop externo que divide k por 2
     * a cada iteração, e um loop interno que conta de 0 até k.
     * Complexidade: O(n log n).
     * Operação relevante: contador++ (incremento no loop interno).
     * @param vetor Vetor com dados para teste.
     * @return Valor do contador acumulado.
     */
    static int codigo2(int[] vetor) {
        int contador = 0;
        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                contador++;
                operacoes++;
            }

        }
        return contador;
    }

    /**
     * Código de teste 3. Selection Sort - ordena o vetor por seleção.
     * Complexidade: O(n²) - quadrática.
     * Operação relevante: comparação vetor[j] < vetor[menor].
     * @param vetor Vetor com dados para teste.
     */
    static void codigo3(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                operacoes++;
                if (vetor[j] < vetor[menor])
                    menor = j;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }

    /**
     * Código de teste 4 (recursivo). Calcula Fibonacci de n recursivamente.
     * Complexidade: O(2^n) - exponencial.
     * Operação relevante: cada chamada recursiva (soma).
     * @param n Ponto inicial do algoritmo
     * @return O n-ésimo número de Fibonacci.
     */
    static int codigo4(int n) {
        operacoes++;
        if (n <= 2)
            return 1;
        else
            return codigo4(n - 1) + codigo4(n - 2);
    }

    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho){
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;
        
    }

    public static void main(String[] args) {

        // =============================================
        // CODIGO 1 - Teste Grande (linear O(n))
        // =============================================
        System.out.println("========================================");
        System.out.println("CODIGO 1 - Complexidade O(n)");
        System.out.println("========================================");
        System.out.printf("%-15s | %-20s | %-15s%n", "Tamanho (n)", "Operacoes", "Tempo (ms)");
        System.out.println("-".repeat(55));

        for (int tamanho : tamanhosTesteGrande) {
            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo1(vetor);
            long fim = System.nanoTime();
            double tempoMs = (fim - inicio) * nanoToMilli;
            System.out.printf("%-15d | %-20d | %-15.2f%n", tamanho, operacoes, tempoMs);
        }

        // =============================================
        // CODIGO 2 - Teste Grande (O(n log n))
        // =============================================
        System.out.println();
        System.out.println("========================================");
        System.out.println("CODIGO 2 - Complexidade O(n log n)");
        System.out.println("========================================");
        System.out.printf("%-15s | %-20s | %-15s%n", "Tamanho (n)", "Operacoes", "Tempo (ms)");
        System.out.println("-".repeat(55));

        for (int tamanho : tamanhosTesteGrande) {
            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo2(vetor);
            long fim = System.nanoTime();
            double tempoMs = (fim - inicio) * nanoToMilli;
            System.out.printf("%-15d | %-20d | %-15.2f%n", tamanho, operacoes, tempoMs);
        }

        // =============================================
        // CODIGO 3 - Teste Médio (Selection Sort O(n²))
        // =============================================
        System.out.println();
        System.out.println("========================================");
        System.out.println("CODIGO 3 - Complexidade O(n^2)");
        System.out.println("========================================");
        System.out.printf("%-15s | %-20s | %-15s%n", "Tamanho (n)", "Operacoes", "Tempo (ms)");
        System.out.println("-".repeat(55));

        for (int tamanho : tamanhosTesteMedio) {
            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo3(vetor);
            long fim = System.nanoTime();
            double tempoMs = (fim - inicio) * nanoToMilli;
            System.out.printf("%-15d | %-20d | %-15.2f%n", tamanho, operacoes, tempoMs);
        }

        // =============================================
        // CODIGO 4 - Teste Pequeno (Fibonacci O(2^n))
        // =============================================
        System.out.println();
        System.out.println("========================================");
        System.out.println("CODIGO 4 - Complexidade O(2^n)");
        System.out.println("========================================");
        System.out.printf("%-15s | %-20s | %-15s%n", "n", "Operacoes", "Tempo (ms)");
        System.out.println("-".repeat(55));

        for (int n : tamanhosTestePequeno) {
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo4(n);
            long fim = System.nanoTime();
            double tempoMs = (fim - inicio) * nanoToMilli;
            System.out.printf("%-15d | %-20d | %-15.2f%n", n, operacoes, tempoMs);
        }
    }
}