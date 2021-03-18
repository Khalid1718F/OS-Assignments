// Creating a class named PidManager.
class PidManager
{
// Declaring the range of pid values from 300 - 500.
private static final int MIN_PID = 300;
private static final int MAX_PID = 5000;

// Declaring a memory location.
private static int[] pids;

/* Creating a method named allocatMap().
 This method is used to allocate the
 memory to store the pids.*/
public static int allocatMap()
{
  //The pids variable is a assigned to the range of static variables between
  //the initially declared minimum and maximum values. 
  pids = new int[MAX_PID - MIN_PID];
  //An if clause takes place to check if the pids variable is in use.
  if (pids == null)
  {
    //If the Pids Variable is not in use then the memory allocation
   //process has failed and a sentinel value of -1 will be returned.
   System.out.println("Memory allocation failed.");
   return -1;
  }
  //The length of the pid is iterated over using a for loop.
  for (int i = 0; i < pids.length; i++)
  {
   //If the pid and index position i has a value of 0 then we can allocate memory.
   pids[i] = 0;
  }
  System.out.println("Memory allocated successfully.");
  //Once memory has been allocated successfully then return value will be 1.
  return 1;
}

// Creating a method named allocatPID().
// This method allocates and returns a pid.
public static int allocatPID()
{
  //If the value of pids is still null then it will not initialize the manager.
  if (pids == null)
  {
   System.out.println("PID Manager is not initialized ");
   return -1;
  }
  //A new pidNum variable is created and set to a value of -1.
  int pidNum = -1;
  //The pid value is iterated over again and if it is equal to 0 at index [i],
  //then it will be assigned to the value 1 at index i and i will be added to 
  //the MIN_PID value and assigned to the variable that was created earlier pidNum.
  for (int i = 0; i < pids.length; i++)
  {
    if (pids[i] == 0) {
      pids[i] = 1;
      pidNum = i + MIN_PID;
      break;
    }
  }
  //If the pidNum is equal to -1  then that means the pid cannot be allocated.
  if (pidNum == -1)
  {
    System.out.println("Unable to allocat PID");
    return -1;
  }
  //Otherwise it will return the value of the pid which was allocated.
  System.out.println("Allocate PID :" + pidNum);
  return pidNum;
}

// Creating a method named releasePID().
// This method is used to release a pid.
public static void releasePID(int pidNum)
{
  if (pids == null)
  {
   System.out.println("PID Manager is not initialized ");
   return;
  }
  if (pidNum < MIN_PID || pidNum > MAX_PID)
  {
   System.out.println("Given PID is out or Range");
  }
  int newPid = pidNum - MIN_PID;
  if (pids[newPid] == 0)
  {
   System.out.println("PID " + pidNum + " is already released ");
   return;
  }
  
  System.out.println("Releasing PID :" + pidNum);  
  pids[newPid] = 0;
}
}
