/*
    David Nong-Ang
    01/03/2023
    Functions File
    *description*
 */
import java.io.*; //Input and Output Library
import java.util.*; //package containing hasmap, hashtable, linkedlist, arraylist, ...

public class Functions {

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

     public static void printInfo(){
        System.out.println("Game Information: ");
        System.out.println("When the game starts, each player places 5 boats by typing in the start and end coordinates. (EX. A1 and A2)");
        System.out.println("They will take turns guessing the coordinates of the other player's boats until one side has no more left.\n");
        System.out.println("Symbols: \n⬜ represents an empty coordinate on the board.\nO represents a coordinate that had been shot.\nX represents a coordinate of a ship that is destroyed.\n(1-5) represents a coordinate with a ship.\n");
     }


     public static void printBoard(String[][] board1_1, String[][] board1_2){
        System.out.println("       Enemy Board");
        for(int i = 0; i < board1_1[0].length; i++){
            System.out.print(Main.LETTERS[i]);
        }
        System.out.println();
        for(int i = 0; i < board1_1.length; i++){
            System.out.print(Main.NUMBERS[i]);
            for(int z = 0; z < board1_1[i].length; z++){
                System.out.print(board1_1[i][z]);
            }
            System.out.println();
        }
        System.out.println("-----------------------");

        for(int i = board1_2.length - 1; i > -1; i--){
            System.out.print(Main.NUMBERS[i]);
            for(int z = board1_2.length - 1; z > -1; z--){
                System.out.print(board1_2[i][z]);
            }
            System.out.println();
        }
        for(int i = 0; i < board1_2[0].length; i++){
            System.out.print(Main.LETTERS[i]);
        }
         System.out.println();
         System.out.println("       Your Side");
         System.out.println();
     }

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

