package org.Cooks;

import static java.lang.Thread.sleep;

public class ThreadCook implements  Runnable{
    Cook cook;
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
                if(!cook.isFree()) {
                  cook.cookWithStrategy();
                }
        }
    }
    public ThreadCook(Cook cook) {
        this.cook = cook;
    }
}
