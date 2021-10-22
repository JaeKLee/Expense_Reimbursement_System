package model;

import java.sql.Timestamp;

public class Reimbursement {

	// Variables
	private int reimbId;
	private double reimbAmount;
	private Timestamp submittedTime;
	private Timestamp resolvedTime;
	private String description;
	private int reimbAuthorId;
	private int reimbResolverId;
	private int reimbStatusId;
	private int reimbTypeId;
	private String reimbResolverFirstName;
	private String reimbResolverLastName;
	private String reimbAuthorFirstName;
	private String reimbAuthorLastName;
	private String reimbType;
	private String reimbStatus;

	// Constructors
	public Reimbursement() {
	}

	public Reimbursement(
			  int reimbId
			, double reimbAmount
			, Timestamp submittedTime
			, Timestamp resolvedTime
			, String description
			, int reimbAuthorId
			, int reimbResolverId
			, int reimbStatusId
			, int reimbTypeId
			, String reimbResolverFirstName
			, String reimbResolverLastName
			, String reimbAuthorFirstName
			, String reimbAuthorLastName
			, String reimbType
			, String reimbStatus) {
		//All args (yes resolver and description
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.submittedTime = submittedTime;
		this.resolvedTime = resolvedTime;
		this.description = description;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbResolverId = reimbResolverId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimbResolverFirstName = reimbResolverFirstName;
		this.reimbResolverLastName = reimbResolverLastName;
		this.reimbAuthorFirstName = reimbAuthorFirstName;
		this.reimbAuthorLastName = reimbAuthorLastName;
		this.reimbType = reimbType;
		this.reimbStatus = reimbStatus;
	}
	
	public Reimbursement(
			  int reimbId
			, double reimbAmount
			, Timestamp submittedTime
			, Timestamp resolvedTime
			, int reimbAuthorId
			, int reimbResolverId
			, int reimbStatusId
			, int reimbTypeId
			, String reimbResolverFirstName
			, String reimbResolverLastName
			, String reimbAuthorFirstName
			, String reimbAuthorLastName
			, String reimbType
			, String reimbStatus) {
		//yes resolver and NO description
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.submittedTime = submittedTime;
		this.resolvedTime = resolvedTime;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbResolverId = reimbResolverId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimbResolverFirstName = reimbResolverFirstName;
		this.reimbResolverLastName = reimbResolverLastName;
		this.reimbAuthorFirstName = reimbAuthorFirstName;
		this.reimbAuthorLastName = reimbAuthorLastName;
		this.reimbType = reimbType;
		this.reimbStatus = reimbStatus;
	}

	public Reimbursement(
			  int reimbId
			, double reimbAmount
			, Timestamp submittedTime
			, String description
			, int reimbAuthorId
			, int reimbStatusId
			, int reimbTypeId
			, String reimbAuthorFirstName
			, String reimbAuthorLastName
			, String reimbType
			, String reimbStatus) {
		//no resolver and yes description
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.submittedTime = submittedTime;
		this.description = description;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimbAuthorFirstName = reimbAuthorFirstName;
		this.reimbAuthorLastName = reimbAuthorLastName;
		this.reimbType = reimbType;
		this.reimbStatus = reimbStatus;
	}

	public Reimbursement(
			  int reimbId
			, double reimbAmount
			, Timestamp submittedTime
			, int reimbAuthorId
			, int reimbStatusId
			, int reimbTypeId
			, String reimbAuthorFirstName
			, String reimbAuthorLastName
			, String reimbType
			, String reimbStatus) {
		//no resolver and NO description
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.submittedTime = submittedTime;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimbAuthorFirstName = reimbAuthorFirstName;
		this.reimbAuthorLastName = reimbAuthorLastName;
		this.reimbType = reimbType;
		this.reimbStatus = reimbStatus;
	}
	
	
	
	//NO reimbursement ID Constructors
	public Reimbursement(
			 double reimbAmount
			, Timestamp submittedTime
			, Timestamp resolvedTime
			, String description
			, int reimbAuthorId
			, int reimbResolverId
			, int reimbStatusId
			, int reimbTypeId
			, String reimbResolverFirstName
			, String reimbResolverLastName
			, String reimbAuthorFirstName
			, String reimbAuthorLastName
			, String reimbType
			, String reimbStatus) {
		//All args (yes resolver and description
		super();
		this.reimbAmount = reimbAmount;
		this.submittedTime = submittedTime;
		this.resolvedTime = resolvedTime;
		this.description = description;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbResolverId = reimbResolverId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimbResolverFirstName = reimbResolverFirstName;
		this.reimbResolverLastName = reimbResolverLastName;
		this.reimbAuthorFirstName = reimbAuthorFirstName;
		this.reimbAuthorLastName = reimbAuthorLastName;
		this.reimbType = reimbType;
		this.reimbStatus = reimbStatus;
	}
	
