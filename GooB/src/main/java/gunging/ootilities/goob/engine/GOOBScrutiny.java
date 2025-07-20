package gunging.ootilities.goob.engine;

/**
 * The philosophy behind GooS was always to wing it if possible during production, but catching all
 * bugs during development. That's why the level of scrutiny exists, so that you can tell GooS to
 * behave as nicely or as pedantic as possible.
 *
 * @author Gunging
 * @since 1.0.0
 */
public enum GOOBScrutiny {

    /**
     * "I know what I'm doing"
     *
     * @since 1.0.0
     */
    MAXIMUM_SCRUTINY,

    /**
     * So you are a java developer hooking onto GooS, that's nice.
     * I recommend developing on this scrutiny so that it doesn't
     * come back to bite you later, but also you know you are doing
     * things the way they are supposed to be done.
     *
     * @since 1.0.0
     */
    DEVELOPMENT_HIGH,

    /**
     * This will only crash on egregious mistakes that are blatantly
     * bad or stupid, things that you should really check before
     * pushing to production.
     *
     * @since 1.0.0
     */
    TESTING_MID,

    /**
     * I will cover for you to the best of my abilities.
     *
     * @since 1.0.0
     */
    PRODUCTION_UNBREAKABLE
}
