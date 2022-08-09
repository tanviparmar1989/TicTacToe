import java.util.Scanner;

public class TicTacToeCLI {

    public static void main(String[] args) {
        TicTacToeCLI ticTacToe = new TicTacToeCLI();
        System.out.println("Welcome to TicTacToe!");

        Scanner in = new Scanner(System.in);

        //initialize to class level.
        //move the array from main.
        // iterate
        char[][] ticTacToeArray = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        ticTacToe.displayBoard(ticTacToeArray);


//        System.out.println("Player1 - Please enter your name");
//        String player1Name = in.nextLine();


//        System.out.println("Player2 - Please enter your name");
//        String player2Name = in.nextLine();
        //give player1 = x, player2 =0


        // keep asking till game is not ended
        boolean gameEnded = false;

        boolean isPlayer1 = true;

        while (!gameEnded) {
            // Get the player symbol; It will be X and then O and so on.
            char playerSymbol = isPlayer1 ? 'X' : 'O';
            System.out.println("Player " + playerSymbol + ". Please play.");

            // Get the location where player wants to play
            int playerLocation = in.nextInt();
            boolean isValidLoc = ticTacToe.checkValidLocation(ticTacToeArray, playerLocation);

            // If the location is not valid then ask player to chose again.
            if (!isValidLoc) {
                System.out.println("Location is not valid. Please try again.");
                continue;
            }


            // update the board
            ticTacToe.updateBoard(ticTacToeArray, playerLocation, playerSymbol);

            // display the board
            ticTacToe.displayBoard(ticTacToeArray);

            //if player won
            boolean playerWon = ticTacToe.winnerCondition(ticTacToeArray);
            if (playerWon) {
                System.out.println("Player " + playerSymbol + " wins the game.");
                gameEnded = true;
            }

            //if board is full
            boolean isBoardFull = ticTacToe.isBoardFull(ticTacToeArray);
            if (isBoardFull) {
                System.out.println("Its a tie, board is full");
                gameEnded = true;
            }

            // change the player
            // if isPlayer1 is true then it becomes false
            // if isPlayer1 is false then it becomes true
            isPlayer1 = !isPlayer1;
        }

    }

    private void displayBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col] + "|");
            }
            System.out.println();
        }
    }

    private void updateBoard(char[][] board, int playerLocation, char playerSymbol) {
        int row = getRow(playerLocation - 1);
        int col = getCol(playerLocation - 1);
        board[row][col] = playerSymbol;
    }

    private int getCol(int i) {
        return (i) % 3;
    }

    private int getRow(int i) {
        return (i) / 3;
    }

    private boolean checkValidLocation(char[][] board, int playerLocation) {
        if (playerLocation < 1 || playerLocation > 9) {
            System.out.println("Invalid number, Please choose the number between 1-9");
            return false;
        }

        int row = getRow(playerLocation - 1);
        int col = getCol(playerLocation - 1);
        if (board[row][col] == 'X' || board[row][col] == 'O') {
            return false;
        }

        return true;
    }

    private boolean winnerCondition(char[][] board) {
        //check each row
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && (board[row][0] == 'X' || board[row][0] == '0')) {
                return true;
            }
        }
        // check each column
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && (board[0][col] == 'X' || board[0][col] == 'O')) {
                return true;
            }
        }
        // check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && (board[0][0] == 'X' || board[0][0] == 'O')) {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && (board[0][2] == 'X' || board[0][2] == 'O')) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 'X' || board[row][col] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }
}
