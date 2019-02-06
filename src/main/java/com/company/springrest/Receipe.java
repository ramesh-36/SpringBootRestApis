package com.company.springrest;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "receipes")
public class Receipe {
	@Id
	@GeneratedValue
	private Integer rid;
	private String title;
	private String href;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "receipe_items", joinColumns = { @JoinColumn(name = "rid") }, inverseJoinColumns = {
			@JoinColumn(name = "iid") })
	private Set<Ingredient> ingredients;
	private String thumbnail;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer id) {
		this.rid = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Receipe(Integer id, String title, String href, Set<Ingredient> ingredients, String thumbnail) {
		super();
		this.rid = id;
		this.title = title;
		this.href = href;
		this.ingredients = ingredients;
		this.thumbnail = thumbnail;
	}

	public Receipe() {
		super();

	}

	@Override
	public int hashCode() {

		return Objects.hash(rid, title, href, ingredients, thumbnail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Receipe other = (Receipe) obj;

		return Objects.equals(rid, other.rid) && Objects.equals(title, other.title) && Objects.equals(href, other.href)
				&& Objects.equals(ingredients, other.ingredients) && Objects.equals(thumbnail, other.thumbnail);
	}

}
