package org.Cooks;

import static java.lang.Thread.sleep;

public class ThreadCook implements  Runnable{
    Cook cook;
    public void run() {
        synchronized (cook) {
        while (true) {
            try {
                System.out.println("Cook is free?" + cook.isFree()+" "+this.hashCode());
                    cook.wait(1000);
                if(!cook.isFree()) {
                  cook.cookWithStrategy();
                }
                }
             catch (InterruptedException e) {
                break;
            }

        }
        }
    }
    public ThreadCook(Cook cook) {
        this.cook = cook;
    }
}
