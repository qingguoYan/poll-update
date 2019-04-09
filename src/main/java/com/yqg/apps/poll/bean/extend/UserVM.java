package com.yqg.apps.poll.bean.extend;


import java.util.List;

import com.yqg.apps.poll.bean.Clazz;

public class UserVM {
	private long id;
	private String gender;
	private String birth;
	private String name;
	private String hiredate;
	private String type;
	private long gradeId;
	private List<Clazz> clazz;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getGradeId() {
		return gradeId;
	}

	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	public List<Clazz> getClazz() {
		return clazz;
	}

	public void setClazz(List<Clazz> clazz) {
		this.clazz = clazz;
	}
}
