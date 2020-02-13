
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] inputs = {"a_example", "b_lovely_landscapes", "c_memorable_moments", "d_pet_pictures", "e_shiny_selfies"};
		
		for(String in:inputs) {
			World world = new World();
			world.parse(in + ".in");
			world.simulate();
			world.print(in + ".out");
		}
		

	}

}
