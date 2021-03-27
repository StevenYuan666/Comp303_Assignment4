import java.util.ArrayList;

public class CompositeAndFilterStrategy implements WatchListFilter{
	private ArrayList<WatchListFilter> filters = new ArrayList<>();
	
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
		boolean result = true;
		for(WatchListFilter f : this.filters) {
			result = result && f.filter(pMovie);
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
		boolean result = true;
		for(WatchListFilter f : this.filters) {
			result = result && f.filter(pTVShow);
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
		boolean result = true;
		for(WatchListFilter f : this.filters) {
			result = result && f.filter(pEpisode);
		}
		return result;
	}
}
