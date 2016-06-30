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
	
	//�ڹ��췽��������ɶ�helper�ĳ�ʼ��
	public PersonDao(Context context){
		helper = new PersonSQLiteOpenHelper(context);
	}
	
	/**
	 * ���һ����¼�����ݿ�
	 * @param name  ����
	 * @param number  �绰
	 */
	public void add(String name, String number){
		SQLiteDatabase db = helper.getWritableDatabase();
		String ADD ="insert into person (name,number) values(?,?)";
		db.execSQL(ADD,new Object[]{name,number});
		db.close();
	}
	
	/**
	 * ��ѯ��¼�Ƿ����
	 * @param name ����
	 * @return true ���� ; false ������
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
	 * �޸�һ����¼
	 * @param name Ҫ�޸ĵ��˵�����
	 * @param newnumber �µĵ绰����
	 */
	public void update(String name,String newnumber){
		SQLiteDatabase db = helper.getWritableDatabase();
		String UPDATE = "update person set number = ? where name = ?";
		db.execSQL(UPDATE,new Object[]{newnumber,name});
		db.close();
	}
	
	
	/**
	 * ɾ��һ����¼
	 * @param name
	 */
	public void delete(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		String DELETE = "delete from person where name = ?";
		db.execSQL(DELETE,new Object[]{name});
		db.close();
	}
	
	
	
	/**
	 * ����ȫ�����ݿ���Ϣ
	 * @return
	 */
	
	public List<Person> findAll(){
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Person> persons = new ArrayList<Person>();
		Cursor cursor = db.rawQuery("select * from person", null);
		while(cursor.moveToNext()){
//			���ַ�ʽ�����ԣ���0,1,2�������˲���⺬�壬Ҳ�������޸�
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
