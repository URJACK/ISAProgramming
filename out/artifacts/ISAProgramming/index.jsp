<%--
  Created by IntelliJ IDEA.
  User: FuFangzhou
  Date: 2017/5/30
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <title>主页</title>
    <link rel="stylesheet" type="text/css"
          href="/css/bootstrap.min.css">
    <style>
        #myCarousel .carousel-caption{
            font-size: 30px;
        }
    </style>
</head>
<body>
<jsp:include page="/topbar.jsp"></jsp:include>
<div id="myCarousel" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active" id="carousel_item_1">
            <img src="/img/home1" alt="ISA Programing">
            <div class="carousel-caption">
                <label class="text-info">ISA Programing</label>
            </div>
        </div>
        <div class="item" id="carousel_item_2">
            <img src="/img/home2" alt="EDA Online Testing">
            <div class="carousel-caption">
                <label class="text-info">EDA Online Testing</label>
            </div>
        </div>
        <div class="item" id="carousel_item_3">
            <img src="/img/home3" alt="For More">
            <div class="carousel-caption">
                <label class="text-info">For More</label>
            </div>
        </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarousel"
       data-slide="prev">&lsaquo;</a>
    <a class="carousel-control right" href="#myCarousel"
       data-slide="next">&rsaquo;</a>
</div>
<script src="/index.js"></script>
</body>
</html>
