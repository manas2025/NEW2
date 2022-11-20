import java.util.Scanner;

public class BestFit {
		static void bestFit(int blockSize[], int m, int processSize[],int n)
		{
			int allocation[] = new int[n];
			for (int i = 0; i < allocation.length; i++) {
				allocation[i] = -1;
			}
			for (int i=0; i<n; i++)
			{
				int bestIdx = -1;
				for (int j=0; j<m; j++)
				{
					if (blockSize[j] >= processSize[i])
					{
						if (bestIdx == -1)
							bestIdx = j;
						else if (blockSize[bestIdx] > blockSize[j])
							bestIdx = j;
					}
				}
				if (bestIdx != -1)
				{
					allocation[i] = bestIdx;
					blockSize[bestIdx] -= processSize[i];
				}
			}
		
			System.out.println("\nProcess No.\tProcess Size\tBlock no.");
			for (int i = 0; i < n; i++)
			{
				System.out.print(" " + (i+1) + "\t\t" + processSize[i] + "\t\t");
				if (allocation[i] != -1) {
					System.out.print(allocation[i] + 1);}
				else {
					System.out.print("Not Allocated");}
				System.out.println();
			}
		}
		

	public static void main(String[] args) {
		int m,n,num;
		Scanner in=new Scanner(System.in);
		System.out.print("Enter how many number of blocks you want to enter:");
		m=in.nextInt();
		int blockSize[]=new int[m];
		for(int i=0;i<m;i++) {
			System.out.print("Enter Data "+(i+1)+":");
			num=in.nextInt();
			blockSize[i]=num;
		}
		System.out.print("Enter how many number of process you want to enter:");
		n=in.nextInt();
		int processSize[]=new int[n];
		for(int i=0;i<n;i++) {
			System.out.print("Enter Data "+(i+1)+":");
			num=in.nextInt();
			processSize[i]=num;
		}
		bestFit(blockSize, m, processSize, n);

	}

}
