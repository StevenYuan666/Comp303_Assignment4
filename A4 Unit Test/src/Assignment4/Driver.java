package Assignment4;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Driver {
	public static void main(String[] args) {
		//Create a library
		Library lib = new Library();
		//Create Several TVShows, episodes and movies to add into the lib
		TVShow t1 = new TVShow("Singer", Language.LATIN, "Other");
		t1.createAndAddEpisode(new File(""), "Singer-ep1");
		TVShow t2 = new TVShow("Super American", Language.ENGLISH, "WarnerBrothers");
		t2.createAndAddEpisode(new File(""), "Super American-ep1");
		t2.createAndAddEpisode(new File(""), "Super American-ep2");
		TVShow t3 = new TVShow("Other American", Language.ENGLISH, "Other Studio");
		t3.createAndAddEpisode(new File(""), "Other American-ep1");
		TVShow t4 = new TVShow("Ce la vie", Language.FRENCH, "WarnerBrothers");
		t4.createAndAddEpisode(new File(""), "Ce la vie-ep1");
		t4.createAndAddEpisode(new File(""), "Ce la vie-ep2");
		Movie m1 = new Movie(new File("Avengers.mp4"), "Avengers",
				Language.ENGLISH, "Marvel");
		Movie m2 = new Movie(new File("French.mp4"), "En rose de la vie",
				Language.FRENCH, "WarnerBrothers");
		lib.addMovie(m1);
		lib.addMovie(m2);
		lib.addTVShow(t1);
		lib.addTVShow(t2);
		lib.addTVShow(t3);
		lib.addTVShow(t4);
		//Generate the composition generator
		//Filter English or French
		CompositeOrFilterStrategy language = new CompositeOrFilterStrategy();
		LanguageFilterStrategy eng = new LanguageFilterStrategy(Language.ENGLISH);
		LanguageFilterStrategy fre = new LanguageFilterStrategy(Language.FRENCH);
		language.add(eng);
		language.add(fre);
		//Filter the studio Warner Brothers
		StudioFilterStrategy std = new StudioFilterStrategy("WarnerBrothers");
		//Filter the first episode only
		FirstEpisodeFilterStrategy f = new FirstEpisodeFilterStrategy();
		//Combine all of these filters together
		//Logic is: (First Episode) and (WarnerBrothers) and (English or French)
		CompositeAndFilterStrategy total = new CompositeAndFilterStrategy();
		total.add(f);
		total.add(std);
		total.add(language);
		//Generate the required watch list
		WatchList request = lib.generateWatchList("Requested", total);
		Iterator<Watchable> iter = request.iterator();
		//Print out all of these filtered episodes
		while(iter.hasNext()){
			Watchable w = iter.next();
			w.watch();
		}
	}

}
