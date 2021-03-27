package Assignment4;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

/**
 * Represents a single episode, with at least a title, and an episode number. Each episode is identified by its path on
 * the file system.
 */
public class Episode implements Sequenceable<Episode>, Cloneable {
	
	private File aPath;
	private final TVShow aTVShow;
	private String aTitle;
	private int aEpisodeNumber;
	private Map<String, String> aCast = new HashMap<>();
	private Map<String, String> aTags = new HashMap<>();
	
	/**
	 * Creates an episode from the file path. This method should not be called by a client. Use
	 * TVShow.createAndAddEpisode instead.
	 *
	 * @param pPath
	 *            location of the episode on the file system.
	 * @param pTVShow
	 *            TVShow that this episode is a part of
	 * @param pTitle
	 *            official title of the episode
	 * @param pEpisodeNumber
	 *            the episode number that identifies the episode
	 * @pre pPath!=null && pTVShow != null && pTitle!=null
	 * @throws IllegalArgumentException
	 *             if the path doesn't point to a file (e.g., it denotes a directory)
	 */
	Episode(File pPath, TVShow pTVShow, String pTitle, int pEpisodeNumber) {
		// Package-private constructor AND notice in the Javadoc to prevent clients from using this constructor.
		// Still, a client could create an Episode directly, and it would only affect the episode object, not the TV
		// show.
		// Alternatively, the Episode class could be nested inside TVShow, with a private constructor.
		assert (pPath != null) && (pTVShow != null) && (pTitle != null);
		if (pPath.exists() && !pPath.isFile()) {
			throw new IllegalArgumentException("The path should point to a file.");
		}
		aPath = pPath; // ok because File is immutable.
		aTVShow = pTVShow;
		aTitle = pTitle;
		aEpisodeNumber = pEpisodeNumber;
	}
	
	@Override
	public void watch() {
		System.out.println("Now watching " + aTVShow.getTitle() + " [" + aEpisodeNumber + "]: " + aTitle);
	}
	
	@Override
	public boolean isValid() {
		return aPath.isFile() && aPath.canRead();
	}
	
	public String getTitle() {
		return aTitle;
	}
	
	public String getStudio() {
		return aTVShow.getStudio();
	}
	
	public Language getLanguage() {
		return aTVShow.getLanguage();
	}
	
	/**
	 * @return the episode number of the episode
	 */
	public int getEpisodeNumber() {
		return aEpisodeNumber;
	}

	public String setCast(String pCharacter, String pActor) {
		if (pActor == null) {
			return aCast.remove(pCharacter);
		}
		else {
			return aCast.put(pCharacter, pActor);
		}
	}

	public String getCast(String pCharacter) {
		return aCast.get(pCharacter);
	}

	public Set<String> getAllCharacters() {
		return Collections.unmodifiableSet(aCast.keySet());
	}

	public String setInfo(String pKey, String pValue) {
		if (pValue == null) {
			return aTags.remove(pKey);
		}
		else {
			return aTags.put(pKey, pValue);
		}
	}
	
	public String getInfo(String pKey) {
		return aTags.get(pKey);
	}
	
	public boolean hasInfo(String pKey) {
		return aTags.containsKey(pKey);
	}
	
	@Override
	public boolean hasPrevious() {
		return aEpisodeNumber > 1;
	}
	
	@Override
	public boolean hasNext() {
		return aEpisodeNumber < aTVShow.getTotalCount();
	}
	
	@Override
	public Episode getPrevious() {
		return aTVShow.getEpisode(aEpisodeNumber - 1);
	}
	
	@Override
	public Episode getNext() {
		return aTVShow.getEpisode(aEpisodeNumber + 1);
	}

	/**
	 * @return a new Episode such that this != copy, this.getClass() == copy.getClass(). this.equals(copy) == true
	 */
	@Override
	public Episode clone() {
		try{
		Episode copy = (Episode) super.clone();
		copy.aPath = new File(this.aPath.getAbsolutePath());
		/*
		 * Shallow copy for TVShow is enough, since it will not affect the corresponding TVShow
		 * shallow copy for string and int type is good enough
		 */
		copy.aCast = new HashMap<String, String>(this.aCast);
		copy.aTags = new HashMap<String, String>(this.aTags);
		return copy;
		}
		catch (CloneNotSupportedException e){
			assert false;
			return null;
		}
	}

	/**
	 * Update the corresponded fields of an episode, this method should not be called by clients directly
	 *
	 * @param f
	 *			the File that the client input from the createAndAdd method in TVShow
	 * @param title
	 * 			the input title as well
	 * @param num
	 * 			get the sequence number from the current size of the episodes list in TVShow
	 */
	void update(File f, String title, int num) {
		this.aPath = f;
		this.aTitle = title;
		this.aEpisodeNumber = num;
	}

	/**
	 * Since after cloning, this.equals(clone) should be return true but this == clone should be false, we
	 * have to Override the equals method. Otherwise equals is same as ==, which only compare the reference
	 * @param o
	 * @return true if the input object is considered as logically equivalent as the current episode
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof Episode){
			Episode toCompare = (Episode) o;
			boolean c1 = this.aPath.getAbsolutePath().equals(toCompare.aPath.getAbsolutePath());
			boolean c2 = this.aEpisodeNumber == toCompare.aEpisodeNumber;
			boolean c3 = this.aTitle == toCompare.aTitle;
			//Since they should belong to the same TVShow, so comparing the reference directly is safe
			boolean c4 = this.aTVShow == toCompare.aTVShow;
			boolean c5 = this.aCast.equals(toCompare.aCast);
			boolean c6 = this.aTags.equals(toCompare.aTags);
			return c1 && c2 && c3 && c4 && c5 && c6;
		}
		else{
			return false;
		}
	}
}
