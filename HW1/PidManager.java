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
  pids = new int[MAX_PID - MIN_PID];
  if (pids == null)
  {
   System.out.println("Memory allocation failed.");
   return -1;
  }

  for (int i = 0; i < pids.length; i++)
  {
   pids[i] = 0;
  }
  System.out.println("Memory allocated successfully.");
  return 1;
}

// Creating a method named allocatPID().
// This method allocates and returns a pid.
public static int allocatPID()
{
  if (pids == null)
  {
   System.out.println("PID Manager is not initialized ");
   return -1;
  }
  
  int pidNum = -1;
  
  for (int i = 0; i < pids.length; i++)
  {
   if (pids[i] == 0)
   {
    pids[i] = 1;
    pidNum = i + MIN_PID;
    break;
   }
  }
  if (pidNum == -1)
  {
   System.out.println("Unable to allocat PID");
   return -1;
  }
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


