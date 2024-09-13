package org.example;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("введите первое число: ");
        int operand1 = scanner.nextInt();
        System.out.println();
        System.out.print("введите оператор: ");
        char operation = scanner.next().charAt(0);
        System.out.println();
        System.out.print("введите второе число: ");
        int operand2 = scanner.nextInt();
        System.out.print("Результат: ");

        switch (operation){
            case '+' -> add(operand1, operand2);
            case '-' -> subtract(operand1, operand2);
            case '*' -> multiply(operand1, operand2);
            case '/' -> divide(operand1, operand2);
            default -> System.out.println("недопустимый оператор");
        }

    }

    private static void divide(int a, int b) {
        if (b != 0) {
            System.out.println(a / b);
        } else {
            System.out.println("Ошибка: деление на ноль");
        }
    }

    private static void multiply(int a, int b) {
        System.out.println(a*b);
    }

    private static void subtract(int a, int b) {
        System.out.println(a-b);
    }

    private static void add(int a, int b) {
        System.out.println(a+b);
    }
}
