package ����ӿڱ��;

public class XidianSchool {
	private School school = null;
	//ע��:�˴��ǶԽӿڵ�set
	public void setSchool(School school) {
	    this.school = school;
	}
	public XidianSchool(){};
	public XidianSchool(School s)
	{
	    this.school= s;
	}
	//��ʾ
	public void getSchoolInfo()
	{
	    school.showGeography();
	    school.showlangtutide();
	}
}
