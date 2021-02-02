package edu.kit.ipd.consistency_analyzer.agents;

import java.util.logging.Logger;

import edu.kit.ipd.consistency_analyzer.datastructures.IConnectionState;
import edu.kit.ipd.consistency_analyzer.datastructures.IModelState;
import edu.kit.ipd.consistency_analyzer.datastructures.IRecommendationState;
import edu.kit.ipd.consistency_analyzer.datastructures.IText;
import edu.kit.ipd.consistency_analyzer.datastructures.ITextState;

public class AgentDatastructure {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private IText text;
    private ITextState textState;
    private IModelState modelState;
    private IRecommendationState recommendationState;
    private IConnectionState connectionState;

    public AgentDatastructure() {
    }

    public AgentDatastructure createCopy() {
        AgentDatastructure data = new AgentDatastructure();
        data.text = text;
        data.textState = textState.createCopy();
        data.modelState = modelState.createCopy();
        data.recommendationState = recommendationState.createCopy();
        data.connectionState = connectionState.createCopy();
        return data;
    }

    public AgentDatastructure(IText text, ITextState textState, IModelState modelState, IRecommendationState recommendationState,
            IConnectionState connectionState) {
        this.text = text;
        this.textState = textState;
        this.modelState = modelState;
        this.recommendationState = recommendationState;
        this.connectionState = connectionState;
    }

    public IText getText() {
        return text;
    }

    public void setText(IText text) {
        this.text = text;
    }

    public ITextState getTextState() {
        return textState;
    }

    public void setTextState(ITextState textState) {
        this.textState = textState;
    }

    public IModelState getModelState() {
        return modelState;
    }

    public void setModelState(IModelState modelState) {
        this.modelState = modelState;
    }

    public IRecommendationState getRecommendationState() {
        return recommendationState;
    }

    public void setRecommendationState(IRecommendationState recommendationState) {
        this.recommendationState = recommendationState;
    }

    public IConnectionState getConnectionState() {
        return connectionState;
    }

    public void setConnectionState(IConnectionState connectionState) {
        this.connectionState = connectionState;
    }

    public void overwrite(AgentDatastructure newData) {
        text = newData.text;
        textState = newData.textState;
        modelState = newData.modelState;
        recommendationState = newData.recommendationState;
        connectionState = newData.connectionState;
    }

    public void extend(IModelState modelState) {
        if (null != this.modelState) {
            logger.warning("ModelState was overwritten.");
        } else {
            this.modelState = modelState;
        }
    }

    public void extend(IText text) {
        if (null != this.text) {
            logger.warning("Text was overwritten.");
        } else {
            this.text = text;
        }
    }

    public void extend(ITextState textState) {
        if (null != this.textState) {
            logger.warning("TextState was overwritten.");
        } else {
            this.textState = textState;
        }
    }

    public void extend(IRecommendationState recommendationState) {
        if (null != this.recommendationState) {
            logger.warning("RecommendationState was overwritten.");
        } else {
            this.recommendationState = recommendationState;
        }
    }

    public void extend(IConnectionState connectionState) {
        if (null != this.connectionState) {
            logger.warning("ConnectionState was overwritten.");
        } else {
            this.connectionState = connectionState;
        }
    }

}