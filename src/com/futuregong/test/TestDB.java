package com.futuregong.test;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.futuregong.dao.PersonDao;
import com.futuregong.db.PersonSQLiteOpenHelper;
import com.futuregong.domain.Person;

public class TestDB extends AndroidTestCase{
	
	
	public void testCreateDB () throws Exception{
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
	}
	
	public void testAdd() throws Exception{
		PersonDao dao = new PersonDao(getContext());
		dao.add("wangwu", "123");
	}
	public void testFind() throws Exception{
		PersonDao dao = new PersonDao(getContext());
		boolean result = dao.find("wangwu");
		assertEquals(true, result);
	}
	public void testUpdate() throws Exception{
		PersonDao dao = new PersonDao(getContext());
		dao.update("wangwu", "321");
	}
	public void testDelete() throws Exception{
		PersonDao dao = new PersonDao(getContext());
		dao.delete("wangwu");
	}
	public void testFindAll() throws Exception{
		PersonDao dao = new PersonDao(getContext());
		List<Person> persons = new ArrayList<Person>();
		persons = dao.findAll();
		for(Person p : persons){
			System.out.println(p.toString());
		}
	}
}
