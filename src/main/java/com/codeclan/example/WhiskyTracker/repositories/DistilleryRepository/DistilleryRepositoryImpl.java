package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public List<Distillery> getDistilleriesByRegion(String region){
        List<Distillery> result = null;

        Session session = entityManager.unwrap(Session.class);
        Criteria cr = session.createCriteria(Distillery.class);

        cr.add(Restrictions.eq("region", region));
        result = cr.list();
        return result;
    }

    @Transactional
    public List<Distillery> getDistilleriesWithWhiskiesThatAreTwelveYearsOld(){
        List<Distillery> result = null;

        Session session = entityManager.unwrap(Session.class);
        Criteria cr = session.createCriteria(Distillery.class);

        cr.createAlias("whiskies", "whisky");
        cr.add(Restrictions.eq("whisky.age", 12));

        result = cr.list();
        return result;
    }


}
