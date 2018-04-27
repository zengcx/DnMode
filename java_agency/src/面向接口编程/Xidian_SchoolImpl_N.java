package 面向接口编程;

public class Xidian_SchoolImpl_N implements School{

	@Override
	public void showGeography() {
		System.out.println("西安电子科技大学地址  ：西安市-雁塔区-太白南路-西安电子科技大学(北校区)");
	}
		

	@Override
	public void showlangtutide() {
		 System.out.println("西安电子科技大学地理位置：34.2369070000,108.9232450000");
		
	}

}
