<%--
  Created by IntelliJ IDEA.
  User: FuFangzhou
  Date: 2017/6/12
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <title>题目</title>
    <link rel="stylesheet" type="text/css"
          href="/css/bootstrap.min.css">
    <style>
        body {
            background: #2e6da4;
        }

        #question_background {
            border-radius: 5px;
            box-shadow: 4px;
            background: whitesmoke;
        }

        #question_title {
            font-size: 30px;
        }

        #top_label {
            font-family: 微软雅黑;
            font-size: 35px;
        }
    </style>
    <script>
        var json = ${questioncontent};
    </script>
</head>
<body>
<jsp:include page="/topbar.jsp"></jsp:include>
<div class="container" id="question_background">
    <div class="row">
        <img src="/source/Question_Lv3.svg" style="display: inline;width: 100px;height: 100px;">
        <label id="top_label">ISA Online Programing</label>
    </div>
    <HR style="FILTER: progid:DXImageTransform.Microsoft.Shadow(color:#987cb9,direction:145,strength:15)" width="80%"
        color=#987cb9 SIZE=1>
    <div class="col-md-offset-1 col-md-10">
        <div class="row">
            <label class="text-primary" id="question_title">1005.继续(3n+1)猜想</label>
        </div>
        <div class="row">
            <p class="text-warning" id="question_tip">
                TimeLimit:1000ms;
            </p>
        </div>
        <div class="row">
            <p class="text-info" id="question_content">
                卡拉兹(Callatz)猜想已经在1001中给出了描述。在这个题目里，情况稍微有些复杂。<br>
                当我们验证卡拉兹猜想的时候，为了避免重复计算，可以记录下递推过程中遇到的每一个数。例如对n=3进行验证的时候，我们需要计算3、5、8、4、2、1，则当我们对n=5、8、4、2进行验证的时候，就可以直接判定卡拉兹猜想的真伪，而不需要重复计算，因为这4个数已经在验证3的时候遇到过了，我们称5、8、4、2是被3“覆盖”的数。我们称一个数列中的某个数n为“关键数”，如果n不能被数列中的其他数字所覆盖。<br>
                现在给定一系列待验证的数字，我们只需要验证其中的几个关键数，就可以不必再重复验证余下的数字。你的任务就是找出这些关键数字，并按从大到小的顺序输出它们。<br>
                <strong>输入格式：</strong>每个测试输入包含1个测试用例，第1行给出一个正整数K(<100)，第2行给出K个互不相同的待验证的正整数n(1< n<= 100)的值，数字间用空格隔开。<br>
                <strong>输出格式：</strong>每个测试用例的输出占一行，按从大到小的顺序输出关键数字。数字间用1个空格隔开，但一行中最后一个数字后没有空格。<br>
                <strong>输入样例：</strong><br>
                6<br>
                3 5 6 7 8 11<br>
                <strong>输出样例：</strong><br>
                7 6<br>
            </p>
        </div>
        <div class="row">
            <div class="col-md-9 col-xs-9">
                <textarea class="bg-info text-info" id="question_content_submit" style="width: 100%; height:  120px; border-radius: 5px"></textarea>
            </div>
            <div class="col-md-3 col-xs-3">
                <img src='/source/submit.svg' style='height: 80px;cursor: pointer'>
            </div>
        </div>
        <div class="row">
            <div class="alert alert-warning" style="display: none">

            </div>
        </div>
        <br>
        <br>
    </div>
</div>
<script src="/js/question.js"></script>
</body>
</html>
