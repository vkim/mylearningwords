package au.com.shopusa.cms

class CmsPageDoesNotExistException extends RuntimeException {

  private String pageId

  CmsPageDoesNotExistException(String pageId){
    super("CMS page with Page-ID \"$pageId\" does not exist".toString())
    this.pageId = pageId
  }

  String getPageId(){
    return pageId
  }
}
