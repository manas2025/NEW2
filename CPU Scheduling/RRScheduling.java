import java.util.*;

public class RRScheduling {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int quantum, sum = 0;
        System.out.println("*** RR Scheduling (Preemptive) ***");
        System.out.print("Enter Number of Process: ");
        int i,n = sc.nextInt();
        int sumBurst=0;
        int time;
        int burstTime[] = new int[n];
        int arrivaltime[] = new int[n];
        int waitingTime[] = new int[n];
        int TAT[] = new int[n];
        int completionTime[] = new int[n];
        int remburstTime[] = new int[n];
        double avgWT=0;
        double avgTAT=0;
        Queue<Integer> q=new LinkedList<>();
        for ( i = 0; i < n; i++) {
            System.out.print("\nEnter Arrival Time for processor " + (i+1) + ":");
            arrivaltime[i] =  sc.nextInt();
            System.out.print("Enter Burst Time for processor " + (i + 1) + " : ");
            burstTime[i] = sc.nextInt();
            remburstTime[i]=burstTime[i];
            completionTime[i]=0;
            sumBurst+=burstTime[i];
            
        }
        System.out.print("\n\nEnter Time quantum:");
        quantum = sc.nextInt();
        q.add(0);
        for (i = 0; i < n-1; i++) {
           if(arrivaltime[i]>arrivaltime[i+1]){
                arrivaltime[i+1]=arrivaltime[i];
                burstTime[i+1]=burstTime[i];
                completionTime[i+1]=completionTime[i];
                waitingTime[i+1]=waitingTime[i];
                TAT[i+1]=TAT[i];
                remburstTime[i+1]=remburstTime[i];
           }
        }
        for(time=arrivaltime[0];time<sumBurst;){
            Integer I=q.remove();
            i=I.intValue();
            if(remburstTime[i]<=quantum){
                time+=remburstTime[i];
                remburstTime[i]=0;
                completionTime[i]=1;
                waitingTime[i]=time-arrivaltime[i]-burstTime[i];
                TAT[i]=time-arrivaltime[i];
            
                for(int j=0;j<n;j++){
                    Integer J=Integer.valueOf(j);
                    if((arrivaltime[j]<=time)&&(completionTime[j]!=1) && (!q.contains(J))){
                    q.add(J);
                    }
                }
            }
            else{
                time+=quantum;
                remburstTime[i]-=quantum;
                for(int j=0;j<n;j++){
                    Integer J=Integer.valueOf(j);
                    if((arrivaltime[j]<=time)&&(completionTime[j]!=1) && (i!=j) && (!q.contains(J))){
                        q.add(j);
                    }
                    q.add(i);

                }
            }
        }
        for( i=0;i<n;i++){
            avgWT+=waitingTime[i];
            avgTAT+=TAT[i];
        }
        System.out.println("\n*** Priority Scheduling (Non Preemptive) ***");
        System.out.println("Processor\tArrival time\tBrust time\tCompletion Time\t\tTurn around time\tWaiting time");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (i = 0; i < n; i++) {
            System.out.println("P"+(i+1)+"\t\t"+arrivaltime[i]+"ms\t\t"+burstTime[i]+"ms\t\t"+completionTime[i]+"ms\t\t\t"+TAT[i]+"ms\t\t\t"+waitingTime[i]+"ms");
        }
        System.out.println("\nAverage waiting time: " + (avgWT / n) + "\nAverage turn around time: " + (avgTAT / n));
    }
}
