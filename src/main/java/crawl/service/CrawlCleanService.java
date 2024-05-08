package crawl.service;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crawl.bean.JobShareInfo;
import crawl.dto.JobInterviewInfoDTO;
import crawl.dto.WorkReviewInfoDTO;
import crawl.model.JobDataRepo;

@Service
public class CrawlCleanService {
	
	@Autowired
	JobDataRepo jobRepo;
	
	
	JobShareInfo jobBean=new JobShareInfo();
	
	private final ExecutorService executor = Executors.newFixedThreadPool(2); // 創建一個大小為 2 的固定線程池

	public Object ReturnService(String url) {
		//判斷網址是否存在資料庫，是取出資料回傳，不是則進入formatData
		if(checkIsExistInTable(url)) {
			
			if(jobBean.getShareType().equals("面試經驗")) {
				JobInterviewInfoDTO interview=new JobInterviewInfoDTO();
				BeanUtils.copyProperties(jobBean,interview);
				return interview;
			}else if(jobBean.getShareType().equals("工作心得")) {
				WorkReviewInfoDTO review=new WorkReviewInfoDTO();
				BeanUtils.copyProperties(jobBean,review);
				return review;
			}
			
			
		}else {
			return formatData(url);
			
		}
		return null;
	}
	
	//確認這個網址是否存在資料庫，若存在，搜尋次數++存入
	public Boolean checkIsExistInTable(String url) {
		String jid=url.replace("https://www.goodjob.life/experiences/", "");
		
		Optional<JobShareInfo> data = jobRepo.findById(jid);
		if(data.isEmpty()) {
			return false;
		}else {
			jobBean=data.get();	
			jobBean.setSearchedCount(jobBean.getSearchedCount()+1);
			jobBean.setJobId(jid);
			jobRepo.save(jobBean);
			return true;
		}	
	}
	
	//決定要用哪個方法（面試或工作經驗）
	public Object formatData(String url) {
		
		String jid=url.replace("https://www.goodjob.life/experiences/", "");
		try {
			Document doc=Jsoup.connect(url).get();
			Elements type=doc.select(".src-components-ExperienceDetail-Heading-__Heading-module___badge");
			
			if("面試經驗".equals(type.html())) {
				JobInterviewInfoDTO interview = formatInterviewInfo(doc);
				BeanUtils.copyProperties(interview, jobBean);
				jobBean.setShareType(type.html());
				jobBean.setSearchedCount(1);
				jobBean.setJobId(jid);
				executor.submit(() -> {
		            jobRepo.save(jobBean);
		        });
				return interview;
			}else if("工作心得".equals(type.html())) {
				WorkReviewInfoDTO review = formatWorkReviewInfo(doc);
				BeanUtils.copyProperties(review, jobBean);
				jobBean.setShareType(type.html());
				jobBean.setSearchedCount(1);
				jobBean.setJobId(jid);
				executor.submit(() -> {
		            jobRepo.save(jobBean);
		        });
				return review;
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public JobInterviewInfoDTO formatInterviewInfo(Document doc) {
		
		JobInterviewInfoDTO jDto=new JobInterviewInfoDTO();
		
		jDto.setShareDate(doc.select(".src-components-ExperienceDetail-Article-__Article-module___date").html());
		Elements contents=doc.select(".src-components-ExperienceDetail-Article-__InfoBlock-module___content");
		
		jDto.setCompanyName(contents.get(0).text());
		jDto.setArea(contents.get(1).text());
		jDto.setJobTitle(contents.get(2).text());
		String str = contents.get(3).text().replaceAll("\\D+", ""); // 移除非數字字符	
		jDto.setRelativeExperienceYear(Float.parseFloat(str));
		jDto.setInterviewTime(contents.get(4).text());
		jDto.setResult(contents.get(5).text());
		
		jDto.setShareContent(doc.select(".src-components-ExperienceDetail-Article-__Article-module___article").html());
		
		return jDto;
		
	}
	
	
	public WorkReviewInfoDTO formatWorkReviewInfo(Document doc) {
		WorkReviewInfoDTO wDto=new WorkReviewInfoDTO();
		
		wDto.setShareDate(doc.select(".src-components-ExperienceDetail-Article-__Article-module___date").html());
		Elements contents=doc.select(".src-components-ExperienceDetail-Article-__InfoBlock-module___content");
		wDto.setArea(contents.get(0).text());
		wDto.setJobTitle(contents.get(1).text());
		String str = contents.get(2).text().replaceAll("\\D+", ""); // 移除非數字字符	
		wDto.setRelativeExperienceYear(Float.parseFloat(str));
		wDto.setHighestDegree(contents.get(3).text());
		wDto.setPerWeekWorkHours(Integer.parseInt(contents.get(4).text()));
		wDto.setTreatment(contents.get(5).text());
		
		return wDto;
		
		
		
	}

}
