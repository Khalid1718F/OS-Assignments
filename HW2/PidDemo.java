class PidManager {
    // Declaring the range of the possible number of pid values.
    private static final int MIN_PID = 300;
    private static final int MAX_PID = 5000;

    // Declaring a memory location.
    private static int[] pids;

    // Creating a method called allocatMap().
    // This method is used to allocate the
    // memory to store the pids.
    public static int allocatMap() {
        //The pids variable is a assigned to the range of static variables between
        //the initially declared minimum and maximum values. 
        pids = new int[MAX_PID - MIN_PID];
        //An if clause takes place to check if the pids variable is in use.
        if (pids == null) {
            //If the Pids Variable is not in use then the memory allocation
            //process has failed and a sentinel value of -1 will be returned.
            System.out.println("Memory allocation failed.");
            return -1;
        }
        //The length of the pid is iterated over using a for loop.
        for (int i = 0; i < pids.length; i++) {
            //If the pid and index position i has a value of 0 then we can allocate memory.
            pids[i] = 0;
        }
        //Once memory has been allocated successfully then return value will be 1.
        System.out.println("Memory allocated successfully.");
        return 1;
    }

    // Create a method named allocatPID().
    // This method is used to allocates and returns a pid.
    public static int allocatPID() {
        //If the value of pids is still null then it will not initialize the manager.
        if (pids == null) {
            System.out.println("PID Manager is not initialized ");
            return -1;
        }
        //A new pidNum variable is created and set to a value of -1.
        int pidNum = -1;
        //The pid value is iterated over again and if it is equal to 0 at index [i],
        //then it will be assigned to the value 1 at index i and i will be added to 
        //the MIN_PID value and assigned to the variable that was created earlier pidNum.
        for (int i = 0; i < pids.length; i++) {
            if (pids[i] == 0) {
                pids[i] = 1;
                pidNum = i + MIN_PID;
                break;
            }
        }
        //If the pidNum is equal to -1  then that means the pid cannot be allocated.
        if (pidNum == -1) {
            System.out.println("Unable to allocat PID");
            return -1;
        }
        //Otherwise it will return the value of the pid which was allocated.
        System.out.println("Allocate PID :" + pidNum);
        return pidNum;
    }

    // Creating a method named releasePID().
    // This method is used to release a pid.
    public static void releasePID(int pidNum) {
        if (pids == null) {
            System.out.println("PID Manager is not initialized ");
            return;
        }
        //If the number of pids is less than the min or more than the max then it is out of range.
        if (pidNum < MIN_PID || pidNum > MAX_PID) {
            System.out.println("Given PID is out or Range");
        }
        //A new pid is then created and decremented.
        int newPid = pidNum - MIN_PID;
        //If the pid is usable at the new pid index then it will be released.
        if (pids[newPid] == 0) {
            System.out.println("PID " + pidNum + " is already released ");
            return;
        }
        //The pid is now released and then set to a value of zeo which means it can be used.
        System.out.println("Releasing PID :" + pidNum);
        pids[newPid] = 0;
    }
}

//Here is the main class declared uses inheritance.
public class PidDemo {
    //This class is statically declared then ectended and run with a void return value.
    static class MyThread extends Thread {
        public void run() {
            //The integer pid variable is now allocated by the PidManager.
            int pid = PidManager.allocatPID();
            //A try-catch is used in conjunction with the sleep method which takes in some parameters.
            try {
                Thread.sleep(1000 * (int) (Math.random() * 10));
            } catch (InterruptedException e) {
            } finally {
                //After the exception the pid manager will release the pid value.
                PidManager.releasePID(pid);
            }
        }
    }

    //Main method declaration.
    public static void main(String[] args) {
        //The Pid manager accesses the allocatMap() function.
        PidManager.allocatMap();
        //A new variable which contains the number of threads is declared.
        int numThreads = 100;
        //A new thread array is created that spans 100 items.
        Thread threads[] = new Thread[100];
        //The numThreads varible is iterated over using a for loop assigns a new instance
        //of the numThreads object to threads at index position [i].
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new MyThread();
        }
        //Another for loop is created in order to iterate over numThreads once more and allow
        //threads at index position[i] to have access to the start() method.
        for (int i = 0; i < numThreads; i++) {
            threads[i].start();
        }
        //One final for loop is used for iteration alongside a try-catch handler.
        //This will allow the threads to be joined and print the Stack Trace.
        for(int i=0; i<numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