     public static int[] coords(String coordinates){
        HashMap <String, Integer> map = new HashMap <>();
        int[] returnCords = {0,0};
        String letter = coordinates.substring(0,1);
        letter = letter.toLowerCase();
        int num = Integer.parseInt(coordinates.substring(1,2));
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);
        map.put("e",5);
        map.put("f",6);
        map.put("g",7);
        map.put("h",8);
        map.put("i",9);
        returnCords[0] = map.get(letter) - 1;
        returnCords[1] = num - 1;
        return returnCords;
     }

     public static boolean coordInputCheck(String coordinates) {
         HashMap<String, Integer> map = new HashMap<>();
         String letter = null;
         int num = 0;
         Integer check = null;
         int valid = 0;
         if (coordinates.length() != 2) {
             return false;
         }
         letter = coordinates.substring(0, 1);
         letter = letter.toLowerCase();

         try {
             num = Integer.parseInt(coordinates.substring(1, 2));
             if (num > 9 || num < 1) {
                 return false;
             } else {
                 valid++;
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
         if (check != null)
             valid++;
         if (valid == 2)
             return true;
         else
             return false;
     }

     public static boolean coordValidator(int[] startCoord, int[] endCoord){
        if((startCoord[0] - endCoord[0] == -1 || startCoord[0] - endCoord[0] == 0 || startCoord[0] - endCoord[0] == 1) && (startCoord[1] - endCoord[1] == -1 || startCoord[1] - endCoord[1] == 0 || startCoord[1] - endCoord[1] == 1)){
            return  true;
        }else{
            return false;
        }
     }

     public static boolean shipCheck(int indexOne, int indexTwo, String[][] board){
        if(board[indexOne][board.length - indexTwo - 1].charAt(0) != '⬜'){
            return false;
        }else{
            return true;
        }
     }

    public static void placeStartShip(){
        Main.userInput = Main.input.nextLine();
        while(Functions.coordInputCheck(Main.userInput) == false){
            System.out.println("Sorry please enter a valid input: ");
            Main.userInput = Main.input.nextLine();
        }
        Main.startCord = Functions.coords(Main.userInput);
        while(Functions.shipCheck(Main.startCord[1], Main.startCord[0], Main.board1_2) == false){
            System.out.println("Sorry, your ship cannot be placed on top of another ship. please enter another coordinate: ");
            Main.userInput = Main.input.nextLine();
            while(Functions.coordInputCheck(Main.userInput) == false){
                System.out.println("Sorry please eanter a valid input: ");
                Main.userInput = Main.input.nextLine();
            }
            Main.startCord = Functions.coords(Main.userInput);
        }
    }

    public static void placeEndShip(){
        Main.userInput = Main.input.nextLine();
        while(Functions.coordInputCheck(Main.userInput) == false){
            System.out.println("Sorry please enter a valid input: ");
            Main.userInput = Main.input.nextLine();
        }
        Main.endCord = Functions.coords(Main.userInput);
        while(Functions.coordValidator(Main.startCord, Main.endCord) == false || (Main.startCord[0] == Main.endCord[0] && Main.startCord[1] == Main.endCord[1])){
            System.out.println("Sorry pelase enter a valid input: ");
            Main.userInput = Main.input.nextLine();
            while(Functions.coordInputCheck(Main.userInput) == false){
                System.out.println("Sorry please enter a valid input: ");
                Main.userInput = Main.input.nextLine();
            }
            Main.endCord = Functions.coords(Main.userInput);
        }
        while((Functions.shipCheck(Main.endCord[1], Main.endCord[0], Main.board1_2) == false) || (Functions.coordInputCheck(Main.userInput) == false) || (Functions.coordValidator(Main.startCord, Main.endCord
        ) == false || (Main.startCord[0] == Main.endCord[0] && Main.startCord[1] == Main.endCord[1]))){
            System.out.println("Sorry, please enter a valid input: ");
            Main.userInput = Main.input.nextLine();
            if(Functions.coordInputCheck(Main.userInput)){
                Main.endCord = Functions.coords(Main.userInput);
            }
        }
        Main.shipCount++;
    }

    public static void playerOneTurn(){
        Main.userInput = Main.input.nextLine();
        while(Functions.coordInputCheck(Main.userInput) == false){
            System.out.println("Sorry please enter a valid input.");
            Main.userInput = Main.input.nextLine();
        }
        System.out.println();
        Main.hitCord = Functions.coords(Main.userInput);
        hitCheckerOne(Main.hitCord[0], Main.hitCord[1], Main.board1_1);
    }

    public static void playerTwoTurn(){
        Main.userInput = Main.input.nextLine();
        while(Functions.coordInputCheck(Main.userInput) == false){
            System.out.println("Sorry please enter a valid input: ");
            Main.userInput = Main.input.nextLine();
        }
        System.out.println();
        Main.hitCord = Functions.coords(Main.userInput);
        hitCheckerTwo(Main.hitCord[0], Main.hitCord[1], Main.board1_1);
    }

    public static void hitCheckerOne(int indexOne, int indexTwo, String[][] board1_1){
        String[][] board2_2 = new String[9][9];
        board2_2 = readFile("board2_2.csv");
        if(board2_2[indexTwo][board1_1.length - indexOne - 1].charAt(0) != '⬜'){
            if(board2_2[indexTwo][board1_1.length - indexOne - 1].equals("X ")){
                System.out.println("Sorry you've already shot here. Please enter another co-ordinate.");
            }
            if(board2_2[indexTwo][board1_1.length - indexOne - 1].equals("O ")){
                System.out.println("Sorry you've already shot here. Please enter another co-ordinate.");
                playerOneTurn();
            }else if(board2_2[indexTwo][board1_1.length - indexOne - 1].equals("1 ") || board2_2[indexTwo][board1_1.length - indexOne - 1].equals("2 ") || board2_2[indexTwo][board1_1.length - indexOne - 1].equals("3 ") || board2_2[indexTwo][board1_1.length - indexOne - 1].equals("4 ") || board2_2[indexTwo][board1_1.length - indexOne - 1].equals("5 ")){
                System.out.println("Congrats! You hit a ship. \n");
                board2_2[indexTwo][board1_1.length - indexOne - 1] = "X ";
                board1_1[indexTwo][indexOne] = "X ";
                writeFile("board2_2.csv", board2_2);
                writeFile("board1_1.csv", board1_1);
                Main.hit = true;
                Main.p1Hits++;
                Main.p1Score+= 100;
            }
        }else if(Main.hit == false){
            System.out.println("You Missed!\n");
            board2_2[indexTwo][board1_1.length - 1] = "O ";
            board1_1[indexTwo][indexOne] = "O ";
            writeFile("board2_2.csv", board2_2);
            writeFile("board1_1.csv", board1_1);
        }
        Main.hit = false;
    }

    public static void hitCheckerTwo(int indexOne, int indexTwo, String[][] board2_1){
        String[][] board1_2 = new String[9][9];
        board1_2 = readFile("board1_2.csv");
        if(board1_2[indexTwo][board2_1.length - indexOne - 1].charAt(0) != '⬜'){
            if(board1_2[indexTwo][board2_1.length - indexOne - 1].equals("X ")){
                System.out.println("Sorry you've already shot here. Please enter another co-ordinate: ");
                playerTwoTurn();
            }
            if(board1_2[indexTwo][board2_1.length - indexOne - 1].equals("O ")){
                System.out.println("Sorry you've already  shot here. Please enter another co-ordinate: ");
                playerTwoTurn();
            }else if((board1_2[indexTwo][board2_1.length - indexOne - 1].equals("1 ")) || board1_2[indexTwo][board2_1.length - indexOne - 1].equals("2 ") || board1_2[indexTwo][board2_1.length - indexOne - 1].equals("3 ") || board1_2[indexTwo][board2_1.length - indexOne - 1].equals("4 ") || board1_2[indexTwo][board2_1.length - indexOne - 1].equals("5 ")){
                System.out.println("Congrats! You hit a ship. \n");
                board2_1[indexTwo][indexOne] = "X ";
                writeFile("board1_2.csv", board1_2);
                writeFile("board2_1.csv", board2_1);
                Main.hit = true;
                Main.p2Hits++;
                Main.p2Score += 100;
            }
        }else if(Main.hit == false){
            System.out.println("You Missed!\n");
            board1_2[indexTwo][board2_1.length - indexOne - 1] = "O ";
            board2_1[indexTwo][indexOne] = "O";
            writeFile("board1_2.csv", board1_2);
            writeFile("baord2_1.csv", board2_1);
        }
        Main.hit = false;
    }

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

    public static void shipPlacementsP1(){
        System.out.print("Please choose the start coordinate of your 1st ship: ");
        placeStartShip();
        System.out.print("Please choose the end  of your 1st ship (1 block away from your start location): ");
        placeEndShip();
        Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
        Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
        printBoard(Main.board1_1,Main.board1_2);

        System.out.print("Please choose the start coordinate of your 2nd ship: ");
        placeStartShip();
        System.out.print("Please choose the end  of your 1st ship (1 block away from your start location): ");
        placeEndShip();
        Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
        Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
        printBoard(Main.board1_1,Main.board1_2);

        System.out.print("Please choose the start coordinate of your 3rd ship: ");
        placeStartShip();
        System.out.print("Please choose the end  of your 1st ship (1 block away from your start location): ");
        placeEndShip();
        Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
        Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
        printBoard(Main.board1_1,Main.board1_2);

        System.out.print("Please choose the start coordinate of your 4th ship: ");
        placeStartShip();
        System.out.print("Please choose the end  of your 4th ship (1 block away from your start location): ");
        placeEndShip();
        Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
        Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
        printBoard(Main.board1_1,Main.board1_2);

        System.out.print("Please choose the start coordinate of your 5th ship: ");
        placeStartShip();
        System.out.print("Please choose the end  of your 5th ship (1 block away from your start location): ");
        placeEndShip();
        Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
        Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
        printBoard(Main.board1_1,Main.board1_2);

        switchToPlayerTwo();
    }

    public static void shipPlacementsP2(){
        System.out.print("Please choose the start coordinate of your 1st ship: ");
        placeStartShip();
        System.out.print("Please choose the end  of your 1st ship (1 block away from your start location): ");
        placeEndShip();
        Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
        Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
        printBoard(Main.board1_1,Main.board1_2);

        System.out.print("Please choose the start coordinate of your 2nd ship: ");
        placeStartShip();
        System.out.print("Please choose the end  of your 1st ship (1 block away from your start location): ");
        placeEndShip();
        Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
        Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
        printBoard(Main.board1_1,Main.board1_2);

        System.out.print("Please choose the start coordinate of your 3rd ship: ");
        placeStartShip();
        System.out.print("Please choose the end  of your 1st ship (1 block away from your start location): ");
        placeEndShip();
        Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
        Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
        printBoard(Main.board1_1,Main.board1_2);

        System.out.print("Please choose the start coordinate of your 4th ship: ");
        placeStartShip();
        System.out.print("Please choose the end  of your 4th ship (1 block away from your start location): ");
        placeEndShip();
        Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
        Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
        printBoard(Main.board1_1,Main.board1_2);

        System.out.print("Please choose the start coordinate of your 5th ship: ");
        placeStartShip();
        System.out.print("Please choose the end  of your 5th ship (1 block away from your start location): ");
        placeEndShip();
        Main.board1_2[Main.startCord[1]][Main.board1_2.length - Main.startCord[0]-1] = Main.shipCount + " ";
        Main.board1_2[Main.endCord[1]][Main.board1_2.length - Main.endCord[0] - 1] = Main.shipCount + " ";
        printBoard(Main.board1_1,Main.board1_2);

    }
}
