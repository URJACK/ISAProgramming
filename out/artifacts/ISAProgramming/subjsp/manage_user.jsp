<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-bordered">
    <caption><label class="text-primary">成员列表</label></caption>
    <thead>
    <tr>
        <th>Account</th>
        <th>Email</th>
        <th>Operate</th>
    </tr>
    </thead>
    <tbody id="manage_user_tbody">
    <tr>
        <td>Fufangzhou</td>
        <td>316585692@qq.com</td>
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