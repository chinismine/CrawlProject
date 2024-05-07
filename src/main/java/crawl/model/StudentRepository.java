package crawl.model;

import org.springframework.data.jpa.repository.JpaRepository;

import crawl.bean.Students;


public interface StudentRepository extends JpaRepository<Students, Integer>{

}
