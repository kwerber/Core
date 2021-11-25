package edu.kit.kastel.mcse.ardoco.core.tests.inconsistencies.eval.text;

import java.io.PrintStream;

import edu.kit.kastel.mcse.ardoco.core.common.AgentDatastructure;
import edu.kit.kastel.mcse.ardoco.core.inconsistency.NameInconsistency;
import edu.kit.kastel.mcse.ardoco.core.model.IModelConnector;
import edu.kit.kastel.mcse.ardoco.core.tests.Projects;
import edu.kit.kastel.mcse.ardoco.core.tests.inconsistencies.eval.AbstractEvalStrategy;
import edu.kit.kastel.mcse.ardoco.core.tests.inconsistencies.eval.EvaluationResult;
import edu.kit.kastel.mcse.ardoco.core.tests.inconsistencies.eval.GoldStandard;
import edu.kit.kastel.mcse.ardoco.core.text.IText;

public class SimplyGetItNaming extends AbstractEvalStrategy {

    @Override
    public EvaluationResult evaluate(Projects p, IModelConnector originalModel, IText originalText, GoldStandard gs, PrintStream os) {
        var originalData = new AgentDatastructure(originalText, null, runModelExtractor(originalModel), null, null, null);
        runTextExtractor(originalData);
        var original = runRecommendationConnectionInconsistency(originalData);

        var inconsistencies = original.getInconsistencyState().getInconsistencies();
        var namings = inconsistencies.select(NameInconsistency.class::isInstance).collect(NameInconsistency.class::cast);

        for (var naming : namings) {
            os.println(naming.getReason());
        }

        return null;
    }

}