/******************************************
项目名称：taotao-item-web
文件：Student.java
作者：hspcadmin
描述：TODO
创建日期：2018年8月28日 下午2:47:10
*******************************************/
package com.taotao.freemarker;

/**
 * @author fab
 *
 */
public class Student {
	private int id;
    private String name;
    private int age;
    private String address;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Student() {

    }
    public Student(int id, String name, int age, String address) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
