<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-bordered">
    <caption><label class="text-primary">帖子列表</label></caption>
    <thead>
    <tr>
        <th>Title</th>
        <th>Owner</th>
        <th>Operate</th>
    </tr>
    </thead>
    <tbody id="manage_topic_tbody">
    <tr>
        <td>Android入门</td>
        <td>Debug</td>
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