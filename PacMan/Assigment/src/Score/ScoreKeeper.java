package Score;

import java.util.Arrays;
import java.util.Comparator;
/**
 * this is about the operation of score, 
 * it will tell the highest and the lowest score
 */
public class ScoreKeeper {
	protected Score[] scores;

 /**
  * Create a score list 
  */
	public ScoreKeeper() {
		scores = new Score[10];
		for (int i = 0; i < scores.length; i++) {
			scores[i] = new Score("Player 1", 0);
		}
	}
/**
 * sort the score in descending order
 */
	private void sortScores() {
		Arrays.sort(scores, new Comparator<Score>() {
			public int compare(Score a, Score b) {
				return Integer.compare(b.value, a.value);
			}
		});
	}
/**
 * this tell the lowest score
 * @return return the lowest score
 */
	public int getLowestScore() {
		sortScores();
		return scores[9].value;
	}
/**
 * get the scores;
 * used in showing highest scores
 * @return return the highest scores
 */
	public Score[] getScores() {
		Score[] scs = new Score[10];
		for (int i = 0; i < scs.length; i++) {
			scs[i] = scores[i];
		}
		return scs;
	}
/**
 * @author 
 * this is score,
 * it contains the name and his score
 */
	public class Score {
		private String name;
		private int value;

		public Score(String n, int s) {
			this.name = n;
			this.value = s;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return value;
		}
	}
/**
 * Just a place holder for the persistent subclass
 */
	public void saveScores() {
		
	}
/**
 * add a new score 
 * @param name name 
 * @param s score
 */
	public void addScore(String name, int s) {
		scores[9] = new Score(name, s);
		sortScores();
	}
}
