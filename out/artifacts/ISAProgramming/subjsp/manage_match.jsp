<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-bordered">
    <caption><label class="text-primary">比赛列表</label></caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Owner</th>
        <th>Name</th>
        <th>Progress</th>
        <th>Operate</th>
    </tr>
    </thead>
    <tbody id="manage_match_tbody">
    </tbody>
</table>
<div class="row">
    <div class="col-md-offset-10 col-md-1 col-xs-offset-8 col-xs-2">
        <a class="btn btn-default" id="manage_match_uppage">上页</a>
    </div>
    <div class="col-md-1 col-xs-2">
        <a class="btn btn-default" id="manage_match_downpage">下页</a>
    </div>
</div>