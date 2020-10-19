package PJ3;

import java.util.*;
import java.io.*;

// You may add new functions or data fields in this class 
// You may modify any functions or data members here
// You must use Student, Advisor and AdvisingArea classes
// to implement AdvisingCenter simulator

// The outline is given below, add statements to complete 
// the simulator

class AdvisingCenter {

    // input parameters
    private int numAdvisors, studentQLimit;
    private int chancesOfArrival, maxAdvisingTime;
    private int simulationTime, dataSource;

    // statistical data
    private int numGoaway, numServed, totalWaitingTime;

    // internal data
    private int counter;                 // student ID counter
    private AdvisingArea advisingarea; // advising area object
    private Scanner dataFile;         // get student data from file
    private Random dataRandom;         // get student data using random function

    // most recent student arrival info, see getStudentData()
    private boolean anyNewArrival;
    private int advisingTime;

    // initialize data fields
    private AdvisingCenter() {
        studentQLimit = 0;
        numAdvisors = 0;
        dataSource = 0;
        simulationTime = 0;
        chancesOfArrival = 0;
        maxAdvisingTime = 0;
        advisingTime = 0;
        anyNewArrival = false;
        dataRandom = new Random();
        dataFile = null;
        advisingarea = null;
        counter = 0;
        numGoaway = 0;
        numServed = 0;
        totalWaitingTime = 0;
    }

    private void setupParameters() {
        System.out.println(" Obtain Parameters for Simulation ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give Simulated Time (< 10000): ");
        simulationTime = scanner.nextInt();
        if (simulationTime < 0 || simulationTime > 10000) {
            System.out.println("Not in the field. Please use positive number less than 10000 and more than 0.");
            setupParameters();
        }
        System.out.print("Please enter number of advisors you need. (< 10): ");
        numAdvisors = scanner.nextInt();
        if (numAdvisors < 0 || numAdvisors > 10) {
            System.out.println("Not in the field. Please use positive numbers less than 10 and more than 0.");
            setupParameters();
        }
        System.out.print("Please input student queue limit (<50): ");
        studentQLimit = scanner.nextInt();
        if (studentQLimit < 0 || studentQLimit > 50) {
            System.out.println("Not in the field. Please use positive numbers less than 50 and more than 0.");
            setupParameters();
        }
        System.out.print("Enter chances (1% < & <= 100%) of new student: ");
        chancesOfArrival = scanner.nextInt();
        if (chancesOfArrival < 1 || chancesOfArrival > 100) {
            System.out.println("Not in the field. Please use a positive number less than 100 and more than 0.");
            setupParameters();
        }
        System.out.print("Please input max serivce time for a student(<500): ");
        maxAdvisingTime = scanner.nextInt();
        if (maxAdvisingTime < 0 || maxAdvisingTime > 500) {
            System.out.println("Not in the field. Please use a positive number less than 500 and greater than 0.");
            setupParameters();
        }
        System.out.print("Enter 1/0 to get data from file/Random: ");
        dataSource = scanner.nextInt();

        if (dataSource == 1) {
            System.out.print("Enter data: ");
            String file = scanner.next();
            try {
                dataFile = new Scanner(new File(file));
            } catch (FileNotFoundException e) {
                System.out.println("No Data found.");
            }
        }
    }
    private void getStudentData() {
      
        int data1, data2;
      if(dataSource == 1){
          data1 = Integer.valueOf(dataFile.next());
          data2 = Integer.valueOf(dataFile.next());
          anyNewArrival = (((data1%100)+1)<= chancesOfArrival);
          advisingTime = (data2%maxAdvisingTime)+1;
         
      }
      else{
          anyNewArrival = ((dataRandom.nextInt(100)+1) <= chancesOfArrival);
          advisingTime = dataRandom.nextInt(maxAdvisingTime)+1;
      }
       
    }
        private void doSimulation ()
        {
            advisingarea = new AdvisingArea(numAdvisors, studentQLimit);
            for (int currentTime = 0; currentTime < simulationTime; currentTime++) {
                getStudentData();
                System.out.println("Time : " + currentTime);
                if (anyNewArrival) {
                    counter++;
                    Student nextArrival = new Student(counter, advisingTime, currentTime);
                    // Step 1.1: setup student data
                    // Step 1.2: check student waiting queue too long?
                    //           if Q too long, update numGoaway
                    if (advisingarea.isStudentQTooLong()) {
                        advisingarea.insertStudentQ(nextArrival);
                        System.out.println("Student #" + counter + " arrives with advising time " + advisingTime + " units");
                        System.out.println("Student #" + counter + " is in the new student queue");
                    } else {
                        numGoaway++;
                    }
                } else {
                    System.out.println("No new student!");
                }
           
            }
        }
  private void printStatistics()
  {
      
      System.out.println("\n\n============================================");
      System.out.println("\n\nThe end of the simulated report: ");
      System.out.println("# students went away      : " + numGoaway);
      System.out.println("# students were advised           : " + (numServed + advisingarea.sizeStudentQ()));
      System.out.println("\n*** Current Students Information ***");
 
  System.out.println("# Students waiting    : " + advisingarea.sizeStudentQ());
  System.out.println("# Ocupied advisors        : " + advisingarea.sizeBusyAdvisorQ());
  System.out.println("# Free Advisors       : " + advisingarea.sizeFreeAdvisorQ());
           
  double waitperiod = (double)totalWaitingTime / numServed;
      
      System.out.println("\nTotal waiting time          : " + totalWaitingTime);
      System.out.printf("Average waiting time        : %.2f\n", waitperiod);
      
      if (!advisingarea.emptyBusyAdvisorQ()) {
        
          System.out.println("Ocupied Students Information Includes :");
      }
     
      while(!advisingarea.emptyBusyAdvisorQ()){
           Advisor advisorperson = advisingarea.removeBusyAdvisorQ();
         
           advisorperson.setEndBusyTime(simulationTime);
          advisorperson.printStatistics();
         
      }
      if(!advisingarea.emptyFreeAdvisorQ()) {
          System.out.println("Free Advisors Info. :");
      }
      while(!advisingarea.emptyFreeAdvisorQ()){
         Advisor advisorperson = advisingarea.removeBusyAdvisorQ();
         
         advisorperson.setEndBusyTime(simulationTime);
          advisorperson.printStatistics();
      }
  }
  public static void main(String[] args) {
   	
      AdvisingCenter runAdvisingCenter=new AdvisingCenter();
   	
      runAdvisingCenter.setupParameters();
   	
      runAdvisingCenter.doSimulation();
   	
      runAdvisingCenter.printStatistics();
  }
}
