package ru.avk;

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
    private static String playerOneName = " ";
    private static char[][] field;                                                //Игровое поле;
    
    private static int scoreHuman = 0;
    private static int scoreAI = 0;
    
    public static void main(String[] args) {
        fieldSizeX = 3;                                                                  //Размер поля по Х;
        fieldSizeY = 3;
        
        while (true) {
            System.out.printf("%sПредставьтесь пожалуйста >>>>>> ", playerOneName);
            playerOneName = SCANNER.nextLine();
            initField();
            pintField();
            
            while (true) {
                humanTurn();
                pintField();
                if (gameCheck(DOT_HIMAN, String.format("%s, Вы великолепны!!!  Победа!", playerOneName))) break;
                aiTurn();
                pintField();
                if (gameCheck(DOT_AI, "Компьютер победил!!!")) break;
            }
            System.out.printf("SCORE IS:\n%s: %d || AI: %d\n", playerOneName, scoreHuman, scoreAI);
            System.out.println(("Сыграем еще? >>>> Y  или N"));
            if (!SCANNER.next().toLowerCase().equals("y")) break;                       // toLowerCase() ?
        }
    }
    
    private static boolean gameCheck(char dot, String s) {
        if (checkWin(dot)) {
            if (dot == DOT_HIMAN) {
                scoreHuman++;
            } else {
                scoreAI++;
            }
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

        for (int y = 0; y < fieldSizeY - 1; y++) {
            for (int x = 0; x < fieldSizeX - 1; x++) {
                int i;
                for (i = 0; i < fieldSizeX - 1; i++) {
                    if (field[y][x + i] != c && (fieldSizeX) != i+1) return false;
                }
            return true;
            }
        }
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
            System.out.printf("%s Введите координаты х и у через пробел >>>>>", playerOneName);
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

