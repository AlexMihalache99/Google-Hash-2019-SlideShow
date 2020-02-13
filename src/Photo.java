import java.util.ArrayList;
import java.util.Collections;

public class Photo {
	
	public boolean isHorizontal;
	public ArrayList <String> tags;
    public int id;
    
    public Photo(int id, ArrayList<String> tags, boolean isHorizontal) {
    	this.id = id;
    	this.tags = tags;
    	this.isHorizontal = isHorizontal;
    	
    	sortTags();
    	
    }
    
    public void sortTags() {
    	
    	Collections.sort(tags);
    }
}
