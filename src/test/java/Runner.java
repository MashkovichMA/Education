import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.mashkovich.education.connections.MyThread;
import com.mashkovich.education.connections.MyThreadRunnable;
import com.mashkovich.education.dao.IAddressDao;
import com.mashkovich.education.dao.IChairDao;
import com.mashkovich.education.dao.ICityDao;
import com.mashkovich.education.dao.ICountryDao;
import com.mashkovich.education.dao.IDao;
import com.mashkovich.education.dao.ILectureDao;
import com.mashkovich.education.dao.IProfessorDao;
import com.mashkovich.education.dao.IStudentDao;
import com.mashkovich.education.dao.IUniversityDao;
import com.mashkovich.education.models.Address;
import com.mashkovich.education.models.Chair;
import com.mashkovich.education.models.City;
import com.mashkovich.education.models.Country;
import com.mashkovich.education.models.Lecture;
import com.mashkovich.education.models.Professor;
import com.mashkovich.education.models.Schedule;
import com.mashkovich.education.models.Student;
import com.mashkovich.education.models.University;
import com.mashkovich.education.services.CityService;
import com.mashkovich.education.services.UniversityService;


public class Runner {
	private final static Logger log= Logger.getLogger(Runner.class);

	public static void main(String[] args) throws SQLException {
	     Reader reader = null;
	     try {
	    	 reader = Resources.getResourceAsReader("mybatis-config.xml"); 
	         SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
	         SqlSession s = factory.openSession();
	         IUniversityDao u = s.getMapper(IUniversityDao.class);
	         University un = u.getByID(4);
	         log.info(un.toString());
	      } catch (IOException e) {
	          log.error(e);
	      }
	    }
		}
