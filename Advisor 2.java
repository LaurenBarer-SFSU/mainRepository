// DO NOT ADD NEW METHODS OR NEW DATA FIELDS!
// DO NOT MODIFY METHODS OR DATA FIELDS!

// Implement methods with "add statements"

package PJ3;

class Advisor {

   private int advisorID;
   private Student serveStudent;


   private int startFreeTime;
   private int endFreeTime;
   private int startBusyTime;
   private int endBusyTime;


   private int totalFreeTime;
   private int totalBusyTime;
   private int totalStudents;

   Advisor()
   {
	this(-1);
   }
   {
   advisorID = 1;
    totalFreeTime = 0;
    totalBusyTime = 0;
    totalStudents = 0;

    serveStudent = new Student ();

   startFreeTime = 0;
   endFreeTime = 0;
   startBusyTime = 0;
   endBusyTime = 0;
   }
   Advisor(int advisorId)
   {
       advisorID = advisorId;
   }
   int getAdvisorID() 
   {
	return advisorID;
   }

   Student getCurrentStudent() 
   {
	return serveStudent;
   }
   int getEndBusyTime() 
   {
	return endBusyTime; 
   }
   // mutator methods

   void setStartFreeTime (int time)
   {
        startFreeTime=time;
   }
   void setStartBusyTime (int time)
   {
        startBusyTime=time;
   }
   void setEndFreeTime (int time)
   {
  	      endFreeTime  = time;
   }
   void setEndBusyTime (int time)
   {
  	endBusyTime   = time;
   }
   void setCurrentStudent(Student aStudent) 
   {
	serveStudent=aStudent;
   }

   void updateTotalFreeTime()
   {
       totalFreeTime = endFreeTime;
   }
   void updateTotalBusyTime()
   {
       totalBusyTime = endBusyTime;
   }
   void updateTotalStudents()
   {
	totalStudents++;
   }

   void startAdvisingSession(Student aStudent, int currentTime)
   {
            this.serveStudent = aStudent;
          endFreeTime = startBusyTime;
       totalFreeTime = totalFreeTime + currentTime - startFreeTime;

          startBusyTime = currentTime;
       endBusyTime = startBusyTime + aStudent.getAdvisingTime();

         aStudent.setWaitTime(aStudent.getAdvisingTime());
       aStudent.setFinishTime(endBusyTime);

               totalStudents++;
   }
   Student endAdvisingSession()
   {
              startFreeTime = endBusyTime;

       totalBusyTime = (endBusyTime - startBusyTime);

             return serveStudent;
   }
   void printStatistics () 
   {
  	System.out.println("\t\tAdvisor ID             : "+advisorID);
  	System.out.println("\t\tTotal free time        : "+totalFreeTime);
  	System.out.println("\t\tTotal busy time        : "+totalBusyTime);
  	System.out.println("\t\tTotal # of students    : "+totalStudents);
  	if (totalStudents > 0)
  	    System.out.format("\t\tAverage advising time  : %.2f%n\n",(totalBusyTime*1.0)/totalStudents);
   }

   public String toString()
   {
	return "AdvisorID="+advisorID+
	       "\n:startFreeTime="+startFreeTime+":endFreeTime="+endFreeTime+":totalFreeTime="+totalFreeTime+
	       "\n:startBusyTime="+startBusyTime+":endBusyTime="+endBusyTime+":totalBusyTime="+totalBusyTime+ 
	       "\n:totalStudent="+totalStudents+"\n-->currentStudent:"+serveStudent+"\n";
   }

   public static void main(String[] args) {
        // quick check
        Student mystudent = new Student(1,8,15);
	Advisor myadvisor = new Advisor(5);
	myadvisor.setStartFreeTime(0);
        System.out.println(myadvisor);
        myadvisor.startAdvisingSession(mystudent, 25);
        System.out.println("\n"+myadvisor);
	myadvisor.endAdvisingSession();
        System.out.println("\n"+myadvisor);
        System.out.println("\n\n");
	myadvisor.printStatistics();

   }

};

