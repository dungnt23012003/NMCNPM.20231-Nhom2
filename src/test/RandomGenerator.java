package src.test;

public class RandomGenerator {
    private static int randomInt(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    private static String randomCCCD() {
        StringBuilder result = new StringBuilder("001");
        result.append(randomInt(0, 3));

        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));

        result.append(0);
        result.append(0);

        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));

        return String.valueOf(result);
    }

    private static String randomHK() {
        StringBuilder result = new StringBuilder();

        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));
        result.append(randomInt(0, 9));

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; ++i) {
            System.out.println(randomHK());
        }
    }
}
