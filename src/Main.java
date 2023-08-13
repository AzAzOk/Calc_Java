import java.util.Scanner;

class Main {
    public static String calc(String input) {
        String[] elements = input.split(" ");
        if (elements.length != 3) {
            throw new IllegalArgumentException("неправильный формат ввода операции");
        }
        int a = parseNumber(elements[0]);
        int b = parseNumber(elements[2]);
        checkRange(a);
        checkRange(b);
        int result;
        switch (elements[1]) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> {
                if (b == 0) {
                    throw new IllegalArgumentException("нельзя делить на ноль");
                }
                result = a / b;
            }
            default -> throw new IllegalArgumentException("неправильный формат ввода операции");
        }
        return formatResult(result, isRoman(elements[0]));
    }

    private static int parseNumber(String s) {
        if (isRoman(s)) {
            return romanToArabic(s);
        } else {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("неправильный формат ввода числа");
            }
        }
    }

    private static void checkRange(int a) {
        if (a < 1 || a > 10) {
            throw new IllegalArgumentException("числа должны быть от 1 до 10 включительно");
        }
    }

    private static boolean isRoman(String s) {
        return s.equals("I") || s.equals("II") || s.equals("III") || s.equals("IV") || s.equals("V") ||
                s.equals("VI") || s.equals("VII") || s.equals("VIII") || s.equals("IX") || s.equals("X");
    }

    private static String formatResult(int result, boolean roman) {
        if (roman) {
            if (result < 1) {
                throw new IllegalArgumentException("результат не может быть меньше 1");
            }
            return toRoman(result);
        } else {
            return Integer.toString(result);
        }
    }

    private static String toRoman(int n) {
        if (n < 1 || n > 3999) {
            throw new IllegalArgumentException("неправильное число для римской системы");
        }
        StringBuilder sb = new StringBuilder();
        while (n >= 1000) {
            sb.append("M");
            n -= 1000;
        }
        if (n >= 900) {
            sb.append("CM");
            n -= 900;
        }
        while (n >= 500) {
            sb.append("D");
            n -= 500;
        }
        if (n >= 400) {
            sb.append("CD");
            n -= 400;
        }
        while (n >= 100) {
            sb.append("C");
            n -= 100;
        }
        if (n >= 90) {
            sb.append("XC");
            n -= 90;
        }
        while (n >= 50) {
            sb.append("L");
            n -= 50;
        }
        if (n >= 40) {
            sb.append("XL");
            n -= 40;
        }
        while (n >= 10) {
            sb.append("X");
            n -= 10;
        }
        if (n == 9) {
            sb.append("IX");
            n = 0;
        }
        while (n >= 5) {
            sb.append("V");
            n -= 5;
        }
        if (n == 4) {
            sb.append("IV");
            n = 0;
        }
        while (n >= 1) {
            sb.append("I");
            n -= 1;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        try {
            String result = calc(input);
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ответ: " + e.getMessage());
        }
    }
    private static int romanToArabic(String romanNumeral) {
        int result = 0;
        String romanNumeralUpper = romanNumeral.toUpperCase();
        for (int i = 0; i < romanNumeralUpper.length(); i++) {
            System.out.println(romanNumeralUpper.length());
            char currentChar = romanNumeralUpper.charAt(i);
            if (i > 0 && romanNumeralValues(currentChar) > romanNumeralValues(romanNumeralUpper.charAt(i - 1))) {
                result += romanNumeralValues(currentChar) - 2 * romanNumeralValues(romanNumeralUpper.charAt(i - 1));
            } else {
                result += romanNumeralValues(currentChar);
            }
        }
        return result;
    }

    private static int romanNumeralValues(char romanNumeral) {
        return switch (romanNumeral) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> throw new IllegalArgumentException("Неправильный символ римского числа: " + romanNumeral);
        };
    }
}