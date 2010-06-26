package bingo.vasms.bizstreet.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.done.model.Hw;
import com.done.service.BusinessService;

import bingosoft.jcf.dao.cache.BaseDao;
import bingosoft.jcf.dao.support.HqlQuery;
import bingosoft.jcf.service.impl.ContextServiceLocator;
 
public class TessQueryCache {
 
       public static void main(String[] args) {
    	   TessQueryCache test = new TessQueryCache();
    	   test.initspring(new String[]{"classpath*:spring/**/*.xml"});
    	   /*
              Session session = (SessionFactory)ContextServiceLocator.getInstance().getBean("sessionFactory").getCurrentSession();
              Transaction tx =null;
              try{
                     tx = session.beginTransaction(); 
                     Query query = session.createQuery("from Sort");
                     //激活查询缓存
                     query.setCacheable(true);
                     //使用自定义的查询缓存区域,若不设置,则使用标准查询缓存区域
                     query.setCacheRegion("myCacheRegion");
                     
                     List list = query.list();
//                     for(int i = 0 ; i < list.size(); i++){
//                            Product prod = (Product)list.get(i);
//                            System.out.println(prod.getName());
//                     }
//                     
                    tx.commit();
              }catch(HibernateException e){
                     if(tx != null){
                            tx.rollback();
                     }
                     e.printStackTrace();
              }finally{
            	  ContextServiceLocator.getInstance().getBean(SessionFactory.class).close();
              }
              */
      		BaseDao baseDao = (BaseDao)ContextServiceLocator.getInstance().getBean("cacheDao");
    		//test.find(dao);
    		//test.findBySQL(dao);
//    		List list = test.findListByHQL(dao);
//    		List list1 = test.findListByHQL(dao);
//    		List list2 = test.findListByHQL(dao);
//    		//Hw hw = baseDao.load(Hw.class, 127);
//    	    Hw hw1 = baseDao.get(Hw.class, 128);
//    		System.out.println(hw1);
    		//System.out.println(hw.getCompany());
    		//System.out.println("size ="+list.size());
    		//System.out.println("size1 ="+list2.size());
    		//System.out.println("size2 ="+list2.size());
    		
//    		StreetService service = (StreetService)ContextServiceLocator.getInstance().getBean(StreetService.class);
//    		Hw h = service.loadHw(127);
//    		h = service.loadHw(127);
//    		h = service.loadHw(127);
//    		h = service.loadHw(128);
//    		
//    		System.out.println(h.getHwName());
//    		
//    		//service.executeByHQL(1);
//    		System.out.println(service.executeByHQL(19));
    		
//      		List<bingo.vasms.bizstreet.model.System> s;
//      		s = baseDao.getAll(bingo.vasms.bizstreet.model.System.class,"id",true);
//      		s = baseDao.getAll(bingo.vasms.bizstreet.model.System.class,"id",true);
//      		s = baseDao.getAll(bingo.vasms.bizstreet.model.System.class,"id",true);
//      		System.out.println("a");
      		
      		
//      		List<bingo.vasms.bizstreet.model.System> s;
//      		s = baseDao.findByHQL("from System where name = '何文强'",1,10);
//      		s = baseDao.findByHQL("from System where name = '何文强'", 1,10);
//      		s = baseDao.findByHQL("from System where name = '何文强'", 1,10);
//      		System.out.println("a");
      		
//      		bingo.vasms.bizstreet.model.System s = baseDao.load(bingo.vasms.bizstreet.model.System.class,1);
//      		s = baseDao.load(bingo.vasms.bizstreet.model.System.class, 1);
//      		s = baseDao.load(bingo.vasms.bizstreet.model.System.class, 1);
//      		System.out.println(s.getName());
       }
       
   	private boolean initspring(String[] spring_paths) {
		ApplicationContext ctx= new FileSystemXmlApplicationContext(spring_paths);
		ContextServiceLocator.getInstance().setContext(ctx);
		System.out.println("ContextServiceLocator = "
				+ ContextServiceLocator.getInstance());
		System.out.println("ContextServiceLocator context = "
				+ ContextServiceLocator.getInstance().getContext());
		return true;
	}
   	
	public List findListByHQL(BaseDao dao){
		HqlQuery hq=new HqlQuery("myaction");
		hq.setQueryString("from Ad");
		hq.setPageSize(2);
		hq.setCurPage("2");
		hq.setAllCountString("select count(*) from Ad");
		hq.setParam(new Object[]{2});
		//hq.setTypes(new Type[]{Hibernate.INTEGER});
		List list = dao.findListByHQL(hq, 0, 5);
		System.out.println("list size = "+list.size());
		return list;
	}
}
