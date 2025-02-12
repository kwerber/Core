/* Licensed under MIT 2022. */
package edu.kit.kastel.mcse.ardoco.core.inconsistency.types;

import org.junit.jupiter.api.BeforeEach;

import edu.kit.kastel.mcse.ardoco.core.api.agent.IClaimant;
import edu.kit.kastel.mcse.ardoco.core.api.data.inconsistency.IInconsistency;

/**
 * This class tests the record MissingModelInconsistency.
 * 
 * @author Jan Keim
 */
public class MissingModelInstanceInconsistencyTest extends AbstractInconsistencyTypeTest implements IClaimant {

    private MissingModelInstanceInconsistency missingModelInstanceInconsistency;

    @BeforeEach
    void beforeEach() {
        missingModelInstanceInconsistency = new MissingModelInstanceInconsistency("inconsistency", 1, 1.0);
    }

    @Override
    protected IInconsistency getInconsistency() {
        return missingModelInstanceInconsistency;
    }

    @Override
    protected String getTypeString() {
        return "MissingModelInstance";
    }

    @Override
    protected String getReasonString() {
        return "Text indicates (confidence: 1.0) that \"inconsistency\" (sentence 1) should be contained in the model(s) but could not be found.";

    }

    @Override
    protected IInconsistency getUnequalInconsistency() {
        return new MissingModelInstanceInconsistency("otherInconsistency", 1, 1.0);
    }

    @Override
    protected IInconsistency getEqualInconsistency() {
        return new MissingModelInstanceInconsistency("inconsistency", 1, 1.0);
    }

    @Override
    protected String[] getFileOutputEntry() {
        return new String[] { getTypeString(), "1", "name", Integer.toString(1), Double.toString(0.0) };
    }

}
