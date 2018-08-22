import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class BlackJackMain {
	public static Map<String, Integer> cardsToPoints = new HashMap<String,Integer>();
	
	static {
		cardsToPoints.put("2", 2);
		cardsToPoints.put("3", 3);
		cardsToPoints.put("4", 4);
		cardsToPoints.put("5", 5);
		cardsToPoints.put("6", 6);
		cardsToPoints.put("7", 7);
		cardsToPoints.put("8", 8);
		cardsToPoints.put("9", 9);
		cardsToPoints.put("10", 10);
		cardsToPoints.put("Jack", 10);
		cardsToPoints.put("Queen", 10);
		cardsToPoints.put("King", 10);
	}
	
	public static void main(String[] args) {
	}
	
    public static ArrayList<Integer> addPoints(ArrayList<String> cards){
    	Set<Integer> points = new TreeSet<Integer>();
    	ArrayList<Integer> pointsArr = new ArrayList<Integer>();
    	addAces(0, cards, points);
    	if(points.size() == 1) pointsArr.add(points.toArray(new Integer[0])[0]);
    	else {
    		for(Integer i: points) {
    			if(i > 21) break; 
    			else pointsArr.add(i);
    		}
    	}
    	if(pointsArr.isEmpty()) pointsArr.add(points.toArray(new Integer[0])[0]);
    	return pointsArr;
    }
    
    public static void addAces(int pointCount, ArrayList<String> cards, Set<Integer> points) {
    	if(cards.size() == 0) points.add(pointCount);
    	else if(cards.get(0).equals("Ace")) {
    		addAces(pointCount + 1, removeLeadingAndCopy(cards, 1), points);
    		addAces(pointCount + 11, removeLeadingAndCopy(cards, 1), points);
    	}
    	else {
    		int cardPoint = cardsToPoints.get(cards.get(0));
    		addAces(pointCount + cardPoint, removeLeadingAndCopy(cards, 1), points);
    	}
    }
    
    public static ArrayList<String> removeLeadingAndCopy(ArrayList<String> clone, int copyPos){
    	ArrayList<String> newArrayList = new ArrayList<String>();
    	for(int i = copyPos; i<clone.size(); i++) {
    		newArrayList.add(clone.get(i));
    	}
    	return newArrayList;
    }
    
    public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
