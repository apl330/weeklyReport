package com.dayatang.weekly.application;

import com.dayatang.domain.AbstractEntity;
import com.dayatang.weekly.domain.Attachment;
import com.dayatang.weekly.domain.WeeklyReport;

/**
 * 应用层门面接口。封装对领域层的访问。
 * 
 * 
 */
public interface ReportApplication {

	/**
	 * 保存实体到仓储中
	 * 
	 * @param entity
	 */
	void saveEntity(AbstractEntity entity);

	/**
	 * 从仓储中删除实体
	 * 
	 * @param entity
	 */
	void removeEntity(AbstractEntity entity);
	
	void saveReportAsDraft(WeeklyReport weeklyReport);
	
	void submitReport(WeeklyReport weeklyReport);
	
	void commentReport(WeeklyReport weeklyReport);
	
	void saveAttachment(Attachment attachment);
	
	void removeAttachment(Attachment attachment);
}
