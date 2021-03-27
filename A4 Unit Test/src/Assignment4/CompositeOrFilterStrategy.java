package Assignment4;
import java.util.ArrayList;

public class CompositeOrFilterStrategy implements WatchListFilter{
	//Since this is a composite pattern, we need an ArrayList here
	private ArrayList<WatchListFilter> filters = new ArrayList<>();
	//Provide an add method so that the clients can add as many filters as they want
	public void add(WatchListFilter inputFilter) {
		assert inputFilter != null;
		this.filters.add(inputFilter);
	}
	
	/**
     * Indicates whether a Watchable elements should be included in the watchlist.
     *
     * @param pMovie
     *            a Watchable to potentially include in the Watchlist
     * @pre pMovie != null
     * @return true if the Watchable must be included, false otherwise
     */
	@Override
	public boolean filter(Movie pMovie) {
		assert pMovie != null;
		boolean result = false;
		for(WatchListFilter f : this.filters) {
			//Logical or here
			result = result || f.filter(pMovie);
		}
		return result;
	}

	/**
     * Indicates whether a Watchable elements should be included in the watchlist.
     *
     * @param pTVShow
     *            a Watchable to potentially include in the Watchlist
     * @pre pTVShow != null
     * @return true if the Watchable must be included, false otherwise
     */
	@Override
	public boolean filter(TVShow pTVShow) {
		assert pTVShow != null;
		boolean result = false;
		for(WatchListFilter f : this.filters) {
			//Logical or here
			result = result || f.filter(pTVShow);
		}
		return result;
	}

	/**
     * Indicates whether a Watchable elements should be included in the watchlist.
     *
     * @param pEpisode
     *            a Watchable to potentially include in the Watchlist
     * @pre pEpisode != null
     * @return true if the Watchable must be included, false otherwise
     */
	@Override
	public boolean filter(Episode pEpisode) {
		assert pEpisode != null;
		boolean result = false;
		for(WatchListFilter f : this.filters) {
			//Logical or here
			result = result || f.filter(pEpisode);
		}
		return result;
	}
}
