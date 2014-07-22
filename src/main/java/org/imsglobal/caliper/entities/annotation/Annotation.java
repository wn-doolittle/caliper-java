package org.imsglobal.caliper.entities.annotation;

import org.imsglobal.caliper.entities.CaliperEntity;
import org.imsglobal.caliper.entities.schemadotorg.Thing;

/**
 * @author pnayak
 * 
 *         The super-class of all Annotation types.
 * 
 *         Direct sub-types can include - Hilight, Attachment, etc. - all of
 *         which are specified in the Caliper Annotation Metric Profile
 * 
 */
public class Annotation extends CaliperEntity implements Thing {

}
