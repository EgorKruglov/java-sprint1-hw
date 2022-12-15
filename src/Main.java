import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                stepTracker.memorySteps(scanner, stepTracker);
            } else if (command == 2) {
                stepTracker.doStat(scanner, stepTracker);
            } else if (command == 3) {
                stepTracker.changeTarget(scanner, stepTracker);
            } else if (command == 4) {
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }

    }

    public static void printMenu() {
        System.out.println("\nЧто вы хотите сделать?");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель количества шагов в день");
        System.out.println("4 - Выйти из приложения");
    }
}
