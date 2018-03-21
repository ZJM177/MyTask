<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<div>
    <h3>下面的值控制刷新范围</h3>
    <form>
        <label>开始值</label><input type="text" id="start" name="start" >
        <label>条数</label><input type="text" id="limit" name="limit" >
    </form>
    <input id="refresh_btn" type="button" value="开始">
    <label>当前结果：</label>
    <textarea id="result_area"></textarea>
</div>


</body>
<script>
$(function () {
   $("#refresh_btn").click(function () {

   });
});
</script>
</html>