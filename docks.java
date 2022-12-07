//package com.company;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Random;
import java.util.ArrayList;

/*interface Ship {

    int getDockingSize();

    Integer getAssignedDock();
}*/

/*interface SeaportManager {

    void init(int numberOfDocks, int seawayCapacity);

    void requestSeawayEntrance(Ship s);

    int requestPortEntrance(Ship s);

    void signalPortEntered(Ship s);

    void requestPortExit(Ship s);

    void signalPortExited(Ship s);

    void signalShipSailedAway(Ship s);
}



class Ship{
    int size;
    int id;
    Integer assignedDock;
    Ship(int sizeArg, int idArg){
        size = sizeArg;
        id = idArg;
        assignedDock = null;
    }
    public int getDockingSize() {
        return size;
    }

    public void setAssignedDock(int dock) {
        assignedDock = Integer.valueOf(dock);
    }

    public Integer getAssignedDock() {
        return assignedDock;
    }
}

class Main {

    static class MyThread implements Runnable{
        Ship s;
        SeaportManagerImpl seaport;
        MyThread(Ship sArg, SeaportManagerImpl seaportArg){
            s = sArg;
            seaport = seaportArg;
        }
        public void run(){
            seaport.requestSeawayEntrance(s);
            synchronized(out) {System.out.print(s.id + " entered the seaway.\n");}
            s.setAssignedDock(seaport.requestPortEntrance(s));
            synchronized(out) {System.out.print(s.id + " is about to enter the port.\n");}
            seaport.signalPortEntered(s);
            synchronized(out) {System.out.print(s.id + " left the seaway and entered the port.\n");}
            seaport.requestPortExit(s);
            synchronized(out) {System.out.print(s.id + " is about to leave the port.\n");}
            seaport.signalPortExited(s);
            synchronized(out) {System.out.print(s.id + " left the port and entered the seaway.\n");}
            seaport.signalShipSailedAway(s);
            synchronized(out) {System.out.print(s.id + " left the seaway.\n");}
        }
    }

    static Object out = new Object();

    public static void shipTest() {
        System.out.println("-----SHIPTEST-----");
        SeaportManagerImpl seaport = new SeaportManagerImpl();
        seaport.init(10, 5);
        Ship s = new Ship(1, 0);
        seaport.requestSeawayEntrance(s);
        synchronized (out) {
            System.out.print(s.id + " entered the seaway.\n");
        }
        s.setAssignedDock(seaport.requestPortEntrance(s));
        synchronized (out) {
            System.out.print(s.id + " is about to enter the port.\n");
        }
        seaport.signalPortEntered(s);
        synchronized (out) {
            System.out.print(s.id + " left the seaway and entered the port.\n");
        }
        seaport.requestPortExit(s);
        synchronized (out) {
            System.out.print(s.id + " is about to leave the port.\n");
        }
        seaport.signalPortExited(s);
        synchronized (out) {
            System.out.print(s.id + " left the port and entered the seaway.\n");
        }
        seaport.signalShipSailedAway(s);
        synchronized (out) {
            System.out.print(s.id + " left the seaway.\n");
        }
    }

    public static void threadTest(){
        System.out.println("-----THREADTEST-----");
        SeaportManagerImpl seaport = new SeaportManagerImpl();
        seaport.init(10, 5);
        int amountOfShips = 20;
        Ship[] ships = new Ship[amountOfShips];
        Thread[] thread = new Thread[amountOfShips];
        for(int i=0; i<amountOfShips; ++i){
            ships[i] = new Ship(i%10+1, i);
            MyThread myThread = new MyThread(ships[i], seaport);
            thread[i] = new Thread(myThread);
        } for(int i = 0; i<amountOfShips; ++i) {
            thread[i].start();
        }
    }

    public static void main(String[] args) {
        shipTest();
        threadTest();
    }
}*/
/*
class Voyage extends Thread
{
    public static int amountLeft;
    public static SeaportManagerImpl seaportManager;
    int size;
    int id;
    Voyage(int size_, int id_)
    {
        size = size_;
        id = id_;
    }

    public void run()
    {
        ShipImpl ship = new ShipImpl();
        ship.dockingSize=size;

        seaportManager.requestSeawayEntrance(ship);

        ship.assignedDock=seaportManager.requestPortEntrance(ship);

        seaportManager.signalPortEntered(ship);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        seaportManager.requestPortExit(ship);

        seaportManager.signalPortExited(ship);

        seaportManager.signalShipSailedAway(ship);
        System.out.println(id+" wyplynal");
        amountLeft++;
        System.out.println("LACZNIE WYPLYNELO: "+amountLeft);

    }
};

class Main
{
    public static void main(String[] args) {
        Voyage.seaportManager=new SeaportManagerImpl();
        Voyage.seaportManager.init(5,3);
        Voyage.amountLeft=0;
        int k=5000;
        Voyage[] instance = new Voyage[k];
        Random rand = new Random();
        for(int i=0;i<k;i++)
        {
            instance[i]= new Voyage(rand.nextInt(3)+1,i);
            instance[i].start();
        }



    }
}


class ShipImpl implements Ship
{
    public int dockingSize;
    public int assignedDock;//it should be a list?
    //if dock is x. it will take x+dockick size docks

    public int getDockingSize()
    {
        return dockingSize;
    }
    public Integer getAssignedDock()
    {

        return assignedDock;
    }


}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
*/

