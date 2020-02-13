import java.util.ArrayList;

public class Slide {

	public ArrayList<Photo> images = new ArrayList<Photo>();
	public ArrayList<String> tags = new ArrayList<String>();
     
	//constructor for 2 vertical images
	public Slide(ArrayList<Photo> images) {
		this.images = images;
	}
	
	//constructor for a horizontal image
	public Slide(Photo image) {
		this.images.add(image);
	}

	public void setTags() {

		if (images.size() == 1) {
			tags = images.get(0).tags;
		} else {
			int p1 = 0;
			int p2 = 0;
			Photo image1 = images.get(0);
			Photo image2 = images.get(1);

			while (p1 < image1.tags.size() && p2 < image2.tags.size()) {

				if (image1.tags.get(p1).compareTo(image2.tags.get(p2)) > 0) {
					tags.add(image2.tags.get(p2));
					p2++;
				} else if (image1.tags.get(p1).compareTo(image2.tags.get(p2)) < 0) {
					tags.add(image1.tags.get(p1));
					p1++;
				} else {
					tags.add(image1.tags.get(p1));
					p1++;
					p2++;
				}
			}
			
			while(p1 < image1.tags.size()) {
				tags.add(image1.tags.get(p1));
				p1++;
			}
			
			while(p2 < image2.tags.size()) {
				tags.add(image2.tags.get(p2));
				p2++;
			}
		}
	}

}
