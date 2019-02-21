
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<script src="${pageContext.request.contextPath}/jquery-1.7.2.js"></script>
<script src="${pageContext.request.contextPath}/assets/html2canvas/html2canvas.js"></script>
<h2>Hello World!</h2>
第一个杠<input id="first"><br>
第二个步骤<input id="second"><br>
要多少个<input id="max"><br>
最后的标识<input id="third"><br>
<button onclick="aaa()">生成</button>
<div id="sss">

</div>
</body>
</html>
<script>
    function aaa() {
        var first = $("#first").val();
        var second = $("#second").val();
        var max = $("#max").val();
        var third = $("#third").val();
        $.post("userInfo/wuliaomei",{first:first,second:second,max:max,third:third},function (result) {
            var data = result.data;
            var  html= "";
            for (var index in data){
                html+='<span>'+data[index]+'</span><br>'
            }
            $("#sss").html(html);

        })
    }
</script>
