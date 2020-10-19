// DO NOT ADD NEW METHODS OR NEW DATA FIELDS!
// DO NOT MODIFY METHODS OR NEW DATA FIELDS!

// Implement methods with "add statements"

package PJ3;

import java.util.*;

//--------------------------------------------------------------------------
//
// Define simulation queues in a advising area. Queues hold references to Student 
// and Advisor objects
//
// Student (FIFO) queue is used to hold waiting students. If the queue is too long
// (i.e. >  studentQLimit), student goes away without entering student queue
//
// There are several advisors in a advising area. Use PriorityQueue to 
// hold BUSY advisors and FIFO queue to hold FREE advisors, 
// i.e. a advisor that is FREE for the longest time should start be used first.
//
// To handle advisor in PriorityQueue, we need to define comparator 
// for comparing 2 advisor objects. Here is a constructor from Java API:
//
// 	PriorityQueue(int initialCapacity, Comparator<? super E> comparator) 
//
// For priority queue, the default compare function is "natural ordering"
// i.e. for numbers, minimum value is returned first
//
// User can define own comparator class for PriorityQueue.
// For advisor objects, we like to have smallest end busy interval time first.
// i.e. use Advisor's getEndBusyTime() 
//
// The following class define compare() for two advisors :

class CompareAdvisor implements Comparator<Advisor>{
	// overide compare() method
 	public int compare(Advisor o1, Advisor o2) {
		return o1.getEndBusyTime() - o2.getEndBusyTime(); 
	}
}


class AdvisingArea {

  
  // Private data fields:
  
  // define one priority queue 
  private PriorityQueue <Advisor> busyAdvisorQ;


  private Queue<Student> studentQ;
  private Queue<Advisor> freeAdvisorQ;


  private int studentQLimit;

  public AdvisingArea() 
  {
      freeAdvisorQ = new ArrayDeque<Advisor>();
      studentQ = new ArrayDeque<Student>();
  }
  public AdvisingArea(int numAdvisors, int studentQlimit)
  {
      for(int i = 0; i < numAdvisors; i++){
          Advisor advisor = new Advisor(i+1);
          freeAdvisorQ.add(advisor);
      }
      busyAdvisorQ= new PriorityQueue<Advisor>( numAdvisors,
              new CompareAdvisor());

      freeAdvisorQ = new ArrayDeque<Advisor>(numAdvisors);
      studentQ = new ArrayDeque<Student>(studentQlimit);

      this.studentQLimit = studentQlimit;
  }
  public Advisor removeFreeAdvisorQ()
  {
      return freeAdvisorQ.remove();
  }
  public void insertFreeAdvisorQ(Advisor advisor)
  {
      freeAdvisorQ.add(advisor);
  }
  public boolean emptyFreeAdvisorQ()
  {
	return freeAdvisorQ.peek() == null;
  }
  public int sizeFreeAdvisorQ()
  {
	return freeAdvisorQ.size();
  }
  public Advisor removeBusyAdvisorQ() 
  {
	return busyAdvisorQ.remove();
  }
  public void insertBusyAdvisorQ(Advisor advisor)
  {
	busyAdvisorQ.add(advisor);
  }

  public boolean emptyBusyAdvisorQ()
  {
	return busyAdvisorQ.peek() == null;
  }
  public int sizeBusyAdvisorQ()
  {
	return busyAdvisorQ.size();
  }
  public Advisor peekBusyAdvisorQ() 
  {
	return busyAdvisorQ.peek();
  }
  public Student removeStudentQ()
  {
	return studentQ.remove();
  }
  public void insertStudentQ(Student student)
  {
	studentQ.add(student);
  }
  public boolean emptyStudentQ()
  {
	return studentQ.peek()== null;
  }
  public int sizeStudentQ()
  {
	return studentQ.size();
  }
  public boolean isStudentQTooLong()
  {
	return studentQ.size() < studentQLimit;
  }
  
  public void printStatistics()
  {
  	System.out.println("\t# waiting students   : "+sizeStudentQ());
  	System.out.println("\t# busy advisors      : "+sizeBusyAdvisorQ());
  	System.out.println("\t# free advisors      : "+sizeFreeAdvisorQ());
  }


  public static void main(String[] args) {

        // quick check

        // create an AdvisingArea and 4 advisors & studentQLimit 5 
        AdvisingArea sc = new AdvisingArea(3, 5);
        Student c1 = new Student(1,18,10);
        Student c2 = new Student(2,33,11);
        Student c3 = new Student(3,21,12);

        // insert students into studentQ
  	sc.insertStudentQ(c1);
  	sc.insertStudentQ(c2);
  	sc.insertStudentQ(c3);
	System.out.println("studentQ:"+sc.studentQ);
	System.out.println("===============================================");
	System.out.println("Remove student:"+sc.removeStudentQ());
	System.out.println("Remove student:"+sc.removeStudentQ());
	System.out.println("Remove student:"+sc.removeStudentQ());
	System.out.println("===============================================");

        // remove advisors from freeAdvisorQ
	System.out.println("freeAdvisorQ:"+sc.freeAdvisorQ);
	System.out.println("===============================================");
	Advisor p1=sc.removeFreeAdvisorQ();
	Advisor p2=sc.removeFreeAdvisorQ();
	Advisor p3=sc.removeFreeAdvisorQ();
	System.out.println("Remove free advisor:"+p1);
	System.out.println("Remove free advisor:"+p2);
	System.out.println("Remove free advisor:"+p3);
	System.out.println("===============================================");
	System.out.println("freeAdvisorQ:"+sc.freeAdvisorQ);
	System.out.println("===============================================");


        // insert students to advisors
        p1.startAdvisingSession(c1, 13);
        p2.startAdvisingSession(c2, 13);
        p3.startAdvisingSession(c3, 13);

        // insert advisors to busyAdvisorQ
	System.out.println("busyAdvisorQ:"+sc.busyAdvisorQ);
	System.out.println("===============================================");
	sc.insertBusyAdvisorQ(p1);
	sc.insertBusyAdvisorQ(p2);
	sc.insertBusyAdvisorQ(p3);
	System.out.println("busyAdvisorQ:"+sc.busyAdvisorQ);
	System.out.println("===============================================");

        // remove advisors from busyAdvisorQ
	p1=sc.removeBusyAdvisorQ();
	p2=sc.removeBusyAdvisorQ();
	p3=sc.removeBusyAdvisorQ();
        p1.endAdvisingSession();
        p2.endAdvisingSession();
        p3.endAdvisingSession();
	System.out.println("Remove busy advisor:"+p1);
	System.out.println("Remove busy advisor:"+p2);
	System.out.println("Remove busy advisor:"+p3);

   }


};


