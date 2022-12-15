public class Converter {
    double stepLenght = 0.00075;    // в км
    double stepKilo = 0.05;     // в ккал

    double getKm(int steps) {
        return steps * stepLenght;
    }

    double getKilo(int steps) {
        return steps * stepKilo;
    }
}
