package com.tool;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by FuFangzhou on 2017/5/24.
 *
 SessionOpenner so = SessionOpenner.getInstance();
 Session session = so.getSession();
 */
public class SessionOpenner {
    static SessionOpenner sessionOpenner;
    SessionFactory sf;

    public static String getNowDate(){
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        return matter1.format(dt);
    }

    public static SessionOpenner getInstance() {
        if (sessionOpenner != null)
            return sessionOpenner;
        else {
            Configuration cfr = new Configuration();
            cfr.configure();
            ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(cfr.getProperties()).buildServiceRegistry();
            SessionFactory sf = cfr.buildSessionFactory(sr);
            return new SessionOpenner(sf);
        }
    }

    public SessionOpenner(SessionFactory sf) {
        this.sf = sf;
    }

    public Session getSession() {
        Session session = sf.openSession();
        return session;
    }
}
