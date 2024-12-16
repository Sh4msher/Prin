import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Sortierung {

    // Bubble Sort Algorithmus
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // QuickSort Algorithmus
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void quickSortWrapper(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    // MergeSort Algorithmus
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void mergeSortWrapper(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    // Selection Sort Algorithmus
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void selectionSortWrapper(int[] arr) {
        selectionSort(arr);
    }

    // Zeitmessung
    public static double measureTime(Runnable sortAlgorithm) {
        long start = System.nanoTime();
        sortAlgorithm.run();
        long end = System.nanoTime();
        return (end - start) / 1e9;
    }

    // Array-Druckfunktion
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // Zufälliges Array generieren
    public static void generateRandomArray(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100); // Zufallszahlen zwischen 0 und 99
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Anzahl der Elemente: ");
        int n = scanner.nextInt();
        if (n <= 0) {
            System.out.println("Ungültige Eingabe. Anzahl der Elemente muss positiv sein.");
            return;
        }

        System.out.print("Möchtest du eine vorgefertigte Liste verwenden? (1 = Ja, 0 = Nein): ");
        int usePreset = scanner.nextInt();

        int[] array = new int[n];
        if (usePreset == 1) {
            generateRandomArray(array);
            System.out.println("Zufällige Liste wurde generiert: ");
            printArray(array);
        } else {
            System.out.println("Gib die Elemente ein:");
            for (int i = 0; i < n; i++) {
                System.out.print("Element " + (i + 1) + ": ");
                array[i] = scanner.nextInt();
            }
        }

        int[] arrayCopy = Arrays.copyOf(array, n);

        System.out.println("\nWähle eine Option: ");
        System.out.println("1. Bubble Sort ");
        System.out.println("2. Quick Sort ");
        System.out.println("3. Merge Sort ");
        System.out.println("4. Selection Sort");
        System.out.print("Deine Option: ");
        int choice = scanner.nextInt();

        double duration = 0.0;

        switch (choice) {
            case 1:
                System.out.println("\nBubble Sort wird ausgeführt...");
                duration = measureTime(() -> bubbleSort(arrayCopy));
                break;
            case 2:
                System.out.println("\nQuick Sort wird ausgeführt...");
                duration = measureTime(() -> quickSortWrapper(arrayCopy));
                break;
            case 3:
                System.out.println("\nMerge Sort wird ausgeführt...");
                duration = measureTime(() -> mergeSortWrapper(arrayCopy));
                break;
            case 4:
                System.out.println("\nSelection Sort wird ausgeführt...");
                duration = measureTime(() -> selectionSortWrapper(arrayCopy));
                break;
            default:
                System.out.println("Ungültige Option!");
                return;
        }

        System.out.println("Sortiertes Ergebnis: ");
        printArray(arrayCopy);
        System.out.printf("Sortieren dauerte %.6f Sekunden.%n", duration);
    }
}
