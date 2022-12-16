import java.util.Scanner;

public class StepTracker {
    MonthData[] monthToData;    // Объявим массив месяцев (нашего типа)
    int myTarget = 10000;       // Цель

    public StepTracker() {      // Это конструктор
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    class MonthData {       //  Это подкласс. Каждый объект имеет массив 30 элементов.
        int[] days = new int[30];

        MonthData () {
            for (int i = 0; i < days.length; i++) {
                days[i] = 0;    // По умолчанию кол-во шагов в днях 0
            }
        }
    }

    void memorySteps(Scanner scanner, StepTracker stepTracker) {    // Запомнить кол-во шагов в день

        int month = 0;
        int day = 0;
        int steps = 0;

        while (true) {      // Пока не корректный ввод месяца
            System.out.println("В каком месяце запомнить шаги?");
            System.out.println("0 - Январь " + "1 - Февраль " + "2 - Март " + "3 - Апрель\n"
                    + "4 - Май " + "5 - Июнь " + "6 - Июль " + "7 - Август\n"
                    + "8 - Сентябрь " + "9 - Октябрь " + "10 - Ноябрь " + "11 - Декабрь");
            month = scanner.nextInt();
            if ((month > -1) && (month < 12)) {
                break;
            } else {
                System.out.println("Некорректный ввод");
            }
        }

        while (true) {      // Пока не корректный ввод дня
            System.out.println("За какое число? (0 - 29)");
            day = scanner.nextInt();
            if ((day > -1) && (day < 30)) {
                break;
            } else {
                System.out.println("Некорректный ввод");
            }
        }

        while (true) {
            System.out.println("Какое количество шагов?");
            steps = scanner.nextInt();
            if (steps > -1) {
                break;
            } else {
                System.out.println("Некорректный ввод");
            }
        }

        stepTracker.monthToData[month].days[day] = steps;
        System.out.println("Сохранено шагов " + stepTracker.monthToData[month].days[day] + "!");
    }

    void changeTarget(Scanner scanner, StepTracker stepTracker) {

        System.out.println("Старая цель количества шагов " + stepTracker.myTarget);
        int localTarget = 0;

        while (true) {
            System.out.println("Какая новая цель?");
            localTarget = scanner.nextInt();
            if (localTarget > -1) {
                break;
            } else {
                System.out.println("Некорректный ввод");
            }
        }
        stepTracker.myTarget = localTarget;
        System.out.println("Новая цель количества шагов в день " + stepTracker.myTarget + "!");
    }

    void doStat(Scanner scanner, StepTracker stepTracker) {

        Converter converter = new Converter();

        int month = 0;
        int stepSum = 0;
        int maxStep = 0;
        double averageStep = 0;
        int winRange = 0;
        int winRangeOld = 0;    // Доп переменная для нахождения лучшей серии

        while (true) {      // Пока не корректный ввод месяца
            System.out.println("За какой месяц?");
            System.out.println("0 - Январь " + "1 - Февраль " + "2 - Март " + "3 - Апрель\n"
                    + "4 - Май " + "5 - Июнь " + "6 - Июль " + "7 - Август\n"
                    + "8 - Сентябрь " + "9 - Октябрь " + "10 - Ноябрь " + "11 - Декабрь");
            month = scanner.nextInt();
            if ((month > -1) && (month < 12)) {
                break;
            } else {
                System.out.println("Некорректный ввод");
            }
        }
        System.out.println("Статистика за месяц " + month + ":");

        for (int i = 0; i < stepTracker.monthToData[month].days.length; i++) {
            System.out.print(i + " день: " + stepTracker.monthToData[month].days[i] + ", ");    // Вывод статистики за дни месяца
            stepSum += stepTracker.monthToData[month].days[i];      // Находим сумму шагов за месяц
            if (maxStep < stepTracker.monthToData[month].days[i]) {     // Находим макс кол-во шагов за месяц
                maxStep = stepTracker.monthToData[month].days[i];
            }

            if (stepTracker.monthToData[month].days[i] >= stepTracker.myTarget) {       // Находим лучшую серию
                winRange += 1;
            } else {
                if (winRange > winRangeOld) {
                    winRangeOld = winRange;
                }
                winRange = 0;
            }
        }
        averageStep = stepSum / stepTracker.monthToData[month].days.length;     // Находим среднее кол-во шагов в день. Тут надо посмотреть, надо ли парсить.
        if (winRangeOld > winRange) {       // Чтобы значение лучшей серии всегда было в winRange.
            winRange = winRangeOld;
        }
        System.out.println("\nКоличество шагов за месяц " + stepSum + "!");
        System.out.println("Максимальное количество шагов в день " + maxStep + "!");
        System.out.println("Среднее количество шагов в день " + averageStep + "!");
        System.out.println("Пройденная дистанция " + converter.convert2km(stepSum) + " км!");
        System.out.println("Сожжено " + converter.convert2kKal(stepSum) + " килокалорий!");
        System.out.println("Ваша лучшая серия побед " + winRange + "!");
    }
}
