$(function () {
    /**myself:
     * account  帐号
     * create   发帖数
     * follow   跟帖数
     */
    var myself;

    /**
     * element in list
     * id       帖子的编号[Hide Not Exist In View]
     * title    标题
     * owner    持有者
     */
    var list;

    var oAccount = $('#topic_account').get(0);
    var oCreate = $('#topic_create').get(0);
    var oFollow = $('#topic_follow').get(0);

    var syncView_Myself = function () {
        oAccount.innerHTML = myself.account;
        oCreate.innerHTML = myself.create;
        oFollow.innerHTML = myself.follow;
    };

    var readData_Myself = function () {
        $.ajax({
            url: "/model/topicmyself",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                if (json.status) {
                    myself = json.obj;
                    syncView_Myself();
                }
            }
        })

    };

    var syncView_List = function () {
        var tbody = $('#topic_tbody').get(0);
        tbody.innerHTML = "";
        for (var i = 0; i < list.length; i++) {
            var oTr = document.createElement('tr');
            var oTd_Id = document.createElement('td');
            var oTd_Title = document.createElement('td');
            var oTd_Owner = document.createElement('td');

            oTd_Id.style.display = 'none';
            oTd_Id.innerHTML = list[i].id;
            oTd_Title.innerHTML = list[i].title;
            oTd_Owner.innerHTML = list[i].owner;

            oTr.appendChild(oTd_Id);
            oTr.appendChild(oTd_Title);
            oTr.appendChild(oTd_Owner);

            oTr.onclick = function () {
                var index = this.children[0].innerHTML;
                gotoTopic(index);
            };

            tbody.appendChild(oTr);
        }
    };
    var readData_List = function () {
        $.ajax({
            url: "/model/topiclist",
            type: "POST",
            success: function (json) {
                json = JSON.parse(json);
                if (json.status) {
                    list = json.obj;
                    syncView_List();
                }
            }
        })
    };

    var gotoTopic = function (index) {
        var temp = document.createElement("form");
        temp.action = "/topic/content?id=" + index;
        temp.method = "post";
        temp.style.display = "none";
        temp.submit();
    };

    var Initialization = function () {
        setTimeout(function () {
            if (!auto_login) {
                window.location = "/";
                alert("请线登陆");
            }
            $('#background').fadeIn();
            readData_Myself();
            readData_List();
        }, 2500);
    };
    Initialization();
});