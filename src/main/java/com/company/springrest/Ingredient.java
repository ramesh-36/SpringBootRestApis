package com.company.springrest;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {
	@Id
	@GeneratedValue
	private Integer iid;
	private String name;

	Integer getIid() {
		return iid;
	}

	public void setIid(Integer id) {
		this.iid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ingredient(Integer id, String name) {
		super();
		this.iid = id;
		this.name = name;

	}

	public Ingredient() {
		super();

	}

	@Override
	public int hashCode() {

		return Objects.hash(iid, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		return Objects.equals(iid, other.iid) && Objects.equals(name, other.name);
	}

}
