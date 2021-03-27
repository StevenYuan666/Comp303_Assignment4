package Assignment4;
public class FirstEpisodeFilterStrategy implements WatchListFilter{
	//Since we only filter the first episode in the tvshow, so no need for constructor
	//The default constructor is good enough

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
		return false;
	}

	/**
     * Indicates whether a Watchable elements should be included in the watchlist.
     *
     * @param pTVShow
     *            a Watchable to potentially include in the Watchlist
     * @pre pTVShowe != null
     * @return true if the Watchable must be included, false otherwise
     */
	@Override
	public boolean filter(TVShow pTVShow) {
		assert pTVShow != null;
		//If the size greater than zero, there's must be a first episode in the TVShow
		return pTVShow.getTotalCount() > 0;
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
		//since we start from index 1
		return (pEpisode.getEpisodeNumber() == 1);
	}

}
