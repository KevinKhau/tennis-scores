package tests;

import org.junit.Test;

import tennis.Person;
import tennis.TennisSet;

public class LaunchTest {

	Person first = new Person("Charles", 20);
	Person second = new Person("Julien", 20);
	TennisSet tennisSet = new TennisSet(first, second);

	@Test
	public void play() {
		tennisSet.confront();
		System.out.println(first + " VS " + second);
		System.out.println(tennisSet);
	}

}
