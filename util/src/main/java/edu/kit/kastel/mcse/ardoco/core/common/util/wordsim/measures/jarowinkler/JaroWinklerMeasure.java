/* Licensed under MIT 2022. */
package edu.kit.kastel.mcse.ardoco.core.common.util.wordsim.measures.jarowinkler;

import edu.kit.kastel.mcse.ardoco.core.common.util.CommonTextToolsConfig;
import edu.kit.kastel.mcse.ardoco.core.common.util.wordsim.ComparisonContext;
import edu.kit.kastel.mcse.ardoco.core.common.util.wordsim.WordSimMeasure;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;

/**
 * This word similarity measure uses the jaro winkler algorithm to calculate similarity.
 */
public class JaroWinklerMeasure implements WordSimMeasure {

    private final JaroWinklerSimilarity jaroWinklerSimilarity = new JaroWinklerSimilarity();

    @Override public boolean areWordsSimilar(ComparisonContext ctx) {
        return jaroWinklerSimilarity.apply(ctx.firstTerm(), ctx.secondTerm()) >= CommonTextToolsConfig.JAROWINKLER_SIMILARITY_THRESHOLD;
    }

}