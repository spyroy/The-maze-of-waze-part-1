package algorithms;
import java.util.Comparator;
import dataStructure.node_data;

public class node_Comperator implements Comparator<node_data>{
	
	@Override
    public int compare(node_data n1,node_data n2)
        {
            int ans = (int) (n2.getWeight()- n1.getWeight());
            if(ans < 0)
            	ans = 1; //n1 is bigger
            else if (ans > 0)
            	ans = -1; //n2 is bigger
            // else {n2-n1 = 0 :they are equal}
            return ans; 
        }

}
