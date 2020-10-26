<!--实现td框全选th框自动选的两种方法：
	1.根据已经选择的td框的数量，若全选则选
	2.遍历td框，若全选则选
-->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			table {
				margin: auto;
			}
		</style>
		<script src="lib/jquery.js" type="text/javascript" charset="utf-8"></script>
		<!--复选框全选全不选-->
		<script type="text/javascript">
			//让后面的复选框和第一个一致即可
			function c(obj) {
				$(".cb").prop("checked", obj.checked)
			}
			//当下面所有的复选框全选之后自动选择最上面的那个复选框
			$(function() {
				$("table td :checkbox").click(function() {
					var c = $("table td :checkbox").length
					var len = $("table td :checked").length;
					$("table th :checkbox").prop("checked", c == len);
				})
			})
		</script>
	</head>
	<body>
		<table id="table" border="1" cellspacing="" cellpadding="" width="40%">

			<tr id="tr1">
				<th class="thclass"><input type="checkbox" onclick="c(this)"  /></th>
				<th>分类ID</th>
				<th>分类名称</th>
				<th>分类描述</th>
				<th>操作</th>
			</tr>
			<tr>
				<td class="thclass"><input type="checkbox" class="cb" /></td>
				<td>0</td>
				<td>手机数码</td>
				<td>手机数码类商品</td>
				<td>
					<a href="#">修改</a>|
					<a href="#">删除</a>
				</td>
			</tr>
			<tr>
				<td class="thclass"><input type="checkbox" class="cb" /></td>
				<td>1</td>
				<td>电脑办公</td>
				<td>电脑办公类商品</td>
				<td>
					<a href="#">修改</a>|
					<a href="#">删除</a>
				</td>
			</tr>
			<tr>
				<td class="thclass"><input type="checkbox" class="cb" /></td>
				<td>2</td>
				<td>鞋靴箱包</td>
				<td>鞋靴箱包类商品</td>
				<td>
					<a href="#">修改</a>|<a href="#">删除</a>
				</td>
			</tr>
			<tr>
				<td class="thclass"><input type="checkbox" class="cb" /></td>
				<td>3</td>
				<td>家居饰品</td>
				<td>家居饰品类商品</td>
				<td>
					<a href="#">修改</a>|
					<a href="#">删除</a>
				</td>
			</tr>
		</table>
		<script>
			//表格颜色代码
			$("tr:even").css("background", "pink")
			$("tr:odd").css("background", "yellow")
			$("#tr1").css("background", "dimgray")
			$("#trr").css("background", "aliceblue")
		</script>
	</body>
</html>