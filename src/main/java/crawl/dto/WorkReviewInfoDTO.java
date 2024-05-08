package crawl.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkReviewInfoDTO {
	
	private String jobId;
	
	private String shareDate;
	private String pageTitle;
	
	private String shareType;
	
//	公司：
	private String companyName;
	
//	工作/面試地區：
	private String area;
	
//	應徵職稱：
	private String jobTitle;
	
//	相關職務工作經驗：
	private Float relativeExperienceYear;
	
//	查詢時間
	private Date searchTime;

//	被查詢次數
	private Integer searchedCount;
	
//	分享內容
	private String shareContent;

//	最高學歷：
	private String highestDegree;

//	一週工時：
	private Integer perWeekWorkHours;

	
	private String treatment;

//	是否推薦此工作：
	private Integer isGood;
	

}
