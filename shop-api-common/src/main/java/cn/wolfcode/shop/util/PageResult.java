package cn.wolfcode.shop.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable{
	private List listData;
	private Integer totalCount;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;
	private Integer prevPage;
	private Integer nextPage;
	public static PageResult empty(Integer pageSize) {
		return new PageResult(Collections.EMPTY_LIST, 0, 1, pageSize);
	}

	public int getTotalPage() {
		return totalPage == 0 ? 1 : totalPage;
	}

	public PageResult(List listData, Integer totalCount, Integer currentPage,
			Integer pageSize) {
		super();
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
				: (totalCount / pageSize) + 1;
		this.prevPage = currentPage - 1 >= 1 ? currentPage - 1 : 1;
		this.nextPage = currentPage + 1 <= totalPage ? currentPage + 1
				: totalPage;
	}
}
