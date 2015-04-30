<#macro greet page  queryForm size>


<#if size?? >
<#local size = "10"/>
</#if>
	<div class="pagination clearfix">
		<form id="pageForm" method="post">
			<ul class="clearfix fr mr_30">
				<li><#if page.totalPages &gt; 5 && page.number lte 4 >
						<#local pageCount = "5"/>
						<#local beginCount = "1"/>
						<#elseif page.totalPages &gt; 5 && page.number &gt; 4>
						<#local pageCount = "${page.number+1}"/>
						<#local beginCount = "${page.number-3}"/>
						<#else>
						<#local pageCount = "${page.totalPages}"/>
						<#local beginCount = "1"/>
						</#if>
					<ul class="pagination">
						<li><a href="#" onclick="pageQuery(1)">&laquo;</a></li>
						 <#--<#if page.number &gt; 0 >
						<#local prev = "${page.number}"/>
						<#else>
						<#local prev = "${page.number+1}"/>
						<#/if>-->
						<li><a href="#"
							onclick="pageQuery('${prev}')">Prev</a></li>
							
							<#list beginCount..pageCount as i>
	
                           
							<li   class="active">
								<a href="#" onclick="pageQuery(${i})">${i}</a>
							</li>
					     </#list>
					      	
						<li><a href="#"
							onclick="pageQuery('<#if page.number < page.totalPages-1 > page.number+2 <else> page.number+1 </#if>')">Next</a></li>
						<li><a href="#" onclick="pageQuery('${page.totalPages}')">&raquo;</a></li>

					</ul></li>
				
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
</#macro>