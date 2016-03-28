// Produce semaphore using methods wait(), notify(), notifyAll() 
public interface Semaphore {
  // Require permission. If exists one,  grip it. Otherwise suspend the thread till the permission does not appear
  public void acquire();
  
  // Require given quantity of permisions. If it exists, grip it. 
  // If not - suspend the thread until this given quantity of permisions do not appear
  public void acquire(int permits);
  
  // Release permission returning it to semaphore
  public void release();

  // Release given quantity of permisions returning it to semaphore
  public void release(int permits);
  
  // Return quantity of free permission at the moment 
  public int getAvailablePermits();
}
