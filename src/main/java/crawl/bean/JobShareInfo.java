package crawl.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "job_share_info")
public class JobShareInfo {
	/**共用欄位**/
	
		@Id
		@Column(name = "job_id")
		private String jobId;
		
		@Column(name = "share_date")
		private String shareDate;
		
		@Column(name = "share_type")
		private String shareType;
		
//		公司：
		@Column(name = "company_name")
		private String companyName;
		
//		工作/面試地區：
		@Column(name = "area")
		private String area;
		
//		應徵職稱：
		@Column(name = "job_title")
		private String jobTitle;
		
//		相關職務工作經驗：
		@Column(name = "relative_experience_year")
		private Float relativeExperienceYear;
		
//		查詢時間
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "search_time")
		private Date searchTime;

//		被查詢次數
		
		@Column(name = "searched_count")
		private Integer searchedCount;
		
//		分享內容
		@Column(name = "share_content")
		private String shareContent;
		
		
		
		/**面試欄位**/
		
//		面試時間：
		@Column(name = "interview_time")
		private String interviewTime;
		
//		面試結果：
		@Column(name = "result")
		private String result;
		
//		整體面試滿意度：?/5
		@Column(name = "satisfaction_score")
		private Integer satisfactionScore;
		
//		特殊問題
		@Column(name = "odd_questions")
		private String oddQuestions;
		
		/**工作分享欄位**/

//		最高學歷：
		@Column(name = "highest_degree")
		private String highestDegree;

//		一週工時：
		@Column(name = "per_week_work_hours")
		private Integer perWeekWorkHours;

//		待遇		
		@Column(name = "treatment")
		private String treatment;

//		是否推薦此工作：
		@Column(name = "is_good")
		private Integer isGood;


}
