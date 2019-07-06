<html>
<#include "../common/header.ftl">

<body>
<#include "../common/nav.ftl">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>年级</th>
                            <th>班级</th>
                            <th>组号</th>
                            <th>密码</th>
                            <th>修改码</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list userPage.content as userInfo>
                        <tr>
                            <td>${userInfo.studentId}</td>
                            <td>${userInfo.studentName}</td>
                            <td>${userInfo.studentGrade}</td>
                            <td>${userInfo.studentClass}</td>
                            <td>${userInfo.studentGroup}</td>
                            <td>${userInfo.password}</td>
                            <td>${userInfo.verificationCode}</td>
                            <td><a href="/measure/manager/user/index?studentId=${userInfo.studentId}">修改</a></td>
                            <td><a href="/measure/manager/user/delete?studentId=${userInfo.studentId}">删除</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <form role="form" method="get" action="/measure/manager/user/find/one?studentId=">
                        <div class="form-group">
                            <div class="col-xs-2">
                                <input type="text" name="studentId" class="form-control" placeholder="请输入学号">
                            </div>
                            <button type="submit" class="btn btn-default"> 查找</button>
                            <a href="/measure/manager/user/index"><button type="button" class="btn btn-default"> 新增</button></a>
                        </div>
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/measure/manager/user/find/all?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..userPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/measure/manager/user/find/all?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte userPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/measure/manager/user/find/all?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>