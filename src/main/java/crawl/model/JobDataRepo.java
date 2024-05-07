package crawl.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import crawl.bean.JobShareInfo;

public interface JobDataRepo extends JpaRepository<JobShareInfo, String> {
	
	@Query(value = "select searched_count From job_share_info where job_id= :jid;",nativeQuery = true)
	public Integer findSearchCountById(String jid);

}
