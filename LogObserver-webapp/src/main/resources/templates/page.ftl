<#macro greet page  queryForm size>
<#setting number_format="#">

<#if size?? >
<#local size = "10"/>
</#if>
	<div class="pagination clearfix">
		<form id="pageForm" method="post">
			<div class="clearfix fr mr_30">
				<#if page.totalPages &gt; 5 && page.number lte 4 >
						<#local pageCount = 5/>
						<#local beginCount = 1/>
						<#elseif page.totalPages &gt; 5 && page.number &gt; 4>
						<#local pageCount = '${page.number+1?number}'/>
						<#local beginCount = "${page.number-3?number}"/>
						<#else>
						<#local pageCount = "${page.totalPages?number}"/>
						<#local beginCount = 1/>
						</#if>
					<ul class="pagination">
						<li><a href="#" onclick="pageQuery(1)">&laquo;</a></li>
						 <#if page.number &gt; 0 >
						<#local prev = "${page.number?number}"/>
					   <#else>
						<#local prev = "${page.number+1?number}"/>
						</#if> 
						<li><a href="#"
							onclick="pageQuery('${prev}')">Prev</a></li>
							
							<#list beginCount?number..pageCount?number as i>
							 <#if page.number == i-1 >
                           <#local active = "active"/>
                           <#else>
                            <#local active = ""/>
                            </#if>
							<li   class="${active}">
								<a href="#" onclick="pageQuery(${i})">${i}</a>
							</li>
					     </#list>
					      	<li><a>共${page.totalPages?number}页</a></li>
					      	<#if page.number lt page.totalPages-1>
						<#local next = "${page.number+2?number}"/>
					   <#else>
						<#local next = "${page.number+1?number}"/>
						</#if> 
						<li><a href="#"
							onclick="pageQuery('${next}')">Next</a></li>
						<li><a href="#" onclick="pageQuery('${page.totalPages?number}')">&raquo;</a></li>

					</ul>
				
			</div>
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
				$('#${queryForm} select').clone().hide().appendTo(form);
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
</#macro>