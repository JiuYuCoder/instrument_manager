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
                            <th>记录ID</th>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>仪器码</th>
                            <th>仪器名称</th>
                            <th>申请时间</th>
                            <th>是否批准</th>
                            <th>批准时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list returnRecordPage.content as returnRecord>
                        <tr>
                            <td>${returnRecord.returnId}</td>
                            <td>${returnRecord.studentId}</td>
                            <td>${returnRecord.studentName}</td>
                            <td>${returnRecord.instrumentId}</td>
                            <td>${returnRecord.instrumentName}</td>
                            <td>${returnRecord.returnTime}</td>
                            <#if returnRecord.isConfirm == 1>
                                <td>已批准</td>
                            <#else>
                                <td>未批准</td>
                            </#if>
                            <td>${returnRecord.confirmTime!''}</td>
                            <#if returnRecord.isConfirm == 1>
                                <td>已批准</td>
                            <#else>
                               <td><a href="/measure/manager/return/record/update?instrumentId=${returnRecord.instrumentId}&isConfirm=0">批准</a></td>
                            </#if>
                            <td><a href="/measure/manager/return/record/delete?recordId=${returnRecord.returnId}">删除</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <form role="form" method="get" action="/measure/manager/return/record/find?studentId=">
                        <div class="form-group">
                            <div class="col-xs-2">
                                <input type="text" name="studentId" class="form-control" placeholder="请输入学号">
                            </div>
                            <button type="submit" class="btn btn-default"> 查找</button>
                        </div>
                        <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/measure/manager/return/record/find/all?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..returnRecordPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/measure/manager/return/record/find/all?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte returnRecordPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/measure/manager/borrow/record/find/all?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                        </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>