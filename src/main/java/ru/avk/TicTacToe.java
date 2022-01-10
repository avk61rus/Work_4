package ru.avk;

import java.awt.datatransfer.StringSelection;
import java.util.Random;
import java.util.Scanner;

/**
 * Tic - tac - toe
 */
public class TicTacToe {

    private static final Random RANDOM = new Random();                            //Случайный выбор;
    private static final Scanner SCANNER = new Scanner(System.in);                //Получение данных от игрока;
    private static final char DOT_HIMAN = 'X';                                    //Фишка игрока;
    private static final char DOT_AI = '0';                                       //Фишка компьютера;
    private static final char DOT_EMPTY = '.';                                    //Пустое поле;
    private static int fieldSizeX;                                                //Объявляем размер поля по оси Х;
    private static int fieldSizeY;                                                //Объявляем размер поля по оси Y;

    private static char[][] field;                                                //Игровое поле;

    public static void main(String[] args) {
        fieldSizeX = 3;                                                                  //Размер поля по Х;
        fieldSizeY = 3;
        while (true) {
            initField();
            pintField();

            while (true) {
                humanTurn();
                pintField();
                if (gameCheck(DOT_HIMAN, "Вы великолепны!!!  Победа!")) break;
                aiTurn();
                pintField();
                if (gameCheck(DOT_AI, "Компьютер победил!!!")) break;
            }
            System.out.println(("Сыграем еще? >>>> Y  или N"));
            if (!SCANNER.next().toLowerCase().equals("y")) break;                       // toLowerCase() ?
        }
    }

    private static boolean gameCheck(char dot, String s) {
        if (checkWin(dot)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("DRAW!!!");
            return true;
        }
        return false;
    }

    private static boolean checkWin(char c) {
        //horiz
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        //vert
        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        //Diag
        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[2][0] == c && field[1][1] == c && field[0][2] == c) return true;
        return false;
    }

    private static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));

        field[y][x] = DOT_AI;
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты х и у через пробел >>>>>");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));

        field[y][x] = DOT_HIMAN;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && y >= 0 && x < fieldSizeY && y < fieldSizeY;
    }


    private static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private static void initField() {                                                     //Инициализируем угровое поле
        field = new char[fieldSizeY][fieldSizeX];                                           //представляющее собой char массив;
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void pintField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++)
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        System.out.println();

        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < fieldSizeX; j++)
                System.out.print(field[i][j] + "|");
            System.out.println();
        }
        for (int k = 0; k < fieldSizeX * 2 + 1; k++)
            System.out.print("-");
        System.out.println();


    }


}

