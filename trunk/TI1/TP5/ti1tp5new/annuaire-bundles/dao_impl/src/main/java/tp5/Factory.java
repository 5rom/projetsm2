package tp5;

public class Factory {
	
	private SiteXMLDAO dao;
	
	public Factory(SiteXMLDAO dao){
		this.dao = dao;
	}
	
	public Site getSite(String desc, String url){
		return new Site(desc,url,this.dao);
	}
	
	public Site getTemp(){
		return new Site(this.dao);
	}
	
	public SiteXMLDAO getDAO(){
		return dao;
	}

}
