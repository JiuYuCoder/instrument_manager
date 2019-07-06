<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/measure/manager/instrument/save">
                        <div class="form-group">
                            <label>仪器码</label>
                            <input name="instrumentId" type="text" class="form-control" value="${(instrument.instrumentId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>仪器名称</label>
                            <input name="instrumentName" type="text" class="form-control" value="${(instrument.instrumentName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>是否归还（0为未归还，1为已归还）</label>
                            <input name="isReturn" type="number" class="form-control" value="${(instrument.isReturn)!''}"/>
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