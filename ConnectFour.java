/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;
import java.util.Scanner;

/**
 *
 * @author labarer
 */
public class ConnectFour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      String[][] board = createEmptyBoard(); 
      
           Scanner input = new Scanner(System.in);
      
   boolean bl = true;
      
   printPattern(board);
     
           while (bl){
          
          int player = 1, userInput;
                      String playerValue = "RED";
    String message = "Drop a disk at the column between 0 and 6;";
          
          while (true){
              userInput = getUserInput(input,"Drop a disk at the column between 0 an 6:");
              
      dropDisk(board,userInput,player);
          printPattern(board);
          if(checkWinner(board)){
               System.out.println(playerValue+ " " + " Won Congrats!!");
                              return;
                  
              }
              if(player==1){
    message = "Please drop a disk at the column between 0 and 6: ";
                      playerValue = "YELLOW";
              player = 2;
                  
              }else if (player==2){
    message = "Please drop a disk at the column between 0 and 6:";
                
                    playerValue= "RED";
           player=1;
                          }
              }
          }
      }
    
    private static int getUserInput(Scanner scanner, String message){
        int userInput;
                    System.out.println(message);
      userInput = scanner.nextInt();
                  if (userInput < 0 || userInput > 6) {
        System.out.println("Invalid Input");
                  return getUserInput(scanner, message);
    }
         return userInput;
    }
    
    public static String[][] createEmptyBoard() {
     
        String[][] f = new String[7][15];
        
        
                for (int i = 0; i < f.length; i++) {
            
             for (int j = 0; j < f[i].length; j++) {
                
                       if (j % 2 == 0) f[i][j] = "|";
               
     else f[i][j] = " ";
                if (i == 6) f[i][j] = "-";
    
            }
    }
               return f;
    }
    
    public static void printPattern(String[][] brd) {
    
        for (int i = 0; i < brd.length; i++) {
                for (int j = 0; j < brd[i].length; j++) {
   System.out.printf(brd[i][j]);
            }
                      System.out.println();
        }
    }
   
    public static void dropDisk(String[][] brd, int position, int player) {
    
        
        for (int row = 6; row >= 0; row--) {
            
        if (brd[row][2 * position + 1].equalsIgnoreCase(" ")) {
           
                if (player == 1) {
                   
                 brd[row][2 * position + 1] = "R";
               
                } else {
                  
                    brd[row][2 * position + 1] = "Y";
                }
                       return;
            }
        }
        System.out.println("Not possible to do here");
    }
    
    public static boolean checkWinner(String[][]brd){
        String str = "   ";
                 return checkForFourInRow(brd)||checkForFourInCol(brd);
    }
    
   public static boolean checkForFourInRow(String board[][]) {
        
       int found = 0;
        
       for (int row = 0; row < 6; row++) {
           
  for (int col = 1; col < 7; col += 2) {
                found = 0;
                
                while (!board[row][col].equalsIgnoreCase(" ") && board[row][col].equalsIgnoreCase(board[row][col + 2])) {
                  
                    found = found + 1;
                    col += 2; 
                }
                
                if(found>=3){
                   
                    return true;
                }
            }
        }
       return false; 
   }
   
   public static boolean checkForFourInCol(String board[][]) {
        
       int found = 0;
        
       for (int col = 1; col < 7; col += 2) {
           
    for (int row = 0; row < 6; row++) {
                found = 0;
               
                    while (!board[row][col].equalsIgnoreCase(" ") && board[row][col].equalsIgnoreCase(board[row + 1][col])) {
                    found = found + 1;
                    row += 1;
       }
                if(found>=3){
                    return true;
                }
            }
        }
        return false;
   }
   
}

   
   



    

