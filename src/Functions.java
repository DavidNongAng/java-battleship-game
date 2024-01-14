/*
    David Nong-Ang
    01/03/2023
    Functions File
    *description*
 */
import java.io.*; //Input and Output Library
import java.util.*; //package containing hasmap, hashtable, linkedlist, arraylist, ...

public class Functions {

    //This function takes no parameters and returns an integer for the user's choice.
    //This function displays the main menu screen and asks the user for their choice to continue the program.
    public static int printMenuScreen(){
        System.out.println("Welcome to Battleship Java Console: \n");
        System.out.println();
        System.out.println("                                     # #  ( )");
        System.out.println("                                  ___#_#___|__");
        System.out.println("                              _  |____________|  _");
        System.out.println("                       _=====| | |            | | |==== _");
        System.out.println("                 =====| |.---------------------------. | |====");
        System.out.println("   <--------------------'   .  .  .  .  .  .  .  .   '--------------/");
        System.out.println("    \\                                                              /");
        System.out.println("     \\                     ||BATTLESHIP GAME||                    /");
        System.out.println("      \\__________________________________________________________/");
        System.out.println();
        System.out.println("1. Play");
        System.out.println("2. How to Play");
        System.out.println("3. Quit \n");
        Main.userInput = Main.input.nextLine();
        Main.menuChoice = Integer.parseInt(Main.userInput);
        System.out.println(Main.menuChoice);
        return Main.menuChoice;
    }

    //This function displays the game details and tells the user how to play.
     public static void printInfo(){
        System.out.println("Game Information: ");
        System.out.println("When the game starts, each player places 5 boats by typing in the start and end coordinates. (EX. A1 and A2)");
        System.out.println("They will take turns guessing the coordinates of the other player's boats until one side has no more left.\n");
        System.out.println("Symbols: \n⬜ represents an empty coordinate on the board.\nO represents a coordinate that had been shot.\nX represents a coordinate of a ship that is destroyed.\n(1-5) represents a coordinate with a ship.\n");
     }

     //This function takes 2 2D String arrays as its parameters.
     //This function displays the game board.
     public static void printBoard(String[][] board1_1, String[][] board1_2){
        System.out.println("       Enemy Board");
        for(int i = 0; i < board1_1[0].length; i++){ //loop through the first array of the 2D array.
            System.out.print(Main.LETTERS[i]); //display the letters in a row.
        }
        System.out.println();
        for(int i = 0; i < board1_1.length; i++){ //Loop through each row of the board.
            System.out.print(Main.NUMBERS[i]); //display the number of the row.
            for(int z = 0; z < board1_1[i].length; z++){ //Loop through each column of the board.
                System.out.print(board1_1[i][z]); //display what is in the board, ⬜, O , or X.
            }
            System.out.println();
        }
        System.out.println("-----------------------");

        for(int i = board1_2.length - 1; i > -1; i--){ //Loops through each row of the board.
            System.out.print(Main.NUMBERS[i]); //display the number of the row.
            for(int z = board1_2.length - 1; z > -1; z--){ // Loop through each column of the board.
                System.out.print(board1_2[i][z]); //display what is in the board, ⬜, O , or X.
            }
            System.out.println();
        }
        for(int i = 0; i < board1_2[0].length; i++){  //loop through the first array of the 2D array.
            System.out.print(Main.LETTERS[i]); //display the letters in a row.
        }
         System.out.println();
         System.out.println("       Your Side");
         System.out.println();
     }

