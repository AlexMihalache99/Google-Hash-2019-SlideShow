import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class World {

	public int nrPhotos;
	public ArrayList<Photo> verticalPhotos = new ArrayList<Photo>();
	public ArrayList<Photo> horizontalPhotos = new ArrayList<Photo>();
	public ArrayList<Slide> slides = new ArrayList<Slide>();
	public SlideShow slideShow;

	class SortbyTags implements Comparator<Slide> {
		// Used for sorting in descending order of tags size
		public int compare(Slide a, Slide b) {
			return b.tags.size() - a.tags.size();
		}
	}

	class SortbySize implements Comparator<Photo> {
		// Used for sorting in descending order of tags size
		public int compare(Photo a, Photo b) {
			return b.tags.size() - a.tags.size();
		}
	}

	void parse(String filename) {

		int bufferSize = 8 * 1024;

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(filename), bufferSize);
			String line1 = bufferedReader.readLine();

			nrPhotos = intValue(line1);

			for (int i = 0; i < nrPhotos; i++) {

				String line = bufferedReader.readLine();
				String[] l = line.split(" ");

				String type = l[0];
				boolean isHorizontal;

				if (type.equals("H")) {
					isHorizontal = true;
				} else {
					isHorizontal = false;
				}

				int nrTags = intValue(l[1]);
				ArrayList<String> tags = new ArrayList<String>();

				for (int j = 0; j < nrTags; j++) {
					tags.add(l[j + 2]);
				}

				Photo photo = new Photo(i, tags, isHorizontal);
				if (isHorizontal == true) {
					horizontalPhotos.add(photo);
				} else {
					verticalPhotos.add(photo);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void simulate() {

		Collections.sort(verticalPhotos, new SortbySize());
		Collections.sort(horizontalPhotos, new SortbySize());

		for (int i = 0; i < verticalPhotos.size() / 2; i++) {
			Photo a = verticalPhotos.get(i);
			Photo b = verticalPhotos.get(verticalPhotos.size() - 1 - i);
			ArrayList<Photo> images = new ArrayList<Photo>();
			images.add(a);
			images.add(b);
			Slide slide = new Slide(images);
			slide.setTags();
			slides.add(slide);

		}

		for (int i = 0; i < horizontalPhotos.size(); i++) {
			Photo a = horizontalPhotos.get(i);
			ArrayList<Photo> images = new ArrayList<Photo>();
			images.add(a);
			Slide slide = new Slide(images);
			slide.setTags();
			slides.add(slide);
		}

		Collections.sort(slides, new SortbyTags());

		for (int i = 0; i < slides.size() - 1; i++) {

			Slide a = slides.get(i);
			int k = i + 1;

			int maxScore = -1;

			for (int j = 1; i + j < slides.size(); j++) {

				Slide b = slides.get(i + j);
				int score = SlideShow.calcScore(a, b);

				if (score > maxScore) {
					maxScore = score;
					k = i + j;
				}
			}

			Collections.swap(slides, i + 1, k);
		}

		slideShow = new SlideShow(slides);

	}

	void print(String filename) {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filename, "UTF-8");

			writer.println(slideShow.slides.size());

			for (int i = 0; i < slideShow.slides.size(); i++) {

				Slide slide = slideShow.slides.get(i);
				writer.print(slide.images.get(0).id);
				if (slide.images.size() > 1) {
					writer.print(" " + slide.images.get(1).id);
				}
				writer.println();

			}

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static int intValue(String s) {
		return Integer.parseInt(s);
	}

}
