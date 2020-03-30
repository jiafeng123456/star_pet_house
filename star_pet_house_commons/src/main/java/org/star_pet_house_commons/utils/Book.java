package org.star_pet_house_commons.utils;

/*
 *@description:
 *@author jiafeng
 *@date 2020/3/30 0030 14:23
 */
public class Book {

    private String title;

    private Integer pageNum;

    public Book(String title, Integer pageNum) {
        this.title = title;
        if (pageNum == null || pageNum < 200){
            System.out.println("页数不能少于200，当前页数:" + pageNum);
            this.pageNum = 200;
        }else {
            this.pageNum = pageNum;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum < 200){
            System.out.println("页数不能少于200，当前页数:" + pageNum);
            this.pageNum = 200;
        }else {
            this.pageNum = pageNum;
        }
    }

    public String Detail(){
        return "title:" + this.title + ";\n" + "page_num = " + this.pageNum;
    }

}
