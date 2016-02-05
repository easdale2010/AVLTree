import java.util.*;
public class Program {

	public static void main(String[] args) {
		
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        
        List<Integer> dataSet = new LinkedList<>();
        
        Random ran = new Random();
        
        System.out.print("Please enter the size of the data set: ");
        Scanner keyboard = new Scanner(System.in);
        int size = keyboard.nextInt();        
        final int N = size*2; // should ensure some duplicates generated on most runs
        System.out.printf("The size of the dataSet for this run is %d \n", size);
        
        int count = 0;
        
        while (bst.size() < size) 
        {
            int candidate = ran.nextInt(N)*2+1;  // add odd values only
            count++;
            if (bst.add(candidate)) 
            {
                dataSet.add(candidate);
            }
        }
        
        System.out.printf("Added %d elements after generating %d random numbers.\n", size, count);
        
        int errorCount = 0;
        
        System.out.println("Testing the tree contents using contains() method:");
        for (int d : dataSet) 
        {
            if (!bst.contains(d))
            {
                errorCount++;
                System.out.printf("Error %d: Tree does not contain value %d from data set.\n",
                		errorCount, d);
            }
            if (bst.contains((d/2)*2))
            { // even number in range tests for absence
                errorCount++;
                System.out.printf("Error %d: Tree contains value %d not in data set.\n",
                		errorCount, (d/2)*2);
            }
         }
        System.out.printf("Testing contents complete with %d errors ", errorCount);
        
        System.out.println();
        
        System.out.println("Testing enumerator throws an exception " +
                " if tree is modified while in use.");
        try {
            for (int i : bst) 
            {
                bst.add(-1);
            }
            errorCount++;
        }
        catch (IllegalStateException e) {}
        
        System.out.printf("Testing exception throwing complete. Errors now %d.\n",
        		errorCount);
        
        System.out.println();
        
        bst.remove(-1);
        if (bst.size() <= 25) 
        {
            int counter = 1;
            System.out.println("Tree contents are:");
            for (int i : bst) 
            {
                System.out.printf("Item number %d is: %d \n", counter++, i);
            }
        }
        
        System.out.println("Removing tree contents:");
        count = bst.size();
        errorCount = 0;
        for (int d : dataSet) 
        {
            if (bst.remove(d)) 
            {
                count--;
                if (count != bst.size())
                {
                    errorCount++;  // tree size not updated
                }
            }
            else 
            { // d not removed
                errorCount++;
            }
        }
        System.out.printf("%d items removed with %d errors.\n", size, errorCount);
        System.out.printf("Tree size = %d, any remaining contents listed below:\n",
            bst.size());
        for (int i : bst) 
        {
            System.out.println(i);
        }
        
        System.out.println("Testing complete....");
        keyboard.close();
	}

}
