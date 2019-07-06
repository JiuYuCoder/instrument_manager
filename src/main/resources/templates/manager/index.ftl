<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/measure/manager/manager/save">
                        <div class="form-group">
                            <label>学号</label>
                            <input name="studentId" type="text" class="form-control" value="${(manager.studentId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>姓名</label>
                            <input name="studentName" type="text" class="form-control" value="${(manager.studentName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>年级</label>
                            <input name="studentGrade" type="number" class="form-control" value="${(manager.studentGrade)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>班级</label>
                            <input name="studentClass" type="number" class="form-control" value="${(manager.studentClass)!''}"/>
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>