public class SeaportManagerImpl implements SeaportManager {


    private int seawayCapacity;
    int[] docks;
    int askforLeave=0;
    int exitBusy=0;


    private int CurrentSeawayCapacity;


    @Override
    public void init(int numberOfDocks, int seawayCapacity) {

        this.seawayCapacity=seawayCapacity;//AMOGUS, it's seaway forward capacity, not seaway capacity

        docks=new int[numberOfDocks];
        for(int i=0;i<docks.length;i++)
        {
            docks[i]=0;
        }


        CurrentSeawayCapacity=0;

    }

//jezeli sa statki oczekujace na wyplyniecie to wtedy traktuj ten statek priorytetowo
    //notify all priorytet

    @Override
    public synchronized void requestSeawayEntrance(Ship s) {
        //Sprawdzanie czy moze wejsc

        while((CurrentSeawayCapacity>=0 && (CurrentSeawayCapacity>=seawayCapacity-1))) //|| askforLeave>0)
        {//CurrentSeawayCapacity
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(SeaportManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(CurrentSeawayCapacity>=seawayCapacity-1)
        {
            throw new RuntimeException("KANAL1");
        }
        CurrentSeawayCapacity++;
    }

    boolean isPlace(Ship s)
    {
        int counter=0;
        int index=0;
        int last=1;
        for(int i=0;i<docks.length;i++)
        {
            if(docks[i]==0 && last==1)
            {
                index=i;
                counter=0;
            }
            if (docks[i]==0)
            {
                counter++;
            }
            if(counter==s.getDockingSize())
            {
                return true;
            }
            last=docks[i];
        }
        return false;
    }


    Integer getDock(Ship s)
    {
        int counter=0;
        int index=0;
        int last=1;
        for(int i=0;i<docks.length;i++)
        {
            if(docks[i]==0 && last==1)
            {
                index=i;
                counter=0;
            }
            if (docks[i]==0)
            {
                counter++;
            }
            if(counter==s.getDockingSize())
            {
                for(int j=index;j<index+s.getDockingSize();j++)
                {
                    docks[j]=1;
                }
                return index;
            }
            last=docks[i];
        }
        return null;
    }

    void freeDocks(Ship s)
    {
        for(int i=s.getAssignedDock();i<s.getAssignedDock()+s.getDockingSize();i++)
        {
            docks[i]=0;
        }
    }


    public synchronized int requestPortEntrance(Ship s)
    {

        while(!isPlace(s))// no free dock)
        {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(SeaportManagerImpl.class.getName()).log(Level.SEVERE, "NIE DZIALA0", ex);
            }
        }
        return getDock(s);
        /////////////////

        /////////
        //dock, blocks
        //zadokuj

    }

    public synchronized void signalPortEntered(Ship s)
    {

        //currentYardGroup=p.group();
        CurrentSeawayCapacity--;
        notifyAll();

        //s.assignedDock = getDock(s); // if -1 then error
        //zajmij doki
    }

    public synchronized void requestPortExit(Ship s)
    {
        //askforLeave++;

        while(((CurrentSeawayCapacity>=0 && (CurrentSeawayCapacity>=seawayCapacity))))//(CurrentSeawayCapacity>0 && (CurrentSeawayCapacity>=seawayCapacity))
        {//CurrentSeawayCapacity
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(SeaportManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(CurrentSeawayCapacity>=seawayCapacity)
        {
            throw new RuntimeException("KANAL1");
        }
        CurrentSeawayCapacity++;
        //exitBusy=1;
        //askforLeave--;

    }

    public synchronized void signalPortExited(Ship s)
    {

        freeDocks(s);
        notifyAll();


        // ?
        //zwolnij doki
    }

    public synchronized void signalShipSailedAway(Ship s)
    {
        CurrentSeawayCapacity--;
        //exitBusy=0;
        notifyAll();
        // kill s
    }


}
