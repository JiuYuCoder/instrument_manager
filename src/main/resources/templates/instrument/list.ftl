<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>仪器码</th>
                            <th>仪器名称</th>
                            <th>是否归还</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list instrumentPage.content as instrument>
                        <tr>
                            <td>${instrument.instrumentId}</td>
                            <td>${instrument.instrumentName}</td>
                            <td>
                                <#if instrument.isReturn == 1>
                                    已归还
                                <#else>
                                    未归还
                                </#if>
                            </td>

                            <td><a href="/measure/manager/instrument/index?instrumentId=${instrument.instrumentId}">修改</a></td>
                            <td><a href="/measure/manager/instrument/delete?instrumentId=${instrument.instrumentId}">删除</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <form role="form" method="get" action="/measure/manager/instrument/find/one?instrumentId=">
                        <div class="form-group">
                            <div class="col-xs-2">
                                <input type="text" name="instrumentId" class="form-control" placeholder="请输入仪器码">
                            </div>
                            <button type="submit" class="btn btn-default"> 查找</button>
                            <a href="/measure/manager/instrument/index"><button type="button" class="btn btn-default"> 新增</button></a>
                        </div>
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/measure/manager/instrument/find/all?page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>

                    <#list 1..instrumentPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/measure/manager/instrument/find/all?page=${index}&size=${size}">${index}</a></li>
                        </#if>
                    </#list>

                    <#if currentPage gte instrumentPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/measure/manager/instrument/find/all?page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>