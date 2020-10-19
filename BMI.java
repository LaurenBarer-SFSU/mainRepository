*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmi;
import java.util.Scanner;

/**
 *
 * @author labarer
 */
public class BMI {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
         System.out.println("Please enter your low weight in pounds");
        int weightLow = in.nextInt();
        
        System.out.println("Please enter your high weight in pounds");
        int weightHigh = in.nextInt();
        
        System.out.println("Please enter your height in feet and inches");
        double feet = in.nextDouble();
        double inches = in.nextDouble();
        
        System.out.println("Weight"+" "+ " "+ " "+" "+" "+ " "+ " " +"BMI"+" "+ " "+" "+" "+ " "+ " " + " " + " " +  " "+ " " + " " + " "+ " " + " " +  " "+ " " + " " + " "+ "Category");
        
        double height = ((feet*12d+inches)*2.54d)/100;
        
        
 
        for(double i = weightLow; i <= weightHigh; i = i+5)

{

double Weight = i * 0.453592;

double heightMeters = (((feet * 12) + inches) * 0.0254);

double bmi = Weight / Math.pow(heightMeters, 2.0);

System.out.print(i); 
System.out.print(" "+ " " + (bmi));

if(bmi < 18.5)

{

System.out.println(" "+"This is Underweight");

}

else if(bmi >= 18.5 && bmi < 25)

{

System.out.println(" " + "This is Normal weight");

}

else if(bmi >= 25 && bmi < 30)

{

System.out.println(" " + "This is Overweight");

}

else if(bmi >= 30)

{

System.out.println(" " + " This is Obese");

}

}

}

}

            
        
        
        // TODO code application logic here
    
