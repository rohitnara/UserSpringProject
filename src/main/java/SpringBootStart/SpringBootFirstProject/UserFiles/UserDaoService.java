package SpringBootStart.SpringBootFirstProject.UserFiles;
import SpringBootStart.SpringBootFirstProject.Controller.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class UserDaoService {
    final static Logger logger = LoggerFactory.getLogger(UserDaoService.class);
    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

        public static List<User> user = new ArrayList<>();
        static{

            user.add(new User(1, "Rohit", "2-5-2013" ));
            user.add(new User(2, "Stefan", "4-4-2014" ));
            user.add(new User(3, "Oleg", "3-8-2012" ));
            user.add(new User(4, "James", "21-7-2011" ));
    }

        public List<User> findAll(){
            return user;
        }

        @Transactional
        public List<String> getAddress(){
            List<String> addresses = new ArrayList<String>();
            List<String> ids = new ArrayList<String>();
            List<String> names = new ArrayList<String>();
            List<String> ages = new ArrayList<String>();
            List<String> salaries = new ArrayList<String>();
            Session session = this.sessionFactory.getCurrentSession();
           // String address = "select ADDRESS from CUSTOMERS";
            String address = "select * from CUSTOMERS";
            NativeQuery query = session.createNativeQuery(address);
            NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
            nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, String>> result = nativeQuery.getResultList();
            for(int i = 0; i< result.size(); i++)
                addresses.add(result.get(i).get("ADDRESS"));
            for(int i = 0; i< result.size(); i++)
                ids.add(result.get(i).get("ID"));
            for(int i = 0; i< result.size(); i++)
                names.add(result.get(i).get("NAME"));
            for(int i = 0; i< result.size(); i++)
                ages.add(result.get(i).get("AGE"));
            for(int i = 0; i< result.size(); i++)
                salaries.add(result.get(i).get("SALARY"));
            return addresses;

        }

}
