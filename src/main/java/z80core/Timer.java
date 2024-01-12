package z80core;

public class Timer {

  protected long averageTime;
  private long startTime;
  private long endTime;
  protected long elapsedTime;
  private long sumTime;
  private long times;
  private long lower = Long.MAX_VALUE;
  private String name;

  public Timer(String name) {
    this.name = name;
  }

  public long end() {
    endTime = System.nanoTime();
    elapsedTime = endTime - startTime;

    sumTime += elapsedTime;
    averageTime = sumTime / ++times;
    
    if (elapsedTime < lower) {
      lower = elapsedTime;
      System.out.println(name + ": lower -> " + lower);
    }

    return elapsedTime;
  }

  public void start() {
    startTime = System.nanoTime();
  }

  public long average() {
    return averageTime;
  }

}