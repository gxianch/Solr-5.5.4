package zxj.solrj;

import java.util.ArrayList;

public class SolrDoc {
	private String name;
	private ArrayList<Integer> area;
	private ArrayList<Integer> column;
	private String spell;
	private Integer year;
	private ArrayList<Integer> type;
	private Integer id;

	private Long utime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Integer> getArea() {
		return area;
	}
	public void setArea(ArrayList<Integer> area) {
		this.area = area;
	}
	public ArrayList<Integer> getColumn() {
		return column;
	}
	public void setColumn(ArrayList<Integer> column) {
		this.column = column;
	}
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ArrayList<Integer> getType() {
		return type;
	}
	public void setType(ArrayList<Integer> type) {
		this.type = type;
	}
	public Long getUtime() {
		return utime;
	}
	public void setUtime(Long utime) {
		this.utime = utime;
	}
	@Override
	public String toString() {
		return "SolrDoc [name=" + name + ", area=" + area + ", column=" + column + ", spell=" + spell + ", year=" + year
				+ ", id=" + id + ", type=" + type + ", utime=" + utime + "]";
	}
	public SolrDoc(String name, ArrayList<Integer> area, ArrayList<Integer> column, String spell, Integer year,
			ArrayList<Integer> type, Integer id, Long utime) {
		super();
		this.name = name;
		this.area = area;
		this.column = column;
		this.spell = spell;
		this.year = year;
		this.type = type;
		this.id = id;
		this.utime = utime;
	}
	public SolrDoc() {
		super();
	}
	
}
