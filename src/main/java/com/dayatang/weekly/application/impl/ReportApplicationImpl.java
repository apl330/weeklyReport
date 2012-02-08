package com.dayatang.weekly.application.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.dayatang.domain.AbstractEntity;
import com.dayatang.weekly.application.ReportApplication;
import com.dayatang.weekly.domain.Attachment;
import com.dayatang.weekly.domain.WeeklyReport;

public class ReportApplicationImpl implements ReportApplication {

	private Logger logger = LoggerFactory.getLogger(ReportApplicationImpl.class);

	@Override
	public void saveEntity(AbstractEntity entity) {
		entity.save();
		logger.info("Save entity " + entity.getClass().getSimpleName() + " with id: " + entity.getId());
	}

	@Override
	public void removeEntity(AbstractEntity entity) {
		entity.remove();
		logger.info("Remove entity " + entity.getClass().getSimpleName() + " with id: " + entity.getId());
	}
	@Transactional(readOnly = false)
	public void saveReportAsDraft(WeeklyReport weeklyReport) {
		weeklyReport.saveAsDraft();
	}

	public void submitReport(WeeklyReport weeklyReport) {
		weeklyReport.submit();
	}

	public void commentReport(WeeklyReport weeklyReport) {
		weeklyReport.makeComment();
	}

	public void saveAttachment(Attachment attachment) {
		attachment.save();
	}

	public void removeAttachment(Attachment attachment) {
		attachment.remove();
	}
	
}
