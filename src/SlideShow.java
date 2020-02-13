import java.util.ArrayList;

public class SlideShow {
	
      public ArrayList <Slide> slides;
      
      public SlideShow(ArrayList <Slide> slides) {
    	  this.slides = slides;
      }
      
      public static int calcScore(Slide slide1, Slide slide2) {
    	  
    	  int p1 = 0;
    	  int p2 = 0;
    	  int commonTags = 0;
    	  
    	  if(slide1.tags.get(slide1.tags.size() - 1).compareTo(slide2.tags.get(0)) > 0 ||
    	     slide2.tags.get(slide2.tags.size() - 1).compareTo(slide1.tags.get(0)) > 0) {
    	   
    	  while (p1 < slide1.tags.size() && p2 < slide2.tags.size()) {
                
				if (slide1.tags.get(p1).compareTo(slide2.tags.get(p2)) > 0) {
					p2++;
				} else if (slide1.tags.get(p1).compareTo(slide2.tags.get(p2)) < 0) {
					p1++;
				} else {///calculating common tags
					commonTags++;
					p1++;
					p2++;
				}
			}
    	  }
			
    	  return Math.min(commonTags, Math.min(slide1.tags.size() - commonTags, slide2.tags.size() - commonTags));
      }
      
}
