import java.io.*; //Input and Output Library
import java.util.*; //package containing hasmap, hashtable, linkedlist, arraylist, ...
/*
    David Nong-Ang
    01/03/2023
    Battleship Game
    *description*
 */

class Main {

    //Constant Variables
    public static final String[] LETTERS = {"    A", " B", " C", " D", " E", " F", " G", " H", " I"};
    public static final String[] NUMBERS = {"1| ", "2| ", "3| ", "4| ", "5| ", "6| ", "7| ", "8| ", "9| "};

    //Public Variables
    public static String[][] board1_1 = new String[9][9];
    public static String[][] board1_2 = new String[9][9];
    public static Scanner input = new Scanner(System.in);
    public static String userInput = null;
    public static int menuChoice = 0;
    public static boolean menuError = false;
    public static int[] startCord = new int[2], endCord = new int[2], hitCord = new int[2];
    public static int shipCount = 0;
    public static boolean p1 = true, p2 = true;
    public static boolean hit = false;
    public static int p1Hits = 0, p2Hits = 0, p1Score = 0, p2Score = 0;
    public static String[] lineArray = null;

    public static void main(String[] args) {
        //Local main Variables
        int turn = 1;
        String line = null;

        //Use try and catch to read and write "names" file for player's names
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("names.csv")); //Create BufferedWriter object to write to the file.

            //Ask the user for the player's names
            System.out.println("Please Enter Player One's Name: ");
            String playerOne = input.nextLine();
            System.out.println("Please Enter Player Two's Name: ");
            String playerTwo = input.nextLine();

            //store the usernames to the file
            bw.write(playerOne + "," + playerTwo + ","); //writes the names to the file followed by commas.
            bw.newLine(); //creates a new line (similar to \n)
            bw.close(); //closes the BufferedWriter

            BufferedReader br = new BufferedReader(new FileReader("names.csv")); //Creates a BufferedReader object to read the file.
            line = br.readLine(); //Reads the string

            while(line != null){
                lineArray = line.split(","); //splits the current line into an array of string using , as the delimiter.
                line = br.readLine(); //reads the String.
            }
            br.close(); //closes the BufferedReader

        }catch(IOException er){
            System.out.println("Invalid Name"+er.getMessage());
        }

        //Loop for main menu screen.
        do{
            menuChoice = Functions.printMenuScreen(); //call to the function that displays the menu and store the user's choice into a variable.
            //Start the game.
            if (menuChoice == 1) {

                //Variables
                shipCount = 0;
                board1_1 = new String[9][9];
                board1_2 = new String[9][9];

                //Create Game boards for each player
                for(int i = 0; i < board1_1.length; i++){
                    for(int z = 0; z < board1_1[i].length; z++){
                        if(board1_1[i][z] == null && board1_2[i][z] == null){
                            board1_1[i][z] = "⬜";
                            board1_2[i][z] = "⬜";
                        }
                    }
                }

                //Write blank boards to files
                Functions.writeFile("board1_1.csv", board1_1);
                Functions.writeFile("board1_2.csv", board1_2);
                Functions.writeFile("board2_1.csv", board1_1);
                Functions.writeFile("board2_2.csv", board1_2);

                //Draw the boards
                board1_1 = Functions.readFile("board1_1.csv");
                board1_2 = Functions.readFile("board1_2.csv");
                Functions.printBoard(board1_1, board1_2);

                Functions.shipPlacementsP1();
                Functions.shipPlacementsP2();

                while(p1 && p2){
                    if(turn == 1 && p1 && p2){
                        Functions.switchToPlayerOne();

                        System.out.println("Player One \n");
                        System.out.println("Please enter a coordinate to shoot at: ");
                        Functions.playerOneTurn();
                        turn  = 2;
                        board1_1 = Functions.readFile("board1_1.csv");
                        board1_2 = Functions.readFile("board1_2.csv");
                        Functions.printBoard(board1_1,board1_2);
                    }

                    if(p1Hits == 10)
                        p2 = false;
                    if (p2Hits == 10)
                        p1 = false;

                    if(turn == 2 && p1 && p2){
                        Functions.switchToPlayerTwo();

                        System.out.println("Player Two \n");
                        System.out.println("Please enter a coordinate to shoot at: ");
                        Functions.playerTwoTurn();
                        turn = 1;
                        board1_1 = Functions.readFile("board2_1.csv");
                        board1_2 = Functions.readFile("board2_2.csv");
                        Functions.printBoard(board1_1,board1_2);
                    }

                    if(p1Hits == 10)
                        p2 = false;
                    if (p2Hits == 10)
                        p1 = false;
                }
                //Check if player one won.
                if(p1){
                    Functions.printEndScreenOne(); //display the winner screen for player one.
                    break; //end the loop
                }
                //Check if player two won.
                if(p2){
                    Functions.printEndScreenTwo(); //display the winner screen for player two.
                    break; //end the loop
                }
            }else if(menuChoice == 2){ //Display the information screen for the game.
                Functions.printInfo(); //Function to display the info.
            }else if(menuChoice == 3){ //Exit the program.
                System.out.println("Thank you for playing! Have a good day. ");
                break;
            }else{
                System.out.println("User's input is invalid, try again!");
            }

        }while(menuChoice != 0);

    }
}