	public Reimbursement(
			 double reimbAmount
			, Timestamp submittedTime
			, Timestamp resolvedTime
			, int reimbAuthorId
			, int reimbResolverId
			, int reimbStatusId
			, int reimbTypeId
			, String reimbResolverFirstName
			, String reimbResolverLastName
			, String reimbAuthorFirstName
			, String reimbAuthorLastName
			, String reimbType
			, String reimbStatus) {
		//yes resolver and NO description
		super();
		this.reimbAmount = reimbAmount;
		this.submittedTime = submittedTime;
		this.resolvedTime = resolvedTime;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbResolverId = reimbResolverId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimbResolverFirstName = reimbResolverFirstName;
		this.reimbResolverLastName = reimbResolverLastName;
		this.reimbAuthorFirstName = reimbAuthorFirstName;
		this.reimbAuthorLastName = reimbAuthorLastName;
		this.reimbType = reimbType;
		this.reimbStatus = reimbStatus;
	}
	
	public Reimbursement(
			  double reimbAmount
			, Timestamp submittedTime
			, String description
			, int reimbAuthorId
			, int reimbStatusId
			, int reimbTypeId
			, String reimbAuthorFirstName
			, String reimbAuthorLastName
			, String reimbType
			, String reimbStatus) {
		//no resolver and yes description
		super();
		this.reimbAmount = reimbAmount;
		this.submittedTime = submittedTime;
		this.description = description;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimbAuthorFirstName = reimbAuthorFirstName;
		this.reimbAuthorLastName = reimbAuthorLastName;
		this.reimbType = reimbType;
		this.reimbStatus = reimbStatus;
	}
	
	public Reimbursement(
			  double reimbAmount
			, Timestamp submittedTime
			, int reimbAuthorId
			, int reimbStatusId
			, int reimbTypeId
			, String reimbAuthorFirstName
			, String reimbAuthorLastName
			, String reimbType
			, String reimbStatus) {
		//no resolver and NO description
		super();
		this.reimbAmount = reimbAmount;
		this.submittedTime = submittedTime;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimbAuthorFirstName = reimbAuthorFirstName;
		this.reimbAuthorLastName = reimbAuthorLastName;
		this.reimbType = reimbType;
		this.reimbStatus = reimbStatus;
	}
	public Reimbursement(
			double reimbAmount
			, Timestamp submittedTime
			, int reimbAuthorId
			, int reimbStatusId
			, int reimbTypeId
			) {
		//no resolver and NO description
		super();
		this.reimbAmount = reimbAmount;
		this.submittedTime = submittedTime;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}
	public Reimbursement(
			double reimbAmount
			, Timestamp submittedTime
			, String description
			, int reimbAuthorId
			, int reimbStatusId
			, int reimbTypeId
			) {
		//no resolver and NO description
		super();
		this.reimbAmount = reimbAmount;
		this.submittedTime = submittedTime;
		this.description = description;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}
	public Reimbursement(
			double reimbAmount
			, String description
			, int reimbTypeId
			) {
		//no resolver and NO description
		super();
		this.reimbAmount = reimbAmount;
		this.description = description;
		this.reimbTypeId = reimbTypeId;
	}
	
	

	// Getters and Setters
	public int getReimbId() {
		return reimbId;
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Timestamp getSubmittedTime() {
		return submittedTime;
	}

	public void setSubmittedTime(Timestamp submittedTime) {
		this.submittedTime = submittedTime;
	}

	public Timestamp getResolvedTime() {
		return resolvedTime;
	}

	public void setResolvedTime(Timestamp resolvedTime) {
		this.resolvedTime = resolvedTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReimbAuthorId() {
		return reimbAuthorId;
	}

	public void setReimbAuthorId(int reimbAuthorId) {
		this.reimbAuthorId = reimbAuthorId;
	}

	public int getReimbResolverId() {
		return reimbResolverId;
	}

	public void setReimbResolverId(int reimbResolverId) {
		this.reimbResolverId = reimbResolverId;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public String getReimbResolverFirstName() {
		return reimbResolverFirstName;
	}

	public void setReimbResolverFirstName(String reimbResolverFirstName) {
		this.reimbResolverFirstName = reimbResolverFirstName;
	}

	public String getReimbResolverLastName() {
		return reimbResolverLastName;
	}

	public void setReimbResolverLastName(String reimbResolverLastName) {
		this.reimbResolverLastName = reimbResolverLastName;
	}

	public String getReimbAuthorFirstName() {
		return reimbAuthorFirstName;
	}

	public void setReimbAuthorFirstName(String reimbAuthorFirstName) {
		this.reimbAuthorFirstName = reimbAuthorFirstName;
	}

	public String getReimbAuthorLastName() {
		return reimbAuthorLastName;
	}

	public void setReimbAuthorLastName(String reimbAuthorLastName) {
		this.reimbAuthorLastName = reimbAuthorLastName;
	}

	@Override
	public String toString() {
		return "\nReimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", submittedTime="
				+ submittedTime + ", resolvedTime=" + resolvedTime + ", description=" + description + ", reimbAuthorId="
				+ reimbAuthorId + ", reimbResolverId=" + reimbResolverId + ", reimbStatusId=" + reimbStatusId
				+ ", reimbTypeId=" + reimbTypeId + ", reimbResolverFirstName=" + reimbResolverFirstName
				+ ", reimbResolverLastName=" + reimbResolverLastName + ", reimbAuthorFirstName=" + reimbAuthorFirstName
				+ ", reimbAuthorLastName=" + reimbAuthorLastName + ", reimbType=" + reimbType + ", reimbStatus="
				+ reimbStatus + "]";
	}

}
