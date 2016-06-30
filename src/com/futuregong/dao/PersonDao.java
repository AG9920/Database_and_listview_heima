package com.futuregong.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.futuregong.db.PersonSQLiteOpenHelper;
import com.futuregong.domain.Person;

public class PersonDao {
	
	
	private PersonSQLiteOpenHelper helper;
	
	//在构造方法里面完成对helper的初始化
	public PersonDao(Context context){
		helper = new PersonSQLiteOpenHelper(context);
	}
	
	/**
	 * 添加一条记录到数据库
	 * @param name  姓名
	 * @param number  电话
	 */
	public void add(String name, String number){
		SQLiteDatabase db = helper.getWritableDatabase();
		String ADD ="insert into person (name,number) values(?,?)";
		db.execSQL(ADD,new Object[]{name,number});
		db.close();
	}
	
	/**
	 * 查询记录是否存在
	 * @param name 姓名
	 * @return true 存在 ; false 不存在
	 */
	public boolean find(String name){
		SQLiteDatabase db  =helper.getReadableDatabase();
		String FIND = "select * from person where name = ?";
		Cursor cursor = db.rawQuery(FIND, new String[]{name});
		boolean result = cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}
	
	/**
	 * 修改一条记录
	 * @param name 要修改的人的姓名
	 * @param newnumber 新的电话号码
	 */
	public void update(String name,String newnumber){
		SQLiteDatabase db = helper.getWritableDatabase();
		String UPDATE = "update person set number = ? where name = ?";
		db.execSQL(UPDATE,new Object[]{newnumber,name});
		db.close();
	}
	
	
	/**
	 * 删除一条记录
	 * @param name
	 */
	public void delete(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		String DELETE = "delete from person where name = ?";
		db.execSQL(DELETE,new Object[]{name});
		db.close();
	}
	
	
	
	/**
	 * 返回全部数据库信息
	 * @return
	 */
	
	public List<Person> findAll(){
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Person> persons = new ArrayList<Person>();
		Cursor cursor = db.rawQuery("select * from person", null);
		while(cursor.moveToNext()){
//			两种方式都可以，但0,1,2容易让人不理解含义，也不便于修改
//			int id = cursor.getInt(0);
//			String name = cursor.getString(1);
//			String number = cursor.getString(2);
			
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String number = cursor.getString(cursor.getColumnIndex("number"));
			Person p = new Person(id,name,number);
			persons.add(p);
		}
		cursor.close();
		db.close();
		return persons;
	}
	
	
	
	
	
	
}
