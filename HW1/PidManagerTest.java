public class PidManagerTest
{
// Creating a main method to test the class named PidManager.
public static void main(String[] args)
{
  // Calling the method named allocatMap() to initialize the memory.
  PidManager.allocatMap();
  
  // Calling the method named allocatPID() to allocate the pid.
  PidManager.allocatPID();  
  PidManager.allocatPID();

  // Calling the method named releasePID() to release the pid.
  PidManager.releasePID(300);
  
  PidManager.allocatPID();
  PidManager.allocatPID();   
}
}