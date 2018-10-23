package com.geo.entities.quartz;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the qrtz_triggers database table.
 * 
 */
@Entity
@Table(name="qrtz_triggers")
@NamedQuery(name="QrtzTrigger.findAll", query="SELECT q FROM QrtzTrigger q")
public class QrtzTrigger implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QrtzTriggerPK id;

	@Column(name="CALENDAR_NAME")
	private String calendarName;

	private String description;

	@Column(name="END_TIME")
	private BigInteger endTime;

	@Lob
	@Column(name="JOB_DATA")
	private byte[] jobData;

	@Column(name="MISFIRE_INSTR")
	private short misfireInstr;

	@Column(name="NEXT_FIRE_TIME")
	private BigInteger nextFireTime;

	@Column(name="PREV_FIRE_TIME")
	private BigInteger prevFireTime;

	private int priority;

	@Column(name="START_TIME")
	private BigInteger startTime;

	@Column(name="TRIGGER_STATE")
	private String triggerState;

	@Column(name="TRIGGER_TYPE")
	private String triggerType;

	//bi-directional many-to-one association to QrtzBlobTrigger
	@OneToMany(mappedBy="qrtzTrigger")
	private List<QrtzBlobTrigger> qrtzBlobTriggers;

	//bi-directional many-to-one association to QrtzCronTrigger
	@OneToMany(mappedBy="qrtzTrigger")
	private List<QrtzCronTrigger> qrtzCronTriggers;

	//bi-directional many-to-one association to QrtzSimpleTrigger
	@OneToMany(mappedBy="qrtzTrigger")
	private List<QrtzSimpleTrigger> qrtzSimpleTriggers;

	//bi-directional many-to-one association to QrtzSimpropTrigger
	@OneToMany(mappedBy="qrtzTrigger")
	private List<QrtzSimpropTrigger> qrtzSimpropTriggers;

	//bi-directional many-to-one association to QrtzJobDetail
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="JOB_GROUP", referencedColumnName="JOB_GROUP",insertable=false,updatable=false),
		@JoinColumn(name="JOB_NAME", referencedColumnName="JOB_NAME",insertable=false,updatable=false),
		@JoinColumn(name="SCHED_NAME", referencedColumnName="SCHED_NAME",insertable=false,updatable=false)
		})
	private QrtzJobDetail qrtzJobDetail;

	public QrtzTrigger() {
	}

	public QrtzTriggerPK getId() {
		return this.id;
	}

	public void setId(QrtzTriggerPK id) {
		this.id = id;
	}

	public String getCalendarName() {
		return this.calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getEndTime() {
		return this.endTime;
	}

	public void setEndTime(BigInteger endTime) {
		this.endTime = endTime;
	}

	public byte[] getJobData() {
		return this.jobData;
	}

	public void setJobData(byte[] jobData) {
		this.jobData = jobData;
	}

	public short getMisfireInstr() {
		return this.misfireInstr;
	}

	public void setMisfireInstr(short misfireInstr) {
		this.misfireInstr = misfireInstr;
	}

	public BigInteger getNextFireTime() {
		return this.nextFireTime;
	}

	public void setNextFireTime(BigInteger nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public BigInteger getPrevFireTime() {
		return this.prevFireTime;
	}

	public void setPrevFireTime(BigInteger prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public BigInteger getStartTime() {
		return this.startTime;
	}

	public void setStartTime(BigInteger startTime) {
		this.startTime = startTime;
	}

	public String getTriggerState() {
		return this.triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	public String getTriggerType() {
		return this.triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public List<QrtzBlobTrigger> getQrtzBlobTriggers() {
		return this.qrtzBlobTriggers;
	}

	public void setQrtzBlobTriggers(List<QrtzBlobTrigger> qrtzBlobTriggers) {
		this.qrtzBlobTriggers = qrtzBlobTriggers;
	}

	public QrtzBlobTrigger addQrtzBlobTrigger(QrtzBlobTrigger qrtzBlobTrigger) {
		getQrtzBlobTriggers().add(qrtzBlobTrigger);
		qrtzBlobTrigger.setQrtzTrigger(this);

		return qrtzBlobTrigger;
	}

	public QrtzBlobTrigger removeQrtzBlobTrigger(QrtzBlobTrigger qrtzBlobTrigger) {
		getQrtzBlobTriggers().remove(qrtzBlobTrigger);
		qrtzBlobTrigger.setQrtzTrigger(null);

		return qrtzBlobTrigger;
	}

	public List<QrtzCronTrigger> getQrtzCronTriggers() {
		return this.qrtzCronTriggers;
	}

	public void setQrtzCronTriggers(List<QrtzCronTrigger> qrtzCronTriggers) {
		this.qrtzCronTriggers = qrtzCronTriggers;
	}

	public QrtzCronTrigger addQrtzCronTrigger(QrtzCronTrigger qrtzCronTrigger) {
		getQrtzCronTriggers().add(qrtzCronTrigger);
		qrtzCronTrigger.setQrtzTrigger(this);

		return qrtzCronTrigger;
	}

	public QrtzCronTrigger removeQrtzCronTrigger(QrtzCronTrigger qrtzCronTrigger) {
		getQrtzCronTriggers().remove(qrtzCronTrigger);
		qrtzCronTrigger.setQrtzTrigger(null);

		return qrtzCronTrigger;
	}

	public List<QrtzSimpleTrigger> getQrtzSimpleTriggers() {
		return this.qrtzSimpleTriggers;
	}

	public void setQrtzSimpleTriggers(List<QrtzSimpleTrigger> qrtzSimpleTriggers) {
		this.qrtzSimpleTriggers = qrtzSimpleTriggers;
	}

	public QrtzSimpleTrigger addQrtzSimpleTrigger(QrtzSimpleTrigger qrtzSimpleTrigger) {
		getQrtzSimpleTriggers().add(qrtzSimpleTrigger);
		qrtzSimpleTrigger.setQrtzTrigger(this);

		return qrtzSimpleTrigger;
	}

	public QrtzSimpleTrigger removeQrtzSimpleTrigger(QrtzSimpleTrigger qrtzSimpleTrigger) {
		getQrtzSimpleTriggers().remove(qrtzSimpleTrigger);
		qrtzSimpleTrigger.setQrtzTrigger(null);

		return qrtzSimpleTrigger;
	}

	public List<QrtzSimpropTrigger> getQrtzSimpropTriggers() {
		return this.qrtzSimpropTriggers;
	}

	public void setQrtzSimpropTriggers(List<QrtzSimpropTrigger> qrtzSimpropTriggers) {
		this.qrtzSimpropTriggers = qrtzSimpropTriggers;
	}

	public QrtzSimpropTrigger addQrtzSimpropTrigger(QrtzSimpropTrigger qrtzSimpropTrigger) {
		getQrtzSimpropTriggers().add(qrtzSimpropTrigger);
		qrtzSimpropTrigger.setQrtzTrigger(this);

		return qrtzSimpropTrigger;
	}

	public QrtzSimpropTrigger removeQrtzSimpropTrigger(QrtzSimpropTrigger qrtzSimpropTrigger) {
		getQrtzSimpropTriggers().remove(qrtzSimpropTrigger);
		qrtzSimpropTrigger.setQrtzTrigger(null);

		return qrtzSimpropTrigger;
	}

	public QrtzJobDetail getQrtzJobDetail() {
		return this.qrtzJobDetail;
	}

	public void setQrtzJobDetail(QrtzJobDetail qrtzJobDetail) {
		this.qrtzJobDetail = qrtzJobDetail;
	}

}