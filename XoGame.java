/**
 * This game is a single player game that we can play competitively with the console.
 * @author Aadhith Kumaresan
 * @version 1.0
 * @since 2022-02-12
 */

import java.util.*;

public class XoGame {
    // Array list to store the users and cpu input
    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    public static void main(String[] args) {
        // 2d array to locate the game board
        char[][] gameboard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        // calling the method to print the board game
        printGameBoard(gameboard);
        // conditions and user input for the player
        while (true) {
            Scanner kb = new Scanner(System.in);
            System.out.println("enter your placement (1-9):");
            int playerPos = kb.nextInt();

            while (playerPosition.contains(playerPos) || cpuPosition.contains(playerPosition)) {
                System.out.println("Position taken! Enter a correct position");
                playerPos = kb.nextInt();
            }

            placePiece(gameboard, playerPos, "player");
            //using random class to make the cpu play
            Random rand = new Random();
            int cpuPas = rand.nextInt(9) + 1;
            // condition to prevent the error of entering a existing place.
            while (playerPosition.contains(cpuPas) || cpuPosition.contains(cpuPas)) {
                System.out.println("Position taken! Enter a correct position");
                cpuPas = rand.nextInt(9) + 1;
            }
            placePiece(gameboard, cpuPas, "cpu");

            printGameBoard(gameboard);

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }


    }

    // method to print board game
    public static void printGameBoard(char[][] gameboard) {
        for (char[] row : gameboard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    // method to place the pieces on the board
    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'x';
            playerPosition.add(pos);
        } else if (user.equals("cpu")) {
            symbol = '0';
            cpuPosition.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    // method to check the winner
    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midtCol = Arrays.asList(2, 5, 8);
        List righttCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(righttCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPosition.containsAll(l)) {
                return "Congratulations you won!";
            } else if (cpuPosition.containsAll(l)) {
                return "CPU wins! Sorry :(";
            } else if (playerPosition.size() + cpuPosition.size() == 9) {
                return "CAT!";
            }

        }
        return "";
    }

}

