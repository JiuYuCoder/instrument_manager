<html>
<#include "../common/header.ftl">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" method="post" action="/measure/login">
                <div class="form-group">
                    <label>账号:</label>
                    <input name="studentId" type="text" class="form-control"/>
                </div>
                <div class="form-group">
                    <label>密码:</label>
                    <input name="password" type="password" class="form-control"/>
                </div>
                <button type="submit" class="btn btn-default">登录</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>