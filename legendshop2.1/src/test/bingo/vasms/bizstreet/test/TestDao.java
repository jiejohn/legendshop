package bingo.vasms.bizstreet.test;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.done.model.Ad;

import bingosoft.jcf.dao.cache.BaseDao;
import bingosoft.jcf.service.impl.ContextServiceLocator;

public class TestDao extends TestCase {

    @Override
    protected void setUp() throws Exception {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(new String[] { "classpath*:spring/**/*.xml" });
        ContextServiceLocator.getInstance().setContext(ctx);
        System.out.println("ContextServiceLocator = " + ContextServiceLocator.getInstance());
        System.out.println("ContextServiceLocator context = " + ContextServiceLocator.getInstance().getContext());

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public final void testDeleteAd() {
        BaseDao baseDao = (BaseDao) ContextServiceLocator.getInstance().getBean("baseDao");
        System.out.println("baseDao = " + baseDao);
        Ad ad = new Ad();

        ad.setUserName("name");
        baseDao.getHibernateTemplate().bulkUpdate("delete from Ad where userName = 'name'");
        System.out.println("finish");

    }

    public final void testFindAd() {
        BaseDao baseDao = (BaseDao) ContextServiceLocator.getInstance().getBean("baseDao");
        System.out.println("baseDao = " + baseDao);
        Ad ad = new Ad();
        ad.setId(9);
        ad.setUrl("url");
        ad.setUserName("name");
        List list = baseDao.findByExample(ad);
        System.out.println(list.size());
        System.out.println("finish");

    }

    public final void testFindAdHQL() {
        BaseDao baseDao = (BaseDao) ContextServiceLocator.getInstance().getBean("baseDao");
        System.out.println("baseDao = " + baseDao);
        Ad ad = new Ad();
        ad.setId(9);
        ad.setUrl("url");
        ad.setUserName("name");
        List list = baseDao.getHibernateTemplate().find("from Ad where userName = ? and id = ?", "name", 1);
        System.out.println(list.size());
        System.out.println("finish");

    }

    public final void testSaveAd() {
        BaseDao baseDao = (BaseDao) ContextServiceLocator.getInstance().getBean("baseDao");
        System.out.println("baseDao = " + baseDao);
        Ad ad = new Ad();
        ad.setBs(1);
        ad.setContent("content");
        ad.setId(100);
        ad.setUrl("url");
        ad.setUserName("name");
        ad.setWordlink("wordlink");
        ad.setUserId("userId");
        baseDao.save(ad);
        System.out.println("finish");

    }

}
