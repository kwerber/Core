/* Licensed under MIT 2022. */
package edu.kit.kastel.mcse.ardoco.core.common.util.wordsim.strategy;

import java.util.List;

import edu.kit.kastel.mcse.ardoco.core.common.util.wordsim.ComparisonContext;
import edu.kit.kastel.mcse.ardoco.core.common.util.wordsim.WordSimMeasure;

/**
 * This comparison strategy accepts any word pair as similar if at least one of the specified word similarity measures
 * also accept that word pair as similar.
 */
public class AtleastOneStrategy implements ComparisonStrategy {

    @Override
    public boolean areWordsSimilar(ComparisonContext ctx, List<WordSimMeasure> measures) {
        for (WordSimMeasure measure : measures) {
            boolean similar = measure.areWordsSimilar(ctx);

            if (similar) {
                return true;
            }
        }

        return false;
    }

}