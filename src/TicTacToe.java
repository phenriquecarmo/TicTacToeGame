import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        printBoard(board);

        while (true) {
            playerMove(board); // Player move
            printBoard(board);
            if (isGameFinished(board)) {
                break;
            }
            computerTurn(board); // Computer move
            if (isGameFinished(board)) {
                break;
            }
            printBoard(board);
        }
    }

    private static void computerMove(char[][] board, int position) {
        int row = ((position) - 1) / 3;
        int col = ((position) - 1) % 3;

        if (row >= 0 && row < 3 && col >= 0) {
            board[row][col] = '0';
        }
    }

    private static void playerMove(char[][] board) {
        String placeNumber;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Where would you like to play? (1-9)");
            placeNumber = scanner.nextLine();
            try {
                if (isSpaceAvailable(board, Integer.parseInt(placeNumber))) {
                    break;
                } else {
                    System.out.println(placeNumber + " is already occupied!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        int row = (Integer.parseInt(placeNumber) - 1) / 3;
        int col = (Integer.parseInt(placeNumber) - 1) % 3;

        if (row >= 0 && row < 3 && col >= 0) {
            board[row][col] = 'X';
        } else {
            System.out.println("Please type a number between 1 and 9");
        }

    }


    private static boolean hasContestantWon(char[][] board, char symbol) {
             if ((board[0][0] == symbol && board [0][1] == symbol && board [0][2] == symbol) ||
                (board[1][0] == symbol && board [1][1] == symbol && board [1][2] == symbol) ||
                (board[2][0] == symbol && board [2][1] == symbol && board [2][2] == symbol) ||

                (board[0][0] == symbol && board [1][0] == symbol && board [2][0] == symbol) ||
                (board[0][1] == symbol && board [1][1] == symbol && board [2][1] == symbol) ||
                (board[0][2] == symbol && board [1][2] == symbol && board [2][2] == symbol) ||

                (board[0][0] == symbol && board [1][1] == symbol && board [2][2] == symbol) ||
                (board[0][2] == symbol && board [1][1] == symbol && board [2][0] == symbol) ) {
            return true;
        }
        return false;
    }

    private static Boolean isGameFinished(char[][] board) {

        if (hasContestantWon(board, 'X')) {
            printBoard(board);
            System.out.println("Player Wins!");
            return true;
        }

        if (hasContestantWon(board, '0')) { // The code is not working to get computer wins, try to understand why
            printBoard(board);
            System.out.println("Computer Wins!");
            return true;
        }


        for (char[] chars : board) {
            for (int j = 0; j < board.length; j++) {
                if (chars[j] == ' ') {
                    return false;
                }
            }
        }

        System.out.println("The game ended in a Tie!");

        return true;
    }

    private static void computerTurn(char[][] board) {
        Random rand = new Random();
        int computerMove;
        while(true) {
            computerMove = rand.nextInt(9) + 1; //nextInt returns a number between zero and one less than that number" that is the reason of the "+1" here
            if (isSpaceAvailable(board, computerMove)) {
                break;
            }
        }
        System.out.println("Computer chose " + computerMove);
        computerMove(board, computerMove);
    }

    private static boolean isSpaceAvailable(char[][] board, int position) {
        int row = (position - 1) / 3; // dividing the shifted position by 3 to determine the row index bc there are 3 rows in the array
        int col = (position - 1) % 3; // calculating column index using position bc there are 3 columns in the array
        return switch (position) {
            case 1, 2, 3, 4, 5, 6, 7, 8, 9 -> board[row][col] == ' '; // returns true if block is empty depending on position
            default -> {
                yield false;
            }
        };
    }


    private static void printBoard(char[][] board) {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }







}
