package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;


import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Autowired
    DistilleryRepository distilleryRepository;

    @Transactional
    public List<Whisky> getWhiskiesByYear(int year){
        List<Whisky> result = null;

        Session session = entityManager.unwrap(Session.class);
        Criteria cr = session.createCriteria(Whisky.class);


        cr.add(Restrictions.eq("year", year));

        result = cr.list();
        return result;
    }

    @Transactional
    public List<Whisky> getWhiskiesByDistillaryIdAndAge(Long id, int age){
        List<Whisky> result = null;

        Distillery distillery = distilleryRepository.getOne(id);

        Session session = entityManager.unwrap(Session.class);
        Criteria cr1 = session.createCriteria(Whisky.class);


        Criterion item1 = Restrictions.eq("age", age);
        Criterion item2 = Restrictions.eq("distillery", distillery);
        cr1.add(Restrictions.and(item1, item2));

        result = cr1.list();
        return result;
    }

    @Transactional
    public List<Whisky> getWhiskiesByRegion(String region){
        List<Whisky> result = new ArrayList<>();

        List<Distillery> distilleries = distilleryRepository.getDistilleriesByRegion(region);

        for (Distillery distillery : distilleries){
            for (Whisky whisky : distillery.getWhiskies()){
                result.add(whisky);
            }
        }

        return result;
    }

}
