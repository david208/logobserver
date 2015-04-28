<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page"
	required="true"%>
<%@ attribute name="size" type="java.lang.Integer"%>
<%@ attribute name="queryForm" type="java.lang.String"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty size }">
	<c:set var="size" value="10"></c:set>
</c:if>
<c:if test="${!empty page }">
	<div class="pagination clearfix">
		<form id="pageForm" method="post">
			${searchParams}
			<ul class="clearfix fr mr_30">
				<li><c:choose>
						<c:when test="${page.totalPages > 5 && page.number<=4}">
							<c:set var="pageCount" scope="session" value="5">
							</c:set>
							<c:set var="beginCount" scope="session" value="1">
							</c:set>
						</c:when>

						<c:when test="${page.totalPages > 5 && page.number>4}">
							<c:set var="pageCount" scope="session" value="${page.number+1}">
							</c:set>
							<c:set var="beginCount" scope="session" value="${page.number-3}">
							</c:set>
						</c:when>
						<c:when test="${page.totalPages <= 4}">
							<c:set var="pageCount" scope="session" value="${page.totalPages}">
							</c:set>
							<c:set var="beginCount" scope="session" value="1">
							</c:set>
						</c:when>
					</c:choose>
					<ul class="pagination">
						<li><a href="#" onclick="pageQuery(1)">&laquo;</a></li>
						<li><a href="#"
							onclick="pageQuery('${page.number>0?page.number:page.number+1}')">Prev</a></li>
						<c:forEach var="i" begin="${beginCount}" end="${pageCount}"
							step="1">

							<li <c:if test='${page.number == i-1}'> class="active"</c:if>>
								<a href="#" onclick="pageQuery(${i})">${i}</a>
							</li>
						</c:forEach>
						<%-- <li> <h4><c:out value="共${page.totalPages}页" /></h4></li>  --%>
						<li><a href="#"
							onclick="pageQuery('${page.number<page.totalPages-1?page.number+2:page.number+1}')">Next</a></li>
						<li><a href="#" onclick="pageQuery('${page.totalPages}')">&raquo;</a></li>

					</ul></li>
				<%-- <li>共${page.totalPages}页</li> --%>
				<%-- <li>
					<!-- 选择每一页显示的页数 --> <select class="w_wd26" id="changeSize">
						<c:forEach var="i" begin="1" end="10" step="1">
							<c:if test="${i==1 or i==2 or i==5 or i==10}">
								<option value="${size*i}"
									<c:if test="${page.size == size*i}">selected</c:if>>
									${size*i}</option>
							</c:if>
						</c:forEach>

				</select>
				</li> --%>
			</ul>
			<input type="hidden" value="1" name="page" /><input type="hidden"
				value="10" name="rows" />
		</form>
	</div>
	<script>
		$(function() {
			$("select[name=selectNo]").change(function() {
				pageQuery($(this).val(), this);
			});
			$("#changeSize").change(function() {
				changePageSize($(this).val(), this);
			});
		});

		function pageQuery(pageNum) {
			var form = $("#pageForm");
			form.find("input[name=page]").val(pageNum);
			if ('${queryForm}' != '') {
				$('#${queryForm} input').clone().hide().appendTo(form);
			}
			if (window.pageCallBack) {
				window.pageCallBack();
			}
			form.submit();
		}

		/* function changePageSize(pageSize) {
			var form = $(elm).parent().parent().parent().parent();
			form.find("input[name=rows]").val(pageSize);
			form.find("input[name=rows]").val(pageSize);
			initIdentNo();
			if (window.pageCallBefore) {
				window.pageCallBefore();
			}

			if ('${queryForm}' != '') {
				$('#${queryForm} input').clone().hide().appendTo(form);
			}
			if (window.pageCallBack) {
				window.pageCallBack();
			}

			loadingMask();
			form.submit();
		} */
	</script>
	<br>
</c:if>