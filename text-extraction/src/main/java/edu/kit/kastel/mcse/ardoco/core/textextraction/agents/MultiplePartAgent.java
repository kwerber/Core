package edu.kit.kastel.mcse.ardoco.core.textextraction.agents;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.kohsuke.MetaInfServices;

import edu.kit.kastel.mcse.ardoco.core.common.Configuration;
import edu.kit.kastel.mcse.ardoco.core.common.util.CommonUtilities;
import edu.kit.kastel.mcse.ardoco.core.text.IText;
import edu.kit.kastel.mcse.ardoco.core.text.IWord;
import edu.kit.kastel.mcse.ardoco.core.textextraction.GenericTextConfig;
import edu.kit.kastel.mcse.ardoco.core.textextraction.INounMapping;
import edu.kit.kastel.mcse.ardoco.core.textextraction.ITextState;
import edu.kit.kastel.mcse.ardoco.core.textextraction.MappingKind;
import edu.kit.kastel.mcse.ardoco.core.textextraction.TextAgent;

/**
 * WORK IN PROGRESS.
 *
 * @author Sophie
 */
@MetaInfServices(TextAgent.class)
public class MultiplePartAgent extends TextAgent {

    private double probability;

    /**
     * Prototype constructor.
     */
    public MultiplePartAgent() {
        super(GenericTextConfig.class);
    }

    /**
     * Creates a new multiple part solver.
     *
     * @param text      the text
     * @param textState the text extraction state
     * @param config    the module configuration
     */
    private MultiplePartAgent(IText text, ITextState textState, GenericTextConfig config) {
        super(GenericTextConfig.class, text, textState);
        probability = config.multiplePartSolverProbability;
    }

    @Override
    public TextAgent create(IText text, ITextState textExtractionState, Configuration config) {
        return new MultiplePartAgent(text, textExtractionState, (GenericTextConfig) config);
    }

    @Override
    public void exec() {
        // TODO make this work properly and not clutter everything
        // TODO disabled in the config for now
        // searchForName();
        searchForType();
    }

    private void searchForName() {
        for (INounMapping nameMap : textState.getNames()) {
            ImmutableList<IWord> nameNodes = Lists.immutable.withAll(nameMap.getWords());
            for (IWord word : nameNodes) {
                incorporatePreWordOfWordForName(word);
            }
        }
    }

    private void searchForType() {
        for (INounMapping typeMap : textState.getTypes()) {
            ImmutableList<IWord> typeNodes = Lists.immutable.withAll(typeMap.getWords());
            for (IWord n : typeNodes) {
                incorporatePreWordOfWordForType(n);
            }
        }
    }

    private void incorporatePreWordOfWordForName(IWord word) {
        IWord previousWord = word.getPreWord();
        boolean nodeIsContainedByNounMappings = textState.isNodeContainedByNounMappings(previousWord);
        boolean nodeIsContainedByTypeNodes = textState.isNodeContainedByTypeNodes(previousWord);
        if (previousWord != null && nodeIsContainedByNounMappings && !nodeIsContainedByTypeNodes) {
            String ref = previousWord.getText() + " " + word.getText();
            addTerm(ref, previousWord, word, MappingKind.NAME);
        }
    }

    private void incorporatePreWordOfWordForType(IWord n) {
        IWord pre = n.getPreWord();
        boolean nodeIsContainedByNounMappings = textState.isNodeContainedByNounMappings(pre);
        boolean nodeIsContainedByNameNodes = textState.isNodeContainedByNameNodes(pre);
        if (pre != null && nodeIsContainedByNounMappings && !nodeIsContainedByNameNodes) {
            String ref = pre.getText() + " " + n.getText();
            addTerm(ref, pre, n, MappingKind.TYPE);
        }
    }

    private void addTerm(String ref, IWord pre, IWord n, MappingKind kind) {
        ImmutableList<INounMapping> preMappings = textState.getNounMappingsByWord(pre);
        ImmutableList<INounMapping> nMappings = textState.getNounMappingsByWord(n);

        ImmutableList<ImmutableList<INounMapping>> cartesianProduct = CommonUtilities.cartesianProduct(preMappings, Lists.immutable.with(nMappings));

        for (ImmutableList<INounMapping> possibleCombination : cartesianProduct) {
            textState.addTerm(ref, possibleCombination.get(0), possibleCombination.get(1), kind, probability);
        }

    }
}
