#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Funktionsdeklarationen
void bubbleSort(int arr[], int n);
void quickSort(int arr[], int low, int high);
void mergeSort(int arr[], int left, int right);
void quickSortWrapper(int arr[], int n);
void mergeSortWrapper(int arr[], int n);
double measureTime(void (*sortAlgorithm)(int[], int), int arr[], int n);
void printArray(int arr[], int n);
int choice();
void generateRandomArray(int arr[], int n);

int main() {
    int n;
    int usePreset;

    // Eingabe für die Anzahl der Elemente
    printf("Anzahl der Elemente: ");
    scanf("%d", &n);

    if (n <= 0) {
        printf("Ungültige Eingabe.\n");
        return 1;
    }

    // Frage, ob der Benutzer eine vorgefertigte Liste möchte
    printf("Möchtest du eine vorgefertigte Liste verwenden? (1 = Ja, 0 = Nein): ");
    scanf("%d", &usePreset);

    int array[n];
    if (usePreset) {
        // Zufällige Liste generieren
        generateRandomArray(array, n);
        printf("Zufällige Liste wurde generiert: ");
        printArray(array, n);
    } else {
        // Benutzerdefinierte Werte eingeben
        printf("Gib die Elemente ein:\n");
        for (int i = 0; i < n; i++) {
            printf("Element %d: ", i + 1);
            scanf("%d", &array[i]);
        }
    }

    // Kopie des Arrays für Mehrfachsortierungen
    int arrayCopy[n];

    int auswahl = choice();

    // Zeitmessung und Sortierung basierend auf der Auswahl
    double duration = 0.0;
    if (auswahl == 1) {
        printf("\nBubble Sort wird ausgeführt...\n");
        for (int i = 0; i < n; i++) {
            arrayCopy[i] = array[i];
        }
        duration = measureTime(bubbleSort, arrayCopy, n);
        printf("Sortiertes Ergebnis: ");
        printArray(arrayCopy, n);
    } else if (auswahl == 2) {
        printf("\nQuick Sort wird ausgeführt...\n");
        for (int i = 0; i < n; i++) {
            arrayCopy[i] = array[i];
        }
        duration = measureTime(quickSortWrapper, arrayCopy, n);
        printf("Sortiertes Ergebnis: ");
        printArray(arrayCopy, n);
    } else if (auswahl == 3) {
        printf("\nMerge Sort wird ausgeführt...\n");
        for (int i = 0; i < n; i++) {
            arrayCopy[i] = array[i];
        }
        duration = measureTime(mergeSortWrapper, arrayCopy, n);
        printf("Sortiertes Ergebnis: ");
        printArray(arrayCopy, n);
    }

    printf("Sortieren dauerte %.6f Sekunden.\n", duration);

    return 0;
}

// Bubble Sort Algorithmus
void bubbleSort(int arr[], int n) {
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

// Partition für QuickSort
int partition(int arr[], int low, int high) {
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

// QuickSort Algorithmus
void quickSort(int arr[], int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

// Wrapper für QuickSort
void quickSortWrapper(int arr[], int n) {
    quickSort(arr, 0, n - 1);
}

// Merge Sort Hilfsfunktion
void merge(int arr[], int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int L[n1], R[n2];

    for (int i = 0; i < n1; i++) L[i] = arr[left + i];
    for (int i = 0; i < n2; i++) R[i] = arr[mid + 1 + i];

    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) arr[k++] = L[i++];
        else arr[k++] = R[j++];
    }
    while (i < n1) arr[k++] = L[i++];
    while (j < n2) arr[k++] = R[j++];
}

// MergeSort Algorithmus
void mergeSort(int arr[], int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}

// Wrapper für MergeSort
void mergeSortWrapper(int arr[], int n) {
    mergeSort(arr, 0, n - 1);
}

// Zeitmessungsfunktion
double measureTime(void (*sortAlgorithm)(int[], int), int arr[], int n) {
    clock_t start, end;
    start = clock(); // Startzeit
    sortAlgorithm(arr, n); // Sortieralgorithmus ausführen
    end = clock(); // Endzeit

    return (double)(end - start) / CLOCKS_PER_SEC; // Zeit in Sekunden
}

// Array-Druckfunktion
void printArray(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

// Funktion zur Benutzerauswahl
int choice() {
    int option;
    printf("\nWähle eine Option: \n");
    printf("1. Bubble Sort \n");
    printf("2. Quick Sort \n");
    printf("3. Merge Sort \n");
    printf("Deine Option: ");
    scanf("%d", &option);

    if (option < 1 || option > 3) {
        printf("Ungültige Eingabe. Bitte wähle zwischen 1 und 3.\n");
        return choice();
    }
    return option;
}

// Funktion zur Generierung zufälliger Arrays
void generateRandomArray(int arr[], int n) {
    srand(time(0)); // Initialisiere den Zufallsgenerator
    for (int i = 0; i < n; i++) {
        arr[i] = rand() % 100; // Zufallszahlen zwischen 0 und 99
    }
}

