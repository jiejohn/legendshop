package bingo.vasms.bizstreet.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.done.model.Ad;
import com.done.model.Logo;
import com.done.service.AdminService;

import bingosoft.jcf.dao.PageDao;
import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.service.impl.ContextServiceLocator;

public class TestAdminService {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TestAdminService test = new TestAdminService();
        test.initspring(new String[] { "classpath*:spring/**/*.xml" });
        AdminService service = (AdminService) ContextServiceLocator.getInstance().getBean("AdminServiceTarget");
        //test.find(dao);
        //test.findBySQL(dao);
        Logo logo = service.get(Logo.class, 13);
        Long num = service.getIndexJpgNum("123done");
        System.out.println("Banner =" + logo.getBanner());
        System.out.println("num = " + num);

    }

    public void findByQbc(PageDao dao) {
        CriteriaQuery cq = new CriteriaQuery(Ad.class, "1", "myaction");
        cq.setPageSize(2);
        PageSupport ps = dao.find(cq, true);
        System.out.println(ps.getResultList());
        System.out.println(ps.getToolBar());
    }

    private boolean initspring(String[] spring_paths) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(spring_paths);
        ContextServiceLocator.getInstance().setContext(ctx);
        System.out.println("ContextServiceLocator = " + ContextServiceLocator.getInstance());
        System.out.println("ContextServiceLocator context = " + ContextServiceLocator.getInstance().getContext());
        return true;
    }

}
