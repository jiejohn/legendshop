package bingo.vasms.bizstreet.test;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.done.model.Ad;
import com.done.model.Hw;

import bingosoft.jcf.dao.cache.BaseDao;
import bingosoft.jcf.dao.PageDao;
import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.HqlQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.service.impl.ContextServiceLocator;

public class TestPageListDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestPageListDao test = new TestPageListDao();
		test.initspring(new String[]{"classpath*:spring/**/*.xml"});
		BaseDao dao = (BaseDao)ContextServiceLocator.getInstance().getBean("baseDao");
		//test.find(dao);
		//test.findBySQL(dao);
		List list = test.findHwListByHQL(dao);
		
		System.out.println("size ="+list.size());

	}
	
	public void findByQbc(PageDao dao){
		CriteriaQuery cq=new CriteriaQuery(Ad.class,"1","myaction");
		cq.setPageSize(2);
		PageSupport ps = dao.find(cq, true);
		System.out.println(ps.getResultList());
		System.out.println(ps.getToolBar());
	}
	
	public List<Hw> getTuijian(PageDao pageDao) {
		//return jdbcTemplate.queryForList("select * from hw where tuijian='true' order by hw_id desc limit 0,10");
		//return baseDao.findByHQL("from Hw where tuijian='true' order by hwId desc limit 0,10");
		CriteriaQuery cq = new CriteriaQuery(Hw.class);
		cq.eq("commend", "true");
		cq.addOrder("desc", "hwId");
		cq.add();
		List list = pageDao.findListByCriteria(cq, 2, 3);
		
		return  list;
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
	
	public List findHwListByHQL(BaseDao dao){
		HqlQuery hq=new HqlQuery(null);
		hq.setQueryString("select new Hw(sort.sortName, nsort.nsortName, hw.hwId, hw.sortId, hw.nsortId, hw.hwName, hw.hwCash, hw.hwContent) from Hw hw,Sort sort,Nsort nsort where hw.sortId = sort.sortId and hw.nsortId = nsort.nsortId");

		List list = dao.findListByHQL(hq, 0, 5);
		System.out.println("list size = "+list.size());
		return list;
	}
	
	public void findBySQL(PageDao dao){
		HqlQuery hq=new HqlQuery("myaction");
		hq.setQueryString("select * from ad where Id = ?");
		hq.setPageSize(4);
		hq.setCurPage("1");
		hq.setAllCountString("select count(*) from ad where id = ?");
		hq.setParam(new Object[]{2});
		hq.setTypes(new Type[]{Hibernate.INTEGER});
		PageSupport ps = dao.findBySql(hq);
		System.out.println(ps.getResultList());
		System.out.println(ps.getCurPageNO());
		System.out.println(ps.getToolBar());
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

}
