
package acme.entities.agents;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidScore;
import acme.client.components.validation.ValidString;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TrackingLog extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	@Temporal(TemporalType.TIMESTAMP)
	@ValidMoment
	private Date				updateMoment;

	@Mandatory
	@ValidString(min = 1, max = 50, message = "acme.validation.tracking-log.steps-length")
	@Automapped
	private String				steps;

	@Mandatory
	@ValidScore
	@Automapped
	private double				resolutionPercentage;

	@Mandatory
	@Valid
	@Automapped
	private TrackingLogStatus	status;

	@Optional
	@ValidString(min = 0, max = 255, message = "acme.validation.tracking-log.resolution-length")
	@Automapped
	private String				resolution;

	// Relationships ----------------------------------------------------------

	@ManyToOne(optional = false)
	@Valid
	@Mandatory
	private Claim				trackingSteps;

	// Tracking Log Status Type -------------------------------------------------------------


	public enum TrackingLogStatus {
		PENDING, ACCEPTED, REJECTED
	}

}