     //This function takes the string storing the file name storing the board and a string 2d array as the parameters.
     //This function takes the 2d array and stores it into the file through the filename.
     public static void writeFile(String fileName, String[][] board){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for(int i = 0; i < board.length; i++){
                for(int z = 0; z < board[i].length; z++){
                    bw.write(board[i][z] + ",");
                }
                bw.write("\n");
            }
            bw.close();
        }catch(IOException er){
            System.out.println("Sorry there was an error");
        }
     }

     //This function takes the string storing the file name as the parameter and returns an array.
     //This function reads through the file, and stores each line in an array.
     public static String[][] readFile(String fileName){
        try{
            String line = null;
            String lineArray[] = new String[9];

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String[][] board = new String[9][9];

            for(int i = 0; i < board.length; i++){
                line = br.readLine();
                lineArray = line.split(",");
                board[i] = lineArray;
            }
            br.close();
            return board;
        }catch (IOException er){
            System.out.println("Sorry there was an error");
            return null;
        }
     }

     //This function takes a string storing the coordinate and returns an integer array.
     //This function takes the coordinate and divides the letter and number, storing it in an integer array.
     public static int[] coords(String coordinates){
        HashMap <String, Integer> map = new HashMap <>(); //create a hashmap with letters corresponding with the number value.
        int[] returnCords = {0,0};
        String letter = coordinates.substring(0,1); //takes the first index of the string, which is the Letter.
        letter = letter.toLowerCase(); //change it to lower case.
        int num = Integer.parseInt(coordinates.substring(1,2)); //takes the second index of the string, which is the number and convert it into an int.
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);
        map.put("e",5);
        map.put("f",6);
        map.put("g",7);
        map.put("h",8);
        map.put("i",9);
        returnCords[0] = map.get(letter) - 1; //get the coordinate value corresponding with the letter and -1 to get its index.
        returnCords[1] = num - 1; //get the
        return returnCords;
     }

     //This function takes a string storing the coordinate and returns a boolean.
     //This function makes sure the coordinates inputted by the user is in the form of letter and number (A1 or B2) and in between the given range A - I and 1 - 9.
     public static boolean coordInputCheck(String coordinates) {
        //Create variables
         HashMap<String, Integer> map = new HashMap<>();
         String letter = null;
         int num = 0;
         Integer check = null;
         int valid = 0;

         //check if the user entered a coordinate that is not 2 values.
         if (coordinates.length() != 2) {
             return false;
         }
         letter = coordinates.substring(0, 1); //first index is the letter.
         letter = letter.toLowerCase(); //change it to lower case.

         try {
             num = Integer.parseInt(coordinates.substring(1, 2)); //second index is a number, and convert it into an int.
             if (num > 9 || num < 1) { //check if its between 1 and 9.
                 return false;
             } else {
                 valid++; //add value to make sure it's in the right form (letter and number)
             }
         } catch (NumberFormatException er) {
             return false;
         }
         map.put("a", 1);
         map.put("b", 2);
         map.put("c", 3);
         map.put("d", 4);
         map.put("e", 5);
         map.put("f", 6);
         map.put("g", 7);
         map.put("h", 8);
         map.put("i", 9);
         check = map.get(letter);
         if (check != null) //check if the letter and its value is between 1 and 9.
             valid++; //add value to make sure it's in the right form.
         if (valid == 2) //check if the form is correct.
             return true;
         else
             return false;
     }

     //This function takes 2 int arrays representing the start and end coordinate and returns a boolean.
     //This function checks if the starting coordinate of the ship is side by side or diagonal with the ending coordinate of the ship
     public static boolean coordValidator(int[] startCoord, int[] endCoord){
        if((startCoord[0] - endCoord[0] == -1 || startCoord[0] - endCoord[0] == 0 || startCoord[0] - endCoord[0] == 1) && (startCoord[1] - endCoord[1] == -1 || startCoord[1] - endCoord[1] == 0 || startCoord[1] - endCoord[1] == 1)){
            return  true;
        }else{
            return false;
        }
     }

     //This function takes 2 integers, one 2D string array and returns a boolean.
     //This function checks to make sure the index of the ship can be placed or not.
     public static boolean shipCheck(int indexOne, int indexTwo, String[][] board){
        if(board[indexOne][board.length - indexTwo - 1].charAt(0) != '⬜'){ //check if the index is an empty space or not.
            return false;
        }else{
            return true;
        }
     }

    //This function takes no parameters and returns an int array.
    //This function validates the user's inputs and stores the starting coordinates of the ship.
    public static void placeStartShip(){
        Main.userInput = Main.input.nextLine();
        while(Functions.coordInputCheck(Main.userInput) == false){ //validate the user's input by checking if its in the form of letter and number.
            System.out.println("Sorry please enter a valid input: ");
            Main.userInput = Main.input.nextLine();
        }
        Main.startCord = Functions.coords(Main.userInput); //store the start coordinate as an array with letter and number.
        while(Functions.shipCheck(Main.startCord[1], Main.startCord[0], Main.board1_2) == false){ //validate the user's input by making sure it isn't placed on another ship.
            System.out.println("Sorry, your ship cannot be placed on top of another ship. please enter another coordinate: ");
            Main.userInput = Main.input.nextLine();
            while(Functions.coordInputCheck(Main.userInput) == false){ //validate the user's input by checking if its in the form of letter and number.
                System.out.println("Sorry please eanter a valid input: ");
                Main.userInput = Main.input.nextLine();
            }
            Main.startCord = Functions.coords(Main.userInput); //store the start coordinate as an array with letter and number.
        }
    }

    //This function takes no parameters and returns an int array.
    //This function validates the user's input and stores the ending coordinates of the ship.
    public static void placeEndShip(){
        Main.userInput = Main.input.nextLine();
        while(Functions.coordInputCheck(Main.userInput) == false){ //validate the user's input by checking if it's in the form of letter and number.
            System.out.println("Sorry please enter a valid input: ");
            Main.userInput = Main.input.nextLine();
        }
        Main.endCord = Functions.coords(Main.userInput); //store the end coordinate as an array with letter and number.
        while(Functions.coordValidator(Main.startCord, Main.endCord) == false || (Main.startCord[0] == Main.endCord[0] && Main.startCord[1] == Main.endCord[1])){ //validate the user's input by making sure it isn't placed over a 2x2 space.
            System.out.println("Sorry pelase enter a valid input: ");
            Main.userInput = Main.input.nextLine();
            while(Functions.coordInputCheck(Main.userInput) == false){ //validate the user's input by checking if its in the form of letter and number.
                System.out.println("Sorry please enter a valid input: ");
                Main.userInput = Main.input.nextLine();
            }
            Main.endCord = Functions.coords(Main.userInput); //store the end coordinate as an array with letter and number.
        }
        while((Functions.shipCheck(Main.endCord[1], Main.endCord[0], Main.board1_2) == false) || (Functions.coordInputCheck(Main.userInput) == false) || (Functions.coordValidator(Main.startCord, Main.endCord
        ) == false || (Main.startCord[0] == Main.endCord[0] && Main.startCord[1] == Main.endCord[1]))){ //validates if the ship can be placed on a tile or not.
            System.out.println("Sorry, please enter a valid input: ");
            Main.userInput = Main.input.nextLine();
            if(Functions.coordInputCheck(Main.userInput)){ //checks if the form of the input is in letter and number form.
                Main.endCord = Functions.coords(Main.userInput);
            }
        }
        Main.shipCount++; //increase the ship value.
    }

    //This function takes no parameters.
    //This function runs the player one's turn.
    public static void playerOneTurn(){
        do {
            Main.userInput = Main.input.nextLine();
            while (Functions.coordInputCheck(Main.userInput) == false) { //check if it's in the right form of letter and number.
                System.out.println("Sorry please enter a valid input.");
                Main.userInput = Main.input.nextLine();
            }
            System.out.println();
            Main.hitCord = Functions.coords(Main.userInput); //divides the letter and number into an int array.
            hitCheckerOne(Main.hitCord[0], Main.hitCord[1], Main.board1_1); //check if the coordinate hits a ship.
        }while(Main.hit); //repeat if the user hits a ship.
    }

    //This function takes no parameters.
    //This function runs the player two's turn.
    public static void playerTwoTurn(){
        do {
            Main.userInput = Main.input.nextLine();
            while (Functions.coordInputCheck(Main.userInput) == false) { //check if it's in the right form of letter and number.
                System.out.println("Sorry please enter a valid input: ");
                Main.userInput = Main.input.nextLine();
            }
            System.out.println();
            Main.hitCord = Functions.coords(Main.userInput); //divides the letter and number into an int array.
            hitCheckerTwo(Main.hitCord[0], Main.hitCord[1], Main.board1_1); //check if the coordinate hits a ship.
        }while(Main.hit); //repeat if the user hits a ship.
    }

    //
    public static void hitCheckerOne(int indexOne, int indexTwo, String[][] board1_1){
        String[][] board2_2 = new String[9][9];
        board2_2 = readFile("board2_2.csv");
        if(board2_2[indexTwo][board1_1.length - indexOne - 1].charAt(0) != '⬜'){ //check if the coordinate has already been shot at.
            if(board2_2[indexTwo][board1_1.length - indexOne - 1].equals("X ")){ //check if it was a ship that was hit.
                System.out.println("Sorry you've already shot here. Please enter another co-ordinate.");
                playerOneTurn(); //run the player's turn again.
            }
            if(board2_2[indexTwo][board1_1.length - indexOne - 1].equals("O ")){ //check if it was a blank spot that was hit.
                System.out.println("Sorry you've already shot here. Please enter another co-ordinate.");
                playerOneTurn(); //run the player's turn again.
            }else if(board2_2[indexTwo][board1_1.length - indexOne - 1].equals("1 ") || board2_2[indexTwo][board1_1.length - indexOne - 1].equals("2 ") || board2_2[indexTwo][board1_1.length - indexOne - 1].equals("3 ") || board2_2[indexTwo][board1_1.length - indexOne - 1].equals("4 ") || board2_2[indexTwo][board1_1.length - indexOne - 1].equals("5 ")){ //check if the coordinate they shot at is a ship.
                System.out.println("Congrats! You hit a ship.\n");
                board2_2[indexTwo][board1_1.length - indexOne - 1] = "X "; //replace the coordinate with an X
                board1_1[indexTwo][indexOne] = "X "; //replace the other board with an X
                writeFile("board2_2.csv", board2_2); //store the array in the file.
                writeFile("board1_1.csv", board1_1); //store the array in the file.
                Main.hit = true;
                Main.p1Hits++; //increase the number of hits.
                Main.p1Score+= 100; //increase the score.

                if(Main.p1Hits == 10){ //check if all the ships are hit.
                    Main.p2 = false;
                }else{  //let the player have another turn.
                    Functions.printBoard(Main.board1_1, Main.board1_2);
                    System.out.println("You get to go again! Please enter a coordinate to shoot at:");
                    playerOneTurn();
                }
            }
        }else if(Main.hit == false){ //if the player miss their shot.
            System.out.println("You Missed!\n");
            board2_2[indexTwo][board1_1.length - indexOne - 1] = "O "; //replace the coordinate with a O to show they missed.
            board1_1[indexTwo][indexOne] = "O "; //replace the other board with a O to show they missed.
            writeFile("board2_2.csv", board2_2); //store the array in the file.
            writeFile("board1_1.csv", board1_1); //store the array in the file.
        }
        Main.hit = false;
    }

    public static void hitCheckerTwo(int indexOne, int indexTwo, String[][] board2_1){
        String[][] board1_2 = new String[9][9];
        board1_2 = readFile("board1_2.csv");
        if(board1_2[indexTwo][board2_1.length - indexOne - 1].charAt(0) != '⬜'){ //check if the coordinate has already been shot at.
            if(board1_2[indexTwo][board2_1.length - indexOne - 1].equals("X ")){  //check if it was a ship that was hit.
                System.out.println("Sorry you've already shot here. Please enter another co-ordinate: ");
                playerTwoTurn();
            }
            if(board1_2[indexTwo][board2_1.length - indexOne - 1].equals("O ")){ //check if it was a blank spot that was hit.
                System.out.println("Sorry you've already shot here. Please enter another co-ordinate: ");
                playerTwoTurn();
            }else if((board1_2[indexTwo][board2_1.length - indexOne - 1].equals("1 ")) || board1_2[indexTwo][board2_1.length - indexOne - 1].equals("2 ") || board1_2[indexTwo][board2_1.length - indexOne - 1].equals("3 ") || board1_2[indexTwo][board2_1.length - indexOne - 1].equals("4 ") || board1_2[indexTwo][board2_1.length - indexOne - 1].equals("5 ")){ //check if the coordinate they shot at is a ship.
                System.out.println("Congrats! You hit a ship.\n");
                board1_2[indexTwo][board2_1.length - indexOne - 1] = "X "; //replace the coordinate with an X
                board2_1[indexTwo][indexOne] = "X "; //replace the other board with an X
                writeFile("board1_2.csv", board1_2); //store the array in the file.
                writeFile("board2_1.csv", board2_1); //store the array in the file.
                Main.hit = true;
                Main.p2Hits++; //increase the number of hits.
                Main.p2Score += 100; //increase the score.


                if(Main.p2Hits == 10){ //check if all the ships are hit.
                    Main.p1 = false;
                }else{ //let the player have another turn.
                    Functions.printBoard(Main.board1_1, Main.board1_2);
                    System.out.println("You get to go again! Please enter a coordinate to shoot at:");
                    playerTwoTurn();
                }
            }
        }else if(Main.hit == false){ //if the player miss their shot.
            System.out.println("You Missed!\n");
            board1_2[indexTwo][board2_1.length - indexOne - 1] = "O "; //replace the coordinate with a O to show they missed.
            board2_1[indexTwo][indexOne] = "O "; //replace the other board with a O to show they missed.
            writeFile("board1_2.csv", board1_2); //store the array in the file.
            writeFile("board2_1.csv", board2_1); //store the array in the file.
        }
        Main.hit = false;
    }

    //This function takes no parameters.
    //This function switches the board to the first player and switches the board.
    public static void switchToPlayerOne(){
        System.out.println("You can now pass the game over to your opponent. Please press enter to continue: ");
        Main.input.nextLine();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        Functions.writeFile("board2_1.csv", Main.board1_1);
        Functions.writeFile("board2_2.csv", Main.board1_2);
        Main.board1_1 = Functions.readFile("board1_1.csv");
        Main.board1_2 = Functions.readFile("board1_2.csv");
        Functions.printBoard(Main.board1_1, Main.board1_2);
        Main.shipCount = 0;

    }

    //This function takes no parameters.
    //This function switches the board to the second player and switches the board.
    public static void switchToPlayerTwo(){
        System.out.println("You can now pass the game over to your opponent. Please press enter to continue: ");
        Main.input.nextLine();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        Functions.writeFile("board1_1.csv", Main.board1_1);
        Functions.writeFile("board1_2.csv", Main.board1_2);
        Main.board1_1 = Functions.readFile("board2_1.csv");
        Main.board1_2 = Functions.readFile("board2_2.csv");
        Functions.printBoard(Main.board1_1, Main.board1_2);
        Main.shipCount = 0;

    }

    //This function takes no parameters.
    //This function displays the winning screen for player one and display both player's score.
    public static void printEndScreenOne(){
        System.out.println();
        System.out.println("                                     # #  ( )");
        System.out.println("                                  ___#_#___|__");
        System.out.println("                              _  |____________|  _");
        System.out.println("                       _=====| | |            | | |==== _");
        System.out.println("                 =====| |.---------------------------. | |====");
        System.out.println("   <--------------------'   .  .  .  .  .  .  .  .   '--------------/");
        System.out.println("    \\                                                              /");
        System.out.println("     \\                     ||PLAYER ONE WINS||                    /");
        System.out.println("      \\__________________________________________________________/\n");
        System.out.println();
        System.out.println("Congratulations " + Main.lineArray[0] + "! You have won the game!");
        System.out.println("Player 1 Final Score: "+ Main.p1Score + " with " + Main.p1Hits + " layers of boats destroyed!");
        System.out.println("Player 2 Final Score: "+ Main.p2Score + " with " + Main.p2Hits + " layers of boats destroyed!\n\n");
    }

    //This function takes no parameters.
    //This function displays the winning screen for player two and display both player's score.
    public static void printEndScreenTwo(){
        System.out.println();
        System.out.println("                                     # #  ( )");
        System.out.println("                                  ___#_#___|__");
        System.out.println("                              _  |____________|  _");
        System.out.println("                       _=====| | |            | | |==== _");
        System.out.println("                 =====| |.---------------------------. | |====");
        System.out.println("   <--------------------'   .  .  .  .  .  .  .  .   '--------------/");
        System.out.println("    \\                                                              /");
        System.out.println("     \\                     ||PLAYER TWO WINS||                    /");
        System.out.println("      \\__________________________________________________________/\n");
        System.out.println();
        System.out.println("Congratulations " + Main.lineArray[1] + "! You have won the game!");
        System.out.println("Player 1 Final Score: "+ Main.p1Score + " with " + Main.p1Hits + " layers of boats destroyed!");
        System.out.println("Player 2 Final Score: "+ Main.p2Score + " with " + Main.p2Hits + " layers of boats destroyed!\n\n");
    }

    //This function takes no parameters.
    //This function ask the user for their ship coordinates for 5 ships.
    public static void shipPlacementsP1(){
        for(int x = 1; x <= 5; x++){
            System.out.print("Please choose the start coordinate of your "+ x +" ship: ");
            placeStartShip();
            System.out.print("Please choose the end  of your "+ x +" ship (1 block away from your start location): ");
            placeEndShip();
            Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
            Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
            printBoard(Main.board1_1,Main.board1_2);
        }

        switchToPlayerTwo();
    }

    //This function takes no parameters.
    //This function ask the user for their ship coordinates for 5 ships.
    public static void shipPlacementsP2(){
        for(int x = 1; x <= 5; x++){
            System.out.print("Please choose the start coordinate of your "+ x +" ship: ");
            placeStartShip();
            System.out.print("Please choose the end  of your "+ x +" ship (1 block away from your start location): ");
            placeEndShip();
            Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
            Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
            printBoard(Main.board1_1,Main.board1_2);
        }

    }
}
