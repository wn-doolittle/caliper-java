/**
 * 
 */
package org.imsglobal.caliper.entities.outcome;

import org.imsglobal.caliper.entities.CaliperAgent;
import org.imsglobal.caliper.entities.CaliperEntity;

/**
 * @author pnayak
 * 
 *         Representation of a Result. Result's are generated as part of an
 *         interaction represented by an OutcomeEvent
 */
public class Result extends CaliperEntity {

	// TODO - need to include target, learningObjective and scoreConstraints
	// from metric profile

	private double normalScore, penaltyScore, extraCreditScore, totalScore,
			curvedTotalScore, curveFactor;
	private String comment;
	private CaliperAgent scoredBy;

	/**
	 * @return the normalScore
	 */
	public double getNormalScore() {
		return normalScore;
	}

	/**
	 * @param normalScore
	 *            the normalScore to set
	 */
	public void setNormalScore(double normalScore) {
		this.normalScore = normalScore;
	}

	/**
	 * @return the penaltyScore
	 */
	public double getPenaltyScore() {
		return penaltyScore;
	}

	/**
	 * @param penaltyScore
	 *            the penaltyScore to set
	 */
	public void setPenaltyScore(double penaltyScore) {
		this.penaltyScore = penaltyScore;
	}

	/**
	 * @return the extraCreditScore
	 */
	public double getExtraCreditScore() {
		return extraCreditScore;
	}

	/**
	 * @param extraCreditScore
	 *            the extraCreditScore to set
	 */
	public void setExtraCreditScore(double extraCreditScore) {
		this.extraCreditScore = extraCreditScore;
	}

	/**
	 * @return the totalScore
	 */
	public double getTotalScore() {
		return totalScore;
	}

	/**
	 * @param totalScore
	 *            the totalScore to set
	 */
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * @return the curvedTotalScore
	 */
	public double getCurvedTotalScore() {
		return curvedTotalScore;
	}

	/**
	 * @param curvedTotalScore
	 *            the curvedTotalScore to set
	 */
	public void setCurvedTotalScore(double curvedTotalScore) {
		this.curvedTotalScore = curvedTotalScore;
	}

	/**
	 * @return the curveFactor
	 */
	public double getCurveFactor() {
		return curveFactor;
	}

	/**
	 * @param curveFactor
	 *            the curveFactor to set
	 */
	public void setCurveFactor(double curveFactor) {
		this.curveFactor = curveFactor;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the scoredBy
	 */
	public CaliperAgent getScoredBy() {
		return scoredBy;
	}

	/**
	 * @param scoredBy
	 *            the scoredBy to set
	 */
	public void setScoredBy(CaliperAgent scoredBy) {
		this.scoredBy = scoredBy;
	}

}
