/* Licensed under MIT 2021-2022. */
package edu.kit.kastel.mcse.ardoco.core.textextraction;

import java.util.List;
import java.util.Map;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import edu.kit.kastel.informalin.framework.configuration.Configurable;
import edu.kit.kastel.mcse.ardoco.core.api.agent.IAgent;
import edu.kit.kastel.mcse.ardoco.core.api.agent.TextAgent;
import edu.kit.kastel.mcse.ardoco.core.api.data.DataStructure;
import edu.kit.kastel.mcse.ardoco.core.api.stage.AbstractExecutionStage;
import edu.kit.kastel.mcse.ardoco.core.textextraction.agents.ComputerScienceWordsAgent;
import edu.kit.kastel.mcse.ardoco.core.textextraction.agents.InitialTextAgent;
import edu.kit.kastel.mcse.ardoco.core.textextraction.agents.PhraseAgent;

/**
 * The Class TextExtractor.
 */
public class TextExtraction extends AbstractExecutionStage {

    private final MutableList<TextAgent> agents = Lists.mutable.of(new InitialTextAgent(), new PhraseAgent(), new ComputerScienceWordsAgent());

    @Configurable
    private List<String> enabledAgents = agents.collect(IAgent::getId);

    /**
     * Instantiates a new text extractor.
     */
    public TextExtraction() {
        // empty
    }

    @Override
    public void execute(DataStructure data, Map<String, String> additionalSettings) {
        // Init new states
        data.setTextState(new TextState(additionalSettings));

        this.applyConfiguration(additionalSettings);
        for (TextAgent agent : findByClassName(enabledAgents, agents)) {
            agent.applyConfiguration(additionalSettings);
            agent.execute(data);
        }
    }
}
