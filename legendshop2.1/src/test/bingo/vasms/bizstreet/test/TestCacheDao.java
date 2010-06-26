package bingo.vasms.bizstreet.test;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.type.Type;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.done.model.Ad;
import com.done.model.Hw;
import com.done.model.ShopDetail;

import bingosoft.jcf.dao.PageDao;
import bingosoft.jcf.dao.cache.BaseDao;
import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.HqlQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.service.impl.ContextServiceLocator;

public class TestCacheDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCacheDao test = new TestCacheDao();
		test.initspring(new String[]{"classpath*:spring/**/*.xml"});
		BaseDao dao = ContextServiceLocator.getInstance().getBean(BaseDao.class,"cacheDao");
		//test.find(dao);
		ShopDetail shopDetail = dao.findUniqueBy("from ShopDetail where userId = ?", ShopDetail.class, new Object[]{"1"});
		//ShopDetail shopDetail = dao.findUniqueBy(ShopDetail.class,"visitTimes",2);
		//ShopDetail shopDetail = dao.get(ShopDetail.class, "1");
//		Query query = dao.createQuery("from ShopDetail where visitTimes = ? and userId = ?", new Object[]{2,2});
//		ShopDetail shopDetail = (ShopDetail)query.list();
		System.out.println("shopDetail getWeb = "+shopDetail.getWeb());
		List<ShopDetail> shop = dao.findByHQL("from ShopDetail where userId = ?",new Object[]{"1"});
		System.out.println("shop = "+shop.size());
		for (ShopDetail detail : shop) {
			System.out.println("Sitename = "+detail.getSitename());
		}
		
		//test.findBySQL(dao);
		//List list = test.getTuijian(dao);
		
		//System.out.println(list.size());

	}
	
	public void findByQbc(PageDao dao){
		CriteriaQuery cq=new CriteriaQuery(Ad.class,"1","myaction");
		cq.setPageSize(2);
		cq.eq("id", 2);
		cq.add();
		PageSupport ps = dao.find(cq, true);
		System.out.println(ps.getResultList());
		System.out.println(ps.getToolBar());
	}
	
	public void findByQbc1(PageDao dao){
		CriteriaQuery cq=new CriteriaQuery(Hw.class,"1","myaction");
		cq.setCurPage("1");
		cq.setPageSize(10);
		cq.addOrder("desc", "hwId");
		cq.eq("sortId", 26);
		cq.add();
		PageSupport ps = dao.find(cq, true);
		System.out.println(ps.getResultList());
		System.out.println(ps.getToolBar());
	}
	
	public List<Hw> getTuijian(PageDao pageDao) {
		//return jdbcTemplate.queryForList("select * from hw where tuijian='true' order by hw_id desc limit 0,10");
		//return baseDao.findByHQL("from Hw where tuijian='true' order by hwId desc limit 0,10");
		CriteriaQuery cq = new CriteriaQuery(Hw.class);
		cq.setMyaction("myaction");
		cq.setCurPage("1");
		
		cq.setPageSize(10);
		cq.eq("tuijian", "true");
		cq.addOrder("desc", "hwId");
		cq.add();
		PageSupport ps = pageDao.find(cq, true);
		return  ps.getResultList();
	}
	
	public void find(PageDao dao){
		HqlQuery hq=new HqlQuery("myaction");
		hq.setQueryString("from Ad");
		hq.setPageSize(2);
		hq.setCurPage("2");
		hq.setAllCountString("select count(*) from Ad");
		hq.setParam(new Object[]{2});
		//hq.setTypes(new Type[]{Hibernate.INTEGER});
		PageSupport ps = dao.find(hq);
		System.out.println(ps.getResultList());
		System.out.println(ps.getCurPageNO());
		System.out.println(ps.getToolBar());
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
