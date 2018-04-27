package 面向接口编程;

public class XidianSchool {
	private School school = null;
	//注意:此处是对接口的set
	public void setSchool(School school) {
	    this.school = school;
	}
	public XidianSchool(){};
	public XidianSchool(School s)
	{
	    this.school= s;
	}
	//显示
	public void getSchoolInfo()
	{
	    school.showGeography();
	    school.showlangtutide();
	}
}
