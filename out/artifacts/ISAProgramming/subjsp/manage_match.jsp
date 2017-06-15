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
    <tr>
        <td>1</td>
        <td>Fufangzhou</td>
        <td>开学第一次Java比赛</td>
        <td>65%</td>
        <td>
            <div class="btn-group">
                <a class="btn btn-default" data-toggle="popover" data-trigger='hover' data-placement='bottom'
                   data-content="删除">
                    <img src="/source/delete.svg">
                </a>
                <a class="btn btn-default" data-toggle="popover" data-trigger='hover' data-placement='bottom'
                   data-content="修改">
                    <img src="/source/query.svg">
                </a>
            </div>
        </td>
    </tr>
    </tbody>
</table>