public class Converter {
    double stepLength = 0.00075;    // в км
    double stepKilo = 0.05;     // в ккал

    double convert2km(int steps) {
        return steps * stepLength;
    }

    double convert2kKal(int steps) {
        return steps * stepKilo;
    }
}
